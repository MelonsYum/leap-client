/*    */ package net.minecraft.scoreboard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScoreObjective
/*    */ {
/*    */   private final Scoreboard theScoreboard;
/*    */   private final String name;
/*    */   private final IScoreObjectiveCriteria objectiveCriteria;
/*    */   private IScoreObjectiveCriteria.EnumRenderType field_178768_d;
/*    */   private String displayName;
/*    */   private static final String __OBFID = "CL_00000614";
/*    */   
/*    */   public ScoreObjective(Scoreboard p_i2307_1_, String p_i2307_2_, IScoreObjectiveCriteria p_i2307_3_) {
/* 16 */     this.theScoreboard = p_i2307_1_;
/* 17 */     this.name = p_i2307_2_;
/* 18 */     this.objectiveCriteria = p_i2307_3_;
/* 19 */     this.displayName = p_i2307_2_;
/* 20 */     this.field_178768_d = p_i2307_3_.func_178790_c();
/*    */   }
/*    */ 
/*    */   
/*    */   public Scoreboard getScoreboard() {
/* 25 */     return this.theScoreboard;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreObjectiveCriteria getCriteria() {
/* 35 */     return this.objectiveCriteria;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 40 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDisplayName(String p_96681_1_) {
/* 45 */     this.displayName = p_96681_1_;
/* 46 */     this.theScoreboard.func_96532_b(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreObjectiveCriteria.EnumRenderType func_178766_e() {
/* 51 */     return this.field_178768_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178767_a(IScoreObjectiveCriteria.EnumRenderType p_178767_1_) {
/* 56 */     this.field_178768_d = p_178767_1_;
/* 57 */     this.theScoreboard.func_96532_b(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\ScoreObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */