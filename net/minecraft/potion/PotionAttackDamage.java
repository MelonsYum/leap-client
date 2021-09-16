/*    */ package net.minecraft.potion;
/*    */ 
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class PotionAttackDamage
/*    */   extends Potion
/*    */ {
/*    */   private static final String __OBFID = "CL_00001525";
/*    */   
/*    */   protected PotionAttackDamage(int p_i45900_1_, ResourceLocation p_i45900_2_, boolean p_i45900_3_, int p_i45900_4_) {
/* 12 */     super(p_i45900_1_, p_i45900_2_, p_i45900_3_, p_i45900_4_);
/*    */   }
/*    */ 
/*    */   
/*    */   public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_) {
/* 17 */     return (this.id == Potion.weakness.id) ? (-0.5F * (p_111183_1_ + 1)) : (1.3D * (p_111183_1_ + 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\potion\PotionAttackDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */