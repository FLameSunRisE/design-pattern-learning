package com.flamesunrises.lab.filewriter.file.csv;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvContentBuilder<T> {

  private static final Logger logger = LoggerFactory.getLogger(CsvContentBuilder.class);

  private Class<T> dataClass;
  private boolean includeHeader;
  private List<String> headerFields;
  private List<String> columnFields;
  private List<T> dataList;

  public CsvContentBuilder(Class<T> dataClass) {
    this.dataClass = dataClass;
    this.includeHeader = false;
    this.headerFields = new ArrayList<>();
    this.columnFields = new ArrayList<>();
    this.dataList = new ArrayList<>();
  }

  public CsvContentBuilder<T> withIncludeHeader(boolean includeHeader) {
    this.includeHeader = includeHeader;
    return this;
  }

  public CsvContentBuilder<T> withHeaderFields(List<String> headerFields) {
    if ((headerFields == null || headerFields.isEmpty()) && includeHeader) {
      List<String> objectFields = getObjectFields(dataClass);
      this.headerFields = objectFields;
    } else {
      this.headerFields = headerFields;
    }
    return this;
  }

  public CsvContentBuilder<T> withColumnFields(List<String> columnFields) {
    if ((columnFields == null || columnFields.isEmpty())) {
      List<String> objectFields = getObjectFields(dataClass);
      this.columnFields = objectFields;
    } else {
      this.columnFields = columnFields;
    }
    return this;
  }

  public CsvContentBuilder<T> withData(List<T> dataList) {
    this.dataList = dataList;
    return this;
  }

  public String generatorContent() {
    StringBuilder csv = new StringBuilder();

    if (includeHeader) {
      appendFields(csv, headerFields);
      csv.append("\n");
    }

    for (T data : dataList) {
      List<String> fieldDataList = getObjectData(data);
      appendFields(csv, fieldDataList);
      csv.append("\n");
    }

    return csv.toString();
  }

  private void appendFields(StringBuilder csv, List<String> fields) {
    int size = fields.size();
    for (int i = 0; i < size; i++) {
      csv.append(fields.get(i));
      if (i != size - 1) {
        csv.append(",");
      }
    }
  }

  private List<String> getObjectFields(Class<?> clazz) {
    List<String> fieldList = new ArrayList<>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      fieldList.add(field.getName());
    }
    logger.debug("fields = {}", fieldList);
    return fieldList;
  }

  private List<String> getObjectData(T data) {
    List<String> fieldDataList = new ArrayList<>();
    Field[] fields = dataClass.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        if (columnFields.contains(field.getName())) {
          Object fieldValue = field.get(data);
          fieldDataList.add(fieldValue != null ? fieldValue.toString() : "");
        }
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Failed to access field value", e);
      }
    }
    return fieldDataList;
  }
}