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
        wantedObjectModel.addAttribute("wantedObject", new WantedObject());
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

    @RequestMapping("/result/object/{name}")
    public String showOneObject(
        @PathVariable("name") final String objectName,
        final Model objectModel
    ){
        objectModel.addAttribute("objectModel", service.getOneObject(objectName));
        return "object-page";
    }
}
