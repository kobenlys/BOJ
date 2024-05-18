import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] caffeArr;
    public static ArrayList<ArrayList<Integer>> arr1;

    public static void setNum() {
        Deque<Integer> dq = new ArrayDeque<>();
        int idx = 0;
        int cntCaffe = 1;
        int tmpY = 0;

        while (idx <= 100000) {

            if (arr1.get(idx).isEmpty()) {
                idx++;
                continue;
            }

            for (int i = 0; i < arr1.get(idx).size(); i++) {
                dq.offer(arr1.get(idx).get(i));
            }

            if (dq.peekFirst() == tmpY) {
                while (!dq.isEmpty()) {
                    caffeArr[cntCaffe][0] = idx;
                    caffeArr[cntCaffe++][1] = dq.pollFirst();
                    tmpY = caffeArr[cntCaffe-1][1];
                }
            } else {
                while (!dq.isEmpty()) {
                    caffeArr[cntCaffe][0] = idx;
                    caffeArr[cntCaffe++][1] = dq.pollLast();
                    tmpY = caffeArr[cntCaffe-1][1];
                }
            }
            idx++;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            N = Integer.parseInt(br.readLine());
            arr1 = new ArrayList<>();
            caffeArr = new int[N+1][2];

            for (int i = 0; i < 100001; i++) {
                arr1.add(new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr1.get(x).add(y);
            }

            for (int i = 0; i < 100001; i++) {
                if (!arr1.get(i).isEmpty()) {
                    Collections.sort(arr1.get(i));
                }
            }

            setNum();

            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            
            for (int i = 0; i < M; i++) {
                int idx = Integer.parseInt(st.nextToken());
                sb.append(caffeArr[idx][0]).append(" ")
                        .append(caffeArr[idx][1]).append("\n");
            }
        }
        System.out.print(sb);
    }
}