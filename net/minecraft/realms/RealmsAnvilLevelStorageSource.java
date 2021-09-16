/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.AnvilConverterException;
/*    */ import net.minecraft.util.IProgressUpdate;
/*    */ import net.minecraft.world.storage.ISaveFormat;
/*    */ import net.minecraft.world.storage.SaveFormatComparator;
/*    */ 
/*    */ 
/*    */ public class RealmsAnvilLevelStorageSource
/*    */ {
/*    */   private ISaveFormat levelStorageSource;
/*    */   private static final String __OBFID = "CL_00001856";
/*    */   
/*    */   public RealmsAnvilLevelStorageSource(ISaveFormat p_i1106_1_) {
/* 19 */     this.levelStorageSource = p_i1106_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 24 */     return this.levelStorageSource.func_154333_a();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean levelExists(String p_levelExists_1_) {
/* 29 */     return this.levelStorageSource.canLoadWorld(p_levelExists_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean convertLevel(String p_convertLevel_1_, IProgressUpdate p_convertLevel_2_) {
/* 34 */     return this.levelStorageSource.convertMapFormat(p_convertLevel_1_, p_convertLevel_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean requiresConversion(String p_requiresConversion_1_) {
/* 39 */     return this.levelStorageSource.isOldMapFormat(p_requiresConversion_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isNewLevelIdAcceptable(String p_isNewLevelIdAcceptable_1_) {
/* 44 */     return this.levelStorageSource.func_154335_d(p_isNewLevelIdAcceptable_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean deleteLevel(String p_deleteLevel_1_) {
/* 49 */     return this.levelStorageSource.deleteWorldDirectory(p_deleteLevel_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConvertible(String p_isConvertible_1_) {
/* 54 */     return this.levelStorageSource.func_154334_a(p_isConvertible_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renameLevel(String p_renameLevel_1_, String p_renameLevel_2_) {
/* 59 */     this.levelStorageSource.renameWorld(p_renameLevel_1_, p_renameLevel_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void clearAll() {
/* 64 */     this.levelStorageSource.flushCache();
/*    */   }
/*    */ 
/*    */   
/*    */   public List getLevelList() throws AnvilConverterException {
/* 69 */     ArrayList<RealmsLevelSummary> var1 = Lists.newArrayList();
/* 70 */     Iterator<SaveFormatComparator> var2 = this.levelStorageSource.getSaveList().iterator();
/*    */     
/* 72 */     while (var2.hasNext()) {
/*    */       
/* 74 */       SaveFormatComparator var3 = var2.next();
/* 75 */       var1.add(new RealmsLevelSummary(var3));
/*    */     } 
/*    */     
/* 78 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsAnvilLevelStorageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */