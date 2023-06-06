package com.flamesunrises.lab.filewriter.file.format.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flamesunrises.lab.filewriter.file.format.IFormatStrategy;
import java.util.List;
import java.util.Map;

/**
 * IJsonFormatStrategy.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 */
public interface IJsonFormatStrategy<T> extends IFormatStrategy<T> {

  String formatData(T data);

  String formatDataList(List<T> dataList);

  Map<String, Object> convertToMap(T data);

  String convertToJson(List<T> dataList) throws JsonProcessingException;
}
