package zakp.fireproject.ui.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import zakp.fireproject.R;
import zakp.fireproject.helper.EventPosterHelper;
import zakp.fireproject.ui.base.BaseActivity;
import zakp.fireproject.ui.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogInActivity extends BaseActivity implements LogInMvpView {


    public final static String LOGIN_SHARE = "login_share";


    View view;

    @Bind(R.id.login_edit_text)
    EditText loginEditText;
    @Bind(R.id.password_edit_text)
    EditText passwordEditText;
    @Bind(R.id.log_in_button)
    Button logInButton;
    @Bind(R.id.log_in_base_text_view)
    TextView baseLogInTextView;
    @Bind(R.id.log_in_error_text_view)
    TextView errorLogInTextView;
    @Bind((R.id.progress_bar))
    ProgressBar progressBar;

    @Inject
    LogInPresenter logInPresenter;
    @Inject
    EventPosterHelper eventPosterHelper;

    public LogInActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getActivityComponent().inject(this);

        logInPresenter.attachView(this);
        ButterKnife.bind(this);

        RxView.clicks(logInButton).subscribe(aVoid -> {
            logInButton.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            logInPresenter.logIn(
                    loginEditText.getText().toString(),
                    passwordEditText.getText().toString()
            );
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow((this.getCurrentFocus() == null) ? view.getWindowToken() : this.getCurrentFocus().getWindowToken(), 0);
        });

    }


    @Override
    public void setNormalState() {
        long delay;
        delay = errorLogInTextView.animate().alpha(0.0f).getDuration();
        errorLogInTextView.animate().alpha(0.0f);
        baseLogInTextView.animate().alpha(1.0f).setStartDelay(delay);

    }

    @Override
    public void setError() {
        long delay;
        delay = baseLogInTextView.animate().alpha(0.0f).getDuration();
        baseLogInTextView.animate().alpha(0.0f);
        errorLogInTextView.animate().alpha(1.0f).setStartDelay(delay);
    }


    @Override
    public void progressBarOff() {

        progressBar.setVisibility(View.GONE);
        logInButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void goToProjectList() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

        finish();
        progressBarOff();
        // FragmentHelper.replaceFragment(this, new ProjectListFragment(), true);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
