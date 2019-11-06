package com.example.android.miwok;

public class Word {
    // Default translation.
    private String mDefaultTranslation;

    // Miwok translation.
    private String mMiwokTranslation;

    // Create new word object.
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    // Get the default translation.
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    // Get the Miwok translation.
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
}
