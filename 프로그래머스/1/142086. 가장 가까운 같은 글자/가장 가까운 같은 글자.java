class Solution {
    public int[] solution(String s) {
       
        String str = s;
        int[] answer = new int[str.length()];

        for (int i = 0; i < str.length()-1; i++) {


            if (answer[i] == 0) {
                answer[i] = -1;
                int left = i, right = i+1;

                while (str.length()  != right) {

                    if (str.charAt(left) == str.charAt(right)) {
                        answer[right] = right - left;
                        left = right;
                        right++;
                    } else {
                        right++;
                    }
                }
            }
        }

        if (answer[str.length() - 1] == 0) {
            answer[str.length() - 1] = -1;
        }

        
        return answer;
    }
}