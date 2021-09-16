/*     */ package net.minecraft.world.chunk.storage;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.chunk.NibbleArray;
/*     */ import optifine.Reflector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtendedBlockStorage
/*     */ {
/*     */   private int yBase;
/*     */   private int blockRefCount;
/*     */   private int tickRefCount;
/*     */   private char[] data;
/*     */   private NibbleArray blocklightArray;
/*     */   private NibbleArray skylightArray;
/*     */   private static final String __OBFID = "CL_00000375";
/*     */   
/*     */   public ExtendedBlockStorage(int y, boolean storeSkylight) {
/*  38 */     this.yBase = y;
/*  39 */     this.data = new char[4096];
/*  40 */     this.blocklightArray = new NibbleArray();
/*     */     
/*  42 */     if (storeSkylight)
/*     */     {
/*  44 */       this.skylightArray = new NibbleArray();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState get(int x, int y, int z) {
/*  50 */     IBlockState var4 = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(this.data[y << 8 | z << 4 | x]);
/*  51 */     return (var4 != null) ? var4 : Blocks.air.getDefaultState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(int x, int y, int z, IBlockState state) {
/*  56 */     if (Reflector.IExtendedBlockState.isInstance(state))
/*     */     {
/*  58 */       state = (IBlockState)Reflector.call(state, Reflector.IExtendedBlockState_getClean, new Object[0]);
/*     */     }
/*     */     
/*  61 */     IBlockState var5 = get(x, y, z);
/*  62 */     Block var6 = var5.getBlock();
/*  63 */     Block var7 = state.getBlock();
/*     */     
/*  65 */     if (var6 != Blocks.air) {
/*     */       
/*  67 */       this.blockRefCount--;
/*     */       
/*  69 */       if (var6.getTickRandomly())
/*     */       {
/*  71 */         this.tickRefCount--;
/*     */       }
/*     */     } 
/*     */     
/*  75 */     if (var7 != Blocks.air) {
/*     */       
/*  77 */       this.blockRefCount++;
/*     */       
/*  79 */       if (var7.getTickRandomly())
/*     */       {
/*  81 */         this.tickRefCount++;
/*     */       }
/*     */     } 
/*     */     
/*  85 */     this.data[y << 8 | z << 4 | x] = (char)Block.BLOCK_STATE_IDS.get(state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Block getBlockByExtId(int x, int y, int z) {
/*  94 */     return get(x, y, z).getBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtBlockMetadata(int x, int y, int z) {
/* 102 */     IBlockState var4 = get(x, y, z);
/* 103 */     return var4.getBlock().getMetaFromState(var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 111 */     return (this.blockRefCount == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNeedsRandomTick() {
/* 120 */     return (this.tickRefCount > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYLocation() {
/* 128 */     return this.yBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtSkylightValue(int x, int y, int z, int value) {
/* 136 */     this.skylightArray.set(x, y, z, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtSkylightValue(int x, int y, int z) {
/* 144 */     return this.skylightArray.get(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtBlocklightValue(int x, int y, int z, int value) {
/* 152 */     this.blocklightArray.set(x, y, z, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtBlocklightValue(int x, int y, int z) {
/* 160 */     return this.blocklightArray.get(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeInvalidBlocks() {
/* 165 */     List<IBlockState> blockStates = Block.BLOCK_STATE_IDS.getObjectList();
/* 166 */     int maxStateId = blockStates.size();
/* 167 */     int localBlockRefCount = 0;
/* 168 */     int localTickRefCount = 0;
/*     */     
/* 170 */     for (int y = 0; y < 16; y++) {
/*     */       
/* 172 */       int by = y << 8;
/*     */       
/* 174 */       for (int z = 0; z < 16; z++) {
/*     */         
/* 176 */         int byz = by | z << 4;
/*     */         
/* 178 */         for (int x = 0; x < 16; x++) {
/*     */           
/* 180 */           char stateId = this.data[byz | x];
/*     */           
/* 182 */           if (stateId > '\000') {
/*     */             
/* 184 */             localBlockRefCount++;
/*     */             
/* 186 */             if (stateId < maxStateId) {
/*     */               
/* 188 */               IBlockState bs = blockStates.get(stateId);
/*     */               
/* 190 */               if (bs != null) {
/*     */                 
/* 192 */                 Block var4 = bs.getBlock();
/*     */                 
/* 194 */                 if (var4.getTickRandomly())
/*     */                 {
/* 196 */                   localTickRefCount++;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 205 */     this.blockRefCount = localBlockRefCount;
/* 206 */     this.tickRefCount = localTickRefCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public char[] getData() {
/* 211 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setData(char[] dataArray) {
/* 216 */     this.data = dataArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NibbleArray getBlocklightArray() {
/* 224 */     return this.blocklightArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NibbleArray getSkylightArray() {
/* 232 */     return this.skylightArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlocklightArray(NibbleArray newBlocklightArray) {
/* 240 */     this.blocklightArray = newBlocklightArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSkylightArray(NibbleArray newSkylightArray) {
/* 248 */     this.skylightArray = newSkylightArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\ExtendedBlockStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */