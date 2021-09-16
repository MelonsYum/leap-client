/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ 
/*    */ public class RegionRenderCacheBuilder
/*    */ {
/*  7 */   private final WorldRenderer[] field_179040_a = new WorldRenderer[(EnumWorldBlockLayer.values()).length];
/*    */   
/*    */   private static final String __OBFID = "CL_00002564";
/*    */   
/*    */   public RegionRenderCacheBuilder() {
/* 12 */     this.field_179040_a[EnumWorldBlockLayer.SOLID.ordinal()] = new WorldRenderer(2097152);
/* 13 */     this.field_179040_a[EnumWorldBlockLayer.CUTOUT.ordinal()] = new WorldRenderer(131072);
/* 14 */     this.field_179040_a[EnumWorldBlockLayer.CUTOUT_MIPPED.ordinal()] = new WorldRenderer(131072);
/* 15 */     this.field_179040_a[EnumWorldBlockLayer.TRANSLUCENT.ordinal()] = new WorldRenderer(262144);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldRenderer func_179038_a(EnumWorldBlockLayer p_179038_1_) {
/* 20 */     return this.field_179040_a[p_179038_1_.ordinal()];
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldRenderer func_179039_a(int p_179039_1_) {
/* 25 */     return this.field_179040_a[p_179039_1_];
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\RegionRenderCacheBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */