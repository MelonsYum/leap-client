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
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
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
/*     */ public class Hypixel
/*     */   extends Module
/*     */ {
/*     */   public Hypixel() {
/*  35 */     super("Hypixel", 0, Module.Category.CONFIGS);
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  39 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*     */     try {
/*  44 */       load();
/*  45 */     } catch (IOException e) {
/*  46 */       e.printStackTrace();
/*     */     } 
/*  48 */     mc.thePlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("§bLeap: §4Warning!: §8modifying these settings too much can result §8in a ban!"));
/*  49 */     toggle();
/*  50 */     super.onEnable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {}
/*     */ 
/*     */   
/*     */   public void load() throws IOException {
/*  58 */     Hypixel instance = new Hypixel();
/*     */     
/*  60 */     File file = instance.getFile("assets/minecraft/configs/Hypixel.cfg");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     File dataFile = file;
/*     */     
/*  67 */     ArrayList<String> lines = new ArrayList<>();
/*     */ 
/*     */     
/*     */     try {
/*  71 */       BufferedReader reader = new BufferedReader(new FileReader(dataFile));
/*  72 */       String line = reader.readLine();
/*  73 */       while (line != null) {
/*  74 */         lines.add(line);
/*  75 */         line = reader.readLine();
/*     */       } 
/*  77 */       reader.close();
/*  78 */     } catch (Exception e) {
/*  79 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  82 */     for (String s : lines) {
/*  83 */       String[] args = s.split(":");
/*  84 */       if (s.toLowerCase().startsWith("mod:")) {
/*  85 */         Module m = Client.getModule(args[1]);
/*  86 */         if (m != null) {
/*     */           
/*  88 */           if (m.name.equals("clickGui")) m.toggled = false; 
/*  89 */           if (m.name.equals("hudEditor")) m.toggled = false;
/*     */           
/*  91 */           if (m.name.equals("blink")) m.toggled = false; 
/*  92 */           if (m.name.equals("autoDisconnect")) m.toggled = false;
/*     */ 
/*     */           
/*  95 */           if (!m.name.equals("ClickGui") && 
/*  96 */             !m.name.equals("blink") && 
/*  97 */             !m.name.equals("autoDisconnect"))
/*  98 */             m.toggled = Boolean.parseBoolean(args[2]); 
/*     */         }  continue;
/*     */       } 
/* 101 */       if (s.toLowerCase().startsWith("set:")) {
/* 102 */         Module m = Client.getModule(args[1]);
/* 103 */         if (m != null) {
/* 104 */           for (Module ms : Client.modules) {
/* 105 */             for (Setting settingd : ms.settings) {
/* 106 */               Setting setting = Client.getSettingByName(m, args[2]);
/* 107 */               if (setting != null) {
/* 108 */                 if (setting instanceof BooleanSetting) {
/* 109 */                   ((BooleanSetting)setting).setEnabled(Boolean.parseBoolean(args[3]));
/*     */                 }
/* 111 */                 if (setting instanceof NumberSetting) {
/* 112 */                   ((NumberSetting)setting).setValue(Double.parseDouble(args[3]));
/*     */                 }
/* 114 */                 if (setting instanceof ModeSetting && ((ModeSetting)setting).modes.toString().contains(args[3])) {
/* 115 */                   ((ModeSetting)setting).index = ((ModeSetting)setting).modes.indexOf(args[3]);
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
/* 129 */     ClassLoader classLoader = getClass().getClassLoader();
/* 130 */     URL resource = classLoader.getResource(fileName);
/*     */     
/* 132 */     if (resource == null) {
/* 133 */       throw new IllegalArgumentException("file is not found!");
/*     */     }
/* 135 */     return new File(resource.getFile());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\configs\Hypixel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */