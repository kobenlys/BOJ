import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        Deque<Character> dq = new ArrayDeque<>();

        dq.offerFirst(str.charAt(0));

        for (int i = 1; i < str.length(); i++) {
            if (dq.peekLast() < str.charAt(i)) {
                dq.offerFirst(str.charAt(i));
            }else{
                dq.offerLast(str.charAt(i));
            }
        }

        while (!dq.isEmpty()) {
            sb.append(dq.pollLast());
        }

        System.out.println(sb);
    }
}