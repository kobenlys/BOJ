import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "No";
        
        int flag = 0;
        int idx1 = 0;
        int idx2 = 0;
        
        while(true){
            
            if(idx1 < cards1.length && cards1[idx1].equals(goal[flag])){
                
                idx1++;
                flag++;
                
            }else if(idx2 < cards2.length && cards2[idx2].equals(goal[flag])){
                idx2++;
                flag++;
                
            }else{
                break;
            }
            
            if((idx1 == cards1.length && idx2 == cards2.length) || flag == goal.length){
                answer =  "Yes";
                break;
            } 
        }
        
        
        
        
        return answer;
    }
}