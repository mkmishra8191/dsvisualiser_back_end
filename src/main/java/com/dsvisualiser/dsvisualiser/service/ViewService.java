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

    void rightViewUtil(Node node,int level,List<Integer> list) {

        // Base Case
        if (node == null)
            return;

        // If this is the last Node of its level
        if (this.max_level < level) {
            list.add(node.getData());
            this.max_level = level;
        }

        // Recur for right subtree first, then left subtree
        rightViewUtil(node.getRight(), level + 1, list);
        rightViewUtil(node.getLeft(), level + 1, list);
    }



    // A wrapper over rightViewUtil()
    public List<Integer> rightView(Node node, int level,List<Integer> list) {
        this.max_level=0;
        rightViewUtil(node,level,list);
        return list;

    }

    public List<Integer> bottomView(Node  root, List<Integer> list)
    {
        if (root == null)
            return list;

        // Initialize a variable 'hd' with 0 for the root element.
        int hd = 0;

        // TreeMap which stores key value pair sorted on key value
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue to store tree nodes in level order traversal
        Queue<Node> queue = new LinkedList<Node>();

        // Assign initialized horizontal distance value to root
        // node and add it to the queue.
        root.setHd(hd);
        queue.add(root);

        // Loop until the queue is empty (standard level order loop)
        while (!queue.isEmpty())
        {
            Node temp = queue.remove();

            // Extract the horizontal distance value from the
            // dequeued tree node.
            hd = temp.getHd();

            // Put the dequeued tree node to TreeMap having key
            // as horizontal distance. Every time we find a node
            // having same horizontal distance we need to replace
            // the data in the map.
            map.put(hd, temp.getData());

            // If the dequeued node has a left child add it to the
            // queue with a horizontal distance hd-1.
            if (temp.getLeft() != null)
            {
                temp.getLeft().setHd(hd-1);
                queue.add(temp.getLeft());
            }
            // If the dequeued node has a right child add it to the
            // queue with a horizontal distance hd+1.
            if (temp.getRight() != null)
            {
                temp.getRight().setHd(hd+1);
                queue.add(temp.getRight());
            }
        }

        // Extract the entries of map into a set to traverse
        // an iterator over that.
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();

        // Make an iterator
        Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();

        // Traverse the map elements using the iterator.
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> me = iterator.next();
            list.add(me.getValue());
        }
        return  list;
    }
    public List<Integer> topView(Node root,List<Integer> list)
    {
        class QueueObj {
            Node node;
            int hd;

            QueueObj(Node node, int hd)
            {
                this.node = node;
                this.hd = hd;
            }
        }
        Queue<QueueObj> q = new LinkedList<QueueObj>();
        Map<Integer, Node> topViewMap
                = new TreeMap<Integer, Node>();

        if (root == null) {
            return list;
        }
        else {
            q.add(new QueueObj(root, 0));
        }



        // count function returns 1 if the container
        // contains an element whose key is equivalent
        // to hd, or returns zero otherwise.
        while (!q.isEmpty()) {
            QueueObj tmpNode = q.poll();
            if (!topViewMap.containsKey(tmpNode.hd)) {
                topViewMap.put(tmpNode.hd, tmpNode.node);
            }

            if (tmpNode.node.getLeft() != null) {
                q.add(new QueueObj(tmpNode.node.getLeft(),
                        tmpNode.hd - 1));
            }
            if (tmpNode.node.getRight() != null) {
                q.add(new QueueObj(tmpNode.node.getRight(),
                        tmpNode.hd + 1));
            }
        }
        for (Map.Entry<Integer, Node> entry :
                topViewMap.entrySet()) {
            list.add(entry.getValue().getData());
        }

        return list;
    }
    public void diagonalPrintUtil(Node root,int d,
                                  TreeMap<Integer,Vector<Integer>> diagonalPrint)
    {

        // Base case
        if (root == null)
            return;

        // get the list at the particular d value
        Vector<Integer> k = diagonalPrint.get(d);

        // k is null then create a
        // vector and store the data
        if (k == null)
        {
            k = new Vector<>();
            k.add(root.getData());
        }

        // k is not null then update the list
        else
        {
            k.add(root.getData());
        }

        // Store all nodes of same line
        // together as a vector
        diagonalPrint.put(d,k);

        // Increase the vertical distance
        // if left child
        diagonalPrintUtil(root.getLeft(),
                d + 1, diagonalPrint);

        // Vertical distance remains
        // same for right child
        diagonalPrintUtil(root.getRight(),
                d, diagonalPrint);
    }

    // Print diagonal traversal
    // of given binary tree
   public   List<Integer> rightDiagonalView(Node root,List<List<Integer>> list)
    {

        // create a map of vectors
        // to store Diagonal elements
        TreeMap<Integer,Vector<Integer>> diagonalPrint = new TreeMap<>();
        diagonalPrintUtil(root, 0, diagonalPrint);


        for (Map.Entry<Integer, Vector<Integer>> entry :
                diagonalPrint.entrySet())
        {
            list.add(entry.getValue());
        }
        return list.get(0);
    }
    public   List<Integer> leftDiagonalView(Node root,List<List<Integer>> list)
    {

        // create a map of vectors
        // to store Diagonal elements
        TreeMap<Integer,Vector<Integer>> diagonalPrint = new TreeMap<>();
        diagonalPrintUtil(root, 0, diagonalPrint);


        for (Map.Entry<Integer, Vector<Integer>> entry :
                diagonalPrint.entrySet())
        {
            list.add(entry.getValue());
        }
        return list.get(list.size()-1);
    }

}



