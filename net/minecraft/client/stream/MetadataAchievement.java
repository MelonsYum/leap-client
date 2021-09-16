/*    */ package net.minecraft.client.stream;
/*    */ 
/*    */ import net.minecraft.stats.Achievement;
/*    */ 
/*    */ public class MetadataAchievement
/*    */   extends Metadata
/*    */ {
/*    */   private static final String __OBFID = "CL_00001824";
/*    */   
/*    */   public MetadataAchievement(Achievement p_i1032_1_) {
/* 11 */     super("achievement");
/* 12 */     func_152808_a("achievement_id", p_i1032_1_.statId);
/* 13 */     func_152808_a("achievement_name", p_i1032_1_.getStatName().getUnformattedText());
/* 14 */     func_152808_a("achievement_description", p_i1032_1_.getDescription());
/* 15 */     func_152807_a("Achievement '" + p_i1032_1_.getStatName().getUnformattedText() + "' obtained!");
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\MetadataAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */