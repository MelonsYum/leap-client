/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockRedstoneDiode;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public abstract class EntityHanging
/*     */   extends Entity
/*     */ {
/*     */   private int tickCounter1;
/*     */   protected BlockPos field_174861_a;
/*     */   public EnumFacing field_174860_b;
/*     */   private static final String __OBFID = "CL_00001546";
/*     */   
/*     */   public EntityHanging(World worldIn) {
/*  25 */     super(worldIn);
/*  26 */     setSize(0.5F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityHanging(World worldIn, BlockPos p_i45853_2_) {
/*  31 */     this(worldIn);
/*  32 */     this.field_174861_a = p_i45853_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */   
/*     */   protected void func_174859_a(EnumFacing p_174859_1_) {
/*  39 */     Validate.notNull(p_174859_1_);
/*  40 */     Validate.isTrue(p_174859_1_.getAxis().isHorizontal());
/*  41 */     this.field_174860_b = p_174859_1_;
/*  42 */     this.prevRotationYaw = this.rotationYaw = (this.field_174860_b.getHorizontalIndex() * 90);
/*  43 */     func_174856_o();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_174856_o() {
/*  48 */     if (this.field_174860_b != null) {
/*     */       
/*  50 */       double var1 = this.field_174861_a.getX() + 0.5D;
/*  51 */       double var3 = this.field_174861_a.getY() + 0.5D;
/*  52 */       double var5 = this.field_174861_a.getZ() + 0.5D;
/*  53 */       double var7 = 0.46875D;
/*  54 */       double var9 = func_174858_a(getWidthPixels());
/*  55 */       double var11 = func_174858_a(getHeightPixels());
/*  56 */       var1 -= this.field_174860_b.getFrontOffsetX() * 0.46875D;
/*  57 */       var5 -= this.field_174860_b.getFrontOffsetZ() * 0.46875D;
/*  58 */       var3 += var11;
/*  59 */       EnumFacing var13 = this.field_174860_b.rotateYCCW();
/*  60 */       var1 += var9 * var13.getFrontOffsetX();
/*  61 */       var5 += var9 * var13.getFrontOffsetZ();
/*  62 */       this.posX = var1;
/*  63 */       this.posY = var3;
/*  64 */       this.posZ = var5;
/*  65 */       double var14 = getWidthPixels();
/*  66 */       double var16 = getHeightPixels();
/*  67 */       double var18 = getWidthPixels();
/*     */       
/*  69 */       if (this.field_174860_b.getAxis() == EnumFacing.Axis.Z) {
/*     */         
/*  71 */         var18 = 1.0D;
/*     */       }
/*     */       else {
/*     */         
/*  75 */         var14 = 1.0D;
/*     */       } 
/*     */       
/*  78 */       var14 /= 32.0D;
/*  79 */       var16 /= 32.0D;
/*  80 */       var18 /= 32.0D;
/*  81 */       func_174826_a(new AxisAlignedBB(var1 - var14, var3 - var16, var5 - var18, var1 + var14, var3 + var16, var5 + var18));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_174858_a(int p_174858_1_) {
/*  87 */     return (p_174858_1_ % 32 == 0) ? 0.5D : 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  95 */     this.prevPosX = this.posX;
/*  96 */     this.prevPosY = this.posY;
/*  97 */     this.prevPosZ = this.posZ;
/*     */     
/*  99 */     if (this.tickCounter1++ == 100 && !this.worldObj.isRemote) {
/*     */       
/* 101 */       this.tickCounter1 = 0;
/*     */       
/* 103 */       if (!this.isDead && !onValidSurface()) {
/*     */         
/* 105 */         setDead();
/* 106 */         onBroken((Entity)null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onValidSurface() {
/*     */     Entity var11;
/* 116 */     if (!this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox()).isEmpty())
/*     */     {
/* 118 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 122 */     int var1 = Math.max(1, getWidthPixels() / 16);
/* 123 */     int var2 = Math.max(1, getHeightPixels() / 16);
/* 124 */     BlockPos var3 = this.field_174861_a.offset(this.field_174860_b.getOpposite());
/* 125 */     EnumFacing var4 = this.field_174860_b.rotateYCCW();
/*     */     
/* 127 */     for (int var5 = 0; var5 < var1; var5++) {
/*     */       
/* 129 */       for (int var6 = 0; var6 < var2; var6++) {
/*     */         
/* 131 */         BlockPos var7 = var3.offset(var4, var5).offsetUp(var6);
/* 132 */         Block var8 = this.worldObj.getBlockState(var7).getBlock();
/*     */         
/* 134 */         if (!var8.getMaterial().isSolid() && !BlockRedstoneDiode.isRedstoneRepeaterBlockID(var8))
/*     */         {
/* 136 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 141 */     List var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox());
/* 142 */     Iterator<Entity> var10 = var9.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 147 */       if (!var10.hasNext())
/*     */       {
/* 149 */         return true;
/*     */       }
/*     */       
/* 152 */       var11 = var10.next();
/*     */     }
/* 154 */     while (!(var11 instanceof EntityHanging));
/*     */     
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hitByEntity(Entity entityIn) {
/* 173 */     return (entityIn instanceof EntityPlayer) ? attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)entityIn), 0.0F) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumFacing func_174811_aO() {
/* 178 */     return this.field_174860_b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 186 */     if (func_180431_b(source))
/*     */     {
/* 188 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 192 */     if (!this.isDead && !this.worldObj.isRemote) {
/*     */       
/* 194 */       setDead();
/* 195 */       setBeenAttacked();
/* 196 */       onBroken(source.getEntity());
/*     */     } 
/*     */     
/* 199 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveEntity(double x, double y, double z) {
/* 208 */     if (!this.worldObj.isRemote && !this.isDead && x * x + y * y + z * z > 0.0D) {
/*     */       
/* 210 */       setDead();
/* 211 */       onBroken((Entity)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVelocity(double x, double y, double z) {
/* 220 */     if (!this.worldObj.isRemote && !this.isDead && x * x + y * y + z * z > 0.0D) {
/*     */       
/* 222 */       setDead();
/* 223 */       onBroken((Entity)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 232 */     tagCompound.setByte("Facing", (byte)this.field_174860_b.getHorizontalIndex());
/* 233 */     tagCompound.setInteger("TileX", func_174857_n().getX());
/* 234 */     tagCompound.setInteger("TileY", func_174857_n().getY());
/* 235 */     tagCompound.setInteger("TileZ", func_174857_n().getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*     */     EnumFacing var2;
/* 243 */     this.field_174861_a = new BlockPos(tagCompund.getInteger("TileX"), tagCompund.getInteger("TileY"), tagCompund.getInteger("TileZ"));
/*     */ 
/*     */     
/* 246 */     if (tagCompund.hasKey("Direction", 99)) {
/*     */       
/* 248 */       var2 = EnumFacing.getHorizontal(tagCompund.getByte("Direction"));
/* 249 */       this.field_174861_a = this.field_174861_a.offset(var2);
/*     */     }
/* 251 */     else if (tagCompund.hasKey("Facing", 99)) {
/*     */       
/* 253 */       var2 = EnumFacing.getHorizontal(tagCompund.getByte("Facing"));
/*     */     }
/*     */     else {
/*     */       
/* 257 */       var2 = EnumFacing.getHorizontal(tagCompund.getByte("Dir"));
/*     */     } 
/*     */     
/* 260 */     func_174859_a(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getWidthPixels();
/*     */ 
/*     */   
/*     */   public abstract int getHeightPixels();
/*     */ 
/*     */   
/*     */   public abstract void onBroken(Entity paramEntity);
/*     */ 
/*     */   
/*     */   protected boolean shouldSetPosAfterLoading() {
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(double x, double y, double z) {
/* 282 */     this.posX = x;
/* 283 */     this.posY = y;
/* 284 */     this.posZ = z;
/* 285 */     BlockPos var7 = this.field_174861_a;
/* 286 */     this.field_174861_a = new BlockPos(x, y, z);
/*     */     
/* 288 */     if (!this.field_174861_a.equals(var7)) {
/*     */       
/* 290 */       func_174856_o();
/* 291 */       this.isAirBorne = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_174857_n() {
/* 297 */     return this.field_174861_a;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */