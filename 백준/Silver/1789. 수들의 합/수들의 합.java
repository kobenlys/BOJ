import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long S = Long.parseLong(br.readLine());
        int answer = 0;

        while (S > 0) {
            answer++;
            S -= answer;
        }
        if(S < 0) answer--;
        System.out.println(answer);
    }
}