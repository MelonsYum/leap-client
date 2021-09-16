/*    */ package net.minecraft.entity.player;
/*    */ 
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public enum EnumPlayerModelParts
/*    */ {
/*  8 */   CAPE("CAPE", 0, 0, "cape"),
/*  9 */   JACKET("JACKET", 1, 1, "jacket"),
/* 10 */   LEFT_SLEEVE("LEFT_SLEEVE", 2, 2, "left_sleeve"),
/* 11 */   RIGHT_SLEEVE("RIGHT_SLEEVE", 3, 3, "right_sleeve"),
/* 12 */   LEFT_PANTS_LEG("LEFT_PANTS_LEG", 4, 4, "left_pants_leg"),
/* 13 */   RIGHT_PANTS_LEG("RIGHT_PANTS_LEG", 5, 5, "right_pants_leg"),
/* 14 */   HAT("HAT", 6, 6, "hat");
/*    */   private final int field_179340_h;
/*    */   private final int field_179341_i;
/*    */   private final String field_179338_j;
/*    */   
/*    */   static {
/* 20 */     $VALUES = new EnumPlayerModelParts[] { CAPE, JACKET, LEFT_SLEEVE, RIGHT_SLEEVE, LEFT_PANTS_LEG, RIGHT_PANTS_LEG, HAT };
/*    */   }
/*    */   private final IChatComponent field_179339_k; private static final EnumPlayerModelParts[] $VALUES; private static final String __OBFID = "CL_00002187";
/*    */   
/*    */   EnumPlayerModelParts(String p_i45809_1_, int p_i45809_2_, int p_i45809_3_, String p_i45809_4_) {
/* 25 */     this.field_179340_h = p_i45809_3_;
/* 26 */     this.field_179341_i = 1 << p_i45809_3_;
/* 27 */     this.field_179338_j = p_i45809_4_;
/* 28 */     this.field_179339_k = (IChatComponent)new ChatComponentTranslation("options.modelPart." + p_i45809_4_, new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_179327_a() {
/* 33 */     return this.field_179341_i;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_179328_b() {
/* 38 */     return this.field_179340_h;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_179329_c() {
/* 43 */     return this.field_179338_j;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_179326_d() {
/* 48 */     return this.field_179339_k;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\player\EnumPlayerModelParts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */