//package com.awesome.kosyo.carserviceproject.api;
//
//import com.google.gson.JsonObject;
//import com.squareup.okhttp.Interceptor;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Response;
//
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//import retrofit.Callback;
//import retrofit.GsonConverterFactory;
//import retrofit.Retrofit;
//import retrofit.http.Body;
//import retrofit.http.Field;
//import retrofit.http.FormUrlEncoded;
//import retrofit.http.GET;
//import retrofit.http.Header;
//import retrofit.http.Headers;
//import retrofit.http.POST;
//import retrofit.http.PUT;
//import retrofit.http.Path;
//import retrofit.http.Query;
//
///**
// * Created by kosyo on 30.11.15.
// */
//public class RestClient {
//
//    private static GitApiInterface gitApiInterface ;
////    private static String baseUrl = "https://Insert your url.com" ;
////
////    public static GitApiInterface getClient() {
////        if (gitApiInterface == null) {
////
////            OkHttpClient okClient = new OkHttpClient();
////            okClient.interceptors().add(new Interceptor() {
////                @Override
////                public Response intercept(Chain chain) throws IOException {
////                    Response response = chain.proceed(chain.request());
////                    return response;
////                }
////            });
////
////            Retrofit client = new Retrofit.Builder()
////                    .baseUrl(baseUrl)
////                    .addConverter(String.class, new ToStringConverter())
////                    .client(okClient)
////                    .addConverterFactory(GsonConverterFactory.create())
////                    .build();
////            gitApiInterface = client.create(GitApiInterface.class);
////        }
////        return gitApiInterface ;
////    }
//
//    public interface GitApiInterface {
//
////        @Headers("User-Agent: Retrofit2.0Tutorial-App")
////        @GET("/search/users")
////        void getUsersNamedTom(@Query("q") String name);
//
//
//        @FormUrlEncoded
//        @POST(ApplicationConfiguration.API_SET_STORE_ALARM)
//        void setStoreAlarm(@Header(ApplicationConfiguration.mToken) String token,
//                           @Field(ApplicationConfiguration.mStoreId) int storeId,
//                           @Field(ApplicationConfiguration.mAlarm) boolean alarm,
//                           Callback<JsonObject> cb);
//
////        @PUT("/user/{id}/update")
////        void updateUser(@Path("id") String id , @Body Item user);
//    }
//
//
//
//
////    private static RestClient instance = null;
////
////    private ResultReadyCallback callback;
////
////    private static final String BASE_URL = "http://10.0.2.2:8080";
////    private APIService service;
////    List<User> users = null;
////    boolean success = false;
////
////
////    public RestClient() {
////        Retrofit retrofit = new Retrofit.Builder()
////                .addConverterFactory(GsonConverterFactory.create())
////                .baseUrl(BASE_URL)
////                .build();
////
////        service = retrofit.create(APIService.class);
////    }
////
////    public List<User> getUsers() {
////        Call<List<User>> userlist = service.users();
////        userlist.enqueue(new Callback<List<User>>() {
////            @Override
////            public void onResponse(Response<List<User>> response) {
////                if (response.isSuccess()) {
////                    users = response.body();
////                    callback.resultReady(users);
////                }
////            }
////
////            @Override
////            public void onFailure(Throwable t) {
////                Log.e("REST", t.getMessage());
////            }
////        });
////        return users;
////    }
////
////    public void setCallback(ResultReadyCallback callback) {
////        this.callback = callback;
////    }
////
////    public boolean createUser(final Context ctx, User user) {
////        Call<User> u = service.createUser(user);
////        u.enqueue(new Callback<User>() {
////            @Override
////            public void onResponse(Response<User> response) {
////                success = response.isSuccess();
////                if(success) {
////                    Toast.makeText(ctx, "User Created", Toast.LENGTH_SHORT).show();
////                } else {
////                    Toast.makeText(ctx, "Couldn't create user", Toast.LENGTH_SHORT).show();
////                }
////            }
////
////            @Override
////            public void onFailure(Throwable t) {
////                Log.w("REST", t.getMessage());
////                Toast.makeText(ctx, "Couldn't create user", Toast.LENGTH_SHORT).show();
////
////            }
////        });
////        return success;
////    }
////
////    public static RestClient getInstance() {
////        if(instance == null) {
////            instance = new RestClient();
////        }
////        return instance;
////    }
////
////    public interface ResultReadyCallback {
////        public void resultReady(List<User> users);
////    }
//
//}
