import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int S = Integer.parseInt(br.readLine());
        int cnt = 0;
        int ans = 0;
        char[] arr1 = br.readLine().toCharArray();

        for (int i = 1; i < S-1; i++) {
            if (arr1[i - 1] == 'I' && arr1[i] == 'O' && arr1[i + 1] == 'I') {
                cnt++;
                if (cnt == N) {
                    cnt--;
                    ans++;
                }
                i++;
            }else{
                cnt = 0;
            }
        }
        System.out.println(ans);
    }
}