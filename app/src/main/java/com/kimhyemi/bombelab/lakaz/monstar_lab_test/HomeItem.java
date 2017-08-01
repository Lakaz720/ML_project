package com.kimhyemi.bombelab.lakaz.monstar_lab_test;

import java.net.URL;

/**
 * Created by Pomiring on 2017-07-26.
 */
public class HomeItem {

    //int image;
    public String image;
    public String story;
    /*public String color_set;
    public String image_url;
    public String sns_code;
    public String user_id;
    public String company;*/

    public HomeItem(){}

    public String getImage() {
        return this.image;
    }
    public String getStory() {return this.story;}
    /*public String getColor_set(){return color_set;}
    public String getImage_url(){return image_url;}
    public String getSns_code(){return sns_code;}
    public String getUser_id(){return user_id;}
    public String getCompany(){return company;}*/
    //String getStory(){return this.story;}
    /*String[] getColor(){return this.color_set;}
    URL getImage_url(){return this.image_url;}
    String getSns_code(){return this.sns_code;}
    String getUser_id(){return this.user_id;}*/

    /*HomeItem(int image, String title, String[] color_set,
             URL image_url, String sns_code, String user_id) {*/
    /*public HomeItem(String image, String story, String color_set,String image_url
            , String sns_code, String user_id, String company) {*/

    public HomeItem(String image, String story) {
        this.image = image;
        this.story = story;
        /*this.color_set = color_set;
        this.image_url = image_url;
        this.sns_code = sns_code;
        this.user_id = user_id;
        this.company = company;*/
        /*this.color_set = color_set;
        this.image_url = image_url;
        this.sns_code = sns_code;
        this.user_id = user_id;*/
    }
}
