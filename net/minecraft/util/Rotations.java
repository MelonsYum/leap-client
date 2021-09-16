/*    */ package net.minecraft.util;
/*    */ 
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagFloat;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ 
/*    */ public class Rotations
/*    */ {
/*    */   protected final float field_179419_a;
/*    */   protected final float field_179417_b;
/*    */   protected final float field_179418_c;
/*    */   private static final String __OBFID = "CL_00002316";
/*    */   
/*    */   public Rotations(float p_i46009_1_, float p_i46009_2_, float p_i46009_3_) {
/* 15 */     this.field_179419_a = p_i46009_1_;
/* 16 */     this.field_179417_b = p_i46009_2_;
/* 17 */     this.field_179418_c = p_i46009_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Rotations(NBTTagList p_i46010_1_) {
/* 22 */     this.field_179419_a = p_i46010_1_.getFloat(0);
/* 23 */     this.field_179417_b = p_i46010_1_.getFloat(1);
/* 24 */     this.field_179418_c = p_i46010_1_.getFloat(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagList func_179414_a() {
/* 29 */     NBTTagList var1 = new NBTTagList();
/* 30 */     var1.appendTag((NBTBase)new NBTTagFloat(this.field_179419_a));
/* 31 */     var1.appendTag((NBTBase)new NBTTagFloat(this.field_179417_b));
/* 32 */     var1.appendTag((NBTBase)new NBTTagFloat(this.field_179418_c));
/* 33 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 38 */     if (!(p_equals_1_ instanceof Rotations))
/*    */     {
/* 40 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 44 */     Rotations var2 = (Rotations)p_equals_1_;
/* 45 */     return (this.field_179419_a == var2.field_179419_a && this.field_179417_b == var2.field_179417_b && this.field_179418_c == var2.field_179418_c);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float func_179415_b() {
/* 51 */     return this.field_179419_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_179416_c() {
/* 56 */     return this.field_179417_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_179413_d() {
/* 61 */     return this.field_179418_c;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Rotations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */