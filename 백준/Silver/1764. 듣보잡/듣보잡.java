import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<String, Boolean> map = new HashMap<>();
        ArrayList<String> res = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        //HashMap에 저장한다.
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            map.put(input, true);
        }
        //HashMap에 검색한다.
        for (int i = 0; i < M; i++) {
            String Key = br.readLine();
            // HashMap 사용하여 이미 입력된 이름이 있는지 Key를 사용해 검색
            if (map.containsKey(Key)) {
                if (map.get(Key)) {
                    res.add(Key);
                }
            }
        }
        // 문자열 사전 순 정렬
        Collections.sort(res);

        for (String e : res) {
            sb.append(e).append("\n");
        }

        System.out.println(res.size() + "\n" + sb);
    }
}
