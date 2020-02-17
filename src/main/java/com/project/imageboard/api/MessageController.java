package com.project.imageboard.api;

import com.project.imageboard.model.MessageThread;
import com.project.imageboard.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/thread:{id}")
    public String inTread(@PathVariable("id") int id, Model model) {
        model.addAttribute("messages", messageService.getMessages(id));
        return "thread-inside";
    }

    @PostMapping(value = "/thread:{id}", params = "Post")
    public String postMessage(@PathVariable("id") int id, @RequestParam(name = "text") String text,
                              @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null){
            messageService.addMessage(id, text, imageFile);
        }
        else messageService.addMessage(id, text);
        return "redirect:/thread:{id}";
    }

}
