import java.util.*;

class Solution {
    public int solution(String[][] board, int h, int w) {
        
        int answer = 0;
        
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        
        int size = board.length;
        
        
        for(int i=0; i<4; i++){
            int nx = w + dx[i];
            int ny = h + dy[i];
            if(nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
            if(board[h][w].equals(board[ny][nx])) answer++;
        }
        
        return answer;
    }
}