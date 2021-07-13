package com.dsvisualiser.dsvisualiser.controller;


import com.dsvisualiser.dsvisualiser.model.Node;
import com.dsvisualiser.dsvisualiser.service.ViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1")

public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @RequestMapping(value = "/leftview", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getView(@RequestBody List<Integer> list) {
        HashMap<Integer, Node> q = new HashMap<>();
        viewService.leftViewUtil(viewService.insert(list,q,1).get(1),1);

        return new ResponseEntity(viewService.getLlist(),HttpStatus.CREATED);

    }
    @RequestMapping(value = "/fullview", method = RequestMethod.POST)
    public ResponseEntity<List<List>> getFullView(@RequestBody List<Integer> list) {
        HashMap<Integer, Node> q = new HashMap<>();

        return new ResponseEntity(viewService.printLevelOrder(viewService.insert(list,q,1).get(1)),HttpStatus.CREATED);

    }
}