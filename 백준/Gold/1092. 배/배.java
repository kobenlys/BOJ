import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> box = new ArrayList<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Integer[] crane = new Integer[N];

        // 크레인 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }

        // 박스 입력
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        // 둘 다 내림차순으로 정렬
        Arrays.sort(crane, Comparator.reverseOrder());
        Collections.sort(box, Comparator.reverseOrder());
        int cnt = 0;

        // 크레인의 최대무게 보다 박스의 최대무게가 크가면 -> -1출력
        if (crane[0] < box.get(0)) {
            System.out.println(-1);
            System.exit(0);
        }

        st:
        while (true) {
            int idx = 0;
            cnt++;
            for (int i = 0; i < N; i++) {
                if (idx == box.size()) break;
                // 크레인이 박스를 옮길 수 있다면
                if (crane[i] >= box.get(idx)) {
                    box.remove(idx); // 박스 제거
                    if (box.isEmpty()) {
                        break st;
                    }
                } else {
                    // 크레인이 박스를 옮길 수 없다면
                    // 해당 크래인 사용해야하니, i올라가는거 막기 + 뒤 박스 탐색
                    i--;
                    idx++;
                }
            }
        }
        System.out.print(cnt);
    }
}