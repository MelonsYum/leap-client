/*    */ package optifine;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import javax.vecmath.Vector3f;
/*    */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*    */ import net.minecraft.client.renderer.block.model.BlockFaceUV;
/*    */ import net.minecraft.client.renderer.block.model.BlockPartFace;
/*    */ import net.minecraft.client.renderer.block.model.BlockPartRotation;
/*    */ import net.minecraft.client.renderer.block.model.FaceBakery;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.client.resources.model.ModelRotation;
/*    */ import net.minecraft.client.resources.model.SimpleBakedModel;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ 
/*    */ public class BlockModelUtils
/*    */ {
/*    */   public static IBakedModel makeModelCube(String spriteName, int tintIndex) {
/* 21 */     TextureAtlasSprite sprite = Config.getMinecraft().getTextureMapBlocks().getAtlasSprite(spriteName);
/* 22 */     return makeModelCube(sprite, tintIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   public static IBakedModel makeModelCube(TextureAtlasSprite sprite, int tintIndex) {
/* 27 */     ArrayList generalQuads = new ArrayList();
/* 28 */     EnumFacing[] facings = EnumFacing.VALUES;
/* 29 */     ArrayList<ArrayList<BakedQuad>> faceQuads = new ArrayList(facings.length);
/*    */     
/* 31 */     for (int bakedModel = 0; bakedModel < facings.length; bakedModel++) {
/*    */       
/* 33 */       EnumFacing facing = facings[bakedModel];
/* 34 */       ArrayList<BakedQuad> quads = new ArrayList();
/* 35 */       quads.add(makeBakedQuad(facing, sprite, tintIndex));
/* 36 */       faceQuads.add(quads);
/*    */     } 
/*    */     
/* 39 */     SimpleBakedModel var8 = new SimpleBakedModel(generalQuads, faceQuads, true, true, sprite, ItemCameraTransforms.field_178357_a);
/* 40 */     return (IBakedModel)var8;
/*    */   }
/*    */ 
/*    */   
/*    */   private static BakedQuad makeBakedQuad(EnumFacing facing, TextureAtlasSprite sprite, int tintIndex) {
/* 45 */     Vector3f posFrom = new Vector3f(0.0F, 0.0F, 0.0F);
/* 46 */     Vector3f posTo = new Vector3f(16.0F, 16.0F, 16.0F);
/* 47 */     BlockFaceUV uv = new BlockFaceUV(new float[] { 0.0F, 0.0F, 16.0F, 16.0F }, 0);
/* 48 */     BlockPartFace face = new BlockPartFace(facing, tintIndex, "#" + facing.getName(), uv);
/* 49 */     ModelRotation modelRotation = ModelRotation.X0_Y0;
/* 50 */     Object partRotation = null;
/* 51 */     boolean uvLocked = false;
/* 52 */     boolean shade = true;
/* 53 */     FaceBakery faceBakery = new FaceBakery();
/* 54 */     BakedQuad quad = faceBakery.func_178414_a(posFrom, posTo, face, sprite, facing, modelRotation, (BlockPartRotation)partRotation, uvLocked, shade);
/* 55 */     return quad;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\BlockModelUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */