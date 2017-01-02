package com.example.android.miwok;

/**
 * Created by Arnab on 26/12/16.
 */

public class Word {
    private static final int NO_IMAGE_PROVIDED = -1;

    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;

    public Word(String DefaultTranslation, String MiwokTranslation, int audioResourceId){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mAudioResourceId = audioResourceId;
    }



    public Word(String DefaultTranslation, String MiwokTranslation,int ImgResId, int audioResourceId){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mImageResourceId = ImgResId;
        mAudioResourceId = audioResourceId;
    }



    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;

    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

    public boolean hasImage() {
               return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
