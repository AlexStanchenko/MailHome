package com.example.firsthomework;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    private List<Integer> numbers;
    private static final String LAST_NUMBER_KEY = "lastKey";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            setInitialData(100);
        } else {
            setInitialData(savedInstanceState.getInt(LAST_NUMBER_KEY));
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.first_fragment, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        final MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(getActivity(), numbers);
        GridLayoutManager layoutManager = null;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getActivity(), 3);
        } else {
            layoutManager = new GridLayoutManager(getActivity(), 4);
        }

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myRecyclerAdapter);
        Button button = (Button) view.findViewById(R.id.fragmentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.add(numbers.size() + 1);
                myRecyclerAdapter.notifyItemChanged(numbers.size());
            }
        });
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(LAST_NUMBER_KEY, numbers.size());
        super.onSaveInstanceState(outState);
    }

    private void setInitialData(int lastNumber) {
        numbers = new ArrayList<Integer>();
        for (int i = 1; i <= lastNumber; i++) {
            numbers.add(i);
        }
    }
}
