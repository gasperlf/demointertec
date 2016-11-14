package co.com.intertec.demo.web.model.daos;

import java.util.List;

public interface SuggestUsernameDAO {

  List<String> getRestrictedWordsMock();
  
  List<String> getUsernamesMock();
  
}
