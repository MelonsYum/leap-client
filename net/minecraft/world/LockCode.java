/*    */ package net.minecraft.world;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class LockCode
/*    */ {
/*  7 */   public static final LockCode EMPTY_CODE = new LockCode("");
/*    */   
/*    */   private final String lock;
/*    */   private static final String __OBFID = "CL_00002260";
/*    */   
/*    */   public LockCode(String p_i45903_1_) {
/* 13 */     this.lock = p_i45903_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 18 */     return !(this.lock != null && !this.lock.isEmpty());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLock() {
/* 23 */     return this.lock;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toNBT(NBTTagCompound nbt) {
/* 28 */     nbt.setString("Lock", this.lock);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LockCode fromNBT(NBTTagCompound nbt) {
/* 33 */     if (nbt.hasKey("Lock", 8)) {
/*    */       
/* 35 */       String var1 = nbt.getString("Lock");
/* 36 */       return new LockCode(var1);
/*    */     } 
/*    */ 
/*    */     
/* 40 */     return EMPTY_CODE;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\LockCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */