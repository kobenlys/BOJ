import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) - 1;

        int J = Integer.parseInt(br.readLine());
        int answer = 0;
        int nowPos = 1;

        for (int i = 0; i < J; i++) {
            int number = Integer.parseInt(br.readLine());

            if (nowPos <= number && number <= nowPos + M) {
                continue;
            }

            if (nowPos < number) {

                answer += number - (nowPos + M);
                nowPos += number - (nowPos + M);

            } else if (number < nowPos) {
                answer += nowPos - number;
                nowPos = number;
            }
        }

        System.out.println(answer);

    }
}