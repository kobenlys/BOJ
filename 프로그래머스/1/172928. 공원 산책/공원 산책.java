import java.util.*;

class Solution {
    
    public static class node{
        int x ,y;
        public node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[] park, String[] routes) {
        
        Queue<node> qu = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        StringTokenizer st;
        int[] answer = new int[2];
        
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        map.put("E",3);
        map.put("W",2);
        map.put("S",1);
        map.put("N",0);
        
        int idx = 0;
        int N = park.length;
        int M = park[0].length();
        
        char[][] arr1 = new char[N][M];
        
        for(int i=0; i< N; i++){
            String str = park[i];
            for(int j=0; j< M; j++){
                arr1[i][j] = str.charAt(j);
                if(arr1[i][j] == 'S'){
                    qu.offer(new node(j,i));
                }
            }
        }

        
        while(!qu.isEmpty()){
            
            node nd = qu.poll();
            
            if(idx >= routes.length){
                answer[0] = nd.y;
                answer[1] = nd.x;
                break;
            }
            
            st = new StringTokenizer(routes[idx++]);
            
            int dir = map.get(st.nextToken());
            int step = Integer.parseInt(st.nextToken());
            boolean isCheck = false;
            
            int flag = 1;
            int nx=0;
            int ny=0;
            
            for(int i=0; i<step; i++){
                nx = nd.x + flag*dx[dir];
                ny = nd.y + flag*dy[dir];
                flag++;
                if(nx < 0 || ny < 0 || nx >= M || ny >= N){
                    isCheck = true;
                    break;
                }
                
                if(arr1[ny][nx] == 'X'){
                    isCheck = true;
                    break;
                }
            }
            
            if(!isCheck){
                qu.offer(new node(nx,ny));
            }else{
                qu.offer(new node(nd.x,nd.y));
            }
        } 
        
        return answer;
    }
}