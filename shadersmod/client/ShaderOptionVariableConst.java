/*    */ package shadersmod.client;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import optifine.StrUtils;
/*    */ 
/*    */ public class ShaderOptionVariableConst
/*    */   extends ShaderOptionVariable
/*    */ {
/* 10 */   private String type = null;
/* 11 */   private static final Pattern PATTERN_CONST = Pattern.compile("^\\s*const\\s*(float|int)\\s*([A-Za-z0-9_]+)\\s*=\\s*(-?[0-9\\.]+f?F?)\\s*;\\s*(//.*)?$");
/*    */ 
/*    */   
/*    */   public ShaderOptionVariableConst(String name, String type, String description, String value, String[] values, String path) {
/* 15 */     super(name, description, value, values, path);
/* 16 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSourceLine() {
/* 21 */     return "const " + this.type + " " + getName() + " = " + getValue() + "; // Shader option " + getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matchesLine(String line) {
/* 26 */     Matcher m = PATTERN_CONST.matcher(line);
/*    */     
/* 28 */     if (!m.matches())
/*    */     {
/* 30 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 34 */     String defName = m.group(2);
/* 35 */     return defName.matches(getName());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ShaderOption parseOption(String line, String path) {
/* 41 */     Matcher m = PATTERN_CONST.matcher(line);
/*    */     
/* 43 */     if (!m.matches())
/*    */     {
/* 45 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 49 */     String type = m.group(1);
/* 50 */     String name = m.group(2);
/* 51 */     String value = m.group(3);
/* 52 */     String description = m.group(4);
/* 53 */     String vals = StrUtils.getSegment(description, "[", "]");
/*    */     
/* 55 */     if (vals != null && vals.length() > 0)
/*    */     {
/* 57 */       description = description.replace(vals, "").trim();
/*    */     }
/*    */     
/* 60 */     String[] values = parseValues(value, vals);
/*    */     
/* 62 */     if (name != null && name.length() > 0) {
/*    */       
/* 64 */       path = StrUtils.removePrefix(path, "/shaders/");
/* 65 */       ShaderOptionVariableConst so = new ShaderOptionVariableConst(name, type, description, value, values, path);
/* 66 */       return so;
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderOptionVariableConst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */