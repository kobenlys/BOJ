class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int answer[] = new int[2];

        for(int i = 0; i < nums.length; i++) {
            int e = nums[i];
            int diff = target - e;

            if(!map.containsKey(e)) {
                map.put(diff, i);
            } else {
                answer[0] = map.get(e);
                answer[1] = i;
                break;
            }
        }

        return answer;
    }
}