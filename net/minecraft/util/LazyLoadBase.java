/*    */ package net.minecraft.util;
/*    */ 
/*    */ 
/*    */ public abstract class LazyLoadBase
/*    */ {
/*    */   private Object value;
/*    */   private boolean isLoaded = false;
/*    */   private static final String __OBFID = "CL_00002263";
/*    */   
/*    */   public Object getValue() {
/* 11 */     if (!this.isLoaded) {
/*    */       
/* 13 */       this.isLoaded = true;
/* 14 */       this.value = load();
/*    */     } 
/*    */     
/* 17 */     return this.value;
/*    */   }
/*    */   
/*    */   protected abstract Object load();
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\LazyLoadBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */