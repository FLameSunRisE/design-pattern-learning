package com.flamesunrises.lab.filewriter;

import com.flamesunrises.lab.filewriter.dto.User;
import com.flamesunrises.lab.filewriter.file.csv.CsvContentBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoMain {

  public static void main(String[] args) {
    List<User> userList = getUserList();

    //    List<String> customHeaderFieldsList = Arrays.asList("Name", "Age", "Gender", "Email", "Phone");
    List<String> customHeaderFieldsList = Arrays.asList("Name", "Age", "Gender", "Email", "Phone");
    //    List<String> customColumnFieldsList = Arrays
    //        .asList("id", "name", "age", "gender", "email", "phone");
    List<String> customColumnFieldsList = Arrays
        .asList("name", "age", "email", "phone");

    CsvContentBuilder<User> csvBuilder = new CsvContentBuilder<>(User.class)
        .withIncludeHeader(true)
        //        .withHeaderFields(Arrays.asList())
        .withHeaderFields(customHeaderFieldsList)
        //        .withHeaderFields(Arrays.asList("Name", "Age", "Gender", "Email", "Phone"))
        .withColumnFields(customColumnFieldsList)
        //        .withColumnFields(Arrays.asList())
        .withData(userList);

    String csvContent = csvBuilder.generatorContent();
    System.out.println(csvContent);
  }

  private static List<User> getUserList() {
    List<User> userList = new ArrayList<>();
    userList.add(new User(1, "John", 30, "Male", "john@example.com", 1234567890));
    userList.add(new User(2, "Mary", 25, "Female", "mary@example.com", 987653210));
    return userList;
  }

}
