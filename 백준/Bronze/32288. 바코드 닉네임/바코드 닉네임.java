import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'I') {
                sb.append("i");
            } else {
                sb.append("L");
            }
        }

        System.out.println(sb);
    }
}