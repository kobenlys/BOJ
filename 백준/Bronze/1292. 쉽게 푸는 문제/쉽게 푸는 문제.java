import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> arr1 = new ArrayList<>();

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int sum = 0;
        int i = 1;

        st:
        while (true) {
            for (int j = 0; j < i; j++) {
                arr1.add(i);
                if (arr1.size() == B) {
                    break st;
                }
            }
            i++;
        }


        for (int j = A - 1; j < B; j++) {
            sum += arr1.get(j);
        }

        System.out.println(sum);
    }
}