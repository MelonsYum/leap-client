/*    */ package shadersmod.client;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import optifine.StrUtils;
/*    */ 
/*    */ public class ShaderOptionSwitchConst
/*    */   extends ShaderOptionSwitch
/*    */ {
/* 10 */   private static final Pattern PATTERN_CONST = Pattern.compile("^\\s*const\\s*bool\\s*([A-Za-z0-9_]+)\\s*=\\s*(true|false)\\s*;\\s*(//.*)?$");
/*    */ 
/*    */   
/*    */   public ShaderOptionSwitchConst(String name, String description, String value, String path) {
/* 14 */     super(name, description, value, path);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSourceLine() {
/* 19 */     return "const bool " + getName() + " = " + getValue() + "; // Shader option " + getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public static ShaderOption parseOption(String line, String path) {
/* 24 */     Matcher m = PATTERN_CONST.matcher(line);
/*    */     
/* 26 */     if (!m.matches())
/*    */     {
/* 28 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 32 */     String name = m.group(1);
/* 33 */     String value = m.group(2);
/* 34 */     String description = m.group(3);
/*    */     
/* 36 */     if (name != null && name.length() > 0) {
/*    */       
/* 38 */       path = StrUtils.removePrefix(path, "/shaders/");
/* 39 */       ShaderOptionSwitchConst so = new ShaderOptionSwitchConst(name, description, value, path);
/* 40 */       so.setVisible(false);
/* 41 */       return so;
/*    */     } 
/*    */ 
/*    */     
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matchesLine(String line) {
/* 52 */     Matcher m = PATTERN_CONST.matcher(line);
/*    */     
/* 54 */     if (!m.matches())
/*    */     {
/* 56 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 60 */     String defName = m.group(1);
/* 61 */     return defName.matches(getName());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkUsed() {
/* 67 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderOptionSwitchConst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */