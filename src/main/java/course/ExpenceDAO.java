package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

public class ExpenceDAO {
    MongoCollection<Document> expenciesCollection;

    public ExpenceDAO(final MongoDatabase blogDatabase) {
        expenciesCollection = blogDatabase.getCollection("expencies");
    }


    public List<Document> findByDateDescending(int report_id,int limit) {

        List<Document> expencies = null;

        Bson sort = descending("created");

        expencies=expenciesCollection.find(eq("report_id",report_id)).sort(sort).limit(limit).into(new ArrayList<Document>());

        return expencies;
    }
}
