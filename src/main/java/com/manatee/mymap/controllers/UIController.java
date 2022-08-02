package com.manatee.mymap.controllers;

import com.manatee.mymap.entities.WantedObject;
import com.manatee.mymap.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UIController {
    private Service service;

    public UIController(
        @Autowired Service service
    ) {
        this.service = service;
    }

    @RequestMapping
    public String showMainPage(final Model wantedObjectModel) {
        WantedObject wantedObject = new WantedObject();
        wantedObjectModel.addAttribute("wantedObject", wantedObject);
        return "main-page";
    }

    @RequestMapping("/result")
    public String showResultPage(
        @ModelAttribute("wantedObject") final WantedObject wantedObject,
        final Model searchResultModel
    ) {
        final var searchResult = service.getSearchResult(wantedObject.getObjectName());
        searchResultModel.addAttribute("resultModel", searchResult);
        return "result-page";
    }

    @RequestMapping("/{name}")
    public String showOneObject(
        @PathVariable("name") final String name,
        final Model objectModel
    ){
        final var searchResult = service.getSearchResult(name);
        var foundObject = searchResult.stream()
            .findFirst().orElseThrow();
        objectModel.addAttribute("objectModel", foundObject);
        return "object-page";
    }
}
