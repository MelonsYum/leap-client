/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.PropertyEnum;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public abstract class BlockRotatedPillar
/*    */   extends Block {
/*  9 */   public static final PropertyEnum field_176298_M = PropertyEnum.create("axis", EnumFacing.Axis.class);
/*    */   
/*    */   private static final String __OBFID = "CL_00000302";
/*    */   
/*    */   protected BlockRotatedPillar(Material p_i45425_1_) {
/* 14 */     super(p_i45425_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRotatedPillar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */