import java.io.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int resLeft = 0; int resRight = 0;
        int left =0; int right =0; int sum = sequence[right];
        int ans = Integer.MAX_VALUE;

        while (true) {

            if (sum < k) {
                ++right;
                if(right == sequence.length) break;
                sum += sequence[right];
            } else if (sum > k) {
                sum -= sequence[left++];
            } else {
                
                int tmp = right - left;
                if (ans > tmp) {
                    resLeft = left;
                    resRight = right;
                    ans = tmp;
                }

                sum -= sequence[left++];
            }
        }

        int[] answer = {resLeft,resRight};
        return answer;
    }
}