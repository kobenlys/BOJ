import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int flag = 10;
        int num = 1;

        for (int i = 1; i <= N; i++) {
            if (i % flag == 0) {
                num++;
                flag *= 10;
            }
            cnt += num;
        }

        System.out.print(cnt);
    }
}
