package com.kimhyemi.bombelab.lakaz.monstar_lab_test;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by Pomiring on 2017-07-25.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<HomeItem> items;
    int item_layout;
    private Target loadtarget;

    public RecyclerAdapter(Context context, List<HomeItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HomeItem item = items.get(position);
        //Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
        System.out.println(item.getImage());
        Picasso.with(context).load(item.getImage()).into(holder.image);
        //holder.image.setImageResource();
        //holder.image.setImageResource(item.getImage());
        //holder.image.setBackground(drawable);
        holder.image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.title.setText(item.getStory());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getStory(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardview;
        ImageButton heart;
        ImageButton paret;
        boolean heart_check=true;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            heart = (ImageButton)itemView.findViewById(R.id.heart);
            heart.setBackgroundDrawable(null);
            paret = (ImageButton)itemView.findViewById(R.id.paret);
            paret.setBackgroundDrawable(null);

            heart.setOnClickListener(new View.OnClickListener(){
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                public void onClick(View v){

                    if(heart_check){
                        heart_check=false;
                        heart.setColorFilter(Color.GREEN);
                    }
                    else{
                        heart_check=true;
                        heart.setColorFilter(Color.BLACK);
                    }
                }
            });

            paret.setOnClickListener(new View.OnClickListener(){
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                public void onClick(View v){

                }
            });


        }/*ViewHolder end*/
    }/*ViewHolder class end*/


}/*RecyclerAdapter end*/
