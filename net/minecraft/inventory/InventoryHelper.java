/*    */ package net.minecraft.inventory;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class InventoryHelper
/*    */ {
/* 13 */   private static final Random field_180177_a = new Random();
/*    */   
/*    */   private static final String __OBFID = "CL_00002262";
/*    */   
/*    */   public static void dropInventoryItems(World worldIn, BlockPos p_180175_1_, IInventory p_180175_2_) {
/* 18 */     func_180174_a(worldIn, p_180175_1_.getX(), p_180175_1_.getY(), p_180175_1_.getZ(), p_180175_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void func_180176_a(World worldIn, Entity p_180176_1_, IInventory p_180176_2_) {
/* 23 */     func_180174_a(worldIn, p_180176_1_.posX, p_180176_1_.posY, p_180176_1_.posZ, p_180176_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   private static void func_180174_a(World worldIn, double p_180174_1_, double p_180174_3_, double p_180174_5_, IInventory p_180174_7_) {
/* 28 */     for (int var8 = 0; var8 < p_180174_7_.getSizeInventory(); var8++) {
/*    */       
/* 30 */       ItemStack var9 = p_180174_7_.getStackInSlot(var8);
/*    */       
/* 32 */       if (var9 != null)
/*    */       {
/* 34 */         func_180173_a(worldIn, p_180174_1_, p_180174_3_, p_180174_5_, var9);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static void func_180173_a(World worldIn, double p_180173_1_, double p_180173_3_, double p_180173_5_, ItemStack p_180173_7_) {
/* 41 */     float var8 = field_180177_a.nextFloat() * 0.8F + 0.1F;
/* 42 */     float var9 = field_180177_a.nextFloat() * 0.8F + 0.1F;
/* 43 */     float var10 = field_180177_a.nextFloat() * 0.8F + 0.1F;
/*    */     
/* 45 */     while (p_180173_7_.stackSize > 0) {
/*    */       
/* 47 */       int var11 = field_180177_a.nextInt(21) + 10;
/*    */       
/* 49 */       if (var11 > p_180173_7_.stackSize)
/*    */       {
/* 51 */         var11 = p_180173_7_.stackSize;
/*    */       }
/*    */       
/* 54 */       p_180173_7_.stackSize -= var11;
/* 55 */       EntityItem var12 = new EntityItem(worldIn, p_180173_1_ + var8, p_180173_3_ + var9, p_180173_5_ + var10, new ItemStack(p_180173_7_.getItem(), var11, p_180173_7_.getMetadata()));
/*    */       
/* 57 */       if (p_180173_7_.hasTagCompound())
/*    */       {
/* 59 */         var12.getEntityItem().setTagCompound((NBTTagCompound)p_180173_7_.getTagCompound().copy());
/*    */       }
/*    */       
/* 62 */       float var13 = 0.05F;
/* 63 */       var12.motionX = field_180177_a.nextGaussian() * var13;
/* 64 */       var12.motionY = field_180177_a.nextGaussian() * var13 + 0.20000000298023224D;
/* 65 */       var12.motionZ = field_180177_a.nextGaussian() * var13;
/* 66 */       worldIn.spawnEntityInWorld((Entity)var12);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\InventoryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */