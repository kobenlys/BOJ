import java.util.*;

class Solution {
    public static List<List<Integer>> list = new ArrayList<>();
    public static Map<Integer, Integer> map = new HashMap <>();
    public static boolean[] banned;
    public static boolean[] vi;
    
    public boolean isEvenOddTree(int start, int flag){
        int nowIndex = map.get(start);
        boolean isOk = true;
        
        if(start % 2 == 0 && (list.get(nowIndex).size()-flag) % 2 != 0){
            return false;
        }
        
        if(start % 2 != 0 && (list.get(nowIndex).size()-flag) % 2 == 0){
            //System.out.println("find!");
            return false;
        }
        
        for(int end : list.get(nowIndex)){
            int nextIndex = map.get(end);
            
            if(!vi[nextIndex]){
                vi[nextIndex] = true;
                isOk = isEvenOddTree(end, 1);
                vi[nextIndex] = false;
                if(!isOk) break;
            }
        }
        return isOk;
    }
    
    
    public boolean isRevEvenOddTree(int start, int flag){
        int nowIndex = map.get(start);
        boolean isOk = true;
        
        if(start % 2 == 0 && (list.get(nowIndex).size()-flag) % 2 == 0){
            return false;
        }
        
        if(start % 2 != 0 && (list.get(nowIndex).size()-flag) % 2 != 0){
            return false;
        }
        
        for(int end : list.get(nowIndex)){
            int nextIndex = map.get(end);
            
            if(!vi[nextIndex]){
                vi[nextIndex] = true;
                isOk = isRevEvenOddTree(end, 1);
                vi[nextIndex] = false;
                if(!isOk) break;
            }
        }
        return isOk;
    }
    
    public void dfs(int startNode){
        int index = map.get(startNode);
        
        for(int e : list.get(index)){
            int nextIndex = map.get(e);
            if(!banned[nextIndex]){
                banned[nextIndex] = true;
                dfs(e);
            }
        }
    }
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {0,0};
        // 1. 그래프 생성
        // 2. 짝홀, 역짝홀 탐색
        // 3. 해당 포래스트 방문 처리 하기.
        vi = new boolean[nodes.length];
        banned = new boolean[nodes.length];
        
        for(int i=0; i< nodes.length; i++){
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i< nodes.length; i++){
            map.put(nodes[i], i);
        }
        
        for(int i=0; i< edges.length; i++){
            int s = edges[i][0];
            int e = edges[i][1];
            int startIndex = 0;
            list.get(map.get(s)).add(e);
            list.get(map.get(e)).add(s);
        }
        
        for(int i=0; i< nodes.length; i++){
            int startNode = nodes[i];
            int nowIndex = map.get(startNode);
            
            if(!banned[i]){
                vi[nowIndex] = true;
                boolean isEvenOddTree = isEvenOddTree(startNode,0);
                boolean isRevEvenOddTee = isRevEvenOddTree(startNode,0);
                // System.out.println(isEvenOddTree + " "+ startNode);
                // System.out.println(isRevEvenOddTee + " "+ startNode);
                // System.out.println();
                vi[nowIndex] = false;
            
                if(isEvenOddTree){
                    //banned[nowIndex] = true;
                    //dfs(startNode);
                    answer[0]++;   
                }
                
                if(isRevEvenOddTee){
                    //banned[nowIndex] = true;
                    //dfs(startNode);
                    answer[1]++;   
                }   
            }
        }
        
        
        return answer;
    }
}