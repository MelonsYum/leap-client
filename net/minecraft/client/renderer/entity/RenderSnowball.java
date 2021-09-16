/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderSnowball
/*    */   extends Render
/*    */ {
/*    */   protected final Item field_177084_a;
/*    */   private final RenderItem field_177083_e;
/*    */   private static final String __OBFID = "CL_00001008";
/*    */   
/*    */   public RenderSnowball(RenderManager p_i46137_1_, Item p_i46137_2_, RenderItem p_i46137_3_) {
/* 18 */     super(p_i46137_1_);
/* 19 */     this.field_177084_a = p_i46137_2_;
/* 20 */     this.field_177083_e = p_i46137_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 31 */     GlStateManager.pushMatrix();
/* 32 */     GlStateManager.translate((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
/* 33 */     GlStateManager.enableRescaleNormal();
/* 34 */     GlStateManager.scale(0.5F, 0.5F, 0.5F);
/* 35 */     GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 36 */     GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 37 */     bindTexture(TextureMap.locationBlocksTexture);
/* 38 */     this.field_177083_e.func_175043_b(func_177082_d(p_76986_1_));
/* 39 */     GlStateManager.disableRescaleNormal();
/* 40 */     GlStateManager.popMatrix();
/* 41 */     super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_177082_d(Entity p_177082_1_) {
/* 46 */     return new ItemStack(this.field_177084_a, 1, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 54 */     return TextureMap.locationBlocksTexture;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderSnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */