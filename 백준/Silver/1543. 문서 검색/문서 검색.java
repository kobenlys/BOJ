import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        String regex = br.readLine();
        int count = 0;
        String tmpStr = str;


        while(true){

            str = str.replaceFirst(regex, " ");
            if (str.equals(tmpStr)) {
                break;
            }

            tmpStr = str;
            count++;

        }

        System.out.println(count);

    }
}