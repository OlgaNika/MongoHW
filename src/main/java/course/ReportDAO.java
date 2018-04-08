package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

public class ReportDAO {

    MongoCollection<Document> reportsCollection;

    public ReportDAO(final MongoDatabase blogDatabase) {
        reportsCollection = blogDatabase.getCollection("reports");
    }

    public List<Document> findByDateDescending(int limit) {

         List<Document> reports = null;

        Bson sort = descending("date");

        reports=reportsCollection.find().sort(sort).limit(limit).into(new ArrayList<Document>());

        //System.out.println(reports);

        return reports;
    }
}
