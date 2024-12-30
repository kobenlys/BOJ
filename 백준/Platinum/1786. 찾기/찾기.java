import java.io.*;
import java.util.*;

public class Main {
    public static int[] pattern;

    public static void makeTable(String str) {
        int len = str.length();
        pattern = new int[len];
        int index = 0;
        for (int i = 1; i < len; i++) {
            while (index > 0 && str.charAt(i) != str.charAt(index)) {
                index = pattern[index - 1];
            }
            if (str.charAt(i) == str.charAt(index)) {
                pattern[i] = ++index;
            }
        }
    }

    public static List<Integer> kmp(String str1, String str2) {
        List<Integer> list = new ArrayList<>();
        int matchCnt = 0;
        int index = 0;

        for (int i = 0; i < str1.length(); i++) {
            while (index > 0 && str1.charAt(i) != str2.charAt(index)) {
                index = pattern[index - 1];
            }

            if (str1.charAt(i) == str2.charAt(index)) {
                if (index == str2.length() - 1) {
                    matchCnt++;
                    list.add(i - index + 1);
                    index = pattern[index];
                } else {
                    index++;
                }
            }
        }

        list.add(matchCnt);
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str1 = br.readLine();
        String str2 = br.readLine();

        makeTable(str2);
        List<Integer> list = kmp(str1, str2);
        sb.append(list.get(list.size() - 1)).append("\n");

        for (int i = 0; i < list.size() - 1; i++) {
            sb.append(list.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}