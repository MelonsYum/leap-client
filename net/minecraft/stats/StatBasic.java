/*    */ package net.minecraft.stats;
/*    */ 
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class StatBasic
/*    */   extends StatBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00001469";
/*    */   
/*    */   public StatBasic(String p_i45303_1_, IChatComponent p_i45303_2_, IStatType p_i45303_3_) {
/* 11 */     super(p_i45303_1_, p_i45303_2_, p_i45303_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public StatBasic(String p_i45304_1_, IChatComponent p_i45304_2_) {
/* 16 */     super(p_i45304_1_, p_i45304_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StatBase registerStat() {
/* 24 */     super.registerStat();
/* 25 */     StatList.generalStats.add(this);
/* 26 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\StatBasic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */