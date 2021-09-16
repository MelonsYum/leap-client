/*     */ package optifine;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLeavesBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ public class ClearWater
/*     */ {
/*     */   public static void updateWaterOpacity(GameSettings settings, World world) {
/*  19 */     if (settings != null) {
/*     */       
/*  21 */       byte cp = 3;
/*     */       
/*  23 */       if (settings.ofClearWater)
/*     */       {
/*  25 */         cp = 1;
/*     */       }
/*     */       
/*  28 */       BlockLeavesBase.setLightOpacity((Block)Blocks.water, cp);
/*  29 */       BlockLeavesBase.setLightOpacity((Block)Blocks.flowing_water, cp);
/*     */     } 
/*     */     
/*  32 */     if (world != null) {
/*     */       
/*  34 */       IChunkProvider var25 = world.getChunkProvider();
/*     */       
/*  36 */       if (var25 != null) {
/*     */         
/*  38 */         Entity rve = Config.getMinecraft().func_175606_aa();
/*     */         
/*  40 */         if (rve != null) {
/*     */           
/*  42 */           int cViewX = (int)rve.posX / 16;
/*  43 */           int cViewZ = (int)rve.posZ / 16;
/*  44 */           int cXMin = cViewX - 512;
/*  45 */           int cXMax = cViewX + 512;
/*  46 */           int cZMin = cViewZ - 512;
/*  47 */           int cZMax = cViewZ + 512;
/*  48 */           int countUpdated = 0;
/*     */           
/*  50 */           for (int threadName = cXMin; threadName < cXMax; threadName++) {
/*     */             
/*  52 */             for (int cz = cZMin; cz < cZMax; cz++) {
/*     */               
/*  54 */               if (var25.chunkExists(threadName, cz)) {
/*     */                 
/*  56 */                 Chunk c = var25.provideChunk(threadName, cz);
/*     */                 
/*  58 */                 if (c != null && !(c instanceof net.minecraft.world.chunk.EmptyChunk)) {
/*     */                   
/*  60 */                   int x0 = threadName << 4;
/*  61 */                   int z0 = cz << 4;
/*  62 */                   int x1 = x0 + 16;
/*  63 */                   int z1 = z0 + 16;
/*  64 */                   BlockPosM posXZ = new BlockPosM(0, 0, 0);
/*  65 */                   BlockPosM posXYZ = new BlockPosM(0, 0, 0);
/*     */                   
/*  67 */                   for (int x = x0; x < x1; x++) {
/*     */                     
/*  69 */                     int z = z0;
/*     */                     
/*  71 */                     while (z < z1) {
/*     */                       
/*  73 */                       posXZ.setXyz(x, 0, z);
/*  74 */                       BlockPos posH = world.func_175725_q(posXZ);
/*  75 */                       int y = 0;
/*     */ 
/*     */ 
/*     */                       
/*  79 */                       while (y < posH.getY()) {
/*     */                         
/*  81 */                         posXYZ.setXyz(x, y, z);
/*  82 */                         IBlockState bs = world.getBlockState(posXYZ);
/*     */                         
/*  84 */                         if (bs.getBlock().getMaterial() != Material.water) {
/*     */                           
/*  86 */                           y++;
/*     */                           
/*     */                           continue;
/*     */                         } 
/*  90 */                         world.markBlocksDirtyVertical(x, z, posXYZ.getY(), posH.getY());
/*  91 */                         countUpdated++;
/*     */                       } 
/*     */                       
/*  94 */                       z++;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 104 */           if (countUpdated > 0) {
/*     */             
/* 106 */             String var26 = "server";
/*     */             
/* 108 */             if (Config.isMinecraftThread())
/*     */             {
/* 110 */               var26 = "client";
/*     */             }
/*     */             
/* 113 */             Config.dbg("ClearWater (" + var26 + ") relighted " + countUpdated + " chunks");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ClearWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */