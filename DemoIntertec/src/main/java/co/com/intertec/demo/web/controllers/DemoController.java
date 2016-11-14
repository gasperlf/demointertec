package co.com.intertec.demo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String printWelcome(ModelMap model) {
      return "demo";
  }

}
