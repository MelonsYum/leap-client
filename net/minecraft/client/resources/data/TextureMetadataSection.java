/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TextureMetadataSection
/*    */   implements IMetadataSection
/*    */ {
/*    */   private final boolean textureBlur;
/*    */   private final boolean textureClamp;
/*    */   private final List listMipmaps;
/*    */   private static final String __OBFID = "CL_00001114";
/*    */   
/*    */   public TextureMetadataSection(boolean p_i45102_1_, boolean p_i45102_2_, List p_i45102_3_) {
/* 15 */     this.textureBlur = p_i45102_1_;
/* 16 */     this.textureClamp = p_i45102_2_;
/* 17 */     this.listMipmaps = p_i45102_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getTextureBlur() {
/* 22 */     return this.textureBlur;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getTextureClamp() {
/* 27 */     return this.textureClamp;
/*    */   }
/*    */ 
/*    */   
/*    */   public List getListMipmaps() {
/* 32 */     return Collections.unmodifiableList(this.listMipmaps);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\TextureMetadataSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */