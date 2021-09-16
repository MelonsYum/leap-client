/*    */ package net.minecraft.world.storage;
/*    */ 
/*    */ import net.minecraft.world.WorldSavedData;
/*    */ 
/*    */ public class SaveDataMemoryStorage
/*    */   extends MapStorage
/*    */ {
/*    */   private static final String __OBFID = "CL_00001963";
/*    */   
/*    */   public SaveDataMemoryStorage() {
/* 11 */     super(null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldSavedData loadData(Class p_75742_1_, String p_75742_2_) {
/* 20 */     return (WorldSavedData)this.loadedDataMap.get(p_75742_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setData(String p_75745_1_, WorldSavedData p_75745_2_) {
/* 28 */     this.loadedDataMap.put(p_75745_1_, p_75745_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveAllData() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getUniqueDataId(String p_75743_1_) {
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\SaveDataMemoryStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */