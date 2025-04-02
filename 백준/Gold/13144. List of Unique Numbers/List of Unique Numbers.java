import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long answer = 1;
        int[] arr1 = new int[N];

        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;


        while (right < N) {

            if (left == right) {
                set.add(arr1[right++]);
                continue;
            }

            if (set.contains(arr1[right])) {

                long size = set.size();

                while (set.contains(arr1[right])) {
                    set.remove(arr1[left++]);
                }
            }

            set.add(arr1[right++]);
            answer += right - left;
        }

        System.out.println(answer);
    }
}