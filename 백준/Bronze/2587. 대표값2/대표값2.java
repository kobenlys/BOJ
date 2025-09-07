import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum / list.size());

        System.out.println(list.get(list.size() / 2));


    }
}