/*    */ package net.minecraft.tileentity;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class TileEntityComparator
/*    */   extends TileEntity
/*    */ {
/*    */   private int outputSignal;
/*    */   private static final String __OBFID = "CL_00000349";
/*    */   
/*    */   public void writeToNBT(NBTTagCompound compound) {
/* 12 */     super.writeToNBT(compound);
/* 13 */     compound.setInteger("OutputSignal", this.outputSignal);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound compound) {
/* 18 */     super.readFromNBT(compound);
/* 19 */     this.outputSignal = compound.getInteger("OutputSignal");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOutputSignal() {
/* 24 */     return this.outputSignal;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOutputSignal(int p_145995_1_) {
/* 29 */     this.outputSignal = p_145995_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */