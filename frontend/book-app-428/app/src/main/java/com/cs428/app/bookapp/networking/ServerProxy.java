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
import com.cs428.app.bookapp.interfaces.OnHomeBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReadingBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReviewedBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.User;

/**
 * Created by mgard on 3/2/2018.
 */

public class ServerProxy implements IServerProxy{

    private IServerCommunicator serverCommunicator;

    public ServerProxy(IServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
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

    @Override
    public boolean followFriend(User user, String followUsername) {
        return false;
    }

    @Override
    public boolean recommendBook(User user, String bookId) {
        //TODO: implement recommendBook
        return false;
    }

    @Override
    public void upvoteBook(String bookID) {
        this.serverCommunicator.upvoteBook(bookID);
    }

    @Override
    public void downvoteBook(String bookID) {
        this.serverCommunicator.downvoteBook(bookID);
    }

    @Override
    public void getRecommendationFor(String bookid, OnHomeBooksTaskComplete listener) {
        //TODO: Home books
        this.serverCommunicator.getRecommendations(bookid, listener);
    }

    @Override
    public void searchBook(String searchTerm, OnSearchTaskComplete listener) {
        this.serverCommunicator.searchBookByTitle(searchTerm, listener);
        this.serverCommunicator.searchBookByAuthor(searchTerm, listener);

    }

    @Override
    public void searchPerson(String searchString, OnSearchTaskComplete listener) {
        this.serverCommunicator.searchPersonByName(searchString, listener);
    }

    @Override
    public void getReadingBookById(String book_id, OnReadingBooksTaskComplete listener) {
        this.serverCommunicator.getReadingBookById(book_id, listener);
    }

    @Override
    public void getReviewedBookById(String book_id, OnReviewedBooksTaskComplete listener) {
        this.serverCommunicator.getReviewedBookById(book_id, listener);
    }

}
