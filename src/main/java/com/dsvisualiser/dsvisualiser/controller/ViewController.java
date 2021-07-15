package com.dsvisualiser.dsvisualiser.controller;


import com.dsvisualiser.dsvisualiser.service.ViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1")

public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }


    @RequestMapping(value = "/left_view", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getView(@RequestBody List<Integer> list) {
        return new ResponseEntity(viewService.leftView((viewService.insert(list,new HashMap<>(),1)),1,new ArrayList<>()),HttpStatus.CREATED);
    }

}