import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static boolean[] vi;
    public static HashMap<Integer, List<Integer>> map = new HashMap<>();

    // 현재 호선, 환승횟수 담는 객체
    public static class node {
        int key, transfer;

        public node(int key, int transfer) {
            this.key = key;
            this.transfer = transfer;
        }
    }

    public static int bfs(int start) {
        Queue<node> qu = new LinkedList<>();
        qu.offer(new node(start, 0));
        vi[start] = true;
        int answer = Integer.MAX_VALUE;

        while (!qu.isEmpty()) {
            node nowKey = qu.poll();
            List<Integer> tmp = map.get(nowKey.key);

            // 현재 호선에 목표인 역이 있다면.
            if (tmp.contains(M)) {
                answer = Math.min(answer, nowKey.transfer);
                continue;
            }

            for (int i = 0; i < tmp.size(); i++) {
                int flag = tmp.get(i);
                // 방문하지 않은 호선이라면
                for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
                    if (!vi[e.getKey()]) {
                        List<Integer> nextTmp = map.get(e.getKey());
                        if (nextTmp.contains(flag)) {
                            // 아직 방문하지 않은 호선에, 현재 역이 속해있다면 큐에 넣기
                            // 환승임
                            vi[e.getKey()] = true;
                            qu.offer(new node(e.getKey(), nowKey.transfer + 1));
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int answer = Integer.MAX_VALUE;
        List<Integer> start = new ArrayList<>();

        // 호선에 해당하는 역들 HashMap에 저장.
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            // 역은 리스트로 관리한다.
            List<Integer> list = new ArrayList<>();

            int t = Integer.parseInt(st.nextToken());
            for (int j = 0; j < t; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (list.contains(n)) break;
                list.add(n);
            }
            // 서울역이 포함된 호선 찾기
            if (list.contains(0)) {
                start.add(i);
            }
            map.put(i, list);
        }

        M = Integer.parseInt(br.readLine());
        // 서울역이 여러곳에 있을 수 있음 ㅋㅋ
        for (int i = 0; i < start.size(); i++) {
            vi = new boolean[N + 1];
            answer = Math.min(answer, bfs(start.get(i)));
        }

        System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}