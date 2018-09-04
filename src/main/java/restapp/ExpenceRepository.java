package restapp;

import model.Expence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ExpenceRepository extends MongoRepository<Expence,String> {

    public Expence findByid(String id);
    public List<Expence> findByReportid(String reportid);
    public List<Expence> findAll();
    //Supports native JSON query string
    //{date:{$gte:new Date('2017-08-09')}}
    //db.expence.find({ "$expr": { "$eq": [{ "$month": "$date" }, 8] } })
    @Query("{ '$expr': { '$eq': [{ '$month': '$date' }, ?0 ] } }")
    public List<Expence> findforMonth(int month);
    public List<Expence> findByType(String type);
    @Query("{ 'type':'?0','$expr': { '$eq': [{ '$month': '$date' }, ?1 ] } }")
    public List<Expence> findByTypeForMonth(String type,int month);

}
