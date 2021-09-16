/*    */ package net.minecraft.client.renderer.tileentity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBook;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityEnchantmentTable;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class TileEntityEnchantmentTableRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 13 */   private static final ResourceLocation field_147540_b = new ResourceLocation("textures/entity/enchanting_table_book.png");
/* 14 */   private ModelBook field_147541_c = new ModelBook();
/*    */   
/*    */   private static final String __OBFID = "CL_00002470";
/*    */   
/*    */   public void func_180537_a(TileEntityEnchantmentTable p_180537_1_, double p_180537_2_, double p_180537_4_, double p_180537_6_, float p_180537_8_, int p_180537_9_) {
/* 19 */     GlStateManager.pushMatrix();
/* 20 */     GlStateManager.translate((float)p_180537_2_ + 0.5F, (float)p_180537_4_ + 0.75F, (float)p_180537_6_ + 0.5F);
/* 21 */     float var10 = p_180537_1_.tickCount + p_180537_8_;
/* 22 */     GlStateManager.translate(0.0F, 0.1F + MathHelper.sin(var10 * 0.1F) * 0.01F, 0.0F);
/*    */     
/*    */     float var11;
/* 25 */     for (var11 = p_180537_1_.bookRotation - p_180537_1_.bookRotationPrev; var11 >= 3.1415927F; var11 -= 6.2831855F);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     while (var11 < -3.1415927F)
/*    */     {
/* 32 */       var11 += 6.2831855F;
/*    */     }
/*    */     
/* 35 */     float var12 = p_180537_1_.bookRotationPrev + var11 * p_180537_8_;
/* 36 */     GlStateManager.rotate(-var12 * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
/* 37 */     GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
/* 38 */     bindTexture(field_147540_b);
/* 39 */     float var13 = p_180537_1_.pageFlipPrev + (p_180537_1_.pageFlip - p_180537_1_.pageFlipPrev) * p_180537_8_ + 0.25F;
/* 40 */     float var14 = p_180537_1_.pageFlipPrev + (p_180537_1_.pageFlip - p_180537_1_.pageFlipPrev) * p_180537_8_ + 0.75F;
/* 41 */     var13 = (var13 - MathHelper.truncateDoubleToInt(var13)) * 1.6F - 0.3F;
/* 42 */     var14 = (var14 - MathHelper.truncateDoubleToInt(var14)) * 1.6F - 0.3F;
/*    */     
/* 44 */     if (var13 < 0.0F)
/*    */     {
/* 46 */       var13 = 0.0F;
/*    */     }
/*    */     
/* 49 */     if (var14 < 0.0F)
/*    */     {
/* 51 */       var14 = 0.0F;
/*    */     }
/*    */     
/* 54 */     if (var13 > 1.0F)
/*    */     {
/* 56 */       var13 = 1.0F;
/*    */     }
/*    */     
/* 59 */     if (var14 > 1.0F)
/*    */     {
/* 61 */       var14 = 1.0F;
/*    */     }
/*    */     
/* 64 */     float var15 = p_180537_1_.bookSpreadPrev + (p_180537_1_.bookSpread - p_180537_1_.bookSpreadPrev) * p_180537_8_;
/* 65 */     GlStateManager.enableCull();
/* 66 */     this.field_147541_c.render(null, var10, var13, var14, var15, 0.0F, 0.0625F);
/* 67 */     GlStateManager.popMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 72 */     func_180537_a((TileEntityEnchantmentTable)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityEnchantmentTableRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */