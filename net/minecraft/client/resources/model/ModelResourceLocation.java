/*    */ package net.minecraft.client.resources.model;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class ModelResourceLocation
/*    */   extends ResourceLocation
/*    */ {
/*    */   private final String field_177519_c;
/*    */   private static final String __OBFID = "CL_00002387";
/*    */   
/*    */   protected ModelResourceLocation(int p_i46078_1_, String... p_i46078_2_) {
/* 13 */     super(0, new String[] { p_i46078_2_[0], p_i46078_2_[1] });
/* 14 */     this.field_177519_c = StringUtils.isEmpty(p_i46078_2_[2]) ? "normal" : p_i46078_2_[2].toLowerCase();
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelResourceLocation(String p_i46079_1_) {
/* 19 */     this(0, func_177517_b(p_i46079_1_));
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelResourceLocation(ResourceLocation p_i46080_1_, String p_i46080_2_) {
/* 24 */     this(p_i46080_1_.toString(), p_i46080_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelResourceLocation(String p_i46081_1_, String p_i46081_2_) {
/* 29 */     this(0, func_177517_b(String.valueOf(p_i46081_1_) + '#' + ((p_i46081_2_ == null) ? "normal" : p_i46081_2_)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected static String[] func_177517_b(String p_177517_0_) {
/* 34 */     String[] var1 = { null, p_177517_0_ };
/* 35 */     int var2 = p_177517_0_.indexOf('#');
/* 36 */     String var3 = p_177517_0_;
/*    */     
/* 38 */     if (var2 >= 0) {
/*    */       
/* 40 */       var1[2] = p_177517_0_.substring(var2 + 1, p_177517_0_.length());
/*    */       
/* 42 */       if (var2 > 1)
/*    */       {
/* 44 */         var3 = p_177517_0_.substring(0, var2);
/*    */       }
/*    */     } 
/*    */     
/* 48 */     System.arraycopy(ResourceLocation.func_177516_a(var3), 0, var1, 0, 2);
/* 49 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_177518_c() {
/* 54 */     return this.field_177519_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 59 */     if (this == p_equals_1_)
/*    */     {
/* 61 */       return true;
/*    */     }
/* 63 */     if (p_equals_1_ instanceof ModelResourceLocation && super.equals(p_equals_1_)) {
/*    */       
/* 65 */       ModelResourceLocation var2 = (ModelResourceLocation)p_equals_1_;
/* 66 */       return this.field_177519_c.equals(var2.field_177519_c);
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 76 */     return 31 * super.hashCode() + this.field_177519_c.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     return String.valueOf(super.toString()) + '#' + this.field_177519_c;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\model\ModelResourceLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */