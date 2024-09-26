import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        int[] arr1 = new int[26];

        for (int i = 0; i < str.length(); i++) {
            arr1[str.charAt(i) - 'a']++;
        }

        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]+" ");
        }



    }
}