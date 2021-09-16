/*    */ package net.minecraft.world.chunk.storage;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class RegionFileCache
/*    */ {
/* 14 */   private static final Map regionsByFilename = Maps.newHashMap();
/*    */   
/*    */   private static final String __OBFID = "CL_00000383";
/*    */   
/*    */   public static synchronized RegionFile createOrLoadRegionFile(File worldDir, int chunkX, int chunkZ) {
/* 19 */     File var3 = new File(worldDir, "region");
/* 20 */     File var4 = new File(var3, "r." + (chunkX >> 5) + "." + (chunkZ >> 5) + ".mca");
/* 21 */     RegionFile var5 = (RegionFile)regionsByFilename.get(var4);
/*    */     
/* 23 */     if (var5 != null)
/*    */     {
/* 25 */       return var5;
/*    */     }
/*    */ 
/*    */     
/* 29 */     if (!var3.exists())
/*    */     {
/* 31 */       var3.mkdirs();
/*    */     }
/*    */     
/* 34 */     if (regionsByFilename.size() >= 256)
/*    */     {
/* 36 */       clearRegionFileReferences();
/*    */     }
/*    */     
/* 39 */     RegionFile var6 = new RegionFile(var4);
/* 40 */     regionsByFilename.put(var4, var6);
/* 41 */     return var6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void clearRegionFileReferences() {
/* 50 */     Iterator<RegionFile> var0 = regionsByFilename.values().iterator();
/*    */     
/* 52 */     while (var0.hasNext()) {
/*    */       
/* 54 */       RegionFile var1 = var0.next();
/*    */ 
/*    */       
/*    */       try {
/* 58 */         if (var1 != null)
/*    */         {
/* 60 */           var1.close();
/*    */         }
/*    */       }
/* 63 */       catch (IOException var3) {
/*    */         
/* 65 */         var3.printStackTrace();
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     regionsByFilename.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static DataInputStream getChunkInputStream(File worldDir, int chunkX, int chunkZ) {
/* 77 */     RegionFile var3 = createOrLoadRegionFile(worldDir, chunkX, chunkZ);
/* 78 */     return var3.getChunkDataInputStream(chunkX & 0x1F, chunkZ & 0x1F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static DataOutputStream getChunkOutputStream(File worldDir, int chunkX, int chunkZ) {
/* 86 */     RegionFile var3 = createOrLoadRegionFile(worldDir, chunkX, chunkZ);
/* 87 */     return var3.getChunkDataOutputStream(chunkX & 0x1F, chunkZ & 0x1F);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\RegionFileCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */