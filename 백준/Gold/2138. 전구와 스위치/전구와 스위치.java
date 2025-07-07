import java.io.*;
import java.util.*;

public class Main {

    public static int matchArray(boolean[] arr1, boolean[] expected) {
        int count = 0;
        int size = arr1.length;

        for (int i = 0; i < size - 1; i++) {

            if (arr1[i] != expected[i]) {
                count++;
                arr1[i] = !arr1[i];
                arr1[i + 1] = !arr1[i + 1];
                if (i != size - 2) {
                    arr1[i + 2] = !arr1[i + 2];
                }
            }
        }

        return arr1[size - 1] == expected[size - 1] ? count : Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        boolean[] arr1 = new boolean[N];
        boolean[] arr2 = new boolean[N];
        boolean[] expected = new boolean[N];

        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            arr1[i] = arr2[i] = Character.getNumericValue(str.charAt(i)) == 1;

        }

        str = br.readLine();
        for (int i = 0; i < N; i++) {
            expected[i] = Character.getNumericValue(str.charAt(i)) == 1;
        }

        int res1 = matchArray(arr1, expected);
        arr2[0] = !arr2[0];
        arr2[1] = !arr2[1];
        int res2 = matchArray(arr2, expected);
        if (res2 != Integer.MAX_VALUE) {
            res2++;
        }
        int answer = Math.min(res1, res2);

        //System.out.println(res1 +" " + res2);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

        // 110
        //
    }
}