import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        boolean isSameStr = true;
        
        // 회문이 아닌 문자열이 있는 경우
        // 1. 애초부터 회문이 아닌경우 = 문자열길이 출력
        // 2. 전체가 회문인 경우 = 문자열길이 - 1 출력 -> 회문불가능
        // 3. 모든 문자열이 같을 경우 = 회문임 -> -1 출력
        
        // 회문이 아닌 문자열이 속한순간 전체가 회문이 아니게되는 성질 이용함.

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                System.out.println(str.length());
                return;
            }
            if (str.charAt(i) != str.charAt(i + 1)) {
                isSameStr = false;
            }
        }

        if (isSameStr) {
            System.out.println(-1);
        } else {
            System.out.println(str.length() - 1);
        }
    }
}