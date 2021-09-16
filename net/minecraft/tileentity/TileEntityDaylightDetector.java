/*    */ package net.minecraft.tileentity;
/*    */ 
/*    */ import net.minecraft.block.BlockDaylightDetector;
/*    */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityDaylightDetector
/*    */   extends TileEntity
/*    */   implements IUpdatePlayerListBox
/*    */ {
/*    */   private static final String __OBFID = "CL_00000350";
/*    */   
/*    */   public void update() {
/* 15 */     if (this.worldObj != null && !this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 20L == 0L) {
/*    */       
/* 17 */       this.blockType = getBlockType();
/*    */       
/* 19 */       if (this.blockType instanceof BlockDaylightDetector)
/*    */       {
/* 21 */         ((BlockDaylightDetector)this.blockType).func_180677_d(this.worldObj, this.pos);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */