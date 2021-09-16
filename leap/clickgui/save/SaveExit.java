/*    */ package leap.clickgui.save;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import leap.Client;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaveExit
/*    */ {
/*    */   private File dir;
/*    */   private File dataFile;
/*    */   public int currentTab;
/* 24 */   Module.Category category = Module.Category.values()[this.currentTab];
/* 25 */   List<Module> modules = Client.getModulesByCategory(this.category);
/*    */ 
/*    */   
/*    */   public SaveExit() {
/* 29 */     this.dir = new File((Minecraft.getMinecraft()).mcDataDir, Client.name);
/* 30 */     if (!this.dir.exists()) {
/* 31 */       this.dir.mkdir();
/*    */     }
/* 33 */     this.dataFile = new File(this.dir, "config.txt");
/* 34 */     if (!this.dataFile.exists()) {
/*    */       
/* 36 */       try { this.dataFile.createNewFile(); }
/* 37 */       catch (IOException e) { e.printStackTrace(); }
/*    */     
/*    */     }
/* 40 */     save();
/*    */   }
/*    */   
/*    */   public void save() {
/* 44 */     ArrayList<String> toSave = new ArrayList<>();
/*    */ 
/*    */     
/* 47 */     for (Module mod : Client.modules) {
/* 48 */       toSave.add("MOD:" + mod.name + ":" + mod.toggled + ":" + mod.getKey());
/*    */     }
/*    */ 
/*    */     
/* 52 */     for (Module mod : Client.modules) {
/*    */       
/* 54 */       for (Setting setting : mod.settings) {
/*    */         
/* 56 */         if (setting instanceof BooleanSetting) {
/* 57 */           BooleanSetting bool = (BooleanSetting)setting;
/* 58 */           toSave.add("SET:" + mod.name + ":" + setting.name + ":" + bool.isEnabled());
/*    */         } 
/*    */         
/* 61 */         if (setting instanceof NumberSetting) {
/* 62 */           NumberSetting numb = (NumberSetting)setting;
/* 63 */           toSave.add("SET:" + mod.name + ":" + setting.name + ":" + numb.getValue());
/*    */         } 
/*    */         
/* 66 */         if (setting instanceof ModeSetting) {
/* 67 */           ModeSetting mode = (ModeSetting)setting;
/* 68 */           toSave.add("SET:" + mod.name + ":" + setting.name + ":" + mode.getMode());
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 80 */       PrintWriter pw = new PrintWriter(this.dataFile);
/* 81 */       for (String str : toSave) {
/* 82 */         pw.println(str);
/*    */       }
/* 84 */       pw.close();
/* 85 */     } catch (FileNotFoundException e) {
/* 86 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\clickgui\save\SaveExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */