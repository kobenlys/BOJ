import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int value, idx;

        public Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        int N = br.readLine().length();
        int M = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        String cordinate = "";
        Node[] arr1 = new Node[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            arr1[i] = new Node(Integer.parseInt(st.nextToken()), i);
        }

        Arrays.sort(arr1, Comparator.comparingInt(e -> e.value));
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (!map.containsKey(arr1[i].value)) {
                map.put(arr1[i].value, cnt++);
            }
        }

        for (int i = 0; i < map.size(); i++) {
            cordinate += "0";
        }
        set.add(cordinate);

        int[] visit = new int[map.size()];

        Arrays.sort(arr1, Comparator.comparingInt(e -> e.idx));
        String dir = br.readLine();

        for (int i = 0; i < M; i++) {
            int index = map.get(arr1[i].value);
            char control = dir.charAt(i);
            int numericValue = visit[index];

            if (control == '+') {
                numericValue += 1;
            } else {
                numericValue -= 1;
            }

            visit[index] = numericValue;
            sb.setLength(0);

            for (int j = 0; j < visit.length; j++) {
                sb.append(visit[j]);
            }

            if (set.contains(sb.toString())) {
                System.out.println(0);
                return;
            }
            set.add(sb.toString());
        }

        System.out.println(1);
    }
}