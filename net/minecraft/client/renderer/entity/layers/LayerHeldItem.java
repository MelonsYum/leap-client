/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class LayerHeldItem
/*    */   implements LayerRenderer
/*    */ {
/*    */   private final RendererLivingEntity field_177206_a;
/*    */   private static final String __OBFID = "CL_00002416";
/*    */   
/*    */   public LayerHeldItem(RendererLivingEntity p_i46115_1_) {
/* 23 */     this.field_177206_a = p_i46115_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 28 */     ItemStack var9 = p_177141_1_.getHeldItem();
/*    */     
/* 30 */     if (var9 != null) {
/*    */       
/* 32 */       GlStateManager.pushMatrix();
/*    */       
/* 34 */       if ((this.field_177206_a.getMainModel()).isChild) {
/*    */         
/* 36 */         float var10 = 0.5F;
/* 37 */         GlStateManager.translate(0.0F, 0.625F, 0.0F);
/* 38 */         GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
/* 39 */         GlStateManager.scale(var10, var10, var10);
/*    */       } 
/*    */       
/* 42 */       ((ModelBiped)this.field_177206_a.getMainModel()).postRenderHiddenArm(0.0625F);
/* 43 */       GlStateManager.translate(-0.0625F, 0.4375F, 0.0625F);
/*    */       
/* 45 */       if (p_177141_1_ instanceof EntityPlayer && ((EntityPlayer)p_177141_1_).fishEntity != null)
/*    */       {
/* 47 */         var9 = new ItemStack((Item)Items.fishing_rod, 0);
/*    */       }
/*    */       
/* 50 */       Item var13 = var9.getItem();
/* 51 */       Minecraft var11 = Minecraft.getMinecraft();
/*    */       
/* 53 */       if (var13 instanceof net.minecraft.item.ItemBlock && Block.getBlockFromItem(var13).getRenderType() == 2) {
/*    */         
/* 55 */         GlStateManager.translate(0.0F, 0.1875F, -0.3125F);
/* 56 */         GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
/* 57 */         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/* 58 */         float var12 = 0.375F;
/* 59 */         GlStateManager.scale(-var12, -var12, var12);
/*    */       } 
/*    */       
/* 62 */       var11.getItemRenderer().renderItem(p_177141_1_, var9, ItemCameraTransforms.TransformType.THIRD_PERSON);
/* 63 */       GlStateManager.popMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerHeldItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */