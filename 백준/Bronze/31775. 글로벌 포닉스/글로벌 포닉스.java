import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashSet<Character> set = new HashSet<>();

        set.add('l');
        set.add('k');
        set.add('p');

        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();

        set.remove(str1.charAt(0));
        set.remove(str2.charAt(0));
        set.remove(str3.charAt(0));

        System.out.println(set.isEmpty() ? "GLOBAL" : "PONIX");
    }
}