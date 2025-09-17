import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();

        Set<Character> set = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }

        String tmp = "MOBIS";

        for (int i = 0; i < tmp.length(); i++) {
            if (set.contains(tmp.charAt(i))) {
                set.remove(tmp.charAt(i));
            }else{
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}