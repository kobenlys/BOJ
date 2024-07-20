import java.io.*;
import java.util.*;

public class Main {
    public static int score;
    public static int[][] blueMap;
    public static int[][] greenMap;

    public static void typeOneBlue(int y, int limit) {
        int lastX = 0;
        for (int i = limit; i < 6; i++) {
            if (blueMap[y][i] == 0) {
                lastX = i;
            } else {
                break;
            }
        }
        blueMap[y][lastX] = 1;
    }

    public static void typeOneGreen(int x, int limit) {
        int lastY = 0;
        for (int i = limit; i < 6; i++) {
            if (greenMap[i][x] == 0) {
                lastY = i;
            } else {
                break;
            }
        }
        greenMap[lastY][x] = 1;
    }

    public static void typeTwoBlue(int y, int limit) {
        int lastX = 0;
        for (int i = limit; i < 6; i++) {
            if (blueMap[y][i] == 0 && blueMap[y + 1][i] == 0) {
                lastX = i;
            } else {
                break;
            }
        }
        blueMap[y][lastX] = 2;
        blueMap[y + 1][lastX] = 2;
    }

    public static void typeTwoGreen(int x, int limit) {
        int lastY = 0;
        for (int i = limit; i < 6; i++) {
            if (greenMap[i][x] == 0 && greenMap[i][x + 1] == 0) {
                lastY = i;
            } else {
                break;
            }
        }
        greenMap[lastY][x] = 2;
        greenMap[lastY][x + 1] = 2;
    }

    public static boolean scoreCntBlue() {
        boolean isUpdate = false;
        for (int i = 0; i < 6; i++) {
            boolean isMake = true;
            for (int j = 0; j < 4; j++) {
                if (blueMap[j][i] == 0) {
                    isMake = false;
                    break;
                }
            }
            if (!isMake) continue;
            score++;
            for (int j = 0; j < 4; j++) {
                isUpdate = true;
                blueMap[j][i] = 0;
            }
        }

        return isUpdate;
    }

    public static boolean scoreCntGreen() {
        boolean isUpdate = false;
        for (int i = 0; i < 6; i++) {
            boolean isMake = true;
            for (int j = 0; j < 4; j++) {
                if (greenMap[i][j] == 0) {
                    isMake = false;
                    break;
                }
            }
            if (!isMake) continue;
            score++;
            for (int j = 0; j < 4; j++) {
                isUpdate = true;
                greenMap[i][j] = 0;
            }
        }

        return isUpdate;
    }

    public static boolean checkSafeAndDelete() {
        int cntB = 0;
        int cntG = 0;
        for (int i = 1; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (blueMap[j][i] != 0) {
                    cntB++;
                    break;
                }
            }
        }

        for (int i = 5; i > 5 - cntB; i--) {
            for (int j = 0; j < 4; j++) {
                blueMap[j][i] = 0;
            }
        }

        for (int i = 1; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (greenMap[i][j] != 0) {
                    cntG++;
                    break;
                }
            }
        }

        for (int i = 5; i > 5 - cntG; i--) {
            for (int j = 0; j < 4; j++) {
                greenMap[i][j] = 0;
            }
        }
        if (cntG != 0 || cntB != 0) return true;
        return false;
    }

    public static void sortBlockBlue() {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (blueMap[j][i] == 1) {
                    blueMap[j][i] = 0;
                    typeOneBlue(j, i);
                }

                if (j != 3 && blueMap[j][i] == 2 && blueMap[j + 1][i] == 2) {
                    blueMap[j][i] = 0;
                    blueMap[j + 1][i] = 0;
                    typeTwoBlue(j, i);
                }

            }
        }
    }

    public static void sortBlockGreen() {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (greenMap[i][j] == 1) {
                    greenMap[i][j] = 0;
                    typeOneGreen(j, i);
                }
                if (j != 3 && greenMap[i][j] == 2 && greenMap[i][j + 1] == 2) {
                    greenMap[i][j] = 0;
                    greenMap[i][j + 1] = 0;
                    typeTwoGreen(j, i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // ㅁ
        // ㅁ ㅁ
        // ㅁ
        // ㅁ

        // [순서]
        // 1. 블럭을 쌓는다 모든 블력은 레드블럭에서 시작히기때문에 범위를 벗어나지 않는다
        // 2. 블럭을 가장 끝 인덱스 부터 넣을 수 있는지 체크한다.
        // 3. 행, 열이 블럭으로 가득찬 곳 제거하면서 스코어 ++
        // 4. 블럭을 아래인덱스로 다시 정렬 후 3번 반복
        // 5. 3번에서 스코어를 얻지 못했다면 6번 실행
        // 6. 각 보드의 0,1 행,열(safeZone)에 블럭있는지 확인
        // 7. 블럭이 있다면 블럭이 있는 행,열 만큼 마지막 인덱스 제거
        // 8. 다시 반복

        // 각 블럭 세로블럭은 그냥 각각 다른 1X1블럭으로 생각해도됨 즉 1*1 블럭을 2번 쌓자

        // [ 재료 ]
        // 1. 1x1 블록,  2x1,1x2 블럭
        // 2. 스코어 체크
        // 3. 블럭 아래로 정렬 + 1x2 , 2x1 블럭 예외처리
        // 4. safeZone 카운트 및 아래 행렬 제거

        blueMap = new int[4][6];
        greenMap = new int[6][4];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (type == 1) {
                typeOneBlue(y, 0);
                typeOneGreen(x, 0);
            }

            if (type == 2) {
                typeOneBlue(y, 0);
                typeOneBlue(y, 0);
                typeTwoGreen(x, 0);
            }

            if (type == 3) {
                typeOneGreen(x, 0);
                typeOneGreen(x, 0);
                typeTwoBlue(y, 0);
            }

            while (scoreCntBlue()) {
                sortBlockBlue();
            }

            while (scoreCntGreen()) {
                sortBlockGreen();
            }

            if (checkSafeAndDelete()) {
                sortBlockBlue();
                sortBlockGreen();
            }
        }
        int cntExistBlock = 0;


        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (blueMap[j][i] > 0) cntExistBlock++;
                if (greenMap[i][j] > 0) cntExistBlock++;
            }
        }

        System.out.println(score);
        System.out.println(cntExistBlock);
    }
}