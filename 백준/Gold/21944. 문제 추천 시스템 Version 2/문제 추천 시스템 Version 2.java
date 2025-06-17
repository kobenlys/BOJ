import java.io.*;
import java.util.*;

public class Main {

    public static Comparator<Problem> upper1 = (p1, p2) -> {
        if (p1.level == p2.level) {
            return p1.problemNumber - p2.problemNumber;
        }
        return p1.level - p2.level;
    };

    public static class Problem {

        int problemNumber, category, level;

        public Problem(int problemNumber, int category, int level) {
            this.problemNumber = problemNumber;
            this.category = category;
            this.level = level;
        }
    }

    public static class RecommendSystem {

        StringBuilder sb = new StringBuilder();
        Map<Integer, TreeSet<Problem>> rec1 = new HashMap<>();
        TreeSet<Problem> rec2 = new TreeSet<>(upper1);

        public void add(Problem problem) {
            if (!rec1.containsKey(problem.category)) {
                rec1.put(problem.category, new TreeSet<>(upper1));
            }
            rec1.get(problem.category).add(problem);
            rec2.add(problem);
        }

        public void solved(Problem problem) {
            rec1.get(problem.category).remove(problem);
            rec2.remove(problem);
        }

        public void rec1Print(int category, int flag) {
            if (flag == 1) {
                sb.append(rec1.get(category).last().problemNumber).append("\n");
                return;
            }
            sb.append(rec1.get(category).first().problemNumber).append("\n");
        }

        public void rec2Print(int flag) {
            if (flag == 1) {
                sb.append(rec2.last().problemNumber).append("\n");
                return;
            }
            sb.append(rec2.first().problemNumber).append("\n");
        }

        public void rec3Print(int flag, int level) {
            if (flag == 1) {
                Problem problem = rec2.ceiling(new Problem(0, 0, level));
                sb.append(problem == null ? -1 : problem.problemNumber).append("\n");
                return;
            }
            Problem problem = rec2.floor(new Problem(0, 0, level));
            sb.append(problem == null ? -1 : problem.problemNumber).append("\n");
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Problem> cache = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        RecommendSystem rs = new RecommendSystem();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(p, g, l);
            cache.put(p, problem);
            rs.add(problem);
        }

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String ctrl = st.nextToken();

            switch (ctrl) {
                case "add": {
                    int p = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    int g = Integer.parseInt(st.nextToken());
                    Problem problem = new Problem(p, g, l);
                    cache.put(p, problem);
                    rs.add(problem);
                    break;
                }
                case "solved": {
                    int problemNumber = Integer.parseInt(st.nextToken());
                    rs.solved(cache.get(problemNumber));
                    cache.remove(problemNumber);
                    break;
                }
                case "recommend": {
                    int category = Integer.parseInt(st.nextToken());
                    int flag = Integer.parseInt(st.nextToken());
                    rs.rec1Print(category, flag);
                    break;
                }
                case "recommend2": {
                    int flag = Integer.parseInt(st.nextToken());
                    rs.rec2Print(flag);
                    break;
                }
                case "recommend3": {
                    int flag = Integer.parseInt(st.nextToken());
                    int level = Integer.parseInt(st.nextToken());
                    rs.rec3Print(flag, level);
                }

            }
        }
        System.out.println(rs.sb.toString());
    }
}