package ua.tor.incrementor.service.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.tor.incrementor.model.ParsedVacancy;

public interface IParsedVacancy extends MongoRepository<ParsedVacancy, ObjectId> {

  List<ParsedVacancy> findByCrawlerId(ObjectId id);

}
