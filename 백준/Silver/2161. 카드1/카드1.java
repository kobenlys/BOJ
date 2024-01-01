import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arr1 = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr1.add(i);
        }

        while (!arr1.isEmpty()) {

            sb.append(arr1.get(0)).append(" ");
            arr1.remove(0);
            if (!arr1.isEmpty()) {
                int temp = arr1.get(0);
                arr1.add(temp);
                arr1.remove(0);
            }
        }
        System.out.print(sb);
    }
}