package com.flamesunrises.lab.filewriter.file.format.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.flamesunrises.lab.filewriter.serializer.CustomJsonDateSerializer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractJsonFormatStrategy<T> implements IJsonFormatStrategy<T> {

  private static final Logger logger = LoggerFactory.getLogger("fridayBatch");

  protected Class<T> dataClass;
  private JsonSerializer dateSerializer;
  protected ObjectMapper objectMapper;

  protected AbstractJsonFormatStrategy(Class<T> dataClass) {
    this.dataClass = dataClass;
    this.dateSerializer = new CustomJsonDateSerializer();
    configureObjectMapper(dateSerializer);
  }


  protected AbstractJsonFormatStrategy(Class<T> dataClass,JsonSerializer dateSerializer) {
    this.dataClass = dataClass;
    this.dateSerializer = dateSerializer;
    configureObjectMapper(dateSerializer);
  }

  private void configureObjectMapper(JsonSerializer dateSerializer) {
    // 設置自訂的日期格式處理器
    this.objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(Date.class, dateSerializer);
    objectMapper.registerModule(module);
  }

  public abstract String formatData(T data);

  public String formatDataList(List<T> dataList) {
    StringBuilder json = new StringBuilder();
    json.append("[");

    for (int i = 0; i < dataList.size(); i++) {
      T data = dataList.get(i);
      String jsonData = formatData(data);
      json.append(jsonData);

      if (i < dataList.size() - 1) {
        json.append(",");
      }
    }

    json.append("]");
    return json.toString();
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
  public Map<String, Object> convertToMap(T data) {
    Map<String, Object> jsonMap = new LinkedHashMap<>();

    // 使用反射獲取物件的屬性和值
    Field[] fields = data.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        String fieldName = field.getName();
        Object fieldValue = field.get(data);
        jsonMap.put(fieldName, fieldValue);
      } catch (IllegalAccessException e) {
        logger.error("Error processing field access: " + e.getMessage(), e);
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return jsonMap;
  }
}