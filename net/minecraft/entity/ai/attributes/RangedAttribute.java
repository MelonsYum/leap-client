/*    */ package net.minecraft.entity.ai.attributes;
/*    */ 
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class RangedAttribute
/*    */   extends BaseAttribute
/*    */ {
/*    */   private final double minimumValue;
/*    */   private final double maximumValue;
/*    */   private String description;
/*    */   private static final String __OBFID = "CL_00001568";
/*    */   
/*    */   public RangedAttribute(IAttribute p_i45891_1_, String p_i45891_2_, double p_i45891_3_, double p_i45891_5_, double p_i45891_7_) {
/* 14 */     super(p_i45891_1_, p_i45891_2_, p_i45891_3_);
/* 15 */     this.minimumValue = p_i45891_5_;
/* 16 */     this.maximumValue = p_i45891_7_;
/*    */     
/* 18 */     if (p_i45891_5_ > p_i45891_7_)
/*    */     {
/* 20 */       throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
/*    */     }
/* 22 */     if (p_i45891_3_ < p_i45891_5_)
/*    */     {
/* 24 */       throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
/*    */     }
/* 26 */     if (p_i45891_3_ > p_i45891_7_)
/*    */     {
/* 28 */       throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public RangedAttribute setDescription(String p_111117_1_) {
/* 34 */     this.description = p_111117_1_;
/* 35 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 40 */     return this.description;
/*    */   }
/*    */ 
/*    */   
/*    */   public double clampValue(double p_111109_1_) {
/* 45 */     p_111109_1_ = MathHelper.clamp_double(p_111109_1_, this.minimumValue, this.maximumValue);
/* 46 */     return p_111109_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\attributes\RangedAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */