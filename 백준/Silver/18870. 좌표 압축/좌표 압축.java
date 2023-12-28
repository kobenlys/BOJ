import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        int[] copy = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            // 값 압축 후 원래 배열 순서대로 출력할때 필요하기때문에 복사배열 하나 만든다.
            copy[i] = arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 순서 매길때 필요하기때문에 정렬한다.
        Arrays.sort(copy);
        int cnt = 0;
        for (int i = 0; i < N; i++) {

            int k = copy[i];
            // hashMap 을 이용한 압축 알고리즘
            if (!map.containsKey(k)) {
                map.put(k, cnt++);
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr1[i])).append(" ");
        }
        System.out.println(sb);
    }
}