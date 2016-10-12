package com.taewonkim.android.listpager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    ListView listView;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        CustomAdapter adapter = new CustomAdapter(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity main = (MainActivity) getActivity();
                main.position = position;

                main.pager.setCurrentItem(1,true);
                Log.i("TEST","======================="+main.position);
            }
        });

        return view;
    }

}

class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public CustomAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MainActivity.database.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.database.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = inflater.inflate(R.layout.list_item, null);

        TextView id = (TextView) convertView.findViewById(R.id.item_id);
        id.setText(position + "");

        TextView title = (TextView) convertView.findViewById(R.id.item_title);
        title.setText(MainActivity.database.get(position).title);

        return convertView;
    }
}