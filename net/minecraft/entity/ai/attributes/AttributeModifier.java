/*     */ package net.minecraft.entity.ai.attributes;
/*     */ 
/*     */ import io.netty.util.internal.ThreadLocalRandom;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributeModifier
/*     */ {
/*     */   private final double amount;
/*     */   private final int operation;
/*     */   private final String name;
/*     */   private final UUID id;
/*     */   private boolean isSaved;
/*     */   private static final String __OBFID = "CL_00001564";
/*     */   
/*     */   public AttributeModifier(String p_i1605_1_, double p_i1605_2_, int p_i1605_4_) {
/*  23 */     this(MathHelper.func_180182_a((Random)ThreadLocalRandom.current()), p_i1605_1_, p_i1605_2_, p_i1605_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributeModifier(UUID p_i1606_1_, String p_i1606_2_, double p_i1606_3_, int p_i1606_5_) {
/*  28 */     this.isSaved = true;
/*  29 */     this.id = p_i1606_1_;
/*  30 */     this.name = p_i1606_2_;
/*  31 */     this.amount = p_i1606_3_;
/*  32 */     this.operation = p_i1606_5_;
/*  33 */     Validate.notEmpty(p_i1606_2_, "Modifier name cannot be empty", new Object[0]);
/*  34 */     Validate.inclusiveBetween(0L, 2L, p_i1606_5_, "Invalid operation");
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getID() {
/*  39 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  44 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOperation() {
/*  49 */     return this.operation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAmount() {
/*  54 */     return this.amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSaved() {
/*  62 */     return this.isSaved;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeModifier setSaved(boolean p_111168_1_) {
/*  70 */     this.isSaved = p_111168_1_;
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  76 */     if (this == p_equals_1_)
/*     */     {
/*  78 */       return true;
/*     */     }
/*  80 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*     */       
/*  82 */       AttributeModifier var2 = (AttributeModifier)p_equals_1_;
/*     */       
/*  84 */       if (this.id != null) {
/*     */         
/*  86 */         if (!this.id.equals(var2.id))
/*     */         {
/*  88 */           return false;
/*     */         }
/*     */       }
/*  91 */       else if (var2.id != null) {
/*     */         
/*  93 */         return false;
/*     */       } 
/*     */       
/*  96 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 106 */     return (this.id != null) ? this.id.hashCode() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 111 */     return "AttributeModifier{amount=" + this.amount + ", operation=" + this.operation + ", name='" + this.name + '\'' + ", id=" + this.id + ", serialize=" + this.isSaved + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\attributes\AttributeModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */