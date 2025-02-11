import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Map<String, Double> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        int size = 0;
        while (true) {
            String wood = br.readLine();
            if(wood == null || wood.length() == 0) break;

            list.add(wood);
            map.put(wood, map.getOrDefault(wood, 0.0) + 1);
            size++;
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {

            if (!map.containsKey(list.get(i))) {
                continue;
            }
            double woodCnt = map.get(list.get(i));
            double per = woodCnt / size * 100;
            BigDecimal perRound = new BigDecimal(per).setScale(4, RoundingMode.HALF_UP);
            map.remove(list.get(i));
            sb.append(list.get(i)).append(" ").append(perRound).append("\n");
        }

        System.out.println(sb);
    }
}