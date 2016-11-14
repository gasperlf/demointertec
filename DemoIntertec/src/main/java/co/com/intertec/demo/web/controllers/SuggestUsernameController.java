package co.com.intertec.demo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.intertec.demo.web.model.services.SuggestUsernameService;
import co.com.intertec.demo.web.model.utils.Result;

@RestController
public class SuggestUsernameController {

  @Autowired
  private SuggestUsernameService suggestUsernameService;
  
  @RequestMapping(value = "/search/api/getsuggestusernames")
  public void getSuggestUsernames(){
    
    Result result = suggestUsernameService.checkUsername("Lewis ");
    System.out.println(result.getUsernames());
    
  }
}
