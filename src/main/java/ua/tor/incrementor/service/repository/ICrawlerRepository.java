package ua.tor.incrementor.service.repository;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tor.incrementor.model.Crawler;

/**
 * 
 * @author alex
 *
 */
@Repository
public interface ICrawlerRepository extends MongoRepository<Crawler, ObjectId> {


	Crawler findOneById(ObjectId id);
}
