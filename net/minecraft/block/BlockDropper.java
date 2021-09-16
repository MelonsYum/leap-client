/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*    */ import net.minecraft.dispenser.IBehaviorDispenseItem;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityDispenser;
/*    */ import net.minecraft.tileentity.TileEntityDropper;
/*    */ import net.minecraft.tileentity.TileEntityHopper;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockDropper extends BlockDispenser {
/* 17 */   private final IBehaviorDispenseItem field_149947_P = (IBehaviorDispenseItem)new BehaviorDefaultDispenseItem();
/*    */   
/*    */   private static final String __OBFID = "CL_00000233";
/*    */   
/*    */   protected IBehaviorDispenseItem func_149940_a(ItemStack p_149940_1_) {
/* 22 */     return this.field_149947_P;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 30 */     return (TileEntity)new TileEntityDropper();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_176439_d(World worldIn, BlockPos p_176439_2_) {
/* 35 */     BlockSourceImpl var3 = new BlockSourceImpl(worldIn, p_176439_2_);
/* 36 */     TileEntityDispenser var4 = (TileEntityDispenser)var3.getBlockTileEntity();
/*    */     
/* 38 */     if (var4 != null) {
/*    */       
/* 40 */       int var5 = var4.func_146017_i();
/*    */       
/* 42 */       if (var5 < 0) {
/*    */         
/* 44 */         worldIn.playAuxSFX(1001, p_176439_2_, 0);
/*    */       }
/*    */       else {
/*    */         
/* 48 */         ItemStack var6 = var4.getStackInSlot(var5);
/*    */         
/* 50 */         if (var6 != null) {
/*    */           ItemStack var10;
/* 52 */           EnumFacing var7 = (EnumFacing)worldIn.getBlockState(p_176439_2_).getValue((IProperty)FACING);
/* 53 */           BlockPos var8 = p_176439_2_.offset(var7);
/* 54 */           IInventory var9 = TileEntityHopper.func_145893_b(worldIn, var8.getX(), var8.getY(), var8.getZ());
/*    */ 
/*    */           
/* 57 */           if (var9 == null) {
/*    */             
/* 59 */             var10 = this.field_149947_P.dispense(var3, var6);
/*    */             
/* 61 */             if (var10 != null && var10.stackSize == 0)
/*    */             {
/* 63 */               var10 = null;
/*    */             }
/*    */           }
/*    */           else {
/*    */             
/* 68 */             var10 = TileEntityHopper.func_174918_a(var9, var6.copy().splitStack(1), var7.getOpposite());
/*    */             
/* 70 */             if (var10 == null) {
/*    */               
/* 72 */               var10 = var6.copy();
/*    */               
/* 74 */               if (--var10.stackSize == 0)
/*    */               {
/* 76 */                 var10 = null;
/*    */               }
/*    */             }
/*    */             else {
/*    */               
/* 81 */               var10 = var6.copy();
/*    */             } 
/*    */           } 
/*    */           
/* 85 */           var4.setInventorySlotContents(var5, var10);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDropper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */