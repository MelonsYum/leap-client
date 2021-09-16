/*    */ package net.minecraft.client.resources.model;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class BuiltInModel
/*    */   implements IBakedModel
/*    */ {
/*    */   private ItemCameraTransforms field_177557_a;
/*    */   private static final String __OBFID = "CL_00002392";
/*    */   
/*    */   public BuiltInModel(ItemCameraTransforms p_i46086_1_) {
/* 15 */     this.field_177557_a = p_i46086_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public List func_177551_a(EnumFacing p_177551_1_) {
/* 20 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List func_177550_a() {
/* 25 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGui3d() {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAmbientOcclusionEnabled() {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBuiltInRenderer() {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public TextureAtlasSprite getTexture() {
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemCameraTransforms getItemCameraTransforms() {
/* 50 */     return this.field_177557_a;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\model\BuiltInModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */