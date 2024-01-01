import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arr1 = new ArrayList<>();

        while (true) {
            int num = Integer.parseInt(br.readLine());
            if (num < 0) {
                System.out.println(sb);
                break;
            }

            for (int i = 1; i < num; i++) {
                if (num % i == 0) {
                    arr1.add(i);
                }
            }
            int sum = 0;
            for (int e : arr1) {
                sum += e;
            }

            if (sum == num) {
                sb.append(num).append(" = ");
                for (int i = 0; i < arr1.size(); i++) {
                    sb.append(arr1.get(i));
                    if (i != arr1.size() - 1) {
                        sb.append(" + ");
                    }
                }
                sb.append("\n");
            } else {
                sb.append(num).append(" is NOT perfect.").append("\n");
            }
            arr1.clear();
        }

    }
}