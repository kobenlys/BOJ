import java.util.*;

public class Main {
   public static String N;
   public static int min;

    public static void algorithm(int num) { // 구현

        for(int i=num; i>0; i--){
            int tmp = i;
            int hap=0;
            String str1 = String.valueOf(tmp);

            for(int j=0; j<str1.length(); j++){
                hap += Character.getNumericValue(str1.charAt(j));
            }

            if(num == tmp+hap){
                min = tmp;
            }
        }
        System.out.println(min);
    }


    public static void main(String[] args) { //조건 입력
        Scanner sc = new Scanner(System.in);
         N = sc.next();

         int num = Integer.parseInt(N);

        algorithm(num);


    }
}
