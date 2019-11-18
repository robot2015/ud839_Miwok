package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    // Create MediaPlayer object.
    private MediaPlayer mediaPlayer;

    // Handles audio focus when playing a sound file.
    private AudioManager audioManager;

    // Create audio focus change manager.
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    // Create OnCompletionListener to clear resources when finished.
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and set up the audio manager.
        audioManager = (AudioManager)
                getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red", "weṭeṭṭi", R.raw.color_red, R.drawable.color_red));
        words.add(new Word("green", "chokokki", R.raw.color_green, R.drawable.color_green));
        words.add(new Word("brown", "ṭakaakki", R.raw.color_brown, R.drawable.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.raw.color_gray, R.drawable.color_gray));
        words.add(new Word("black", "kululli", R.raw.color_black, R.drawable.color_black));
        words.add(new Word("white", "kelelli", R.raw.color_white, R.drawable.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));

        // Create WordAdapter.
        final WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        // Create ListView.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make ListView use the WordAdapter above.
        listView.setAdapter(adapter);

        // Create click listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Get the {@link Word} object at the given position the user clicked on.
                Log.v("ColorsFragment", "Current word: " + words.get(i));

                // Ensure MediaPlayer is clear before starting.
                releaseMediaPlayer();

                // Request audio focus.
                int result = audioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Play media.
                    mediaPlayer = MediaPlayer.create(getActivity(), words.get(i).getSoundResourceId());
                    mediaPlayer.start();

                    // Release MediaPlayer when finished.
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

        return rootView;
    }

    // Clean up MediaPlayer by releasing its resources.
    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    public void onStop() {
        releaseMediaPlayer();
        super.onStop();
    }
}