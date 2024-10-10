import java.io.*;
import java.util.*;

public class Main {

	private static class Trie {

		private Node root = new Node();

		private class Node {
			Map<Character, Node> map = new HashMap<>();
			boolean isEnd = false;
		}

		private void insert(String str) {

			Node parent = root;

			for (int i = 0; i < str.length(); i++) {
				parent = parent.map.computeIfAbsent(str.charAt(i), e -> new Node());
			}
			parent.isEnd = true;
		}

		private boolean search(String str) {
			StringBuilder sb = new StringBuilder();
			Node parent = root;

			for (int i = 0; i < str.length(); i++) {
				
				if(Objects.isNull(parent)) return true;
				
				parent = parent.map.getOrDefault(str.charAt(i), null);
				sb.append(str.charAt(i));
				
				if(!Objects.isNull(parent) && parent.isEnd) {
					if(str.equals(sb.toString())) return true;
					return false;
				}
			}
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {

			int N = Integer.parseInt(br.readLine());
			String answer = "YES";

			Trie trie = new Trie();
			String[] arr1 = new String[N];

			for (int i = 0; i < N; i++) {
				String tmp = br.readLine();
				arr1[i] = tmp;
				trie.insert(tmp);
			}

			for (int i = 0; i < N; i++) {

				boolean isExist = trie.search(arr1[i]);

				if (!isExist) {
					answer = "NO";
					break;
				}
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb);
	}
}