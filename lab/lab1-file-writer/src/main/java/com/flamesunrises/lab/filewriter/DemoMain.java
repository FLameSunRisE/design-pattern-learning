package com.flamesunrises.lab.filewriter;

import com.flamesunrises.lab.filewriter.dto.User;
import com.flamesunrises.lab.filewriter.file.csv.CsvContentBuilder;
import com.flamesunrises.lab.filewriter.file.format.csv.DefaultCsvFormatConvert;
import com.flamesunrises.lab.filewriter.file.format.json.DefaultJsonFormatConverter;
import com.flamesunrises.lab.filewriter.file.writer.CsvWriterStrategy;
import com.flamesunrises.lab.filewriter.file.writer.CsvWriterStrategyBuilder;
import com.flamesunrises.lab.filewriter.file.writer.JsonWriterStrategy;
import com.flamesunrises.lab.filewriter.file.writer.JsonWriterStrategyBuilder;
import com.flamesunrises.lab.filewriter.serializer.UserDataDateSerializer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoMain {

  private static final Logger logger = LoggerFactory.getLogger(DemoMain.class);

  public static void main(String[] args) {
    jsonWriterStrategyBuilderDemo_default();
    //    csvWriterStrategyBuilderDemo_default();
    //    csvWriterStrategyBuilderDemo_remove_column();

    //        csvContentBuilderDemo_default();
    //        csvContentBuilderDemo_column_custom();
  }

  private static void jsonWriterStrategyBuilderDemo_default() {
    // 1. prepare data
    List<User> userList = getUserList();

    // 2. 產生csv builder
    // Step 1: 创建 CsvWriterStrategy 的建造者 JsonWriterStrategyBuilder
    JsonWriterStrategyBuilder jsonWriterStrategyBuilder = new JsonWriterStrategyBuilder(
        User.class).withFormatStrategy(
        new DefaultJsonFormatConverter(User.class, new UserDataDateSerializer()));

    // 3. 產生csv file content
    JsonWriterStrategy<User> jsonWriterStrategy = jsonWriterStrategyBuilder.build();
    jsonWriterStrategy.getContentData(userList);

    /* OUTPUT
      17:55:54.196 [main] INFO fridayBatch - csvContent : id,name,age,gender,email,phone
      1,John,30,Male,john@example.com,1234567890
      2,Mary,25,Female,mary@example.com,987653210
     */
  }

  private static void csvWriterStrategyBuilderDemo_default() {
    // 1. prepare data
    List<User> userList = getUserList();

    // 2. 產生csv builder
    CsvWriterStrategyBuilder<User> csvWriterStrategyBuilder = new CsvWriterStrategyBuilder<>(
        User.class)
        .withIncludeHeader(true)
        .withHeaderFields(null)
        .withColumnFields(null)
        .withFormatStrategy(new DefaultCsvFormatConvert(User.class));

    // 3. 產生csv file content
    CsvWriterStrategy<User> csvWriterStrategy = csvWriterStrategyBuilder.build();
    csvWriterStrategy.getContentData(userList);
    /* OUTPUT
     18:11:58.923 [main] INFO com.flamesunrises.lab.filewriter.file.writer.JsonWriterStrategy - getContentData : [{"id":1,"name":"John","age":30,"gender":"Male","email":"john@example.com","phone":1234567890},{"id":2,"name":"Mary","age":25,"gender":"Female","email":"mary@example.com","phone":987653210}]
     */
  }

  private static void csvWriterStrategyBuilderDemo_remove_column() {
    // 1. prepare data
    List<User> userList = getUserList();
    List<String> headerFields = Arrays.asList("姓名", "歲數"); // 客製化設定header欄位名稱
    List<String> columnFields = Arrays.asList("name", "age"); //移除id

    // 2. 產生csv builder
    CsvWriterStrategyBuilder<User> csvWriterStrategyBuilder = new CsvWriterStrategyBuilder<>(
        User.class)
        .withIncludeHeader(true)
        .withHeaderFields(headerFields)
        .withColumnFields(columnFields)
        .withFormatStrategy(
            new DefaultCsvFormatConvert(User.class, true, headerFields, columnFields));

    // 3. 產生csv file content
    CsvWriterStrategy<User> csvWriterStrategy = csvWriterStrategyBuilder.build();
    csvWriterStrategy.getContentData(userList);
    /* OUTPUT
      17:55:54.201 [main] INFO fridayBatch - csvContent : 姓名,歲數
      John,30
      Mary,25
     */
  }

  private static void csvContentBuilderDemo_default() {
    // 1. prepare data
    List<User> userList = getUserList();

    // 2. 產生csv builder
    CsvContentBuilder<User> csvBuilder = new CsvContentBuilder<>(User.class)
        .withIncludeHeader(true)
        .withHeaderFields(null)
        .withColumnFields(null)
        .withData(userList);

    // 3. 產生csv file content
    String csvContent = csvBuilder.generatorContent();
    logger.info("csvContent: {}", csvContent);
  }

  private static void csvContentBuilderDemo_column_custom() {
    // 1. prepare data
    List<User> userList = getUserList();
    List<String> customHeaderFieldsList = Arrays.asList("Name", "Age", "Gender", "Email", "Phone");
    //    List<String> customColumnFieldsList = Arrays
    //        .asList("id", "name", "age", "gender", "email", "phone");
    List<String> customColumnFieldsList = Arrays
        .asList("name", "age", "email", "phone");

    // 2. 產生csv builder
    CsvContentBuilder<User> csvBuilder = new CsvContentBuilder<>(User.class)
        .withIncludeHeader(true)
        .withHeaderFields(customHeaderFieldsList)
        .withColumnFields(customColumnFieldsList)
        .withData(userList);

    // 3. 產生csv file content
    String csvContent = csvBuilder.generatorContent();
    logger.info("csvContent: {}", csvContent);
  }

  private static List<User> getUserList() {
    List<User> userList = new ArrayList<>();
    userList.add(new User(1, "John", 30, "Male", "john@example.com", 1234567890));
    userList.add(new User(2, "Mary", 25, "Female", "mary@example.com", 987653210));
    return userList;
  }
}
