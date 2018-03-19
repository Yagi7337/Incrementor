package ua.tor.incrementor.api;

import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.tor.incrementor.service.IncrementorService;

@RestController
public class Incrementor {

  @Autowired
  private IncrementorService incrementorService;

  @RequestMapping(method = RequestMethod.GET, path = "/get")
  public Map<String, Integer> getQnt(@RequestParam(value = "crawler_id") ObjectId crawlerId) {
    return incrementorService.getVacancies(crawlerId);
  }
}
