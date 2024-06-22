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

        for (node pos1 : arr1) {
            for (node pos2 : arr1) {
                int tmpCnt = 0;

                int tX = pos1.x;
                int tY = pos2.y;

                if (tX + K > N) tX = N - K;
                if (tY + K > M) tY = M - K;

                for (node tmp : arr1) {
                    if (tX <= tmp.x && tX + K >= tmp.x
                            && tY <= tmp.y && tY + K >= tmp.y) {
                        tmpCnt++;
                    }
                }
                
                if (cnt <= tmpCnt) {
                    cnt = tmpCnt;
                    answerX = tX;
                    answerY = tY + K;
                    if (answerY > M) answerY = M;
                }
            }
        }

        sb.append(answerX).append(" ").append(answerY).append("\n");
        sb.append(cnt);
        System.out.print(sb);
    }
}