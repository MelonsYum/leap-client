/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockSlime
/*    */   extends BlockBreakable
/*    */ {
/*    */   private static final String __OBFID = "CL_00002063";
/*    */   
/*    */   public BlockSlime() {
/* 16 */     super(Material.clay, false);
/* 17 */     setCreativeTab(CreativeTabs.tabDecorations);
/* 18 */     this.slipperiness = 0.8F;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumWorldBlockLayer getBlockLayer() {
/* 23 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
/* 33 */     if (entityIn.isSneaking()) {
/*    */       
/* 35 */       super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
/*    */     }
/*    */     else {
/*    */       
/* 39 */       entityIn.fall(fallDistance, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onLanded(World worldIn, Entity entityIn) {
/* 49 */     if (entityIn.isSneaking()) {
/*    */       
/* 51 */       super.onLanded(worldIn, entityIn);
/*    */     }
/* 53 */     else if (entityIn.motionY < 0.0D) {
/*    */       
/* 55 */       entityIn.motionY = -entityIn.motionY;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
/* 64 */     if (Math.abs(entityIn.motionY) < 0.1D && !entityIn.isSneaking()) {
/*    */       
/* 66 */       double var4 = 0.4D + Math.abs(entityIn.motionY) * 0.2D;
/* 67 */       entityIn.motionX *= var4;
/* 68 */       entityIn.motionZ *= var4;
/*    */     } 
/*    */     
/* 71 */     super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */