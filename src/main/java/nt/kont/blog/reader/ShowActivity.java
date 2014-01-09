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

        Post post = Post.getById(getIntent().getExtras().getInt("post_id"));
        TextView title = (TextView)findViewById(R.id.titleTextView);
        TextView body = (TextView)findViewById(R.id.bodyTextView);
        title.setText(post.getTitle());
        body.setText(post.getBody());
    }
}
