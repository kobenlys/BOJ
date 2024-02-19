import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // ()((())))(())

        String str = br.readLine();

        boolean isCheck = false;
        int cnt =0, ans = 0;
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '(') {
                cnt++;
                isCheck = false;
            } else if (str.charAt(i) == ')') {

                if (!isCheck) {
                    cnt--;
                    ans += cnt;
                    isCheck = true;
                } else {
                    cnt--;
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}