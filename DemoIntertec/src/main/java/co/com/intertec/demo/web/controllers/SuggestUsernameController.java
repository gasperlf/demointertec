package co.com.intertec.demo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.intertec.demo.web.model.services.SuggestUsername;
import co.com.intertec.demo.web.model.utils.Result;

@RestController
public class SuggestUsernameController {

  @Autowired
  private SuggestUsername suggestUsername;
  
  @RequestMapping(value = "/search/api/getsuggestusernames")
  public void getSuggestUsernames(){
    
    Result result = suggestUsername.checkUsername("Lewis ");
    System.out.println(result.getUsernames());
    
  }
}
