/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityAgeable
/*     */   extends EntityCreature {
/*     */   protected int field_175504_a;
/*     */   protected int field_175502_b;
/*     */   protected int field_175503_c;
/*  16 */   private float field_98056_d = -1.0F;
/*     */   
/*     */   private float field_98057_e;
/*     */   private static final String __OBFID = "CL_00001530";
/*     */   
/*     */   public EntityAgeable(World worldIn) {
/*  22 */     super(worldIn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract EntityAgeable createChild(EntityAgeable paramEntityAgeable);
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer p_70085_1_) {
/*  32 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*     */     
/*  34 */     if (var2 != null && var2.getItem() == Items.spawn_egg) {
/*     */       
/*  36 */       if (!this.worldObj.isRemote) {
/*     */         
/*  38 */         Class<?> var3 = EntityList.getClassFromID(var2.getMetadata());
/*     */         
/*  40 */         if (var3 != null && getClass() == var3) {
/*     */           
/*  42 */           EntityAgeable var4 = createChild(this);
/*     */           
/*  44 */           if (var4 != null) {
/*     */             
/*  46 */             var4.setGrowingAge(-24000);
/*  47 */             var4.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
/*  48 */             this.worldObj.spawnEntityInWorld(var4);
/*     */             
/*  50 */             if (var2.hasDisplayName())
/*     */             {
/*  52 */               var4.setCustomNameTag(var2.getDisplayName());
/*     */             }
/*     */             
/*  55 */             if (!p_70085_1_.capabilities.isCreativeMode) {
/*     */               
/*  57 */               var2.stackSize--;
/*     */               
/*  59 */               if (var2.stackSize <= 0)
/*     */               {
/*  61 */                 p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  68 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  78 */     super.entityInit();
/*  79 */     this.dataWatcher.addObject(12, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGrowingAge() {
/*  89 */     return this.worldObj.isRemote ? this.dataWatcher.getWatchableObjectByte(12) : this.field_175504_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175501_a(int p_175501_1_, boolean p_175501_2_) {
/*  94 */     int var3 = getGrowingAge();
/*  95 */     int var4 = var3;
/*  96 */     var3 += p_175501_1_ * 20;
/*     */     
/*  98 */     if (var3 > 0) {
/*     */       
/* 100 */       var3 = 0;
/*     */       
/* 102 */       if (var4 < 0)
/*     */       {
/* 104 */         func_175500_n();
/*     */       }
/*     */     } 
/*     */     
/* 108 */     int var5 = var3 - var4;
/* 109 */     setGrowingAge(var3);
/*     */     
/* 111 */     if (p_175501_2_) {
/*     */       
/* 113 */       this.field_175502_b += var5;
/*     */       
/* 115 */       if (this.field_175503_c == 0)
/*     */       {
/* 117 */         this.field_175503_c = 40;
/*     */       }
/*     */     } 
/*     */     
/* 121 */     if (getGrowingAge() == 0)
/*     */     {
/* 123 */       setGrowingAge(this.field_175502_b);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGrowth(int p_110195_1_) {
/* 133 */     func_175501_a(p_110195_1_, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGrowingAge(int p_70873_1_) {
/* 142 */     this.dataWatcher.updateObject(12, Byte.valueOf((byte)MathHelper.clamp_int(p_70873_1_, -1, 1)));
/* 143 */     this.field_175504_a = p_70873_1_;
/* 144 */     setScaleForAge(isChild());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 152 */     super.writeEntityToNBT(tagCompound);
/* 153 */     tagCompound.setInteger("Age", getGrowingAge());
/* 154 */     tagCompound.setInteger("ForcedAge", this.field_175502_b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 162 */     super.readEntityFromNBT(tagCompund);
/* 163 */     setGrowingAge(tagCompund.getInteger("Age"));
/* 164 */     this.field_175502_b = tagCompund.getInteger("ForcedAge");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 173 */     super.onLivingUpdate();
/*     */     
/* 175 */     if (this.worldObj.isRemote) {
/*     */       
/* 177 */       if (this.field_175503_c > 0) {
/*     */         
/* 179 */         if (this.field_175503_c % 4 == 0)
/*     */         {
/* 181 */           this.worldObj.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */         
/* 184 */         this.field_175503_c--;
/*     */       } 
/*     */       
/* 187 */       setScaleForAge(isChild());
/*     */     }
/*     */     else {
/*     */       
/* 191 */       int var1 = getGrowingAge();
/*     */       
/* 193 */       if (var1 < 0) {
/*     */         
/* 195 */         var1++;
/* 196 */         setGrowingAge(var1);
/*     */         
/* 198 */         if (var1 == 0)
/*     */         {
/* 200 */           func_175500_n();
/*     */         }
/*     */       }
/* 203 */       else if (var1 > 0) {
/*     */         
/* 205 */         var1--;
/* 206 */         setGrowingAge(var1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_175500_n() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChild() {
/* 218 */     return (getGrowingAge() < 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScaleForAge(boolean p_98054_1_) {
/* 226 */     setScale(p_98054_1_ ? 0.5F : 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setSize(float width, float height) {
/* 234 */     boolean var3 = (this.field_98056_d > 0.0F);
/* 235 */     this.field_98056_d = width;
/* 236 */     this.field_98057_e = height;
/*     */     
/* 238 */     if (!var3)
/*     */     {
/* 240 */       setScale(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void setScale(float p_98055_1_) {
/* 246 */     super.setSize(this.field_98056_d * p_98055_1_, this.field_98057_e * p_98055_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityAgeable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */