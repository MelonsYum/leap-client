/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class WeightedRandomFishable
/*    */   extends WeightedRandom.Item
/*    */ {
/*    */   private final ItemStack returnStack;
/*    */   private float maxDamagePercent;
/*    */   private boolean enchantable;
/*    */   private static final String __OBFID = "CL_00001664";
/*    */   
/*    */   public WeightedRandomFishable(ItemStack p_i45317_1_, int p_i45317_2_) {
/* 16 */     super(p_i45317_2_);
/* 17 */     this.returnStack = p_i45317_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(Random p_150708_1_) {
/* 22 */     ItemStack var2 = this.returnStack.copy();
/*    */     
/* 24 */     if (this.maxDamagePercent > 0.0F) {
/*    */       
/* 26 */       int var3 = (int)(this.maxDamagePercent * this.returnStack.getMaxDamage());
/* 27 */       int var4 = var2.getMaxDamage() - p_150708_1_.nextInt(p_150708_1_.nextInt(var3) + 1);
/*    */       
/* 29 */       if (var4 > var3)
/*    */       {
/* 31 */         var4 = var3;
/*    */       }
/*    */       
/* 34 */       if (var4 < 1)
/*    */       {
/* 36 */         var4 = 1;
/*    */       }
/*    */       
/* 39 */       var2.setItemDamage(var4);
/*    */     } 
/*    */     
/* 42 */     if (this.enchantable)
/*    */     {
/* 44 */       EnchantmentHelper.addRandomEnchantment(p_150708_1_, var2, 30);
/*    */     }
/*    */     
/* 47 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public WeightedRandomFishable setMaxDamagePercent(float p_150709_1_) {
/* 52 */     this.maxDamagePercent = p_150709_1_;
/* 53 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public WeightedRandomFishable setEnchantable() {
/* 58 */     this.enchantable = true;
/* 59 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\WeightedRandomFishable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */