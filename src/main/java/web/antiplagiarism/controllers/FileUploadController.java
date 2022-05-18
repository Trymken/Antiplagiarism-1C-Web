package web.antiplagiarism.controllers;


import algorithms.FileWalker;
import algorithms.Start;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static algorithms.Algorithms.*;

@Controller
public class FileUploadController {

    public static String uploadDirectory =  "./uploads";
    public static Start start;


    @RequestMapping("/")
    public String uploadPage(Model model){
        return "index";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file1") MultipartFile file1,
                                   @RequestParam("file2") MultipartFile file2,
                                   RedirectAttributes redirectAttributes,
                                   HttpServletResponse response) throws IOException {

        Cookie cookie = new Cookie("uuid", UUID.randomUUID().toString());

        if (file1 != null) {
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }

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

        String filename = file1.getOriginalFilename();
        Path path = Paths.get(dir1.getAbsolutePath(), filename);
        unZipFile(file1.getInputStream(), path.toString());

        filename = file2.getOriginalFilename();
        path = Paths.get(dir2.getAbsolutePath(), filename);
        unZipFile(file2.getInputStream(), path.toString());

        response.addCookie(cookie);

        return "redirect:/files";
    }

    @RequestMapping("/uploads")
    public String uploadsListPage(@ModelAttribute("filesPath") String folderPath,
                                  Model model,
                                  RedirectAttributes attributes) throws IOException, NoSuchAlgorithmException {

        File folder1 = new File(folderPath + '\\' + '1');
        File folder2 = new File(folderPath + '\\' + '2');

        File[] listOfFiles1 = folder1.listFiles();
        File[] listOfFiles2 = folder2.listFiles();

        model.addAttribute("files1", listOfFiles1);
        model.addAttribute("files2", listOfFiles2);

        start = new Start(listOfFiles1[0].getAbsolutePath(), listOfFiles2[0].getAbsolutePath(), "sha-1");

        attributes.addFlashAttribute("file1", listOfFiles1[0].getAbsolutePath());
        attributes.addFlashAttribute("file2", listOfFiles2[0].getAbsolutePath());
        attributes.addFlashAttribute("score", start.getWinnowing().getScore());

        return "redirect:/files";
    }

    @RequestMapping("/file/{index}/{filename}")
    public String show(@PathVariable("index") Character index,
                       @PathVariable("filename") String filename,
                       Model model,
                       HttpServletRequest request) throws IOException {

        Cookie cookie = WebUtils.getCookie(request, "uuid");
        String uuid = cookie.getValue();

        String folderPath = uploadDirectory + '\\' + uuid;

        File folder1 = new File(folderPath + '\\' + '1');
        File folder2 = new File(folderPath + '\\' + '2');

        File[] listOfFiles1 = folder1.listFiles();
        File[] listOfFiles2 = folder2.listFiles();

        model.addAttribute("files1", listOfFiles1);
        model.addAttribute("files2", listOfFiles2);


        List<String> content = Files.readAllLines(Path.of(folderPath + '\\' + index + '\\' + filename ));
        String result = "";

        ArrayList<int[]> list;
        if(index == '1')
            list = start.getIntervals1();
        else
            list = start.getIntervals2();

        result = highlightText(content, list);

        if(index == '1'){
            model.addAttribute("content1", result);
        } else {
            model.addAttribute("content2", result);
        }

        return "uploads";
    }


    @RequestMapping("/files")
    public String showFiles(Model model, HttpServletRequest request) throws
            IOException,
            NoSuchAlgorithmException {

        String uploadLocalDir = uploadDirectory + '\\' + Objects.
                requireNonNull(WebUtils.getCookie(request, "uuid")).getValue();

//        File file1 = Objects.requireNonNull(new File(uploadLocalDir + '\\' + '1').listFiles())[0];
//        File file2 = Objects.requireNonNull(new File(uploadLocalDir + '\\' + '2').listFiles())[0];
//
//        start = new Start(file1.getAbsolutePath(), file2.getAbsolutePath(), "sha-1");
//
//        List<String> content1 = Files.readAllLines(Path.of(file1.getPath()));
//        List<String> content2 = Files.readAllLines(Path.of(file2.getPath()));
//
//        String result1, result2;
//        ArrayList<int[]> list1, list2;
//
//        list1 = start.getIntervals1();
//        list2 = start.getIntervals2();
//
//        result1 = highlightText(content1, list1);
//        result2 = highlightText(content2, list2);

        FileWalker fw1 = new FileWalker();
        FileWalker fw2 = new FileWalker();
        fw1.walk(uploadLocalDir + '\\' + '1');
        fw2.walk(uploadLocalDir + '\\' + '2');

        cartesianProduct(fw1.getListFiles(), fw2.getListFiles(), uploadLocalDir);

//        model.addAttribute("content1", result1);
//        model.addAttribute("content2", result2);
//        model.addAttribute("score", "Score: " + start.getWinnowing().getScore());

        return "files";
    }
}


