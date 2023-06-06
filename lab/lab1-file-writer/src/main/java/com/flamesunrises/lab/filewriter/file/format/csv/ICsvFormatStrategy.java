package com.flamesunrises.lab.filewriter.file.format.csv;

import com.flamesunrises.lab.filewriter.file.format.IFormatStrategy;
import java.util.List;

public interface ICsvFormatStrategy<T> extends IFormatStrategy<T> {

  String formatHeader(List<String> headerFields);

  String formatColumnFields(List<String> columnFields);

  String formatData(T data);

  String formatDataList(List<T> dataList);

}