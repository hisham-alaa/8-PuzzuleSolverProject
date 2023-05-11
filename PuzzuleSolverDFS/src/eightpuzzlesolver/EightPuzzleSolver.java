package eightpuzzlesolver;

import java.util.Scanner;

public class EightPuzzleSolver {

    public static int goal[]=new int [9];
    
    public static int numOfInversion(int[]state) {
        int res=0;
        
        for (int i=0;i<8;i++)
            for (int j=i+1;j<9;j++) 
                if(state[i]>state[j]&&state[j]!=0)
                    res++;
        
        return res;
    }
    
    public static boolean isSolvable( int[] initial , int[] goal ) {
        return (numOfInversion(initial)%2==numOfInversion(goal)%2);
    }
    
    public static void main(String[] args) {
        
        Scanner input=new Scanner(System.in);
        int initBlank=0,initial[]=new int[9];
        
        
        System.out.println("ENTER INITIAL STATE from 0 to 8:");               
        for (int i=0;i<9;i++){
            initial[i]=input.nextInt();
            if(initial[i]==0)initBlank=i;
        }
        
        System.out.println("ENTER GOAL STATE :");
        for (int i=0;i<9;i++)
            goal[i]=input.nextInt();
        
        if (isSolvable(initial ,goal))
        {
            System.out.println("SEARCHING FOR THE SOLUTION....");        
            Node root=new Node(initial,initBlank);
            Search search=new Search();
            search.BFS(root);
        }
        
        else System.out.println("THIS STATE IS UNSOLVABLE\nTRY AGAIN !!! ");        
    }
    
}