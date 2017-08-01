package com.kimhyemi.bombelab.lakaz.monstar_lab_test;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Pomiring on 2017-07-25.
 */
public class HomeFragment extends Fragment {

    int ITEM_SIZE = 2;
    private int STORY_ID =100000;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("sns_info");
    DatabaseReference myRef = database.getReference();

    View view;

    public void HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                                       ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);



        /*myRef.addChildEventListener(new ChildEventListener() {
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
        });*/

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Iterator<DataSnapshot> storyIdIter = dataSnapshot.child("sns_info").getChildren().iterator();
                ITEM_SIZE= (int) dataSnapshot.child("sns_info").getChildrenCount();
                List<HomeItem> items = new ArrayList<>();
                HomeItem[] item = new HomeItem[ITEM_SIZE];

                System.out.println(storyIdIter);

                Object value;
                for(int i=0;i<dataSnapshot.child("sns_info").getChildrenCount();i++){
                    String storyId = String.valueOf(storyIdIter.next().getKey());
                    System.out.println(storyId);
                    //value = dataSnapshot.child("sns_info").child(storyId).child("color_code").getValue();
                    //System.out.println(value);
                    //String colorCode = value.toString();
                    //value = dataSnapshot.child("sns_info").child(storyId).child("company").getValue();
                    //String company = value.toString();
                    value = dataSnapshot.child("sns_info").child(storyId).child("image_url").getValue();
                    String imageUrl = value.toString();
                    value = dataSnapshot.child("sns_info").child(storyId).child("story").getValue();
                    String story = "";
                    if(story!=null)story = value.toString();
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

        /*color data example "015241;F52654;015241;F52654;015241;F52654;"*/

        String color_set[];
        color_set = color_code_set.split(";");
        return color_set;

    }

}
