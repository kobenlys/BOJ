import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> arr1 = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        int max = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
            max = Math.max(max, map.get(str));
        }

        for (String s : map.keySet()) {
            if (map.get(s) == max) {
                arr1.add(s);
            }
        }

        Collections.sort(arr1);
        System.out.print(arr1.get(0));
    }
}