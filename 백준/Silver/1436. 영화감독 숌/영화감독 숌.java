import java.util.*;

public class Main {
    public static int N, cnt=0, i=666;


    public static int algorithm(int N) { // 구현
        if (Integer.toString(i).contains("666")) {
            cnt++;
        }
        if (cnt == N) {
            return i;
        }
        i++;
        return algorithm(N);
    }


    public static void main(String[] args) { //조건 입력

        Scanner sc = new Scanner(System.in);
         N = sc.nextInt();
        int res = 0;
        int sum = 666;



        System.out.println(algorithm(N));
    }
}
