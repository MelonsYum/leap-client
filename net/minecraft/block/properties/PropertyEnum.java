/*    */ package net.minecraft.block.properties;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.base.Predicates;
/*    */ import com.google.common.collect.Collections2;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.IStringSerializable;
/*    */ 
/*    */ 
/*    */ public class PropertyEnum
/*    */   extends PropertyHelper
/*    */ {
/*    */   private final ImmutableSet allowedValues;
/* 19 */   private final Map nameToValue = Maps.newHashMap();
/*    */   
/*    */   private static final String __OBFID = "CL_00002015";
/*    */   
/*    */   protected PropertyEnum(String name, Class valueClass, Collection allowedValues) {
/* 24 */     super(name, valueClass);
/* 25 */     this.allowedValues = ImmutableSet.copyOf(allowedValues);
/* 26 */     Iterator<Enum> var4 = allowedValues.iterator();
/*    */     
/* 28 */     while (var4.hasNext()) {
/*    */       
/* 30 */       Enum var5 = var4.next();
/* 31 */       String var6 = ((IStringSerializable)var5).getName();
/*    */       
/* 33 */       if (this.nameToValue.containsKey(var6))
/*    */       {
/* 35 */         throw new IllegalArgumentException("Multiple values have the same name '" + var6 + "'");
/*    */       }
/*    */       
/* 38 */       this.nameToValue.put(var6, var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection getAllowedValues() {
/* 44 */     return (Collection)this.allowedValues;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName(Enum value) {
/* 49 */     return ((IStringSerializable)value).getName();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PropertyEnum create(String name, Class clazz) {
/* 57 */     return create(name, clazz, Predicates.alwaysTrue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PropertyEnum create(String name, Class<Object> clazz, Predicate filter) {
/* 65 */     return create(name, clazz, Collections2.filter(Lists.newArrayList(clazz.getEnumConstants()), filter));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PropertyEnum create(String name, Class clazz, Enum... values) {
/* 73 */     return create(name, clazz, Lists.newArrayList((Object[])values));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PropertyEnum create(String name, Class clazz, Collection values) {
/* 81 */     return new PropertyEnum(name, clazz, values);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName(Comparable value) {
/* 89 */     return getName((Enum)value);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\properties\PropertyEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */