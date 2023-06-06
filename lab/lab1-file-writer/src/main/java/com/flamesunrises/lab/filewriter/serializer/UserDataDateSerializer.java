package com.flamesunrises.lab.filewriter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDataDateSerializer extends AbstractJsonDateSerializer {

  public UserDataDateSerializer() {
    super();
  }

  public UserDataDateSerializer(String dateFormat) {
    super(dateFormat);
  }

  @Override
  public void serialize(Date date, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
    // 判斷日期欄位的條件，例如根據欄位名稱或其他標識進行判斷
    String fieldName = jsonGenerator.getOutputContext().getCurrentName();
    if (isStartDateField(fieldName)) {
      // 使用自訂的日期格式處理
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
      String formattedDate = dateFormat1.format(date);
      jsonGenerator.writeString(formattedDate);
    } else if (isEndDateField(fieldName)) {
      // 使用另一種自訂的日期格式處理
      SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
      String formattedDate = dateFormat2.format(date);
      jsonGenerator.writeString(formattedDate);
    } else {
      // 預設日期格式處理
      jsonGenerator.writeObject(date);
    }
  }

  // 根據需要實作判斷日期欄位的邏輯
  private boolean isStartDateField(String fieldName) {
    return fieldName.equals("startDateTime");
  }

  private boolean isEndDateField(String fieldName) {
    return fieldName.equals("endDateTime");
  }
}