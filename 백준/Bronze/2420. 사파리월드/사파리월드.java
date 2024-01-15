import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] num = br.readLine().split(" ");
        long ans = Long.parseLong(num[0]) - Long.parseLong(num[1]);
        System.out.println(Math.abs(ans));
    }
}
