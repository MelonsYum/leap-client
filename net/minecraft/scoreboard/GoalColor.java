/*    */ package net.minecraft.scoreboard;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ public class GoalColor
/*    */   implements IScoreObjectiveCriteria
/*    */ {
/*    */   private final String field_178794_j;
/*    */   private static final String __OBFID = "CL_00001961";
/*    */   
/*    */   public GoalColor(String p_i45549_1_, EnumChatFormatting p_i45549_2_) {
/* 13 */     this.field_178794_j = String.valueOf(p_i45549_1_) + p_i45549_2_.getFriendlyName();
/* 14 */     IScoreObjectiveCriteria.INSTANCES.put(this.field_178794_j, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 19 */     return this.field_178794_j;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_96635_a(List p_96635_1_) {
/* 24 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReadOnly() {
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreObjectiveCriteria.EnumRenderType func_178790_c() {
/* 34 */     return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\GoalColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */