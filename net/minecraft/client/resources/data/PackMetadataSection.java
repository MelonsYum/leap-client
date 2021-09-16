/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class PackMetadataSection
/*    */   implements IMetadataSection
/*    */ {
/*    */   private final IChatComponent packDescription;
/*    */   private final int packFormat;
/*    */   private static final String __OBFID = "CL_00001112";
/*    */   
/*    */   public PackMetadataSection(IChatComponent p_i1034_1_, int p_i1034_2_) {
/* 13 */     this.packDescription = p_i1034_1_;
/* 14 */     this.packFormat = p_i1034_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_152805_a() {
/* 19 */     return this.packDescription;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPackFormat() {
/* 24 */     return this.packFormat;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\PackMetadataSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */