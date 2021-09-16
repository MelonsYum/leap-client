/*     */ package net.minecraft.client.renderer.vertex;
/*     */ 
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class VertexFormatElement
/*     */ {
/*   8 */   private static final Logger field_177381_a = LogManager.getLogger();
/*     */   
/*     */   private final EnumType field_177379_b;
/*     */   private final EnumUseage field_177380_c;
/*     */   private int field_177377_d;
/*     */   private int field_177378_e;
/*     */   private int field_177376_f;
/*     */   private static final String __OBFID = "CL_00002399";
/*     */   
/*     */   public VertexFormatElement(int p_i46096_1_, EnumType p_i46096_2_, EnumUseage p_i46096_3_, int p_i46096_4_) {
/*  18 */     if (!func_177372_a(p_i46096_1_, p_i46096_3_)) {
/*     */       
/*  20 */       field_177381_a.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
/*  21 */       this.field_177380_c = EnumUseage.UV;
/*     */     }
/*     */     else {
/*     */       
/*  25 */       this.field_177380_c = p_i46096_3_;
/*     */     } 
/*     */     
/*  28 */     this.field_177379_b = p_i46096_2_;
/*  29 */     this.field_177377_d = p_i46096_1_;
/*  30 */     this.field_177378_e = p_i46096_4_;
/*  31 */     this.field_177376_f = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177371_a(int p_177371_1_) {
/*  36 */     this.field_177376_f = p_177371_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_177373_a() {
/*  41 */     return this.field_177376_f;
/*     */   }
/*     */ 
/*     */   
/*     */   private final boolean func_177372_a(int p_177372_1_, EnumUseage p_177372_2_) {
/*  46 */     return !(p_177372_1_ != 0 && p_177372_2_ != EnumUseage.UV);
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumType func_177367_b() {
/*  51 */     return this.field_177379_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumUseage func_177375_c() {
/*  56 */     return this.field_177380_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int func_177370_d() {
/*  61 */     return this.field_177378_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int func_177369_e() {
/*  66 */     return this.field_177377_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  71 */     return String.valueOf(this.field_177378_e) + "," + this.field_177380_c.func_177384_a() + "," + this.field_177379_b.func_177396_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public final int func_177368_f() {
/*  76 */     return this.field_177379_b.func_177395_a() * this.field_177378_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean func_177374_g() {
/*  81 */     return (this.field_177380_c == EnumUseage.POSITION);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  86 */     if (this == p_equals_1_)
/*     */     {
/*  88 */       return true;
/*     */     }
/*  90 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*     */       
/*  92 */       VertexFormatElement var2 = (VertexFormatElement)p_equals_1_;
/*  93 */       return (this.field_177378_e != var2.field_177378_e) ? false : ((this.field_177377_d != var2.field_177377_d) ? false : ((this.field_177376_f != var2.field_177376_f) ? false : ((this.field_177379_b != var2.field_177379_b) ? false : ((this.field_177380_c == var2.field_177380_c)))));
/*     */     } 
/*     */ 
/*     */     
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 103 */     int var1 = this.field_177379_b.hashCode();
/* 104 */     var1 = 31 * var1 + this.field_177380_c.hashCode();
/* 105 */     var1 = 31 * var1 + this.field_177377_d;
/* 106 */     var1 = 31 * var1 + this.field_177378_e;
/* 107 */     var1 = 31 * var1 + this.field_177376_f;
/* 108 */     return var1;
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */   {
/* 113 */     FLOAT("FLOAT", 0, 4, "Float", 5126),
/* 114 */     UBYTE("UBYTE", 1, 1, "Unsigned Byte", 5121),
/* 115 */     BYTE("BYTE", 2, 1, "Byte", 5120),
/* 116 */     USHORT("USHORT", 3, 2, "Unsigned Short", 5123),
/* 117 */     SHORT("SHORT", 4, 2, "Short", 5122),
/* 118 */     UINT("UINT", 5, 4, "Unsigned Int", 5125),
/* 119 */     INT("INT", 6, 4, "Int", 5124);
/*     */     
/*     */     private final int field_177407_h;
/*     */     private final String field_177408_i;
/*     */     private final int field_177405_j;
/* 124 */     private static final EnumType[] $VALUES = new EnumType[] { FLOAT, UBYTE, BYTE, USHORT, SHORT, UINT, INT };
/*     */     
/*     */     private static final String __OBFID = "CL_00002398";
/*     */     
/*     */     EnumType(String p_i46095_1_, int p_i46095_2_, int p_i46095_3_, String p_i46095_4_, int p_i46095_5_) {
/* 129 */       this.field_177407_h = p_i46095_3_;
/* 130 */       this.field_177408_i = p_i46095_4_;
/* 131 */       this.field_177405_j = p_i46095_5_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public int func_177395_a() {
/* 136 */       return this.field_177407_h;
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_177396_b() {
/* 141 */       return this.field_177408_i;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_177397_c() {
/* 146 */       return this.field_177405_j;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumUseage
/*     */   {
/* 152 */     POSITION("POSITION", 0, "Position"),
/* 153 */     NORMAL("NORMAL", 1, "Normal"),
/* 154 */     COLOR("COLOR", 2, "Vertex Color"),
/* 155 */     UV("UV", 3, "UV"),
/* 156 */     MATRIX("MATRIX", 4, "Bone Matrix"),
/* 157 */     BLEND_WEIGHT("BLEND_WEIGHT", 5, "Blend Weight"),
/* 158 */     PADDING("PADDING", 6, "Padding");
/*     */     
/*     */     private final String field_177392_h;
/* 161 */     private static final EnumUseage[] $VALUES = new EnumUseage[] { POSITION, NORMAL, COLOR, UV, MATRIX, BLEND_WEIGHT, PADDING }; private static final String __OBFID = "CL_00002397";
/*     */     static {
/*     */     
/*     */     }
/*     */     EnumUseage(String p_i46094_1_, int p_i46094_2_, String p_i46094_3_) {
/* 166 */       this.field_177392_h = p_i46094_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_177384_a() {
/* 171 */       return this.field_177392_h;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\vertex\VertexFormatElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */