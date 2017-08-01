package com.kimhyemi.bombelab.lakaz.monstar_lab_test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Pomiring on 2017-07-27.
 */
public class AddFragment extends Fragment {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    ImageView imageView;
    ImageButton colorView_1;
    ImageButton colorView_2;
    ImageButton colorView_3;
    ImageButton colorView_4;
    ImageButton colorView_5;
    ImageButton colorView_6;
    ImageButton upload;
    EditText story;
    Uri filepath;
    String colorCode[]=new String [6];

    public void AddFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                                       ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.camera_fragment, container, false);
        imageView = (ImageView)view.findViewById(R.id.imageView);
        colorView_1 = (ImageButton)view.findViewById(R.id.color_1);
        colorView_2 = (ImageButton)view.findViewById(R.id.color_2);
        colorView_3 = (ImageButton)view.findViewById(R.id.color_3);
        colorView_4 = (ImageButton)view.findViewById(R.id.color_4);
        colorView_5 = (ImageButton)view.findViewById(R.id.color_5);
        colorView_6 = (ImageButton)view.findViewById(R.id.color_6);
        upload = (ImageButton)view.findViewById(R.id.upload);
        story = (EditText)view.findViewById(R.id.editText);
        //if(checkPermission()){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        //}


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {

                imageView.setImageURI(data.getData());
                filepath=data.getData();
                Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                //Palette palette = Palette.from(image).generate();

                Palette.from(image).generate(new Palette.PaletteAsyncListener() {
                    @Override public void onGenerated(Palette palette) {

                        if(palette==null) return;
                        Palette.Swatch color_1 = palette.getVibrantSwatch();
                        Palette.Swatch color_2 = palette.getDarkVibrantSwatch();
                        Palette.Swatch color_3 = palette.getLightVibrantSwatch();
                        Palette.Swatch color_4 = palette.getMutedSwatch();
                        Palette.Swatch color_5 = palette.getDarkMutedSwatch();
                        Palette.Swatch color_6 = palette.getLightMutedSwatch();

                        if(color_1!=null){
                            colorView_1.setBackgroundColor(color_1.getRgb());
                            colorCode[0]= String.valueOf(color_1.getRgb());

                        }
                        if(color_2!=null){
                            colorView_2.setBackgroundColor(color_2.getRgb());
                            colorCode[1]= String.valueOf(color_2.getRgb());

                        }
                        if(color_3!=null){
                            colorView_3.setBackgroundColor(color_3.getRgb());
                            colorCode[2]= String.valueOf(color_3.getRgb());

                        }
                        if(color_4!=null){
                            colorView_4.setBackgroundColor(color_4.getRgb());
                            colorCode[3]= String.valueOf(color_4.getRgb());

                        }
                        if(color_5!=null){
                            colorView_5.setBackgroundColor(color_5.getRgb());
                            colorCode[4]= String.valueOf(color_5.getRgb());

                        }
                        if(color_6!=null){
                            colorView_6.setBackgroundColor(color_6.getRgb());
                            colorCode[5]= String.valueOf(color_6.getRgb());

                        }
                    }/*onGenerated end*/
                });

                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uploadInfo(uploadFile(filepath));
                    }
                });

            }/*if (RESULT_OK) end*/
        }/*if(ACTIVITY_REQUEST_CODE) end*/

    }

    private String uploadFile(Uri filePath) {

        final String[] image_url = {"null"};

        if(filePath == null){
            /*null check*/
            System.out.println(filePath);
            System.out.println(imageView.getId());
        }
        if (filePath != null) {
            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            /*Make a file name*/
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            final String filename = formatter.format(now) + ".png";

            /*setting file address*/

            /*StorageReference storageRef = storage.getReferenceFromUrl("gs://aaaa-a1eab.appspot.com")
                    .child("images/"+username+"/"+filename);*/
            final StorageReference storageRef = storage.getReferenceFromUrl("gs://aaaa-a1eab.appspot.com")
                    .child("images/"+filename);

            /*upload success or fail message*/
            storageRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Upload OK!");
                            imageView.setImageResource(R.drawable.nothing);
                            colorView_1.setImageResource(R.drawable.nothing);
                            colorView_2.setImageResource(R.drawable.nothing);
                            colorView_3.setImageResource(R.drawable.nothing);
                            colorView_4.setImageResource(R.drawable.nothing);
                            colorView_5.setImageResource(R.drawable.nothing);
                            colorView_6.setImageResource(R.drawable.nothing);
                            //image_url[0] = String.valueOf(storageRef.getDownloadUrl());
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            /*if failure message*/
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            /*if Processing program message*/
                        }
                    });

            /*upload image address*/
            //image_url = "gs://aaaa-a1eab.appspot.com/"+"image"+filename;

            //image_url=storageRef.getDownloadUrl();
        }

        uploadInfo(image_url[0]);
        return image_url[0];

    }/*uploadFile end*/


    private void uploadInfo(String imageUrl){


        Random i = new Random();
        int a = i.nextInt(50000);
        a= a+100000;
        String temp = String.valueOf(story.getText());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        /*HomeItem[] item = new HomeItem[1];
        item[0] = new HomeItem("address", "맛있네요! 정말!　大好き！！！");*/
        mDatabase.child("sns_info")
                .child(Integer.toString(a)).child("image_url")
                .setValue("https://firebasestorage.googleapis.com/v0/b/aaaa-a1eab.appspot.com/o/images%2F20170717_0118.png?alt=media&token=39fd54fa-4f44-410e-bd6f-2aaf6920a9f8");
        mDatabase.child("sns_info").child(Integer.toString(a)).child("story").setValue(temp);
    }


}
