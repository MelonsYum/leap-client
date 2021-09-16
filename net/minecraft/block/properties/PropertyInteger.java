/*    */ package net.minecraft.block.properties;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class PropertyInteger
/*    */   extends PropertyHelper
/*    */ {
/*    */   private final ImmutableSet allowedValues;
/*    */   private static final String __OBFID = "CL_00002014";
/*    */   
/*    */   protected PropertyInteger(String name, int min, int max) {
/* 15 */     super(name, Integer.class);
/*    */     
/* 17 */     if (min < 0)
/*    */     {
/* 19 */       throw new IllegalArgumentException("Min value of " + name + " must be 0 or greater");
/*    */     }
/* 21 */     if (max <= min)
/*    */     {
/* 23 */       throw new IllegalArgumentException("Max value of " + name + " must be greater than min (" + min + ")");
/*    */     }
/*    */ 
/*    */     
/* 27 */     HashSet<Integer> var4 = Sets.newHashSet();
/*    */     
/* 29 */     for (int var5 = min; var5 <= max; var5++)
/*    */     {
/* 31 */       var4.add(Integer.valueOf(var5));
/*    */     }
/*    */     
/* 34 */     this.allowedValues = ImmutableSet.copyOf(var4);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection getAllowedValues() {
/* 40 */     return (Collection)this.allowedValues;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 45 */     if (this == p_equals_1_)
/*    */     {
/* 47 */       return true;
/*    */     }
/* 49 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*    */       
/* 51 */       if (!super.equals(p_equals_1_))
/*    */       {
/* 53 */         return false;
/*    */       }
/*    */ 
/*    */       
/* 57 */       PropertyInteger var2 = (PropertyInteger)p_equals_1_;
/* 58 */       return this.allowedValues.equals(var2.allowedValues);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 69 */     int var1 = super.hashCode();
/* 70 */     var1 = 31 * var1 + this.allowedValues.hashCode();
/* 71 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PropertyInteger create(String name, int min, int max) {
/* 76 */     return new PropertyInteger(name, min, max);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName0(Integer value) {
/* 81 */     return value.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName(Comparable value) {
/* 89 */     return getName0((Integer)value);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\properties\PropertyInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */