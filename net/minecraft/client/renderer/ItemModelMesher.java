/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.client.resources.model.ModelManager;
/*    */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ItemModelMesher
/*    */ {
/* 16 */   private final Map simpleShapes = Maps.newHashMap();
/* 17 */   private final Map simpleShapesCache = Maps.newHashMap();
/* 18 */   private final Map shapers = Maps.newHashMap();
/*    */   
/*    */   private final ModelManager modelManager;
/*    */   private static final String __OBFID = "CL_00002536";
/*    */   
/*    */   public ItemModelMesher(ModelManager p_i46250_1_) {
/* 24 */     this.modelManager = p_i46250_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public TextureAtlasSprite getParticleIcon(Item p_178082_1_) {
/* 29 */     return getParticleIcon(p_178082_1_, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public TextureAtlasSprite getParticleIcon(Item p_178087_1_, int p_178087_2_) {
/* 34 */     return getItemModel(new ItemStack(p_178087_1_, 1, p_178087_2_)).getTexture();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBakedModel getItemModel(ItemStack p_178089_1_) {
/* 39 */     Item var2 = p_178089_1_.getItem();
/* 40 */     IBakedModel var3 = getItemModel(var2, getMetadata(p_178089_1_));
/*    */     
/* 42 */     if (var3 == null) {
/*    */       
/* 44 */       ItemMeshDefinition var4 = (ItemMeshDefinition)this.shapers.get(var2);
/*    */       
/* 46 */       if (var4 != null)
/*    */       {
/* 48 */         var3 = this.modelManager.getModel(var4.getModelLocation(p_178089_1_));
/*    */       }
/*    */     } 
/*    */     
/* 52 */     if (var3 == null)
/*    */     {
/* 54 */       var3 = this.modelManager.getMissingModel();
/*    */     }
/*    */     
/* 57 */     return var3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getMetadata(ItemStack p_178084_1_) {
/* 62 */     return p_178084_1_.isItemStackDamageable() ? 0 : p_178084_1_.getMetadata();
/*    */   }
/*    */ 
/*    */   
/*    */   protected IBakedModel getItemModel(Item p_178088_1_, int p_178088_2_) {
/* 67 */     return (IBakedModel)this.simpleShapesCache.get(Integer.valueOf(getIndex(p_178088_1_, p_178088_2_)));
/*    */   }
/*    */ 
/*    */   
/*    */   private int getIndex(Item p_178081_1_, int p_178081_2_) {
/* 72 */     return Item.getIdFromItem(p_178081_1_) << 16 | p_178081_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void register(Item p_178086_1_, int p_178086_2_, ModelResourceLocation p_178086_3_) {
/* 77 */     this.simpleShapes.put(Integer.valueOf(getIndex(p_178086_1_, p_178086_2_)), p_178086_3_);
/* 78 */     this.simpleShapesCache.put(Integer.valueOf(getIndex(p_178086_1_, p_178086_2_)), this.modelManager.getModel(p_178086_3_));
/*    */   }
/*    */ 
/*    */   
/*    */   public void register(Item p_178080_1_, ItemMeshDefinition p_178080_2_) {
/* 83 */     this.shapers.put(p_178080_1_, p_178080_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelManager getModelManager() {
/* 88 */     return this.modelManager;
/*    */   }
/*    */ 
/*    */   
/*    */   public void rebuildCache() {
/* 93 */     this.simpleShapesCache.clear();
/* 94 */     Iterator<Map.Entry> var1 = this.simpleShapes.entrySet().iterator();
/*    */     
/* 96 */     while (var1.hasNext()) {
/*    */       
/* 98 */       Map.Entry var2 = var1.next();
/* 99 */       this.simpleShapesCache.put(var2.getKey(), this.modelManager.getModel((ModelResourceLocation)var2.getValue()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\ItemModelMesher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */