package com.manatee.mymap.controllers;

import com.manatee.mymap.entities.WantedObject;
import com.manatee.mymap.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private Service service;

    public MainController(
        @Autowired Service service
    ) {
        this.service = service;
    }

    @GetMapping
    public String showMainPage(final Model wantedObjectModel) {
        WantedObject wantedObject = new WantedObject();
        wantedObjectModel.addAttribute("wantedObject", wantedObject);
        return "main-page";
    }

    @GetMapping("/result")
    public String showResultPage(@ModelAttribute("wantedObject") final WantedObject wantedObject) {
        System.out.println(service.getSearchResult(wantedObject.getObjectName()));
        return "result-page";
    }
}
