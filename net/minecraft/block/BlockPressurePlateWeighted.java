/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyInteger;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockPressurePlateWeighted
/*    */   extends BlockBasePressurePlate {
/* 15 */   public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
/*    */   
/*    */   private final int field_150068_a;
/*    */   private static final String __OBFID = "CL_00000334";
/*    */   
/*    */   protected BlockPressurePlateWeighted(String p_i45436_1_, Material p_i45436_2_, int p_i45436_3_) {
/* 21 */     super(p_i45436_2_);
/* 22 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)POWER, Integer.valueOf(0)));
/* 23 */     this.field_150068_a = p_i45436_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
/* 28 */     int var3 = Math.min(worldIn.getEntitiesWithinAABB(Entity.class, getSensitiveAABB(pos)).size(), this.field_150068_a);
/*    */     
/* 30 */     if (var3 > 0) {
/*    */       
/* 32 */       float var4 = Math.min(this.field_150068_a, var3) / this.field_150068_a;
/* 33 */       return MathHelper.ceiling_float_int(var4 * 15.0F);
/*    */     } 
/*    */ 
/*    */     
/* 37 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getRedstoneStrength(IBlockState p_176576_1_) {
/* 43 */     return ((Integer)p_176576_1_.getValue((IProperty)POWER)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   protected IBlockState setRedstoneStrength(IBlockState p_176575_1_, int p_176575_2_) {
/* 48 */     return p_176575_1_.withProperty((IProperty)POWER, Integer.valueOf(p_176575_2_));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int tickRate(World worldIn) {
/* 56 */     return 10;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState getStateFromMeta(int meta) {
/* 64 */     return getDefaultState().withProperty((IProperty)POWER, Integer.valueOf(meta));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 72 */     return ((Integer)state.getValue((IProperty)POWER)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState createBlockState() {
/* 77 */     return new BlockState(this, new IProperty[] { (IProperty)POWER });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPressurePlateWeighted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */