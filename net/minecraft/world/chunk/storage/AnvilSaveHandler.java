/*    */ package net.minecraft.world.chunk.storage;
/*    */ 
/*    */ import java.io.File;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraft.world.storage.SaveHandler;
/*    */ import net.minecraft.world.storage.ThreadedFileIOBase;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnvilSaveHandler
/*    */   extends SaveHandler
/*    */ {
/*    */   private static final String __OBFID = "CL_00000581";
/*    */   
/*    */   public AnvilSaveHandler(File p_i2142_1_, String p_i2142_2_, boolean p_i2142_3_) {
/* 18 */     super(p_i2142_1_, p_i2142_2_, p_i2142_3_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChunkLoader getChunkLoader(WorldProvider p_75763_1_) {
/* 26 */     File var2 = getWorldDirectory();
/*    */ 
/*    */     
/* 29 */     if (p_75763_1_ instanceof net.minecraft.world.WorldProviderHell) {
/*    */       
/* 31 */       File var3 = new File(var2, "DIM-1");
/* 32 */       var3.mkdirs();
/* 33 */       return new AnvilChunkLoader(var3);
/*    */     } 
/* 35 */     if (p_75763_1_ instanceof net.minecraft.world.WorldProviderEnd) {
/*    */       
/* 37 */       File var3 = new File(var2, "DIM1");
/* 38 */       var3.mkdirs();
/* 39 */       return new AnvilChunkLoader(var3);
/*    */     } 
/*    */ 
/*    */     
/* 43 */     return new AnvilChunkLoader(var2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveWorldInfoWithPlayer(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_) {
/* 52 */     p_75755_1_.setSaveVersion(19133);
/* 53 */     super.saveWorldInfoWithPlayer(p_75755_1_, p_75755_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() {
/*    */     try {
/* 63 */       ThreadedFileIOBase.func_178779_a().waitForFinish();
/*    */     }
/* 65 */     catch (InterruptedException var2) {
/*    */       
/* 67 */       var2.printStackTrace();
/*    */     } 
/*    */     
/* 70 */     RegionFileCache.clearRegionFileReferences();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\AnvilSaveHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */