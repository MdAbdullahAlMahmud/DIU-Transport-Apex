package com.socalledengineers.diutransportapex.ui.login;

public class LoginPresenter implements  LoginContract.Presenter, LoginContract.Model.OnUserLoginListener {

    private LoginContract.View view ;
    private LoginContract.Model model;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void userLogin(String email, String password) {

        if (view!=null){
            view.showLoading();
            model.OnUserLogin(this,email,password);
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccessListener() {
        if (view!=null){
            view.hideLoading();

            view.onLoginSuccess();

        }
    }

    @Override
    public void onFailureListener(String error) {

        if (view!=null){
            view.onLoginFailure(error);
            view.hideLoading();
        }
    }

    @Override
    public void onUserStatus(boolean status) {
        if (view!=null){
            view.checkUserStatus(status);
        }

    }

    @Override
    public void checkUserStatus() {
        if (view!=null){
            model.onUserStatus(this);
        }
    }
}
