package org.infinispan.protostream.annotations.impl;

import org.infinispan.protostream.RawProtoStreamReader;
import org.infinispan.protostream.RawProtoStreamWriter;
import org.infinispan.protostream.impl.BaseMarshallerDelegate;
import org.infinispan.protostream.impl.ByteArrayOutputStreamEx;
import org.infinispan.protostream.impl.RawProtoStreamWriterImpl;

import java.io.IOException;

/**
 * Base class for generated marshallers. Provides some handy helper methods.
 *
 * @author anistor@redhat.com
 * @since 3.0
 */
public class GeneratedMarshallerBase {

   protected final <T> T readMessage(BaseMarshallerDelegate<T> marshallerDelegate, RawProtoStreamReader in) throws IOException {
      return marshallerDelegate.unmarshall(null, null, in);
   }

   protected final <T> void writeMessage(BaseMarshallerDelegate<T> marshallerDelegate, RawProtoStreamWriter out, T message) throws IOException {
      if (message == null) {
         throw new IllegalArgumentException("Object to marshall cannot be null");
      }
      marshallerDelegate.marshall(null, message, null, out);
      out.flush();
   }

   protected final <T> void writeNestedMessage(BaseMarshallerDelegate<T> marshallerDelegate, RawProtoStreamWriter out, int fieldNumber, T message) throws IOException {
      ByteArrayOutputStreamEx baos = new ByteArrayOutputStreamEx();
      RawProtoStreamWriter nested = RawProtoStreamWriterImpl.newInstance(baos);
      writeMessage(marshallerDelegate, nested, message);
      out.writeBytes(fieldNumber, baos.getByteBuffer());
   }
}
