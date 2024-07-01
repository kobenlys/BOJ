import java.io.*;
import java.util.*;

public class Main {

    public static int makeClockNumber(int[] copy) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int tmp = 0;
            for (int j = i; j < 4 + i; j++) {
                tmp *= 10;
                tmp += copy[j % 4];

            }
            min = Math.min(min, tmp);
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st;

        int[] arr1 = new int[4];
        int min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            int tmp = 0;
            for (int j = i; j < 4 + i; j++) {
                tmp *= 10;
                tmp += arr1[j % 4];
            }
            min = Math.min(min, tmp);
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    for (int l = 1; l <= 9; l++) {
                        int[] copy = {i, j, k, l};
                        set.add(makeClockNumber(copy));
                    }
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (min <= list.get(i)) {
                if (min == list.get(i)) {
                    System.out.println(i + 1);
                } else {
                    System.out.println(i);
                }
                break;
            }
        }
    }
}