import java.io.*;
import java.util.*;

class Solution {   
    public static int donut, stick, eight, firstNode;
    public static ArrayList<ArrayList<Integer>> arr1;
    public static ArrayList<ArrayList<Integer>> arr2;
    public static ArrayList<Integer> Donut = new ArrayList<>();
    public static ArrayList<Integer> Stick = new ArrayList<>();
    public static ArrayList<Integer> Eight = new ArrayList<>();
    public static boolean[] vi;

    public static void dfs(int start) {

        for (int i = 0; i < arr1.get(start).size(); i++) {
            int nd = arr1.get(start).get(i);
            if (!vi[nd]) {
                vi[nd] = true;
                dfs(nd);
            }
        }
    }
    
    public static void dfs2(int start) {

        for (int i = 0; i < arr2.get(start).size(); i++) {
            int nd = arr2.get(start).get(i);
            if (!vi[nd]) {
                vi[nd] = true;
                dfs2(nd);
            }
        }
    }
    
    public int[] solution(int[][] edges) {
        
        int max = 0;

        int N = edges.length;
        arr1 = new ArrayList<>();
        arr2 = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            arr1.add(new ArrayList<>());
            arr2.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int s = edges[i][0] - 1;
            int e = edges[i][1] - 1;
            max = Math.max(max, Math.max(s, e));
            arr1.get(s).add(e);
            arr2.get(e).add(s);
        }



        for (int i = 0; i <= max; i++) {

            if (arr1.get(i).size() >= 2 && arr2.get(i).isEmpty()) {
                firstNode = i + 1;
                continue;
            }

            if (arr1.get(i).size() == 2 && arr2.get(i).size() >= 2) {
                Eight.add(i);
                continue;
            }

            if (arr1.get(i).isEmpty() && !arr2.get(i).isEmpty()) {
                Stick.add(i);
                continue;
            }


            if (!arr1.get(i).isEmpty() && !arr2.get(i).isEmpty()) {
                Donut.add(i);
            }

        }

        vi = new boolean[max+1];
        
        vi = new boolean[max + 1];
        for (int i = 0; i < Eight.size(); i++) {
            if (!vi[Eight.get(i)]) {
                eight++;
                dfs(Eight.get(i));
                vi[Eight.get(i)] = true;
            }
        }


        for (int i = 0; i < Stick.size(); i++) {
            if (!vi[Stick.get(i)]) {
                stick++;
                dfs2(Stick.get(i));
                vi[Stick.get(i)] = true;
            }
        }

        for (int i = 0; i < Donut.size(); i++) {
            if (!vi[Donut.get(i)]) {
                donut++;
                dfs(Donut.get(i));
                vi[Donut.get(i)] = true;
            }
        }

        
        int[] answer = {firstNode, donut, stick, eight};
        return answer;
    }
}