package nt.kont.blog.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BlogReader extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView postListView = (ListView)findViewById(R.id.postListView);
        final TextView infoTextView = (TextView)findViewById(R.id.infoTextView);

        final ArrayList<String> posts = new ArrayList<String>();
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, posts);
        postListView.setAdapter(adapter);

        //fake loading posts
        for(int i=0; i<10; i++){
            posts.add(0, "post #" + i);
            adapter.notifyDataSetChanged();
        }

        //list item click listener
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id){
                infoTextView.setText(posts.get(pos));
                Intent intent = new Intent(BlogReader.this, ShowActivity.class);
                intent.putExtra("item name", posts.get(pos));
                startActivity(intent);
            }
        });
    }
}

