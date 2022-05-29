package web.antiplagiarism.controllers;


import algorithms.FileWalker;
import algorithms.comparators.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

import static algorithms.UsefulMethods.*;

@Controller
public class FileUploadController {

    private static final String uploadDirectory =  "./uploads";
    private static final ArrayList<String> extensions = new ArrayList<>(List.of(new String[]{"zip", "rar"}));

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
                "Invalid file extension, please upload a file with the extension .zip, .rar");

        Cookie cookie = new Cookie("uuid", UUID.randomUUID().toString());
        String uuid = cookie.getValue();
        String uploadLocalDir = uploadDirectory + '\\' + uuid;

        File uploadDir = new File(uploadLocalDir);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        createDirectories(file1.length, uploadLocalDir);
        unzipMultipartFiles(file1, uploadLocalDir);

        for (int i = 0; i < file1.length; i++) {
            FileWalker fw1 = new FileWalker();
            fw1.walk(uploadLocalDir + '\\' + i);
            for (int j = i + 1; j < file1.length; j++) {
                FileWalker fw2 = new FileWalker();
                fw2.walk(uploadLocalDir + '\\' + String.format("%d", j));
                String archivenames = String.format("%s\n%s", file1[i].getOriginalFilename(), file1[j].getOriginalFilename());
                cartesianProduct(fw1.getListFilesBsl(), fw2.getListFilesBsl(), uploadLocalDir, form, i, j);
                if(form.isCheckXml())
                    cartesianProductXml(fw1.getListFilesXml(), fw2.getListFilesXml(), uploadLocalDir, form, i, j);
                String dir = uploadLocalDir + '\\' + "Results" + '\\' + i + '_' + j + '\\' + "archivenames.txt";
                try {
                    Files.write(Path.of(dir), archivenames.getBytes());
                } catch (NoSuchFileException e) {
                    new File(uploadLocalDir + '\\' + "Results").mkdir();
                    new File(uploadLocalDir + '\\' + "Results" + '\\' + i + '_' + j).mkdir();
                    Files.write(Path.of(dir), archivenames.getBytes());
                }
            }
        }

        response.addCookie(cookie);

        Cookie sort = new Cookie("sort", sorting);
        Cookie orderBy = new Cookie("orderBy", sortOrder);

        response.addCookie(sort);
        response.addCookie(orderBy);

        return "redirect:/files";
    }


    @RequestMapping("/files")
    public String showFiles(Model model, HttpServletRequest request) throws IOException, CookieException {
        String uuid;
        try {
            uuid = Objects.requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();
        }
        catch (NullPointerException e){
            throw new CookieException("Cookie not found. Please reupload your files again.");
        }

        String uploadLocalDir = uploadDirectory + '\\' + uuid + '\\' + "Results";

        File root = new File(uploadLocalDir);
        File[] list = root.listFiles();
        if (list == null) return "table";
        ArrayList<Row> rows = getTableRows(list, uploadLocalDir, true);


        model.addAttribute("rows", rows);
        return "table";
    }

    @RequestMapping("/files/{id}")
    public String getTable(Model model, @PathVariable(value = "id") Integer id, HttpServletRequest request)
            throws CookieException, IOException {

        String uuid, sorting, sortOrder;
        try {
            sorting = Objects.requireNonNull(WebUtils.getCookie(request, "sort")).getValue();
            sortOrder = Objects.requireNonNull(WebUtils.getCookie(request, "orderBy")).getValue();
            uuid = Objects.requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();
        }
        catch (NullPointerException e){
            throw new CookieException("Cookie not found. Please reupload your files again.");
        }

        String uploadLocalDir = uploadDirectory + '\\' + uuid + '\\' + "Results";

        String path = getDirNameByID(uploadLocalDir, id);
        uploadLocalDir = uploadLocalDir + '\\' + path;

        File root = new File(uploadLocalDir);
        File[] list = root.listFiles();
        if (list == null) return "uploads";
        ArrayList<Row> rows = getTableRows(list, uploadLocalDir, false);

        switch (sorting) {
            case "score" : if (sortOrder.equals("desc")) rows.sort(new ScoreComparatorDESC()); else rows.sort(new ScoreComparatorASC()); break;
            case "index" : if (sortOrder.equals("desc")) rows.sort(new IndexComparatorDESC()); else rows.sort(new IndexComparatorASC()); break;
            case "firstFile" : if (sortOrder.equals("desc")) rows.sort(new FirstFileComparatorDESC()); else rows.sort(new FirstFileComparatorASC()); break;
            case "secondFile" : if (sortOrder.equals("desc")) rows.sort(new SecondFileComparatorDESC()); else rows.sort(new SecondFileComparatorASC()); break;
        }

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


