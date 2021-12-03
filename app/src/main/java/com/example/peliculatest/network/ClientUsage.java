package com.example.peliculatest.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientUsage {

    private static final String API_URL = "https://api.themoviedb.org/3/";
    private static final String BEARER = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NjI2NDlhNTZhNGVmMjlkYjY4Zjg3NzdkMzM1MmY5NCIsInN1YiI6IjViZTVhOWFkMGUwYTI2MTRiMTA0NTFlNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gIOUhDUq1gvHaEoplGCIRR_xiA_XKiSACoec0CEghw4";
    private static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().header("Authorization","Bearer "+ BEARER).build();
            return chain.proceed(request);
        });
        OkHttpClient client = builder.build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .build();
        }
        return retrofit;
    }

    private static Gson createGson() {
        GsonBuilder builder = new GsonBuilder().serializeNulls();
        return builder.create();

    }
}
