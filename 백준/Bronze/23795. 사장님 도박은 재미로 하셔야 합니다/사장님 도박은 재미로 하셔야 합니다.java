import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int answer = 0;

        while (true) {
            int num = Integer.parseInt(br.readLine());
            if(num == -1) break;
            answer += num;
        }

        System.out.println(answer);
    }
}