import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        if (N >= 20 && N <= 23) {
            answer += 24 - N + Integer.parseInt(br.readLine());
        } else {
            answer += Integer.parseInt(br.readLine()) - N;
        }
        System.out.println(answer);
    }
}