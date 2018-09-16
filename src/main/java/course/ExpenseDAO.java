package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

public class ExpenseDAO {
    MongoCollection<Document> expensesCollection;

    public ExpenseDAO(final MongoDatabase expenserDatabase) {
        expensesCollection = expenserDatabase.getCollection("expenses");
    }

    public Document findByPermalink(String permalink) {
        Document expense = expensesCollection.find(eq("permalink", permalink)).first();

        // fix up if a post has no likes

      /*  if (expense != null) {
            List<Document> comments = (List<Document>) expense.get("comments");
            for (Document comment : comments) {
                if (!comment.containsKey("num_likes")) {
                    comment.put("num_likes", 0);
                }
            }
        }*/


        return expense;
    }

    public List<Document> findByDateDescending(int limit) {

        List<Document> expenses = null;

        Bson sort = descending("created");

        //expenses=expensesCollection.find(eq("report_id",report_id)).sort(sort).limit(limit).into(new ArrayList<Document>());
        expenses=expensesCollection.find().sort(sort).limit(limit).into(new ArrayList<Document>());

        return expenses;
    }

    public String addExpense(String title, String body, List tags, String username, String description ) {

        System.out.println("inserting expenses entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();


        // Build the post object and insert it
        Document expense = new Document();
        ArrayList<Document> comments=new ArrayList();
        expense.append("author",username).append("body",body).append("permalink",permalink).append("tags",tags).append("comments",comments).append("date",new Date()).append("title",title)
                .append("description",description);
        expensesCollection.insertOne(expense);
        System.out.println("adding post ="+expense);

        return permalink;
    }




}
