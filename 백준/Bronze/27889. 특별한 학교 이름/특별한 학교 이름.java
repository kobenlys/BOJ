import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// testcase
// 2 3
// 3 2

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String name = br.readLine();

        if (name.equals("NLCS")) {
            System.out.println("North London Collegiate School");
        } else if (name.equals("BHA")) {
            System.out.println("Branksome Hall Asia");
        } else if (name.equals("KIS")) {
            System.out.println("Korea International School");
        } else {
            System.out.println("St. Johnsbury Academy");
        }



    }
}
