/*     */ package net.minecraft.entity.ai.attributes;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModifiableAttributeInstance
/*     */   implements IAttributeInstance
/*     */ {
/*     */   private final BaseAttributeMap attributeMap;
/*     */   private final IAttribute genericAttribute;
/*  21 */   private final Map mapByOperation = Maps.newHashMap();
/*  22 */   private final Map mapByName = Maps.newHashMap();
/*  23 */   private final Map mapByUUID = Maps.newHashMap();
/*     */   
/*     */   private double baseValue;
/*     */   private boolean needsUpdate = true;
/*     */   private double cachedValue;
/*     */   private static final String __OBFID = "CL_00001567";
/*     */   
/*     */   public ModifiableAttributeInstance(BaseAttributeMap p_i1608_1_, IAttribute p_i1608_2_) {
/*  31 */     this.attributeMap = p_i1608_1_;
/*  32 */     this.genericAttribute = p_i1608_2_;
/*  33 */     this.baseValue = p_i1608_2_.getDefaultValue();
/*     */     
/*  35 */     for (int var3 = 0; var3 < 3; var3++)
/*     */     {
/*  37 */       this.mapByOperation.put(Integer.valueOf(var3), Sets.newHashSet());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IAttribute getAttribute() {
/*  46 */     return this.genericAttribute;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBaseValue() {
/*  51 */     return this.baseValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseValue(double p_111128_1_) {
/*  56 */     if (p_111128_1_ != getBaseValue()) {
/*     */       
/*  58 */       this.baseValue = p_111128_1_;
/*  59 */       flagForUpdate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection getModifiersByOperation(int p_111130_1_) {
/*  65 */     return (Collection)this.mapByOperation.get(Integer.valueOf(p_111130_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection func_111122_c() {
/*  70 */     HashSet var1 = Sets.newHashSet();
/*     */     
/*  72 */     for (int var2 = 0; var2 < 3; var2++)
/*     */     {
/*  74 */       var1.addAll(getModifiersByOperation(var2));
/*     */     }
/*     */     
/*  77 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeModifier getModifier(UUID p_111127_1_) {
/*  85 */     return (AttributeModifier)this.mapByUUID.get(p_111127_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180374_a(AttributeModifier p_180374_1_) {
/*  90 */     return (this.mapByUUID.get(p_180374_1_.getID()) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyModifier(AttributeModifier p_111121_1_) {
/*  95 */     if (getModifier(p_111121_1_.getID()) != null)
/*     */     {
/*  97 */       throw new IllegalArgumentException("Modifier is already applied on this attribute!");
/*     */     }
/*     */ 
/*     */     
/* 101 */     Object var2 = this.mapByName.get(p_111121_1_.getName());
/*     */     
/* 103 */     if (var2 == null) {
/*     */       
/* 105 */       var2 = Sets.newHashSet();
/* 106 */       this.mapByName.put(p_111121_1_.getName(), var2);
/*     */     } 
/*     */     
/* 109 */     ((Set<AttributeModifier>)this.mapByOperation.get(Integer.valueOf(p_111121_1_.getOperation()))).add(p_111121_1_);
/* 110 */     ((Set<AttributeModifier>)var2).add(p_111121_1_);
/* 111 */     this.mapByUUID.put(p_111121_1_.getID(), p_111121_1_);
/* 112 */     flagForUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void flagForUpdate() {
/* 118 */     this.needsUpdate = true;
/* 119 */     this.attributeMap.func_180794_a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeModifier(AttributeModifier p_111124_1_) {
/* 124 */     for (int var2 = 0; var2 < 3; var2++) {
/*     */       
/* 126 */       Set var3 = (Set)this.mapByOperation.get(Integer.valueOf(var2));
/* 127 */       var3.remove(p_111124_1_);
/*     */     } 
/*     */     
/* 130 */     Set var4 = (Set)this.mapByName.get(p_111124_1_.getName());
/*     */     
/* 132 */     if (var4 != null) {
/*     */       
/* 134 */       var4.remove(p_111124_1_);
/*     */       
/* 136 */       if (var4.isEmpty())
/*     */       {
/* 138 */         this.mapByName.remove(p_111124_1_.getName());
/*     */       }
/*     */     } 
/*     */     
/* 142 */     this.mapByUUID.remove(p_111124_1_.getID());
/* 143 */     flagForUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllModifiers() {
/* 148 */     Collection var1 = func_111122_c();
/*     */     
/* 150 */     if (var1 != null) {
/*     */       
/* 152 */       ArrayList var4 = Lists.newArrayList(var1);
/* 153 */       Iterator<AttributeModifier> var2 = var4.iterator();
/*     */       
/* 155 */       while (var2.hasNext()) {
/*     */         
/* 157 */         AttributeModifier var3 = var2.next();
/* 158 */         removeModifier(var3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttributeValue() {
/* 165 */     if (this.needsUpdate) {
/*     */       
/* 167 */       this.cachedValue = computeValue();
/* 168 */       this.needsUpdate = false;
/*     */     } 
/*     */     
/* 171 */     return this.cachedValue;
/*     */   }
/*     */ 
/*     */   
/*     */   private double computeValue() {
/* 176 */     double var1 = getBaseValue();
/*     */ 
/*     */     
/* 179 */     for (Iterator<AttributeModifier> var3 = func_180375_b(0).iterator(); var3.hasNext(); var1 += var4.getAmount())
/*     */     {
/* 181 */       AttributeModifier var4 = var3.next();
/*     */     }
/*     */     
/* 184 */     double var7 = var1;
/*     */     
/*     */     Iterator<AttributeModifier> var5;
/*     */     
/* 188 */     for (var5 = func_180375_b(1).iterator(); var5.hasNext(); var7 += var1 * var6.getAmount())
/*     */     {
/* 190 */       AttributeModifier var6 = var5.next();
/*     */     }
/*     */     
/* 193 */     for (var5 = func_180375_b(2).iterator(); var5.hasNext(); var7 *= 1.0D + var6.getAmount())
/*     */     {
/* 195 */       AttributeModifier var6 = var5.next();
/*     */     }
/*     */     
/* 198 */     return this.genericAttribute.clampValue(var7);
/*     */   }
/*     */ 
/*     */   
/*     */   private Collection func_180375_b(int p_180375_1_) {
/* 203 */     HashSet var2 = Sets.newHashSet(getModifiersByOperation(p_180375_1_));
/*     */     
/* 205 */     for (IAttribute var3 = this.genericAttribute.func_180372_d(); var3 != null; var3 = var3.func_180372_d()) {
/*     */       
/* 207 */       IAttributeInstance var4 = this.attributeMap.getAttributeInstance(var3);
/*     */       
/* 209 */       if (var4 != null)
/*     */       {
/* 211 */         var2.addAll(var4.getModifiersByOperation(p_180375_1_));
/*     */       }
/*     */     } 
/*     */     
/* 215 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\attributes\ModifiableAttributeInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */