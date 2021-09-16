/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.village.VillageDoorInfo;
/*     */ 
/*     */ public class EntityAIMoveThroughVillage
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityCreature theEntity;
/*     */   private double movementSpeed;
/*     */   private PathEntity entityPathNavigate;
/*     */   private VillageDoorInfo doorInfo;
/*     */   private boolean isNocturnal;
/*  24 */   private List doorList = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00001597";
/*     */   
/*     */   public EntityAIMoveThroughVillage(EntityCreature p_i1638_1_, double p_i1638_2_, boolean p_i1638_4_) {
/*  29 */     this.theEntity = p_i1638_1_;
/*  30 */     this.movementSpeed = p_i1638_2_;
/*  31 */     this.isNocturnal = p_i1638_4_;
/*  32 */     setMutexBits(1);
/*     */     
/*  34 */     if (!(p_i1638_1_.getNavigator() instanceof PathNavigateGround))
/*     */     {
/*  36 */       throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  45 */     func_75414_f();
/*     */     
/*  47 */     if (this.isNocturnal && this.theEntity.worldObj.isDaytime())
/*     */     {
/*  49 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  53 */     Village var1 = this.theEntity.worldObj.getVillageCollection().func_176056_a(new BlockPos((Entity)this.theEntity), 0);
/*     */     
/*  55 */     if (var1 == null)
/*     */     {
/*  57 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  61 */     this.doorInfo = func_75412_a(var1);
/*     */     
/*  63 */     if (this.doorInfo == null)
/*     */     {
/*  65 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  69 */     PathNavigateGround var2 = (PathNavigateGround)this.theEntity.getNavigator();
/*  70 */     boolean var3 = var2.func_179686_g();
/*  71 */     var2.func_179688_b(false);
/*  72 */     this.entityPathNavigate = var2.func_179680_a(this.doorInfo.func_179852_d());
/*  73 */     var2.func_179688_b(var3);
/*     */     
/*  75 */     if (this.entityPathNavigate != null)
/*     */     {
/*  77 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  81 */     Vec3 var4 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 10, 7, new Vec3(this.doorInfo.func_179852_d().getX(), this.doorInfo.func_179852_d().getY(), this.doorInfo.func_179852_d().getZ()));
/*     */     
/*  83 */     if (var4 == null)
/*     */     {
/*  85 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  89 */     var2.func_179688_b(false);
/*  90 */     this.entityPathNavigate = this.theEntity.getNavigator().getPathToXYZ(var4.xCoord, var4.yCoord, var4.zCoord);
/*  91 */     var2.func_179688_b(var3);
/*  92 */     return (this.entityPathNavigate != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 105 */     if (this.theEntity.getNavigator().noPath())
/*     */     {
/* 107 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 111 */     float var1 = this.theEntity.width + 4.0F;
/* 112 */     return (this.theEntity.getDistanceSq(this.doorInfo.func_179852_d()) > (var1 * var1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 121 */     this.theEntity.getNavigator().setPath(this.entityPathNavigate, this.movementSpeed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 129 */     if (this.theEntity.getNavigator().noPath() || this.theEntity.getDistanceSq(this.doorInfo.func_179852_d()) < 16.0D)
/*     */     {
/* 131 */       this.doorList.add(this.doorInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private VillageDoorInfo func_75412_a(Village p_75412_1_) {
/* 137 */     VillageDoorInfo var2 = null;
/* 138 */     int var3 = Integer.MAX_VALUE;
/* 139 */     List var4 = p_75412_1_.getVillageDoorInfoList();
/* 140 */     Iterator<VillageDoorInfo> var5 = var4.iterator();
/*     */     
/* 142 */     while (var5.hasNext()) {
/*     */       
/* 144 */       VillageDoorInfo var6 = var5.next();
/* 145 */       int var7 = var6.getDistanceSquared(MathHelper.floor_double(this.theEntity.posX), MathHelper.floor_double(this.theEntity.posY), MathHelper.floor_double(this.theEntity.posZ));
/*     */       
/* 147 */       if (var7 < var3 && !func_75413_a(var6)) {
/*     */         
/* 149 */         var2 = var6;
/* 150 */         var3 = var7;
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     return var2;
/*     */   }
/*     */   
/*     */   private boolean func_75413_a(VillageDoorInfo p_75413_1_) {
/*     */     VillageDoorInfo var3;
/* 159 */     Iterator<VillageDoorInfo> var2 = this.doorList.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 164 */       if (!var2.hasNext())
/*     */       {
/* 166 */         return false;
/*     */       }
/*     */       
/* 169 */       var3 = var2.next();
/*     */     }
/* 171 */     while (!p_75413_1_.func_179852_d().equals(var3.func_179852_d()));
/*     */     
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_75414_f() {
/* 178 */     if (this.doorList.size() > 15)
/*     */     {
/* 180 */       this.doorList.remove(0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIMoveThroughVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */