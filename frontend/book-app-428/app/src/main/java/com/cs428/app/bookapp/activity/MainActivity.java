package com.cs428.app.bookapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.fragments.HomeFragment;
import com.cs428.app.bookapp.activity.fragments.LoginFragment;
import com.cs428.app.bookapp.activity.fragments.ProfileFragment;
import com.cs428.app.bookapp.activity.fragments.SettingsFragment;

/*
getUser()
getCurrentUser()
register() / login() / logout()
postRecommendation()
addToReadinglist()
follow() / unfollow()
getBook()
searchBook()
 */

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar toolbar;
    private FragmentDrawer fragmentDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        toolbar = (Toolbar) findViewById(R.id.banner);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragmentDrawer = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        fragmentDrawer.setDrawerListener(this);

        setCardViewClickListeners();
        setActionBarClickListeners();

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayFragment(view, position);
    }

    private void showSnackbarMessage(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void setActionBarClickListeners() {
        View actionBar = findViewById(R.id.action_bar);

        ImageButton friendsButton = (ImageButton) actionBar.findViewById(R.id.friends_nav_button);
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doFriendsNavButtonAction();
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

    private void doFriendsNavButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Friends clicked", Toast.LENGTH_SHORT).show();
    }

    private void doHomeNavButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show();
    }

    private void doBooksNavButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Books clicked", Toast.LENGTH_SHORT).show();
    }

    private void setCardViewClickListeners() {
//        View cardView = findViewById(R.id.card_view);
//
//        Button rateButton = (Button) cardView.findViewById(R.id.rate_button);
//        rateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doRateButtonAction();
//            }
//        });

//        Button shareButton = (Button) cardView.findViewById(R.id.share_button);
//        shareButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doShareButtonAction();
//            }
//        });

//        Button reviewButton = (Button) cardView.findViewById(R.id.review_button);
//        reviewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doReviewButtonAction();
//            }
//        });

    }

    private void doRateButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Rate clicked", Toast.LENGTH_SHORT).show();
    }

    private void doShareButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Share clicked", Toast.LENGTH_SHORT).show();
    }

    private void doReviewButtonAction() {
        // the following code is for testing purposes, will be changed
        Context context = getApplicationContext();
        Toast.makeText(context, "Review clicked", Toast.LENGTH_SHORT).show();
    }

    private void displayFragment(View view, int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new ProfileFragment();
                title = getString(R.string.title_profile);
                break;
            case 2:
                fragment = new SettingsFragment();
                title = getString(R.string.title_settings);
                break;
            default:
                break;
        }

        showSnackbarMessage(view, title);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the banner title
            getSupportActionBar().setTitle(title);
        }
    }
}

/*
    https://www.androidhive.info/2015/04/android-getting-started-with-material-design/ PART 3.3
 */