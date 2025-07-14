import java.util.*;

class Solution {
    
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][][] vi;
    public static List<Integer> list = new ArrayList<>();
    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {-1,0,1,0};
    
    
    public static class Node{
        int x, y, dir;
        
        public Node(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        
        public void updateY(){
            if(this.y < 0 || this.y >= N) {
                this.y = this.y == N ? 0 : N - 1;      
            }
        }
        
        public void updateX(){
            if(this.x < 0 || this.x >= M) {
                this.x = this.x == M ? 0 : M - 1;   
            }
        }
        
        public boolean isRange(){
            return this.x >= 0 && this.y >= 0 && this.x < M && this.y < N;
        }
        
        public boolean isEqual(Node nd){
            return this.x == nd.x && this.y == nd.y && this.dir == nd.dir;
        }
        
        public void changeDirection(char target){
            
            if(target == 'R'){
                this.dir = (this.dir + 3) % 4;
            }else if(target == 'L'){
                this.dir = (this.dir + 1) % 4;
            }
            
            this.x += dx[dir];
            this.y += dy[dir];
            updateX();
            updateY();
        }
    }
    
    public static void findCircle(Node pos) {
        
        boolean isRevoke = false;
        List<Node> trace = new ArrayList<>();
        int step = 0;
        Node startPos = new Node(pos.x, pos.y, pos.dir);
        
        while (true) {
            trace.add(new Node(pos.x, pos.y, -1));
            if(vi[pos.y][pos.x][pos.dir]) {
                if(pos.isEqual(startPos)) {
                    list.add(step);
                    break;
                }
                isRevoke = true;
                break;
            }
            
            vi[pos.y][pos.x][pos.dir] = true;
            char point = arr1[pos.y][pos.x];
            step++;
            pos.changeDirection(point);
        }
        
        for(Node nd : trace){
            vi[pos.y][pos.x][pos.dir] = false;
        }
    }
    
    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        
        arr1 = new char[N][M];
        vi = new boolean[N][M][4];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                arr1[i][j] = grid[i].charAt(j);
            }
        }
        
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < 4; k++){
                    if(!vi[i][j][k]){
                        findCircle(new Node(j, i, k));
                    }
                }
            }
        }
        
        int[] answer = new int[list.size()];
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}