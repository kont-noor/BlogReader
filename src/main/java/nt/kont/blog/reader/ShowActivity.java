package nt.kont.blog.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import nt.kont.blog.reader.Post;

public class ShowActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        final TextView title = (TextView)findViewById(R.id.titleTextView);
        final TextView body = (TextView)findViewById(R.id.bodyTextView);

        Integer id = getIntent().getExtras().getInt("post_id");

        Post.getById(id, this, new PostSuccess(){
            public void onResponse(Post post){
                title.setText(post.getTitle());
                body.setText(post.getBody());
            }
        }, new PostError(){
            public void onError(String error){
                title.setText(error);
            }
        });
    }
}
