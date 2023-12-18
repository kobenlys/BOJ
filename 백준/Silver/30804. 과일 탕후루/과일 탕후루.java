package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> box = new ArrayList<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        int[] arr1 = new int[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0; // 투 포인터 초기화
        int cnt = 0, max = 0;
        int temp = 0;

        while (right < N) { // 투 포인터 알고리즘

            if (box.size() < 2) { // step 1. arr1배열에 2가지 과일 담기
                if (box.size() == 1) {
                    if (box.get(0) != arr1[right]) {
                        // 과일이 중복적으로 들어 올 수도 있음, 각 다른 과일 2가지 저장
                        box.add(arr1[right]);
                        temp = arr1[right];
                        left = right;
                    }
                } else {
                    box.add(arr1[right]);
                }
                cnt++;
                right++;

            } else if (box.size() == 2) { // 서로 다른 과일이 나올때 까지 카운팅
                // 2가지 과일과 현재 과일이 중복된다면 cnt++, 아니라면 right, cnt, box 초기화
                if (box.get(0) == arr1[right]) {
                    // 예제 1 2 1 1 3 3 3 3 wrong = 4 , answer = 6
                    // 위 예제를 만족하기 위해서는 마지막으로 값이 바뀌었을때를 left에 표기
                    if (box.get(0) != temp) {
                        //temp 에 저번 과일을 저장 하고 현제 과일이랑 다르다면 left에 표기
                        temp = box.get(0);
                        left = right;
                    }
                    cnt++;
                    right++;
                } else if (box.get(1) == arr1[right]) {
                    if (box.get(1) != temp) {
                        temp = box.get(1);
                        left = right;
                    }
                    cnt++;
                    right++;
                } else {
                    // 서로 다른 과일이 3개라면 다시 카운팅 위해
                    right = left;
                    box.clear();
                    cnt = 0;
                }
            }
            // 가장 큰 cnt 값 구하기
            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
