package co.com.intertec.demo.web.model.utils;

import java.util.ArrayList;
import java.util.List;

public class Result {

  private Boolean validate;
  
  private List<String> usernames;

  public Result() {
    this.usernames = new ArrayList<>();
  }
  
  public Boolean getValidate() {
      return validate;
  }

  public void setValidate(Boolean validate) {
      this.validate = validate;
  }

  public List<String> getUsernames() {
      return usernames;
  }

  public void setUsernames(List<String> usernames) {
      this.usernames = usernames;
  }
  
}
