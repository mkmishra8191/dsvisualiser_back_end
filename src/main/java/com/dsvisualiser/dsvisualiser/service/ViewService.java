package com.dsvisualiser.dsvisualiser.service;
import com.dsvisualiser.dsvisualiser.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ViewService {

    int max_level;

    @Autowired
    public ViewService() {
    }
    // A wrapper over leftViewUtil()
    public List<Integer> leftView(Node node, int level,List<Integer> list) {
        this.max_level=0;
        leftViewUtil(node,level,list);
        return list;

    }
    // recursive function to collect left view
    public void leftViewUtil(Node node,int level,List<Integer> list)
    {
        // Base Case
        if (node == null)
            return;

        // If this is the first node of its level
        if (this.max_level < level) {
            list.add(node.getData());
            this.max_level = level;
        }

        // Recursion for left and right subtrees
        leftViewUtil(node.getLeft(),level + 1,list);
        leftViewUtil(node.getRight(),level + 1,list);

    }



}
