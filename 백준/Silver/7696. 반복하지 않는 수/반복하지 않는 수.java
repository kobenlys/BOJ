import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int number = 0;
        List<Integer> list = new ArrayList<>();
        boolean[] isDuplicate = new boolean[10];

        while (list.size() <= 1_000_000) {
            number++;

            int tmp = number;
            boolean isOk = true;
            Arrays.fill(isDuplicate, false);

            while (tmp > 0) {
                if (!isDuplicate[tmp % 10]) {
                    isDuplicate[tmp % 10] = true;
                }else{
                    isOk = false;
                    break;
                }
                tmp /= 10;
            }

            if (isOk) {
                list.add(number);
            }
        }


        while (true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            sb.append(list.get(N-1)).append("\n");
        }

        System.out.println(sb);

    }
}