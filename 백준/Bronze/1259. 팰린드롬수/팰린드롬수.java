import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();

            if(Integer.parseInt(str) == 0){ // 조건 1. "0" 입력시 종료
                System.out.println(sb);
                break;
            }

            // left, right 자리 지정.
            int left=0;
            int right = str.length()-1;
            boolean isPalindrome = true; // 팰린드롬 체크

            // 투 포인터 알고리즘
            while (left <= right) {
                if (str.charAt(left) != str.charAt(right)) {
                    isPalindrome = false;
                    break;
                }
                left++;
                right--;
            }

            if (isPalindrome) {
                sb.append("yes").append("\n");
            }else{
                sb.append("no").append("\n");
            }
        }
    }
}
