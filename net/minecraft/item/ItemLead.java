/*    */ package net.minecraft.item;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLeashKnot;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemLead
/*    */   extends Item
/*    */ {
/*    */   private static final String __OBFID = "CL_00000045";
/*    */   
/*    */   public ItemLead() {
/* 22 */     setCreativeTab(CreativeTabs.tabTools);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 33 */     Block var9 = worldIn.getBlockState(pos).getBlock();
/*    */     
/* 35 */     if (var9 instanceof net.minecraft.block.BlockFence) {
/*    */       
/* 37 */       if (worldIn.isRemote)
/*    */       {
/* 39 */         return true;
/*    */       }
/*    */ 
/*    */       
/* 43 */       func_180618_a(playerIn, worldIn, pos);
/* 44 */       return true;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean func_180618_a(EntityPlayer p_180618_0_, World worldIn, BlockPos p_180618_2_) {
/* 55 */     EntityLeashKnot var3 = EntityLeashKnot.func_174863_b(worldIn, p_180618_2_);
/* 56 */     boolean var4 = false;
/* 57 */     double var5 = 7.0D;
/* 58 */     int var7 = p_180618_2_.getX();
/* 59 */     int var8 = p_180618_2_.getY();
/* 60 */     int var9 = p_180618_2_.getZ();
/* 61 */     List var10 = worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(var7 - var5, var8 - var5, var9 - var5, var7 + var5, var8 + var5, var9 + var5));
/* 62 */     Iterator<EntityLiving> var11 = var10.iterator();
/*    */     
/* 64 */     while (var11.hasNext()) {
/*    */       
/* 66 */       EntityLiving var12 = var11.next();
/*    */       
/* 68 */       if (var12.getLeashed() && var12.getLeashedToEntity() == p_180618_0_) {
/*    */         
/* 70 */         if (var3 == null)
/*    */         {
/* 72 */           var3 = EntityLeashKnot.func_174862_a(worldIn, p_180618_2_);
/*    */         }
/*    */         
/* 75 */         var12.setLeashedToEntity((Entity)var3, true);
/* 76 */         var4 = true;
/*    */       } 
/*    */     } 
/*    */     
/* 80 */     return var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemLead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */