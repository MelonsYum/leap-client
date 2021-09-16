/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityHanging;
/*    */ import net.minecraft.entity.item.EntityItemFrame;
/*    */ import net.minecraft.entity.item.EntityPainting;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemHangingEntity
/*    */   extends Item {
/*    */   private final Class hangingEntityClass;
/*    */   private static final String __OBFID = "CL_00000038";
/*    */   
/*    */   public ItemHangingEntity(Class p_i45342_1_) {
/* 19 */     this.hangingEntityClass = p_i45342_1_;
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
/* 31 */     if (side == EnumFacing.DOWN)
/*    */     {
/* 33 */       return false;
/*    */     }
/* 35 */     if (side == EnumFacing.UP)
/*    */     {
/* 37 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 41 */     BlockPos var9 = pos.offset(side);
/*    */     
/* 43 */     if (!playerIn.func_175151_a(var9, side, stack))
/*    */     {
/* 45 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 49 */     EntityHanging var10 = func_179233_a(worldIn, var9, side);
/*    */     
/* 51 */     if (var10 != null && var10.onValidSurface()) {
/*    */       
/* 53 */       if (!worldIn.isRemote)
/*    */       {
/* 55 */         worldIn.spawnEntityInWorld((Entity)var10);
/*    */       }
/*    */       
/* 58 */       stack.stackSize--;
/*    */     } 
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private EntityHanging func_179233_a(World worldIn, BlockPos p_179233_2_, EnumFacing p_179233_3_) {
/* 68 */     return (this.hangingEntityClass == EntityPainting.class) ? (EntityHanging)new EntityPainting(worldIn, p_179233_2_, p_179233_3_) : ((this.hangingEntityClass == EntityItemFrame.class) ? (EntityHanging)new EntityItemFrame(worldIn, p_179233_2_, p_179233_3_) : null);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemHangingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */