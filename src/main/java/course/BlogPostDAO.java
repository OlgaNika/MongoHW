package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.descending;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public Document findByPermalink(String permalink) {

        // XXX HW 3.2,  Work Here
        //looks like it done
        Document post = null;

        post=postsCollection.find(eq("permalink", permalink)).first();

        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.

    public List<Document> findByDateDescending(int limit) {

        // XXX HW 3.2,  Work Here
        // Return a list of DBObjects, each one a post from the posts collection
        List<Document> posts = null;

        Bson sort = descending("date");

        posts=postsCollection.find().sort(sort).into(new ArrayList<Document>());

        return posts;
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();


        // XXX HW 3.2, Work Here
        // Remember that a valid post has the following keys:
        // author, body, permalink, tags, comments, date, title
        //
        // A few hints:
        // - Don't forget to create an empty list of comments
        // - for the value of the date key, today's datetime is fine.
        // - tags are already in list form that implements suitable interface.
        //todo check comments
        // - we created the permalink for you above.

        // Build the post object and insert it
        Document post = new Document();
        ArrayList<Document> comments=new ArrayList();
        post.append("author",username).append("body",body).append("permalink",permalink).append("tags",tags).append("comments",comments).append("date",new Date()).append("title",title);
        postsCollection.insertOne(post);
        return permalink;
    }




    // White space to protect the innocent








    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink, final String username) {

        // XXX HW 3.3, Work Here
        // Hints:
        // - email is optional and may come in NULL. Check for that.
        // - best solution uses an update command to the database and a suitable
        //   operator to append the comment on to any existing list of comments
        Document post = new Document();
         post = postsCollection.find(eq("permalink", permalink)).first();

         Document comment = new Document().append("author",name).append("body",body).append("date", new  Date());

      //   if (post != null && !post.equals("")) {


             //  System.out.println("adding "+name+";password"+password);

             if (email != null && !email.equals("")) {
                 // the provided email address
                 comment.append("email", email);

             }
       //  }
        System.out.println(comment);
        System.out.println(post);
       // postsCollection.updateOne("permalink", permalink,  new Document("$set", new Document("comments", comment)));

        Document filter = new Document("permalink", permalink);

      // if (post.get("comments") == null) {

           // postsCollection.updateOne(eq("_id", post.get("_id")) , comment);
           postsCollection.updateOne(eq("permalink", permalink), new Document("$push", new Document("comments", comment)));
      // }

       // new  Document("$upsert", true)
      //  else
       //    postsCollection.updateOne(eq("_id", post.get("_id")) , new Document("$set", new Document("comments.date", new Date())));

    }
}
