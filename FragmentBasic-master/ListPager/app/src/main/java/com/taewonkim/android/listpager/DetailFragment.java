package com.taewonkim.android.listpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TextView title;
    TextView contents;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        title = (TextView) view.findViewById(R.id.title);
        contents = (TextView) view.findViewById(R.id.contents);

        return view;
    }

    public void setData(){
        MainActivity main = (MainActivity) getActivity();
        main.
    }

}
