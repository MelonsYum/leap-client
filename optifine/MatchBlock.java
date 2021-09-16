/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.block.state.BlockStateBase;
/*    */ 
/*    */ public class MatchBlock
/*    */ {
/*  7 */   private int blockId = -1;
/*  8 */   private int[] metadatas = null;
/*    */ 
/*    */   
/*    */   public MatchBlock(int blockId) {
/* 12 */     this.blockId = blockId;
/*    */   }
/*    */ 
/*    */   
/*    */   public MatchBlock(int blockId, int metadata) {
/* 17 */     this.blockId = blockId;
/*    */     
/* 19 */     if (metadata >= 0 && metadata <= 15)
/*    */     {
/* 21 */       this.metadatas = new int[] { metadata };
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public MatchBlock(int blockId, int[] metadatas) {
/* 27 */     this.blockId = blockId;
/* 28 */     this.metadatas = metadatas;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockId() {
/* 33 */     return this.blockId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] getMetadatas() {
/* 38 */     return this.metadatas;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(BlockStateBase blockState) {
/* 43 */     return (blockState.getBlockId() != this.blockId) ? false : Matches.metadata(blockState.getMetadata(), this.metadatas);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(int id, int metadata) {
/* 48 */     return (id != this.blockId) ? false : Matches.metadata(metadata, this.metadatas);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addMetadata(int metadata) {
/* 53 */     if (this.metadatas != null)
/*    */     {
/* 55 */       if (metadata >= 0 && metadata <= 15) {
/*    */         
/* 57 */         for (int i = 0; i < this.metadatas.length; i++) {
/*    */           
/* 59 */           if (this.metadatas[i] == metadata) {
/*    */             return;
/*    */           }
/*    */         } 
/*    */ 
/*    */         
/* 65 */         this.metadatas = Config.addIntToArray(this.metadatas, metadata);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     return this.blockId + ":" + Config.arrayToString(this.metadatas);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\MatchBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */