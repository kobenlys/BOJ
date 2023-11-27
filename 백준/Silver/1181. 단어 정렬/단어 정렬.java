import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> hs = new HashSet<>(); // HashSet은 중복 제거용 으로 사용
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 입력
        for (int i = 0; i < N; i++) {
            hs.add(br.readLine());
        }

        String[] arr1 = new String[hs.size()];
        // HashSet은 get 메서드가 없기 때문에 반복자 Iterator를 사용한다.
        Iterator<String> iter = hs.iterator();

        int i = 0;
        while (iter.hasNext()) {
            arr1[i] = iter.next();
            i++;
        }

        // 두 비교대상의 문자열 길이가 같다면 문자열 기준 사전순 정렬
        // 두 비교대상의 문자열 길이가 다르다면 문자열 길이 기준 내림차순 정렬
        Arrays.sort(arr1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                } else {
                    return o1.compareTo(o2);
                }
            }
        });

        // 출력
        for (String e : arr1) {
            sb.append(e).append("\n");
        }
        System.out.println(sb);
    }
}
