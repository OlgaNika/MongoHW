package restapp;

import model.Expence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenceRepository extends MongoRepository<Expence,String> {

    public Expence findByid(String id);
    public List<Expence> findByReportid(String reportid);
    public List<Expence> findAll();
    //Supports native JSON query string
    //@Query("{date:{$gte:new Date('?0')}}")
    //{date:{$gte:new Date('2017-08-09')}}
   // public List<Expence> findforMonth(String date);
    public List<Expence> findByType(String type);
}
