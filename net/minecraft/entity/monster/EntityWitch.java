/*     */ package net.minecraft.entity.monster;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityWitch extends EntityMob implements IRangedAttackMob {
/*  33 */   private static final UUID field_110184_bp = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
/*  34 */   private static final AttributeModifier field_110185_bq = (new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0)).setSaved(false);
/*     */ 
/*     */   
/*  37 */   private static final Item[] witchDrops = new Item[] { Items.glowstone_dust, Items.sugar, Items.redstone, Items.spider_eye, Items.glass_bottle, Items.gunpowder, Items.stick, Items.stick };
/*     */ 
/*     */   
/*     */   private int witchAttackTimer;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001701";
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityWitch(World worldIn) {
/*  48 */     super(worldIn);
/*  49 */     setSize(0.6F, 1.95F);
/*  50 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  51 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
/*  52 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIWander(this, 1.0D));
/*  53 */     this.tasks.addTask(2, this.field_175455_a);
/*  54 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  55 */     this.tasks.addTask(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  56 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget(this, false, new Class[0]));
/*  57 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  62 */     super.entityInit();
/*  63 */     getDataWatcher().addObject(21, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAggressive(boolean p_82197_1_) {
/*  95 */     getDataWatcher().updateObject(21, Byte.valueOf((byte)(p_82197_1_ ? 1 : 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAggressive() {
/* 103 */     return (getDataWatcher().getWatchableObjectByte(21) == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 108 */     super.applyEntityAttributes();
/* 109 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26.0D);
/* 110 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 119 */     if (!this.worldObj.isRemote) {
/*     */       
/* 121 */       if (getAggressive()) {
/*     */         
/* 123 */         if (this.witchAttackTimer-- <= 0)
/*     */         {
/* 125 */           setAggressive(false);
/* 126 */           ItemStack var1 = getHeldItem();
/* 127 */           setCurrentItemOrArmor(0, null);
/*     */           
/* 129 */           if (var1 != null && var1.getItem() == Items.potionitem) {
/*     */             
/* 131 */             List var2 = Items.potionitem.getEffects(var1);
/*     */             
/* 133 */             if (var2 != null) {
/*     */               
/* 135 */               Iterator<PotionEffect> var3 = var2.iterator();
/*     */               
/* 137 */               while (var3.hasNext()) {
/*     */                 
/* 139 */                 PotionEffect var4 = var3.next();
/* 140 */                 addPotionEffect(new PotionEffect(var4));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 145 */           getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(field_110185_bq);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 150 */         short var5 = -1;
/*     */         
/* 152 */         if (this.rand.nextFloat() < 0.15F && isInsideOfMaterial(Material.water) && !isPotionActive(Potion.waterBreathing)) {
/*     */           
/* 154 */           var5 = 8237;
/*     */         }
/* 156 */         else if (this.rand.nextFloat() < 0.15F && isBurning() && !isPotionActive(Potion.fireResistance)) {
/*     */           
/* 158 */           var5 = 16307;
/*     */         }
/* 160 */         else if (this.rand.nextFloat() < 0.05F && getHealth() < getMaxHealth()) {
/*     */           
/* 162 */           var5 = 16341;
/*     */         }
/* 164 */         else if (this.rand.nextFloat() < 0.25F && getAttackTarget() != null && !isPotionActive(Potion.moveSpeed) && getAttackTarget().getDistanceSqToEntity((Entity)this) > 121.0D) {
/*     */           
/* 166 */           var5 = 16274;
/*     */         }
/* 168 */         else if (this.rand.nextFloat() < 0.25F && getAttackTarget() != null && !isPotionActive(Potion.moveSpeed) && getAttackTarget().getDistanceSqToEntity((Entity)this) > 121.0D) {
/*     */           
/* 170 */           var5 = 16274;
/*     */         } 
/*     */         
/* 173 */         if (var5 > -1) {
/*     */           
/* 175 */           setCurrentItemOrArmor(0, new ItemStack((Item)Items.potionitem, 1, var5));
/* 176 */           this.witchAttackTimer = getHeldItem().getMaxItemUseDuration();
/* 177 */           setAggressive(true);
/* 178 */           IAttributeInstance var6 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 179 */           var6.removeModifier(field_110185_bq);
/* 180 */           var6.applyModifier(field_110185_bq);
/*     */         } 
/*     */       } 
/*     */       
/* 184 */       if (this.rand.nextFloat() < 7.5E-4F)
/*     */       {
/* 186 */         this.worldObj.setEntityState((Entity)this, (byte)15);
/*     */       }
/*     */     } 
/*     */     
/* 190 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 195 */     if (p_70103_1_ == 15) {
/*     */       
/* 197 */       for (int var2 = 0; var2 < this.rand.nextInt(35) + 10; var2++)
/*     */       {
/* 199 */         this.worldObj.spawnParticle(EnumParticleTypes.SPELL_WITCH, this.posX + this.rand.nextGaussian() * 0.12999999523162842D, (getEntityBoundingBox()).maxY + 0.5D + this.rand.nextGaussian() * 0.12999999523162842D, this.posZ + this.rand.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 204 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_) {
/* 213 */     p_70672_2_ = super.applyPotionDamageCalculations(p_70672_1_, p_70672_2_);
/*     */     
/* 215 */     if (p_70672_1_.getEntity() == this)
/*     */     {
/* 217 */       p_70672_2_ = 0.0F;
/*     */     }
/*     */     
/* 220 */     if (p_70672_1_.isMagicDamage())
/*     */     {
/* 222 */       p_70672_2_ = (float)(p_70672_2_ * 0.15D);
/*     */     }
/*     */     
/* 225 */     return p_70672_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 233 */     int var3 = this.rand.nextInt(3) + 1;
/*     */     
/* 235 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 237 */       int var5 = this.rand.nextInt(3);
/* 238 */       Item var6 = witchDrops[this.rand.nextInt(witchDrops.length)];
/*     */       
/* 240 */       if (p_70628_2_ > 0)
/*     */       {
/* 242 */         var5 += this.rand.nextInt(p_70628_2_ + 1);
/*     */       }
/*     */       
/* 245 */       for (int var7 = 0; var7 < var5; var7++)
/*     */       {
/* 247 */         dropItem(var6, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
/* 257 */     if (!getAggressive()) {
/*     */       
/* 259 */       EntityPotion var3 = new EntityPotion(this.worldObj, (EntityLivingBase)this, 32732);
/* 260 */       double var4 = p_82196_1_.posY + p_82196_1_.getEyeHeight() - 1.100000023841858D;
/* 261 */       var3.rotationPitch -= -20.0F;
/* 262 */       double var6 = p_82196_1_.posX + p_82196_1_.motionX - this.posX;
/* 263 */       double var8 = var4 - this.posY;
/* 264 */       double var10 = p_82196_1_.posZ + p_82196_1_.motionZ - this.posZ;
/* 265 */       float var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10);
/*     */       
/* 267 */       if (var12 >= 8.0F && !p_82196_1_.isPotionActive(Potion.moveSlowdown)) {
/*     */         
/* 269 */         var3.setPotionDamage(32698);
/*     */       }
/* 271 */       else if (p_82196_1_.getHealth() >= 8.0F && !p_82196_1_.isPotionActive(Potion.poison)) {
/*     */         
/* 273 */         var3.setPotionDamage(32660);
/*     */       }
/* 275 */       else if (var12 <= 3.0F && !p_82196_1_.isPotionActive(Potion.weakness) && this.rand.nextFloat() < 0.25F) {
/*     */         
/* 277 */         var3.setPotionDamage(32696);
/*     */       } 
/*     */       
/* 280 */       var3.setThrowableHeading(var6, var8 + (var12 * 0.2F), var10, 0.75F, 8.0F);
/* 281 */       this.worldObj.spawnEntityInWorld((Entity)var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 287 */     return 1.62F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */