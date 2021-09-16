/*    */ package net.minecraft.dispenser;
/*    */ 
/*    */ import net.minecraft.block.BlockDispenser;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorDefaultDispenseItem
/*    */   implements IBehaviorDispenseItem
/*    */ {
/*    */   private static final String __OBFID = "CL_00001195";
/*    */   
/*    */   public final ItemStack dispense(IBlockSource source, ItemStack stack) {
/* 18 */     ItemStack var3 = dispenseStack(source, stack);
/* 19 */     playDispenseSound(source);
/* 20 */     spawnDispenseParticles(source, BlockDispenser.getFacing(source.getBlockMetadata()));
/* 21 */     return var3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 29 */     EnumFacing var3 = BlockDispenser.getFacing(source.getBlockMetadata());
/* 30 */     IPosition var4 = BlockDispenser.getDispensePosition(source);
/* 31 */     ItemStack var5 = stack.splitStack(1);
/* 32 */     doDispense(source.getWorld(), var5, 6, var3, var4);
/* 33 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void doDispense(World worldIn, ItemStack stack, int speed, EnumFacing p_82486_3_, IPosition position) {
/* 38 */     double var5 = position.getX();
/* 39 */     double var7 = position.getY();
/* 40 */     double var9 = position.getZ();
/*    */     
/* 42 */     if (p_82486_3_.getAxis() == EnumFacing.Axis.Y) {
/*    */       
/* 44 */       var7 -= 0.125D;
/*    */     }
/*    */     else {
/*    */       
/* 48 */       var7 -= 0.15625D;
/*    */     } 
/*    */     
/* 51 */     EntityItem var11 = new EntityItem(worldIn, var5, var7, var9, stack);
/* 52 */     double var12 = worldIn.rand.nextDouble() * 0.1D + 0.2D;
/* 53 */     var11.motionX = p_82486_3_.getFrontOffsetX() * var12;
/* 54 */     var11.motionY = 0.20000000298023224D;
/* 55 */     var11.motionZ = p_82486_3_.getFrontOffsetZ() * var12;
/* 56 */     var11.motionX += worldIn.rand.nextGaussian() * 0.007499999832361937D * speed;
/* 57 */     var11.motionY += worldIn.rand.nextGaussian() * 0.007499999832361937D * speed;
/* 58 */     var11.motionZ += worldIn.rand.nextGaussian() * 0.007499999832361937D * speed;
/* 59 */     worldIn.spawnEntityInWorld((Entity)var11);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void playDispenseSound(IBlockSource source) {
/* 67 */     source.getWorld().playAuxSFX(1000, source.getBlockPos(), 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void spawnDispenseParticles(IBlockSource source, EnumFacing facingIn) {
/* 75 */     source.getWorld().playAuxSFX(2000, source.getBlockPos(), func_82488_a(facingIn));
/*    */   }
/*    */ 
/*    */   
/*    */   private int func_82488_a(EnumFacing facingIn) {
/* 80 */     return facingIn.getFrontOffsetX() + 1 + (facingIn.getFrontOffsetZ() + 1) * 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\dispenser\BehaviorDefaultDispenseItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */