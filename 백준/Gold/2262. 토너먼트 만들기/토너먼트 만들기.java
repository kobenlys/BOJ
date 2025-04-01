import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = N; i > 1; i--) {
            int index = list.indexOf(i);

            if (index == list.size() - 1) {
                answer += i - list.get(index - 1);
                list.remove(index);
                continue;
            }

            if (index == 0) {
                answer += i - list.get(1);
                list.remove(index);
                continue;
            }

            answer += Math.min(i - list.get(index - 1), i - list.get(index + 1));
            list.remove(index);
        }

        System.out.println(answer);
    }
}