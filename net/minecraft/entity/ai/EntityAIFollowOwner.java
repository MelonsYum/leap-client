/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIFollowOwner extends EntityAIBase {
/*     */   private EntityTameable thePet;
/*     */   private EntityLivingBase theOwner;
/*     */   World theWorld;
/*     */   private double field_75336_f;
/*     */   private PathNavigate petPathfinder;
/*     */   private int field_75343_h;
/*     */   float maxDist;
/*     */   float minDist;
/*     */   private boolean field_75344_i;
/*     */   private static final String __OBFID = "CL_00001585";
/*     */   
/*     */   public EntityAIFollowOwner(EntityTameable p_i1625_1_, double p_i1625_2_, float p_i1625_4_, float p_i1625_5_) {
/*  26 */     this.thePet = p_i1625_1_;
/*  27 */     this.theWorld = p_i1625_1_.worldObj;
/*  28 */     this.field_75336_f = p_i1625_2_;
/*  29 */     this.petPathfinder = p_i1625_1_.getNavigator();
/*  30 */     this.minDist = p_i1625_4_;
/*  31 */     this.maxDist = p_i1625_5_;
/*  32 */     setMutexBits(3);
/*     */     
/*  34 */     if (!(p_i1625_1_.getNavigator() instanceof PathNavigateGround))
/*     */     {
/*  36 */       throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  45 */     EntityLivingBase var1 = this.thePet.func_180492_cm();
/*     */     
/*  47 */     if (var1 == null)
/*     */     {
/*  49 */       return false;
/*     */     }
/*  51 */     if (this.thePet.isSitting())
/*     */     {
/*  53 */       return false;
/*     */     }
/*  55 */     if (this.thePet.getDistanceSqToEntity((Entity)var1) < (this.minDist * this.minDist))
/*     */     {
/*  57 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  61 */     this.theOwner = var1;
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  71 */     return (!this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity((Entity)this.theOwner) > (this.maxDist * this.maxDist) && !this.thePet.isSitting());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  79 */     this.field_75343_h = 0;
/*  80 */     this.field_75344_i = ((PathNavigateGround)this.thePet.getNavigator()).func_179689_e();
/*  81 */     ((PathNavigateGround)this.thePet.getNavigator()).func_179690_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  89 */     this.theOwner = null;
/*  90 */     this.petPathfinder.clearPathEntity();
/*  91 */     ((PathNavigateGround)this.thePet.getNavigator()).func_179690_a(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  99 */     this.thePet.getLookHelper().setLookPositionWithEntity((Entity)this.theOwner, 10.0F, this.thePet.getVerticalFaceSpeed());
/*     */     
/* 101 */     if (!this.thePet.isSitting())
/*     */     {
/* 103 */       if (--this.field_75343_h <= 0) {
/*     */         
/* 105 */         this.field_75343_h = 10;
/*     */         
/* 107 */         if (!this.petPathfinder.tryMoveToEntityLiving((Entity)this.theOwner, this.field_75336_f))
/*     */         {
/* 109 */           if (!this.thePet.getLeashed())
/*     */           {
/* 111 */             if (this.thePet.getDistanceSqToEntity((Entity)this.theOwner) >= 144.0D) {
/*     */               
/* 113 */               int var1 = MathHelper.floor_double(this.theOwner.posX) - 2;
/* 114 */               int var2 = MathHelper.floor_double(this.theOwner.posZ) - 2;
/* 115 */               int var3 = MathHelper.floor_double((this.theOwner.getEntityBoundingBox()).minY);
/*     */               
/* 117 */               for (int var4 = 0; var4 <= 4; var4++) {
/*     */                 
/* 119 */                 for (int var5 = 0; var5 <= 4; var5++) {
/*     */                   
/* 121 */                   if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && World.doesBlockHaveSolidTopSurface((IBlockAccess)this.theWorld, new BlockPos(var1 + var4, var3 - 1, var2 + var5)) && !this.theWorld.getBlockState(new BlockPos(var1 + var4, var3, var2 + var5)).getBlock().isFullCube() && !this.theWorld.getBlockState(new BlockPos(var1 + var4, var3 + 1, var2 + var5)).getBlock().isFullCube()) {
/*     */                     
/* 123 */                     this.thePet.setLocationAndAngles(((var1 + var4) + 0.5F), var3, ((var2 + var5) + 0.5F), this.thePet.rotationYaw, this.thePet.rotationPitch);
/* 124 */                     this.petPathfinder.clearPathEntity();
/*     */                     return;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           }
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIFollowOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */