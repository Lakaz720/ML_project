package com.kimhyemi.bombelab.lakaz.monstar_lab_test;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Pomiring on 2017-07-28.
 */
public class CoslistFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coslist_fragment, container, false);
        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listView);
        listview.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_color_lens_black_24dp),
                "MAC", "FRUITY JUICY", 0xfcbdaa) ;
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_color_lens_black_24dp),
                "etude", "マンダリンオレンジ", 0xff5800) ;
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_color_lens_black_24dp),
                "資生堂", "シマリング クリーム アイカラー", 0x9c8e73) ;


        return view;
    }
}
