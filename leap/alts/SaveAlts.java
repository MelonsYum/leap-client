/*    */ package leap.alts;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import leap.Client;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaveAlts
/*    */ {
/*    */   private File dir;
/*    */   private File dataFile;
/*    */   public int currentTab;
/* 24 */   public static ArrayList<String> toSave = new ArrayList<>();
/*    */   
/* 26 */   Module.Category category = Module.Category.values()[this.currentTab];
/* 27 */   List<Module> modules = Client.getModulesByCategory(this.category);
/*    */ 
/*    */   
/*    */   public SaveAlts() {
/* 31 */     this.dir = new File((Minecraft.getMinecraft()).mcDataDir, Client.name);
/* 32 */     if (!this.dir.exists()) {
/* 33 */       this.dir.mkdir();
/*    */     }
/* 35 */     this.dataFile = new File(this.dir, "alts.txt");
/* 36 */     if (!this.dataFile.exists()) {
/*    */       
/* 38 */       try { this.dataFile.createNewFile(); }
/* 39 */       catch (IOException e) { e.printStackTrace(); }
/*    */     
/*    */     }
/* 42 */     save();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void save() {
/*    */     try {
/* 56 */       PrintWriter pw = new PrintWriter(this.dataFile);
/* 57 */       for (String str : toSave) {
/* 58 */         pw.println(str);
/*    */       }
/* 60 */       pw.close();
/* 61 */     } catch (FileNotFoundException e) {
/* 62 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\SaveAlts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */