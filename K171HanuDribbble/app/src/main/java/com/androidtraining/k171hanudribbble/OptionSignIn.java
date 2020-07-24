package com.androidtraining.k171hanudribbble;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class OptionSignIn extends AppCompatActivity {
    private GoogleSignInClient gsi;
    private GoogleSignInOptions gso;
    private int RC_SIGN_IN = 1;
    private CallbackManager callbackManager;
    private LoginButton signInFacebook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_options);

        Button signIn = findViewById(R.id.sign_in);
        Button signUp = findViewById(R.id.sign_up);
        Button facebook = findViewById(R.id.facebook);
        Button signInWithGoogle = findViewById(R.id.google);
        signInFacebook = findViewById(R.id.facebookButton);
        Button back = findViewById(R.id.x);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionSignIn.this,SignIn.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dribbble.com/signup/new"));
                startActivity(browserIntent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionSignIn.this,MainActivity.class);
                startActivity(intent);
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        gsi = GoogleSignIn.getClient(this, gso);

        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });

        // facebook
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInFacebook.performClick();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        signInFacebook.setReadPermissions("email");

        signInFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void signInGoogle(){
        Intent signIn = gsi.getSignInIntent();
        startActivity(signIn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleResult(task);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleResult(Task<GoogleSignInAccount> task){
        try {
            GoogleSignInAccount account = task.getResult();
            updateUI(account);
        }catch (Exception e){
            System.err.print(e.toString());
        }
    }

    private void updateUI(GoogleSignInAccount account){

    }
}
