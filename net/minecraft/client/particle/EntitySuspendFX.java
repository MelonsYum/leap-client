/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntitySuspendFX
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00000928";
/*    */   
/*    */   protected EntitySuspendFX(World worldIn, double p_i1231_2_, double p_i1231_4_, double p_i1231_6_, double p_i1231_8_, double p_i1231_10_, double p_i1231_12_) {
/* 13 */     super(worldIn, p_i1231_2_, p_i1231_4_ - 0.125D, p_i1231_6_, p_i1231_8_, p_i1231_10_, p_i1231_12_);
/* 14 */     this.particleRed = 0.4F;
/* 15 */     this.particleGreen = 0.4F;
/* 16 */     this.particleBlue = 0.7F;
/* 17 */     setParticleTextureIndex(0);
/* 18 */     setSize(0.01F, 0.01F);
/* 19 */     this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
/* 20 */     this.motionX = p_i1231_8_ * 0.0D;
/* 21 */     this.motionY = p_i1231_10_ * 0.0D;
/* 22 */     this.motionZ = p_i1231_12_ * 0.0D;
/* 23 */     this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
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
/* 34 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */     
/* 36 */     if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.water)
/*    */     {
/* 38 */       setDead();
/*    */     }
/*    */     
/* 41 */     if (this.particleMaxAge-- <= 0)
/*    */     {
/* 43 */       setDead();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002579";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 53 */       return new EntitySuspendFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntitySuspendFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */