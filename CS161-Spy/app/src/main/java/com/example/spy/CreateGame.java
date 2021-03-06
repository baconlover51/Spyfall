package com.example.spy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.io.InputStream;

public class CreateGame extends AppCompatActivity implements View.OnClickListener {
    EditText game_name;
    EditText game_code;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        //load image for activity_create_game.xml
        //load image for login screen from asset folder
        ImageView imageView3 = (ImageView)findViewById(R.id.newGame_spy);

        try {
            InputStream istream = getResources().getAssets().open("newGame_spy.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView3.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.d("Assets","Error");
        }

        //Views
        game_name = (EditText) findViewById(R.id.field_game_name);
        game_code = (EditText) findViewById(R.id.field_game_code);

        //Buttons
        findViewById(R.id.button_begin_game).setOnClickListener(this);
        findViewById(R.id.button_return_lobby).setOnClickListener(this);
    }

    //Do stuff when buttons are clicked
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_begin_game) {
            //Attach the game code to an Intent, and send it to the game waiting lobby
            Intent start_game_lobby = new Intent(CreateGame.this, GameLobby.class);

            data = new Bundle(); //Create a bundle to hold game data
            data.putString("game_code", game_code.getText().toString());
            data.putString("game_name", game_name.getText().toString());

            start_game_lobby.putExtra("game_data", data); //Attach the Bundle to the Intent <key, val>
            startActivity(start_game_lobby);
        } else if (i == R.id.button_return_lobby) {
            Intent return_to_lobby = new Intent(CreateGame.this, Lobby.class);
            startActivity(return_to_lobby);
        }
    }
}
