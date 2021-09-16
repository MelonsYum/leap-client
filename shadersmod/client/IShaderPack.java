package shadersmod.client;

import java.io.InputStream;

public interface IShaderPack {
  String getName();
  
  InputStream getResourceAsStream(String paramString);
  
  boolean hasDirectory(String paramString);
  
  void close();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\IShaderPack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */