/*    */ package shadersmod.client;
/*    */ 
/*    */ import optifine.Config;
/*    */ 
/*    */ 
/*    */ public class ShaderUtils
/*    */ {
/*    */   public static ShaderOption getShaderOption(String name, ShaderOption[] opts) {
/*  9 */     if (opts == null)
/*    */     {
/* 11 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 15 */     for (int i = 0; i < opts.length; i++) {
/*    */       
/* 17 */       ShaderOption so = opts[i];
/*    */       
/* 19 */       if (so.getName().equals(name))
/*    */       {
/* 21 */         return so;
/*    */       }
/*    */     } 
/*    */     
/* 25 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ShaderProfile detectProfile(ShaderProfile[] profs, ShaderOption[] opts, boolean def) {
/* 31 */     if (profs == null)
/*    */     {
/* 33 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 37 */     for (int i = 0; i < profs.length; i++) {
/*    */       
/* 39 */       ShaderProfile prof = profs[i];
/*    */       
/* 41 */       if (matchProfile(prof, opts, def))
/*    */       {
/* 43 */         return prof;
/*    */       }
/*    */     } 
/*    */     
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean matchProfile(ShaderProfile prof, ShaderOption[] opts, boolean def) {
/* 53 */     if (prof == null)
/*    */     {
/* 55 */       return false;
/*    */     }
/* 57 */     if (opts == null)
/*    */     {
/* 59 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 63 */     String[] optsProf = prof.getOptions();
/*    */     
/* 65 */     for (int p = 0; p < optsProf.length; p++) {
/*    */       
/* 67 */       String opt = optsProf[p];
/* 68 */       ShaderOption so = getShaderOption(opt, opts);
/*    */       
/* 70 */       if (so != null) {
/*    */         
/* 72 */         String optVal = def ? so.getValueDefault() : so.getValue();
/* 73 */         String profVal = prof.getValue(opt);
/*    */         
/* 75 */         if (!Config.equals(optVal, profVal))
/*    */         {
/* 77 */           return false;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */