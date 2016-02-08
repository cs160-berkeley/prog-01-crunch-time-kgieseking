package com.example.kgies_000.crunchtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.EditText;
import android.view.Gravity;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
    private static final String TAG = "MainTagName";

    private Spinner spinner;
    private Button btnSubmit;
    private EditText tx;
    private int calories;
    private String[] timeActivities = {
            "Leg-lift",
            "Plank",
            "Jumping Jacks",
            "Cycling",
            "Walking",
            "Jogging",
            "Swimming",
            "Stair-Climbing"
    };
    private String[] repActivities = {
            "Pushups",
            "Situps",
            "Squats",
            "Pullups"
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        Log.d(TAG, "initiated");
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.activity);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String act = String.valueOf(spinner.getSelectedItem());
                tx = (EditText) findViewById(R.id.reps);
                Log.d(TAG, "string is " + act);
                if (Arrays.asList(repActivities).contains(act)) {
                    Log.d(TAG, "reps");
                    tx.setHint("reps");
                } else {
                    Log.d(TAG, "minutes");
                    tx.setHint("minutes");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.activity);
        tx = (EditText) findViewById(R.id.reps);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String act = String.valueOf(spinner.getSelectedItem());
                int timeReps = Integer.parseInt(tx.getText().toString());
                calories = calculateSingleCalories(act, timeReps);

                showCalories(calories);
            }
        });
    }

    public void showCalories(int calories) {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setMessage("You burned " + calories + " calories!\n" +
                                "Equivalent exercises:\n" +
                                "Pushups: " + (int) ((double) calories/.75) + " reps\n" +
                                "Situps: " + (int) ((double) calories/.2) + " reps\n" +
                                "Jumping Jacks: " + (int) ((double) calories/3.9) + " minutes\n" +
                                "Jogging: " +  (int) ((double) calories/9.1) + " minutes"
                                );
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    public int calculateSingleCalories(String activity, int timeReps) {
        int singleCalories;
        double cbp = 0;  //calories burned per minute/rep
        if (activity.equals("Pushups"))
            cbp = .75;
        if (activity.equals("Situps"))
            cbp =  .2;
        if (activity.equals("Squats"))
            cbp = .7;
        if (activity.equals("Leg-lift"))
            cbp = 4;
        if (activity.equals("Plank"))
            cbp = 3.9;
        if (activity.equals("Jumping Jacks"))
            cbp = 5.1;
        if (activity.equals("Pullups"))
            cbp = 4;
        if (activity.equals("Cycling"))
            cbp = 6;
        if (activity.equals("Walking"))
            cbp = 5.3;
        if (activity.equals("Jogging"))
            cbp = 9.1;
        if (activity.equals("Swimming"))
            cbp = 6.7;
        if (activity.equals("Stair-Climbing"))
            cbp = 14.2;
        singleCalories = (int) ((double) timeReps * cbp);
        return singleCalories;
    }

   /* public int EquivalentCalories(String activity, int calories) {
        int eqReps = 0;
        double cbp = 0;  //calories burned per minute/rep
        if (activity.equals("Pushups"))
            eqReps = (int) ((double) calories/.75);
        if (activity.equals("Situps"))
            eqReps = (int) ((double) calories/.2);
*//*        if (activity.equals("Squats"))
            eqReps = (int) ((double) calories/.7);
        if (activity.equals("Leg-lift"))
            eqReps = (int) ((double) calories/4);
        if (activity.equals("Plank"))*//*
            eqReps = (int) ((double) calories/3.9);
        if (activity.equals("Jumping Jacks"))
            eqReps = (int) ((double) calories/5.1);
*//*        if (activity.equals("Pullups"))
            eqReps = (int) ((double) calories/4);
        if (activity.equals("Cycling"))
            eqReps = (int) ((double) calories/6);
        if (activity.equals("Walking"))*//*
            eqReps = (int) ((double) calories/5.3);
        if (activity.equals("Jogging"))
            eqReps = (int) ((double) calories/9.1);
*//*        if (activity.equals("Swimming"))
            eqReps = (int) ((double) calories/6.7);
        if (activity.equals("Stair-Climbing"))
            eqReps = (int) ((double) calories/14.2);*//*
    }*/
}