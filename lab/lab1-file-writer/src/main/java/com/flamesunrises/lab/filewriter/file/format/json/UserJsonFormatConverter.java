package com.flamesunrises.lab.filewriter.file.format.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flamesunrises.lab.filewriter.dto.User;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserJsonFormatConverter.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/06
 * @see AbstractJsonFormatStrategy
 */
public class UserJsonFormatConverter<T> extends AbstractJsonFormatStrategy<T> {

  private static final Logger logger = LoggerFactory.getLogger("fridayBatch");
  public static final String DEMO_NAME_FILED = "DEMO";

  public UserJsonFormatConverter(Class<T> dataClass) {
    super(dataClass);
  }

  public UserJsonFormatConverter(Class<T> dataClass, JsonSerializer dateSerializer) {
    super(dataClass, dateSerializer);
  }

  public String formatData(T data) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Map<String, Object> jsonMap = convertToMap(data);
      return objectMapper.writeValueAsString(jsonMap);
    } catch (JsonProcessingException e) {
      logger.error("JsonProcess fail" + e.getMessage(), e);
      return "";
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return "";
    }
  }

  @Override
  public String formatDataList(List<T> dataList) {
    try {
      return this.convertToJson(dataList);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage(), e);
    }
    return "";
  }

  @Override
  public String convertToJson(List<T> dataList)
      throws JsonProcessingException {
    List<Map<String, Object>> jsonList = new ArrayList<>();
    for (T data : dataList) {
      Map<String, Object> jsonMap = convertToMap(data);
      jsonList.add(jsonMap);
    }
    return objectMapper.writeValueAsString(jsonList);
  }

  @Override
  public Map<String, Object> convertToMap(T obj) {
    if (!(obj instanceof User)) {
      throw new IllegalArgumentException("Invalid object type. Expected User.");
    }
    User data = (User) obj;
    Map<String, Object> jsonMap = new LinkedHashMap<>();
    jsonMap.put("demo_name", DEMO_NAME_FILED);
    jsonMap.put("name", data.getName());
    jsonMap.put("age", data.getAge() + "歲數");
    jsonMap.put("email", data.getEmail());
    logger.debug("User data :{}", data.toString());
    return jsonMap;
  }
}