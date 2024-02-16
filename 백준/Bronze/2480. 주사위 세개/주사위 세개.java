import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        HashSet<Integer> set = new HashSet<>();

        int flag = 0;
        int max = 0;
        for (int i = 0; i < 3; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (set.contains(num)) {
                flag = num;
            }
            max = Math.max(max, num);
            set.add(num);
        }

        if (set.size() == 1) {
            System.out.println(10000+max*1000);
        } else if (set.size() == 2) {
            System.out.println(1000 + flag * 100);
        } else {
            System.out.println(max*100);
        }
    }
}