/*    */ package net.minecraft.entity.projectile;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityLargeFireball
/*    */   extends EntityFireball {
/* 12 */   public int field_92057_e = 1;
/*    */   
/*    */   private static final String __OBFID = "CL_00001719";
/*    */   
/*    */   public EntityLargeFireball(World worldIn) {
/* 17 */     super(worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLargeFireball(World worldIn, double p_i1768_2_, double p_i1768_4_, double p_i1768_6_, double p_i1768_8_, double p_i1768_10_, double p_i1768_12_) {
/* 22 */     super(worldIn, p_i1768_2_, p_i1768_4_, p_i1768_6_, p_i1768_8_, p_i1768_10_, p_i1768_12_);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLargeFireball(World worldIn, EntityLivingBase p_i1769_2_, double p_i1769_3_, double p_i1769_5_, double p_i1769_7_) {
/* 27 */     super(worldIn, p_i1769_2_, p_i1769_3_, p_i1769_5_, p_i1769_7_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(MovingObjectPosition p_70227_1_) {
/* 35 */     if (!this.worldObj.isRemote) {
/*    */       
/* 37 */       if (p_70227_1_.entityHit != null) {
/*    */         
/* 39 */         p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, (Entity)this.shootingEntity), 6.0F);
/* 40 */         func_174815_a(this.shootingEntity, p_70227_1_.entityHit);
/*    */       } 
/*    */       
/* 43 */       boolean var2 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
/* 44 */       this.worldObj.newExplosion(null, this.posX, this.posY, this.posZ, this.field_92057_e, var2, var2);
/* 45 */       setDead();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 54 */     super.writeEntityToNBT(tagCompound);
/* 55 */     tagCompound.setInteger("ExplosionPower", this.field_92057_e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 63 */     super.readEntityFromNBT(tagCompund);
/*    */     
/* 65 */     if (tagCompund.hasKey("ExplosionPower", 99))
/*    */     {
/* 67 */       this.field_92057_e = tagCompund.getInteger("ExplosionPower");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityLargeFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */