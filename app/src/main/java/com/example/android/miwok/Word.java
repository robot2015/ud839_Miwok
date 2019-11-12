package com.example.android.miwok;

public class Word {
    // Create constructor without image.
    public Word(String defaultTranslation, String miwokTranslation, int soundResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSoundResourceId = soundResourceId;
    }

    // Create constructor with image.
    public Word(String defaultTranslation, String miwokTranslation, int soundResourceId, int imageResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSoundResourceId = soundResourceId;
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
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public int getImageResourceId() {
        return mImageResourceId;
    }

    // Create indicator for image.
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    // Sound.
    private int mSoundResourceId;

    public int getSoundResourceId() {
        return mSoundResourceId;
    }

    // Debug test.

    // Add "toString" method for debugging.
    // On Windows, the keyboard shortcut is ALT + Insert.
    // https://classroom.udacity.com/courses/ud839/lessons/7863766808/concepts/81887887670923
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mSoundResourceId=" + mSoundResourceId +
                '}';
    }
}