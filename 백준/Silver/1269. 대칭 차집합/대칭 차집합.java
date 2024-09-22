import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < Math.max(N, M); i++) {
            if (i < N) {
                setA.add(Integer.parseInt(st1.nextToken()));
            }
            if (i < M) {
                setB.add(Integer.parseInt(st2.nextToken()));
            }
        }

        Iterator<Integer> iterA = setA.iterator();
        Iterator<Integer> iterB = setB.iterator();

        while (iterA.hasNext()) {
            if (!setB.contains(iterA.next())) {
                cnt++;
            }
        }

        while (iterB.hasNext()) {
            if (!setA.contains(iterB.next())) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}