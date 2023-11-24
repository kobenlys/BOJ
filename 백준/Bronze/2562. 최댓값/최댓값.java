import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int flag = 0;

        for (int i = 0; i < 9; i++) {
            int num = Integer.parseInt(br.readLine());
            // num이 max보다 크다면 max를 num으로 초기화 후 flag에 순서를 입력한다
            if (max < num) {
                max = num;
                flag = i + 1;
            }
        }
        System.out.println(max);
        System.out.println(flag);
    }
}
