import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static ArrayList<Integer> arr1 = new ArrayList<>();

    public static void dfs(int num) {

        if (N < num) {
            return;
        }

        if (num > 0) {
            arr1.add(num);
        }

        dfs(num * 10 + 7);
        dfs(num * 10 + 4);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dfs(0);
        Collections.sort(arr1);
        System.out.println(arr1.get(arr1.size() - 1));
    }
}