package com.socalledengineers.diutransportadmin.utils;

import android.content.Context;

import es.dmoral.toasty.Toasty;

public class Display {

    public static  void infoToast(Context context , String msg){

        Toasty.info(context,msg,Toasty.LENGTH_SHORT,true).show();
    }
    public static  void successToast(Context context , String msg){

        Toasty.success(context,msg,Toasty.LENGTH_SHORT,true).show();
    }
    public static  void errorToast(Context context , String msg){

        Toasty.error(context,msg,Toasty.LENGTH_SHORT,true).show();
    }



}