import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int h=sc.nextInt();
        int m=sc.nextInt();
        int s=sc.nextInt();
        int n=sc.nextInt();
        int sum=s+n;
        m+=sum/60;
        sum%=60;
        h+=m/60;
        m%=60;
        h%=24;
        System.out.format("%d %d %d",h,m,sum);
    }
}