class Solution {
    public int repeatedNTimes(int[] nums) {
        boolean[] vi = new boolean[10001];
        for(int e : nums) {
            if(vi[e]){
                return e;
            }
            vi[e] = true;
        }
        return -1;
    }
}