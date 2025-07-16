import java.util.*;

class Solution {
    
    public static Map<String, List<Integer>> map = new HashMap<>();
    
    public static void dfs(int start, String command, String[] info){
        if(start == 4){
            if(!map.containsKey(command)){
                map.put(command, new ArrayList<>());   
            }
            map.get(command).add(Integer.parseInt(info[4]));
            return;
        }
        dfs(start+1, command + info[start], info);
        dfs(start+1, command + "-", info);
    }
    
    public static int binarySearch(String command, int target){
        
        List<Integer> list = map.get(command);
        if(list == null) return 0;
        
        int left = 0, right = list.size() - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            int num = list.get(mid);
            
            if(target <= num){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return list.size() - left;
    }
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < info.length; i++) {
            dfs(0, "", info[i].split(" "));
        }
        
        for(List<Integer> list : map.values()) {
            Collections.sort(list);
        }
        
        for(int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", " ");
            String[] arr1 = query[i].split(" ");
            sb.setLength(0);
            
            for(int j = 0; j < arr1.length - 1; j++) {
                sb.append(arr1[j]);
            }
            answer[i] = binarySearch(sb.toString(), Integer.parseInt(arr1[4]));
        }
        
        return answer;
    }
}