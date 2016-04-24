package zakp.fireproject.ui.login;


import zakp.fireproject.ui.base.MvpView;

public interface LogInMvpView extends MvpView {
    void setNormalState();

    void setError();


    void progressBarOff();

    void goToProjectList();

}
