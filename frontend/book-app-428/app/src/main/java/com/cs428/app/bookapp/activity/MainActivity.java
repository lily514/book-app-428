package com.cs428.app.bookapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
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
import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.networking.Serializer;
import com.cs428.app.bookapp.networking.ServerCommunicator;
import com.cs428.app.bookapp.networking.ServerProxy;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MainPresenter presenter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        toolbar = (Toolbar) findViewById(R.id.banner);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchView = (SearchView) findViewById(R.id.search_text_view);
        setSearchViewListener(searchView);
        searchView.setSubmitButtonEnabled(true);

        transitionFragment(HomeFragment.newInstance(presenter), "Home");

        /******* This is for passing the correct user information to AWS Cognito and initilizing the model *********/

        // Pass this info to server communicator, login, update model with the user, let observer update ui.
        CognitoUserPool pool = new CognitoUserPool(this, new AWSConfiguration(this));
        Model.getSINGLETON().setUserPool(pool);
        Model.getSINGLETON().initializeServer();

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

    /** Menu methods **/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the banner if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                doHomeNavButtonAction();
                return true;
            case R.id.action_profile:
                doProfileNavButtonAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** search methods **/
    public void setSearchViewListener(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                OnSearchTaskComplete onSearchTaskComplete = doSearchButtonAction();
                return presenter.doSearch(query, onSearchTaskComplete);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                OnSearchTaskComplete onSearchTaskComplete = doSearchButtonAction();
                return presenter.doSearch(newText, onSearchTaskComplete);
            }
        });

    }

    public OnSearchTaskComplete doSearchButtonAction() {
        Fragment searchFragment = SearchFragment.newInstance(presenter);
        transitionFragment(searchFragment, "Home");
        return (OnSearchTaskComplete) searchFragment;
    }


    /** action methods and fragment transitions **/

    private void showSnackbarMessage(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
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


    public void transitionFragment(Fragment newFragment, String fragmentTitle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_body, newFragment).commit();
    }

    public void setBannerTitle(String title) {
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_up);
    }
}

/*
    https://www.androidhive.info/2015/04/android-getting-started-with-material-design/ PART 3.3
 */