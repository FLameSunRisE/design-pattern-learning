package com.flamesunrises.lab.filewriter.file.format;

import java.util.List;

public interface IFormatStrategy<T> {

  String formatDataList(List<T> dataList);
}
