import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();

        set.add("swi");

        for (int i = 0; i < N; i++) {
            String human = br.readLine();
            if(human.equals("dongho")){
                System.out.println("dongho");
                return;
            }
            set.add(human);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String human = br.readLine();
            set.remove(human);
        }
        

        List<String> list = new ArrayList<>(set);

        Collections.sort(list);

        if (list.size() == 1) {
            System.out.println(list.get(0));
            return;
        }

        set.remove("swi");

        if (list.contains("bumin")) {
            System.out.println("bumin");
        }else if (list.contains("cake")) {
            System.out.println("cake");
        }else if (list.contains("lawyer")) {
            System.out.println("lawyer");
        }else{
            System.out.println(list.get(0));
        }

    }
}