import java.util.*;

class Solution {
    
    public static class node implements Comparable<node>{
        int x, y;
        public node(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(node o1){
            return x - o1.x;
        }
    }
    
    public int solution(int[][] targets) {
        int answer = 0;
        
        PriorityQueue<node> pq = new PriorityQueue<>();
        
        for(int i=0; i<targets.length; i++){
            int x = targets[i][0];
            int y = targets[i][1];
            pq.offer(new node(x,y));
        }
        
        while(!pq.isEmpty()){
            
            node now = pq.poll();
            int nowY = now.y;
            answer++;
            
            while(!pq.isEmpty() && nowY > pq.peek().x){
                nowY = Math.min(nowY, pq.poll().y);
            }
            
        }
        
        return answer;
    }
}





