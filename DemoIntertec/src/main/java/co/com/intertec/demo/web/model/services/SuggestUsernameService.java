package co.com.intertec.demo.web.model.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.intertec.demo.web.model.daos.SuggestUsernameDAO;
import co.com.intertec.demo.web.model.utils.ReponseValidateRestrictedWords;
import co.com.intertec.demo.web.model.utils.Result;

@Service
@Transactional
public class SuggestUsernameService implements SuggestUsername{

  @Autowired
  private SuggestUsernameDAO suggestUsernameDAO;

  // class variable
  final String letters = "abcdefghaijk_12345674890lmnopqrstuvwxyz";

  final java.util.Random rand = new java.util.Random();
  final Set<String> identifiers = new HashSet<>();

  /**
   * Genetat usernames
   * @param cantidad total of letter to add to username
   * @return 
   */
  protected String generadorIdentifier(int cantidad) {
    StringBuilder builder = new StringBuilder();
    while (builder.toString().length() == 0) {
      int length = rand.nextInt(3) + cantidad;
      for (int i = 0; i < length; i++) {
        builder.append(letters.charAt(rand.nextInt(letters.length())));
      }
      if (identifiers.contains(builder.toString())) {
        builder = new StringBuilder();
      }
    }
    return builder.toString();
  }

  /**
   * this is main method called from controller
   */
  public Result checkUsername(String username) {

    Result result = new Result();
    // limpio el username apra que no contenga niniguna palabra prohibida
    ReponseValidateRestrictedWords reponseValidateRestrictedWords = validadRestrictWords(username.replaceAll("\\s*", ""));

    boolean existeUsername;
    // existe la mala palabra
    if (reponseValidateRestrictedWords.getResponse()) {
      existeUsername = existeUsername(reponseValidateRestrictedWords.getUsername());
      result.setValidate(existeUsername);
      // creo los nuevos username, crear la logica
      result = generadorUsernames(result, reponseValidateRestrictedWords.getUsername(), 14);
    } else {
      // validado que no exista
      existeUsername = existeUsername(reponseValidateRestrictedWords.getUsername());
      if (existeUsername) {
        // creo los nuevos username, crear la logica
        result = generadorUsernames(result, username, 14);
      } else {
        result.setValidate(existeUsername);
        result = generadorUsernames(result, username, 7);
      }
    }
    Collections.sort(result.getUsernames());
    return result;
  }
  
  /**
   * this method has the responsability to generate the unique username and suggest username depending on rules 
   * @param object to complement ot reponse
   * @param username username get in to user
   * @param cantidad how much username need to generate
   * @return result response of usernames
   */
  protected Result generadorUsernames(Result result, String username, int cantidad) {
    int n = 0;
    int cantidadCaracteres = 0;
    if (username.length() < 6) {
      cantidadCaracteres = (6 - username.length()) + 1;
    } else {
      cantidadCaracteres = 4;
    }
    while (n < cantidad) {
      String usernameAlternative = username + generadorIdentifier(cantidadCaracteres);
      ReponseValidateRestrictedWords restrictedWords = validadRestrictWords(usernameAlternative);
      if (restrictedWords.getResponse()) {
        username = restrictedWords.getUsername();
      }
      if (!existeUsername(usernameAlternative)) {
        result.getUsernames().add(usernameAlternative);
        n++;
      }
      usernameAlternative = null;
    }
    return result;
  }
  
  /**
   * This method valid that not iexist username in Mock List, or in other case this method would replace call database statement
   * @param name username to validate
   * @return return true or false
   */
  private boolean existeUsername(String name) {

    List<String> usernames = getUsernamesMock();
    boolean existe = false;
    for (String username : usernames) {
      if (username.equals(name)) {
        existe = true;
      }
    }
    return existe;
  }

  /**
   * This method valid that not exits the username contains restricted words in Mock list or in other case this method would replace call database statement
   * @param name Username to validate
   * @return Object that contain answer if usernames is contains restricted word or not
   */
  private ReponseValidateRestrictedWords validadRestrictWords(String name) {

    ReponseValidateRestrictedWords reponseValidateRestrictedWords = new ReponseValidateRestrictedWords();
    List<String> restriectedWords = getRestrictedWordsMock();
    boolean existeRestrictedWord = false;
    for (String restrictedWord : restriectedWords) {
      if (name.toUpperCase().contains(restrictedWord.toUpperCase())) {
        name = name.replace(restrictedWord, "");
        existeRestrictedWord = true;
      }
    }
    reponseValidateRestrictedWords.setResponse(existeRestrictedWord);
    reponseValidateRestrictedWords.setUsername(name);

    return reponseValidateRestrictedWords;
  }
  
  /**
   * this method return List of restricted words
   * @return List
   */
  public List<String> getRestrictedWordsMock() {

    List<String> restrictedWords = new ArrayList<>();

    restrictedWords.add("cannabis");
    restrictedWords.add("abuse");
    restrictedWords.add("crack");
    restrictedWords.add("damn");
    restrictedWords.add("drunk");
    restrictedWords.add("grass");
    restrictedWords.add("Fuck");
    return restrictedWords;
}
  /**
   * this method return list usernames mock
   * @return List
   */
  public List<String> getUsernamesMock() {

    List<String> usernames = new ArrayList<>();

    usernames.add("gasper_lf");
    usernames.add("gasper124");
    usernames.add("gasper_lf12");
    usernames.add("pepito");
    usernames.add("carlos32");
    usernames.add("ligia124");
    usernames.add("Lewis");

    return usernames;
} 


}
