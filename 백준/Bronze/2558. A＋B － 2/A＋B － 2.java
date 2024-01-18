import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());

        System.out.println(T+B);


    }
}
