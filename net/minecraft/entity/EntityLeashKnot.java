/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class EntityLeashKnot
/*     */   extends EntityHanging
/*     */ {
/*     */   private static final String __OBFID = "CL_00001548";
/*     */   
/*     */   public EntityLeashKnot(World worldIn) {
/*  21 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLeashKnot(World worldIn, BlockPos p_i45851_2_) {
/*  26 */     super(worldIn, p_i45851_2_);
/*  27 */     setPosition(p_i45851_2_.getX() + 0.5D, p_i45851_2_.getY() + 0.5D, p_i45851_2_.getZ() + 0.5D);
/*  28 */     float var3 = 0.125F;
/*  29 */     float var4 = 0.1875F;
/*  30 */     float var5 = 0.25F;
/*  31 */     func_174826_a(new AxisAlignedBB(this.posX - 0.1875D, this.posY - 0.25D + 0.125D, this.posZ - 0.1875D, this.posX + 0.1875D, this.posY + 0.25D + 0.125D, this.posZ + 0.1875D));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  36 */     super.entityInit();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174859_a(EnumFacing p_174859_1_) {}
/*     */   
/*     */   public int getWidthPixels() {
/*  43 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeightPixels() {
/*  48 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/*  53 */     return -0.0625F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double distance) {
/*  62 */     return (distance < 1024.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBroken(Entity p_110128_1_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean writeToNBTOptional(NBTTagCompound tagCompund) {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactFirst(EntityPlayer playerIn) {
/*  95 */     ItemStack var2 = playerIn.getHeldItem();
/*  96 */     boolean var3 = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (var2 != null && var2.getItem() == Items.lead && !this.worldObj.isRemote) {
/*     */       
/* 104 */       double var4 = 7.0D;
/* 105 */       List var6 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(this.posX - var4, this.posY - var4, this.posZ - var4, this.posX + var4, this.posY + var4, this.posZ + var4));
/* 106 */       Iterator<EntityLiving> var7 = var6.iterator();
/*     */       
/* 108 */       while (var7.hasNext()) {
/*     */         
/* 110 */         EntityLiving var8 = var7.next();
/*     */         
/* 112 */         if (var8.getLeashed() && var8.getLeashedToEntity() == playerIn) {
/*     */           
/* 114 */           var8.setLeashedToEntity(this, true);
/* 115 */           var3 = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     if (!this.worldObj.isRemote && !var3) {
/*     */       
/* 122 */       setDead();
/*     */       
/* 124 */       if (playerIn.capabilities.isCreativeMode) {
/*     */         
/* 126 */         double var4 = 7.0D;
/* 127 */         List var6 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(this.posX - var4, this.posY - var4, this.posZ - var4, this.posX + var4, this.posY + var4, this.posZ + var4));
/* 128 */         Iterator<EntityLiving> var7 = var6.iterator();
/*     */         
/* 130 */         while (var7.hasNext()) {
/*     */           
/* 132 */           EntityLiving var8 = var7.next();
/*     */           
/* 134 */           if (var8.getLeashed() && var8.getLeashedToEntity() == this)
/*     */           {
/* 136 */             var8.clearLeashed(true, false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onValidSurface() {
/* 150 */     return this.worldObj.getBlockState(this.field_174861_a).getBlock() instanceof net.minecraft.block.BlockFence;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityLeashKnot func_174862_a(World worldIn, BlockPos p_174862_1_) {
/* 155 */     EntityLeashKnot var2 = new EntityLeashKnot(worldIn, p_174862_1_);
/* 156 */     var2.forceSpawn = true;
/* 157 */     worldIn.spawnEntityInWorld(var2);
/* 158 */     return var2;
/*     */   }
/*     */   
/*     */   public static EntityLeashKnot func_174863_b(World worldIn, BlockPos p_174863_1_) {
/*     */     EntityLeashKnot var7;
/* 163 */     int var2 = p_174863_1_.getX();
/* 164 */     int var3 = p_174863_1_.getY();
/* 165 */     int var4 = p_174863_1_.getZ();
/* 166 */     List var5 = worldIn.getEntitiesWithinAABB(EntityLeashKnot.class, new AxisAlignedBB(var2 - 1.0D, var3 - 1.0D, var4 - 1.0D, var2 + 1.0D, var3 + 1.0D, var4 + 1.0D));
/* 167 */     Iterator<EntityLeashKnot> var6 = var5.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 172 */       if (!var6.hasNext())
/*     */       {
/* 174 */         return null;
/*     */       }
/*     */       
/* 177 */       var7 = var6.next();
/*     */     }
/* 179 */     while (!var7.func_174857_n().equals(p_174863_1_));
/*     */     
/* 181 */     return var7;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityLeashKnot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */