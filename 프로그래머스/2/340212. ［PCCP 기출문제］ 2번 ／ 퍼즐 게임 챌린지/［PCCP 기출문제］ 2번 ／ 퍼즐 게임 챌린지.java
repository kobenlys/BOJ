class Solution {
    
    public static boolean isPossibleLevel(int level, int[] diffs, int[] times, long limit){
        
        
        long nowTime = 0;
        
        for(int i = 0; i < diffs.length; i++){
            if(diffs[i] <= level){
                nowTime += times[i];
            }else{
                nowTime += (diffs[i] - level) * (times[i] + times[i-1]) + times[i];
            }
            if(nowTime > limit) return false;
        }
        
        return true;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int level = Integer.MAX_VALUE;
        int left = 1, right =  100_000;
        
        while(left <= right){
            
            int mid = (left + right) >> 1;
            
            if(isPossibleLevel(mid, diffs, times, limit)){
                level = Math.min(level, mid);
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        
        return level;
    }
}