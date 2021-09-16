/*    */ package leap.clickgui.save;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ public class SaveLoad
/*    */ {
/*    */   private File dir;
/*    */   private File dataFile;
/* 24 */   public int currentTab = (Module.Category.values()).length - 1;
/*    */   
/* 26 */   Module.Category category = Module.Category.values()[this.currentTab];
/* 27 */   List<Module> modules = Client.getModulesByCategory(this.category);
/*    */   
/*    */   public SaveLoad() {
/* 30 */     this.dir = new File((Minecraft.getMinecraft()).mcDataDir, Client.name);
/* 31 */     if (!this.dir.exists()) {
/* 32 */       this.dir.mkdir();
/*    */     }
/* 34 */     this.dataFile = new File(this.dir, "config.txt");
/* 35 */     if (!this.dataFile.exists()) {
/*    */       
/* 37 */       try { this.dataFile.createNewFile(); }
/* 38 */       catch (IOException e) { e.printStackTrace(); }
/*    */     
/*    */     }
/* 41 */     load();
/*    */   }
/*    */   
/*    */   public void load() {
/* 45 */     ArrayList<String> lines = new ArrayList<>();
/*    */     
/*    */     try {
/* 48 */       BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
/* 49 */       String line = reader.readLine();
/* 50 */       while (line != null) {
/* 51 */         lines.add(line);
/* 52 */         line = reader.readLine();
/*    */       } 
/* 54 */       reader.close();
/* 55 */     } catch (Exception e) {
/* 56 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 59 */     for (String s : lines) {
/* 60 */       String[] args = s.split(":");
/* 61 */       if (s.toLowerCase().startsWith("mod:")) {
/* 62 */         Module m = Client.getModule(args[1]);
/* 63 */         if (m != null) {
/*    */           
/* 65 */           if (m.name.equals("clickGui")) m.toggled = false; 
/* 66 */           if (m.name.equals("hudEditor")) m.toggled = false;
/*    */           
/* 68 */           if (m.name.equals("blink")) m.toggled = false; 
/* 69 */           if (m.name.equals("autoDisconnect")) m.toggled = false;
/*    */ 
/*    */           
/* 72 */           if (!m.name.equals("ClickGui") && 
/* 73 */             !m.name.equals("blink") && 
/* 74 */             !m.name.equals("autoDisconnect")) {
/* 75 */             m.toggled = Boolean.parseBoolean(args[2]);
/* 76 */             m.keyCode.setKeyCode(Integer.parseInt(args[3]));
/*    */           } 
/*    */         }  continue;
/* 79 */       }  if (s.toLowerCase().startsWith("set:")) {
/* 80 */         Module m = Client.getModule(args[1]);
/* 81 */         if (m != null)
/* 82 */           for (Module ms : Client.modules) {
/* 83 */             for (Setting settingd : ms.settings) {
/* 84 */               Setting setting = Client.getSettingByName(m, args[2]);
/* 85 */               if (setting != null) {
/* 86 */                 if (setting instanceof BooleanSetting) {
/* 87 */                   ((BooleanSetting)setting).setEnabled(Boolean.parseBoolean(args[3]));
/*    */                 }
/* 89 */                 if (setting instanceof NumberSetting) {
/* 90 */                   ((NumberSetting)setting).setValue(Double.parseDouble(args[3]));
/*    */                 }
/* 92 */                 if (setting instanceof ModeSetting && ((ModeSetting)setting).modes.toString().contains(args[3]))
/* 93 */                   ((ModeSetting)setting).index = ((ModeSetting)setting).modes.indexOf(args[3]); 
/*    */               } 
/*    */             } 
/*    */           }  
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\clickgui\save\SaveLoad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */