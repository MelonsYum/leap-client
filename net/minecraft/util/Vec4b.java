/*    */ package net.minecraft.util;
/*    */ 
/*    */ 
/*    */ public class Vec4b
/*    */ {
/*    */   private byte field_176117_a;
/*    */   private byte field_176115_b;
/*    */   private byte field_176116_c;
/*    */   private byte field_176114_d;
/*    */   private static final String __OBFID = "CL_00001964";
/*    */   
/*    */   public Vec4b(byte p_i45555_1_, byte p_i45555_2_, byte p_i45555_3_, byte p_i45555_4_) {
/* 13 */     this.field_176117_a = p_i45555_1_;
/* 14 */     this.field_176115_b = p_i45555_2_;
/* 15 */     this.field_176116_c = p_i45555_3_;
/* 16 */     this.field_176114_d = p_i45555_4_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vec4b(Vec4b p_i45556_1_) {
/* 21 */     this.field_176117_a = p_i45556_1_.field_176117_a;
/* 22 */     this.field_176115_b = p_i45556_1_.field_176115_b;
/* 23 */     this.field_176116_c = p_i45556_1_.field_176116_c;
/* 24 */     this.field_176114_d = p_i45556_1_.field_176114_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte func_176110_a() {
/* 29 */     return this.field_176117_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte func_176112_b() {
/* 34 */     return this.field_176115_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte func_176113_c() {
/* 39 */     return this.field_176116_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte func_176111_d() {
/* 44 */     return this.field_176114_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 49 */     if (this == p_equals_1_)
/*    */     {
/* 51 */       return true;
/*    */     }
/* 53 */     if (!(p_equals_1_ instanceof Vec4b))
/*    */     {
/* 55 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 59 */     Vec4b var2 = (Vec4b)p_equals_1_;
/* 60 */     return (this.field_176117_a != var2.field_176117_a) ? false : ((this.field_176114_d != var2.field_176114_d) ? false : ((this.field_176115_b != var2.field_176115_b) ? false : ((this.field_176116_c == var2.field_176116_c))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 66 */     byte var1 = this.field_176117_a;
/* 67 */     int var2 = 31 * var1 + this.field_176115_b;
/* 68 */     var2 = 31 * var2 + this.field_176116_c;
/* 69 */     var2 = 31 * var2 + this.field_176114_d;
/* 70 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Vec4b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */