/*     */ package net.minecraft.entity.projectile;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityWitherSkull
/*     */   extends EntityFireball
/*     */ {
/*     */   private static final String __OBFID = "CL_00001728";
/*     */   
/*     */   public EntityWitherSkull(World worldIn) {
/*  21 */     super(worldIn);
/*  22 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWitherSkull(World worldIn, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_) {
/*  27 */     super(worldIn, p_i1794_2_, p_i1794_3_, p_i1794_5_, p_i1794_7_);
/*  28 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMotionFactor() {
/*  36 */     return isInvulnerable() ? 0.73F : super.getMotionFactor();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWitherSkull(World worldIn, double p_i1795_2_, double p_i1795_4_, double p_i1795_6_, double p_i1795_8_, double p_i1795_10_, double p_i1795_12_) {
/*  41 */     super(worldIn, p_i1795_2_, p_i1795_4_, p_i1795_6_, p_i1795_8_, p_i1795_10_, p_i1795_12_);
/*  42 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getExplosionResistance(Explosion p_180428_1_, World worldIn, BlockPos p_180428_3_, IBlockState p_180428_4_) {
/*  58 */     float var5 = super.getExplosionResistance(p_180428_1_, worldIn, p_180428_3_, p_180428_4_);
/*     */     
/*  60 */     if (isInvulnerable() && p_180428_4_.getBlock() != Blocks.bedrock && p_180428_4_.getBlock() != Blocks.end_portal && p_180428_4_.getBlock() != Blocks.end_portal_frame && p_180428_4_.getBlock() != Blocks.command_block)
/*     */     {
/*  62 */       var5 = Math.min(0.8F, var5);
/*     */     }
/*     */     
/*  65 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition p_70227_1_) {
/*  73 */     if (!this.worldObj.isRemote) {
/*     */       
/*  75 */       if (p_70227_1_.entityHit != null) {
/*     */         
/*  77 */         if (this.shootingEntity != null) {
/*     */           
/*  79 */           if (p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F))
/*     */           {
/*  81 */             if (!p_70227_1_.entityHit.isEntityAlive())
/*     */             {
/*  83 */               this.shootingEntity.heal(5.0F);
/*     */             }
/*     */             else
/*     */             {
/*  87 */               func_174815_a(this.shootingEntity, p_70227_1_.entityHit);
/*     */             }
/*     */           
/*     */           }
/*     */         } else {
/*     */           
/*  93 */           p_70227_1_.entityHit.attackEntityFrom(DamageSource.magic, 5.0F);
/*     */         } 
/*     */         
/*  96 */         if (p_70227_1_.entityHit instanceof EntityLivingBase) {
/*     */           
/*  98 */           byte var2 = 0;
/*     */           
/* 100 */           if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) {
/*     */             
/* 102 */             var2 = 10;
/*     */           }
/* 104 */           else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
/*     */             
/* 106 */             var2 = 40;
/*     */           } 
/*     */           
/* 109 */           if (var2 > 0)
/*     */           {
/* 111 */             ((EntityLivingBase)p_70227_1_.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * var2, 1));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 116 */       this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
/* 117 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 139 */     this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInvulnerable() {
/* 147 */     return (this.dataWatcher.getWatchableObjectByte(10) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInvulnerable(boolean p_82343_1_) {
/* 155 */     this.dataWatcher.updateObject(10, Byte.valueOf((byte)(p_82343_1_ ? 1 : 0)));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityWitherSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */