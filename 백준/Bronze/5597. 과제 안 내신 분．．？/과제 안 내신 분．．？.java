import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st;

        for (int i = 0; i < 28; i++) {
            int num = Integer.parseInt(br.readLine());
            set.add(num);
        }

        for (int i = 1; i <= 30; i++) {
            if (!set.contains(i)) {
                System.out.println(i);
            }
        }
    }
}
