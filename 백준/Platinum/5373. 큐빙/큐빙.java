import java.io.*;
import java.util.*;

public class Main {
    public static HashMap<Character, int[]> map = new HashMap<>();
    public static char[] arr1;

    public static void cubing(char base, char dir) {
        int[] tmp = map.get(base); // 해당 면의 정보 가져오기.
        char[][] side = new char[3][3];
        List<Character> list = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            list.add(arr1[tmp[i]]);
        }

        if (dir == '+') { // 시계 방향
            // 보고있는 면 돌리면 같이 돌아가는 큐브칸 돌리기
            list.add(0, list.get(11));
            list.add(0, list.get(11));
            list.add(0, list.get(11));
            for (int i = 0; i < 3; i++) {
                list.remove(list.size() - 1);
            }
            // 보고있는 면만 돌리기
            for (int i = 0; i < 9; i++) { // 90도 시계방향 회전
                side[i % 3][2 - i / 3] = arr1[tmp[0] + i];
            }

        } else { // 반시계 방향
            // 보고있는 면 돌리면 같이 돌아가는 큐브칸 돌리기
            list.add(list.size(), list.get(0));
            list.add(list.size(), list.get(1));
            list.add(list.size(), list.get(2));
            for (int i = 0; i < 3; i++) {
                list.remove(0);
            }
            // 보고있는 면만 돌리기
            for (int i = 0; i < 9; i++) { // 90도 반시계 회전
                side[2 - i % 3][i / 3] = arr1[tmp[0] + i];
            }
        }

        // 본 배열에 업데이트
        for (int i = 0; i < 9; i++) {
            arr1[tmp[0] + i] = side[i / 3][i % 3];
        }

        for (int i = 1; i <= 12; i++) {
            arr1[tmp[i]] = list.get(i - 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        // 색깔
        char[] color = {'w', 'g', 'r', 'b', 'y', 'o'};
        arr1 = new char[54];

        // 해당 면의 시작 인덱스 번호와, 마주보는 면과 닿아 있는 주위 큐브칸 순서대로 저장
        // 해쉬맵으로 저장하고 Key값 기준으로 마주보는면 + 주위 큐브칸을 같이 돌려준다.
        int[] diceSide = {0, 51, 52, 53, 29, 28, 27, 20, 19, 18, 11, 10, 9};
        map.put('U', diceSide);
        diceSide = new int[]{18, 6, 7, 8, 27, 30, 33, 38, 37, 36, 17, 14, 11};
        map.put('F', diceSide);
        diceSide = new int[]{9, 0, 3, 6, 18, 21, 24, 36, 39, 42, 45, 48, 51};
        map.put('L', diceSide);
        diceSide = new int[]{27, 8, 5, 2, 53, 50, 47, 44, 41, 38, 26, 23, 20};
        map.put('R', diceSide);
        diceSide = new int[]{36, 24, 25, 26, 33, 34, 35, 47, 46, 45, 15, 16, 17};
        map.put('D', diceSide);
        diceSide = new int[]{45, 42, 43, 44, 35, 32, 29, 2, 1, 0, 9, 12, 15};
        map.put('B', diceSide);


        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());

            // 각 큐브칸별 색깔 넣기
            for (int i = 0; i < 54; i++) {
                arr1[i] = color[i / 9];
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                String control = st.nextToken();
                char base = control.charAt(0);
                char dir = control.charAt(1);

                cubing(base, dir);
            }
            for (int j = 0; j < 9; j++) {
                sb.append(arr1[j]);
                if (j == 2) sb.append("\n");
                if (j == 5) sb.append("\n");
                if (j == 8) sb.append("\n");
            }

        }
        System.out.println(sb);
    }
}