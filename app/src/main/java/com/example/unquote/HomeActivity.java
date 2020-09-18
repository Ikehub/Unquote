package com.example.unquote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Button btnMusic, btnMovie, btnSports, btnHistory, btnAbout;
    public static final String EXTRA = "Section_Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        // TODO: 6/24/2020 Set alert dialog with information on how to play the game
        String gameInfo = "Pick a section and guess who said the quote. You've got only three lives. ENJOY!";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("How To Play");
        builder.setMessage(gameInfo);
        builder.setPositiveButton("Got It!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent("Music");
            }
        });

        btnSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent("Sports");
            }
        });

        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent("Movie");
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent("History");
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and Developed with love by Ikenna \n" +
                        "Contact me at ikennaokonkwo2012@gmail.com for any enquires.");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("Contact", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: Navigate user to an email app with my email as the receiver
                        String mailto = "mailto:ikennaokonkwo2012@gmail.com" +
                                "?subject=" + Uri.encode("Important Message") +
                                "&body=" + Uri.encode("Hi There, \n");

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse(mailto));

                        try {
                            startActivity(emailIntent);
                        } catch (ActivityNotFoundException e) {
                            //TODO: Handle case where no email app is available
                            Toast.makeText(HomeActivity.this, "You have no available email application, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        });
    }

    private void startIntent(String section) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra(EXTRA, section);
        startActivity(intent);
    }

    private void initViews() {
        btnMusic = findViewById(R.id.btnMusic);
        btnMovie = findViewById(R.id.btnMovie);
        btnSports = findViewById(R.id.btnSports);
        btnHistory = findViewById(R.id.btnHistory);
        btnAbout = findViewById(R.id.btnAbout);
    }

    // TODO: 6/25/2020 Set the best background color for both activities in their xml files
    // TODO: 6/25/2020 Create drawables for other sections and existing sections in order to match their background colors with the activity's
    // TODO: 6/25/2020 When the user clicks on play again and is navigated back to the home activity, the alert dialog should not show for a second time. Find a way to fix it
    // TODO: 6/25/2020 Also change the app logo inorder to personalize it
    // TODO: 6/25/2020 Also change the current questions inorder to personalize it
}