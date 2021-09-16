/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityFireworkOverlayFX
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00000904";
/*    */   
/*    */   protected EntityFireworkOverlayFX(World worldIn, double p_i46357_2_, double p_i46357_4_, double p_i46357_6_) {
/* 14 */     super(worldIn, p_i46357_2_, p_i46357_4_, p_i46357_6_);
/* 15 */     this.particleMaxAge = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 20 */     float var9 = 0.25F;
/* 21 */     float var10 = var9 + 0.25F;
/* 22 */     float var11 = 0.125F;
/* 23 */     float var12 = var11 + 0.25F;
/* 24 */     float var13 = 7.1F * MathHelper.sin((this.particleAge + p_180434_3_ - 1.0F) * 0.25F * 3.1415927F);
/* 25 */     this.particleAlpha = 0.6F - (this.particleAge + p_180434_3_ - 1.0F) * 0.25F * 0.5F;
/* 26 */     float var14 = (float)(this.prevPosX + (this.posX - this.prevPosX) * p_180434_3_ - interpPosX);
/* 27 */     float var15 = (float)(this.prevPosY + (this.posY - this.prevPosY) * p_180434_3_ - interpPosY);
/* 28 */     float var16 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * p_180434_3_ - interpPosZ);
/* 29 */     p_180434_1_.func_178960_a(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
/* 30 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 - p_180434_6_ * var13 - p_180434_8_ * var13), var10, var12);
/* 31 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 - p_180434_6_ * var13 + p_180434_8_ * var13), var10, var11);
/* 32 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 + p_180434_6_ * var13 + p_180434_8_ * var13), var9, var11);
/* 33 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 + p_180434_6_ * var13 - p_180434_8_ * var13), var9, var12);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityFireworkOverlayFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */