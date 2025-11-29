import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int basket = sc.nextInt();
        int rotate = sc.nextInt();

        int[] baskets = new int[basket];
        for(int i = 0; i < basket; i++){
            baskets[i] = i+1;
        }

        for(int i =0; i < rotate; i++ ){
            int begin = sc.nextInt()-1;
            int end = sc.nextInt()-1;
            int mid = sc.nextInt()-1;

            int[] _begin = Arrays.copyOfRange(baskets,0,begin);
            int[] begin_mid = Arrays.copyOfRange(baskets,begin,mid);
            int[] mid_end = Arrays.copyOfRange(baskets,mid,end+1);
            int[] end_ = Arrays.copyOfRange(baskets,end+1 ,baskets.length);

            System.arraycopy(_begin, 0, baskets, 0, _begin.length);
            System.arraycopy(mid_end, 0, baskets, begin, mid_end.length);
            System.arraycopy(begin_mid, 0, baskets, begin+ mid_end.length, begin_mid.length);
            System.arraycopy(end_, 0, baskets, end+1, end_.length);
        }

        for(int i = 0; i < baskets.length; i++){
            System.out.print(baskets[i] + " " );
        }
    }
}