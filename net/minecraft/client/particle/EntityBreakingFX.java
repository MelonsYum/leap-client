/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityBreakingFX
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00000897";
/*    */   
/*    */   protected EntityBreakingFX(World worldIn, double p_i1195_2_, double p_i1195_4_, double p_i1195_6_, Item p_i1195_8_) {
/* 17 */     this(worldIn, p_i1195_2_, p_i1195_4_, p_i1195_6_, p_i1195_8_, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityBreakingFX(World worldIn, double p_i1197_2_, double p_i1197_4_, double p_i1197_6_, double p_i1197_8_, double p_i1197_10_, double p_i1197_12_, Item p_i1197_14_, int p_i1197_15_) {
/* 22 */     this(worldIn, p_i1197_2_, p_i1197_4_, p_i1197_6_, p_i1197_14_, p_i1197_15_);
/* 23 */     this.motionX *= 0.10000000149011612D;
/* 24 */     this.motionY *= 0.10000000149011612D;
/* 25 */     this.motionZ *= 0.10000000149011612D;
/* 26 */     this.motionX += p_i1197_8_;
/* 27 */     this.motionY += p_i1197_10_;
/* 28 */     this.motionZ += p_i1197_12_;
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityBreakingFX(World worldIn, double p_i1196_2_, double p_i1196_4_, double p_i1196_6_, Item p_i1196_8_, int p_i1196_9_) {
/* 33 */     super(worldIn, p_i1196_2_, p_i1196_4_, p_i1196_6_, 0.0D, 0.0D, 0.0D);
/* 34 */     func_180435_a(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(p_i1196_8_, p_i1196_9_));
/* 35 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/* 36 */     this.particleGravity = Blocks.snow.blockParticleGravity;
/* 37 */     this.particleScale /= 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 42 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 47 */     float var9 = (this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
/* 48 */     float var10 = var9 + 0.015609375F;
/* 49 */     float var11 = (this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
/* 50 */     float var12 = var11 + 0.015609375F;
/* 51 */     float var13 = 0.1F * this.particleScale;
/*    */     
/* 53 */     if (this.particleIcon != null) {
/*    */       
/* 55 */       var9 = this.particleIcon.getInterpolatedU((this.particleTextureJitterX / 4.0F * 16.0F));
/* 56 */       var10 = this.particleIcon.getInterpolatedU(((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
/* 57 */       var11 = this.particleIcon.getInterpolatedV((this.particleTextureJitterY / 4.0F * 16.0F));
/* 58 */       var12 = this.particleIcon.getInterpolatedV(((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
/*    */     } 
/*    */     
/* 61 */     float var14 = (float)(this.prevPosX + (this.posX - this.prevPosX) * p_180434_3_ - interpPosX);
/* 62 */     float var15 = (float)(this.prevPosY + (this.posY - this.prevPosY) * p_180434_3_ - interpPosY);
/* 63 */     float var16 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * p_180434_3_ - interpPosZ);
/* 64 */     p_180434_1_.func_178986_b(this.particleRed, this.particleGreen, this.particleBlue);
/* 65 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 - p_180434_6_ * var13 - p_180434_8_ * var13), var9, var12);
/* 66 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 - p_180434_6_ * var13 + p_180434_8_ * var13), var9, var11);
/* 67 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 + p_180434_6_ * var13 + p_180434_8_ * var13), var10, var11);
/* 68 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 + p_180434_6_ * var13 - p_180434_8_ * var13), var10, var12);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002613";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 77 */       int var16 = (p_178902_15_.length > 1) ? p_178902_15_[1] : 0;
/* 78 */       return new EntityBreakingFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_, Item.getItemById(p_178902_15_[0]), var16);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class SlimeFactory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002612";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 88 */       return new EntityBreakingFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, Items.slime_ball);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class SnowballFactory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002611";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 98 */       return new EntityBreakingFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, Items.snowball);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityBreakingFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */