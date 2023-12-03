import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int i = 1; i <= 10000; i++) {
            String num = String.valueOf(i);
            int sum = i;
            // 생성자로 생성한 번호 HashMap에 저장
            for (int j = 0; j < num.length(); j++) {
                sum += Character.getNumericValue(num.charAt(j));
            }
            map.put(sum, true);
        }

        for (int i = 1; i <= 10000; i++) {
            // Key = i 값이 HashMap에 없다면 셀프넘버이다.
            if (!map.containsKey(i)) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }
}


