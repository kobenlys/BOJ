import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arr1 = new int[3];
        int[] arr2 = new int[2];

        for (int i = 0; i < 3; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < 2; i++) {
            arr2[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        System.out.println(arr1[0] + arr2[0] - 50);
        
    }
}