import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(br.readLine());

        if (money * 2 > M + N) {
            System.out.println(N + M);
        }else{
            System.out.println(M + N - (money * 2));
        }

    }
}