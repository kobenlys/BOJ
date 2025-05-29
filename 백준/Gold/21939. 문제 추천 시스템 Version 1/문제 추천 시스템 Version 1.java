import java.io.*;
import java.util.*;

public class Main {

    public static class Problem {

        int number, diff;

        public Problem(int number, int diff) {
            this.number = number;
            this.diff = diff;
        }
    }

    public static class 목데이터김성준 {

        TreeSet<Problem> upper;
        TreeSet<Problem> lower;
        Map<Integer, Problem> store = new HashMap<>();

        public 목데이터김성준(TreeSet<Problem> upper, TreeSet<Problem> lower) {
            this.upper = upper;
            this.lower = lower;
        }


        public void add(Problem problem) {
            store.put(problem.number, problem);
            upper.add(problem);
            lower.add(problem);
        }

        public Problem recommended(int flag) {
            if (flag == 1) {
                return upper.first();
            }
            return lower.first();
        }

        public void solved(int problemNumber) {
            upper.remove(store.get(problemNumber));
            lower.remove(store.get(problemNumber));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Comparator<Problem> upper = (p1, p2) -> {
            if (p1.diff == p2.diff) {
                return p2.number - p1.number;
            }
            return p2.diff - p1.diff;
        };

        Comparator<Problem> lower = (p1, p2) -> {
            if (p1.diff == p2.diff) {
                return p1.number - p2.number;
            }
            return p1.diff - p2.diff;
        };

        목데이터김성준 problemRecommend = new 목데이터김성준(new TreeSet<>(upper),
            new TreeSet<>(lower));

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());
            int diff = Integer.parseInt(st.nextToken());

            Problem problem = new Problem(number, diff);
            problemRecommend.add(problem);
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String ctrl = st.nextToken();

            if ("add".equals(ctrl)) {
                int number = Integer.parseInt(st.nextToken());
                int diff = Integer.parseInt(st.nextToken());
                Problem problem = new Problem(number, diff);
                problemRecommend.add(problem);

            } else if ("solved".equals(ctrl)) {
                int number = Integer.parseInt(st.nextToken());
                problemRecommend.solved(number);
            } else {
                int flag = Integer.parseInt(st.nextToken());
                Problem problem = problemRecommend.recommended(flag);
                sb.append(problem.number).append("\n");
            }
        }

        System.out.println(sb);
    }
}