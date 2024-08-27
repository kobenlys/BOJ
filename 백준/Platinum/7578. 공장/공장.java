import java.io.*;
import java.util.*;

public class Main {
	public static int[] segment;

	// 세그먼트 트리 내 요소 업데이트 하기.
	public static void update(int start, int end, int idx, int id, int val) {

		if (id < start || id > end)
			return;

		if (start == end) {
			segment[idx] = val;
			return;
		}

		int mid = (start + end) / 2;
		update(start, mid, idx * 2, id, val);
		update(mid + 1, end, idx * 2 + 1, id, val);
		segment[idx] = segment[idx * 2] + segment[idx * 2 + 1];
	}

	// 나보다 역전된 ( 케이블이 겹친) 노드 찾기.
	public static long getInversion(int start, int end, int idx, int left, int right) {
		if (left > end || right < start)
			return 0;
		if (left <= start && right >= end)
			return segment[idx];

		int mid = (start + end) / 2;

		return getInversion(start, mid, idx * 2, left, right) + getInversion(mid + 1, end, idx * 2 + 1, left, right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1;
		StringTokenizer st2;

		int N = Integer.parseInt(br.readLine());
		long answer = 0;

		int[] arr1 = new int[N + 1];
		segment = new int[(N + 1) * 4]; // 세그먼트 트리 높이지정

		// 해시맵으로 기계번호마다 인덱스를 체크
		HashMap<Integer, Integer> map = new HashMap<>();

		st1 = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			arr1[i] = Integer.parseInt(st1.nextToken()); // a 배열
			map.put(Integer.parseInt(st2.nextToken()), i); // b 배열
		}

		for (int i = 1; i <= N; i++) {

			int start = map.get(arr1[i]);
			// start +1 ~ N까지 나보다 뒤에 있는 노드 -> 케이블이 겹쳤는지 확인
			// 가능한 이유 : arr1[i]를 인덱스 순으로 차근차근 입력하는데
			// 나보다 뒤에 있다면 내 이전 순번이 나보다 뒤에 있다느 뜻이니 케이블이 겹칠 수 밖에 없음
			answer += getInversion(1, N, 1, start + 1, N);

			// 이번순번 노드를 탐색했으니 방문처리를 해준다
			update(1, N, 1, start, 1);
		}

		System.out.print(answer);
	}
}