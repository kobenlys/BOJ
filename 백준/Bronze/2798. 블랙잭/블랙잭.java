import java.util.*;

public class Main {
   public static int[] arr1;
   public static int limit , max;

    public static void algorithm(int start, int card, int sum) { // 구현

        if(sum > limit){ // limit 보다 sum이 크다면 return
            return;
        }

        if(card == 3){ // limit이 sum보다 작으면서 card를 3장만 뽑았을 경우
            max = Math.max(max, sum);
        }else if(card > 3){ // card가 3장 이상일 경우 return;
            return;
        }

        if(start == arr1.length){ // 배열을 다 돌았다면 return = 탈출
            return;
        }
        //카드를 사용하는경우
        algorithm(start+1,card+1, sum+arr1[start]);
        //카드를 사용하지 않는 경우 (조건 : limit보다 작고, card 가 3보다 크고, 배열탐색 끝난경우)
        algorithm(start+1,card,sum);
    }


    public static void main(String[] args) { //조건 입력
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        limit = sc.nextInt();

        arr1 = new int[n];
        max =0;
        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }

        algorithm(0,0,0);
        System.out.println(max);

    }
}
