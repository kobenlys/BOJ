import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int start, target, weight;

        public node(int start, int target, int weight) {
            this.start = start;
            this.target = target;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            if (target == o.target) {
                return start - o.start;
            }
            return target - o.target;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> boxList = new PriorityQueue<>();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        int answer = 0;

        // 마을마다 적재 가능한 최대 무게 담는 배열
        int[] arr1 = new int[N + 1];
        Arrays.fill(arr1, C);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            boxList.add(new node(s, e, w));
        }
        // 가장 빨리 도착가능한 마을을 기준으로 최적해를 구한다.
        // 도착 마을 내림차순으로 정렬.
        while (!boxList.isEmpty()) {

            node nowBox = boxList.poll();
            int limitW = Integer.MAX_VALUE;

            // 최대 가능한 무개 구하기
            for (int i = nowBox.start; i < nowBox.target; i++) {
                limitW = Math.min(limitW, arr1[i]);
            }


            if (nowBox.weight > limitW) {
                // 현재 박스를 전체 다 못가져 가는 경우
                for (int i = nowBox.start; i < nowBox.target; i++) {
                    // start ~ target-1 마을의 가능한 무게 줄이기
                    arr1[i] -= limitW;
                }
                answer += limitW;
            } else {
                // 현재 박스를 다 가져갈 수 있을때.
                for (int i = nowBox.start; i < nowBox.target; i++) {
                    // start ~ target-1 마을의 가능한 무게 줄이기
                    arr1[i] -= nowBox.weight;
                }
                answer += nowBox.weight;
            }
        }

        System.out.println(answer);
    }
}