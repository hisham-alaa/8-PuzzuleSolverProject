package eightpuzzlesolver;

import java.util.*;
import static eightpuzzlesolver.EightPuzzleSolver.goal;

public class Node {
    public Node parent=null;
    public ArrayList<Node> children=new ArrayList<Node>(4);
    public int [] puzzle=new int[9];
    public int blank=0;
    
    
    Node(int[] puzzle, int blank){
        this.setPuzzle(puzzle);
        this.blank=blank;
    }
    
    public void setPuzzle(int[] p) {
        this.puzzle=p.clone();
    }
    
    public void expandToChildrens() {
        this.moveUp();
        this.moveDown();
        this.moveLeft();
        this.moveRight();
    }

    public void moveLeft() {
        if(blank%3!=0)
        {
            int[]left=puzzle.clone();
            left[blank]=left[blank-1];
            left[blank-1]=0;
            Node childLeft=new Node(left,blank-1);
            childLeft.parent=this;
            this.children.add(childLeft);
        }
    }

    public void moveRight() {
        if(blank%3!=2)
        {
            int[]right=puzzle.clone();
            right[blank]=right[blank+1];
            right[blank+1]=0;
            Node childLeft=new Node(right,blank+1);
            childLeft.parent=this;
            this.children.add(childLeft);
        }
    }
    
    public void moveUp() {
        if(blank>2)
        {
            int[]up=puzzle.clone();
            up[blank]=up[blank-3];
            up[blank-3]=0;
            Node childLeft=new Node(up,blank-3);
            childLeft.parent=this;
            this.children.add(childLeft);
        }
    }

    public void moveDown() {
        if(blank<6)
        {
            int[]down=puzzle.clone();
            down[blank]=down[blank+3];
            down[blank+3]=0;
            Node childDown=new Node(down,blank+3);
            childDown.parent=this;
            this.children.add(childDown);
        }
    }

    public void printPuzzle(){
        for (int i=0;i<9;i++)
        {
            if(puzzle[i]==0)System.out.print("# ");
            else System.out.print(puzzle[i]+" ");
            if(i%3==2)System.out.println();
        }
//        System.out.println("------------"+heuristicMisplaced());
    }
    
    public boolean isGoal() {
        return Arrays.equals(puzzle, goal);
    }
    
    public boolean isSamePuzzle(int[] arr){
        return Arrays.equals(puzzle, arr);
    }
    
    public int heuristicMisplaced(){
        int res=0;
        for (int i=0;i<9;i++)
            if(puzzle[i]!=goal[i]&&puzzle[i]!=0)
                res++;
        
        return res;
    }
    
    public int heuristicManhattan(){
        int res=0,goalIndex=0;
        for (int i=0;i<9;i++)
        {
            if(puzzle[i]!=goal[i]&&puzzle[i]!=0)
            {
                for(int j=0;j<9;j++)
                    if(goal[j]==puzzle[i])
                        goalIndex=j;
                
                int pi=i/3, pj=i%3;
                int gi=goalIndex/3,gj=goalIndex%3;
                res+=(Math.abs(pi-gi)+Math.abs(pj-gj));
            }
        }
        return res;
    }
 
}