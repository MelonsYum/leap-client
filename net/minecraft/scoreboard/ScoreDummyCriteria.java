/*    */ package net.minecraft.scoreboard;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class ScoreDummyCriteria
/*    */   implements IScoreObjectiveCriteria
/*    */ {
/*    */   private final String field_96644_g;
/*    */   private static final String __OBFID = "CL_00000622";
/*    */   
/*    */   public ScoreDummyCriteria(String p_i2311_1_) {
/* 12 */     this.field_96644_g = p_i2311_1_;
/* 13 */     IScoreObjectiveCriteria.INSTANCES.put(p_i2311_1_, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 18 */     return this.field_96644_g;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_96635_a(List p_96635_1_) {
/* 23 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReadOnly() {
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreObjectiveCriteria.EnumRenderType func_178790_c() {
/* 33 */     return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\ScoreDummyCriteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */