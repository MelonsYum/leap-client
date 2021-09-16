/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumEnchantmentType
/*    */ {
/* 12 */   ALL("ALL", 0),
/* 13 */   ARMOR("ARMOR", 1),
/* 14 */   ARMOR_FEET("ARMOR_FEET", 2),
/* 15 */   ARMOR_LEGS("ARMOR_LEGS", 3),
/* 16 */   ARMOR_TORSO("ARMOR_TORSO", 4),
/* 17 */   ARMOR_HEAD("ARMOR_HEAD", 5),
/* 18 */   WEAPON("WEAPON", 6),
/* 19 */   DIGGER("DIGGER", 7),
/* 20 */   FISHING_ROD("FISHING_ROD", 8),
/* 21 */   BREAKABLE("BREAKABLE", 9),
/* 22 */   BOW("BOW", 10);
/*    */   static {
/* 24 */     $VALUES = new EnumEnchantmentType[] { ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW };
/*    */   }
/*    */ 
/*    */   
/*    */   private static final EnumEnchantmentType[] $VALUES;
/*    */   
/*    */   private static final String __OBFID = "CL_00000106";
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item p_77557_1_) {
/* 34 */     if (this == ALL)
/*    */     {
/* 36 */       return true;
/*    */     }
/* 38 */     if (this == BREAKABLE && p_77557_1_.isDamageable())
/*    */     {
/* 40 */       return true;
/*    */     }
/* 42 */     if (p_77557_1_ instanceof ItemArmor) {
/*    */       
/* 44 */       if (this == ARMOR)
/*    */       {
/* 46 */         return true;
/*    */       }
/*    */ 
/*    */       
/* 50 */       ItemArmor var2 = (ItemArmor)p_77557_1_;
/* 51 */       return (var2.armorType == 0) ? ((this == ARMOR_HEAD)) : ((var2.armorType == 2) ? ((this == ARMOR_LEGS)) : ((var2.armorType == 1) ? ((this == ARMOR_TORSO)) : ((var2.armorType == 3) ? ((this == ARMOR_FEET)) : false)));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 56 */     return (p_77557_1_ instanceof net.minecraft.item.ItemSword) ? ((this == WEAPON)) : ((p_77557_1_ instanceof net.minecraft.item.ItemTool) ? ((this == DIGGER)) : ((p_77557_1_ instanceof net.minecraft.item.ItemBow) ? ((this == BOW)) : ((p_77557_1_ instanceof net.minecraft.item.ItemFishingRod) ? ((this == FISHING_ROD)) : false)));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnumEnchantmentType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */