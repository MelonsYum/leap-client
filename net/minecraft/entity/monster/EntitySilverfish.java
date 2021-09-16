/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSilverfish;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySilverfish extends EntityMob {
/*     */   private AISummonSilverfish field_175460_b;
/*     */   private static final String __OBFID = "CL_00001696";
/*     */   
/*     */   public EntitySilverfish(World worldIn) {
/*  31 */     super(worldIn);
/*  32 */     setSize(0.4F, 0.3F);
/*  33 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  34 */     this.tasks.addTask(3, this.field_175460_b = new AISummonSilverfish());
/*  35 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  36 */     this.tasks.addTask(5, (EntityAIBase)new AIHideInStone());
/*  37 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget(this, true, new Class[0]));
/*  38 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/*  43 */     return 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  48 */     super.applyEntityAttributes();
/*  49 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
/*  50 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*  51 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  68 */     return "mob.silverfish.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  76 */     return "mob.silverfish.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  84 */     return "mob.silverfish.kill";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  92 */     if (func_180431_b(source))
/*     */     {
/*  94 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  98 */     if (source instanceof net.minecraft.util.EntityDamageSource || source == DamageSource.magic)
/*     */     {
/* 100 */       this.field_175460_b.func_179462_f();
/*     */     }
/*     */     
/* 103 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 109 */     playSound("mob.silverfish.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 122 */     this.renderYawOffset = this.rotationYaw;
/* 123 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_180484_a(BlockPos p_180484_1_) {
/* 128 */     return (this.worldObj.getBlockState(p_180484_1_.offsetDown()).getBlock() == Blocks.stone) ? 10.0F : super.func_180484_a(p_180484_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 144 */     if (super.getCanSpawnHere()) {
/*     */       
/* 146 */       EntityPlayer var1 = this.worldObj.getClosestPlayerToEntity((Entity)this, 5.0D);
/* 147 */       return (var1 == null);
/*     */     } 
/*     */ 
/*     */     
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 160 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */ 
/*     */   
/*     */   class AIHideInStone
/*     */     extends EntityAIWander
/*     */   {
/*     */     private EnumFacing field_179483_b;
/*     */ 
/*     */     
/*     */     public AIHideInStone() {
/* 171 */       super(EntitySilverfish.this, 1.0D, 10);
/* 172 */       setMutexBits(1);
/*     */     }
/*     */     private boolean field_179484_c; private static final String __OBFID = "CL_00002205";
/*     */     
/*     */     public boolean shouldExecute() {
/* 177 */       if (EntitySilverfish.this.getAttackTarget() != null)
/*     */       {
/* 179 */         return false;
/*     */       }
/* 181 */       if (!EntitySilverfish.this.getNavigator().noPath())
/*     */       {
/* 183 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 187 */       Random var1 = EntitySilverfish.this.getRNG();
/*     */       
/* 189 */       if (var1.nextInt(10) == 0) {
/*     */         
/* 191 */         this.field_179483_b = EnumFacing.random(var1);
/* 192 */         BlockPos var2 = (new BlockPos(EntitySilverfish.this.posX, EntitySilverfish.this.posY + 0.5D, EntitySilverfish.this.posZ)).offset(this.field_179483_b);
/* 193 */         IBlockState var3 = EntitySilverfish.this.worldObj.getBlockState(var2);
/*     */         
/* 195 */         if (BlockSilverfish.func_176377_d(var3)) {
/*     */           
/* 197 */           this.field_179484_c = true;
/* 198 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 202 */       this.field_179484_c = false;
/* 203 */       return super.shouldExecute();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean continueExecuting() {
/* 209 */       return this.field_179484_c ? false : super.continueExecuting();
/*     */     }
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 214 */       if (!this.field_179484_c) {
/*     */         
/* 216 */         super.startExecuting();
/*     */       }
/*     */       else {
/*     */         
/* 220 */         World var1 = EntitySilverfish.this.worldObj;
/* 221 */         BlockPos var2 = (new BlockPos(EntitySilverfish.this.posX, EntitySilverfish.this.posY + 0.5D, EntitySilverfish.this.posZ)).offset(this.field_179483_b);
/* 222 */         IBlockState var3 = var1.getBlockState(var2);
/*     */         
/* 224 */         if (BlockSilverfish.func_176377_d(var3)) {
/*     */           
/* 226 */           var1.setBlockState(var2, Blocks.monster_egg.getDefaultState().withProperty((IProperty)BlockSilverfish.VARIANT_PROP, (Comparable)BlockSilverfish.EnumType.func_176878_a(var3)), 3);
/* 227 */           EntitySilverfish.this.spawnExplosionParticle();
/* 228 */           EntitySilverfish.this.setDead();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class AISummonSilverfish extends EntityAIBase {
/*     */     AISummonSilverfish() {
/* 236 */       this.field_179464_a = EntitySilverfish.this;
/*     */     }
/*     */     
/*     */     private EntitySilverfish field_179464_a;
/*     */     
/*     */     public void func_179462_f() {
/* 242 */       if (this.field_179463_b == 0)
/*     */       {
/* 244 */         this.field_179463_b = 20; } 
/*     */     }
/*     */     private int field_179463_b;
/*     */     private static final String __OBFID = "CL_00002204";
/*     */     
/*     */     public boolean shouldExecute() {
/* 250 */       return (this.field_179463_b > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 255 */       this.field_179463_b--;
/*     */       
/* 257 */       if (this.field_179463_b <= 0) {
/*     */         
/* 259 */         World var1 = this.field_179464_a.worldObj;
/* 260 */         Random var2 = this.field_179464_a.getRNG();
/* 261 */         BlockPos var3 = new BlockPos((Entity)this.field_179464_a);
/*     */         
/* 263 */         for (int var4 = 0; var4 <= 5 && var4 >= -5; var4 = (var4 <= 0) ? (1 - var4) : (0 - var4)) {
/*     */           
/* 265 */           for (int var5 = 0; var5 <= 10 && var5 >= -10; var5 = (var5 <= 0) ? (1 - var5) : (0 - var5)) {
/*     */             
/* 267 */             for (int var6 = 0; var6 <= 10 && var6 >= -10; var6 = (var6 <= 0) ? (1 - var6) : (0 - var6)) {
/*     */               
/* 269 */               BlockPos var7 = var3.add(var5, var4, var6);
/* 270 */               IBlockState var8 = var1.getBlockState(var7);
/*     */               
/* 272 */               if (var8.getBlock() == Blocks.monster_egg) {
/*     */                 
/* 274 */                 if (var1.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */                   
/* 276 */                   var1.destroyBlock(var7, true);
/*     */                 }
/*     */                 else {
/*     */                   
/* 280 */                   var1.setBlockState(var7, ((BlockSilverfish.EnumType)var8.getValue((IProperty)BlockSilverfish.VARIANT_PROP)).func_176883_d(), 3);
/*     */                 } 
/*     */                 
/* 283 */                 if (var2.nextBoolean())
/*     */                   return; 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntitySilverfish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */