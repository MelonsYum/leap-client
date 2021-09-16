/*    */ package shadersmod.client;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class ShaderProfile
/*    */ {
/* 11 */   private String name = null;
/* 12 */   private Map<String, String> mapOptionValues = new HashMap<>();
/* 13 */   private Set<String> disabledPrograms = new HashSet<>();
/*    */ 
/*    */   
/*    */   public ShaderProfile(String name) {
/* 17 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 22 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addOptionValue(String option, String value) {
/* 27 */     this.mapOptionValues.put(option, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addOptionValues(ShaderProfile prof) {
/* 32 */     if (prof != null)
/*    */     {
/* 34 */       this.mapOptionValues.putAll(prof.mapOptionValues);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyOptionValues(ShaderOption[] options) {
/* 40 */     for (int i = 0; i < options.length; i++) {
/*    */       
/* 42 */       ShaderOption so = options[i];
/* 43 */       String key = so.getName();
/* 44 */       String val = this.mapOptionValues.get(key);
/*    */       
/* 46 */       if (val != null)
/*    */       {
/* 48 */         so.setValue(val);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getOptions() {
/* 55 */     Set<String> keys = this.mapOptionValues.keySet();
/* 56 */     String[] opts = keys.<String>toArray(new String[keys.size()]);
/* 57 */     return opts;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue(String key) {
/* 62 */     return this.mapOptionValues.get(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDisabledProgram(String program) {
/* 67 */     this.disabledPrograms.add(program);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getDisabledPrograms() {
/* 72 */     return new HashSet<>(this.disabledPrograms);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDisabledPrograms(Collection<String> programs) {
/* 77 */     this.disabledPrograms.addAll(programs);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isProgramDisabled(String program) {
/* 82 */     return this.disabledPrograms.contains(program);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */