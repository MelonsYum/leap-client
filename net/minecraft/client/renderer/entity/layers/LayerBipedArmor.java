/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ 
/*    */ public class LayerBipedArmor
/*    */   extends LayerArmorBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00002417";
/*    */   
/*    */   public LayerBipedArmor(RendererLivingEntity p_i46116_1_) {
/* 13 */     super(p_i46116_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_177177_a() {
/* 18 */     this.field_177189_c = (ModelBase)new ModelBiped(0.5F);
/* 19 */     this.field_177186_d = (ModelBase)new ModelBiped(1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_177195_a(ModelBiped p_177195_1_, int p_177195_2_) {
/* 24 */     func_177194_a(p_177195_1_);
/*    */     
/* 26 */     switch (p_177195_2_) {
/*    */       
/*    */       case 1:
/* 29 */         p_177195_1_.bipedRightLeg.showModel = true;
/* 30 */         p_177195_1_.bipedLeftLeg.showModel = true;
/*    */         break;
/*    */       
/*    */       case 2:
/* 34 */         p_177195_1_.bipedBody.showModel = true;
/* 35 */         p_177195_1_.bipedRightLeg.showModel = true;
/* 36 */         p_177195_1_.bipedLeftLeg.showModel = true;
/*    */         break;
/*    */       
/*    */       case 3:
/* 40 */         p_177195_1_.bipedBody.showModel = true;
/* 41 */         p_177195_1_.bipedRightArm.showModel = true;
/* 42 */         p_177195_1_.bipedLeftArm.showModel = true;
/*    */         break;
/*    */       
/*    */       case 4:
/* 46 */         p_177195_1_.bipedHead.showModel = true;
/* 47 */         p_177195_1_.bipedHeadwear.showModel = true;
/*    */         break;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void func_177194_a(ModelBiped p_177194_1_) {
/* 53 */     p_177194_1_.func_178719_a(false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_177179_a(ModelBase p_177179_1_, int p_177179_2_) {
/* 58 */     func_177195_a((ModelBiped)p_177179_1_, p_177179_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerBipedArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */