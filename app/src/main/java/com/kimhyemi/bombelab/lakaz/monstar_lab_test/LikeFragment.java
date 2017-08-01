package com.kimhyemi.bombelab.lakaz.monstar_lab_test;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pomiring on 2017-07-26.
 */
public class LikeFragment extends Fragment {

    final int ITEM_SIZE = 2;
    private int STORY_ID =100000;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("sns_info");
    DatabaseReference myRef = database.getReference();
    List<HomeItem> items = new ArrayList<>();
    HomeItem[] item = new HomeItem[ITEM_SIZE];
    View view;

    public void LikeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);



        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String storyId =String.valueOf(STORY_ID);
                System.out.println(storyId);
                //long countStory =dataSnapshot.child("sns_info").getChildrenCount();
                System.out.println("               "+dataSnapshot.getChildrenCount());
                System.out.println(dataSnapshot.child("sns_info").child(storyId).getChildrenCount());
                Object value;
                for(int i=0;i<dataSnapshot.child("sns_info").getChildrenCount();i++){
                    storyId = String.valueOf(STORY_ID+i);
                    System.out.println(storyId);
                    //value = dataSnapshot.child("sns_info").child(storyId).child("color_code").getValue();
                    //System.out.println(value);
                    //String colorCode = value.toString();
                    //value = dataSnapshot.child("sns_info").child(storyId).child("company").getValue();
                    //String company = value.toString();
                    value = dataSnapshot.child("sns_info").child(storyId).child("image_url").getValue();
                    String imageUrl = value.toString();
                    value = dataSnapshot.child("sns_info").child(storyId).child("story").getValue();
                    String story = value.toString();
                    //String value = dataSnapshot.getValue(String.class);
                    System.out.println(value);
                    //Log.d(TAG, "Value is: " + value);
                    item[i] = new HomeItem(imageUrl, story);
                    System.out.println(item[i].getImage());
                    items.add(item[i]);
                }
                RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.home_recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new RecyclerAdapter(getActivity(),items, R.layout.activity_main));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        return view;
    }


    public static String[] color_split(String color_code_set){

        String color_set[] = new String[5];


        return color_set;

    }
}
