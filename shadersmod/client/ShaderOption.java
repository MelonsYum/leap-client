/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import optifine.Config;
/*     */ import optifine.StrUtils;
/*     */ 
/*     */ 
/*     */ public abstract class ShaderOption
/*     */ {
/*  11 */   private String name = null;
/*  12 */   private String description = null;
/*  13 */   private String value = null;
/*  14 */   private String[] values = null;
/*  15 */   private String valueDefault = null;
/*  16 */   private String[] paths = null;
/*     */   
/*     */   private boolean enabled = true;
/*     */   private boolean visible = true;
/*     */   public static final String COLOR_GREEN = "§a";
/*     */   public static final String COLOR_RED = "§c";
/*     */   public static final String COLOR_BLUE = "§9";
/*     */   
/*     */   public ShaderOption(String name, String description, String value, String[] values, String valueDefault, String path) {
/*  25 */     this.name = name;
/*  26 */     this.description = description;
/*  27 */     this.value = value;
/*  28 */     this.values = values;
/*  29 */     this.valueDefault = valueDefault;
/*     */     
/*  31 */     if (path != null)
/*     */     {
/*  33 */       this.paths = new String[] { path };
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  39 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  44 */     return this.description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescriptionText() {
/*  49 */     String desc = Config.normalize(this.description);
/*  50 */     desc = StrUtils.removePrefix(desc, "//");
/*  51 */     desc = Shaders.translate("option." + getName() + ".comment", desc);
/*  52 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  57 */     this.description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  62 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setValue(String value) {
/*  67 */     int index = getIndex(value, this.values);
/*     */     
/*  69 */     if (index < 0)
/*     */     {
/*  71 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  75 */     this.value = value;
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueDefault() {
/*  82 */     return this.valueDefault;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetValue() {
/*  87 */     this.value = this.valueDefault;
/*     */   }
/*     */ 
/*     */   
/*     */   public void nextValue() {
/*  92 */     int index = getIndex(this.value, this.values);
/*     */     
/*  94 */     if (index >= 0) {
/*     */       
/*  96 */       index = (index + 1) % this.values.length;
/*  97 */       this.value = this.values[index];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getIndex(String str, String[] strs) {
/* 103 */     for (int i = 0; i < strs.length; i++) {
/*     */       
/* 105 */       String s = strs[i];
/*     */       
/* 107 */       if (s.equals(str))
/*     */       {
/* 109 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 113 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getPaths() {
/* 118 */     return this.paths;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPaths(String[] newPaths) {
/* 123 */     List<String> pathList = Arrays.asList(this.paths);
/*     */     
/* 125 */     for (int i = 0; i < newPaths.length; i++) {
/*     */       
/* 127 */       String newPath = newPaths[i];
/*     */       
/* 129 */       if (!pathList.contains(newPath))
/*     */       {
/* 131 */         this.paths = (String[])Config.addObjectToArray((Object[])this.paths, newPath);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 138 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 143 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChanged() {
/* 148 */     return !Config.equals(this.value, this.valueDefault);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 153 */     return this.visible;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 158 */     this.visible = visible;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidValue(String val) {
/* 163 */     return (getIndex(val, this.values) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameText() {
/* 168 */     return Shaders.translate("option." + this.name, this.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueText(String val) {
/* 173 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueColor(String val) {
/* 178 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matchesLine(String line) {
/* 183 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkUsed() {
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsedInLine(String line) {
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSourceLine() {
/* 198 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getValues() {
/* 203 */     return (String[])this.values.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 208 */     return this.name + ", value: " + this.value + ", valueDefault: " + this.valueDefault + ", paths: " + Config.arrayToString((Object[])this.paths);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */