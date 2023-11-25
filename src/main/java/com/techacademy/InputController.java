package com.techacademy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InputController {

    @GetMapping({ "/input", "/input/" })
    public String getInput(@RequestParam(name = "previous", required = false) String val, Model model) {
        model.addAttribute("val", val);
        return "input";
    }

}