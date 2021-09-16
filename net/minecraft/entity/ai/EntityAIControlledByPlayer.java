/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.pathfinder.WalkNodeProcessor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIControlledByPlayer
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityLiving thisEntity;
/*     */   private final float maxSpeed;
/*     */   private float currentSpeed;
/*     */   private boolean speedBoosted;
/*     */   private int speedBoostTime;
/*     */   private int maxSpeedBoostTime;
/*     */   private static final String __OBFID = "CL_00001580";
/*     */   
/*     */   public EntityAIControlledByPlayer(EntityLiving p_i1620_1_, float p_i1620_2_) {
/*  36 */     this.thisEntity = p_i1620_1_;
/*  37 */     this.maxSpeed = p_i1620_2_;
/*  38 */     setMutexBits(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  46 */     this.currentSpeed = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  54 */     this.speedBoosted = false;
/*  55 */     this.currentSpeed = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  63 */     return (this.thisEntity.isEntityAlive() && this.thisEntity.riddenByEntity != null && this.thisEntity.riddenByEntity instanceof EntityPlayer && (this.speedBoosted || this.thisEntity.canBeSteered()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  71 */     EntityPlayer var1 = (EntityPlayer)this.thisEntity.riddenByEntity;
/*  72 */     EntityCreature var2 = (EntityCreature)this.thisEntity;
/*  73 */     float var3 = MathHelper.wrapAngleTo180_float(var1.rotationYaw - this.thisEntity.rotationYaw) * 0.5F;
/*     */     
/*  75 */     if (var3 > 5.0F)
/*     */     {
/*  77 */       var3 = 5.0F;
/*     */     }
/*     */     
/*  80 */     if (var3 < -5.0F)
/*     */     {
/*  82 */       var3 = -5.0F;
/*     */     }
/*     */     
/*  85 */     this.thisEntity.rotationYaw = MathHelper.wrapAngleTo180_float(this.thisEntity.rotationYaw + var3);
/*     */     
/*  87 */     if (this.currentSpeed < this.maxSpeed)
/*     */     {
/*  89 */       this.currentSpeed += (this.maxSpeed - this.currentSpeed) * 0.01F;
/*     */     }
/*     */     
/*  92 */     if (this.currentSpeed > this.maxSpeed)
/*     */     {
/*  94 */       this.currentSpeed = this.maxSpeed;
/*     */     }
/*     */     
/*  97 */     int var4 = MathHelper.floor_double(this.thisEntity.posX);
/*  98 */     int var5 = MathHelper.floor_double(this.thisEntity.posY);
/*  99 */     int var6 = MathHelper.floor_double(this.thisEntity.posZ);
/* 100 */     float var7 = this.currentSpeed;
/*     */     
/* 102 */     if (this.speedBoosted) {
/*     */       
/* 104 */       if (this.speedBoostTime++ > this.maxSpeedBoostTime)
/*     */       {
/* 106 */         this.speedBoosted = false;
/*     */       }
/*     */       
/* 109 */       var7 += var7 * 1.15F * MathHelper.sin(this.speedBoostTime / this.maxSpeedBoostTime * 3.1415927F);
/*     */     } 
/*     */     
/* 112 */     float var8 = 0.91F;
/*     */     
/* 114 */     if (this.thisEntity.onGround)
/*     */     {
/* 116 */       var8 = (this.thisEntity.worldObj.getBlockState(new BlockPos(MathHelper.floor_float(var4), MathHelper.floor_float(var5) - 1, MathHelper.floor_float(var6))).getBlock()).slipperiness * 0.91F;
/*     */     }
/*     */     
/* 119 */     float var9 = 0.16277136F / var8 * var8 * var8;
/* 120 */     float var10 = MathHelper.sin(var2.rotationYaw * 3.1415927F / 180.0F);
/* 121 */     float var11 = MathHelper.cos(var2.rotationYaw * 3.1415927F / 180.0F);
/* 122 */     float var12 = var2.getAIMoveSpeed() * var9;
/* 123 */     float var13 = Math.max(var7, 1.0F);
/* 124 */     var13 = var12 / var13;
/* 125 */     float var14 = var7 * var13;
/* 126 */     float var15 = -(var14 * var10);
/* 127 */     float var16 = var14 * var11;
/*     */     
/* 129 */     if (MathHelper.abs(var15) > MathHelper.abs(var16)) {
/*     */       
/* 131 */       if (var15 < 0.0F)
/*     */       {
/* 133 */         var15 -= this.thisEntity.width / 2.0F;
/*     */       }
/*     */       
/* 136 */       if (var15 > 0.0F)
/*     */       {
/* 138 */         var15 += this.thisEntity.width / 2.0F;
/*     */       }
/*     */       
/* 141 */       var16 = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 145 */       var15 = 0.0F;
/*     */       
/* 147 */       if (var16 < 0.0F)
/*     */       {
/* 149 */         var16 -= this.thisEntity.width / 2.0F;
/*     */       }
/*     */       
/* 152 */       if (var16 > 0.0F)
/*     */       {
/* 154 */         var16 += this.thisEntity.width / 2.0F;
/*     */       }
/*     */     } 
/*     */     
/* 158 */     int var17 = MathHelper.floor_double(this.thisEntity.posX + var15);
/* 159 */     int var18 = MathHelper.floor_double(this.thisEntity.posZ + var16);
/* 160 */     int var19 = MathHelper.floor_float(this.thisEntity.width + 1.0F);
/* 161 */     int var20 = MathHelper.floor_float(this.thisEntity.height + var1.height + 1.0F);
/* 162 */     int var21 = MathHelper.floor_float(this.thisEntity.width + 1.0F);
/*     */     
/* 164 */     if (var4 != var17 || var6 != var18) {
/*     */       
/* 166 */       Block var22 = this.thisEntity.worldObj.getBlockState(new BlockPos(var4, var5, var6)).getBlock();
/* 167 */       boolean var23 = (!isStairOrSlab(var22) && (var22.getMaterial() != Material.air || !isStairOrSlab(this.thisEntity.worldObj.getBlockState(new BlockPos(var4, var5 - 1, var6)).getBlock())));
/*     */       
/* 169 */       if (var23 && WalkNodeProcessor.func_176170_a((IBlockAccess)this.thisEntity.worldObj, (Entity)this.thisEntity, var17, var5, var18, var19, var20, var21, false, false, true) == 0 && 1 == WalkNodeProcessor.func_176170_a((IBlockAccess)this.thisEntity.worldObj, (Entity)this.thisEntity, var4, var5 + 1, var6, var19, var20, var21, false, false, true) && 1 == WalkNodeProcessor.func_176170_a((IBlockAccess)this.thisEntity.worldObj, (Entity)this.thisEntity, var17, var5 + 1, var18, var19, var20, var21, false, false, true))
/*     */       {
/* 171 */         var2.getJumpHelper().setJumping();
/*     */       }
/*     */     } 
/*     */     
/* 175 */     if (!var1.capabilities.isCreativeMode && this.currentSpeed >= this.maxSpeed * 0.5F && this.thisEntity.getRNG().nextFloat() < 0.006F && !this.speedBoosted) {
/*     */       
/* 177 */       ItemStack var24 = var1.getHeldItem();
/*     */       
/* 179 */       if (var24 != null && var24.getItem() == Items.carrot_on_a_stick) {
/*     */         
/* 181 */         var24.damageItem(1, (EntityLivingBase)var1);
/*     */         
/* 183 */         if (var24.stackSize == 0) {
/*     */           
/* 185 */           ItemStack var25 = new ItemStack((Item)Items.fishing_rod);
/* 186 */           var25.setTagCompound(var24.getTagCompound());
/* 187 */           var1.inventory.mainInventory[var1.inventory.currentItem] = var25;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     this.thisEntity.moveEntityWithHeading(0.0F, var7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isStairOrSlab(Block p_151498_1_) {
/* 200 */     return !(!(p_151498_1_ instanceof net.minecraft.block.BlockStairs) && !(p_151498_1_ instanceof net.minecraft.block.BlockSlab));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSpeedBoosted() {
/* 208 */     return this.speedBoosted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void boostSpeed() {
/* 216 */     this.speedBoosted = true;
/* 217 */     this.speedBoostTime = 0;
/* 218 */     this.maxSpeedBoostTime = this.thisEntity.getRNG().nextInt(841) + 140;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isControlledByPlayer() {
/* 226 */     return (!isSpeedBoosted() && this.currentSpeed > this.maxSpeed * 0.3F);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIControlledByPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */