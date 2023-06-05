package com.flamesunrises.lab.filewriter.file;

public interface IFileWriterStrategy {
  void writeData(String filePath, String content);
}