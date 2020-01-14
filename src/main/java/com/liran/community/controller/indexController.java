package com.liran.community.controller;
import com.liran.community.dto.PaginationDTO;
import com.liran.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class indexController {
    @Autowired
    private QuestionService QuestionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "2")Integer size){

        PaginationDTO pagination= QuestionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
