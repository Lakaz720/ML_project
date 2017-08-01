package com.kimhyemi.bombelab.lakaz.monstar_lab_test;

/**
 * Created by Pomiring on 2017-08-01.
 */

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private int colorCode;

    public void setIcon(Drawable icon) {iconDrawable = icon ;}
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setColorCode(int color){colorCode=color;}

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public int getColor(){return  this.colorCode;}
}
