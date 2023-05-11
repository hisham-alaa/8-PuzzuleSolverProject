package eightpuzzlesolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Search {
    
    Queue<Node> fringeQ=new LinkedList<Node>();
    Stack <Node> fringeS=new Stack<Node>();
    
    ArrayList<Node> closed=new ArrayList<Node>();
    ArrayList<Node> path=new ArrayList<Node>();
    
    Search(){
        
        
    }
    
    public void BFS(Node root) {
        fringeQ.add(root);
        boolean goalFound=false;
        
        while (!goalFound)
        {
            Node curNode=fringeQ.remove();
            closed.add(curNode);
            
            curNode.expandToChildrens();
            for (Node child:curNode.children)
            {
                if(child.isGoal())
                {
                    System.out.println("Goal is Found !");
                    goalFound=true;
                    pathTrace(child);
                    printPath();
                }
                if(!existed(child,1)) fringeQ.add(child);
            }
        }
        closed.clear();
        fringeQ.clear();
    }
    
//    public void DFS(Node root) {
//        
//        fringeS.push(root);
//        boolean goalFound=false;
//        
//        while (!goalFound)
//        {
//            Node curNode=fringeS.pop();
//            closed.add(curNode);
//            curNode.expandToChildrens();
//            for (Node child:curNode.children)
//            {
//                if(child.isGoal())
//                {
//                    System.out.println("Goal is Found !");
//                    goalFound=true;
//                    pathTrace(child);
//                    printPath();
//                }
//                if(!existed(child,2)) fringeS.push(child);
//            }
//        }
//        closed.clear();
//        fringeS.clear();
//    }
    
//    public void ASTAR(Node root) {
//        
//        boolean goalFound=false;
//        Node curNode=root;
//        while (!goalFound)
//        {
//            closed.add(curNode);
//            curNode.expandToChildrens();
//            curNode.printPuzzle();
//            Node nextNode=new Node(new int[]{0,8,7,6,4,5,3,2,1},0);
//            for (Node child:curNode.children)
//            {
//                if(child.isGoal())
//                {
//                    System.out.println("Goal is Found !");
//                    goalFound=true;
//                    pathTrace(child);
//                    printPath();
//                }
//                else if(!existed(child,3))
//                {
//                    if(curNode.heuristicMisplaced()>child.heuristicMisplaced())
//                        nextNode=child;
//                }    
//            }
//            curNode=nextNode;
//        }
//        closed.clear();
//    }
    
    public void pathTrace(Node goal) {
        Node node=goal;
        while (node!=null)
        {
            path.add(node);
            node=node.parent;
        }
    }
    
    public void printPath(){
        for(int i=path.size()-1;i>=0; i--){
            path.get(i).printPuzzle();
            if (!(i==0))
            {
                System.out.println(" ||");
                System.out.println(  " \\/");
            }
        }
        System.out.println("----------------------\n!!! Done !!!");
    }
    
    public boolean existed(Node node,int which) {
        for (Node n:closed)
            if(n.isSamePuzzle(node.puzzle))
                return true;
        if (which==1)
        {
            for (Node n:fringeQ)
                if(n.isSamePuzzle(node.puzzle))
                    return true;
        }
        else if (which==2)
        {
            for(Node n:fringeS)
                if(n.isSamePuzzle(node.puzzle))
                    return true;
        }
        
        return false;
    }
    
}
