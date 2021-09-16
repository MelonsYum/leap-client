/*    */ package net.minecraft.entity.item;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityEndermite;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityEnderPearl
/*    */   extends EntityThrowable
/*    */ {
/*    */   private static final String __OBFID = "CL_00001725";
/*    */   
/*    */   public EntityEnderPearl(World worldIn, EntityLivingBase p_i1783_2_) {
/* 20 */     super(worldIn, p_i1783_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderPearl(World worldIn, double p_i1784_2_, double p_i1784_4_, double p_i1784_6_) {
/* 25 */     super(worldIn, p_i1784_2_, p_i1784_4_, p_i1784_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(MovingObjectPosition p_70184_1_) {
/* 33 */     EntityLivingBase var2 = getThrower();
/*    */     
/* 35 */     if (p_70184_1_.entityHit != null)
/*    */     {
/* 37 */       p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)var2), 0.0F);
/*    */     }
/*    */     
/* 40 */     for (int var3 = 0; var3 < 32; var3++)
/*    */     {
/* 42 */       this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian(), new int[0]);
/*    */     }
/*    */     
/* 45 */     if (!this.worldObj.isRemote) {
/*    */       
/* 47 */       if (var2 instanceof EntityPlayerMP) {
/*    */         
/* 49 */         EntityPlayerMP var5 = (EntityPlayerMP)var2;
/*    */         
/* 51 */         if (var5.playerNetServerHandler.getNetworkManager().isChannelOpen() && var5.worldObj == this.worldObj && !var5.isPlayerSleeping()) {
/*    */           
/* 53 */           if (this.rand.nextFloat() < 0.05F && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobSpawning")) {
/*    */             
/* 55 */             EntityEndermite var4 = new EntityEndermite(this.worldObj);
/* 56 */             var4.setSpawnedByPlayer(true);
/* 57 */             var4.setLocationAndAngles(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
/* 58 */             this.worldObj.spawnEntityInWorld((Entity)var4);
/*    */           } 
/*    */           
/* 61 */           if (var2.isRiding())
/*    */           {
/* 63 */             var2.mountEntity(null);
/*    */           }
/*    */           
/* 66 */           var2.setPositionAndUpdate(this.posX, this.posY, this.posZ);
/* 67 */           var2.fallDistance = 0.0F;
/* 68 */           var2.attackEntityFrom(DamageSource.fall, 5.0F);
/*    */         } 
/*    */       } 
/*    */       
/* 72 */       setDead();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 81 */     EntityLivingBase var1 = getThrower();
/*    */     
/* 83 */     if (var1 != null && var1 instanceof net.minecraft.entity.player.EntityPlayer && !var1.isEntityAlive()) {
/*    */       
/* 85 */       setDead();
/*    */     }
/*    */     else {
/*    */       
/* 89 */       super.onUpdate();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityEnderPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */