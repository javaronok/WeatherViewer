
package weather.viewer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class StreamUtils {

  public static String readToString(final InputStream is) throws IOException {
    return readToString(is, CharsetUtils.defaultCharsetName);
  }

  public static String readToString(final InputStream is, final int maxSize) throws IOException {
    return readToString(is, CharsetUtils.defaultCharsetName, maxSize);
  }

  public static String readToString(final InputStream is, final String encoding) throws IOException {
    return readToString(is, encoding, -1);
  }

  public static String readToString(final InputStream is, final String encoding, final int maxSize) throws IOException {
    final char buf[] = new char[4096];
    try (BufferedReader in = new BufferedReader(new InputStreamReader(is, encoding))) {
      final StringBuilder sb = new StringBuilder();
      while (true) {
        final int c = in.read(buf);
        if (c < 0)
          break;
        sb.append(buf, 0, c);
        if (maxSize > 0 && sb.length() >= maxSize) {
          sb.setLength(maxSize);
          return sb.toString();
        }
      }
      return sb.toString();
    }
  }
}