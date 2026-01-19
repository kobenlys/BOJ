class Solution {

    public int maxProfit(int[] prices) {
        int answer = 0;

        int left = 0;
        int right = 0;

        while(right != prices.length) {

            if(left == right) {
                right++;
                continue;
            }

            if(prices[left] < prices[right]){
                answer = Math.max(answer, prices[right] - prices[left]);
                right++;
            } else {
                left = right;
            }
        }
        

        return answer;
    }
}