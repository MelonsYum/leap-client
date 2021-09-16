/*    */ package net.minecraft.world.gen.structure;
/*    */ 
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.WorldSavedData;
/*    */ 
/*    */ public class MapGenStructureData extends WorldSavedData {
/*  8 */   private NBTTagCompound field_143044_a = new NBTTagCompound();
/*    */   
/*    */   private static final String __OBFID = "CL_00000510";
/*    */   
/*    */   public MapGenStructureData(String p_i43001_1_) {
/* 13 */     super(p_i43001_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound nbt) {
/* 21 */     this.field_143044_a = nbt.getCompoundTag("Features");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound nbt) {
/* 29 */     nbt.setTag("Features", (NBTBase)this.field_143044_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_143043_a(NBTTagCompound p_143043_1_, int p_143043_2_, int p_143043_3_) {
/* 34 */     this.field_143044_a.setTag(func_143042_b(p_143043_2_, p_143043_3_), (NBTBase)p_143043_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String func_143042_b(int p_143042_0_, int p_143042_1_) {
/* 39 */     return "[" + p_143042_0_ + "," + p_143042_1_ + "]";
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound func_143041_a() {
/* 44 */     return this.field_143044_a;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenStructureData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */