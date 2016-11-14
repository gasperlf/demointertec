package co.com.intertec.demo.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import co.com.intertec.demo.web.model.services.SuggestUsernameService;
import co.com.intertec.demo.web.model.utils.Result;

public class TestDemo {

  private SuggestUsernameService suggestUsername; 
  private Result result;
  
  @Before
  public void setup(){
    suggestUsername = new SuggestUsernameService();
    result = suggestUsername.checkUsername("Lewiscannabis");
  }
  
  @Test(timeout=0)
  public void isObjectNotnull(){  
    assertNotNull("El objeto no es null",suggestUsername);
  }
  
  @Test
  public void validateResultUsernames(){
    
   
   assertNotNull("El objeto Result no es null",result);
   assertNotNull("El objeto Lista de Usernames no es null",result.getUsernames());   
  }
  
  
  @Test
  public void testListUSernames(){
    
    assertTrue("List usernames is not empty ",result.getUsernames().size()>0);
    assertTrue("It's not contains username Lewis ", !result.getUsernames().contains("Lewis"));    
    assertTrue("It's not contains username less tna 6 characters ", result.getUsernames().get(0).length() >= 6);    
  }
  
}
