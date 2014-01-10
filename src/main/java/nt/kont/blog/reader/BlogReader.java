package nt.kont.blog.reader;

import android.util.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nt.kont.blog.reader.PostListAdapter;
import nt.kont.blog.reader.Post;

public class BlogReader extends Activity
{
    private static final String TAG = BlogReader.class.getSimpleName();
    private ArrayList<Post> posts;
    private PostListAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView postListView = (ListView)findViewById(R.id.postListView);
        final TextView infoTextView = (TextView)findViewById(R.id.infoTextView);

        posts = new ArrayList<Post>();
        adapter = new PostListAdapter(this, posts);
        postListView.setAdapter(adapter);

        Post.getAll(this, new PostsSuccess(){
            public void onResponse(ArrayList<Post> response){
                for (int i = 0; i < response.size(); i++){
                    posts.add(response.get(i));
                }
                adapter.notifyDataSetChanged();
                infoTextView.setText("got " + response.size() + " items");
            }
        }, new PostError(){
            public void onError(String error){
                infoTextView.setText(error);
            }
        });

        //list item click listener
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id){
                infoTextView.setText(posts.get(pos).getTitle());
                Intent intent = new Intent(BlogReader.this, ShowActivity.class);
                intent.putExtra("post_id", posts.get(pos).getId());
                startActivity(intent);
            }
        });
    }
}
