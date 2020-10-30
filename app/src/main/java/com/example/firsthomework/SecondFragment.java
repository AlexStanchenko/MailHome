package com.example.firsthomework;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;


public class SecondFragment extends Fragment implements OnBackPressedListener {

    private String number;
    private static final String NUMBER_KEY = "numberKey";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        TextView text = (TextView) view.findViewById(R.id.bigNumber);
        text.setText(number);
        if (Integer.parseInt(number) % 2 == 0) {
            text.setTextColor(getActivity().getColor(R.color.colorEven));
        } else {
            text.setTextColor(getActivity().getColor(R.color.colorOdd));
        }
        return view;
    }

    public static SecondFragment newInstance(String someString) {
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.number = someString;
        return secondFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            number = savedInstanceState.getString(NUMBER_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(NUMBER_KEY, number);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }
}