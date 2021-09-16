/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class Barrier
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00002615";
/*    */   
/*    */   protected Barrier(World worldIn, double p_i46286_2_, double p_i46286_4_, double p_i46286_6_, Item p_i46286_8_) {
/* 16 */     super(worldIn, p_i46286_2_, p_i46286_4_, p_i46286_6_, 0.0D, 0.0D, 0.0D);
/* 17 */     func_180435_a(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(p_i46286_8_));
/* 18 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/* 19 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/* 20 */     this.particleGravity = 0.0F;
/* 21 */     this.particleMaxAge = 80;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 26 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 31 */     float var9 = this.particleIcon.getMinU();
/* 32 */     float var10 = this.particleIcon.getMaxU();
/* 33 */     float var11 = this.particleIcon.getMinV();
/* 34 */     float var12 = this.particleIcon.getMaxV();
/* 35 */     float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * p_180434_3_ - interpPosX);
/* 36 */     float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * p_180434_3_ - interpPosY);
/* 37 */     float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * p_180434_3_ - interpPosZ);
/* 38 */     p_180434_1_.func_178986_b(this.particleRed, this.particleGreen, this.particleBlue);
/* 39 */     float var16 = 0.5F;
/* 40 */     p_180434_1_.addVertexWithUV((var13 - p_180434_4_ * var16 - p_180434_7_ * var16), (var14 - p_180434_5_ * var16), (var15 - p_180434_6_ * var16 - p_180434_8_ * var16), var10, var12);
/* 41 */     p_180434_1_.addVertexWithUV((var13 - p_180434_4_ * var16 + p_180434_7_ * var16), (var14 + p_180434_5_ * var16), (var15 - p_180434_6_ * var16 + p_180434_8_ * var16), var10, var11);
/* 42 */     p_180434_1_.addVertexWithUV((var13 + p_180434_4_ * var16 + p_180434_7_ * var16), (var14 + p_180434_5_ * var16), (var15 + p_180434_6_ * var16 + p_180434_8_ * var16), var9, var11);
/* 43 */     p_180434_1_.addVertexWithUV((var13 + p_180434_4_ * var16 - p_180434_7_ * var16), (var14 - p_180434_5_ * var16), (var15 + p_180434_6_ * var16 - p_180434_8_ * var16), var9, var12);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002614";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 52 */       return new Barrier(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, Item.getItemFromBlock(Blocks.barrier));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\Barrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */