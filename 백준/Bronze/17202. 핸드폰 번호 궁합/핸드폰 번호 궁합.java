import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> copy = new ArrayList<>();

        String n1 = br.readLine();
        String n2 = br.readLine();

        for (int i = 0; i < n1.length(); i++) {
            arr1.add(Character.getNumericValue(n1.charAt(i)));
            arr1.add(Character.getNumericValue(n2.charAt(i)));
        }

        while (arr1.size() > 2) {
            for (int i = 0; i < arr1.size()-1; i++) {

                copy.add((arr1.get(i) + arr1.get(i + 1))%10);
            }

            arr1 = new ArrayList<>(copy);
            copy.clear();
        }
        System.out.print(arr1.get(0) + "" + arr1.get(1));
    }
}