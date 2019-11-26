package com.bfby.coldchain.di.component;

import com.bfby.coldchain.MyApplication;
import com.bfby.coldchain.data.http.ApiService;
import com.bfby.coldchain.di.module.AppModule;
import com.bfby.coldchain.di.module.HttpModule;
import com.google.gson.Gson;
import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class, HttpModule.class})
@Singleton
public interface AppComponent {

    ApiService getApiService();

    MyApplication getApplication();

    Gson getGson();
}
