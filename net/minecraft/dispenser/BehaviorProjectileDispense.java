/*    */ package net.minecraft.dispenser;
/*    */ 
/*    */ import net.minecraft.block.BlockDispenser;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.IProjectile;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BehaviorProjectileDispense
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   private static final String __OBFID = "CL_00001394";
/*    */   
/*    */   public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 19 */     World var3 = source.getWorld();
/* 20 */     IPosition var4 = BlockDispenser.getDispensePosition(source);
/* 21 */     EnumFacing var5 = BlockDispenser.getFacing(source.getBlockMetadata());
/* 22 */     IProjectile var6 = getProjectileEntity(var3, var4);
/* 23 */     var6.setThrowableHeading(var5.getFrontOffsetX(), (var5.getFrontOffsetY() + 0.1F), var5.getFrontOffsetZ(), func_82500_b(), func_82498_a());
/* 24 */     var3.spawnEntityInWorld((Entity)var6);
/* 25 */     stack.splitStack(1);
/* 26 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void playDispenseSound(IBlockSource source) {
/* 34 */     source.getWorld().playAuxSFX(1002, source.getBlockPos(), 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract IProjectile getProjectileEntity(World paramWorld, IPosition paramIPosition);
/*    */ 
/*    */ 
/*    */   
/*    */   protected float func_82498_a() {
/* 44 */     return 6.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_82500_b() {
/* 49 */     return 1.1F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\dispenser\BehaviorProjectileDispense.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */