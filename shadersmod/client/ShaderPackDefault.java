/*    */ package shadersmod.client;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class ShaderPackDefault
/*    */   implements IShaderPack
/*    */ {
/*    */   public void close() {}
/*    */   
/*    */   public InputStream getResourceAsStream(String resName) {
/* 11 */     return ShaderPackDefault.class.getResourceAsStream(resName);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 16 */     return Shaders.packNameDefault;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasDirectory(String name) {
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderPackDefault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */