import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        if (num >= 60 && num <= 69) {
            System.out.println("D");
        } else if (num >= 70 && num <= 79) {
            System.out.println("C");
        } else if (num >= 80 && num <= 89) {
            System.out.println("B");
        } else if (num >= 90 && num <= 100) {
            System.out.println("A");
        }else{
            System.out.println("F");
        }
    }
}


