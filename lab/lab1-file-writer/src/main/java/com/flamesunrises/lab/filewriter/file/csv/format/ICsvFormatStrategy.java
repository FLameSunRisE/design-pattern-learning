package com.flamesunrises.lab.filewriter.file.csv.format;

import java.util.List;

public interface ICsvFormatStrategy<T> {

  String formatHeader(List<String> headerFields);

  String formatColumnFields(List<String> columnFields);

  String formatData(T data);

  String formatDataList(List<T> dataList);

}