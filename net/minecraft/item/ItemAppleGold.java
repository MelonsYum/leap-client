/*    */ package net.minecraft.item;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemAppleGold
/*    */   extends ItemFood
/*    */ {
/*    */   private static final String __OBFID = "CL_00000037";
/*    */   
/*    */   public ItemAppleGold(int p_i45341_1_, float p_i45341_2_, boolean p_i45341_3_) {
/* 16 */     super(p_i45341_1_, p_i45341_2_, p_i45341_3_);
/* 17 */     setHasSubtypes(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasEffect(ItemStack stack) {
/* 22 */     return (stack.getMetadata() > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack stack) {
/* 30 */     return (stack.getMetadata() == 0) ? EnumRarity.RARE : EnumRarity.EPIC;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onFoodEaten(ItemStack p_77849_1_, World worldIn, EntityPlayer p_77849_3_) {
/* 35 */     if (!worldIn.isRemote)
/*    */     {
/* 37 */       p_77849_3_.addPotionEffect(new PotionEffect(Potion.absorption.id, 2400, 0));
/*    */     }
/*    */     
/* 40 */     if (p_77849_1_.getMetadata() > 0) {
/*    */       
/* 42 */       if (!worldIn.isRemote)
/*    */       {
/* 44 */         p_77849_3_.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
/* 45 */         p_77849_3_.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
/* 46 */         p_77849_3_.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 51 */       super.onFoodEaten(p_77849_1_, worldIn, p_77849_3_);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/* 62 */     subItems.add(new ItemStack(itemIn, 1, 0));
/* 63 */     subItems.add(new ItemStack(itemIn, 1, 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemAppleGold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */