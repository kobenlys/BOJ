import java.io.*;
import java.util.*;

public class Main {

    public static int[] kingPos;
    public static int[] stonePos;
    public static Map<String, int[]> map;


    public static int[] convertToIdx(String pos) {
        int x = pos.charAt(0) - 'A';
        int y = pos.charAt(1) - '0';
        return new int[]{y, x};
    }

    public static void printPos() {
        String pos1 = (char) ('A' + kingPos[1] - 1) + "" + kingPos[0];
        String pos2 = (char) ('A' + stonePos[1] - 1) + "" + stonePos[0];
        System.out.println(pos1);
        System.out.println(pos2);
    }

    public static void moveKing(String command) {

        int[] dx = map.get(command);

        if (kingPos[0] + dx[0] > 0 && kingPos[0] + dx[0] < 9
            && kingPos[1] + dx[1] > 0 && kingPos[1] + dx[1] < 9) {
            kingPos[0] += dx[0];
            kingPos[1] += dx[1];
        }

        if (kingPos[0] == stonePos[0] && kingPos[1] == stonePos[1]) {

            if (stonePos[0] + dx[0] > 0 && stonePos[0] + dx[0] < 9
                && stonePos[1] + dx[1] > 0 && stonePos[1] + dx[1] < 9) {
                stonePos[0] += dx[0];
                stonePos[1] += dx[1];
            } else{
                kingPos[0] -= dx[0];
                kingPos[1] -= dx[1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        kingPos = convertToIdx(st.nextToken());
        stonePos = convertToIdx(st.nextToken());
        int times = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        map.put("R", new int[]{0, 1});
        map.put("L", new int[]{0, -1});
        map.put("B", new int[]{-1, 0});
        map.put("T", new int[]{1, 0});
        map.put("RT", new int[]{1, 1});
        map.put("LT", new int[]{1, -1});
        map.put("RB", new int[]{-1, 1});
        map.put("LB", new int[]{-1, -1});

        kingPos[1]++;
        stonePos[1]++;

        while (times-- > 0) {
            String command = br.readLine();
            moveKing(command);
        }

        printPos();
    }
}