package com.socalledengineers.diutransportapex.ui.registration;

public class RegistrationContract {

    public interface View{

        void showLoading();
        void hideLoading();
        void onAccountSuccess();
        void onAccountFailure(String error);
    }


    public  interface Model{
        interface OnAccountCreateListener{
            void onSuccessListener();
            void onFailureListener(String error);
        }
        void OnAccountCreate(OnAccountCreateListener onAccountCreateListener,String email , String pass,String name,String id);
    }

    public interface Presenter{
        void createAccount(String email , String password,String name,String id);
        void onDestroy();
    }


}
