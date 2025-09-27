import java.io.*;
import java.util.*;

public class Main {

    public static class Album {

        String name;
        TreeSet<String> photos = new TreeSet<>();
        TreeMap<String, Album> tree = new TreeMap<>();

        public Album(String name) {
            this.name = name;
        }

        public boolean isInAlbum(String param) {
            return tree.containsKey(param);
        }

        public boolean isInPhoto(String param) {
            return photos.contains(param);
        }

        public Album search(String param) {
            return tree.get(param);
        }

        public void makeAlbum(String name) {
            tree.put(name, new Album(name));
        }

        public void makePhoto(String name) {
            photos.add(name);
        }

        public int[] findChild() {
            Queue<Album> qu = new ArrayDeque<>();
            int albumCnt = tree.size();
            int photoCnt = photos.size();

            for (Album album : tree.values()) {
                qu.offer(album);
            }

            while (!qu.isEmpty()) {
                Album nowAlbum = qu.poll();
                albumCnt += nowAlbum.tree.size();
                photoCnt += nowAlbum.photos.size();
                for (Album album : nowAlbum.tree.values()) {
                    qu.offer(album);
                }
            }
            return new int[]{albumCnt + 1, photoCnt};
        }
    }

    public static class AlbumManager {

        Album album;
        Stack<Album> traceCache;
        StringBuilder sb;
        int[] res = new int[2];

        public AlbumManager() {
            this.album = new Album("album");
            this.traceCache = new Stack<>();
            this.sb = new StringBuilder();
        }

        private void mk1000(String param) {
            if (this.album.isInAlbum(param)) {
                sb.append("duplicated album name").append("\n");
            } else {
                this.album.makeAlbum(param);
            }
        }

        private void rm1000(String param) {
            res[0] = res[1] = 0;
            if (album.tree.containsKey(param)) {
                res = this.album.tree.get(param).findChild();
                album.tree.remove(param);
            }
            sb.append(res[0]).append(" ").append(res[1]).append("\n");
        }

        private void rm2000() {
            res[0] = res[1] = 0;
            if (!album.tree.isEmpty()) {
                res = this.album.tree.get(this.album.tree.firstKey()).findChild();
                album.tree.remove(this.album.tree.firstKey());
            }
            sb.append(res[0]).append(" ").append(res[1]).append("\n");
        }

        private void rm3000() {
            res[0] = res[1] = 0;
            Iterator<Album> it = album.tree.values().iterator();
            while (it.hasNext()) {
                int[] tmpRes = it.next().findChild();
                res[0] += tmpRes[0];
                res[1] += tmpRes[1];
                it.remove();
            }
            sb.append(res[0]).append(" ").append(res[1]).append("\n");
        }

        private void rm4000() {
            res[0] = res[1] = 0;
            if (!album.tree.isEmpty()) {
                res = this.album.tree.get(this.album.tree.lastKey()).findChild();
                album.tree.remove(this.album.tree.lastKey());
            }
            sb.append(res[0]).append(" ").append(res[1]).append("\n");
        }

        private void insert(String photo) {
            if (album.isInPhoto(photo)) {
                sb.append("duplicated photo name").append("\n");
            } else {
                album.makePhoto(photo);
            }
        }

        private void del1000(String param) {
            if (this.album.isInPhoto(param)) {
                sb.append("1");
                this.album.photos.remove(param);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }

        private void del2000() {
            if (!this.album.photos.isEmpty()) {
                sb.append("1");
                this.album.photos.remove(this.album.photos.first());
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }

        private void del3000() {
            if (!this.album.photos.isEmpty()) {
                sb.append(this.album.photos.size());
                this.album.photos.clear();
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }

        private void del4000() {
            if (!this.album.photos.isEmpty()) {
                sb.append("1");
                this.album.photos.remove(this.album.photos.last());
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }


        private void ca1000(String param) {

            if (this.album.isInAlbum(param)) {
                traceCache.push(this.album);
                this.album = album.search(param);
                sb.append(param).append("\n");
            } else {
                sb.append(this.album.name).append("\n");
            }
        }

        private void ca2000() {
            if (traceCache.isEmpty()) {
                sb.append("album").append("\n");
            } else {
                this.album = traceCache.pop();
                sb.append(album.name).append("\n");
            }
        }

        private void ca3000() {

            while (!traceCache.isEmpty()) {
                Album tmp = traceCache.pop();
                if (traceCache.isEmpty()) {
                    this.album = tmp;
                }
            }
            sb.append("album").append("\n");
        }

        public void ctrl(String command, String param) {

            switch (command) {
                case "mkalb":
                    mk1000(param);
                    break;
                case "rmalb":
                    switch (param) {
                        case "-1":
                            rm2000();
                            break;
                        case "0":
                            rm3000();
                            break;
                        case "1":
                            rm4000();
                            break;
                        default:
                            rm1000(param);
                    }
                    break;
                case "insert":
                    insert(param);
                    break;
                case "delete":
                    switch (param) {
                        case "-1":
                            del2000();
                            break;
                        case "0":
                            del3000();
                            break;
                        case "1":
                            del4000();
                            break;
                        default:
                            del1000(param);
                    }
                    break;
                case "ca":
                    switch (param) {
                        case "..":
                            ca2000();
                            break;
                        case "/":
                            ca3000();
                            break;
                        default:
                            ca1000(param);
                    }
            }
        }

        public void printAnswer() {
            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        AlbumManager am = new AlbumManager();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            String param = st.nextToken();
            am.ctrl(command, param);
        }
        am.printAnswer();
    }
}