/*    */ package shadersmod.client;
/*    */ 
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStream;
/*    */ import optifine.StrUtils;
/*    */ 
/*    */ 
/*    */ public class ShaderPackFolder
/*    */   implements IShaderPack
/*    */ {
/*    */   protected File packFile;
/*    */   
/*    */   public ShaderPackFolder(String name, File file) {
/* 16 */     this.packFile = file;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {}
/*    */ 
/*    */   
/*    */   public InputStream getResourceAsStream(String resName) {
/*    */     try {
/* 25 */       String excp = StrUtils.removePrefixSuffix(resName, "/", "/");
/* 26 */       File resFile = new File(this.packFile, excp);
/* 27 */       return !resFile.exists() ? null : new BufferedInputStream(new FileInputStream(resFile));
/*    */     }
/* 29 */     catch (Exception var4) {
/*    */       
/* 31 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasDirectory(String name) {
/* 37 */     File resFile = new File(this.packFile, name.substring(1));
/* 38 */     return !resFile.exists() ? false : resFile.isDirectory();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 43 */     return this.packFile.getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderPackFolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */