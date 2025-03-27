import java.util.*;

public class Main {
    public static int min(String n){
        return Integer.parseInt(n.replaceAll("6","5"));
    }
    public static int max(String n){
        return Integer.parseInt(n.replaceAll("5","6"));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.next();
        String m = scanner.next();
        int min,max;
        min = min(n) + min(m);
        max = max(n) + max(m);
        System.out.println(min +" "+max);
    }
}