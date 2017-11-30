/**
 * Created by Annie on 5/6/2017.
 */
package com.example.android.miwok;

public class Word {
    private String miwokWord;
    private String englishTranslation;
    private int imageResourceID = NO_IMAGE_PROVIDED;
    private int audioResourceID;

    private static final int NO_IMAGE_PROVIDED = 1;

    public Word(String miwokWord, String englishTranslation)
    {
        this.miwokWord = miwokWord;
        this.englishTranslation = englishTranslation;
    }

    public Word(String miwokWord, String englishTranslation, int imageResourceID, int audioResourceID)
    {
        this.imageResourceID = imageResourceID;
        this.miwokWord = miwokWord;
        this.englishTranslation = englishTranslation;
        this.audioResourceID = audioResourceID;
    }

    public String getMiwokWord()
    {
        return miwokWord;
    }

    public String getEnglishTranslation()
    {
        return englishTranslation;
    }

    public int getImageResourceID()
    {
        return imageResourceID;
    }

    public int getAudioResourceID()
    {
        return audioResourceID;
    }

    public boolean hasImage()
    {
        return imageResourceID != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{ \nMiwok: " + miwokWord + "\nEnglish: " + englishTranslation + "\nImageRes: " + imageResourceID + "\nAudioRes: " + audioResourceID + "}";
    }
}
