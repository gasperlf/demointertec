package co.com.intertec.demo.web.model.services;

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
public class SuggestUsernameService {

  @Autowired
  private SuggestUsernameDAO suggestUsernameDAO;

  // class variable
  final String letters = "abcdefghaijk_12345674890lmnopqrstuvwxyz";

  final java.util.Random rand = new java.util.Random();
  final Set<String> identifiers = new HashSet<>();

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

  private boolean existeUsername(String name) {

    List<String> usernames = suggestUsernameDAO.getUsernamesMock();
    boolean existe = false;
    for (String username : usernames) {
      if (username.equals(name)) {
        existe = true;
      }
    }
    return existe;
  }

  private ReponseValidateRestrictedWords validadRestrictWords(String name) {

    ReponseValidateRestrictedWords reponseValidateRestrictedWords = new ReponseValidateRestrictedWords();
    List<String> restriectedWords = suggestUsernameDAO.getRestrictedWordsMock();
    boolean existeRestrictedWord = false;
    for (String restrictedWord : restriectedWords) {
      if (name.contains(restrictedWord)) {
        name = name.replace(restrictedWord, "");
        existeRestrictedWord = true;
      }
    }
    reponseValidateRestrictedWords.setResponse(existeRestrictedWord);
    reponseValidateRestrictedWords.setUsername(name);

    return reponseValidateRestrictedWords;
  }

}
