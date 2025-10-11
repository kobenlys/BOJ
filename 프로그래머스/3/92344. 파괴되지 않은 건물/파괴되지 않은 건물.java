import java.util.*;

class Solution {
    
    public int countSurvivingBuildings(int[][] board, int[][] diffArr, int N, int M){
        int count = 0;
        int[][] prefixSum = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                
                int left = j != 0 ? prefixSum[i][j-1] : 0;
                int top = i != 0 ? prefixSum[i-1][j] : 0;
                int diagonal = (i != 0 && j != 0) ? prefixSum[i-1][j-1] : 0;
                prefixSum[i][j] = diffArr[i][j] + top + left - diagonal;
                
                if(prefixSum[i][j] + board[i][j] > 0){
                    count++;
                }
            }
        }
        
        
        return count;
    }
    
    public int solution(int[][] board, int[][] skill) {
        int answer  = 0;
        //System.out.println(i);
        
        int N = board.length;
        int M = board[0].length;
        int[][] diffArr = new int[N][M];
        
        for(int i = 0; i < skill.length; i++){
            int type = skill[i][0];
            int sY = skill[i][1];
            int sX = skill[i][2];
            int eY = skill[i][3];
            int eX = skill[i][4];
            int val = skill[i][5];
                
            if (type == 1){
                val = -val;   
            }
                
            diffArr[sY][sX] += val;
                
            if(eX+1 < M){
                diffArr[sY][eX+1] -= val;   
            }
            if(eY + 1 < N){
                diffArr[eY+1][sX] -= val;
            }
            if(eY + 1 < N && eX + 1 < M){
                diffArr[eY+1][eX+1] += val;
            } 
        }
        
        answer = countSurvivingBuildings(board, diffArr, N, M);
        return answer;
    }
}