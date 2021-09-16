/*     */ package net.minecraft.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Rotations;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemArmorStand
/*     */   extends Item
/*     */ {
/*     */   private static final String __OBFID = "CL_00002182";
/*     */   
/*     */   public ItemArmorStand() {
/*  23 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  34 */     if (side == EnumFacing.DOWN)
/*     */     {
/*  36 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  40 */     boolean var9 = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
/*  41 */     BlockPos var10 = var9 ? pos : pos.offset(side);
/*     */     
/*  43 */     if (!playerIn.func_175151_a(var10, side, stack))
/*     */     {
/*  45 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  49 */     BlockPos var11 = var10.offsetUp();
/*  50 */     boolean var12 = (!worldIn.isAirBlock(var10) && !worldIn.getBlockState(var10).getBlock().isReplaceable(worldIn, var10));
/*  51 */     int i = var12 | ((!worldIn.isAirBlock(var11) && !worldIn.getBlockState(var11).getBlock().isReplaceable(worldIn, var11)) ? 1 : 0);
/*     */     
/*  53 */     if (i != 0)
/*     */     {
/*  55 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  59 */     double var13 = var10.getX();
/*  60 */     double var15 = var10.getY();
/*  61 */     double var17 = var10.getZ();
/*  62 */     List var19 = worldIn.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.fromBounds(var13, var15, var17, var13 + 1.0D, var15 + 2.0D, var17 + 1.0D));
/*     */     
/*  64 */     if (var19.size() > 0)
/*     */     {
/*  66 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  70 */     if (!worldIn.isRemote) {
/*     */       
/*  72 */       worldIn.setBlockToAir(var10);
/*  73 */       worldIn.setBlockToAir(var11);
/*  74 */       EntityArmorStand var20 = new EntityArmorStand(worldIn, var13 + 0.5D, var15, var17 + 0.5D);
/*  75 */       float var21 = MathHelper.floor_float((MathHelper.wrapAngleTo180_float(playerIn.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
/*  76 */       var20.setLocationAndAngles(var13 + 0.5D, var15, var17 + 0.5D, var21, 0.0F);
/*  77 */       func_179221_a(var20, worldIn.rand);
/*  78 */       NBTTagCompound var22 = stack.getTagCompound();
/*     */       
/*  80 */       if (var22 != null && var22.hasKey("EntityTag", 10)) {
/*     */         
/*  82 */         NBTTagCompound var23 = new NBTTagCompound();
/*  83 */         var20.writeToNBTOptional(var23);
/*  84 */         var23.merge(var22.getCompoundTag("EntityTag"));
/*  85 */         var20.readFromNBT(var23);
/*     */       } 
/*     */       
/*  88 */       worldIn.spawnEntityInWorld((Entity)var20);
/*     */     } 
/*     */     
/*  91 */     stack.stackSize--;
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_179221_a(EntityArmorStand p_179221_1_, Random p_179221_2_) {
/* 101 */     Rotations var3 = p_179221_1_.getHeadRotation();
/* 102 */     float var5 = p_179221_2_.nextFloat() * 5.0F;
/* 103 */     float var6 = p_179221_2_.nextFloat() * 20.0F - 10.0F;
/* 104 */     Rotations var4 = new Rotations(var3.func_179415_b() + var5, var3.func_179416_c() + var6, var3.func_179413_d());
/* 105 */     p_179221_1_.setHeadRotation(var4);
/* 106 */     var3 = p_179221_1_.getBodyRotation();
/* 107 */     var5 = p_179221_2_.nextFloat() * 10.0F - 5.0F;
/* 108 */     var4 = new Rotations(var3.func_179415_b(), var3.func_179416_c() + var5, var3.func_179413_d());
/* 109 */     p_179221_1_.setBodyRotation(var4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */