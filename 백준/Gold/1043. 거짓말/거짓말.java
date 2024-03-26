import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] parent, people;
    public static ArrayList<ArrayList<Integer>> party;
    
    // 부모노드 찾기.
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // 집합에 포함하기.
    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from != to) {
            parent[to] = from;
        }
    }
    
    // 거짓말을 할 수 있는 파티 개수 구하기
    public static int partyCount() {
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            boolean isOK = false;
            for (int j = 0; j < party.get(i).size(); j++) {
                for (int k = 0; k < people.length; k++) {
                    // 파티별 파티원이 진실을 알고 있는 인원과 접촉했는지 판단.
                    if (find(party.get(i).get(j)) == find(people[k])) {
                        // 파티원의 부모노드 와 진실을 알고있는 인원의 부모노드가 같다면
                        // 해당 파티는 제외, 두 인원이 접촉 한적있음.
                        isOK = true;
                    }
                }
                if (isOK) break;
            }
            if (!isOK) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N]; // 자식, 부모 노드 표시
        party = new ArrayList<>(); // 파티 별 포함 인원 저장
        
        // 초기값 설정
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            party.add(new ArrayList<>());
        }


        st = new StringTokenizer(br.readLine(), " ");
        int P = Integer.parseInt(st.nextToken());
        people = new int[P];
        // P == 0 이라면 완벽한 거짓말 가능!
        if (P != 0) {
            for (int i = 0; i < P; i++) {
                people[i] = Integer.parseInt(st.nextToken()) - 1;
            }
        } else {
            System.out.println(M);
            System.exit(0);
        }
        
        // 파티 별 포함인원 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            
            for (int j = 0; j < n; j++) {
                party.get(i).add(Integer.parseInt(st.nextToken()) - 1);
            }
        }
        // 파티 별 포함인원 끼리 union함수 진행.
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < party.get(i).size(); j++) {
                for (int k = j; k < party.get(i).size(); k++) {
                    union(party.get(i).get(j), party.get(i).get(k));
                }
            }
        }
        // 함수 실행 + 출력
        System.out.println(partyCount());
    }
}