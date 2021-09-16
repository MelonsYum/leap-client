/*    */ package net.minecraft.stats;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class StatCrafting
/*    */   extends StatBase
/*    */ {
/*    */   private final Item field_150960_a;
/*    */   private static final String __OBFID = "CL_00001470";
/*    */   
/*    */   public StatCrafting(String p_i45910_1_, String p_i45910_2_, IChatComponent p_i45910_3_, Item p_i45910_4_) {
/* 14 */     super(String.valueOf(p_i45910_1_) + p_i45910_2_, p_i45910_3_);
/* 15 */     this.field_150960_a = p_i45910_4_;
/* 16 */     int var5 = Item.getIdFromItem(p_i45910_4_);
/*    */     
/* 18 */     if (var5 != 0)
/*    */     {
/* 20 */       IScoreObjectiveCriteria.INSTANCES.put(String.valueOf(p_i45910_1_) + var5, func_150952_k());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Item func_150959_a() {
/* 26 */     return this.field_150960_a;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\StatCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */