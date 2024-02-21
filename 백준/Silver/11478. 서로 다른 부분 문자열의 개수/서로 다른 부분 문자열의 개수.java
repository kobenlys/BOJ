import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static String S;
    public static HashSet<String> set = new HashSet<>();


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();

        for (int i = 0; i < S.length(); i++) {
            String tmp;
            for (int j = i+1; j <= S.length(); j++) {
                tmp = S.substring(i, j);
                set.add(tmp);
            }
        }

        System.out.println(set.size());
    }
}