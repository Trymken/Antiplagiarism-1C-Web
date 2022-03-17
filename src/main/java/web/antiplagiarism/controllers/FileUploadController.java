package web.antiplagiarism.controllers;


import algorithms.Start;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.unbescape.html.HtmlEscape;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static algorithms.Algorithms.highlightText;

@Controller
public class FileUploadController {

    public static String uploadDirectory =  "./uploads";
    public static String uuid;
    public static Start start;


    @RequestMapping("/")
    public String uploadPage(Model model){
        return "index";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file1") MultipartFile[] files1,
                                   @RequestParam("file2") MultipartFile[] files2,
                                   RedirectAttributes redirectAttributes) throws IOException {

        if (files1 != null) {
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }

        uuid = UUID.randomUUID().toString();
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

        for (MultipartFile file : files1) {
            String filename = file.getOriginalFilename();
            Path path = Paths.get(dir1.getAbsolutePath(), filename);
            Files.write(path, file.getBytes());
        }

        for (MultipartFile file : files2) {
            String filename = file.getOriginalFilename();
            Path path = Paths.get(dir2.getAbsolutePath(), filename);
            Files.write(path, file.getBytes());
        }
        redirectAttributes.addFlashAttribute("filesPath", uploadDir.getAbsolutePath());

        return "redirect:/uploads";
    }

    @RequestMapping("/uploads")
    public String uploadsListPage(@ModelAttribute("filesPath") String folderPath, Model model) throws IOException, NoSuchAlgorithmException {
        File folder1 = new File(folderPath + '\\' + '1');
        File folder2 = new File(folderPath + '\\' + '2');

        File[] listOfFiles1 = folder1.listFiles();
        File[] listOfFiles2 = folder2.listFiles();

        model.addAttribute("files1", listOfFiles1);
        model.addAttribute("files2", listOfFiles2);

        if(start == null){
            start = new Start(listOfFiles1[0].getAbsolutePath(), listOfFiles2[0].getAbsolutePath(), "sha-1");
        }

        return "uploads";
    }

    @RequestMapping("/file/{index}/{filename}")
    public String show(@PathVariable("index") Character index, @PathVariable("filename") String filename, Model model) throws IOException {
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

        model.addAttribute("content", result);
        return "uploads";
    }

}