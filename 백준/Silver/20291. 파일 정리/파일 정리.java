import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), ".");
            st.nextToken();
            String key = st.nextToken();
            set.add(key);
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }

        ArrayList<String> arr1 = new ArrayList<>(set);
        Collections.sort(arr1);

        for (int i = 0; i < arr1.size(); i++) {
            sb.append(arr1.get(i)).append(" ").append(map.get(arr1.get(i))).append("\n");
        }
        System.out.print(sb);
    }
}