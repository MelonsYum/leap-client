/*    */ package shadersmod.client;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import optifine.StrUtils;
/*    */ 
/*    */ 
/*    */ public class ShaderPackZip
/*    */   implements IShaderPack
/*    */ {
/*    */   protected File packFile;
/*    */   protected ZipFile packZipFile;
/*    */   
/*    */   public ShaderPackZip(String name, File file) {
/* 18 */     this.packFile = file;
/* 19 */     this.packZipFile = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 24 */     if (this.packZipFile != null) {
/*    */ 
/*    */       
/*    */       try {
/* 28 */         this.packZipFile.close();
/*    */       }
/* 30 */       catch (Exception exception) {}
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 35 */       this.packZipFile = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream getResourceAsStream(String resName) {
/*    */     try {
/* 43 */       if (this.packZipFile == null)
/*    */       {
/* 45 */         this.packZipFile = new ZipFile(this.packFile);
/*    */       }
/*    */       
/* 48 */       String excp = StrUtils.removePrefix(resName, "/");
/* 49 */       ZipEntry entry = this.packZipFile.getEntry(excp);
/* 50 */       return (entry == null) ? null : this.packZipFile.getInputStream(entry);
/*    */     }
/* 52 */     catch (Exception var4) {
/*    */       
/* 54 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasDirectory(String resName) {
/*    */     try {
/* 62 */       if (this.packZipFile == null)
/*    */       {
/* 64 */         this.packZipFile = new ZipFile(this.packFile);
/*    */       }
/*    */       
/* 67 */       String e = StrUtils.removePrefix(resName, "/");
/* 68 */       ZipEntry entry = this.packZipFile.getEntry(e);
/* 69 */       return (entry != null);
/*    */     }
/* 71 */     catch (IOException var4) {
/*    */       
/* 73 */       return false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 79 */     return this.packFile.getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderPackZip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */