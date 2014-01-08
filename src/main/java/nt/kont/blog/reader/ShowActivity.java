package nt.kont.blog.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

public class ShowActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        TextView text = (TextView)findViewById(R.id.postTextView);
        text.setText(getIntent().getExtras().getString("item name"));
    }
}
