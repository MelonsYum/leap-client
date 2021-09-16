/*    */ package net.minecraft.entity.projectile;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntitySnowball
/*    */   extends EntityThrowable
/*    */ {
/*    */   private static final String __OBFID = "CL_00001722";
/*    */   
/*    */   public EntitySnowball(World worldIn) {
/* 16 */     super(worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySnowball(World worldIn, EntityLivingBase p_i1774_2_) {
/* 21 */     super(worldIn, p_i1774_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySnowball(World worldIn, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
/* 26 */     super(worldIn, p_i1775_2_, p_i1775_4_, p_i1775_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(MovingObjectPosition p_70184_1_) {
/* 34 */     if (p_70184_1_.entityHit != null) {
/*    */       
/* 36 */       byte var2 = 0;
/*    */       
/* 38 */       if (p_70184_1_.entityHit instanceof net.minecraft.entity.monster.EntityBlaze)
/*    */       {
/* 40 */         var2 = 3;
/*    */       }
/*    */       
/* 43 */       p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, (Entity)getThrower()), var2);
/*    */     } 
/*    */     
/* 46 */     for (int var3 = 0; var3 < 8; var3++)
/*    */     {
/* 48 */       this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*    */     }
/*    */     
/* 51 */     if (!this.worldObj.isRemote)
/*    */     {
/* 53 */       setDead();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntitySnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */