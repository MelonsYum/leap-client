/*    */ package net.minecraft.util;
/*    */ 
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ public class ResourceLocation
/*    */ {
/*    */   protected final String resourceDomain;
/*    */   protected final String resourcePath;
/*    */   private static final String __OBFID = "CL_00001082";
/*    */   
/*    */   protected ResourceLocation(int p_i45928_1_, String... p_i45928_2_) {
/* 13 */     this.resourceDomain = StringUtils.isEmpty(p_i45928_2_[0]) ? "minecraft" : p_i45928_2_[0].toLowerCase();
/* 14 */     this.resourcePath = p_i45928_2_[1];
/* 15 */     Validate.notNull(this.resourcePath);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation(String p_i1293_1_) {
/* 20 */     this(0, func_177516_a(p_i1293_1_));
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation(String p_i1292_1_, String p_i1292_2_) {
/* 25 */     this(0, new String[] { p_i1292_1_, p_i1292_2_ });
/*    */   }
/*    */ 
/*    */   
/*    */   protected static String[] func_177516_a(String p_177516_0_) {
/* 30 */     String[] var1 = { null, p_177516_0_ };
/* 31 */     int var2 = p_177516_0_.indexOf(':');
/*    */     
/* 33 */     if (var2 >= 0) {
/*    */       
/* 35 */       var1[1] = p_177516_0_.substring(var2 + 1, p_177516_0_.length());
/*    */       
/* 37 */       if (var2 > 1)
/*    */       {
/* 39 */         var1[0] = p_177516_0_.substring(0, var2);
/*    */       }
/*    */     } 
/*    */     
/* 43 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourcePath() {
/* 48 */     return this.resourcePath;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourceDomain() {
/* 53 */     return this.resourceDomain;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     return String.valueOf(this.resourceDomain) + ':' + this.resourcePath;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 63 */     if (this == p_equals_1_)
/*    */     {
/* 65 */       return true;
/*    */     }
/* 67 */     if (!(p_equals_1_ instanceof ResourceLocation))
/*    */     {
/* 69 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 73 */     ResourceLocation var2 = (ResourceLocation)p_equals_1_;
/* 74 */     return (this.resourceDomain.equals(var2.resourceDomain) && this.resourcePath.equals(var2.resourcePath));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 80 */     return 31 * this.resourceDomain.hashCode() + this.resourcePath.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ResourceLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */