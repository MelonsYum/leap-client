/*    */ package net.minecraft.block.properties;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ 
/*    */ public abstract class PropertyHelper
/*    */   implements IProperty
/*    */ {
/*    */   private final Class valueClass;
/*    */   private final String name;
/*    */   private static final String __OBFID = "CL_00002018";
/*    */   
/*    */   protected PropertyHelper(String name, Class valueClass) {
/* 13 */     this.valueClass = valueClass;
/* 14 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 19 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class getValueClass() {
/* 27 */     return this.valueClass;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     return Objects.toStringHelper(this).add("name", this.name).add("clazz", this.valueClass).add("values", getAllowedValues()).toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 37 */     if (this == p_equals_1_)
/*    */     {
/* 39 */       return true;
/*    */     }
/* 41 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*    */       
/* 43 */       PropertyHelper var2 = (PropertyHelper)p_equals_1_;
/* 44 */       return (this.valueClass.equals(var2.valueClass) && this.name.equals(var2.name));
/*    */     } 
/*    */ 
/*    */     
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 54 */     return 31 * this.valueClass.hashCode() + this.name.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\properties\PropertyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */