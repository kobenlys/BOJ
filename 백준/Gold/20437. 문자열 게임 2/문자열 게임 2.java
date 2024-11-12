import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());
            int[] arr1 = new int[26];

            List<List<Integer>> list = new ArrayList<>();
            int minAns = Integer.MAX_VALUE;
            int maxAns = Integer.MIN_VALUE;

            for (int i = 0; i < 26; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < str.length(); i++) {
                list.get(str.charAt(i) - 'a').add(i);
            }

            for (int i = 0; i < 26; i++) {
                if (list.get(i).size() >= K) {

                    for (int j = K - 1; j < list.get(i).size(); j++) {
                        int s = list.get(i).get(j - (K - 1));
                        int e = list.get(i).get(j);
                        minAns = Math.min(minAns, Math.abs(s - e));
                        maxAns = Math.max(maxAns, Math.abs(s - e));
                    }
                }
            }
            if (minAns == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(minAns + 1).append(" ").append(maxAns + 1).append("\n");
            }
        }
        System.out.println(sb);
    }
}