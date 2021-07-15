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
    // recursive function to print left view
    public void leftViewUtil(Node node,int level,List<Integer> list)
    {
        // Base Case
        if (node == null)
            return;

        // If this is the first node of its level
        if (this.max_level < level) {
            list.add(node.getKey());
            this.max_level = level;
        }

        // Recursion for left and right subtrees
        leftViewUtil(node.getLeft(),level + 1,list);
        leftViewUtil(node.getRight(),level + 1,list);

    }


    /*function to insert elements in binary tree and returning the root */
   public Node  insert(List<Integer> list,HashMap<Integer, Node> q , int level) {
       List<Integer> nList= new ArrayList<>();
       while (!list.isEmpty()){
           list.forEach(i-> {
                if (level == 1){
                    if (!list.contains(2*i) && !list.contains(2*i + 1)) {
                        q.put(i, new Node(i));
                        nList.add(i);
                    }
                } else
                if( q.containsKey(2*i) && q.containsKey(2*i + 1)){
                    Node node= new Node(i);
                    node.setLeft(q.get(2*i));
                    node.setRight(q.get(2*i + 1));
                    q.put(i,node);
                    nList.add(i);
                } else
                    if(q.containsKey(2*i)&& !list.contains(2*i +1)){
                        Node node= new Node(i);
                        node.setLeft(q.get(2*i));
                        q.put(i,node);
                        nList.add(i);
                    }  else
                        if(q.containsKey(2*i +1)&& !list.contains(2*i)){
                            Node node= new Node(i);
                            node.setRight(q.get(2*i + 1));
                            q.put(i,node);
                            nList.add(i);                    }
           });
            nList.forEach(list::remove);
            insert(list,q,2);
       }
       return q.get(1);
   }
}
