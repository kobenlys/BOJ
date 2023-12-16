import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException { // 값 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N % 4 == 0) {
            if (N % 100 != 0) {
                System.out.println(1);
            }else if (N % 400 == 0) {
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        } else {
            System.out.println(0);
        }
    }
}