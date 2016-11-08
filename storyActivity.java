package com.example.tirza.soutetirza_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class storyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        Bundle extras = getIntent().getExtras();
        String chosenStory = extras.getString("finishedStory", "none");

        // Write the story that was created and place it in the center of the screen
        TextView putStory = (TextView) findViewById(R.id.finishedStory);
        putStory.setText(chosenStory);
        putStory.setGravity(Gravity.CENTER);
    }

    // Go back to the home screen of the app if the play again button is clicked
    public void startOver(View view) {
        finish();
    }
}
