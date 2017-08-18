package myt.ahmed.isbtask.dagger;

import android.content.Context;


import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import myt.ahmed.isbtask.Helpers.ServicesConnection;
import myt.ahmed.isbtask.callBacks.ApiInterface;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Ahmed shaban on 7/30/2017.
 */

@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return ServicesConnection.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }


    @Provides
    @Singleton
    OkHttpClient  provideOkHTTp(Interceptor interceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named(NAME_BASE_URL) String baseUrl, Converter.Factory converter) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideUsdaApi(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }



}
