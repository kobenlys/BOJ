class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int time = 0;
        int idx = 0;
        int cnt = 0;
        int answer = 0;
        int nowHp = health;
        int targetTime = attacks[attacks.length-1][0];
        
        while(true){
        
            
            if(time != attacks[idx][0]){
                
                cnt++;
                
                if(cnt == bandage[0]){
                    nowHp += bandage[1]+bandage[2];
                    cnt = 0;
                }else{
                    nowHp += bandage[1];
                }
                
                if(nowHp > health){
                    nowHp = health;
                }
                
                
            }else{
                // 같다면
                
                nowHp -= attacks[idx++][1];
                cnt = 0;
            }
            
            
            if(nowHp <= 0){
                answer = -1;
                break;
            }
            
            time ++;
            if(time == targetTime+1){
                answer = nowHp;
                break;
            }
        }
        
        return answer;
    }
}