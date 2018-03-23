package com.cs428.app.bookapp.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobile.auth.ui.AuthUIConfiguration;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.cs428.app.bookapp.R;

/**
 * Created by mgard on 3/20/2018.
 */

public class LoginActivity extends Activity  {
    private Toolbar toolbar;
    private FragmentDrawer fragmentDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.home_fragment);

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(final AWSStartupResult awsStartupResult) {
                AuthUIConfiguration config =
                        new AuthUIConfiguration.Builder()
                                .userPools(true)  // true? show the Email and Password UI
                                .backgroundColor(Color.WHITE) // Change the backgroundColor
                                .isBackgroundColorFullScreen(true) // Full screen backgroundColor the backgroundColor full screenff
                                .fontFamily("sans-serif-light") // Apply sans-serif-light as the global font
                                .canCancel(true)
                                .build();
                SignInUI signinUI = (SignInUI) AWSMobileClient.getInstance().getClient(LoginActivity.this, SignInUI.class);
                signinUI.login(LoginActivity.this, MainActivity.class).authUIConfiguration(config).execute();
            }
        }).execute();

        if(getIntent().getBooleanExtra("quit", true)){
            finish();
        }
    }
}
