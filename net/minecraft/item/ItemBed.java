/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockBed;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemBed extends Item {
/*    */   private static final String __OBFID = "CL_00001771";
/*    */   
/*    */   public ItemBed() {
/* 20 */     setCreativeTab(CreativeTabs.tabDecorations);
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
/* 31 */     if (worldIn.isRemote)
/*    */     {
/* 33 */       return true;
/*    */     }
/* 35 */     if (side != EnumFacing.UP)
/*    */     {
/* 37 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 41 */     IBlockState var9 = worldIn.getBlockState(pos);
/* 42 */     Block var10 = var9.getBlock();
/* 43 */     boolean var11 = var10.isReplaceable(worldIn, pos);
/*    */     
/* 45 */     if (!var11)
/*    */     {
/* 47 */       pos = pos.offsetUp();
/*    */     }
/*    */     
/* 50 */     int var12 = MathHelper.floor_double((playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 51 */     EnumFacing var13 = EnumFacing.getHorizontal(var12);
/* 52 */     BlockPos var14 = pos.offset(var13);
/* 53 */     boolean var15 = var10.isReplaceable(worldIn, var14);
/* 54 */     boolean var16 = !(!worldIn.isAirBlock(pos) && !var11);
/* 55 */     boolean var17 = !(!worldIn.isAirBlock(var14) && !var15);
/*    */     
/* 57 */     if (playerIn.func_175151_a(pos, side, stack) && playerIn.func_175151_a(var14, side, stack)) {
/*    */       
/* 59 */       if (var16 && var17 && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, var14.offsetDown())) {
/*    */         
/* 61 */         int var18 = var13.getHorizontalIndex();
/* 62 */         IBlockState var19 = Blocks.bed.getDefaultState().withProperty((IProperty)BlockBed.OCCUPIED_PROP, Boolean.valueOf(false)).withProperty((IProperty)BlockBed.AGE, (Comparable)var13).withProperty((IProperty)BlockBed.PART_PROP, (Comparable)BlockBed.EnumPartType.FOOT);
/*    */         
/* 64 */         if (worldIn.setBlockState(pos, var19, 3)) {
/*    */           
/* 66 */           IBlockState var20 = var19.withProperty((IProperty)BlockBed.PART_PROP, (Comparable)BlockBed.EnumPartType.HEAD);
/* 67 */           worldIn.setBlockState(var14, var20, 3);
/*    */         } 
/*    */         
/* 70 */         stack.stackSize--;
/* 71 */         return true;
/*    */       } 
/*    */ 
/*    */       
/* 75 */       return false;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */