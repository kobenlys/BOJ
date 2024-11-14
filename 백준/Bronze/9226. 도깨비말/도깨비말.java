import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            String str = br.readLine();
            if(str.equals("#")) break;

            Set<Character> set = new HashSet<>();
            set.add('a');
            set.add('e');
            set.add('i');
            set.add('o');
            set.add('u');
            int idx = 0;
            for (int i = 0; i < str.length(); i++) {
                if (set.contains(str.charAt(i))) {
                    idx = i;
                    break;
                }
            }

            for (int i = idx; i < idx+str.length(); i++) {
                sb.append(str.charAt(i % str.length()));
            }
            sb.append("ay").append("\n");
        }
        System.out.println(sb);
    }
}