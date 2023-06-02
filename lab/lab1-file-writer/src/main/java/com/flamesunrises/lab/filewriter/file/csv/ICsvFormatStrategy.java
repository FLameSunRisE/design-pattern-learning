package com.flamesunrises.lab.filewriter.file.csv;

import java.util.List;

public interface ICsvFormatStrategy {
  String formatHeader(List<String> headerFields);
  String formatColumnFields(List<String> columnFields);
  String formatData(Object data);
}
