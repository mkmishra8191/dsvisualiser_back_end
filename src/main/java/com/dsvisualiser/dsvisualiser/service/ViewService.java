package com.dsvisualiser.dsvisualiser.service;

import com.dsvisualiser.dsvisualiser.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ViewService {
   static int max_level;

    @Autowired
    public ViewService() {
    }
    public List<Integer> leftView(Node node,int level) {
        this.max_level=0;
        List<Integer> llist= new ArrayList<>();
        leftViewUtil(node,level,llist);


        return llist;

    }



   public void leftViewUtil(Node node,int level,List<Integer> llist)
    {

        // Base Case
        if (node == null)
            return;

        // If this is the first node of its level
        if (this.max_level < level) {
            llist.add(node.getKey());
            this.max_level = level;
        }

        // Recur for left and right subtrees
        leftViewUtil(node.getLeft(),level + 1,llist);
        leftViewUtil(node.getRight(),level + 1,llist);

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

    public List<List> getFullTree(Node root){
        List<Integer> llist=new ArrayList<>();
        List<List> aList= new ArrayList<>();

        printLevelOrder(root,aList,llist);


        return aList;

    }

    public void printLevelOrder(Node root,List<List> aList,List<Integer> llist)
    {


        int h = height(root);
        int i;
        for (i=0; i<h; i++)
        {
            List<Integer> aaList= new ArrayList<>();


            for (int j=0;j<(int) Math.pow(2, i); j++){
               aaList.add(null);
           }
           printGivenLevel(root, i,llist);
            int finalI = (int) Math.pow(2, i);
            llist.forEach(e->{

              aaList.set(e- finalI,e);

           });
             llist.clear();
             aList.add(aaList);

        }

    }
    /* Print nodes at a given level */
    public void printGivenLevel(Node root, int level,List<Integer> llist) {
        List<Integer> list= new ArrayList<>();
        if (root == null)

            return ;



        if (level == 0)
            llist.add(root.getKey());
        else if (level > 0 ) {
            printGivenLevel(root.getLeft(), level - 1,llist);
            printGivenLevel(root.getRight(), level - 1,llist);
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
