package com.socalledengineers.diutransportapex.ui.registration;

public class RegistrationPresenter implements
        RegistrationContract.Presenter,RegistrationContract.Model.OnAccountCreateListener{

    RegistrationContract.View view ;
    RegistrationContract.Model model;

    public RegistrationPresenter(RegistrationContract.View view) {
        this.view = view;
        model = new RegistrationModel();
    }

    @Override
    public void createAccount(String email , String password,String name,String id) {
        if (view!=null){
            view.showLoading();
            model.OnAccountCreate(this,email , password,name,id);
        }

    }

    @Override
    public void onSuccessListener() {
        if (view!=null){
            view.hideLoading();
            view.onAccountSuccess();
        }
    }

    @Override
    public void onFailureListener(String error) {
        if (view!=null){
            view.hideLoading();
            view.onAccountFailure(error);
        }
    }



    @Override
    public void onDestroy() {
       view = null;
    }
}
