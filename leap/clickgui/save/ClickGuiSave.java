/*    */ package leap.clickgui.save;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Paths;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ 
/*    */ public class ClickGuiSave {
/*    */   public ClickGuiSave() {
/*    */     try {
/* 11 */       clickGuiSave();
/*    */     }
/* 13 */     catch (IOException e) {
/* 14 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   public static final String fileName = "leap/";
/* 18 */   String mainName = "clickGui/";
/*    */   
/*    */   public void clickGuiSave() throws IOException {
/* 21 */     if (!Files.exists(Paths.get("leap/", new String[0]), new java.nio.file.LinkOption[0])) {
/* 22 */       Files.createDirectories(Paths.get("leap/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
/*    */     }
/* 24 */     if (!Files.exists(Paths.get("leap/" + this.mainName, new String[0]), new java.nio.file.LinkOption[0])) {
/* 25 */       Files.createDirectories(Paths.get("leap/" + this.mainName, new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
/*    */     }
/*    */   }
/*    */   
/*    */   public void registerFiles(String location, String name) throws IOException {
/* 30 */     if (!Files.exists(Paths.get("leap/" + location + name + ".json", new String[0]), new java.nio.file.LinkOption[0])) {
/* 31 */       Files.createFile(Paths.get("leap/" + location + name + ".json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
/*    */     } else {
/*    */       
/* 34 */       File file = new File("leap/" + location + name + ".json");
/*    */       
/* 36 */       file.delete();
/*    */       
/* 38 */       Files.createFile(Paths.get("leap/" + location + name + ".json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\clickgui\save\ClickGuiSave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */