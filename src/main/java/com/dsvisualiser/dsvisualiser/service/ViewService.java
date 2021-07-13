package com.dsvisualiser.dsvisualiser.service;

import com.dsvisualiser.dsvisualiser.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ViewService {
    public int max_level;

    public List<Integer> getLlist() {
        return llist;
    }

    public void setLlist(List<Integer> llist) {
        this.llist = llist;
    }

    public List<Integer> llist;
    @Autowired
    public ViewService() {
        this.llist = new ArrayList<>();
        this.max_level=0;
    }
   public void leftViewUtil(Node node, int level)
    {
        // Base Case
        if (node == null)
            return;

        // If this is the first node of its level
        if (this.max_level < level) {
            this.llist.add(node.getKey());
            this.max_level = level;
        }

        // Recur for left and right subtrees
        leftViewUtil(node.getLeft(), level + 1);
        leftViewUtil(node.getRight(), level + 1);

    }
    /* Inorder traversal of a binary tree*/
    public void inorder (Node temp)
    {
        if (temp == null)
            return;

        inorder(temp.getLeft());
        System.out.print(temp.getKey() + " ");
        inorder(temp.getRight());


    }

  public int height(Node root)
    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.getLeft());
            int rheight = height(root.getRight());

            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }
    public List<List> printLevelOrder(Node root)
    {
        List<List> aList= new ArrayList<>();

        int h = height(root);
        int i;
        for (i=0; i<h; i++)
        {
            List<Integer> aaList= new ArrayList<>();


            for (int j=0;j<(int) Math.pow(2, i); j++){
               aaList.add(null);
           }
           printGivenLevel(root, i);
            int finalI = (int) Math.pow(2, i);
            llist.forEach(e->{

              aaList.set(e- finalI,e);

           });
             llist.clear();
             aList.add(aaList);

        }
        return aList;
    }
    /* Print nodes at a given level */
    public void printGivenLevel(Node root, int level) {
        List<Integer> list= new ArrayList<>();
        if (root == null)

            return ;



        if (level == 0)
            llist.add(root.getKey());
        else if (level > 0 ) {
            printGivenLevel(root.getLeft(), level - 1);
            printGivenLevel(root.getRight(), level - 1);
        }
        return;
    }



    /*function to insert element in binary tree */
   public HashMap<Integer,Node>  insert(List<Integer> list, HashMap<Integer, Node> q, int level) {
        int  maxlevel=level;
       List<Integer> nList= new ArrayList<>();



       while (!list.isEmpty()){

            list.forEach(i-> {
                if (maxlevel== 1){
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
            nList.forEach(i->{
                list.remove(i);
            });

            insert(list,q,2);


       }
       return q;

   }


}
