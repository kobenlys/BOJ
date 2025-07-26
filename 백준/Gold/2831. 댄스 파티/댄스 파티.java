import java.io.*;
import java.util.*;

public class Main {

    public static class Human implements Comparable<Human> {

        int height;
        boolean isMale;

        public Human(int height, boolean isMale) {
            this.height = height;
            this.isMale = isMale;
        }

        @Override
        public int compareTo(Human o) {
            return o.height - height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        List<Human> listManHigher = new ArrayList<>();
        List<Human> listManLower = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int man = Integer.parseInt(st.nextToken());
            if (man >= 0) {
                listManLower.add(new Human(man, true));
            } else {
                listManHigher.add(new Human(man * -1, true));
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int man = Integer.parseInt(st.nextToken());
            if (man >= 0) {
                listManHigher.add(new Human(man, false));
            } else {
                listManLower.add(new Human(man * -1, false));
            }
        }

        Collections.sort(listManHigher);
        Collections.sort(listManLower);

        Queue<Integer> maleCache = new ArrayDeque<>();
        for (Human human : listManHigher) {
            if (!maleCache.isEmpty() && !human.isMale && maleCache.peek() != human.height) {
                answer++;
                maleCache.poll();
            }
            if (human.isMale) {
                maleCache.offer(human.height);
            }
        }

        Queue<Integer> femaleCache = new ArrayDeque<>();
        for (Human human : listManLower) {
            if (!femaleCache.isEmpty() && human.isMale && femaleCache.peek() != human.height) {
                answer++;
                femaleCache.poll();
            }
            if (!human.isMale) {
                femaleCache.offer(human.height);
            }
        }

        System.out.println(answer);
    }
}