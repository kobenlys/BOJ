import java.util.*;


class Solution {
    
    public String[] solution(String[] players, String[] callings) {
        
        
        HashMap<String,Integer> map = new HashMap<>();
        
        for(int i=0; i<players.length; i++){
            map.put(players[i], i);
        }
        
        for(int i=0; i<callings.length; i++){
           
            int idx = map.get(callings[i]);
            int newIdx = idx-1;
            
            String tmp = players[newIdx];
            players[newIdx] = players[idx];
            players[idx] = tmp;
            
            map.put(players[newIdx], newIdx);
            map.put(players[idx], idx);
            
        }
        
        return players;
    }
}