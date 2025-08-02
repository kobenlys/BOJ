import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int answer = 300;

        for (int i = 0; i < 4; i++) {
            answer += Integer.parseInt(br.readLine());
        }

        System.out.println(answer <= 1800 ? "Yes" : "No");

    }
}
