/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityHugeExplodeFX
/*    */   extends EntityFX
/*    */ {
/*    */   private int timeSinceStart;
/* 13 */   private int maximumTime = 8;
/*    */   
/*    */   private static final String __OBFID = "CL_00000911";
/*    */   
/*    */   protected EntityHugeExplodeFX(World worldIn, double p_i1214_2_, double p_i1214_4_, double p_i1214_6_, double p_i1214_8_, double p_i1214_10_, double p_i1214_12_) {
/* 18 */     super(worldIn, p_i1214_2_, p_i1214_4_, p_i1214_6_, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 28 */     for (int var1 = 0; var1 < 6; var1++) {
/*    */       
/* 30 */       double var2 = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
/* 31 */       double var4 = this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
/* 32 */       double var6 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
/* 33 */       this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, var2, var4, var6, (this.timeSinceStart / this.maximumTime), 0.0D, 0.0D, new int[0]);
/*    */     } 
/*    */     
/* 36 */     this.timeSinceStart++;
/*    */     
/* 38 */     if (this.timeSinceStart == this.maximumTime)
/*    */     {
/* 40 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 46 */     return 1;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002597";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 55 */       return new EntityHugeExplodeFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityHugeExplodeFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */