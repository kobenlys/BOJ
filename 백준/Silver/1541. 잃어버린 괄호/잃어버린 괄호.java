import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        // 가장 큰수를 만들고 빼줘야 한다 즉
        // "+" 연산을 다 해준후 - 연산을 해주면 된다, 앞에서 부터
        int sum = Integer.MAX_VALUE;

        while (st.hasMoreTokens()) {
            int temp = 0;

            StringTokenizer newSt = new StringTokenizer(st.nextToken(),"+");
            while (newSt.hasMoreTokens()) {
                temp += Integer.parseInt(newSt.nextToken());
            }

            if (sum == Integer.MAX_VALUE) {
                sum = temp;
            }else{
                sum -= temp;
            }
        }

        System.out.println(sum);
    }
}