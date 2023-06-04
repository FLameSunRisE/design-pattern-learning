package com.flamesunrises.lab.filewriter;

import com.flamesunrises.lab.filewriter.dto.User;
import com.flamesunrises.lab.filewriter.file.csv.CsvContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoMain {

    private static final Logger logger = LoggerFactory.getLogger(DemoMain.class);

    public static void main(String[] args) {
        csvContentBuilderDemo_default();
        csvContentBuilderDemo_column_custom();
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
