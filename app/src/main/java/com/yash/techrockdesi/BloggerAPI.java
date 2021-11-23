package com.yash.techrockdesi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class BloggerAPI {

    // this is API key
    private static final String key = "AIzaSyB3L4pkI_J0ELTLBi0R7gVc8uhESc4Y4iw";
    private static final String url = "https://www.googleapis.com/blogger/v3/blogs/3194500583568298467/posts/";

    public static PostService postService = null;

    public static PostService getService(){

        if (postService == null){
            //Create
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostService.class);
        }
        return postService;
    }
    public interface PostService{

        @GET("?key="+key)
        Call<PostList> getPostList();



        // For Access the Specific Post
        /*
        @GET("{postId}/?key="+key)
        Call<Item> getPostById(@Path("postId") String id);

         */
    }

}
