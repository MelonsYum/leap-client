/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ public enum EnumRarity
/*    */ {
/*  7 */   COMMON("COMMON", 0, EnumChatFormatting.WHITE, "Common"),
/*  8 */   UNCOMMON("UNCOMMON", 1, EnumChatFormatting.YELLOW, "Uncommon"),
/*  9 */   RARE("RARE", 2, EnumChatFormatting.AQUA, "Rare"),
/* 10 */   EPIC("EPIC", 3, EnumChatFormatting.LIGHT_PURPLE, "Epic");
/*    */   
/*    */   public final EnumChatFormatting rarityColor;
/*    */   
/*    */   public final String rarityName;
/*    */   
/*    */   private static final EnumRarity[] $VALUES;
/*    */   
/*    */   private static final String __OBFID = "CL_00000056";
/*    */   
/*    */   static {
/* 21 */     $VALUES = new EnumRarity[] { COMMON, UNCOMMON, RARE, EPIC };
/*    */   }
/*    */ 
/*    */   
/*    */   EnumRarity(String p_i45349_1_, int p_i45349_2_, EnumChatFormatting p_i45349_3_, String p_i45349_4_) {
/* 26 */     this.rarityColor = p_i45349_3_;
/* 27 */     this.rarityName = p_i45349_4_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\EnumRarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */