/*    */ package net.minecraft.block.properties;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class PropertyBool
/*    */   extends PropertyHelper {
/*  8 */   private final ImmutableSet allowedValues = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));
/*    */   
/*    */   private static final String __OBFID = "CL_00002017";
/*    */   
/*    */   protected PropertyBool(String name) {
/* 13 */     super(name, Boolean.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection getAllowedValues() {
/* 18 */     return (Collection)this.allowedValues;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PropertyBool create(String name) {
/* 23 */     return new PropertyBool(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName0(Boolean value) {
/* 31 */     return value.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName(Comparable value) {
/* 39 */     return getName0((Boolean)value);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\properties\PropertyBool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */