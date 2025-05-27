import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input = br.readLine();
        Stack<Character> stk = new Stack<>();
        char[] ppap = {'P', 'A', 'P', 'P'};

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {

            if(stk.size() >= 4){
                boolean isPPAP = true;
                list.clear();
                for (int j = 0; j < 4; j++) {
                    list.add(stk.pop());
                }

                for (int j = 0; j < 4; j++) {
                    if(list.get(j) != ppap[j]){
                        isPPAP = false;
                        break;
                    }
                }
                if(!isPPAP){
                    for (int j = 3; j >= 0 ; j--) {
                        stk.push(list.get(j));
                    }
                }else{
                    stk.push('P');
                }
            }

            stk.push(input.charAt(i));
        }

        list.clear();
        if(stk.size() >= 4){
            boolean isPPAP = true;
            for (int j = 0; j < 4; j++) {
                list.add(stk.pop());
            }

            for (int j = 0; j < 4; j++) {
                if(list.get(j) != ppap[j]){
                    isPPAP = false;
                    break;
                }
            }
            if(!isPPAP){
                for (char c : list) {
                    stk.push(c);
                }
            }else{
                stk.push('P');
            }
        }
        
        
        System.out.println(stk.size() == 1 && stk.peek() == 'P' ? "PPAP" : "NP");
    }
}