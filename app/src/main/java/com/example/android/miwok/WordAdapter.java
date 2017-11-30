package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.Word;

import java.util.ArrayList;

/**
 * Created by Annie on 5/6/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private MediaPlayer mp;
    private int colorResID;

    public WordAdapter(Activity context, ArrayList<Word> wordList, int colorID)
    {
        super(context, 0, wordList);
        colorResID = colorID;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the object located at this position in the list
        Word currentWord = getItem(position);

        //set background color
        View text = listItemView.findViewById(R.id.text);
        int color = ContextCompat.getColor(getContext(), colorResID);
        text.setBackgroundColor(color);

        // Find the TextView in the list_item.xml layout with the top line of text
        TextView miwokTV = (TextView) listItemView.findViewById(R.id.miwok_tv);
        // Get the version name from the current object and
        // set this text on the name TextView
        miwokTV.setText(currentWord.getMiwokWord());

        // Find the TextView in the list_item.xml layout with the next line of text
        TextView englishTV = (TextView) listItemView.findViewById(R.id.translation_tv);
        // Get the version number from the current object and
        // set this text on the number TextView
        englishTV.setText(currentWord.getEnglishTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.iconPic);
        if(currentWord.hasImage())
        {
            // Get the image resource ID from the current AndroidFlavor object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getImageResourceID());
            iconView.setVisibility(View.VISIBLE);
        }
        else
        {
            iconView.setVisibility(View.GONE);
        }

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
