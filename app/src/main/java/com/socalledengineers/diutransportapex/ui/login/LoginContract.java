package com.socalledengineers.diutransportapex.ui.login;


public class LoginContract {
    public interface View{

        void showLoading();
        void hideLoading();
        void onLoginSuccess();
        void onLoginFailure(String error);
        void checkUserStatus(boolean status);
    }


    public  interface Model{
        interface OnUserLoginListener{
            void onSuccessListener();
            void onFailureListener(String error);
            void onUserStatus(boolean status);
        }
        void OnUserLogin(OnUserLoginListener userLoginListener, String email, String pass);
        void onUserStatus(OnUserLoginListener userLoginListener);
    }

    public interface Presenter{
        void userLogin(String email , String password);
        void checkUserStatus();
        void onDestroy();
    }

}
