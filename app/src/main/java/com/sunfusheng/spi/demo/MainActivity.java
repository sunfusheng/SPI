package com.sunfusheng.spi.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test() {
        List<List<Integer>> lists = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        lists.add(list1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        lists.add(list2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(3);
        list3.add(4);
        lists.add(list3);

        List<Integer> list4 = new ArrayList<>();
        list4.add(1);
        lists.add(list4);

        int N = lists.size();
        int[] L = new int[N];
        L[0] = 1;
        for (int i = 1; i < N; i++) {
            L[i] = lists.get(i - 1).size() * L[i - 1];
        }
        int SUM = L[N - 1] * lists.get(N - 1).size();

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < SUM; i++) {
            List<Integer> list = new ArrayList<>();
            result.add(list);
        }

        for (int i = 0; i < N; i++) {
            int index = 0;
            while (index < SUM) {
                for (int j = 0; j < lists.get(i).size(); j++) {
                    for (int k = 0; k < L[i]; k++) {
                        List<Integer> list = result.get(index);
                        list.add(lists.get(i).get(j));
                        index++;
                    }
                }
            }
        }

        Log.d("sfs", "lists: " + lists.toString());
        for (List<Integer> list : result) {
            Log.d("sfs", list.toString());
        }
    }

}
