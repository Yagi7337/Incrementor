package ua.tor.incrementor.model;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "parsed_vacancy")
public class ParsedVacancy {
  @Field("raw_vacancy")
  private List<String> rowVacancy;
  @Field("crawler_id")
  private ObjectId crawlerId;

  public List<String> getRowVacancy() {
    return rowVacancy;
  }

  public void setRowVacancy(List<String> rowVacancy) {
    this.rowVacancy = rowVacancy;
  }

  public ObjectId getCrawlerId() {
    return crawlerId;
  }

  public void setCrawlerId(ObjectId crawlerId) {
    this.crawlerId = crawlerId;
  }

  @Override
  public String toString() {
    return "ParsedVacancy [rowVacancy=" + rowVacancy + ", crawlerId=" + crawlerId + "]";
  }
}
