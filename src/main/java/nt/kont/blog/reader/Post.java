package nt.kont.blog.reader;
import java.util.ArrayList;

public class Post{
    private Integer id;
    private String title;
    private String body;

    public static ArrayList<Post> getAll(){
        ArrayList<Post> posts = new ArrayList<Post>();
        for(int i=0; i<10; i++){
            Post post = new Post(i, "title #" + i, "body #" + i);
            posts.add(post);
        }
        return posts;
    }

    public static Post getById(Integer id){
        return new Post(id, "title #" + id, "body#" + id);
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
