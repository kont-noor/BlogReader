package nt.kont.blog.reader;

import android.util.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import nt.kont.blog.reader.PostListAdapter;
import nt.kont.blog.reader.Post;

public class BlogReader extends Activity
{
    private static final String TAG = BlogReader.class.getSimpleName();
    private ArrayList<Post> posts;
    private PostListAdapter adapter;
    private Context context;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;

        ListView postListView = (ListView)findViewById(R.id.postListView);
        final TextView infoTextView = (TextView)findViewById(R.id.infoTextView);

        posts = new ArrayList<Post>();
        adapter = new PostListAdapter(this, posts);
        postListView.setAdapter(adapter);

        Post.getAll(context, new PostsSuccess(){
            public void onResponse(Collection<Post> response){
                posts.clear();
                posts.addAll(response);
                adapter.notifyDataSetChanged();
                Toast.makeText(context, "got " + response.size() + " items", Toast.LENGTH_LONG).show();
            }
        }, new PostError(){
            public void onError(String error){
                Toast.makeText(context, error).show();
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
