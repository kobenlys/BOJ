class Solution {
    public int solution(String name) {
        int answer = Integer.MAX_VALUE;
        int startIndex = 0;
        
        while(startIndex < name.length()){
            int res = 0;
            int move1 = 0;
            int move2 = 0;
            
            for(int i = 0; i <= startIndex; i++){
                char tmp = name.charAt(i);
                if(tmp != 'A'){
                    move1 = i;
                }

                int diff = Math.abs(tmp - 'A');
                res += Math.min(diff, Math.abs(26 - diff));
            }
            
            res += move1;
            for(int i = name.length()-1; i > startIndex; i--){

                char tmp = name.charAt(i);

                if(tmp != 'A'){
                    move2 = name.length() - i;   
                }

                int diff = Math.abs(tmp - 'A');
                res += Math.min(diff, Math.abs(26 - diff));
            }
            res += move2;
            
            if(startIndex  > 0 && startIndex < name.length() -1){
                res += Math.min(move1, move2);   
            }
            
            answer = Math.min(answer, res);
            startIndex++;
        }
        
        
        return answer;
    }
}