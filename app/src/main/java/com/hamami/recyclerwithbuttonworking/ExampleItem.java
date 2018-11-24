package com.hamami.recyclerwithbuttonworking;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ExampleItem(int imageResource, String Text1, String Text2) {
        this.mImageResource = imageResource;
        this.mText1 = Text1;
        this.mText2 = Text2;
    }
    public void changeText1(String text){
        mText1 = text;
    }
    public void changeText2(String text){
        mText2 = text;
    }
    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
