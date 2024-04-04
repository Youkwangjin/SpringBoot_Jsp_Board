package com.spring.lots.board.controller.index;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String indexPage() {
        return "index/index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "index/register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "index/login";
    }
}
