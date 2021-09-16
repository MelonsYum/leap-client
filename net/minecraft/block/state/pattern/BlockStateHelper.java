/*    */ package net.minecraft.block.state.pattern;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ 
/*    */ public class BlockStateHelper
/*    */   implements Predicate
/*    */ {
/*    */   private final BlockState field_177641_a;
/* 16 */   private final Map field_177640_b = Maps.newHashMap();
/*    */   
/*    */   private static final String __OBFID = "CL_00002019";
/*    */   
/*    */   private BlockStateHelper(BlockState p_i45653_1_) {
/* 21 */     this.field_177641_a = p_i45653_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public static BlockStateHelper forBlock(Block p_177638_0_) {
/* 26 */     return new BlockStateHelper(p_177638_0_.getBlockState());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_177639_a(IBlockState p_177639_1_) {
/* 31 */     if (p_177639_1_ != null && p_177639_1_.getBlock().equals(this.field_177641_a.getBlock())) {
/*    */       Map.Entry var3; Comparable var4;
/* 33 */       Iterator<Map.Entry> var2 = this.field_177640_b.entrySet().iterator();
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       do {
/* 39 */         if (!var2.hasNext())
/*    */         {
/* 41 */           return true;
/*    */         }
/*    */         
/* 44 */         var3 = var2.next();
/* 45 */         var4 = p_177639_1_.getValue((IProperty)var3.getKey());
/*    */       }
/* 47 */       while (((Predicate)var3.getValue()).apply(var4));
/*    */       
/* 49 */       return false;
/*    */     } 
/*    */ 
/*    */     
/* 53 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockStateHelper func_177637_a(IProperty p_177637_1_, Predicate p_177637_2_) {
/* 59 */     if (!this.field_177641_a.getProperties().contains(p_177637_1_))
/*    */     {
/* 61 */       throw new IllegalArgumentException(this.field_177641_a + " cannot support property " + p_177637_1_);
/*    */     }
/*    */ 
/*    */     
/* 65 */     this.field_177640_b.put(p_177637_1_, p_177637_2_);
/* 66 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean apply(Object p_apply_1_) {
/* 72 */     return func_177639_a((IBlockState)p_apply_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\pattern\BlockStateHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */