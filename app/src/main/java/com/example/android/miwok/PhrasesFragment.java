package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    private MediaPlayer mp;
    private AudioManager am;
    AudioManager.OnAudioFocusChangeListener afcListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //pause playback
                        mp.pause();
                        mp.seekTo(0);
                    } else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //resume playback
                        mp.start();
                    } else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //stop playback
                        releaseMediaPlayer();
                    }
                }
            };

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> phrases = new ArrayList<Word>();
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));
        phrases.add(new Word("phrases", "one"));

        WordAdapter adapter = new WordAdapter(getActivity(), phrases, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.numList);
        listView.setAdapter(adapter);

        //audio
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = phrases.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                releaseMediaPlayer();

                // Start the audio file
                int result = am.requestAudioFocus(afcListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mp = MediaPlayer.create(getActivity(), word.getAudioResourceID());
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

        return rootView;
    }

    private void releaseMediaPlayer()
    {
        if(mp != null)
        {
            mp.release();
            mp = null;
            am.abandonAudioFocus(afcListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}
