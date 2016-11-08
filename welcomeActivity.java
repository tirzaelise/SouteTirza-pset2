package com.example.tirza.soutetirza_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    // Go to the next activity
    public void toPlaceholder(View view) {
        Intent goToPlaceHolder = new Intent(this, placeholderActivity.class);
        String selectedValue = getSelectedValue();
        // Send the selected story to the next activity and go to the next activity
        goToPlaceHolder.putExtra("selectedStory", selectedValue);
        startActivity(goToPlaceHolder);
    }

    // Get the value that was selected in the dropdown menu
    public String getSelectedValue() {
        Spinner spinner = (Spinner) findViewById(R.id.chooseStory);
        return spinner.getSelectedItem().toString();
    }
}