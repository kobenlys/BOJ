import java.io.*;
import java.util.*;

public class Main {

    public static class SortInfo implements Comparable<SortInfo> {

        int number, frequency, firstIdx;

        public SortInfo(int nunber, int frequency, int firstIdx) {
            this.number = nunber;
            this.frequency = frequency;
            this.firstIdx = firstIdx;
        }

        @Override
        public int compareTo(SortInfo o) {
            if (frequency == o.frequency) {
                return firstIdx - o.firstIdx;
            }
            return o.frequency - frequency;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Map<Integer, SortInfo> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (map.containsKey(num)) {
                SortInfo si = map.get(num);
                si.frequency += 1;
                map.put(num, si);
            } else {
                map.put(num, new SortInfo(num, 1, i));
            }
        }

        List<SortInfo> list = new ArrayList<>(map.values());
        Collections.sort(list);

        for (SortInfo si : list) {
            for (int i = 0; i < si.frequency; i++) {
                sb.append(si.number).append(" ");
            }
        }
        System.out.println(sb);
    }
}