package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public interface IBakedModel {
  List func_177551_a(EnumFacing paramEnumFacing);
  
  List func_177550_a();
  
  boolean isGui3d();
  
  boolean isAmbientOcclusionEnabled();
  
  boolean isBuiltInRenderer();
  
  TextureAtlasSprite getTexture();
  
  ItemCameraTransforms getItemCameraTransforms();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\model\IBakedModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */