import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str1 = br.readLine();
        String str2 = br.readLine();

        Queue<Integer> qu = new ArrayDeque<>();
        int[] arr1 = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

        for (int i = 0; i < str1.length(); i++) {
            qu.offer(arr1[str1.charAt(i) - 'A']);
            qu.offer(arr1[str2.charAt(i) - 'A']);
        }

        while (qu.size() > 2) {

            int size = qu.size();

            for (int i = 1; i < size; i++) {
                int num = qu.poll();
                qu.offer((num + qu.peek()) % 10);
            }
            qu.poll();
        }

        System.out.println(String.valueOf(qu.poll()) + String.valueOf(qu.poll()));

    }
}