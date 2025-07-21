import java.util.*;

class Solution {
    
    public static int N, M, answer = Integer.MAX_VALUE;
    public static boolean[][][] vi;
    
    public static void dfs(int start, int sizeA, int sizeB, int[][] info){
        
        if(start == info.length){
            answer = Math.min(answer, sizeA);
            return;
        }
        
        if(vi[start][sizeA][sizeB]){
            return;
        }
        
        vi[start][sizeA][sizeB] = true;
        
        if(sizeA + info[start][0] < N){
            dfs(start+1, sizeA + info[start][0], sizeB, info);
        }
        
        if(sizeB + info[start][1] < M){
            dfs(start+1, sizeA, sizeB + info[start][1], info);
        }
    }
    
    public int solution(int[][] info, int n, int m) {
        N = n;
        M = m;
        vi = new boolean[info.length][200][200];
        dfs(0, 0, 0, info);
        
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}