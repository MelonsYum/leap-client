/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import net.minecraft.world.storage.SaveFormatComparator;
/*    */ 
/*    */ public class RealmsLevelSummary
/*    */   implements Comparable
/*    */ {
/*    */   private SaveFormatComparator levelSummary;
/*    */   private static final String __OBFID = "CL_00001857";
/*    */   
/*    */   public RealmsLevelSummary(SaveFormatComparator p_i1109_1_) {
/* 12 */     this.levelSummary = p_i1109_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGameMode() {
/* 17 */     return this.levelSummary.getEnumGameType().getID();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLevelId() {
/* 22 */     return this.levelSummary.getFileName();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasCheats() {
/* 27 */     return this.levelSummary.getCheatsEnabled();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHardcore() {
/* 32 */     return this.levelSummary.isHardcoreModeEnabled();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRequiresConversion() {
/* 37 */     return this.levelSummary.requiresConversion();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLevelName() {
/* 42 */     return this.levelSummary.getDisplayName();
/*    */   }
/*    */ 
/*    */   
/*    */   public long getLastPlayed() {
/* 47 */     return this.levelSummary.getLastTimePlayed();
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(SaveFormatComparator p_compareTo_1_) {
/* 52 */     return this.levelSummary.compareTo(p_compareTo_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSizeOnDisk() {
/* 57 */     return this.levelSummary.func_154336_c();
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(RealmsLevelSummary p_compareTo_1_) {
/* 62 */     return (this.levelSummary.getLastTimePlayed() < p_compareTo_1_.getLastPlayed()) ? 1 : ((this.levelSummary.getLastTimePlayed() > p_compareTo_1_.getLastPlayed()) ? -1 : this.levelSummary.getFileName().compareTo(p_compareTo_1_.getLevelId()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object p_compareTo_1_) {
/* 67 */     return compareTo((RealmsLevelSummary)p_compareTo_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsLevelSummary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */