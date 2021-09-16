/*    */ package net.minecraft.client.util;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class QuadComparator
/*    */   implements Comparator
/*    */ {
/*    */   private float field_147630_a;
/*    */   private float field_147628_b;
/*    */   private float field_147629_c;
/*    */   private FloatBuffer field_147627_d;
/*    */   private int field_178079_e;
/*    */   private static final String __OBFID = "CL_00000958";
/*    */   
/*    */   public QuadComparator(FloatBuffer p_i46247_1_, float p_i46247_2_, float p_i46247_3_, float p_i46247_4_, int p_i46247_5_) {
/* 17 */     this.field_147627_d = p_i46247_1_;
/* 18 */     this.field_147630_a = p_i46247_2_;
/* 19 */     this.field_147628_b = p_i46247_3_;
/* 20 */     this.field_147629_c = p_i46247_4_;
/* 21 */     this.field_178079_e = p_i46247_5_;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compare(Integer p_compare_1_, Integer p_compare_2_) {
/* 26 */     float var3 = this.field_147627_d.get(p_compare_1_.intValue()) - this.field_147630_a;
/* 27 */     float var4 = this.field_147627_d.get(p_compare_1_.intValue() + 1) - this.field_147628_b;
/* 28 */     float var5 = this.field_147627_d.get(p_compare_1_.intValue() + 2) - this.field_147629_c;
/* 29 */     float var6 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e + 0) - this.field_147630_a;
/* 30 */     float var7 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e + 1) - this.field_147628_b;
/* 31 */     float var8 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e + 2) - this.field_147629_c;
/* 32 */     float var9 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 2 + 0) - this.field_147630_a;
/* 33 */     float var10 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 2 + 1) - this.field_147628_b;
/* 34 */     float var11 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 2 + 2) - this.field_147629_c;
/* 35 */     float var12 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 3 + 0) - this.field_147630_a;
/* 36 */     float var13 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 3 + 1) - this.field_147628_b;
/* 37 */     float var14 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 3 + 2) - this.field_147629_c;
/* 38 */     float var15 = this.field_147627_d.get(p_compare_2_.intValue()) - this.field_147630_a;
/* 39 */     float var16 = this.field_147627_d.get(p_compare_2_.intValue() + 1) - this.field_147628_b;
/* 40 */     float var17 = this.field_147627_d.get(p_compare_2_.intValue() + 2) - this.field_147629_c;
/* 41 */     float var18 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e + 0) - this.field_147630_a;
/* 42 */     float var19 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e + 1) - this.field_147628_b;
/* 43 */     float var20 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e + 2) - this.field_147629_c;
/* 44 */     float var21 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 2 + 0) - this.field_147630_a;
/* 45 */     float var22 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 2 + 1) - this.field_147628_b;
/* 46 */     float var23 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 2 + 2) - this.field_147629_c;
/* 47 */     float var24 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 3 + 0) - this.field_147630_a;
/* 48 */     float var25 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 3 + 1) - this.field_147628_b;
/* 49 */     float var26 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 3 + 2) - this.field_147629_c;
/* 50 */     float var27 = (var3 + var6 + var9 + var12) * 0.25F;
/* 51 */     float var28 = (var4 + var7 + var10 + var13) * 0.25F;
/* 52 */     float var29 = (var5 + var8 + var11 + var14) * 0.25F;
/* 53 */     float var30 = (var15 + var18 + var21 + var24) * 0.25F;
/* 54 */     float var31 = (var16 + var19 + var22 + var25) * 0.25F;
/* 55 */     float var32 = (var17 + var20 + var23 + var26) * 0.25F;
/* 56 */     float var33 = var27 * var27 + var28 * var28 + var29 * var29;
/* 57 */     float var34 = var30 * var30 + var31 * var31 + var32 * var32;
/* 58 */     return Float.compare(var34, var33);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 63 */     return compare((Integer)p_compare_1_, (Integer)p_compare_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\clien\\util\QuadComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */