import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static HashMap<Character, Integer> map = new HashMap<>();
    public static ArrayList<Character> arr1 = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();
    public static String str;
    public static char odd;

    public static void odd() { // 홀수 경우
        int oddCnt = 0;

        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            if (map.containsKey(key)) {
                if (map.containsKey(key) && map.get(key) % 2 > 0) {
                    oddCnt++;
                    odd = key; // 가운데 문자 저장.
                    if (oddCnt > 1) {
                        // 홀수개 인 문자가 한개 이상일때 팰린드롬 불가능.
                        System.out.print("I'm Sorry Hansoo");
                        System.exit(0);
                    }
                }
                for (int j = 0; j < map.get(key) / 2; j++) {
                    // 문자 갯수 / 2 개 만큼 저장해야 짝에 맞게 출력가능
                    arr1.add(key);
                }
                map.remove(key);
            }
        }

        if (oddCnt == 1) {
            Collections.sort(arr1); // 사전순으로 출력하기 위함.
            for (char e : arr1) {
                sb.append(e);
            }
            String front = sb.toString() + odd; // 가운데 문자 추가.
            String back = String.valueOf(sb.reverse());
            System.out.print(front + back);

        }
    }

    public static void even() { // 짝수 경우

        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            if (map.containsKey(key)) {
                if (map.get(key) % 2 > 0) {
                    // 문자열이 짝수라면, 단 하나의 홀수개 인 문자 존재시 팰린드롬 불가능.
                    System.out.print("I'm Sorry Hansoo");
                    System.exit(0);
                }
                for (int j = 0; j < map.get(key) / 2; j++) {
                    arr1.add(key);
                }
                map.remove(key);
            }
        }

        Collections.sort(arr1);
        for (char e : arr1) {
            sb.append(e);
        }
        String front = sb.toString();
        String back = String.valueOf(sb.reverse());
        System.out.print(front + back);
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            // 동일 단어 개수 출력
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        if (str.length() % 2 == 0) {
            even(); // 문자열 길이가 짝수 일때
            // 모든 문자가 짝수개가 존재해야함.
        } else {
            odd(); // 문자열 길이가 홀수 일때
            // 단 하나의 문자만 홀수이고, 나머지는 짝수여야함.
        }
    }
}
