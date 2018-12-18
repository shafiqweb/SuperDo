package com.example.shafiq.designproject2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.activities.BankAccountActivity;

public class CompletedFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.completed_fragment, container, false);
        Button buttonCompleted = view.findViewById(R.id.buttonCompleted);
        buttonCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BankAccountActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
