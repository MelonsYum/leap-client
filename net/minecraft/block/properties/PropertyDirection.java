/*    */ package net.minecraft.block.properties;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.base.Predicates;
/*    */ import com.google.common.collect.Collections2;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class PropertyDirection
/*    */   extends PropertyEnum
/*    */ {
/*    */   private static final String __OBFID = "CL_00002016";
/*    */   
/*    */   protected PropertyDirection(String name, Collection values) {
/* 16 */     super(name, EnumFacing.class, values);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PropertyDirection create(String name) {
/* 24 */     return create(name, Predicates.alwaysTrue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PropertyDirection create(String name, Predicate filter) {
/* 32 */     return create(name, Collections2.filter(Lists.newArrayList((Object[])EnumFacing.values()), filter));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PropertyDirection create(String name, Collection values) {
/* 40 */     return new PropertyDirection(name, values);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\properties\PropertyDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */