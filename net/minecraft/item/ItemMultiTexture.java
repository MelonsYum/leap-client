/*    */ package net.minecraft.item;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ public class ItemMultiTexture
/*    */   extends ItemBlock
/*    */ {
/*    */   protected final Block theBlock;
/*    */   protected final Function nameFunction;
/*    */   private static final String __OBFID = "CL_00000051";
/*    */   
/*    */   public ItemMultiTexture(Block p_i45784_1_, Block p_i45784_2_, Function p_i45784_3_) {
/* 14 */     super(p_i45784_1_);
/* 15 */     this.theBlock = p_i45784_2_;
/* 16 */     this.nameFunction = p_i45784_3_;
/* 17 */     setMaxDamage(0);
/* 18 */     setHasSubtypes(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemMultiTexture(Block p_i45346_1_, Block p_i45346_2_, String[] p_i45346_3_) {
/* 23 */     this(p_i45346_1_, p_i45346_2_, new Function(p_i45346_3_)
/*    */         {
/*    */           private static final String __OBFID = "CL_00002161";
/*    */           
/*    */           public String apply(ItemStack p_179541_1_) {
/* 28 */             int var2 = p_179541_1_.getMetadata();
/*    */             
/* 30 */             if (var2 < 0 || var2 >= p_i45346_3_.length)
/*    */             {
/* 32 */               var2 = 0;
/*    */             }
/*    */             
/* 35 */             return p_i45346_3_[var2];
/*    */           }
/*    */           
/*    */           public Object apply(Object p_apply_1_) {
/* 39 */             return apply((ItemStack)p_apply_1_);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int damage) {
/* 50 */     return damage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack stack) {
/* 59 */     return String.valueOf(getUnlocalizedName()) + "." + (String)this.nameFunction.apply(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemMultiTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */