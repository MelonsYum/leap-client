/*    */ package net.minecraft.entity.ai.attributes;
/*    */ 
/*    */ public abstract class BaseAttribute
/*    */   implements IAttribute
/*    */ {
/*    */   private final IAttribute field_180373_a;
/*    */   private final String unlocalizedName;
/*    */   private final double defaultValue;
/*    */   private boolean shouldWatch;
/*    */   private static final String __OBFID = "CL_00001565";
/*    */   
/*    */   protected BaseAttribute(IAttribute p_i45892_1_, String p_i45892_2_, double p_i45892_3_) {
/* 13 */     this.field_180373_a = p_i45892_1_;
/* 14 */     this.unlocalizedName = p_i45892_2_;
/* 15 */     this.defaultValue = p_i45892_3_;
/*    */     
/* 17 */     if (p_i45892_2_ == null)
/*    */     {
/* 19 */       throw new IllegalArgumentException("Name cannot be null!");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAttributeUnlocalizedName() {
/* 25 */     return this.unlocalizedName;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getDefaultValue() {
/* 30 */     return this.defaultValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getShouldWatch() {
/* 35 */     return this.shouldWatch;
/*    */   }
/*    */ 
/*    */   
/*    */   public BaseAttribute setShouldWatch(boolean p_111112_1_) {
/* 40 */     this.shouldWatch = p_111112_1_;
/* 41 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IAttribute func_180372_d() {
/* 46 */     return this.field_180373_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 51 */     return this.unlocalizedName.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 56 */     return (p_equals_1_ instanceof IAttribute && this.unlocalizedName.equals(((IAttribute)p_equals_1_).getAttributeUnlocalizedName()));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\attributes\BaseAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */