/*     */ package leap.modules.configs;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Vanilla
/*     */   extends Module
/*     */ {
/*     */   public Vanilla() {
/*  34 */     super("Vanilla", 0, Module.Category.CONFIGS);
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  38 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*     */     try {
/*  43 */       load();
/*  44 */     } catch (IOException e) {
/*  45 */       e.printStackTrace();
/*     */     } 
/*  47 */     toggle();
/*  48 */     super.onEnable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {}
/*     */ 
/*     */   
/*     */   public void load() throws IOException {
/*  56 */     Vanilla instance = new Vanilla();
/*     */     
/*  58 */     File file = instance.getFile("assets/minecraft/configs/Vanilla.cfg");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     File dataFile = file;
/*     */     
/*  65 */     ArrayList<String> lines = new ArrayList<>();
/*     */ 
/*     */     
/*     */     try {
/*  69 */       BufferedReader reader = new BufferedReader(new FileReader(dataFile));
/*  70 */       String line = reader.readLine();
/*  71 */       while (line != null) {
/*  72 */         lines.add(line);
/*  73 */         line = reader.readLine();
/*     */       } 
/*  75 */       reader.close();
/*  76 */     } catch (Exception e) {
/*  77 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  80 */     for (String s : lines) {
/*  81 */       String[] args = s.split(":");
/*  82 */       if (s.toLowerCase().startsWith("mod:")) {
/*  83 */         Module m = Client.getModule(args[1]);
/*  84 */         if (m != null) {
/*     */           
/*  86 */           if (m.name.equals("clickGui")) m.toggled = false; 
/*  87 */           if (m.name.equals("hudEditor")) m.toggled = false;
/*     */           
/*  89 */           if (m.name.equals("blink")) m.toggled = false; 
/*  90 */           if (m.name.equals("autoDisconnect")) m.toggled = false;
/*     */ 
/*     */           
/*  93 */           if (!m.name.equals("ClickGui") && 
/*  94 */             !m.name.equals("blink") && 
/*  95 */             !m.name.equals("autoDisconnect"))
/*  96 */             m.toggled = Boolean.parseBoolean(args[2]); 
/*     */         }  continue;
/*     */       } 
/*  99 */       if (s.toLowerCase().startsWith("set:")) {
/* 100 */         Module m = Client.getModule(args[1]);
/* 101 */         if (m != null) {
/* 102 */           for (Module ms : Client.modules) {
/* 103 */             for (Setting settingd : ms.settings) {
/* 104 */               Setting setting = Client.getSettingByName(m, args[2]);
/* 105 */               if (setting != null) {
/* 106 */                 if (setting instanceof BooleanSetting) {
/* 107 */                   ((BooleanSetting)setting).setEnabled(Boolean.parseBoolean(args[3]));
/*     */                 }
/* 109 */                 if (setting instanceof NumberSetting) {
/* 110 */                   ((NumberSetting)setting).setValue(Double.parseDouble(args[3]));
/*     */                 }
/* 112 */                 if (setting instanceof ModeSetting && ((ModeSetting)setting).modes.toString().contains(args[3])) {
/* 113 */                   ((ModeSetting)setting).index = ((ModeSetting)setting).modes.indexOf(args[3]);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private File getFile(String fileName) throws IOException {
/* 127 */     ClassLoader classLoader = getClass().getClassLoader();
/* 128 */     URL resource = classLoader.getResource(fileName);
/*     */     
/* 130 */     if (resource == null) {
/* 131 */       throw new IllegalArgumentException("file is not found!");
/*     */     }
/* 133 */     return new File(resource.getFile());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\configs\Vanilla.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */