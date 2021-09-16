/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.block.BlockFurnace;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMinecartFurnace
/*     */   extends EntityMinecart {
/*     */   private int fuel;
/*     */   public double pushX;
/*     */   public double pushZ;
/*     */   private static final String __OBFID = "CL_00001675";
/*     */   
/*     */   public EntityMinecartFurnace(World worldIn) {
/*  26 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartFurnace(World worldIn, double p_i1719_2_, double p_i1719_4_, double p_i1719_6_) {
/*  31 */     super(worldIn, p_i1719_2_, p_i1719_4_, p_i1719_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecart.EnumMinecartType func_180456_s() {
/*  36 */     return EntityMinecart.EnumMinecartType.FURNACE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  41 */     super.entityInit();
/*  42 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  50 */     super.onUpdate();
/*     */     
/*  52 */     if (this.fuel > 0)
/*     */     {
/*  54 */       this.fuel--;
/*     */     }
/*     */     
/*  57 */     if (this.fuel <= 0)
/*     */     {
/*  59 */       this.pushX = this.pushZ = 0.0D;
/*     */     }
/*     */     
/*  62 */     setMinecartPowered((this.fuel > 0));
/*     */     
/*  64 */     if (isMinecartPowered() && this.rand.nextInt(4) == 0)
/*     */     {
/*  66 */       this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected double func_174898_m() {
/*  72 */     return 0.2D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void killMinecart(DamageSource p_94095_1_) {
/*  77 */     super.killMinecart(p_94095_1_);
/*     */     
/*  79 */     if (!p_94095_1_.isExplosion())
/*     */     {
/*  81 */       entityDropItem(new ItemStack(Blocks.furnace, 1), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180460_a(BlockPos p_180460_1_, IBlockState p_180460_2_) {
/*  87 */     super.func_180460_a(p_180460_1_, p_180460_2_);
/*  88 */     double var3 = this.pushX * this.pushX + this.pushZ * this.pushZ;
/*     */     
/*  90 */     if (var3 > 1.0E-4D && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001D) {
/*     */       
/*  92 */       var3 = MathHelper.sqrt_double(var3);
/*  93 */       this.pushX /= var3;
/*  94 */       this.pushZ /= var3;
/*     */       
/*  96 */       if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0D) {
/*     */         
/*  98 */         this.pushX = 0.0D;
/*  99 */         this.pushZ = 0.0D;
/*     */       }
/*     */       else {
/*     */         
/* 103 */         double var5 = var3 / func_174898_m();
/* 104 */         this.pushX *= var5;
/* 105 */         this.pushZ *= var5;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyDrag() {
/* 112 */     double var1 = this.pushX * this.pushX + this.pushZ * this.pushZ;
/*     */     
/* 114 */     if (var1 > 1.0E-4D) {
/*     */       
/* 116 */       var1 = MathHelper.sqrt_double(var1);
/* 117 */       this.pushX /= var1;
/* 118 */       this.pushZ /= var1;
/* 119 */       double var3 = 1.0D;
/* 120 */       this.motionX *= 0.800000011920929D;
/* 121 */       this.motionY *= 0.0D;
/* 122 */       this.motionZ *= 0.800000011920929D;
/* 123 */       this.motionX += this.pushX * var3;
/* 124 */       this.motionZ += this.pushZ * var3;
/*     */     }
/*     */     else {
/*     */       
/* 128 */       this.motionX *= 0.9800000190734863D;
/* 129 */       this.motionY *= 0.0D;
/* 130 */       this.motionZ *= 0.9800000190734863D;
/*     */     } 
/*     */     
/* 133 */     super.applyDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactFirst(EntityPlayer playerIn) {
/* 141 */     ItemStack var2 = playerIn.inventory.getCurrentItem();
/*     */     
/* 143 */     if (var2 != null && var2.getItem() == Items.coal) {
/*     */       
/* 145 */       if (!playerIn.capabilities.isCreativeMode && --var2.stackSize == 0)
/*     */       {
/* 147 */         playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
/*     */       }
/*     */       
/* 150 */       this.fuel += 3600;
/*     */     } 
/*     */     
/* 153 */     this.pushX = this.posX - playerIn.posX;
/* 154 */     this.pushZ = this.posZ - playerIn.posZ;
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 163 */     super.writeEntityToNBT(tagCompound);
/* 164 */     tagCompound.setDouble("PushX", this.pushX);
/* 165 */     tagCompound.setDouble("PushZ", this.pushZ);
/* 166 */     tagCompound.setShort("Fuel", (short)this.fuel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 174 */     super.readEntityFromNBT(tagCompund);
/* 175 */     this.pushX = tagCompund.getDouble("PushX");
/* 176 */     this.pushZ = tagCompund.getDouble("PushZ");
/* 177 */     this.fuel = tagCompund.getShort("Fuel");
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isMinecartPowered() {
/* 182 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setMinecartPowered(boolean p_94107_1_) {
/* 187 */     if (p_94107_1_) {
/*     */       
/* 189 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(this.dataWatcher.getWatchableObjectByte(16) | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 193 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(this.dataWatcher.getWatchableObjectByte(16) & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_180457_u() {
/* 199 */     return (isMinecartPowered() ? Blocks.lit_furnace : Blocks.furnace).getDefaultState().withProperty((IProperty)BlockFurnace.FACING, (Comparable)EnumFacing.NORTH);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityMinecartFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */