import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int cost, number;

        public Node(int cost, int number) {
            this.cost = cost;
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            if (cost == o.cost) {
                return o.number - number;
            }
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        List<Node> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        if (N == 1) { // N == 1이면 무조건 0이 답임.
            System.out.println(0);
            return;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            list.add(new Node(arr1[i], i));
        }

        int totalCost = Integer.parseInt(br.readLine());
        int tmpCost = 0;
        Collections.sort(list);

        while (true) { // 가장 가격이 낮은 숫자로 자릿수 늘리기.
            if (totalCost < tmpCost + list.get(0).cost) break;
            if (tmpCost == 0 && list.get(0).number == 0) { // 0은 앞에 올 수 없음
                if (totalCost < tmpCost + list.get(1).cost) break;
                result.add(list.get(1).number);
                tmpCost += list.get(1).cost;
            } else {
                result.add(list.get(0).number);
                tmpCost += list.get(0).cost;
            }
        }

        Collections.sort(result, Comparator.reverseOrder()); // 정렬
        if (tmpCost < totalCost) { // 아직 사용 할 수 있는 비용이 더 남았다면
            for (int i = 0; i < result.size(); i++) { // 가장 앞 자리부터

                for (int j = N - 1; j >= 0; j--) { // 큰 숫자가 들어갈 수 있는지 가격비교 진행
                    if (result.get(i) < j && totalCost >= tmpCost - arr1[result.get(i)] + arr1[j]) {
                        tmpCost = tmpCost - arr1[result.get(i)] + arr1[j]; // 가격 업데이트
                        result.set(i, j); // 숫자 업데이트
                    }
                }
            }
        }
        // 한번에 비용, 숫자를 처리할 필요가 없이 단계별로 진행
        // 1. 자릿수 늘리기
        // 2. 남은 비용으로 가장 앞자리 숫자 높일 수 있다면 높여보기
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
        }
        System.out.println(result.isEmpty() ? 0 : sb);
    }
}