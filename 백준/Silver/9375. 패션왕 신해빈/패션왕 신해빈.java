import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tcase = 0; tcase < T; tcase++) {
            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                st.nextToken();
                String key = st.nextToken();

                if (map.containsKey(key)) { // 옷 종류 중복제거 및 중복 수 입력
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }

            int result = 1;
            for (int val : map.values()) {
                // +1의 의미는 옷을 안입었을때도 고려해야함.
                // 두가지 옷 종류가 있다고 하면, 한가지 옷 종류를 입어도, 다른 종류 옷은 안입을 수 있기 때문에
                result *= val + 1;
            }
            // -1의 의미는 옷종류를 다 입지 않는 경우 -> 알몸인 경우를 빼준다
            System.out.println(--result);
            map.clear();
        }
    }
}