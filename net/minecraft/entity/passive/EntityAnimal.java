/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityAnimal
/*     */   extends EntityAgeable implements IAnimals {
/*     */   protected Block field_175506_bl;
/*     */   private int inLove;
/*     */   private EntityPlayer playerInLove;
/*     */   private static final String __OBFID = "CL_00001638";
/*     */   
/*     */   public EntityAnimal(World worldIn) {
/*  25 */     super(worldIn);
/*  26 */     this.field_175506_bl = (Block)Blocks.grass;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/*  31 */     if (getGrowingAge() != 0)
/*     */     {
/*  33 */       this.inLove = 0;
/*     */     }
/*     */     
/*  36 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  45 */     super.onLivingUpdate();
/*     */     
/*  47 */     if (getGrowingAge() != 0)
/*     */     {
/*  49 */       this.inLove = 0;
/*     */     }
/*     */     
/*  52 */     if (this.inLove > 0) {
/*     */       
/*  54 */       this.inLove--;
/*     */       
/*  56 */       if (this.inLove % 10 == 0) {
/*     */         
/*  58 */         double var1 = this.rand.nextGaussian() * 0.02D;
/*  59 */         double var3 = this.rand.nextGaussian() * 0.02D;
/*  60 */         double var5 = this.rand.nextGaussian() * 0.02D;
/*  61 */         this.worldObj.spawnParticle(EnumParticleTypes.HEART, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var1, var3, var5, new int[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  71 */     if (func_180431_b(source))
/*     */     {
/*  73 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  77 */     this.inLove = 0;
/*  78 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_180484_a(BlockPos p_180484_1_) {
/*  84 */     return (this.worldObj.getBlockState(p_180484_1_.offsetDown()).getBlock() == Blocks.grass) ? 10.0F : (this.worldObj.getLightBrightness(p_180484_1_) - 0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  92 */     super.writeEntityToNBT(tagCompound);
/*  93 */     tagCompound.setInteger("InLove", this.inLove);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 101 */     super.readEntityFromNBT(tagCompund);
/* 102 */     this.inLove = tagCompund.getInteger("InLove");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 110 */     int var1 = MathHelper.floor_double(this.posX);
/* 111 */     int var2 = MathHelper.floor_double((getEntityBoundingBox()).minY);
/* 112 */     int var3 = MathHelper.floor_double(this.posZ);
/* 113 */     BlockPos var4 = new BlockPos(var1, var2, var3);
/* 114 */     return (this.worldObj.getBlockState(var4.offsetDown()).getBlock() == this.field_175506_bl && this.worldObj.getLight(var4) > 8 && super.getCanSpawnHere());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 122 */     return 120;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getExperiencePoints(EntityPlayer p_70693_1_) {
/* 138 */     return 1 + this.worldObj.rand.nextInt(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBreedingItem(ItemStack p_70877_1_) {
/* 147 */     return (p_70877_1_ == null) ? false : ((p_70877_1_.getItem() == Items.wheat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer p_70085_1_) {
/* 155 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*     */     
/* 157 */     if (var2 != null) {
/*     */       
/* 159 */       if (isBreedingItem(var2) && getGrowingAge() == 0 && this.inLove <= 0) {
/*     */         
/* 161 */         func_175505_a(p_70085_1_, var2);
/* 162 */         setInLove(p_70085_1_);
/* 163 */         return true;
/*     */       } 
/*     */       
/* 166 */       if (isChild() && isBreedingItem(var2)) {
/*     */         
/* 168 */         func_175505_a(p_70085_1_, var2);
/* 169 */         func_175501_a((int)((-getGrowingAge() / 20) * 0.1F), true);
/* 170 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     return super.interact(p_70085_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175505_a(EntityPlayer p_175505_1_, ItemStack p_175505_2_) {
/* 179 */     if (!p_175505_1_.capabilities.isCreativeMode) {
/*     */       
/* 181 */       p_175505_2_.stackSize--;
/*     */       
/* 183 */       if (p_175505_2_.stackSize <= 0)
/*     */       {
/* 185 */         p_175505_1_.inventory.setInventorySlotContents(p_175505_1_.inventory.currentItem, null);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInLove(EntityPlayer p_146082_1_) {
/* 192 */     this.inLove = 600;
/* 193 */     this.playerInLove = p_146082_1_;
/* 194 */     this.worldObj.setEntityState((Entity)this, (byte)18);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer func_146083_cb() {
/* 199 */     return this.playerInLove;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInLove() {
/* 207 */     return (this.inLove > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetInLove() {
/* 212 */     this.inLove = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(EntityAnimal p_70878_1_) {
/* 220 */     return (p_70878_1_ == this) ? false : ((p_70878_1_.getClass() != getClass()) ? false : ((isInLove() && p_70878_1_.isInLove())));
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 225 */     if (p_70103_1_ == 18) {
/*     */       
/* 227 */       for (int var2 = 0; var2 < 7; var2++)
/*     */       {
/* 229 */         double var3 = this.rand.nextGaussian() * 0.02D;
/* 230 */         double var5 = this.rand.nextGaussian() * 0.02D;
/* 231 */         double var7 = this.rand.nextGaussian() * 0.02D;
/* 232 */         this.worldObj.spawnParticle(EnumParticleTypes.HEART, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var3, var5, var7, new int[0]);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 237 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */