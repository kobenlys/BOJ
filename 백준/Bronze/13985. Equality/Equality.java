import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String[] arr1 = br.readLine().split(" ");
        System.out.println(
            Integer.parseInt(arr1[0]) + Integer.parseInt(arr1[2]) == Integer.parseInt(arr1[4])
                ? "YES" : "NO"
        );
    }
}