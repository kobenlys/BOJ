import java.io.*;
import java.util.*;

public class Main {

    public static class Game {

        int nowIndex;
        int target;
        int[] arr1;

        public Game(int nowIndex, int target, int[] arr1) {
            this.nowIndex = nowIndex;
            this.target = target;
            this.arr1 = arr1;
        }

        public boolean rollingDice(int diceNumber) {
            nowIndex += diceNumber;
            if (nowIndex >= target) {
                return true;
            }
            boolean isOk = bonusMove(arr1[nowIndex]);
            return isOk;
        }

        public boolean bonusMove(int move) {
            nowIndex += move;
            if (nowIndex >= target) {
                return true;
            }
            return false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Game game = new Game(1, N, new int[N + 1]);

        for (int i = 1; i <= N; i++) {
            game.arr1[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= M; i++) {
            if (game.rollingDice(Integer.parseInt(br.readLine()))) {
                System.out.println(i);
                return;
            }
        }

    }
}