import java.io.*;
import java.util.*;

public class Main {

    public static class GoStack extends Stack<Long> {

        public static StringBuilder sb = new StringBuilder();
        public static int LIMIT_NUMBER = 1_000_000_000;

        public void runGoStack(List<String> commands, long num) {
            this.clear();
            this.add(num);
            boolean isError = false;
            for (String command : commands) {
                switch (command) {
                    case "POP":
                        isError = popp();
                        break;
                    case "INV":
                        isError = inv();
                        break;
                    case "DUP":
                        isError = dup();
                        break;
                    case "SWP":
                        isError = swp();
                        break;
                    case "ADD":
                        isError = add();
                        break;
                    case "SUB":
                        isError = sub();
                        break;
                    case "MUL":
                        isError = mul();
                        break;
                    case "DIV":
                        isError = div();
                        break;
                    case "MOD":
                        isError = mod();
                        break;
                    default:
                        long pushNum = Integer.parseInt(command.split(" ")[1]);
                        this.push(pushNum);
                        break;
                }
                if (isError) {
                    sb.append("ERROR").append("\n");
                    return;
                }
            }
            if (this.size() != 1) {
                sb.append("ERROR").append("\n");
            } else {
                sb.append(this.pop()).append("\n");
            }
        }

        private boolean popp() {
            if (this.size() == 0) {
                return true;
            }
            this.pop();
            return false;
        }

        private boolean dup() {
            if (this.size() == 0) {
                return true;
            }
            this.push(this.peek());
            return false;
        }

        private boolean inv() {
            if (this.size() == 0) {
                return true;
            }
            this.push(this.pop() * -1);
            return false;
        }

        private boolean swp() {
            if (this.size() < 2) {
                return true;
            }
            long num1 = this.pop();
            long num2 = this.pop();
            this.push(num1);
            this.push(num2);
            return false;
        }

        private boolean add() {
            if (this.size() < 2) {
                return true;
            }
            long num1 = this.pop();
            long num2 = this.pop();
            if (isNotAllowedNumber(num1 + num2)) {
                return true;
            }
            this.push(num1 + num2);
            return false;
        }

        private boolean sub() {
            if (this.size() < 2) {
                return true;
            }
            long num1 = this.pop();
            long num2 = this.pop();
            if (isNotAllowedNumber(num1 - num2)) {
                return true;
            }
            this.push(num2 - num1);
            return false;
        }

        private boolean mul() {
            if (this.size() < 2) {
                return true;
            }
            long num1 = this.pop();
            long num2 = this.pop();
            if (isNotAllowedNumber(num2 * num1)) {
                return true;
            }
            this.push(num1 * num2);
            return false;
        }

        private boolean div() {
            if (this.size() < 2) {
                return true;
            }
            long num1 = this.pop();
            long num2 = this.pop();
            if (num1 == 0) {
                return true;
            }
            this.push(num2 / num1);
            return false;
        }

        private boolean mod() {
            if (this.size() < 2) {
                return true;
            }
            long num1 = this.pop();
            long num2 = this.pop();
            if (num1 == 0) {
                return true;
            }
            this.push(num2 % num1);
            return false;
        }

        private boolean isNotAllowedNumber(long num) {
            return Math.abs(num) > LIMIT_NUMBER;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {

            List<String> commands = new ArrayList<>();
            GoStack gos = new GoStack();

            while (true) {
                String command = br.readLine();
                if ("QUIT".equals(command)) {
                    System.out.println(gos.sb.toString());
                    System.exit(0);
                }
                if ("END".equals(command)) {
                    break;
                }
                commands.add(command);
            }

            int seq = Integer.parseInt(br.readLine());
            for (int i = 0; i < seq; i++) {
                long num = Integer.parseInt(br.readLine());
                gos.runGoStack(commands, num);
            }
            gos.sb.append("\n");
            br.readLine();
        }
    }
}