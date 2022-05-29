package web.antiplagiarism.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import web.antiplagiarism.exceptions.CookieException;
import web.antiplagiarism.exceptions.InvalidFileExtensionException;
import web.antiplagiarism.exceptions.Response;

import java.io.FileNotFoundException;

@ControllerAdvice
public class AdviceController {

    @RequestMapping("/error")
    @ExceptionHandler(CookieException.class)
    public String handleException(Model model, CookieException e) {
        Response response = new Response(e.getMessage());
        model.addAttribute("response",response);
        return "error";
    }

    @RequestMapping("/error")
    @ExceptionHandler(InvalidFileExtensionException.class)
    public String handleException(Model model, InvalidFileExtensionException e) {
        Response response = new Response(e.getMessage());
        model.addAttribute("response",response);
        return "error";
    }

    @RequestMapping("/error")
    @ExceptionHandler(FileNotFoundException.class)
    public String handleException(Model model, FileNotFoundException e) {
        Response response = new Response(e.getMessage());
        model.addAttribute("response",response);
        return "error";
    }
}
