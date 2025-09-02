import java.io.*;
import java.util.*;

public class Main {

    public static int answer;
    public static Egg[] arr1;

    public static class Egg {

        int durability, power;
        boolean isAlive;

        public Egg(int durability, int power) {
            this.durability = durability;
            this.power = power;
            this.isAlive = true;
        }

        public void check() {
            if (this.durability <= 0) {
                isAlive = false;
            }
        }

        public void eggVsEgg(Egg 달걀) {
            this.durability -= 달걀.power;
            달걀.durability -= this.power;
            this.check();
            달걀.check();
        }
    }

    public static int allEggCheck() {
        int deadCnt = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].isAlive) {
                deadCnt++;
            }
        }
        return deadCnt;
    }

    public static void dfs(int idx) {

        if (idx == arr1.length) {
            answer = Math.max(answer, allEggCheck());
            return;
        }

        if (!arr1[idx].isAlive) {
            dfs(idx + 1);
            return;
        }
        boolean isActive = false;

        for (int i = 0; i < arr1.length; i++) {
            if (i == idx || !arr1[i].isAlive) {
                continue;
            }

            int egg1Durability = arr1[idx].durability;
            int egg2Durability = arr1[i].durability;
            boolean egg1Stat = arr1[idx].isAlive;
            boolean egg2Stat = arr1[i].isAlive;
            isActive = true;
            arr1[idx].eggVsEgg(arr1[i]);

            dfs(idx + 1);
            arr1[idx].durability = egg1Durability;
            arr1[i].durability = egg2Durability;
            arr1[idx].isAlive = egg1Stat;
            arr1[i].isAlive = egg2Stat;
        }

        if (!isActive) {
            dfs(idx+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr1[i] = new Egg(d, p);
        }

        dfs(0);
        System.out.println(answer);
    }
}