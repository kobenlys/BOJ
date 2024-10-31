import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int left = 0, right = 0;
        int maxLen = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        while (left < N && right < N) {
            map.put(arr1[right], right);

            if (map.lastKey() - map.firstKey() > 2) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == left) {
                        map.remove(entry.getKey());
                        break;
                    }
                }

                left++;
            } else {

                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }
        }

        System.out.println(maxLen);
    }
}