import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int answer = 0;

        while (st.hasMoreTokens()) {
            answer += Integer.parseInt(st.nextToken());
        }

        System.out.println(answer);

    }
}