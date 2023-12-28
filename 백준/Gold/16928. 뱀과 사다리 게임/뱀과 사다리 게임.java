import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static HashMap<Integer, Integer> map1 = new HashMap<>();
    public static HashMap<Integer, Integer> map2 = new HashMap<>();
    public static Queue<node> qu = new LinkedList<>();
    public static boolean[] vi;

    public static class node { // 현재 위치, 주사위 사용횟수
        int num, roll;

        public node(int num, int roll) {
            this.num = num;
            this.roll = roll;
        }
    }

    public static void bfs() { // BFS 알고리즘 구현
        qu.offer(new node(1, 0));

        while (!qu.isEmpty()) {
            node nd = qu.poll();

            if (nd.num == 100) { // 먼저 도착한 노드의 주사위 사용횟수 출력
                System.out.println(nd.roll);
                break;
            }
            // 주사위에서 나올 수 있는 모든 경우의 숫자 만큼 이동.
            for (int i = 1; i <= 6 ; i++) {
                int nextNum = nd.num + i;
                int nextRoll = nd.roll + 1; // 주사위 사용했으니 +1

                if (nextNum > 0 && nextNum <= 100 && !vi[nextNum]) { // 범위 체크
                    vi[nextNum] = true;
                    if (map1.containsKey(nextNum)) {
                        // 사다리 사용 경우
                        int newNum = map1.get(nextNum);
                        qu.offer(new node(newNum, nextRoll));

                    } else if (map2.containsKey(nextNum)) {
                        // 뱀 사용 경우
                        int newNum = map2.get(nextNum);
                        qu.offer(new node(newNum, nextRoll));
                    } else {
                        // 사다리, 뱀 사용하지 않는 경우
                        qu.offer(new node(nextNum, nextRoll));
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        vi = new boolean[101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map1.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map2.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bfs();
    }
}