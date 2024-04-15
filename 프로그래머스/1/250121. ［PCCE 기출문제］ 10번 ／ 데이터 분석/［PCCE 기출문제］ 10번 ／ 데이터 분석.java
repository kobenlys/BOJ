import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        ArrayList<int []> answer = new ArrayList<>();
        int idx = 0;
        int dir = 0;
        
    
        
        if(sort_by.equals("code")){
            Arrays.sort(data, Comparator.comparingInt((int[] o) -> o[0]));
        }else if(sort_by.equals("date")){
            Arrays.sort(data, Comparator.comparingInt((int[] o) -> o[1]));
        }else if(sort_by.equals("maximum")){
            Arrays.sort(data, Comparator.comparingInt((int[] o) -> o[2]));
        }else{
            Arrays.sort(data,Comparator.comparingInt((int[] o) -> o[3]));
        }
        
        
        if(ext.equals("code")){
            dir = 0;
        }else if(ext.equals("date")){
            dir = 1;
        }else if(ext.equals("maximum")){
            dir = 2;
        }else{
            dir = 3;
        }
        
        for(int i=0; i<data.length; i++){
            if(val_ext > data[i][dir]){
                answer.add(data[i]);
            }
        }
        
        return answer.toArray(new int[0][]);
    }
}