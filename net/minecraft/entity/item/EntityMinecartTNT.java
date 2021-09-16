/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.block.BlockRailBase;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMinecartTNT
/*     */   extends EntityMinecart {
/*  18 */   private int minecartTNTFuse = -1;
/*     */   
/*     */   private static final String __OBFID = "CL_00001680";
/*     */   
/*     */   public EntityMinecartTNT(World worldIn) {
/*  23 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartTNT(World worldIn, double p_i1728_2_, double p_i1728_4_, double p_i1728_6_) {
/*  28 */     super(worldIn, p_i1728_2_, p_i1728_4_, p_i1728_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecart.EnumMinecartType func_180456_s() {
/*  33 */     return EntityMinecart.EnumMinecartType.TNT;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_180457_u() {
/*  38 */     return Blocks.tnt.getDefaultState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  46 */     super.onUpdate();
/*     */     
/*  48 */     if (this.minecartTNTFuse > 0) {
/*     */       
/*  50 */       this.minecartTNTFuse--;
/*  51 */       this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*  53 */     else if (this.minecartTNTFuse == 0) {
/*     */       
/*  55 */       explodeCart(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*     */     } 
/*     */     
/*  58 */     if (this.isCollidedHorizontally) {
/*     */       
/*  60 */       double var1 = this.motionX * this.motionX + this.motionZ * this.motionZ;
/*     */       
/*  62 */       if (var1 >= 0.009999999776482582D)
/*     */       {
/*  64 */         explodeCart(var1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  74 */     Entity var3 = source.getSourceOfDamage();
/*     */     
/*  76 */     if (var3 instanceof EntityArrow) {
/*     */       
/*  78 */       EntityArrow var4 = (EntityArrow)var3;
/*     */       
/*  80 */       if (var4.isBurning())
/*     */       {
/*  82 */         explodeCart(var4.motionX * var4.motionX + var4.motionY * var4.motionY + var4.motionZ * var4.motionZ);
/*     */       }
/*     */     } 
/*     */     
/*  86 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void killMinecart(DamageSource p_94095_1_) {
/*  91 */     super.killMinecart(p_94095_1_);
/*  92 */     double var2 = this.motionX * this.motionX + this.motionZ * this.motionZ;
/*     */     
/*  94 */     if (!p_94095_1_.isExplosion())
/*     */     {
/*  96 */       entityDropItem(new ItemStack(Blocks.tnt, 1), 0.0F);
/*     */     }
/*     */     
/*  99 */     if (p_94095_1_.isFireDamage() || p_94095_1_.isExplosion() || var2 >= 0.009999999776482582D)
/*     */     {
/* 101 */       explodeCart(var2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void explodeCart(double p_94103_1_) {
/* 110 */     if (!this.worldObj.isRemote) {
/*     */       
/* 112 */       double var3 = Math.sqrt(p_94103_1_);
/*     */       
/* 114 */       if (var3 > 5.0D)
/*     */       {
/* 116 */         var3 = 5.0D;
/*     */       }
/*     */       
/* 119 */       this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)(4.0D + this.rand.nextDouble() * 1.5D * var3), true);
/* 120 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {
/* 126 */     if (distance >= 3.0F) {
/*     */       
/* 128 */       float var3 = distance / 10.0F;
/* 129 */       explodeCart((var3 * var3));
/*     */     } 
/*     */     
/* 132 */     super.fall(distance, damageMultiplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onActivatorRailPass(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
/* 140 */     if (p_96095_4_ && this.minecartTNTFuse < 0)
/*     */     {
/* 142 */       ignite();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 148 */     if (p_70103_1_ == 10) {
/*     */       
/* 150 */       ignite();
/*     */     }
/*     */     else {
/*     */       
/* 154 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignite() {
/* 163 */     this.minecartTNTFuse = 80;
/*     */     
/* 165 */     if (!this.worldObj.isRemote) {
/*     */       
/* 167 */       this.worldObj.setEntityState(this, (byte)10);
/*     */       
/* 169 */       if (!isSlient())
/*     */       {
/* 171 */         this.worldObj.playSoundAtEntity(this, "game.tnt.primed", 1.0F, 1.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_94104_d() {
/* 178 */     return this.minecartTNTFuse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIgnited() {
/* 186 */     return (this.minecartTNTFuse > -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getExplosionResistance(Explosion p_180428_1_, World worldIn, BlockPos p_180428_3_, IBlockState p_180428_4_) {
/* 194 */     return (isIgnited() && (BlockRailBase.func_176563_d(p_180428_4_) || BlockRailBase.func_176562_d(worldIn, p_180428_3_.offsetUp()))) ? 0.0F : super.getExplosionResistance(p_180428_1_, worldIn, p_180428_3_, p_180428_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_174816_a(Explosion p_174816_1_, World worldIn, BlockPos p_174816_3_, IBlockState p_174816_4_, float p_174816_5_) {
/* 199 */     return (isIgnited() && (BlockRailBase.func_176563_d(p_174816_4_) || BlockRailBase.func_176562_d(worldIn, p_174816_3_.offsetUp()))) ? false : super.func_174816_a(p_174816_1_, worldIn, p_174816_3_, p_174816_4_, p_174816_5_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 207 */     super.readEntityFromNBT(tagCompund);
/*     */     
/* 209 */     if (tagCompund.hasKey("TNTFuse", 99))
/*     */     {
/* 211 */       this.minecartTNTFuse = tagCompund.getInteger("TNTFuse");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 220 */     super.writeEntityToNBT(tagCompound);
/* 221 */     tagCompound.setInteger("TNTFuse", this.minecartTNTFuse);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityMinecartTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */