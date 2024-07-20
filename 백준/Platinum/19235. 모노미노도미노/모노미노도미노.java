import java.io.*;
import java.util.*;

public class Main {
    public static int score;
    public static int[][] blueMap;
    public static int[][] greenMap;

    // 파랑보드 1x1 블럭 생성
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

    // 초록보드 1x1 블럭 생성
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

    // 파랑보드 2x1 블럭 생성
    public static void typeTwoBlue(int y, int limit) {
        int lastX = 0;
        // 2x1 블럭이 걸리지 않는 조건에서 최대한 밑에 배치하기
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

    // 파랑보드 2x1 블럭 생성
    public static void typeTwoGreen(int x, int limit) {
        int lastY = 0;
        // 2x1 블럭이 걸리지 않는 조건에서 최대한 밑에 배치하기
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

    // 완성된 열 모든 블럭 제거 후 스코어++
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

    // 완성된 행 모든 블럭 제거 후 스코어++
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

    // 각보드 별 세이프존에 블럭이 있다면, 있는 수 만큼 가장 밑 보드 행렬 제거
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

    // 파랑보드 블럭 내리기,
    public static void sortBlockBlue() {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                // 따로 만들 필요없이, 이미 만들어진 블럭배치 함수를 재사용한다
                // 리미트  -> 끝 범위 안에서 블럭 배치한다.
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

    // 초록보드 블럭 내리기
    public static void sortBlockGreen() {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                // 따로 만들 필요없이, 이미 만들어진 블럭배치 함수를 재사용한다
                // 리미트  -> 끝 범위 안에서 블럭 배치한다.
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

        blueMap = new int[4][6];
        greenMap = new int[6][4];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            // 그린기준
            // 1x1 블럭은 typeOne 함수 사용해서 배치
            // 1x2(가로) 블럭은 typeTwo 함수 사용해서 배치
            // 2x1(세로) 블럭은 typeOne 함수 2번 사용해서 배치.
            // -> 세로블럭은 다른 블럭과 걸칠 수 없기 때문에, 1x1블럭 2개로 생성해도 무관하다.
            // -> 반대로 가로블럭은 밑으로 이동시 다른 블럭에 걸칠 수 있기때문에 예외처리 해야한다.
            
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

            // 파랑보드, 초록보드 테트리스
            // 완성 -> 블럭 내리기 -> 완성, 반복적으로 제거
            while (scoreCntBlue()) { // 열 완성시
                sortBlockBlue(); // 윗 블럭 내리기.
            }

            while (scoreCntGreen()) {
                sortBlockGreen(); // 윗 블럭 내리기.
            }

            // 더 이상 점수를 올릴 수 없다면
            // 만약 세이프존에 블럭 있다면
            // true -> 블럭이 있는 행, 열  숫자만큼 가장 밑에 있는 블럭 모두 제거
            // false -> 패스
            if (checkSafeAndDelete()) { // 만약 세이프존에 블럭이 있다면
                // 가장 밑 블럭제거 후, 다시 블럭 내리기.
                sortBlockBlue();
                sortBlockGreen();
            }
        }

        int cntExistBlock = 0;
        // 남은 블럭 찾기
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
