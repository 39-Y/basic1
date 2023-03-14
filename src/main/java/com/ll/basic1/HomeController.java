package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HomeController {
    int count=0;
    @GetMapping("/home/main")
    public String showMain(){
        return "Hi dear!!";
    }

    @GetMapping("/home/main2")
    public String showMain2(){
        return "Googbye dear ~";
    }

    @GetMapping("/home/main3")
    public String showMain3(){
        return "Well.. I dont think so...";
    }

    @GetMapping("/home/increase")
    public int showIncrese(){
        return count++;
    }

}
