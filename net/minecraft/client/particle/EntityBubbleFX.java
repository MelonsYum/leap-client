/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityBubbleFX
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00000898";
/*    */   
/*    */   protected EntityBubbleFX(World worldIn, double p_i1198_2_, double p_i1198_4_, double p_i1198_6_, double p_i1198_8_, double p_i1198_10_, double p_i1198_12_) {
/* 13 */     super(worldIn, p_i1198_2_, p_i1198_4_, p_i1198_6_, p_i1198_8_, p_i1198_10_, p_i1198_12_);
/* 14 */     this.particleRed = 1.0F;
/* 15 */     this.particleGreen = 1.0F;
/* 16 */     this.particleBlue = 1.0F;
/* 17 */     setParticleTextureIndex(32);
/* 18 */     setSize(0.02F, 0.02F);
/* 19 */     this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
/* 20 */     this.motionX = p_i1198_8_ * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D;
/* 21 */     this.motionY = p_i1198_10_ * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D;
/* 22 */     this.motionZ = p_i1198_12_ * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D;
/* 23 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 31 */     this.prevPosX = this.posX;
/* 32 */     this.prevPosY = this.posY;
/* 33 */     this.prevPosZ = this.posZ;
/* 34 */     this.motionY += 0.002D;
/* 35 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 36 */     this.motionX *= 0.8500000238418579D;
/* 37 */     this.motionY *= 0.8500000238418579D;
/* 38 */     this.motionZ *= 0.8500000238418579D;
/*    */     
/* 40 */     if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.water)
/*    */     {
/* 42 */       setDead();
/*    */     }
/*    */     
/* 45 */     if (this.particleMaxAge-- <= 0)
/*    */     {
/* 47 */       setDead();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002610";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 57 */       return new EntityBubbleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityBubbleFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */