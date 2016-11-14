package co.com.intertec.demo.web.model.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SuggestUsernameDAOImpl implements SuggestUsernameDAO{

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
