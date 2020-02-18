package com.project.imageboard.api;

import com.project.imageboard.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@Controller
public class ThreadController {

    private final MessageService messageService;

    @Autowired
    public ThreadController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String showThreads(Model model)
    {
        model.addAttribute("threads", messageService.getThreads());
        return "threads-view";
    }

    @PostMapping(value = "/", params = "newThread")
    public String addThread(@RequestParam("header") String header,
                            @RequestParam("text") String text,
                            @RequestParam(name = "imageThread", required = false) MultipartFile imageThread)
    {
        if (!imageThread.isEmpty()){
            messageService.addThread(header, text, imageThread);
        }
        else messageService.addThread(header, text);
        return "redirect:/";
    }

    @PostMapping(value = "/", params = "inThread")
    public String toThread(@RequestParam("threadId") String thread) {
        return "redirect:/thread/" + thread;
    }
}
