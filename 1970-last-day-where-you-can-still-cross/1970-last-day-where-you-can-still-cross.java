class Solution {

    public int[][] arr1;
    public int[][] vi;

    class Node {
        int x, y, max;

        public Node(int x, int y,int max) {
            this.x = x;
            this.y = y;
            this.max = max;
        }
    }

    public int bfs(List<Node> startNodes, int N, int M) {
        Queue<Node> qu = new ArrayDeque<>();
        int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };
        int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 };
        
        int answer = Integer.MAX_VALUE;
        for (Node sn : startNodes) {
            qu.offer(sn);
            vi[sn.y][sn.x] = 0;
        }

        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            if(nd.x == M - 1) {
                //System.out.println(3121 +" "+ vi[nd.y][nd.x]);
                answer = Math.min(answer, Math.max(arr1[nd.y][nd.x], nd.max));
                continue;
            }

            for (int i = 0; i < 8; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    continue;
                }

                if(arr1[ny][nx] != 0 && vi[ny][nx] > nd.max) {
                    //System.out.println(nd.y + " " + nd.x + " " + vi[nd.y][nd.x]);
                    vi[ny][nx] = nd.max;
                    //System.out.println(ny + " " + nx + " " + vi[ny][nx]);
                    qu.offer(new Node (nx, ny, Math.max(arr1[ny][nx], nd.max)));
                }
            }
        }
        return answer;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {

        arr1 = new int[row][col];
        vi = new int[row][col];
        List<Node> startNodes = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            int y = cells[i][0] - 1;
            int x = cells[i][1] - 1;
            vi[y][x] = Integer.MAX_VALUE;
            arr1[y][x] = i + 1;
            if (x == 0) {
                startNodes.add(new Node(x, y, i + 1));
            }
        }

        return bfs(startNodes, row, col) - 1;
    }
}