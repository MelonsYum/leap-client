/*     */ package net.minecraft.entity.ai.attributes;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.management.LowerStringMap;
/*     */ 
/*     */ public class ServersideAttributeMap
/*     */   extends BaseAttributeMap {
/*  13 */   private final Set attributeInstanceSet = Sets.newHashSet();
/*  14 */   protected final Map descriptionToAttributeInstanceMap = (Map)new LowerStringMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00001569";
/*     */   
/*     */   public ModifiableAttributeInstance func_180795_e(IAttribute p_180795_1_) {
/*  19 */     return (ModifiableAttributeInstance)super.getAttributeInstance(p_180795_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModifiableAttributeInstance func_180796_b(String p_180796_1_) {
/*  24 */     IAttributeInstance var2 = super.getAttributeInstanceByName(p_180796_1_);
/*     */     
/*  26 */     if (var2 == null)
/*     */     {
/*  28 */       var2 = (IAttributeInstance)this.descriptionToAttributeInstanceMap.get(p_180796_1_);
/*     */     }
/*     */     
/*  31 */     return (ModifiableAttributeInstance)var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IAttributeInstance registerAttribute(IAttribute p_111150_1_) {
/*  39 */     IAttributeInstance var2 = super.registerAttribute(p_111150_1_);
/*     */     
/*  41 */     if (p_111150_1_ instanceof RangedAttribute && ((RangedAttribute)p_111150_1_).getDescription() != null)
/*     */     {
/*  43 */       this.descriptionToAttributeInstanceMap.put(((RangedAttribute)p_111150_1_).getDescription(), var2);
/*     */     }
/*     */     
/*  46 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IAttributeInstance func_180376_c(IAttribute p_180376_1_) {
/*  51 */     return new ModifiableAttributeInstance(this, p_180376_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180794_a(IAttributeInstance p_180794_1_) {
/*  56 */     if (p_180794_1_.getAttribute().getShouldWatch())
/*     */     {
/*  58 */       this.attributeInstanceSet.add(p_180794_1_);
/*     */     }
/*     */     
/*  61 */     Iterator<IAttribute> var2 = this.field_180377_c.get(p_180794_1_.getAttribute()).iterator();
/*     */     
/*  63 */     while (var2.hasNext()) {
/*     */       
/*  65 */       IAttribute var3 = var2.next();
/*  66 */       ModifiableAttributeInstance var4 = func_180795_e(var3);
/*     */       
/*  68 */       if (var4 != null)
/*     */       {
/*  70 */         var4.flagForUpdate();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set getAttributeInstanceSet() {
/*  77 */     return this.attributeInstanceSet;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection getWatchedAttributes() {
/*  82 */     HashSet<IAttributeInstance> var1 = Sets.newHashSet();
/*  83 */     Iterator<IAttributeInstance> var2 = getAllAttributes().iterator();
/*     */     
/*  85 */     while (var2.hasNext()) {
/*     */       
/*  87 */       IAttributeInstance var3 = var2.next();
/*     */       
/*  89 */       if (var3.getAttribute().getShouldWatch())
/*     */       {
/*  91 */         var1.add(var3);
/*     */       }
/*     */     } 
/*     */     
/*  95 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public IAttributeInstance getAttributeInstanceByName(String p_111152_1_) {
/* 100 */     return func_180796_b(p_111152_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public IAttributeInstance getAttributeInstance(IAttribute p_111151_1_) {
/* 105 */     return func_180795_e(p_111151_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\attributes\ServersideAttributeMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */