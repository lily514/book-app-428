package com.cs428.app.bookapp.networking;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.tokens.CognitoIdToken;
import com.cs428.app.bookapp.interfaces.IServerCommunicator;
import com.cs428.app.bookapp.interfaces.IServerProxy;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by mgard on 3/2/2018.
 */

public class ServerProxy implements IServerProxy{

    private IServerCommunicator serverCommunicator;

    public ServerProxy(IServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
    }

    @Override
    public boolean followFriend(User user, String followUsername) {
        List<User> allUsers = this.serverCommunicator.getUsers();
        for(User otherUser : allUsers) {
            if(otherUser.getName().equals(followUsername)){
                return this.serverCommunicator.followUser(user.getId(), otherUser.getId());
            }
        }
        return false;
    }

    @Override
    public void searchBook(String searchTerm) {
        this.serverCommunicator.searchBookByTitle(searchTerm);
    }

    @Override
    public boolean recommendBook(User user, String bookId) {
        return false;
    }

    @Override
    public boolean rateBook(User user, String bookID, int rating) {
        //TODO: implement rateBook
        return false;
    }

    @Override
    public List<Book> getRecommendationFor(User user) {
        //TODO: Will a user have a list of recommendations? What is the api endpoint for that??
        return null;
    }

    @Override
    public void getBookById(String bookId) {
        this.serverCommunicator.getBookById(bookId);
    }

    @Override
    public void initialize() {
        final CognitoUserPool userPool = Model.getSINGLETON().getUserPool();
        CognitoUser user = userPool.getCurrentUser();
        user.getSession(new AuthenticationHandler() {
            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                CognitoIdToken token = userSession.getIdToken();
                serverCommunicator.setUserToken(token.getJWTToken());
                serverCommunicator.loadUser(userPool.getCurrentUser().getUserId());
            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {

            }

            @Override
            public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

            }

            @Override
            public void authenticationChallenge(ChallengeContinuation continuation) {

            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
