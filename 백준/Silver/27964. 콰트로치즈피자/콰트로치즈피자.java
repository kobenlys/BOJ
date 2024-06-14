import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String validator = "Cheese";

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String tmp = st.nextToken();

            if (tmp.length() <= 5) continue;
            int idx = 0;

            boolean isOk = true;
            // 문자열 뒤 Cheese 검증하기.
            for (int j = (tmp.length() - 1) - 5; j < tmp.length(); j++) {
                if (tmp.charAt(j) != validator.charAt(idx++)) {
                    isOk = false;
                    break;
                }
            }

            if (!isOk) continue;
            set.add(tmp); // 중복제거.
        }
        
        // set의 크기가 4 이상이라면 yummy 출력
        System.out.println(set.size() >= 4 ? "yummy" : "sad");
    }
}