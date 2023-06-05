package com.flamesunrises.lab.filewriter.file.writer;

import java.util.List;

public interface IWriterStrategy<T> {
  void writeData(String filePath, List<T> dataList);
}