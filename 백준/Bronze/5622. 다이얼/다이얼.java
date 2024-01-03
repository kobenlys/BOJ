import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int time = 0;

        for (int i = 0; i < str.length(); i++) {
            int num = str.charAt(i) - 'A' + 1;

            if (num > 18) {
                num--;
            }

            if (str.charAt(i) == 'S') {
                time += 8;
                continue;
            } else if (str.charAt(i) == 'Z') {
                time += 10;
                continue;
            }

            if (num % 3 == 0) {
                time += num / 3 + 2;
            } else {
                time += num / 3 + 3;
            }
        }
        System.out.print(time);
    }
}
