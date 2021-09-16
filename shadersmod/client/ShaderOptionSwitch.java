/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import optifine.Config;
/*     */ import optifine.Lang;
/*     */ import optifine.StrUtils;
/*     */ 
/*     */ public class ShaderOptionSwitch
/*     */   extends ShaderOption
/*     */ {
/*  12 */   private static final Pattern PATTERN_DEFINE = Pattern.compile("^\\s*(//)?\\s*#define\\s+([A-Za-z0-9_]+)\\s*(//.*)?$");
/*  13 */   private static final Pattern PATTERN_IFDEF = Pattern.compile("^\\s*#if(n)?def\\s+([A-Za-z0-9_]+)(\\s*)?$");
/*     */ 
/*     */   
/*     */   public ShaderOptionSwitch(String name, String description, String value, String path) {
/*  17 */     super(name, description, value, new String[] { "true", "false" }, value, path);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSourceLine() {
/*  22 */     return isTrue(getValue()) ? ("#define " + getName() + " // Shader option ON") : ("//#define " + getName() + " // Shader option OFF");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueText(String val) {
/*  27 */     return isTrue(val) ? Lang.getOn() : Lang.getOff();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueColor(String val) {
/*  32 */     return isTrue(val) ? "§a" : "§c";
/*     */   }
/*     */ 
/*     */   
/*     */   public static ShaderOption parseOption(String line, String path) {
/*  37 */     Matcher m = PATTERN_DEFINE.matcher(line);
/*     */     
/*  39 */     if (!m.matches())
/*     */     {
/*  41 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  45 */     String comment = m.group(1);
/*  46 */     String name = m.group(2);
/*  47 */     String description = m.group(3);
/*     */     
/*  49 */     if (name != null && name.length() > 0) {
/*     */       
/*  51 */       boolean commented = Config.equals(comment, "//");
/*  52 */       boolean enabled = !commented;
/*  53 */       path = StrUtils.removePrefix(path, "/shaders/");
/*  54 */       ShaderOptionSwitch so = new ShaderOptionSwitch(name, description, String.valueOf(enabled), path);
/*  55 */       return so;
/*     */     } 
/*     */ 
/*     */     
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matchesLine(String line) {
/*  66 */     Matcher m = PATTERN_DEFINE.matcher(line);
/*     */     
/*  68 */     if (!m.matches())
/*     */     {
/*  70 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  74 */     String defName = m.group(2);
/*  75 */     return defName.matches(getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkUsed() {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsedInLine(String line) {
/*  86 */     Matcher mif = PATTERN_IFDEF.matcher(line);
/*     */     
/*  88 */     if (mif.matches()) {
/*     */       
/*  90 */       String name = mif.group(2);
/*     */       
/*  92 */       if (name.equals(getName()))
/*     */       {
/*  94 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTrue(String val) {
/* 103 */     return Boolean.valueOf(val).booleanValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderOptionSwitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */