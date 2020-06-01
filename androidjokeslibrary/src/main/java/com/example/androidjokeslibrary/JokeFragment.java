package com.example.androidjokeslibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JokeFragment extends Fragment {
        private static TextView mTextView;

        public JokeFragment(){

        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_joke_fragment,container,false);

        Intent intent=getActivity().getIntent();

        String jokeString=intent.getStringExtra("joke");

        mTextView=root.findViewById(R.id.textView_first);
        if(jokeString!=null && jokeString.length()!=0){
            mTextView.setText(jokeString);
        }


            return root;
    }
}
