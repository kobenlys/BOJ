import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static int[][] food;
    public static int[][] foodNow;
    public static ArrayList<Integer>[][] woods;

    public static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!woods[i][j].isEmpty()) {
                    // 어린 나무부터 양분 공급
                    Collections.sort(woods[i][j]);
                    int die = 0;
                    for (int k = 0; k < woods[i][j].size(); k++) {

                        if (foodNow[i][j] >= woods[i][j].get(k)) {
                            // 양분 먹을 수 있다면, 성장 + 해당 땅에 양분 빼기
                            int Wood = woods[i][j].get(k) + 1;
                            foodNow[i][j] -= woods[i][j].get(k);
                            woods[i][j].add(k, Wood);
                            woods[i][j].remove(k + 1);

                        } else {
                            // 양분을 먹을 수 없는 경우
                            die += woods[i][j].get(k) / 2;
                            woods[i][j].remove(k);
                            k--;

                        }
                    }
                    // 죽은 나무에 해당하는 양분 땅에 공급
                    foodNow[i][j] += die;
                }
            }
        }
    }

    public static void autumn() { // 번식가능한 나무 번식하기
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!woods[i][j].isEmpty()) {
                    for (int k = 0; k < woods[i][j].size(); k++) {
                        int Wood = woods[i][j].get(k);
                        if (Wood % 5 == 0) {
                            for (int l = 0; l < 8; l++) {
                                // 중앙을 제외한 상,하,좌,우,좌상,우상,좌하,우하 번식
                                int nx = j + dx[l];
                                int ny = i + dy[l];
                                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                                woods[ny][nx].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void winter() { // 로봇으로 양분 공급
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                foodNow[i][j] += food[i][j];
            }
        }
    }

    public static int woodCnt() { // K년 후 남아있는 나무 개수 출력
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!woods[i][j].isEmpty()) {
                    cnt += woods[i][j].size();
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        food = new int[N][N];
        foodNow = new int[N][N];
        woods = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                food[i][j] = Integer.parseInt(st.nextToken());
                foodNow[i][j] = 5;
                woods[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            woods[y][x].add(z);
        }

        for (int i = 0; i < K; i++) {
            springAndSummer(); // 양분 먹고 나무 키우기 + 죽은 나무 양분으로 변경
            autumn(); // 번식가능한 나무 번식하기.
            winter(); // 로봇으로 양분 뿌리기
        }
        System.out.print(woodCnt());
    }
}