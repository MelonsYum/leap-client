/*    */ package net.minecraft.world.storage;
/*    */ 
/*    */ import net.minecraft.world.WorldSettings;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaveFormatComparator
/*    */   implements Comparable
/*    */ {
/*    */   private final String fileName;
/*    */   private final String displayName;
/*    */   private final long lastTimePlayed;
/*    */   private final long sizeOnDisk;
/*    */   private final boolean requiresConversion;
/*    */   private final WorldSettings.GameType theEnumGameType;
/*    */   private final boolean hardcore;
/*    */   private final boolean cheatsEnabled;
/*    */   private static final String __OBFID = "CL_00000601";
/*    */   
/*    */   public SaveFormatComparator(String p_i2161_1_, String p_i2161_2_, long p_i2161_3_, long p_i2161_5_, WorldSettings.GameType p_i2161_7_, boolean p_i2161_8_, boolean p_i2161_9_, boolean p_i2161_10_) {
/* 24 */     this.fileName = p_i2161_1_;
/* 25 */     this.displayName = p_i2161_2_;
/* 26 */     this.lastTimePlayed = p_i2161_3_;
/* 27 */     this.sizeOnDisk = p_i2161_5_;
/* 28 */     this.theEnumGameType = p_i2161_7_;
/* 29 */     this.requiresConversion = p_i2161_8_;
/* 30 */     this.hardcore = p_i2161_9_;
/* 31 */     this.cheatsEnabled = p_i2161_10_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFileName() {
/* 39 */     return this.fileName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 47 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   
/*    */   public long func_154336_c() {
/* 52 */     return this.sizeOnDisk;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean requiresConversion() {
/* 57 */     return this.requiresConversion;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getLastTimePlayed() {
/* 62 */     return this.lastTimePlayed;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(SaveFormatComparator p_compareTo_1_) {
/* 67 */     return (this.lastTimePlayed < p_compareTo_1_.lastTimePlayed) ? 1 : ((this.lastTimePlayed > p_compareTo_1_.lastTimePlayed) ? -1 : this.fileName.compareTo(p_compareTo_1_.fileName));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldSettings.GameType getEnumGameType() {
/* 75 */     return this.theEnumGameType;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHardcoreModeEnabled() {
/* 80 */     return this.hardcore;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getCheatsEnabled() {
/* 88 */     return this.cheatsEnabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object p_compareTo_1_) {
/* 93 */     return compareTo((SaveFormatComparator)p_compareTo_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\SaveFormatComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */