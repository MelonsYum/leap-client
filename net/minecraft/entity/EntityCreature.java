/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityCreature extends EntityLiving {
/*  14 */   public static final UUID field_110179_h = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
/*  15 */   public static final AttributeModifier field_110181_i = (new AttributeModifier(field_110179_h, "Fleeing speed bonus", 2.0D, 2)).setSaved(false);
/*     */   
/*     */   private BlockPos homePosition;
/*     */   
/*     */   private float maximumHomeDistance;
/*     */   
/*     */   private EntityAIBase aiBase;
/*     */   private boolean field_110180_bt;
/*     */   private static final String __OBFID = "CL_00001558";
/*     */   
/*     */   public EntityCreature(World worldIn) {
/*  26 */     super(worldIn);
/*  27 */     this.homePosition = BlockPos.ORIGIN;
/*  28 */     this.maximumHomeDistance = -1.0F;
/*  29 */     this.aiBase = (EntityAIBase)new EntityAIMoveTowardsRestriction(this, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_180484_a(BlockPos p_180484_1_) {
/*  34 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/*  42 */     return (super.getCanSpawnHere() && func_180484_a(new BlockPos(this.posX, (getEntityBoundingBox()).minY, this.posZ)) >= 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPath() {
/*  50 */     return !this.navigator.noPath();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWithinHomeDistanceCurrentPosition() {
/*  55 */     return func_180485_d(new BlockPos(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180485_d(BlockPos p_180485_1_) {
/*  60 */     return (this.maximumHomeDistance == -1.0F) ? true : ((this.homePosition.distanceSq((Vec3i)p_180485_1_) < (this.maximumHomeDistance * this.maximumHomeDistance)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175449_a(BlockPos p_175449_1_, int p_175449_2_) {
/*  65 */     this.homePosition = p_175449_1_;
/*  66 */     this.maximumHomeDistance = p_175449_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180486_cf() {
/*  71 */     return this.homePosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaximumHomeDistance() {
/*  76 */     return this.maximumHomeDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void detachHome() {
/*  81 */     this.maximumHomeDistance = -1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasHome() {
/*  89 */     return (this.maximumHomeDistance != -1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateLeashedState() {
/*  97 */     super.updateLeashedState();
/*     */     
/*  99 */     if (getLeashed() && getLeashedToEntity() != null && (getLeashedToEntity()).worldObj == this.worldObj) {
/*     */       
/* 101 */       Entity var1 = getLeashedToEntity();
/* 102 */       func_175449_a(new BlockPos((int)var1.posX, (int)var1.posY, (int)var1.posZ), 5);
/* 103 */       float var2 = getDistanceToEntity(var1);
/*     */       
/* 105 */       if (this instanceof EntityTameable && ((EntityTameable)this).isSitting()) {
/*     */         
/* 107 */         if (var2 > 10.0F)
/*     */         {
/* 109 */           clearLeashed(true, true);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 115 */       if (!this.field_110180_bt) {
/*     */         
/* 117 */         this.tasks.addTask(2, this.aiBase);
/*     */         
/* 119 */         if (getNavigator() instanceof PathNavigateGround)
/*     */         {
/* 121 */           ((PathNavigateGround)getNavigator()).func_179690_a(false);
/*     */         }
/*     */         
/* 124 */         this.field_110180_bt = true;
/*     */       } 
/*     */       
/* 127 */       func_142017_o(var2);
/*     */       
/* 129 */       if (var2 > 4.0F)
/*     */       {
/* 131 */         getNavigator().tryMoveToEntityLiving(var1, 1.0D);
/*     */       }
/*     */       
/* 134 */       if (var2 > 6.0F) {
/*     */         
/* 136 */         double var3 = (var1.posX - this.posX) / var2;
/* 137 */         double var5 = (var1.posY - this.posY) / var2;
/* 138 */         double var7 = (var1.posZ - this.posZ) / var2;
/* 139 */         this.motionX += var3 * Math.abs(var3) * 0.4D;
/* 140 */         this.motionY += var5 * Math.abs(var5) * 0.4D;
/* 141 */         this.motionZ += var7 * Math.abs(var7) * 0.4D;
/*     */       } 
/*     */       
/* 144 */       if (var2 > 10.0F)
/*     */       {
/* 146 */         clearLeashed(true, true);
/*     */       }
/*     */     }
/* 149 */     else if (!getLeashed() && this.field_110180_bt) {
/*     */       
/* 151 */       this.field_110180_bt = false;
/* 152 */       this.tasks.removeTask(this.aiBase);
/*     */       
/* 154 */       if (getNavigator() instanceof PathNavigateGround)
/*     */       {
/* 156 */         ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*     */       }
/*     */       
/* 159 */       detachHome();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_142017_o(float p_142017_1_) {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityCreature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */