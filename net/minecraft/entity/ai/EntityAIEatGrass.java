/*     */ package net.minecraft.entity.ai;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockTallGrass;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.pattern.BlockStateHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIEatGrass extends EntityAIBase {
/*  15 */   private static final Predicate field_179505_b = (Predicate)BlockStateHelper.forBlock((Block)Blocks.tallgrass).func_177637_a((IProperty)BlockTallGrass.field_176497_a, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
/*     */ 
/*     */   
/*     */   private EntityLiving grassEaterEntity;
/*     */ 
/*     */   
/*     */   private World entityWorld;
/*     */   
/*     */   int eatingGrassTimer;
/*     */   
/*     */   private static final String __OBFID = "CL_00001582";
/*     */ 
/*     */   
/*     */   public EntityAIEatGrass(EntityLiving p_i45314_1_) {
/*  29 */     this.grassEaterEntity = p_i45314_1_;
/*  30 */     this.entityWorld = p_i45314_1_.worldObj;
/*  31 */     setMutexBits(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  39 */     if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
/*     */     {
/*  41 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  45 */     BlockPos var1 = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
/*  46 */     return field_179505_b.apply(this.entityWorld.getBlockState(var1)) ? true : ((this.entityWorld.getBlockState(var1.offsetDown()).getBlock() == Blocks.grass));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  55 */     this.eatingGrassTimer = 40;
/*  56 */     this.entityWorld.setEntityState((Entity)this.grassEaterEntity, (byte)10);
/*  57 */     this.grassEaterEntity.getNavigator().clearPathEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  65 */     this.eatingGrassTimer = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  73 */     return (this.eatingGrassTimer > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEatingGrassTimer() {
/*  81 */     return this.eatingGrassTimer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  89 */     this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
/*     */     
/*  91 */     if (this.eatingGrassTimer == 4) {
/*     */       
/*  93 */       BlockPos var1 = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
/*     */       
/*  95 */       if (field_179505_b.apply(this.entityWorld.getBlockState(var1))) {
/*     */         
/*  97 */         if (this.entityWorld.getGameRules().getGameRuleBooleanValue("mobGriefing"))
/*     */         {
/*  99 */           this.entityWorld.destroyBlock(var1, false);
/*     */         }
/*     */         
/* 102 */         this.grassEaterEntity.eatGrassBonus();
/*     */       }
/*     */       else {
/*     */         
/* 106 */         BlockPos var2 = var1.offsetDown();
/*     */         
/* 108 */         if (this.entityWorld.getBlockState(var2).getBlock() == Blocks.grass) {
/*     */           
/* 110 */           if (this.entityWorld.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */             
/* 112 */             this.entityWorld.playAuxSFX(2001, var2, Block.getIdFromBlock((Block)Blocks.grass));
/* 113 */             this.entityWorld.setBlockState(var2, Blocks.dirt.getDefaultState(), 2);
/*     */           } 
/*     */           
/* 116 */           this.grassEaterEntity.eatGrassBonus();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIEatGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */