package nt.kont.blog.reader;
import android.content.Context;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.LayoutInflater;
import nt.kont.blog.reader.Post;

public class PostListAdapter extends BaseAdapter {
    private ArrayList<Post> posts;
    private Context context;

    public PostListAdapter(Context _context, ArrayList _posts){
        super();
        posts = _posts;
        context = _context;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.post_list_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.postTextView);
        textView.setText(String.valueOf(posts.get(position).getTitle()));
        return convertView;
    }
}
