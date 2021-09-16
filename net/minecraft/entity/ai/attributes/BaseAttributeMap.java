/*    */ package net.minecraft.entity.ai.attributes;
/*    */ 
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.management.LowerStringMap;
/*    */ 
/*    */ 
/*    */ public abstract class BaseAttributeMap
/*    */ {
/* 14 */   protected final Map attributes = Maps.newHashMap();
/* 15 */   protected final Map attributesByName = (Map)new LowerStringMap();
/* 16 */   protected final Multimap field_180377_c = (Multimap)HashMultimap.create();
/*    */   
/*    */   private static final String __OBFID = "CL_00001566";
/*    */   
/*    */   public IAttributeInstance getAttributeInstance(IAttribute p_111151_1_) {
/* 21 */     return (IAttributeInstance)this.attributes.get(p_111151_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public IAttributeInstance getAttributeInstanceByName(String p_111152_1_) {
/* 26 */     return (IAttributeInstance)this.attributesByName.get(p_111152_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IAttributeInstance registerAttribute(IAttribute p_111150_1_) {
/* 34 */     if (this.attributesByName.containsKey(p_111150_1_.getAttributeUnlocalizedName()))
/*    */     {
/* 36 */       throw new IllegalArgumentException("Attribute is already registered!");
/*    */     }
/*    */ 
/*    */     
/* 40 */     IAttributeInstance var2 = func_180376_c(p_111150_1_);
/* 41 */     this.attributesByName.put(p_111150_1_.getAttributeUnlocalizedName(), var2);
/* 42 */     this.attributes.put(p_111150_1_, var2);
/*    */     
/* 44 */     for (IAttribute var3 = p_111150_1_.func_180372_d(); var3 != null; var3 = var3.func_180372_d())
/*    */     {
/* 46 */       this.field_180377_c.put(var3, p_111150_1_);
/*    */     }
/*    */     
/* 49 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract IAttributeInstance func_180376_c(IAttribute paramIAttribute);
/*    */ 
/*    */   
/*    */   public Collection getAllAttributes() {
/* 57 */     return this.attributesByName.values();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180794_a(IAttributeInstance p_180794_1_) {}
/*    */   
/*    */   public void removeAttributeModifiers(Multimap p_111148_1_) {
/* 64 */     Iterator<Map.Entry> var2 = p_111148_1_.entries().iterator();
/*    */     
/* 66 */     while (var2.hasNext()) {
/*    */       
/* 68 */       Map.Entry var3 = var2.next();
/* 69 */       IAttributeInstance var4 = getAttributeInstanceByName((String)var3.getKey());
/*    */       
/* 71 */       if (var4 != null)
/*    */       {
/* 73 */         var4.removeModifier((AttributeModifier)var3.getValue());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyAttributeModifiers(Multimap p_111147_1_) {
/* 80 */     Iterator<Map.Entry> var2 = p_111147_1_.entries().iterator();
/*    */     
/* 82 */     while (var2.hasNext()) {
/*    */       
/* 84 */       Map.Entry var3 = var2.next();
/* 85 */       IAttributeInstance var4 = getAttributeInstanceByName((String)var3.getKey());
/*    */       
/* 87 */       if (var4 != null) {
/*    */         
/* 89 */         var4.removeModifier((AttributeModifier)var3.getValue());
/* 90 */         var4.applyModifier((AttributeModifier)var3.getValue());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\attributes\BaseAttributeMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */