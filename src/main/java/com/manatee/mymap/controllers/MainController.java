package com.manatee.mymap.controllers;

import com.manatee.mymap.entities.FoundObject;
import com.manatee.mymap.entities.WantedObject;
import com.manatee.mymap.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String showResultPage(
        @ModelAttribute("wantedObject") final WantedObject wantedObject,
        final Model searchResultModel
    ) {
        final var searchResult = service.getSearchResult(wantedObject.getObjectName());
        searchResultModel.addAttribute("resultModel", searchResult);
        return "result-page";
    }

    @GetMapping("/{name}")
    public String showOneObject(
        @PathVariable("name") final String name,
        final Model objectModel
    ){
        final var searchResult = service.getSearchResult(name);
        var foundObject = searchResult.stream()
            .findFirst().orElseThrow();
        objectModel.addAttribute("objectModel", foundObject);
        return "object";
    }
}
