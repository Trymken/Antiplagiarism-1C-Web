package web.antiplagiarism.controllers;


import algorithms.FileWalker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import org.xml.sax.SAXException;
import struct.Form;
import struct.Row;
import web.antiplagiarism.exceptions.CookieException;
import web.antiplagiarism.exceptions.InvalidFileExtensionException;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static algorithms.Utils.*;

@Controller
public class FileUploadController {

    private static final String uploadDirectory =  "./uploads";
    private static final ArrayList<String> extensions = new ArrayList<>(List.of(new String[]{"zip"}));


    @RequestMapping("/")
    public String uploadPage(Model model){
        model.addAttribute("myform", new Form());
        return "index";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file1") MultipartFile[] file1,
                                   @RequestParam("sorting") String sorting,
                                   @RequestParam("sortOrder") String sortOrder,
                                   @ModelAttribute Form form,
                                   HttpServletResponse response)
            throws IOException, NoSuchAlgorithmException, InvalidFileExtensionException,
            ParserConfigurationException, SAXException {

        if(file1.length <= 1){
            throw new FileNotFoundException(
                    "The number of files must be greater than or equal to 2, please upload files again");
        }

        if (isValidExtension(file1, extensions)){
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        } else throw new InvalidFileExtensionException(
                "Invalid file extension, please upload a file with the extension .zip");

        Cookie cookie = new Cookie("uuid", UUID.randomUUID().toString());
        String uuid = cookie.getValue();
        String uploadLocalDir = uploadDirectory + '\\' + uuid;

        File uploadDir = new File(uploadLocalDir);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        createDirectories(file1.length, uploadLocalDir);
        unzipMultipartFiles(file1, uploadLocalDir);
        checkFiles(file1, form, uploadLocalDir);

        response.addCookie(cookie);

        Cookie sort = new Cookie("sort", sorting);
        Cookie orderBy = new Cookie("orderBy", sortOrder);

        response.addCookie(sort);
        response.addCookie(orderBy);

        return "redirect:/files";
    }




    @RequestMapping("/files")
    public String showFiles(Model model,
                            HttpServletRequest request,
                            @ModelAttribute("greaterScore") String attribute,
                            @ModelAttribute("numOfFiles") String numberOfFiles)
            throws IOException, CookieException {

        String uuid;
        double score;
        int numOfFiles;
        try {
            uuid = Objects.requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();
        }
        catch (NullPointerException e){
            throw new CookieException("Cookie not found. Please reupload your files again.");
        }

        try {
            score = Double.parseDouble(attribute);
            if (score < 0) score = 0.0;
        } catch (Exception e){
            score = 0.0;
        }

        try {
            numOfFiles = Integer.parseInt(numberOfFiles);
            if (numOfFiles < 0) numOfFiles = 1;
        } catch (Exception e){
            numOfFiles = 1;
        }

        System.out.println(numberOfFiles);

        String uploadLocalDir = uploadDirectory + '\\' + uuid + '\\' + "Results";

        File root = new File(uploadLocalDir);
        File[] list = root.listFiles();
        if (list == null) return "table";
        ArrayList<Row> rows = getTableRows(list, uploadLocalDir, true);
        rows = getTableRowsByScore(rows, uploadLocalDir, score, numOfFiles);

        model.addAttribute("rows", rows);
        return "table";
    }

    @PostMapping("/files")
    public String showfilesPost(@RequestParam("greaterScore") Double score,
                                @RequestParam("numberOfFiles") Integer numFiles,
                                RedirectAttributes ra){

        ra.addFlashAttribute("greaterScore", score);
        ra.addFlashAttribute("numOfFiles", numFiles);
        return "redirect:/files";
    }

    @RequestMapping("/files/{id}")
    public String getTable(Model model, @PathVariable(value = "id") Integer id, HttpServletRequest request)
            throws CookieException, IOException {

        String uuid = "", sorting = "", sortOrder;
        try {
            uuid = Objects.requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();
            sorting = Objects.requireNonNull(WebUtils.getCookie(request, "sort")).getValue();
            sortOrder = Objects.requireNonNull(WebUtils.getCookie(request, "orderBy")).getValue();
        }
        catch (NullPointerException e){
                sortOrder = "desc";
            if (sorting.equals(""))
                sorting = "index";
            if (uuid.equals(""))
                throw new CookieException("Cookie not found. Please reupload your files again.");
        }

        String uploadLocalDir = uploadDirectory + '\\' + uuid + '\\' + "Results";

        String path = getDirNameByID(uploadLocalDir, id);
        uploadLocalDir = uploadLocalDir + '\\' + path;

        File root = new File(uploadLocalDir);
        File[] list = root.listFiles();
        if (list == null) return "uploads";
        ArrayList<Row> rows = getTableRows(list, uploadLocalDir, false);

        sortTableRows(sorting, sortOrder, rows);

        model.addAttribute("rows", rows);
        model.addAttribute("tableId", id);

        return "uploads";
    }

    @RequestMapping("/files/{archiveid}/{id}")
    public String getResult(Model model,
                            @PathVariable(value="archiveid") Integer arcId,
                            @PathVariable(value="id") Integer id, HttpServletRequest request)
            throws IOException, CookieException {

        String uuid;
        try {
            uuid = Objects.requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();
        }
        catch (NullPointerException e){
            throw new CookieException("Cookie not found. Please reupload your files again.");
        }

        String uploadLocalDir = uploadDirectory + '\\' + uuid + '\\' + "Results";
        String path = getDirNameByID(uploadLocalDir, arcId);
        uploadLocalDir = uploadLocalDir + '\\' + path;
        path = getDirNameByID(uploadLocalDir, id);
        uploadLocalDir = uploadLocalDir + '\\' + path;

        String file1 = Files.readString(Path.of(uploadLocalDir +'\\' + "1.txt"));
        String file2 = Files.readString(Path.of(uploadLocalDir + '\\' + "2.txt"));
        List<String> score = Files.readAllLines(Path.of(uploadLocalDir + '\\' + "score.txt"));
        List<String> filePaths = Files.readAllLines(Path.of(uploadLocalDir + '\\' + "filenames.txt"));

        String dir = filePaths.get(2).split(uuid)[1].substring(1);
        dir = dir.substring(dir.indexOf('\\') + 1);
        filePaths.set(2, dir);
        dir = filePaths.get(3).split(uuid)[1].substring(1);
        dir = dir.substring(dir.indexOf('\\') + 1);
        filePaths.set(3, dir);

        model.addAttribute("dir1", filePaths.get(2));
        model.addAttribute("dir2", filePaths.get(3));
        model.addAttribute("content1", file1);
        model.addAttribute("content2", file2);
        model.addAttribute("score", score);

        return "files";
    }
}


