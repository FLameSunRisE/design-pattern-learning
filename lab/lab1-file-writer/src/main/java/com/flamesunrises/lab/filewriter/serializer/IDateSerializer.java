package com.flamesunrises.lab.filewriter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Date;

public interface IDateSerializer {

  void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException;

}
