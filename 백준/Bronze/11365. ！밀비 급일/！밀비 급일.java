import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true){
            String str = br.readLine();
            if(str.equals("END")) break;

            sb = new StringBuilder(str);
            System.out.println(sb.reverse());
        }
    }
}