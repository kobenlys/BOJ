import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static HashMap<String, Integer> map = new HashMap<>();
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static void bfs(String input) {
        Queue<String> qu = new LinkedList<>();
        map.put(input, 0);
        qu.offer(input);

        while (!qu.isEmpty()) {

            String now = qu.poll();
            int zeroIdx = now.indexOf("0");
            // 1차원 배열 인덱스를 
            // 3*3 2차원 배열 인덱스로 변환
            // ex. 1차원 배열 7번째 값은
            // 2차원 배열의 [2][1]자리에 존재한다.
            int nowX = zeroIdx % 3; 
            int nowY = zeroIdx / 3;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;
                // 2차원 배열의 인덱스를 -> 1차원 배열의 인덱스로 변환
                // 배열[2][1] 값은 -> 3*2+1 -> 배열[7]에 존재함
                int nextIdx = 3 * ny + nx;
                
                // StringBuilder의 메서드를 통해 0과 flag 자리 스왑
                StringBuilder sb = new StringBuilder(now);
                char flag = sb.charAt(nextIdx);
                sb.setCharAt(nextIdx, '0');
                sb.setCharAt(zeroIdx, flag);
                
                // 맵에 포함되지 않았다면 새로운 조합임 -> 방문체크 효과도있음(무한루프 방지)
                if (!map.containsKey(sb.toString())) {
                    qu.offer(sb.toString());
                    // 현재조합 과 (전번조합이 만들어 지기까지의 횟수 +1) 저장
                    map.put(sb.toString(), map.get(now) + 1);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = "";

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                str += st.nextToken();
            }
        }
        // 너비우선탐색으로 만들 수 있는 모든 경우의 조합을 만들고 맵에 저장한다.
        bfs(str);
        
        // 올바른 조합 "123456780"이 맵에 없다면, 만들 수 없는 경우임.
        if (map.containsKey("123456780")) {
            System.out.print(map.get("123456780"));
        } else {
            System.out.print(-1);
        }
    }
}