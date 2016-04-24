package zakp.fireproject.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import javax.inject.Inject;

import zakp.fireproject.model.ReferenceUrl;
import zakp.fireproject.ui.base.BasePresenter;


public class LogInPresenter extends BasePresenter<LogInMvpView> {

    private final String correctUsername = "admin"; //FIXME mockup
    private final String correctPassword = "admin"; //FIXME mockup

    /* Reference to firebase */
    private Firebase mFirebaseChatRef;

    /* Reference to users in firebase */
    private Firebase mFireChatUsersRef;


    @Inject
    Activity activity;

    @Inject
    public LogInPresenter() {
    }


    @Override
    public void attachView(LogInMvpView mvpView) {
        super.attachView(mvpView);


        mFirebaseChatRef = new Firebase(ReferenceUrl.FIREBASE_CHAT_URL); // Get app main firebase url

        mFireChatUsersRef = new Firebase(ReferenceUrl.FIREBASE_CHAT_URL).child(ReferenceUrl.CHILD_USERS);


    }

    public void logIn(String username, String password) {

        getMvpView().setNormalState();
        //TODO After Log in Fragment

        mFireChatUsersRef.child(username).child("password").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                String passwordReturn = (String) snapshot.getValue();

                if (password.equals(passwordReturn)) {

                    SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(LogInActivity.LOGIN_SHARE, username);
                    editor.apply();

                    getMvpView().goToProjectList();
                } else {
                    getMvpView().progressBarOff();
                    getMvpView().setError();
                }

            }

            @Override
            public void onCancelled(FirebaseError error) {
                getMvpView().progressBarOff();
            }

        });

        //getMvpView().pop();
    }


}
