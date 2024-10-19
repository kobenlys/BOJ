import java.io.*;
import java.util.*;

public class Main {
    public static Node root = new Node();
    public static StringBuilder sb = new StringBuilder();

    public static class Node{
        Map<String, Node> map = new HashMap<>();
    }

    public static void insert(String path){

        Node parent = root;
        StringTokenizer st = new StringTokenizer(path, "\\");

        while(st.hasMoreTokens()){
            parent = parent.map.computeIfAbsent(st.nextToken(), e -> new Node());
        }
    }

    public static void printTrie(String key, String space, Node parent){

        parent = parent.map.getOrDefault(key, null);

        if(Objects.isNull(parent)) return;

        List<String> list = new ArrayList<>(parent.map.keySet());
        Collections.sort(list);

        for (String tmpKey : list) {
            sb.append(space).append(tmpKey).append("\n");
            printTrie(tmpKey, space + " ", parent);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String path = br.readLine();
            insert(path);
        }

        List<String> list = new ArrayList<>(root.map.keySet());
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append("\n");
            printTrie(list.get(i), " ", root);

        }

        System.out.println(sb);
    }
}