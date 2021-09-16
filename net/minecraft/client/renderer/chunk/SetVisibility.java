/*    */ package net.minecraft.client.renderer.chunk;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class SetVisibility
/*    */ {
/* 10 */   private static final int field_178623_a = (EnumFacing.values()).length;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   private final BitSet field_178622_b = new BitSet(field_178623_a * field_178623_a);
/*    */   
/*    */   private static final String __OBFID = "CL_00002448";
/*    */   
/*    */   public void func_178620_a(Set p_178620_1_) {
/* 21 */     Iterator<EnumFacing> var2 = p_178620_1_.iterator();
/*    */     
/* 23 */     while (var2.hasNext()) {
/*    */       
/* 25 */       EnumFacing var3 = var2.next();
/* 26 */       Iterator<EnumFacing> var4 = p_178620_1_.iterator();
/*    */       
/* 28 */       while (var4.hasNext()) {
/*    */         
/* 30 */         EnumFacing var5 = var4.next();
/* 31 */         func_178619_a(var3, var5, true);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178619_a(EnumFacing p_178619_1_, EnumFacing p_178619_2_, boolean p_178619_3_) {
/* 38 */     this.field_178622_b.set(p_178619_1_.ordinal() + p_178619_2_.ordinal() * field_178623_a, p_178619_3_);
/* 39 */     this.field_178622_b.set(p_178619_2_.ordinal() + p_178619_1_.ordinal() * field_178623_a, p_178619_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178618_a(boolean p_178618_1_) {
/* 44 */     this.field_178622_b.set(0, this.field_178622_b.size(), p_178618_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_178621_a(EnumFacing p_178621_1_, EnumFacing p_178621_2_) {
/* 49 */     return this.field_178622_b.get(p_178621_1_.ordinal() + p_178621_2_.ordinal() * field_178623_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     StringBuilder var1 = new StringBuilder();
/* 55 */     var1.append(' ');
/* 56 */     EnumFacing[] var2 = EnumFacing.values();
/* 57 */     int var3 = var2.length;
/*    */     
/*    */     int var4;
/*    */     
/* 61 */     for (var4 = 0; var4 < var3; var4++) {
/*    */       
/* 63 */       EnumFacing var5 = var2[var4];
/* 64 */       var1.append(' ').append(var5.toString().toUpperCase().charAt(0));
/*    */     } 
/*    */     
/* 67 */     var1.append('\n');
/* 68 */     var2 = EnumFacing.values();
/* 69 */     var3 = var2.length;
/*    */     
/* 71 */     for (var4 = 0; var4 < var3; var4++) {
/*    */       
/* 73 */       EnumFacing var5 = var2[var4];
/* 74 */       var1.append(var5.toString().toUpperCase().charAt(0));
/* 75 */       EnumFacing[] var6 = EnumFacing.values();
/* 76 */       int var7 = var6.length;
/*    */       
/* 78 */       for (int var8 = 0; var8 < var7; var8++) {
/*    */         
/* 80 */         EnumFacing var9 = var6[var8];
/*    */         
/* 82 */         if (var5 == var9) {
/*    */           
/* 84 */           var1.append("  ");
/*    */         }
/*    */         else {
/*    */           
/* 88 */           boolean var10 = func_178621_a(var5, var9);
/* 89 */           var1.append(' ').append(var10 ? 89 : 110);
/*    */         } 
/*    */       } 
/*    */       
/* 93 */       var1.append('\n');
/*    */     } 
/*    */     
/* 96 */     return var1.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\chunk\SetVisibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */