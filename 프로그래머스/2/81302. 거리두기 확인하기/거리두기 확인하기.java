import java.util.*;

class Solution {
    
    public  class Node{
        int x, y, chance, sX, sY;
        
        public Node(int x, int y, int chance, int sX, int sY){
            this.x = x;
            this.y = y;
            this.chance = chance;
            this.sX = sX;
            this.sY = sY;
        }
    }
    
    public  boolean isAllowed(char[][] arr1, Queue<Node> qu){
        
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        
        while(!qu.isEmpty()){
            Node nd = qu.poll();
            
            for(int i = 0; i < 4; i ++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                            
                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5){
                    continue;
                }
                if(arr1[ny][nx] == 'O' || arr1[ny][nx] == 'P'){
                    if(arr1[ny][nx] == 'P' && (nx != nd.sX || ny != nd.sY)){
                        return false;
                    }   
                
                    if(nd.chance + 1 < 2){
                        qu.offer(new Node(nx, ny, nd.chance +1, nd.sX, nd.sY));
                    }        
                }
            }
        }
        
        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i = 0; i < 5; i ++){
            Queue<Node> qu = new ArrayDeque<>();
            char[][] arr1 = new char[5][5];
            
            
            for(int ii=0; ii<5; ii++){
                for(int iii = 0; iii < 5; iii++){
                    arr1[ii][iii] = places[i][ii].charAt(iii);
                    
                    if(arr1[ii][iii] == 'P') { 
                       qu.offer(new Node(iii, ii, 0, iii, ii));
                    }
                }
            }
            if(isAllowed(arr1, qu)){
                answer[i] = 1;
            }
        }
        return answer;
    }
}