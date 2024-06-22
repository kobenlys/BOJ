import java.io.*;
import java.util.*;

public class Main {
    
    // 금강석 위치 담는 클래스
    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answerX = 0;
        int answerY = 0;
        int cnt = 0;

        ArrayList<node> arr1 = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr1.add(new node(x, y));
        }
        
        // pos1, pos2를 정사각형 변에 걸치게 설정 후 범위 내 금강석 탐색
        for (node pos1 : arr1) {
            for (node pos2 : arr1) {
                int tmpCnt = 0;

                int tX = pos1.x;
                int tY = pos2.y;
                
                // 문제조건 : 정사각형의 범위가 지도 밖으로 나가는 것 불가.
                // 범위를 넘는다면 범위를 수정 해 준다.
                if (tX + K > N) tX = N - K;
                if (tY + K > M) tY = M - K;

                for (node tmp : arr1) {
                    // 정사각형 범위 내 금강석이 존재하는지 탐색.
                    if (tX <= tmp.x && tX + K >= tmp.x
                            && tY <= tmp.y && tY + K >= tmp.y) {
                        tmpCnt++;
                    }
                }
                // 최적값 발견시 업데이트
                if (cnt < tmpCnt) {
                    cnt = tmpCnt;
                    // 왼쪽 상단 꼭짓점.
                    answerX = tX;
                    answerY = tY + K;
                }
            }
        }
        
        // 출력
        sb.append(answerX).append(" ").append(answerY).append("\n");
        sb.append(cnt);
        System.out.print(sb);
    }
}