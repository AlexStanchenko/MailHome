package com.example.firsthomework;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final FragmentActivity activity;
    private final List<Integer> numbers;

    MyRecyclerAdapter(FragmentActivity activity, List<Integer> numbers) {
        this.activity = activity;
        this.numbers = numbers;
    }


    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(MyRecyclerAdapter.ViewHolder holder, final int position) {
        final int number = numbers.get(position);
        holder.numberView.setText(String.valueOf(number));
        if (number % 2 == 0) {
            holder.numberView.setTextColor(activity.getColor(R.color.colorEven));
        } else {
            holder.numberView.setTextColor(activity.getColor(R.color.colorOdd));
        }


        holder.numberView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SecondFragment secondFragment = SecondFragment.newInstance(String.valueOf(number));
                fragmentTransaction.replace(R.id.container, secondFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }


    @Override
    public int getItemCount() {
        return numbers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView numberView;

        ViewHolder(View view) {
            super(view);
            numberView = (TextView) view.findViewById(R.id.number);
        }
    }

}
