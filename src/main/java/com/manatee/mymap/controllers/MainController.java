package com.manatee.mymap.controllers;

import com.manatee.mymap.entities.WantedObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String showMainPage(final Model wantedObjectModel) {
        WantedObject wantedObject = new WantedObject();
        wantedObjectModel.addAttribute("wantedObject", wantedObject);
        return "main-page";
    }

    @GetMapping("/result")
    public String showResultPage(@ModelAttribute("wantedObject") final WantedObject wantedObject) {

        return "result-page";
    }
}
