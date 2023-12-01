import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();

        String input = br.readLine();
        String str = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 26; i++) {
            map.put(str.charAt(i), -1);
        }
        
        for (int i = 0; i < input.length(); i++) {
            if (map.get(input.charAt(i)) != -1) {
                continue;
            }
            map.put(input.charAt(i), i);
        }


        for (int i = 0; i < 26; i++) {
            sb.append(map.get(str.charAt(i))).append(" ");
        }
        System.out.println(sb);
    }
}


