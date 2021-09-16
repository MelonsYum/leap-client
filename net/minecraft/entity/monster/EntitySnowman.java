/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntitySnowball;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySnowman extends EntityGolem implements IRangedAttackMob {
/*     */   private static final String __OBFID = "CL_00001650";
/*     */   
/*     */   public EntitySnowman(World worldIn) {
/*  30 */     super(worldIn);
/*  31 */     setSize(0.7F, 1.9F);
/*  32 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  33 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
/*  34 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIWander(this, 1.0D));
/*  35 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*  36 */     this.tasks.addTask(4, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  37 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, true, false, IMob.mobSelector));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  42 */     super.applyEntityAttributes();
/*  43 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
/*  44 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  53 */     super.onLivingUpdate();
/*     */     
/*  55 */     if (!this.worldObj.isRemote) {
/*     */       
/*  57 */       int var1 = MathHelper.floor_double(this.posX);
/*  58 */       int var2 = MathHelper.floor_double(this.posY);
/*  59 */       int var3 = MathHelper.floor_double(this.posZ);
/*     */       
/*  61 */       if (isWet())
/*     */       {
/*  63 */         attackEntityFrom(DamageSource.drown, 1.0F);
/*     */       }
/*     */       
/*  66 */       if (this.worldObj.getBiomeGenForCoords(new BlockPos(var1, 0, var3)).func_180626_a(new BlockPos(var1, var2, var3)) > 1.0F)
/*     */       {
/*  68 */         attackEntityFrom(DamageSource.onFire, 1.0F);
/*     */       }
/*     */       
/*  71 */       for (int var4 = 0; var4 < 4; var4++) {
/*     */         
/*  73 */         var1 = MathHelper.floor_double(this.posX + ((var4 % 2 * 2 - 1) * 0.25F));
/*  74 */         var2 = MathHelper.floor_double(this.posY);
/*  75 */         var3 = MathHelper.floor_double(this.posZ + ((var4 / 2 % 2 * 2 - 1) * 0.25F));
/*     */         
/*  77 */         if (this.worldObj.getBlockState(new BlockPos(var1, var2, var3)).getBlock().getMaterial() == Material.air && this.worldObj.getBiomeGenForCoords(new BlockPos(var1, 0, var3)).func_180626_a(new BlockPos(var1, var2, var3)) < 0.8F && Blocks.snow_layer.canPlaceBlockAt(this.worldObj, new BlockPos(var1, var2, var3)))
/*     */         {
/*  79 */           this.worldObj.setBlockState(new BlockPos(var1, var2, var3), Blocks.snow_layer.getDefaultState());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/*  87 */     return Items.snowball;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/*  95 */     int var3 = this.rand.nextInt(16);
/*     */     
/*  97 */     for (int var4 = 0; var4 < var3; var4++)
/*     */     {
/*  99 */       dropItem(Items.snowball, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
/* 108 */     EntitySnowball var3 = new EntitySnowball(this.worldObj, (EntityLivingBase)this);
/* 109 */     double var4 = p_82196_1_.posY + p_82196_1_.getEyeHeight() - 1.100000023841858D;
/* 110 */     double var6 = p_82196_1_.posX - this.posX;
/* 111 */     double var8 = var4 - var3.posY;
/* 112 */     double var10 = p_82196_1_.posZ - this.posZ;
/* 113 */     float var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10) * 0.2F;
/* 114 */     var3.setThrowableHeading(var6, var8 + var12, var10, 1.6F, 12.0F);
/* 115 */     playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
/* 116 */     this.worldObj.spawnEntityInWorld((Entity)var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 121 */     return 1.7F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntitySnowman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */