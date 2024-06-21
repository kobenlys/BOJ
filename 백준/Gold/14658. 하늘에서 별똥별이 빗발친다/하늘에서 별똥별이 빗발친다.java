import java.io.*;
import java.util.*;

public class Main {

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;
        ArrayList<node> arr1 = new ArrayList<>();
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr1.add(new node(x, y));
        }

        // 기본 아이디어
        // 두 노드가 L*L의 사각형에 모서리에 걸쳐 있는 경우 최적값이 될 수 있음.

        for (node y : arr1) { // 첫번째 노드
            for (node x : arr1) { // 두번째 노드
                int cnt = 0;
                for (node pos : arr1) { // 첫번째 노드 && 두번째노드 의 L 범위 내 노드 체크
                    // 첫번째 노드 X ~ X+L , 두번째 노드 Y ~ Y+L 범위 내 노드 체크 한다.
                    if (y.x <= pos.x && y.x + L >= pos.x && x.y <= pos.y && x.y + L >= pos.y) {
                        cnt++;
                    }
                }
                answer = Math.max(answer, cnt);
            }
        }
        // 출력
        System.out.println(K - answer);
    }
}