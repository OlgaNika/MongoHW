package restapp;

import model.Expence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenceRepository extends MongoRepository<Expence,String> {

    public Expence findByid(String id);
    public List<Expence> findByReportId(String reportId);
    public List<Expence> findAll();
}
