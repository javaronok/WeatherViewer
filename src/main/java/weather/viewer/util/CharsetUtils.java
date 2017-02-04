
package weather.viewer.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class CharsetUtils {
  public final static String defaultCharsetName;
  public final static Charset defaultCharset;
  static {
    defaultCharsetName = System.getProperty("file.encoding");
    defaultCharset = Charset.forName(defaultCharsetName);
  }

  private CharsetUtils() { }

  public static byte[] convert(String data) {
    return convert(data, defaultCharset);
  }

  public static byte[] convert(String data, Charset charset) {
    return charset.encode(data).array();
  }

  public static String convert(byte[] data) {
    return convert(data, defaultCharset);
  }

  public static String convert(byte[] data, Charset charset) {
    return charset.decode(ByteBuffer.wrap(data)).toString();
  }
}
