import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap 을 이용한 좌표압축
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        int[] copy;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        copy = Arrays.copyOf(arr1, N);
        Arrays.sort(copy); // copy배열을 정렬하여 최솟값부터 HashMap에 입력 및 순위 기록

        int cnt = 0;
        for (int e : copy) {
            // 중복된 값은 스킵한다
            if (!map.containsKey(e)) {
                map.put(e, cnt);
                cnt++;
            }
        }
        // 원래 배열을 통해 순서에 맞게 순위를 출력한다.
        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr1[i])).append(" ");
        }
        System.out.println(sb);
    }
}