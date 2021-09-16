/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import optifine.Lang;
/*     */ 
/*     */ public class ShaderOptionProfile
/*     */   extends ShaderOption
/*     */ {
/*   9 */   private ShaderProfile[] profiles = null;
/*  10 */   private ShaderOption[] options = null;
/*     */   
/*     */   private static final String NAME_PROFILE = "<profile>";
/*     */   private static final String VALUE_CUSTOM = "<custom>";
/*     */   
/*     */   public ShaderOptionProfile(ShaderProfile[] profiles, ShaderOption[] options) {
/*  16 */     super("<profile>", "", detectProfileName(profiles, options), getProfileNames(profiles), detectProfileName(profiles, options, true), null);
/*  17 */     this.profiles = profiles;
/*  18 */     this.options = options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void nextValue() {
/*  23 */     super.nextValue();
/*     */     
/*  25 */     if (getValue().equals("<custom>"))
/*     */     {
/*  27 */       super.nextValue();
/*     */     }
/*     */     
/*  30 */     applyProfileOptions();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProfile() {
/*  35 */     ShaderProfile prof = getProfile(getValue());
/*     */     
/*  37 */     if (prof == null || !ShaderUtils.matchProfile(prof, this.options, false)) {
/*     */       
/*  39 */       String val = detectProfileName(this.profiles, this.options);
/*  40 */       setValue(val);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyProfileOptions() {
/*  46 */     ShaderProfile prof = getProfile(getValue());
/*     */     
/*  48 */     if (prof != null) {
/*     */       
/*  50 */       String[] opts = prof.getOptions();
/*     */       
/*  52 */       for (int i = 0; i < opts.length; i++) {
/*     */         
/*  54 */         String name = opts[i];
/*  55 */         ShaderOption so = getOption(name);
/*     */         
/*  57 */         if (so != null) {
/*     */           
/*  59 */           String val = prof.getValue(name);
/*  60 */           so.setValue(val);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ShaderOption getOption(String name) {
/*  68 */     for (int i = 0; i < this.options.length; i++) {
/*     */       
/*  70 */       ShaderOption so = this.options[i];
/*     */       
/*  72 */       if (so.getName().equals(name))
/*     */       {
/*  74 */         return so;
/*     */       }
/*     */     } 
/*     */     
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private ShaderProfile getProfile(String name) {
/*  83 */     for (int i = 0; i < this.profiles.length; i++) {
/*     */       
/*  85 */       ShaderProfile prof = this.profiles[i];
/*     */       
/*  87 */       if (prof.getName().equals(name))
/*     */       {
/*  89 */         return prof;
/*     */       }
/*     */     } 
/*     */     
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameText() {
/*  98 */     return Lang.get("of.shaders.profile");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueText(String val) {
/* 103 */     return val.equals("<custom>") ? Lang.get("of.general.custom", "<custom>") : Shaders.translate("profile." + val, val);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueColor(String val) {
/* 108 */     return val.equals("<custom>") ? "§c" : "§a";
/*     */   }
/*     */ 
/*     */   
/*     */   private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts) {
/* 113 */     return detectProfileName(profs, opts, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts, boolean def) {
/* 118 */     ShaderProfile prof = ShaderUtils.detectProfile(profs, opts, def);
/* 119 */     return (prof == null) ? "<custom>" : prof.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String[] getProfileNames(ShaderProfile[] profs) {
/* 124 */     ArrayList<String> list = new ArrayList();
/*     */     
/* 126 */     for (int names = 0; names < profs.length; names++) {
/*     */       
/* 128 */       ShaderProfile prof = profs[names];
/* 129 */       list.add(prof.getName());
/*     */     } 
/*     */     
/* 132 */     list.add("<custom>");
/* 133 */     String[] var4 = list.<String>toArray(new String[list.size()]);
/* 134 */     return var4;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderOptionProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */