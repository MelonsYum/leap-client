/*     */ package net.minecraft.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityBoat;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBoat
/*     */   extends Item
/*     */ {
/*     */   private static final String __OBFID = "CL_00001774";
/*     */   
/*     */   public ItemBoat() {
/*  23 */     this.maxStackSize = 1;
/*  24 */     setCreativeTab(CreativeTabs.tabTransport);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/*  32 */     float var4 = 1.0F;
/*  33 */     float var5 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * var4;
/*  34 */     float var6 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * var4;
/*  35 */     double var7 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * var4;
/*  36 */     double var9 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * var4 + playerIn.getEyeHeight();
/*  37 */     double var11 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * var4;
/*  38 */     Vec3 var13 = new Vec3(var7, var9, var11);
/*  39 */     float var14 = MathHelper.cos(-var6 * 0.017453292F - 3.1415927F);
/*  40 */     float var15 = MathHelper.sin(-var6 * 0.017453292F - 3.1415927F);
/*  41 */     float var16 = -MathHelper.cos(-var5 * 0.017453292F);
/*  42 */     float var17 = MathHelper.sin(-var5 * 0.017453292F);
/*  43 */     float var18 = var15 * var16;
/*  44 */     float var20 = var14 * var16;
/*  45 */     double var21 = 5.0D;
/*  46 */     Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
/*  47 */     MovingObjectPosition var24 = worldIn.rayTraceBlocks(var13, var23, true);
/*     */     
/*  49 */     if (var24 == null)
/*     */     {
/*  51 */       return itemStackIn;
/*     */     }
/*     */ 
/*     */     
/*  55 */     Vec3 var25 = playerIn.getLook(var4);
/*  56 */     boolean var26 = false;
/*  57 */     float var27 = 1.0F;
/*  58 */     List<Entity> var28 = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)playerIn, playerIn.getEntityBoundingBox().addCoord(var25.xCoord * var21, var25.yCoord * var21, var25.zCoord * var21).expand(var27, var27, var27));
/*     */     
/*  60 */     for (int var29 = 0; var29 < var28.size(); var29++) {
/*     */       
/*  62 */       Entity var30 = var28.get(var29);
/*     */       
/*  64 */       if (var30.canBeCollidedWith()) {
/*     */         
/*  66 */         float var31 = var30.getCollisionBorderSize();
/*  67 */         AxisAlignedBB var32 = var30.getEntityBoundingBox().expand(var31, var31, var31);
/*     */         
/*  69 */         if (var32.isVecInside(var13))
/*     */         {
/*  71 */           var26 = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     if (var26)
/*     */     {
/*  78 */       return itemStackIn;
/*     */     }
/*     */ 
/*     */     
/*  82 */     if (var24.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/*  84 */       BlockPos var33 = var24.func_178782_a();
/*     */       
/*  86 */       if (worldIn.getBlockState(var33).getBlock() == Blocks.snow_layer)
/*     */       {
/*  88 */         var33 = var33.offsetDown();
/*     */       }
/*     */       
/*  91 */       EntityBoat var34 = new EntityBoat(worldIn, (var33.getX() + 0.5F), (var33.getY() + 1.0F), (var33.getZ() + 0.5F));
/*  92 */       var34.rotationYaw = (((MathHelper.floor_double((playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3) - 1) * 90);
/*     */       
/*  94 */       if (!worldIn.getCollidingBoundingBoxes((Entity)var34, var34.getEntityBoundingBox().expand(-0.1D, -0.1D, -0.1D)).isEmpty())
/*     */       {
/*  96 */         return itemStackIn;
/*     */       }
/*     */       
/*  99 */       if (!worldIn.isRemote)
/*     */       {
/* 101 */         worldIn.spawnEntityInWorld((Entity)var34);
/*     */       }
/*     */       
/* 104 */       if (!playerIn.capabilities.isCreativeMode)
/*     */       {
/* 106 */         itemStackIn.stackSize--;
/*     */       }
/*     */       
/* 109 */       playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*     */     } 
/*     */     
/* 112 */     return itemStackIn;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */