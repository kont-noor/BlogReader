package nt.kont.blog.reader;
import java.util.ArrayList;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

interface PostsSuccess{
    void onResponse(ArrayList<Post> posts);
}

interface PostSuccess{
    void onResponse(Post post);
}

interface PostError{
    void onError(String error);
}

public class Post{
    private Integer id;
    private String title;
    private String body;
    private static ArrayList<Post> posts;

    public static void getAll(Context context, final PostsSuccess success, final PostError error){
        //load items from server
        final RequestQueue queue = Volley.newRequestQueue(context);

        final String url = "http://stormy-brushlands-5721.herokuapp.com/posts.json";

        JsonArrayRequest jsArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response){
                posts = new ArrayList();
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject o = response.getJSONObject(i);
                        posts.add(new Post(o.optInt("id"), o.get("title").toString(), o.get("body").toString()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                success.onResponse(posts);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError exception){
                String errorMessage = "Error: " + exception.toString();
                error.onError(errorMessage);
            }
        });

        queue.add(jsArrayRequest);
    }

    public static void getById(Integer id, Context context, final PostSuccess success, final PostError error){
        final RequestQueue queue = Volley.newRequestQueue(context);

        final String url = "http://stormy-brushlands-5721.herokuapp.com/posts/" + id + ".json";

        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    Post post = new Post(response.optInt("id"), response.get("title").toString(), response.get("body").toString());
                    success.onResponse(post);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError exception){
                String errorMessage = "Error: " + exception.toString();
                error.onError(errorMessage);
            }
        });

        queue.add(jsObjectRequest);
    }

    public Post(String _title, String _body){
        title = _title;
        body = _body;
    }

    public Post(Integer _id, String _title, String _body){
        id = _id;
        title = _title;
        body = _body;
    }

    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }
}
