package com.example.android.miwok;

public class Word {
    // Create constructor without image.
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    // Create constructor with image.
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    // Default translation.
    private String mDefaultTranslation;

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    // Miwok translation.
    private String mMiwokTranslation;

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    // Image.
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public int getImageResourceId() {
        return mImageResourceId;
    }

    // Create indicator for image.
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
