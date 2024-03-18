import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, ans = 11;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static HashMap<String, Integer> map = new HashMap<>();


    public static void bfs(String input) {
        Queue<String> qu = new LinkedList<>();
        map.put(input, 0);
        qu.offer(input);

        while (!qu.isEmpty()) {

            String now = qu.poll();
            // 1차원 배열의 구슬 인덱스를 2차원 배열의 구슬 인덱스로 변환
            int red = now.indexOf("R");
            int blue = now.indexOf("B");
            int redX = red % M;
            int redY = red / M;
            int blueX = blue % M;
            int blueY = blue / M;


            for (int i = 0; i < 4; i++) {

                int nextRedX = redX + dx[i];
                int nextRedY = redY + dy[i];
                int nextBlueX = blueX + dx[i];
                int nextBlueY = blueY + dy[i];

                // 빨간구슬만 움직 일 수 있을때
                if (now.charAt(M * nextRedY + nextRedX) == '.'
                        || now.charAt(M * nextRedY + nextRedX) == 'O') {
                    // whoFristMove 함수 호출
                    // return X = 파란구슬 또는 모든 구슬이 구멍에 빠졌을때
                    // -> 다음번 계산에서 제외

                    // return O = 빨간 구슬만 구멍에 빠졌을때
                    // -> 정답 출력

                    // return 맵 = 두 구슬 다 맵에 존재할때
                    // -> 다음번 계산을 위해 큐에 넣기

                    String newInput = whoFirstMove(i, now);

                    if (newInput.equals("O")) {
                        ans = Math.min(ans, map.get(now) + 1);
                        continue;
                    } else if (newInput.equals("X")) {
                        continue;
                    }

                    if (!map.containsKey(newInput)) {
                        map.put(newInput, map.get(now) + 1);
                        qu.offer(newInput);
                    } else if (map.get(newInput) > map.get(now) + 1) {
                        // 이미 저장된 값이 최솟값(움직임 횟수)이 아닐때
                        // 최솟값으로 해당경로 재 지정후 qu에 저장
                        map.put(newInput, map.get(now) + 1);
                        qu.offer(newInput);
                    }

                    // 파란구슬만 움직 일 수 있을때
                } else if (now.charAt(M * nextBlueY + nextBlueX) == '.') {

                    // whoFristMove 함수 호출 -> 파란구슬 먼저 움직여야함.
                    String newInput = whoFirstMove(i, now);

                    if (newInput.equals("O")) {
                        ans = Math.min(ans, map.get(now) + 1);
                        continue;
                    } else if (newInput.equals("X")) {
                        continue;
                    }

                    if (!map.containsKey(newInput)) {
                        map.put(newInput, map.get(now) + 1);
                        qu.offer(newInput);

                    } else if (map.get(newInput) > map.get(now) + 1) {
                        // 이미 저장된 값이 최솟값(움직임 횟수)이 아닐때
                        // 최솟값으로 해당경로 재 지정후 qu에 저장
                        map.put(newInput, map.get(now) + 1);
                        qu.offer(newInput);
                    }
                }
            }
        }
    }

    public static String moveRedBall(int dir, String map) {
        StringBuilder sb = new StringBuilder();
        // 문자열에 있는 인덱스를 2차원 인덱스로 변경
        int red = map.indexOf("R");
        int redX = red % M;
        int redY = red / M;
        sb.append(map);

        // 빨간 구슬 움직이기.
        for (int i = 0; i < 10; i++) {

            int tmpX = redX; // 구슬 움직였다면 전 자리 구슬 제거 위함
            int tmpY = redY;
            // 구슬 움직이기
            redX = redX + dx[dir];
            redY = redY + dy[dir];
            // 2차원 배열 인덱스 주소를 1차원 배열 인덱스로 변경
            int strIdx = M * redY + redX;
            // 빈공간을 기준으로 움직인다.
            if (sb.charAt(strIdx) == '.') {
                // 구슬 움직이고
                sb.setCharAt(strIdx, 'R');
                // 전 자리 '.' 처리 하기.
                sb.setCharAt(M * tmpY + tmpX, '.');

            } else if (sb.charAt(strIdx) == 'O') {
                sb.setCharAt(M * tmpY + tmpX, '.');
                break;
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static String moveBlueBall(int dir, String map) {
        StringBuilder sb = new StringBuilder();
        // 문자열에 있는 인덱스를 2차원 인덱스로 변경

        int blue = map.indexOf("B");

        int blueX = blue % M;
        int blueY = blue / M;

        sb.append(map);

        for (int i = 0; i < 10; i++) {

            int tmpX = blueX;
            int tmpY = blueY;
            blueX = blueX + dx[dir];
            blueY = blueY + dy[dir];
            int strIdx = M * blueY + blueX;

            if (sb.charAt(strIdx) == '.') {
                sb.setCharAt(strIdx, 'B');
                sb.setCharAt(M * tmpY + tmpX, '.');
            } else if (sb.charAt(strIdx) == 'O') {
                sb.setCharAt(M * tmpY + tmpX, '.');
                break;
            } else {
                break;
            }
        }
        return sb.toString();
    }

    // 보드 움직여서 구슬 움직이기 구현
    public static String whoFirstMove(int dir, String map) {
        int isRedBall;
        int isBlueBall;
        String nMap = "";

        isRedBall = map.indexOf('R');
        isBlueBall = map.indexOf('B');

        int tmpRedY = isRedBall / M;
        int tmpBlueY = isBlueBall / M;
        int tmpRedX = isRedBall % M;
        int tmpBlueX = isBlueBall % M;

        // 이동방향에 따른 먼저 움직여야 할 구슬 선택
        // #..R.B..# -> 왼쪽방향 이동시 빨간구슬 부터, 오른쪽방향 이동시 파란구슬 부터.
        // 구슬이 올바르게 정렬 할 수 있다.
        if (dir == 0) {
            if (tmpRedY < tmpBlueY) {
                nMap = moveRedBall(dir, map);
                nMap = moveBlueBall(dir, nMap);
            } else {
                nMap = moveBlueBall(dir, map);
                nMap = moveRedBall(dir, nMap);
            }
        } else if (dir == 1) {
            if (tmpRedY > tmpBlueY) {
                nMap = moveRedBall(dir, map);
                nMap = moveBlueBall(dir, nMap);
            } else {
                nMap = moveBlueBall(dir, map);
                nMap = moveRedBall(dir, nMap);
            }
        } else if (dir == 2) {
            if (tmpRedX < tmpBlueX) {
                nMap = moveRedBall(dir, map);
                nMap = moveBlueBall(dir, nMap);
            } else {
                nMap = moveBlueBall(dir, map);
                nMap = moveRedBall(dir, nMap);
            }
        }else{
            if (tmpRedX > tmpBlueX) {
                nMap = moveRedBall(dir, map);
                nMap = moveBlueBall(dir, nMap);
            } else {
                nMap = moveBlueBall(dir, map);
                nMap = moveRedBall(dir, nMap);
            }
        }

        // indexOf -> 문자열내 해당 문자열 없다면 '-1' 리턴
        // 구슬의 존재여부를 판단 할 수 있음.
        isRedBall = nMap.indexOf('R');
        isBlueBall = nMap.indexOf('B');


        if (isRedBall == -1 && isBlueBall != -1) {
            // 빨간구슬만 구멍에 들어갔을때 ->정답
            return "O";
        } else if (isRedBall != -1 && isBlueBall != -1) {
            // 두 구슬 다 존재할때 -> 맵, 큐에 넣기
            return nMap;
        } else {
            // 파란구슬, 두 구슬 다 구멍에 빠졌을때 -> 계산할 필요 X
            return "X";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String input = "";
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            input = input.concat(str);
        }

        bfs(input);
        // 조건에 맞는 답 출력
        System.out.println(ans <= 10 ? ans : -1);
    }
}