package com.example.Enotes_springboot_project.controller;

import com.example.Enotes_springboot_project.entity.User;
import com.example.Enotes_springboot_project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session){
       boolean f = userService.existEmailCheck(user.getEmail());
       if(f){
           session.setAttribute("msg","email already exist");
       }
       else {
           User newUser = userService.saveUser(user);
           if (newUser!=null){
               session.setAttribute("msg","Registration successfully");
           }
           else {
               session.setAttribute("msg","some wrong due to error on the server");
           }
       }

        return "redirect:/register";
    }




    @GetMapping("/base")
    public String base(){
        return "base";
    }
    @GetMapping("/signin")
    public String login(){
        return "login";
    }


}
