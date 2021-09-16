/*    */ package net.minecraft.world.gen.structure;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class MapGenMineshaft
/*    */   extends MapGenStructure
/*    */ {
/* 10 */   private double field_82673_e = 0.004D;
/*    */   
/*    */   private static final String __OBFID = "CL_00000443";
/*    */   
/*    */   public MapGenMineshaft() {}
/*    */   
/*    */   public String getStructureName() {
/* 17 */     return "Mineshaft";
/*    */   }
/*    */ 
/*    */   
/*    */   public MapGenMineshaft(Map p_i2034_1_) {
/* 22 */     Iterator<Map.Entry> var2 = p_i2034_1_.entrySet().iterator();
/*    */     
/* 24 */     while (var2.hasNext()) {
/*    */       
/* 26 */       Map.Entry var3 = var2.next();
/*    */       
/* 28 */       if (((String)var3.getKey()).equals("chance"))
/*    */       {
/* 30 */         this.field_82673_e = MathHelper.parseDoubleWithDefault((String)var3.getValue(), this.field_82673_e);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_) {
/* 37 */     return (this.rand.nextDouble() < this.field_82673_e && this.rand.nextInt(80) < Math.max(Math.abs(p_75047_1_), Math.abs(p_75047_2_)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) {
/* 42 */     return new StructureMineshaftStart(this.worldObj, this.rand, p_75049_1_, p_75049_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenMineshaft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */