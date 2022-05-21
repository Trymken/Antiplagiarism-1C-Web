package web.antiplagiarism.controllers;


import algorithms.FileWalker;
import algorithms.comparators.*;
import algorithms.UnZip;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import struct.Form;
import struct.Row;
import web.antiplagiarism.exceptions.CookieException;
import web.antiplagiarism.exceptions.InvalidFileExtensionException;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static algorithms.Algorithms.*;

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
    public String handleFileUpload(@RequestParam("file1") MultipartFile file1,
                                   @RequestParam("file2") MultipartFile file2,
                                   @RequestParam("sorting") String sorting,
                                   @RequestParam("sortOrder") String sortOrder,
                                   @ModelAttribute Form myform,
                                   RedirectAttributes redirectAttributes,
                                   HttpServletResponse response)
            throws IOException, NoSuchAlgorithmException, InvalidFileExtensionException {

        if (file1 != null && file2 != null) {
            String extension1 = getFileExtension(file1.getOriginalFilename());
            String extension2 = getFileExtension(file2.getOriginalFilename());
            if (extensions.contains(extension1) && extensions.contains(extension2)){
                File uploadDir = new File(uploadDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
            } else throw new InvalidFileExtensionException(
                    "Invalid file extension, please upload a file with the extension .zip, .rar");
        }

        Cookie cookie = new Cookie("uuid", UUID.randomUUID().toString());
        String uuid = cookie.getValue();

        File uploadDir = new File(uploadDirectory + '\\' + uuid);
        File dir1 = new File(uploadDirectory + '\\' + uuid + '\\' + '1');
        File dir2 = new File(uploadDirectory + '\\' + uuid + '\\' + '2');

        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        if (!dir1.exists()){
            dir1.mkdir();
        }

        if (!dir2.exists()){
            dir2.mkdir();
        }

        UnZip unZip = new UnZip();

        String filename = file1.getOriginalFilename();
        Path path = Paths.get(dir1.getAbsolutePath(), filename);
        unZip.unZipFile(file1.getInputStream(), path.toString());

        filename = file2.getOriginalFilename();
        path = Paths.get(dir2.getAbsolutePath(), filename);
        unZip.unZipFile(file2.getInputStream(), path.toString());

        String uploadLocalDir = uploadDirectory + '\\' + uuid;

        FileWalker fw1 = new FileWalker();
        FileWalker fw2 = new FileWalker();
        fw1.walk(uploadLocalDir + '\\' + '1');
        fw2.walk(uploadLocalDir + '\\' + '2');

        cartesianProduct(fw1.getListFilesBsl(), fw2.getListFilesBsl(), uploadLocalDir, false, myform);
        cartesianProduct(fw1.getListFilesXml(), fw2.getListFilesXml(), uploadLocalDir, true, myform);

        response.addCookie(cookie);

        Cookie sort = new Cookie("sort", sorting);
        Cookie orderBy = new Cookie("orderBy", sortOrder);

        response.addCookie(sort);
        response.addCookie(orderBy);

        return "redirect:/files";
    }


    @RequestMapping("/files")
    public String showFiles(Model model, HttpServletRequest request) throws IOException, CookieException {
        String uuid, sorting, sortOrder;
        try {
            uuid = Objects.requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();
            sorting = Objects.requireNonNull(WebUtils.getCookie(request, "sort")).getValue();
            sortOrder = Objects.requireNonNull(WebUtils.getCookie(request, "orderBy")).getValue();
        }
        catch (NullPointerException e){
            throw new CookieException("Cookie not found. Please reupload your files again.");
        }

        String uploadLocalDir = uploadDirectory + '\\' + uuid;

        File root = new File(uploadLocalDir + '\\' + "Results");
        File[] list = root.listFiles();
        ArrayList<Row> rows = new ArrayList<>();
        if (list == null) return "uploads";
        int counter = 1;

        for ( File f : list ) {
            if ( f.isDirectory()) {
               rows.add(getTableRow(uploadLocalDir, f.getName(), counter));
               counter++;
            }
        }

        //TODO подумать над другой реализацией
        switch (sorting) {
            case "score" : if (sortOrder.equals("desc")) rows.sort(new ScoreComparatorDESC()); else rows.sort(new ScoreComparatorASC()); break;
            case "index" : if (sortOrder.equals("desc")) rows.sort(new IndexComparatorDESC()); else rows.sort(new IndexComparatorASC()); break;
            case "firstFile" : if (sortOrder.equals("desc")) rows.sort(new FirstFileComparatorDESC()); else rows.sort(new FirstFileComparatorASC()); break;
            case "secondFile" : if (sortOrder.equals("desc")) rows.sort(new SecondFileComparatorDESC()); else rows.sort(new SecondFileComparatorASC()); break;
        }

        model.addAttribute("rows", rows);
        return "uploads";
    }


    @RequestMapping("/files/{id}")
    public String getResult(Model model, @PathVariable(value="id") Integer id, HttpServletRequest request)
            throws IOException, CookieException {

        String uuid;
        try {
            uuid = Objects.requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();
        }
        catch (NullPointerException e){
            throw new CookieException("Cookie not found. Please reupload your files again.");
        }

        String uploadLocalDir = uploadDirectory + '\\' + uuid;
        ArrayList<String> paths = getFilesPath(uploadLocalDir, uuid, id);
        if (paths.isEmpty()) return "files";

        String file1 = Files.readString(Path.of(uploadLocalDir + '\\' + "Results" +
                '\\' + paths.get(2) + '\\' + "1.txt"));

        String file2 = Files.readString(Path.of(uploadLocalDir + '\\' + "Results" +
                '\\' + paths.get(2) + '\\' + "2.txt"));

        List<String> score = Files.readAllLines(Path.of(uploadLocalDir + '\\' + "Results" +
                '\\' + paths.get(2) + '\\' + "score.txt"));

        model.addAttribute("dir1", paths.get(0));
        model.addAttribute("dir2", paths.get(1));
        model.addAttribute("content1", file1);
        model.addAttribute("content2", file2);
        model.addAttribute("score", score);

        return "files";
    }
}


