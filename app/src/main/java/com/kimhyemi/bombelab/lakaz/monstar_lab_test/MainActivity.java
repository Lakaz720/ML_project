package com.kimhyemi.bombelab.lakaz.monstar_lab_test;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.*;



public class MainActivity extends ActionBarActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //private Fragment fr= new HomeFragment();
    private Fragment fr=new HomeFragment();
    private FragmentManager fm=getSupportFragmentManager();
    private FirebaseAnalytics mFirebaseAnalytics;

    private boolean auth_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        fm.beginTransaction().replace(R.id.fragment_page, fr).commit();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                System.out.println(user);
                if (user != null) {
                    auth_check=true;
                    //System.out.println(auth_check);
                } else {
                    auth_check=false;
                }System.out.println(auth_check);
            }
        };

        Toolbar abToolbar = (Toolbar) findViewById(R.id.above_toolbar);
        setSupportActionBar(abToolbar);

        BottomNavigationView bottomNavigationView
                = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_bo_home:
                        fr = new HomeFragment();
                        break;
                    case R.id.nav_bo_add:
                        fr = new AddFragment();
                        break;
                    case R.id.nav_bo_like:
                        if(auth_check) fr = new LikeFragment();
                        else fr=new LoginFragment();
                        break;
                    case R.id.nav_bo_coslist:
                        fr = new CoslistFragment();
                        break;
                }
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_page, fr).commit();
                return true;
            }
        });
    }/*onCreate end*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_above_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.user:
                if(auth_check) fr=new UserFragment();
                else fr=new LoginFragment();
                break;
            case R.id.camera:
                fr= new CameraFragment();
                break;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_page, fr).commit();
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                System.out.println(user);
                if (user != null) {
                    auth_check=true;
                    //System.out.println(auth_check);
                } else {
                    auth_check=false;
                }System.out.println(auth_check);
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    System.out.println(user);
                    if (user != null) {
                        auth_check=true;
                        //System.out.println(auth_check);
                    } else {
                        auth_check=false;
                    }System.out.println(auth_check);
                }
            };
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
