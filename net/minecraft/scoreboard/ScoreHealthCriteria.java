/*    */ package net.minecraft.scoreboard;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ScoreHealthCriteria
/*    */   extends ScoreDummyCriteria
/*    */ {
/*    */   private static final String __OBFID = "CL_00000623";
/*    */   
/*    */   public ScoreHealthCriteria(String p_i2312_1_) {
/* 14 */     super(p_i2312_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_96635_a(List p_96635_1_) {
/* 19 */     float var2 = 0.0F;
/*    */ 
/*    */     
/* 22 */     for (Iterator<EntityPlayer> var3 = p_96635_1_.iterator(); var3.hasNext(); var2 += var4.getHealth() + var4.getAbsorptionAmount())
/*    */     {
/* 24 */       EntityPlayer var4 = var3.next();
/*    */     }
/*    */     
/* 27 */     if (p_96635_1_.size() > 0)
/*    */     {
/* 29 */       var2 /= p_96635_1_.size();
/*    */     }
/*    */     
/* 32 */     return MathHelper.ceiling_float_int(var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReadOnly() {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreObjectiveCriteria.EnumRenderType func_178790_c() {
/* 42 */     return IScoreObjectiveCriteria.EnumRenderType.HEARTS;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\ScoreHealthCriteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */