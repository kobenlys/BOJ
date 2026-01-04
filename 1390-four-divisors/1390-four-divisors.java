class Solution {
    public int sumFourDivisors(int[] nums) {
        
        int[] cache = new int[1_00_001];
        int answer = 0;

        for(int e : nums) {
            int cnt = 0;
            int tempSum = 0;

            if(cache[e] != 0) {
                answer += cache[e];
                continue;
            }

            for(int i = 1; i <= Math.sqrt(e); i++) {
                
                int t = e / i;

                if(e % i == 0) {
                    tempSum += i + t;
                    cnt += 2;
                    if(i == t) {
                        cnt--;
                        tempSum -= i;
                    }
                }

                if(cnt > 4) {
                    break;
                }
            }

            if(cnt == 4){
                cache[e] = tempSum;
                answer += tempSum;
            }
            
        }
        return answer;
    }
}