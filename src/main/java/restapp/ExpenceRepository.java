package restapp;

import model.Expence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenceRepository extends MongoRepository<Expence,String> {
}
