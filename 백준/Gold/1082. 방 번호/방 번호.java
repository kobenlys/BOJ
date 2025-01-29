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

        if (N == 1) {
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

        while (true) {
            if (totalCost < tmpCost + list.get(0).cost) break;
            if (tmpCost == 0 && list.get(0).number == 0) {
                if (totalCost < tmpCost + list.get(1).cost) break;
                result.add(list.get(1).number);
                tmpCost += list.get(1).cost;
            } else {
                result.add(list.get(0).number);
                tmpCost += list.get(0).cost;
            }
        }

        Collections.sort(result, Comparator.reverseOrder());
        if (tmpCost < totalCost) {
            for (int i = 0; i < result.size(); i++) {

                for (int j = N - 1; j >= 0; j--) {
                    if (result.get(i) < j && totalCost >= tmpCost - arr1[result.get(i)] + arr1[j]) {
                        tmpCost = tmpCost - arr1[result.get(i)] + arr1[j];
                        result.set(i, j);
                    }
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
        }
        System.out.println(result.isEmpty() ? 0 : sb);
    }
}