import java.util.*;

class Solution {
    public static int answer = Integer.MAX_VALUE, limit;
    public static String[] minerals;
    public static int[] picks;
    
    public static void dfs(int start, int idx, int score){
        
        if(idx >= minerals.length){
            answer = Math.min(answer, score);
            return;
        }
        
        if(start == limit){
            answer = Math.min(answer, score);
            return;
        }
        
        for(int i=0; i<3; i++){
            if(picks[i] > 0){
                picks[i]--;
                int tmpScore = score;
                score += cntScore(idx, i);
                dfs(start+1, idx+5, score);
                score = tmpScore;
                picks[i]++;
            }
        }
    }
    
    public static int cntScore(int start, int idx){
        
        int cnt = 0;
        int res = 0;
        
        for(int i = start; i<minerals.length; i++){
            if(cnt == 5) break;
            
            if(idx == 0){
                res++;
            }else if(idx == 1){
                if(minerals[i].equals("diamond")) res +=5;
                if(!minerals[i].equals("diamond")) res +=1;
            }else{
                if(minerals[i].equals("diamond")) res +=25;
                if(minerals[i].equals("iron")) res +=5;
                if(minerals[i].equals("stone")) res +=1;
            }
            cnt++;
        }
        
        return res;
    }
    
    public int solution(int[] pick, String[] mineral) {
        picks = pick;
        minerals = mineral;
        
        limit += picks[0];
        limit += picks[1];
        limit += picks[2];
        
        dfs(0,0,0);
        return answer;
    }
}