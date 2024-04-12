import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arr1 = new int[3];

        st = new StringTokenizer(br.readLine()," ");
        for(int i =0; i<3; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);

        System.out.println(arr1[0]+" "+arr1[1]+" "+arr1[2]);
    }
}