package ua.tor.incrementor.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.tor.incrementor.model.ParsedVacancy;
import ua.tor.incrementor.service.repository.IParsedVacancy;

/**
 * 
 * @author Alexander
 * @date 19.03.2018
 */
@Component
public class IncrementorService {

  private static final String SAMPLE_CSV_FILE = "./dump.csv";
  private final Integer DEFAULT_WEIGHT = 1;
  @Autowired
  private IParsedVacancy parsedVacancy;

  private Map<String, Integer> qntConter;
  private List<ParsedVacancy> listOfParsedVacancies;

  /**
   * Method for getting skill and it's quantity;
   * 
   * @param crawlerId requested param for getting batch;
   * @return map of skill and it's quantity;
   */
  public Map<String, Integer> getVacancies(ObjectId crawlerId) {
    listOfParsedVacancies = parsedVacancy.findByCrawlerId(crawlerId);
    return getCollection(listOfParsedVacancies);
  }

  private Map<String, Integer> getCollection(List<ParsedVacancy> lsOfVacancies) {
    qntConter = new HashMap<>();
    for (ParsedVacancy words : lsOfVacancies) {
      for (String word : words.getRowVacancy()) {
        if (qntConter.containsKey(word)) {
          qntConter.put(word, qntConter.get(word) + 1);
        } else {
          qntConter.put(word, DEFAULT_WEIGHT);
        }
      }
    }
    return sortMap(qntConter);
  }

  private Map<String, Integer> sortMap(Map<String, Integer> map) {
    Map<String, Integer> result =
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    writeToCsv(result);
    return result;
  }

  private void writeToCsv(Map<String, Integer> map) {
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
        CSVPrinter csvPrinter =
            new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Skill", "Quantity"));) {
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        csvPrinter.printRecord(entry.getKey(), entry.getValue());
      }
      csvPrinter.flush();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }
}