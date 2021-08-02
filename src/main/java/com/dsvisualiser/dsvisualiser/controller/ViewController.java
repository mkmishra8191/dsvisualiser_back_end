package com.dsvisualiser.dsvisualiser.controller;


import com.dsvisualiser.dsvisualiser.model.Node;
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
    private Node root;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }
    @RequestMapping(value = "/right_diagonal_view", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getRightDiagonalView(@RequestBody Node root) {
        this.root=root;
        return new ResponseEntity(viewService.rightDiagonalView(this.root,new ArrayList<List<Integer>>()),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/left_diagonal_view", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getLeftDiagonalView(@RequestBody Node root) {
        this.root=root;
        return new ResponseEntity(viewService.leftDiagonalView(this.root,new ArrayList<List<Integer>>()),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/left_view", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getLeftView(@RequestBody Node root) {
        this.root=root;
        return new ResponseEntity(viewService.leftView(this.root,1,new ArrayList<>()),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/top_view", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getTopView(@RequestBody Node root) {
        this.root=root;
        return new ResponseEntity(viewService.topView(this.root,new ArrayList<>()),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/right_view", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getRightView(@RequestBody Node root) {
        this.root=root;
        return new ResponseEntity(viewService.rightView(this.root,1,new ArrayList<>()),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/bottom_view", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> getBottomView(@RequestBody Node root) {
        this.root=root;
        return new ResponseEntity(viewService.bottomView(this.root,new ArrayList<>()),HttpStatus.CREATED);
    }

}