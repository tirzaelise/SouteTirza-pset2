package com.example.tirza.soutetirza_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class placeholderActivity extends AppCompatActivity {
    // Global variables
    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);

        // Get story that was selected in the dropdown menu
        Bundle extras = getIntent().getExtras();
        String chosenStory = extras.getString("selectedStory", "none");
        InputStream inputStory = stringToStory(chosenStory);
        story = new Story(inputStory);
        replaceType(story);
    }

    // Get word to fill in through user input
    public void giveWord(View view) {
        Intent goToStory = new Intent(this, storyActivity.class);
        EditText editText = (EditText) findViewById(R.id.givenWord);
        String givenWord = editText.getText().toString();

        // Check if a word was given place it in the story
        if (!(givenWord.length() == 0)) {
            story.fillInPlaceholder(givenWord);
            int remainingCount = story.getPlaceholderRemainingCount();
            replaceCount(remainingCount);
            replaceType(story);
            // If it was the last word, send the story to the next activity and go there
            if (remainingCount == 0) {
                String finishedStory = story.toString();
                goToStory.putExtra("finishedStory", finishedStory);
                startActivity(goToStory);
                finish();
            }
        // Give a pop up if there was no user input
        } else {
            Toast noWordError = Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT);
            noWordError.show();
        }
    }

    // Replace the number in the amount of words remaining text
    public void replaceCount(int remainingCount) {
        TextView wordsRemaining = (TextView) findViewById(R.id.wordsRemaining);
        String newWordsRemaining;

        if (remainingCount == 1) {
            newWordsRemaining = "There is " + remainingCount + " word remaining.";
        } else {
            newWordsRemaining = "There are " + remainingCount + " words remaining.";
        }
        wordsRemaining.setText(newWordsRemaining);
    }

    // Replace the type of word that is given as the hint
    public void replaceType(Story story) {
        EditText editText = (EditText) findViewById(R.id.givenWord);
        String hint = story.getNextPlaceholder();
        editText.setText("");
        editText.setHint(hint);
    }

    // Convert the string that was given as chosen story to an input stream
    public InputStream stringToStory(String chosenStory) {
        InputStream is = null;

        switch (chosenStory) {
            case "Clothes":
                try {
                    is = getAssets().open("madlib3_clothes.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Dance":
                try {
                    is = getAssets().open("madlib4_dance.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Simple":
                try {
                    is = getAssets().open("madlib0_simple.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Tarzan":
                try {
                    is = getAssets().open("madlib1_tarzan.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "University":
                try {
                    is = getAssets().open("madlib2_university.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return is;
    }
}