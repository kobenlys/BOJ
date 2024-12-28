import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static List<Node> list = new ArrayList<>();

    public static class Node implements Comparable<Node> {
        int number, team;

        public Node(int number, int team) {
            this.number = number;
            this.team = team;
        }

        @Override
        public int compareTo(Node o) {
            return number - o.number;
        }
    }

    public static boolean isPossible(int size) {

        Set<Integer> set = new HashSet<>();

        int left = 0;
        int right = 0;
        int[] teamCnt = new int[N + 1];

        while (right < list.size()) {
            
            if (set.size() < N && list.get(right).number - list.get(left).number <= size) {
                teamCnt[list.get(right).team]++;
                set.add(list.get(right).team);
                if (set.size() == N && list.get(right).number - list.get(left).number <= size) {
                    return true;
                }
                right++;
                continue;
            }

            if (list.get(right).number - list.get(left).number > size) {
                if (teamCnt[list.get(left).team] == 1) {
                    set.remove(list.get(left).team);
                }
                teamCnt[list.get(left++).team]--;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                list.add(new Node(Integer.parseInt(st.nextToken()), i));
            }
        }
        Collections.sort(list);

        int left = 0, right = 1_000_000_000;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}