import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> res = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr1 = new int[N + 1];
        int flag = 1;
        int cnt = 1;

        for (int i = 1; i <= N; i++) {
            arr1[i] = i;
        }

        while (res.size() != N) {
            if (flag > N) {
                flag = 1;
            }
            // 요소가 아직있는 노드를 지나 친다면 cnt++
            // 요소가 없는 노드를 지나 친다면 카운팅 x
            if (cnt == K) {
                if (arr1[flag] != 0) {
                    res.add(arr1[flag]);
                    arr1[flag] = 0;
                    cnt = 1;
                }
            } else {
                if (arr1[flag] != 0) {
                    cnt++;
                }
            }
            flag++;
        }

        sb.append("<");
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i));
            if (i != res.size() - 1) {
                sb.append(", ");
            } else {
                sb.append(">");
            }
        }

        System.out.println(sb);
    }
}