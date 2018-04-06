package com.cs428.app.bookapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.tokens.CognitoAccessToken;
import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.fragments.HomeFragment;
import com.cs428.app.bookapp.activity.fragments.ProfileFragment;
import com.cs428.app.bookapp.activity.fragments.SearchFragment;
import com.cs428.app.bookapp.activity.fragments.SettingsFragment;
import com.cs428.app.bookapp.interfaces.IClientFacade;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.networking.Serializer;
import com.cs428.app.bookapp.networking.ServerCommunicator;
import com.cs428.app.bookapp.networking.ServerProxy;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        presenter = new MainPresenter();

        toolbar = (Toolbar) findViewById(R.id.banner);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setActionBarClickListeners();
        fragmentDrawer = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        fragmentDrawer.setDrawerListener(this);

        /******* This is for passing the correct user information to AWS Cognito and initilizing the model *********/

        // Pass this info to server communicator, login, update model with the user, let observer update ui.
        setActionBarClickListeners();
        CognitoUserPool pool = new CognitoUserPool(this, new AWSConfiguration(this));
        Model.getSINGLETON().setUserPool(pool);
        new ServerProxy(new ServerCommunicator(new Serializer())).initialize();

        /**************************************** END **************************************************************/
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();

        CognitoCredentialsProvider provider = (CognitoCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
        provider.withSessionDuration(0);
        provider.clearCredentials();
        provider.withLogins(null);
        AWSStartupResult result = new AWSStartupResult(new IdentityManager(this));
        AWSMobileClient.getInstance().initialize(this);

        CognitoUserPool pool = new CognitoUserPool(this, new AWSConfiguration(this));
        pool.getCurrentUser().signOut();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("quit", true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the banner if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void showSnackbarMessage(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void setActionBarClickListeners() {
        View actionBar = findViewById(R.id.action_bar);

        ImageButton friendsButton = (ImageButton) actionBar.findViewById(R.id.profile_nav_button);
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProfileNavButtonAction();
            }
        });

        ImageButton homeButton = (ImageButton) actionBar.findViewById(R.id.home_nav_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doHomeNavButtonAction();
            }
        });

        ImageButton booksButton = (ImageButton) actionBar.findViewById(R.id.books_nav_button);
        booksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doBooksNavButtonAction();
            }
        });
    }

    public void doProfileNavButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show();
        Fragment profileFragment = ProfileFragment.newInstance(presenter);
        transitionFragment(profileFragment, "Profile");
    }

    public void doHomeNavButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show();
        Fragment homeFragment = HomeFragment.newInstance(presenter);
        transitionFragment(homeFragment, "Home");
    }

    public void doBooksNavButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Books clicked", Toast.LENGTH_SHORT).show();
    }

    public void transitionFragment(Fragment newFragment, String fragmentTitle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_body, newFragment).commit();

        toolbar = (Toolbar) findViewById(R.id.banner);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(fragmentTitle);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_up);
    }
}

/*
    https://www.androidhive.info/2015/04/android-getting-started-with-material-design/ PART 3.3
 */