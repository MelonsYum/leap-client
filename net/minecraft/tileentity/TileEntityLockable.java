/*    */ package net.minecraft.tileentity;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.IInteractionObject;
/*    */ import net.minecraft.world.ILockableContainer;
/*    */ import net.minecraft.world.LockCode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TileEntityLockable
/*    */   extends TileEntity
/*    */   implements IInteractionObject, ILockableContainer
/*    */ {
/* 18 */   private LockCode code = LockCode.EMPTY_CODE;
/*    */   
/*    */   private static final String __OBFID = "CL_00002040";
/*    */   
/*    */   public void readFromNBT(NBTTagCompound compound) {
/* 23 */     super.readFromNBT(compound);
/* 24 */     this.code = LockCode.fromNBT(compound);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound compound) {
/* 29 */     super.writeToNBT(compound);
/*    */     
/* 31 */     if (this.code != null)
/*    */     {
/* 33 */       this.code.toNBT(compound);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocked() {
/* 39 */     return (this.code != null && !this.code.isEmpty());
/*    */   }
/*    */ 
/*    */   
/*    */   public LockCode getLockCode() {
/* 44 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLockCode(LockCode code) {
/* 49 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent getDisplayName() {
/* 54 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityLockable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */