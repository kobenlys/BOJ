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
        
        // 투포인터로 최대 - 최소가 현재 size에 부합하면서 set.size가 N 인지 판별
        while (right < list.size()) {

            int team = list.get(right).team;
            int rightNum = list.get(right).number;
            int leftNum = list.get(left).number;
            
            // set에 팀 기록하기
            if (set.size() < N && rightNum - leftNum <= size) {
                teamCnt[team]++;
                set.add(team);
                if (set.size() == N) { // 가능한경우
                    return true;
                }
                right++;
                continue;
            }

            team = list.get(left++).team; // left 팀
            if (teamCnt[team] == 1) {
                set.remove(team);
            }
            teamCnt[team]--;
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
        int max = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                max = Math.max(max, num);
                list.add(new Node(num, i));
            }
        }
        Collections.sort(list);

        int left = 0, right = max;
        int answer = 0;
        // 이분탐색으로 가능한 결과값 찾기
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