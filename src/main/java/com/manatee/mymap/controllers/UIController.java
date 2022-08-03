package com.manatee.mymap.controllers;

import com.manatee.mymap.entities.FoundObject;
import com.manatee.mymap.entities.WantedObject;
import com.manatee.mymap.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @RequestMapping("/object/{name}/{id}")
    public String showOneObject(
        @PathVariable("name") final String objectName,
        @PathVariable("id") final Long id,
        final Model objectModel
        ){
        objectModel.addAttribute("objectModel", service.getOneObject(objectName, id));
        return "object-page";
    }
}
