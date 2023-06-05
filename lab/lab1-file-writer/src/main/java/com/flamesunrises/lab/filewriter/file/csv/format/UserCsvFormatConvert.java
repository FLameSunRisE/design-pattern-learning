package com.flamesunrises.lab.filewriter.file.csv.format;

import com.flamesunrises.lab.filewriter.dto.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserCsvFormatConvert.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 * @see AbstractCsvFormatStrategy
 */
public class UserCsvFormatConvert<T> extends AbstractCsvFormatStrategy<T> {

  public UserCsvFormatConvert(Class<T> dataClass, boolean isIncludeHeader,
      List<String> headerFields,
      List<String> columnFields) {
    super(dataClass);
    super.setIncludeHeader(isIncludeHeader);
    super.setHeaderFields(headerFields);
    super.setColumnFields(columnFields);
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
  public String formatData(T data) {
    User fakeData = (User) data;
    List<String> columnFields = super.getColumnFields();
    if (columnFields == null || columnFields.isEmpty()) {
      columnFields = getObjectFields(data.getClass());
    }

    Map<String, String> fieldMapping = getFieldMapping(fakeData);
    List<String> fieldValues = new ArrayList<>();
    for (String field : columnFields) {
      String fieldValue = fieldMapping.get(field);
      if (fieldValue == null) {
        // 处理字段不存在的情况
        fieldValue = ""; // 或者抛出异常等处理方式
      }
      fieldValues.add(fieldValue);
    }
    return String.join(",", fieldValues);
  }

  @Override
  public String formatDataList(List<T> dataList) {
    StringBuilder csv = new StringBuilder();
    boolean includeHeader = super.isIncludeHeader();
    List<String> headerFields = super.getHeaderFields();

    if (includeHeader) {
      String formatHeader = formatHeader(headerFields);
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

  private Map<String, String> getFieldMapping(User fakeData) {
    Map<String, String> fieldMapping = new HashMap<>();
    fieldMapping.put("id", String.valueOf(fakeData.getId()));
    fieldMapping.put("name", fakeData.getName());
    fieldMapping.put("age", String.valueOf(fakeData.getAge() + "歲"));
    // 可以根据需要添加其他字段的映射关系
    return fieldMapping;
  }
}
