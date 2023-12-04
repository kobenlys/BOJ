import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr1 = new int[4];

        for (int i = 0; i < 4; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        int subject5 = Integer.parseInt(br.readLine());
        int subject6 = Integer.parseInt(br.readLine());

        subject6 = Math.max(subject5, subject6);

        Arrays.sort(arr1);

        System.out.println(arr1[3]+arr1[2]+arr1[1]+subject6);

    }
}