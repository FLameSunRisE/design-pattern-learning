package com.flamesunrises.lab.filewriter.file.format.csv;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractCsvFormatStrategy.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 * @see ICsvFormatStrategy
 */
public abstract class AbstractCsvFormatStrategy<T> implements ICsvFormatStrategy<T> {

  private static final Logger logger = LoggerFactory.getLogger(AbstractCsvFormatStrategy.class);

  private Class<T> dataClass;
  private boolean isIncludeHeader;
  private List<String> headerFields;
  private List<String> columnFields;

  protected AbstractCsvFormatStrategy(Class<T> dataClass) {
    this.dataClass = dataClass;
    this.isIncludeHeader = true;
    this.headerFields = null;
    this.columnFields = null;
  }

  public void setDataClass(Class<T> dataClass) {
    this.dataClass = dataClass;
  }

  public void setIncludeHeader(boolean isIncludeHeader) {
    this.isIncludeHeader = isIncludeHeader;
  }

  public void setHeaderFields(List<String> headerFields) {
    this.headerFields = headerFields;
  }

  public void setColumnFields(List<String> columnFields) {
    this.columnFields = columnFields;
  }

  public Class<T> getDataClass() {
    return dataClass;
  }

  public boolean isIncludeHeader() {
    return isIncludeHeader;
  }

  public List<String> getHeaderFields() {
    return headerFields;
  }

  public List<String> getColumnFields() {
    return columnFields;
  }

  @Override
  public String formatHeader(List<String> headerFields) {
    return String.join(",", headerFields);
  }

  @Override
  public String formatColumnFields(List<String> columnFields) {
    return String.join(",", columnFields);
  }

  @Override
  public String formatDataList(List<T> dataList) {
    StringBuilder csv = new StringBuilder();

    if (this.isIncludeHeader) {
      String formatHeader = formatHeader(this.headerFields);
      csv.append(formatHeader);
      csv.append("\n");
    }

    for (T data : dataList) {
      String formatDataStr = formatData(data);
      csv.append(formatDataStr);
      csv.append("\n");
    }

    return csv.toString();
  }

  public String formatData(T data) {
    StringBuilder sb = new StringBuilder();
    List<String> columnFields = getColumnFields();
    if (columnFields == null || columnFields.isEmpty()) {
      columnFields = getObjectFields(data.getClass());
    }
    for (String field : columnFields) {
      try {
        Field objField = data.getClass().getDeclaredField(field);
        objField.setAccessible(true);
        Object fieldValue = objField.get(data);
        sb.append(fieldValue).append(",");
      } catch (NoSuchFieldException | IllegalAccessException e) {
        // 处理字段不存在或无法访问的异常
        logger.error(e.getMessage(), e);
      }
    }
    // 移除最后一个逗号
    sb.setLength(sb.length() - 1);
    return sb.toString();
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


  public static List<String> getObjectFields(Class<?> clazz) {
    List<String> fieldList = new ArrayList<>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      fieldList.add(field.getName());
    }
    return fieldList;
  }

}

