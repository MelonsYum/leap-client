/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityDiggingFX
/*    */   extends EntityFX {
/*    */   private IBlockState field_174847_a;
/*    */   private static final String __OBFID = "CL_00000932";
/*    */   
/*    */   protected EntityDiggingFX(World worldIn, double p_i46280_2_, double p_i46280_4_, double p_i46280_6_, double p_i46280_8_, double p_i46280_10_, double p_i46280_12_, IBlockState p_i46280_14_) {
/* 19 */     super(worldIn, p_i46280_2_, p_i46280_4_, p_i46280_6_, p_i46280_8_, p_i46280_10_, p_i46280_12_);
/* 20 */     this.field_174847_a = p_i46280_14_;
/* 21 */     func_180435_a(Minecraft.getMinecraft().getBlockRendererDispatcher().func_175023_a().func_178122_a(p_i46280_14_));
/* 22 */     this.particleGravity = (p_i46280_14_.getBlock()).blockParticleGravity;
/* 23 */     this.particleRed = this.particleGreen = this.particleBlue = 0.6F;
/* 24 */     this.particleScale /= 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityDiggingFX func_174846_a(BlockPos p_174846_1_) {
/* 29 */     if (this.field_174847_a.getBlock() == Blocks.grass)
/*    */     {
/* 31 */       return this;
/*    */     }
/*    */ 
/*    */     
/* 35 */     int var2 = this.field_174847_a.getBlock().colorMultiplier((IBlockAccess)this.worldObj, p_174846_1_);
/* 36 */     this.particleRed *= (var2 >> 16 & 0xFF) / 255.0F;
/* 37 */     this.particleGreen *= (var2 >> 8 & 0xFF) / 255.0F;
/* 38 */     this.particleBlue *= (var2 & 0xFF) / 255.0F;
/* 39 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityDiggingFX func_174845_l() {
/* 45 */     Block var1 = this.field_174847_a.getBlock();
/*    */     
/* 47 */     if (var1 == Blocks.grass)
/*    */     {
/* 49 */       return this;
/*    */     }
/*    */ 
/*    */     
/* 53 */     int var2 = var1.getRenderColor(this.field_174847_a);
/* 54 */     this.particleRed *= (var2 >> 16 & 0xFF) / 255.0F;
/* 55 */     this.particleGreen *= (var2 >> 8 & 0xFF) / 255.0F;
/* 56 */     this.particleBlue *= (var2 & 0xFF) / 255.0F;
/* 57 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 63 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 68 */     float var9 = (this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
/* 69 */     float var10 = var9 + 0.015609375F;
/* 70 */     float var11 = (this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
/* 71 */     float var12 = var11 + 0.015609375F;
/* 72 */     float var13 = 0.1F * this.particleScale;
/*    */     
/* 74 */     if (this.particleIcon != null) {
/*    */       
/* 76 */       var9 = this.particleIcon.getInterpolatedU((this.particleTextureJitterX / 4.0F * 16.0F));
/* 77 */       var10 = this.particleIcon.getInterpolatedU(((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
/* 78 */       var11 = this.particleIcon.getInterpolatedV((this.particleTextureJitterY / 4.0F * 16.0F));
/* 79 */       var12 = this.particleIcon.getInterpolatedV(((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
/*    */     } 
/*    */     
/* 82 */     float var14 = (float)(this.prevPosX + (this.posX - this.prevPosX) * p_180434_3_ - interpPosX);
/* 83 */     float var15 = (float)(this.prevPosY + (this.posY - this.prevPosY) * p_180434_3_ - interpPosY);
/* 84 */     float var16 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * p_180434_3_ - interpPosZ);
/* 85 */     p_180434_1_.func_178986_b(this.particleRed, this.particleGreen, this.particleBlue);
/* 86 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 - p_180434_6_ * var13 - p_180434_8_ * var13), var9, var12);
/* 87 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 - p_180434_6_ * var13 + p_180434_8_ * var13), var9, var11);
/* 88 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 + p_180434_6_ * var13 + p_180434_8_ * var13), var10, var11);
/* 89 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 + p_180434_6_ * var13 - p_180434_8_ * var13), var10, var12);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002575";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 98 */       return (new EntityDiggingFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_, Block.getStateById(p_178902_15_[0]))).func_174845_l();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityDiggingFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */