import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> arr1 = new ArrayList<>();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            arr1.add(str1);
            map.put(str1, str2); 
        }

        arr1.sort(Comparator.reverseOrder());

        for (int i = 0; i < arr1.size(); i++) {
            if (map.containsKey(arr1.get(i)) && map.get(arr1.get(i)).equals("enter")) {
                sb.append(arr1.get(i)).append("\n");
                map.remove(arr1.get(i));
            }
        }
        System.out.print(sb);
    }
}