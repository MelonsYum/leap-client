/*     */ package net.minecraft.entity.projectile;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityPotion
/*     */   extends EntityThrowable
/*     */ {
/*     */   private ItemStack potionDamage;
/*     */   private static final String __OBFID = "CL_00001727";
/*     */   
/*     */   public EntityPotion(World worldIn) {
/*  26 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World worldIn, EntityLivingBase p_i1789_2_, int p_i1789_3_) {
/*  31 */     this(worldIn, p_i1789_2_, new ItemStack((Item)Items.potionitem, 1, p_i1789_3_));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World worldIn, EntityLivingBase p_i1790_2_, ItemStack p_i1790_3_) {
/*  36 */     super(worldIn, p_i1790_2_);
/*  37 */     this.potionDamage = p_i1790_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World worldIn, double p_i1791_2_, double p_i1791_4_, double p_i1791_6_, int p_i1791_8_) {
/*  42 */     this(worldIn, p_i1791_2_, p_i1791_4_, p_i1791_6_, new ItemStack((Item)Items.potionitem, 1, p_i1791_8_));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World worldIn, double p_i1792_2_, double p_i1792_4_, double p_i1792_6_, ItemStack p_i1792_8_) {
/*  47 */     super(worldIn, p_i1792_2_, p_i1792_4_, p_i1792_6_);
/*  48 */     this.potionDamage = p_i1792_8_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getGravityVelocity() {
/*  56 */     return 0.05F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70182_d() {
/*  61 */     return 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70183_g() {
/*  66 */     return -20.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPotionDamage(int p_82340_1_) {
/*  71 */     if (this.potionDamage == null)
/*     */     {
/*  73 */       this.potionDamage = new ItemStack((Item)Items.potionitem, 1, 0);
/*     */     }
/*     */     
/*  76 */     this.potionDamage.setItemDamage(p_82340_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPotionDamage() {
/*  84 */     if (this.potionDamage == null)
/*     */     {
/*  86 */       this.potionDamage = new ItemStack((Item)Items.potionitem, 1, 0);
/*     */     }
/*     */     
/*  89 */     return this.potionDamage.getMetadata();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition p_70184_1_) {
/*  97 */     if (!this.worldObj.isRemote) {
/*     */       
/*  99 */       List var2 = Items.potionitem.getEffects(this.potionDamage);
/*     */       
/* 101 */       if (var2 != null && !var2.isEmpty()) {
/*     */         
/* 103 */         AxisAlignedBB var3 = getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D);
/* 104 */         List var4 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, var3);
/*     */         
/* 106 */         if (!var4.isEmpty()) {
/*     */           
/* 108 */           Iterator<EntityLivingBase> var5 = var4.iterator();
/*     */           
/* 110 */           while (var5.hasNext()) {
/*     */             
/* 112 */             EntityLivingBase var6 = var5.next();
/* 113 */             double var7 = getDistanceSqToEntity((Entity)var6);
/*     */             
/* 115 */             if (var7 < 16.0D) {
/*     */               
/* 117 */               double var9 = 1.0D - Math.sqrt(var7) / 4.0D;
/*     */               
/* 119 */               if (var6 == p_70184_1_.entityHit)
/*     */               {
/* 121 */                 var9 = 1.0D;
/*     */               }
/*     */               
/* 124 */               Iterator<PotionEffect> var11 = var2.iterator();
/*     */               
/* 126 */               while (var11.hasNext()) {
/*     */                 
/* 128 */                 PotionEffect var12 = var11.next();
/* 129 */                 int var13 = var12.getPotionID();
/*     */                 
/* 131 */                 if (Potion.potionTypes[var13].isInstant()) {
/*     */                   
/* 133 */                   Potion.potionTypes[var13].func_180793_a(this, (Entity)getThrower(), var6, var12.getAmplifier(), var9);
/*     */                   
/*     */                   continue;
/*     */                 } 
/* 137 */                 int var14 = (int)(var9 * var12.getDuration() + 0.5D);
/*     */                 
/* 139 */                 if (var14 > 20)
/*     */                 {
/* 141 */                   var6.addPotionEffect(new PotionEffect(var13, var14, var12.getAmplifier()));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 150 */       this.worldObj.playAuxSFX(2002, new BlockPos(this), getPotionDamage());
/* 151 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 160 */     super.readEntityFromNBT(tagCompund);
/*     */     
/* 162 */     if (tagCompund.hasKey("Potion", 10)) {
/*     */       
/* 164 */       this.potionDamage = ItemStack.loadItemStackFromNBT(tagCompund.getCompoundTag("Potion"));
/*     */     }
/*     */     else {
/*     */       
/* 168 */       setPotionDamage(tagCompund.getInteger("potionValue"));
/*     */     } 
/*     */     
/* 171 */     if (this.potionDamage == null)
/*     */     {
/* 173 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 182 */     super.writeEntityToNBT(tagCompound);
/*     */     
/* 184 */     if (this.potionDamage != null)
/*     */     {
/* 186 */       tagCompound.setTag("Potion", (NBTBase)this.potionDamage.writeToNBT(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */