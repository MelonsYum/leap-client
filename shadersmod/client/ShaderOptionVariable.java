/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import optifine.Config;
/*     */ import optifine.StrUtils;
/*     */ 
/*     */ public class ShaderOptionVariable
/*     */   extends ShaderOption
/*     */ {
/*  12 */   private static final Pattern PATTERN_VARIABLE = Pattern.compile("^\\s*#define\\s+([A-Za-z0-9_]+)\\s+(-?[0-9\\.]*)F?f?\\s*(//.*)?$");
/*     */ 
/*     */   
/*     */   public ShaderOptionVariable(String name, String description, String value, String[] values, String path) {
/*  16 */     super(name, description, value, values, value, path);
/*  17 */     setVisible(((getValues()).length > 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSourceLine() {
/*  22 */     return "#define " + getName() + " " + getValue() + " // Shader option " + getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueColor(String val) {
/*  27 */     return "§a";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matchesLine(String line) {
/*  32 */     Matcher m = PATTERN_VARIABLE.matcher(line);
/*     */     
/*  34 */     if (!m.matches())
/*     */     {
/*  36 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  40 */     String defName = m.group(1);
/*  41 */     return defName.matches(getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShaderOption parseOption(String line, String path) {
/*  47 */     Matcher m = PATTERN_VARIABLE.matcher(line);
/*     */     
/*  49 */     if (!m.matches())
/*     */     {
/*  51 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  55 */     String name = m.group(1);
/*  56 */     String value = m.group(2);
/*  57 */     String description = m.group(3);
/*  58 */     String vals = StrUtils.getSegment(description, "[", "]");
/*     */     
/*  60 */     if (vals != null && vals.length() > 0)
/*     */     {
/*  62 */       description = description.replace(vals, "").trim();
/*     */     }
/*     */     
/*  65 */     String[] values = parseValues(value, vals);
/*     */     
/*  67 */     if (name != null && name.length() > 0) {
/*     */       
/*  69 */       path = StrUtils.removePrefix(path, "/shaders/");
/*  70 */       ShaderOptionVariable so = new ShaderOptionVariable(name, description, value, values, path);
/*  71 */       return so;
/*     */     } 
/*     */ 
/*     */     
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] parseValues(String value, String valuesStr) {
/*  82 */     String[] values = { value };
/*     */     
/*  84 */     if (valuesStr == null)
/*     */     {
/*  86 */       return values;
/*     */     }
/*     */ 
/*     */     
/*  90 */     valuesStr = valuesStr.trim();
/*  91 */     valuesStr = StrUtils.removePrefix(valuesStr, "[");
/*  92 */     valuesStr = StrUtils.removeSuffix(valuesStr, "]");
/*  93 */     valuesStr = valuesStr.trim();
/*     */     
/*  95 */     if (valuesStr.length() <= 0)
/*     */     {
/*  97 */       return values;
/*     */     }
/*     */ 
/*     */     
/* 101 */     String[] parts = Config.tokenize(valuesStr, " ");
/*     */     
/* 103 */     if (parts.length <= 0)
/*     */     {
/* 105 */       return values;
/*     */     }
/*     */ 
/*     */     
/* 109 */     if (!Arrays.<String>asList(parts).contains(value))
/*     */     {
/* 111 */       parts = (String[])Config.addObjectToArray((Object[])parts, value, 0);
/*     */     }
/*     */     
/* 114 */     return parts;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderOptionVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */