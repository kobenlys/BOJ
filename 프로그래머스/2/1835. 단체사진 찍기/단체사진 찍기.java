import java.util.*;

class Solution {
    
    public static List<String> list;
    public static boolean[] vi;
    public static String[] arr1 = {"A", "C", "F", "J", "M", "N", "R", "T"};
    
    public static void dfs(String perm){
        
        if(perm.length() == 8){
            list.add(perm);
            return;
        }
        
        for(int i = 0; i < 8; i++){
            if(!vi[i]){
                vi[i] = true;
                dfs(perm+arr1[i]);
                vi[i] = false;
            }
        }
    }
    
    public int solution(int n, String[] data) {
        int answer = 0;
        list = new ArrayList<>();
        vi = new boolean[8];
        dfs("");

        for(String perm : list){
            int successCnt = 0;
            for(int i = 0; i < data.length; i++){
                char targetA = data[i].charAt(0);
                char targetB = data[i].charAt(2);
                char compare = data[i].charAt(3);
                int number =  Character.getNumericValue(data[i].charAt(4));
                int indexA = perm.indexOf(targetA);
                int indexB = perm.indexOf(targetB);
                boolean isOK = false;
                
                if(compare == '>' && Math.abs(indexA - indexB) - 1 > number){
                    successCnt++;
                    isOK =true;
                }else if(compare == '<' && Math.abs(indexA - indexB) - 1 < number){
                    successCnt++;
                    isOK =true;
                }else if (compare == '=' && Math.abs(indexA - indexB) - 1 == number){
                    isOK =true;
                    successCnt++;
                }
                if(!isOK) break;
            }
            
            if(successCnt == data.length){
                answer++;
            }
        }
        
        return answer;
    }
}