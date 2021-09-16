/*      */ package net.minecraft.client.renderer.entity;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.Callable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockDirt;
/*      */ import net.minecraft.block.BlockDoublePlant;
/*      */ import net.minecraft.block.BlockFlower;
/*      */ import net.minecraft.block.BlockHugeMushroom;
/*      */ import net.minecraft.block.BlockPlanks;
/*      */ import net.minecraft.block.BlockPrismarine;
/*      */ import net.minecraft.block.BlockQuartz;
/*      */ import net.minecraft.block.BlockRedSandstone;
/*      */ import net.minecraft.block.BlockSand;
/*      */ import net.minecraft.block.BlockSandStone;
/*      */ import net.minecraft.block.BlockSilverfish;
/*      */ import net.minecraft.block.BlockStone;
/*      */ import net.minecraft.block.BlockStoneBrick;
/*      */ import net.minecraft.block.BlockStoneSlab;
/*      */ import net.minecraft.block.BlockStoneSlabNew;
/*      */ import net.minecraft.block.BlockTallGrass;
/*      */ import net.minecraft.block.BlockWall;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.renderer.EntityRenderer;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*      */ import net.minecraft.client.renderer.ItemModelMesher;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.WorldRenderer;
/*      */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*      */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*      */ import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.renderer.texture.TextureUtil;
/*      */ import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*      */ import net.minecraft.client.resources.model.IBakedModel;
/*      */ import net.minecraft.client.resources.model.ModelManager;
/*      */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.EnumDyeColor;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemFishFood;
/*      */ import net.minecraft.item.ItemPotion;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumWorldBlockLayer;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Vec3i;
/*      */ import optifine.Config;
/*      */ import optifine.CustomColors;
/*      */ import optifine.CustomItems;
/*      */ import optifine.Reflector;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RenderItem
/*      */   implements IResourceManagerReloadListener
/*      */ {
/*   72 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*      */   
/*      */   private boolean field_175058_l = true;
/*      */   
/*      */   public float zLevel;
/*      */   private final ItemModelMesher itemModelMesher;
/*      */   private final TextureManager field_175057_n;
/*   79 */   public static float field_175055_b = 0.0F;
/*   80 */   public static float field_175056_c = 0.0F;
/*   81 */   public static float field_175053_d = 0.0F;
/*   82 */   public static float field_175054_e = 0.0F;
/*   83 */   public static float field_175051_f = 0.0F;
/*   84 */   public static float field_175052_g = 0.0F;
/*   85 */   public static float field_175061_h = 0.0F;
/*   86 */   public static float field_175062_i = 0.0F;
/*   87 */   public static float field_175060_j = 0.0F;
/*      */   private static final String __OBFID = "CL_00001003";
/*   89 */   private ModelResourceLocation modelLocation = null;
/*      */ 
/*      */   
/*      */   public RenderItem(TextureManager p_i46165_1_, ModelManager p_i46165_2_) {
/*   93 */     this.field_175057_n = p_i46165_1_;
/*   94 */     Config.setModelManager(p_i46165_2_);
/*      */     
/*   96 */     if (Reflector.ItemModelMesherForge_Constructor.exists()) {
/*      */       
/*   98 */       this.itemModelMesher = (ItemModelMesher)Reflector.newInstance(Reflector.ItemModelMesherForge_Constructor, new Object[] { p_i46165_2_ });
/*      */     }
/*      */     else {
/*      */       
/*  102 */       this.itemModelMesher = new ItemModelMesher(p_i46165_2_);
/*      */     } 
/*      */     
/*  105 */     registerItems();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175039_a(boolean p_175039_1_) {
/*  110 */     this.field_175058_l = p_175039_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemModelMesher getItemModelMesher() {
/*  115 */     return this.itemModelMesher;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void registerItem(Item p_175048_1_, int p_175048_2_, String p_175048_3_) {
/*  120 */     this.itemModelMesher.register(p_175048_1_, p_175048_2_, new ModelResourceLocation(p_175048_3_, "inventory"));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void registerBlock(Block p_175029_1_, int p_175029_2_, String p_175029_3_) {
/*  125 */     registerItem(Item.getItemFromBlock(p_175029_1_), p_175029_2_, p_175029_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   private void registerBlock(Block p_175031_1_, String p_175031_2_) {
/*  130 */     registerBlock(p_175031_1_, 0, p_175031_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   private void registerItem(Item p_175047_1_, String p_175047_2_) {
/*  135 */     registerItem(p_175047_1_, 0, p_175047_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175036_a(IBakedModel p_175036_1_, ItemStack p_175036_2_) {
/*  140 */     func_175045_a(p_175036_1_, -1, p_175036_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175035_a(IBakedModel p_175035_1_, int p_175035_2_) {
/*  145 */     func_175045_a(p_175035_1_, p_175035_2_, null);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175045_a(IBakedModel p_175045_1_, int p_175045_2_, ItemStack p_175045_3_) {
/*  150 */     Tessellator var4 = Tessellator.getInstance();
/*  151 */     WorldRenderer var5 = var4.getWorldRenderer();
/*  152 */     boolean renderTextureMap = Minecraft.getMinecraft().getTextureMapBlocks().isTextureBound();
/*  153 */     boolean multiTexture = (Config.isMultiTexture() && renderTextureMap);
/*      */     
/*  155 */     if (multiTexture)
/*      */     {
/*  157 */       var5.setBlockLayer(EnumWorldBlockLayer.SOLID);
/*      */     }
/*      */     
/*  160 */     var5.startDrawingQuads();
/*  161 */     var5.setVertexFormat(DefaultVertexFormats.field_176599_b);
/*  162 */     EnumFacing[] var6 = EnumFacing.VALUES;
/*  163 */     int var7 = var6.length;
/*      */     
/*  165 */     for (int var8 = 0; var8 < var7; var8++) {
/*      */       
/*  167 */       EnumFacing var9 = var6[var8];
/*  168 */       func_175032_a(var5, p_175045_1_.func_177551_a(var9), p_175045_2_, p_175045_3_);
/*      */     } 
/*      */     
/*  171 */     func_175032_a(var5, p_175045_1_.func_177550_a(), p_175045_2_, p_175045_3_);
/*  172 */     var4.draw();
/*      */     
/*  174 */     if (multiTexture) {
/*      */       
/*  176 */       var5.setBlockLayer(null);
/*  177 */       GlStateManager.bindCurrentTexture();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180454_a(ItemStack p_180454_1_, IBakedModel p_180454_2_) {
/*  183 */     GlStateManager.pushMatrix();
/*  184 */     GlStateManager.scale(0.5F, 0.5F, 0.5F);
/*      */     
/*  186 */     if (p_180454_2_.isBuiltInRenderer()) {
/*      */       
/*  188 */       GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/*  189 */       GlStateManager.translate(-0.5F, -0.5F, -0.5F);
/*  190 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  191 */       GlStateManager.enableRescaleNormal();
/*  192 */       TileEntityRendererChestHelper.instance.renderByItem(p_180454_1_);
/*      */     }
/*      */     else {
/*      */       
/*  196 */       if (Config.isCustomItems())
/*      */       {
/*  198 */         p_180454_2_ = CustomItems.getCustomItemModel(p_180454_1_, p_180454_2_, this.modelLocation);
/*      */       }
/*      */       
/*  201 */       GlStateManager.translate(-0.5F, -0.5F, -0.5F);
/*  202 */       func_175036_a(p_180454_2_, p_180454_1_);
/*      */       
/*  204 */       if (p_180454_1_.hasEffect() && (!Config.isCustomItems() || !CustomItems.renderCustomEffect(this, p_180454_1_, p_180454_2_)))
/*      */       {
/*  206 */         renderEffect(p_180454_2_);
/*      */       }
/*      */     } 
/*      */     
/*  210 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderEffect(IBakedModel p_180451_1_) {
/*  215 */     if (!Config.isCustomItems() || CustomItems.isUseGlint()) {
/*      */       
/*  217 */       GlStateManager.depthMask(false);
/*  218 */       GlStateManager.depthFunc(514);
/*  219 */       GlStateManager.disableLighting();
/*  220 */       GlStateManager.blendFunc(768, 1);
/*  221 */       this.field_175057_n.bindTexture(RES_ITEM_GLINT);
/*  222 */       GlStateManager.matrixMode(5890);
/*  223 */       GlStateManager.pushMatrix();
/*  224 */       GlStateManager.scale(8.0F, 8.0F, 8.0F);
/*  225 */       float var2 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
/*  226 */       GlStateManager.translate(var2, 0.0F, 0.0F);
/*  227 */       GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
/*  228 */       func_175035_a(p_180451_1_, -8372020);
/*  229 */       GlStateManager.popMatrix();
/*  230 */       GlStateManager.pushMatrix();
/*  231 */       GlStateManager.scale(8.0F, 8.0F, 8.0F);
/*  232 */       float var3 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
/*  233 */       GlStateManager.translate(-var3, 0.0F, 0.0F);
/*  234 */       GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
/*  235 */       func_175035_a(p_180451_1_, -8372020);
/*  236 */       GlStateManager.popMatrix();
/*  237 */       GlStateManager.matrixMode(5888);
/*  238 */       GlStateManager.blendFunc(770, 771);
/*  239 */       GlStateManager.enableLighting();
/*  240 */       GlStateManager.depthFunc(515);
/*  241 */       GlStateManager.depthMask(true);
/*  242 */       this.field_175057_n.bindTexture(TextureMap.locationBlocksTexture);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175038_a(WorldRenderer p_175038_1_, BakedQuad p_175038_2_) {
/*  248 */     Vec3i var3 = p_175038_2_.getFace().getDirectionVec();
/*  249 */     p_175038_1_.func_178975_e(var3.getX(), var3.getY(), var3.getZ());
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175033_a(WorldRenderer p_175033_1_, BakedQuad p_175033_2_, int p_175033_3_) {
/*  254 */     if (p_175033_1_.isMultiTexture()) {
/*      */       
/*  256 */       p_175033_1_.func_178981_a(p_175033_2_.getVertexDataSingle());
/*  257 */       p_175033_1_.putSprite(p_175033_2_.getSprite());
/*      */     }
/*      */     else {
/*      */       
/*  261 */       p_175033_1_.func_178981_a(p_175033_2_.func_178209_a());
/*      */     } 
/*      */     
/*  264 */     if (Reflector.IColoredBakedQuad.exists() && Reflector.IColoredBakedQuad.isInstance(p_175033_2_)) {
/*      */       
/*  266 */       forgeHooksClient_putQuadColor(p_175033_1_, p_175033_2_, p_175033_3_);
/*      */     }
/*      */     else {
/*      */       
/*  270 */       p_175033_1_.func_178968_d(p_175033_3_);
/*      */     } 
/*      */     
/*  273 */     func_175038_a(p_175033_1_, p_175033_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175032_a(WorldRenderer p_175032_1_, List p_175032_2_, int p_175032_3_, ItemStack p_175032_4_) {
/*  278 */     boolean var5 = (p_175032_3_ == -1 && p_175032_4_ != null);
/*      */ 
/*      */ 
/*      */     
/*  282 */     for (Iterator<BakedQuad> var6 = p_175032_2_.iterator(); var6.hasNext(); func_175033_a(p_175032_1_, var7, var8)) {
/*      */       
/*  284 */       BakedQuad var7 = var6.next();
/*  285 */       int var8 = p_175032_3_;
/*      */       
/*  287 */       if (var5 && var7.func_178212_b()) {
/*      */         
/*  289 */         var8 = p_175032_4_.getItem().getColorFromItemStack(p_175032_4_, var7.func_178211_c());
/*      */         
/*  291 */         if (Config.isCustomColors())
/*      */         {
/*  293 */           var8 = CustomColors.getColorFromItemStack(p_175032_4_, var7.func_178211_c(), var8);
/*      */         }
/*      */         
/*  296 */         if (EntityRenderer.anaglyphEnable)
/*      */         {
/*  298 */           var8 = TextureUtil.func_177054_c(var8);
/*      */         }
/*      */         
/*  301 */         var8 |= 0xFF000000;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175050_a(ItemStack p_175050_1_) {
/*  308 */     IBakedModel var2 = this.itemModelMesher.getItemModel(p_175050_1_);
/*  309 */     return (var2 == null) ? false : var2.isAmbientOcclusionEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175046_c(ItemStack p_175046_1_) {
/*  314 */     IBakedModel var2 = this.itemModelMesher.getItemModel(p_175046_1_);
/*  315 */     Item var3 = p_175046_1_.getItem();
/*      */     
/*  317 */     if (var3 != null) {
/*      */       
/*  319 */       boolean var4 = var2.isAmbientOcclusionEnabled();
/*      */       
/*  321 */       if (!var4)
/*      */       {
/*  323 */         GlStateManager.scale(2.0F, 2.0F, 2.0F);
/*      */       }
/*      */       
/*  326 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175043_b(ItemStack p_175043_1_) {
/*  332 */     IBakedModel var2 = this.itemModelMesher.getItemModel(p_175043_1_);
/*  333 */     func_175040_a(p_175043_1_, var2, ItemCameraTransforms.TransformType.NONE);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175049_a(ItemStack p_175049_1_, EntityLivingBase p_175049_2_, ItemCameraTransforms.TransformType p_175049_3_) {
/*  338 */     IBakedModel var4 = this.itemModelMesher.getItemModel(p_175049_1_);
/*      */     
/*  340 */     if (p_175049_2_ instanceof EntityPlayer) {
/*      */       
/*  342 */       EntityPlayer var5 = (EntityPlayer)p_175049_2_;
/*  343 */       Item var6 = p_175049_1_.getItem();
/*  344 */       ModelResourceLocation var7 = null;
/*      */       
/*  346 */       if (var6 == Items.fishing_rod && var5.fishEntity != null) {
/*      */         
/*  348 */         var7 = new ModelResourceLocation("fishing_rod_cast", "inventory");
/*      */       }
/*  350 */       else if (var6 == Items.bow && var5.getItemInUse() != null) {
/*      */         
/*  352 */         int var8 = p_175049_1_.getMaxItemUseDuration() - var5.getItemInUseCount();
/*      */         
/*  354 */         if (var8 >= 18)
/*      */         {
/*  356 */           var7 = new ModelResourceLocation("bow_pulling_2", "inventory");
/*      */         }
/*  358 */         else if (var8 > 13)
/*      */         {
/*  360 */           var7 = new ModelResourceLocation("bow_pulling_1", "inventory");
/*      */         }
/*  362 */         else if (var8 > 0)
/*      */         {
/*  364 */           var7 = new ModelResourceLocation("bow_pulling_0", "inventory");
/*      */         }
/*      */       
/*  367 */       } else if (Reflector.ForgeItem_getModel.exists()) {
/*      */         
/*  369 */         var7 = (ModelResourceLocation)Reflector.call(var6, Reflector.ForgeItem_getModel, new Object[] { p_175049_1_, var5, Integer.valueOf(var5.getItemInUseCount()) });
/*      */       } 
/*      */       
/*  372 */       this.modelLocation = var7;
/*      */       
/*  374 */       if (var7 != null)
/*      */       {
/*  376 */         var4 = this.itemModelMesher.getModelManager().getModel(var7);
/*      */       }
/*      */     } 
/*      */     
/*  380 */     func_175040_a(p_175049_1_, var4, p_175049_3_);
/*  381 */     this.modelLocation = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175034_a(ItemTransformVec3f p_175034_1_) {
/*  386 */     applyVanillaTransform(p_175034_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void applyVanillaTransform(ItemTransformVec3f p_175034_1_) {
/*  391 */     if (p_175034_1_ != ItemTransformVec3f.field_178366_a) {
/*      */       
/*  393 */       GlStateManager.translate(p_175034_1_.field_178365_c.x + field_175055_b, p_175034_1_.field_178365_c.y + field_175056_c, p_175034_1_.field_178365_c.z + field_175053_d);
/*  394 */       GlStateManager.rotate(p_175034_1_.field_178364_b.y + field_175051_f, 0.0F, 1.0F, 0.0F);
/*  395 */       GlStateManager.rotate(p_175034_1_.field_178364_b.x + field_175054_e, 1.0F, 0.0F, 0.0F);
/*  396 */       GlStateManager.rotate(p_175034_1_.field_178364_b.z + field_175052_g, 0.0F, 0.0F, 1.0F);
/*  397 */       GlStateManager.scale(p_175034_1_.field_178363_d.x + field_175061_h, p_175034_1_.field_178363_d.y + field_175062_i, p_175034_1_.field_178363_d.z + field_175060_j);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175040_a(ItemStack p_175040_1_, IBakedModel p_175040_2_, ItemCameraTransforms.TransformType p_175040_3_) {
/*  403 */     this.field_175057_n.bindTexture(TextureMap.locationBlocksTexture);
/*  404 */     this.field_175057_n.getTexture(TextureMap.locationBlocksTexture).func_174936_b(false, false);
/*  405 */     func_175046_c(p_175040_1_);
/*  406 */     GlStateManager.enableRescaleNormal();
/*  407 */     GlStateManager.alphaFunc(516, 0.1F);
/*  408 */     GlStateManager.enableBlend();
/*  409 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  410 */     GlStateManager.pushMatrix();
/*      */     
/*  412 */     if (Reflector.ForgeHooksClient_handleCameraTransforms.exists()) {
/*      */       
/*  414 */       p_175040_2_ = (IBakedModel)Reflector.call(Reflector.ForgeHooksClient_handleCameraTransforms, new Object[] { p_175040_2_, p_175040_3_ });
/*      */     }
/*      */     else {
/*      */       
/*  418 */       switch (SwitchTransformType.field_178640_a[p_175040_3_.ordinal()]) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  425 */           func_175034_a((p_175040_2_.getItemCameraTransforms()).field_178355_b);
/*      */           break;
/*      */         
/*      */         case 3:
/*  429 */           func_175034_a((p_175040_2_.getItemCameraTransforms()).field_178356_c);
/*      */           break;
/*      */         
/*      */         case 4:
/*  433 */           func_175034_a((p_175040_2_.getItemCameraTransforms()).field_178353_d);
/*      */           break;
/*      */         
/*      */         case 5:
/*  437 */           func_175034_a((p_175040_2_.getItemCameraTransforms()).field_178354_e);
/*      */           break;
/*      */       } 
/*      */     } 
/*  441 */     func_180454_a(p_175040_1_, p_175040_2_);
/*  442 */     GlStateManager.popMatrix();
/*  443 */     GlStateManager.disableRescaleNormal();
/*  444 */     GlStateManager.disableBlend();
/*  445 */     this.field_175057_n.bindTexture(TextureMap.locationBlocksTexture);
/*  446 */     this.field_175057_n.getTexture(TextureMap.locationBlocksTexture).func_174935_a();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175042_a(ItemStack p_175042_1_, int p_175042_2_, int p_175042_3_) {
/*  451 */     IBakedModel var4 = this.itemModelMesher.getItemModel(p_175042_1_);
/*  452 */     GlStateManager.pushMatrix();
/*  453 */     this.field_175057_n.bindTexture(TextureMap.locationBlocksTexture);
/*  454 */     this.field_175057_n.getTexture(TextureMap.locationBlocksTexture).func_174936_b(false, false);
/*  455 */     GlStateManager.enableRescaleNormal();
/*  456 */     GlStateManager.enableAlpha();
/*  457 */     GlStateManager.alphaFunc(516, 0.1F);
/*  458 */     GlStateManager.enableBlend();
/*  459 */     GlStateManager.blendFunc(770, 771);
/*  460 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  461 */     func_180452_a(p_175042_2_, p_175042_3_, var4.isAmbientOcclusionEnabled());
/*      */     
/*  463 */     if (Reflector.ForgeHooksClient_handleCameraTransforms.exists()) {
/*      */       
/*  465 */       var4 = (IBakedModel)Reflector.call(Reflector.ForgeHooksClient_handleCameraTransforms, new Object[] { var4, ItemCameraTransforms.TransformType.GUI });
/*      */     }
/*      */     else {
/*      */       
/*  469 */       func_175034_a((var4.getItemCameraTransforms()).field_178354_e);
/*      */     } 
/*      */     
/*  472 */     func_180454_a(p_175042_1_, var4);
/*  473 */     GlStateManager.disableAlpha();
/*  474 */     GlStateManager.disableRescaleNormal();
/*  475 */     GlStateManager.disableLighting();
/*  476 */     GlStateManager.popMatrix();
/*  477 */     this.field_175057_n.bindTexture(TextureMap.locationBlocksTexture);
/*  478 */     this.field_175057_n.getTexture(TextureMap.locationBlocksTexture).func_174935_a();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180452_a(int p_180452_1_, int p_180452_2_, boolean p_180452_3_) {
/*  483 */     GlStateManager.translate(p_180452_1_, p_180452_2_, 100.0F + this.zLevel);
/*  484 */     GlStateManager.translate(8.0F, 8.0F, 0.0F);
/*  485 */     GlStateManager.scale(1.0F, 1.0F, -1.0F);
/*  486 */     GlStateManager.scale(0.5F, 0.5F, 0.5F);
/*      */     
/*  488 */     if (p_180452_3_) {
/*      */       
/*  490 */       GlStateManager.scale(40.0F, 40.0F, 40.0F);
/*  491 */       GlStateManager.rotate(210.0F, 1.0F, 0.0F, 0.0F);
/*  492 */       GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
/*  493 */       GlStateManager.enableLighting();
/*      */     }
/*      */     else {
/*      */       
/*  497 */       GlStateManager.scale(64.0F, 64.0F, 64.0F);
/*  498 */       GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
/*  499 */       GlStateManager.disableLighting();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180450_b(final ItemStack p_180450_1_, int p_180450_2_, int p_180450_3_) {
/*  505 */     if (p_180450_1_ != null) {
/*      */       
/*  507 */       this.zLevel += 50.0F;
/*      */ 
/*      */       
/*      */       try {
/*  511 */         func_175042_a(p_180450_1_, p_180450_2_, p_180450_3_);
/*      */       }
/*  513 */       catch (Throwable var7) {
/*      */         
/*  515 */         CrashReport var5 = CrashReport.makeCrashReport(var7, "Rendering item");
/*  516 */         CrashReportCategory var6 = var5.makeCategory("Item being rendered");
/*  517 */         var6.addCrashSectionCallable("Item Type", new Callable()
/*      */             {
/*      */               private static final String __OBFID = "CL_00001004";
/*      */               
/*      */               public String call() {
/*  522 */                 return String.valueOf(p_180450_1_.getItem());
/*      */               }
/*      */             });
/*  525 */         var6.addCrashSectionCallable("Item Aux", new Callable()
/*      */             {
/*      */               private static final String __OBFID = "CL_00001005";
/*      */               
/*      */               public String call() {
/*  530 */                 return String.valueOf(p_180450_1_.getMetadata());
/*      */               }
/*      */             });
/*  533 */         var6.addCrashSectionCallable("Item NBT", new Callable()
/*      */             {
/*      */               private static final String __OBFID = "CL_00001006";
/*      */               
/*      */               public String call() {
/*  538 */                 return String.valueOf(p_180450_1_.getTagCompound());
/*      */               }
/*      */             });
/*  541 */         var6.addCrashSectionCallable("Item Foil", new Callable()
/*      */             {
/*      */               public String call()
/*      */               {
/*  545 */                 return String.valueOf(p_180450_1_.hasEffect());
/*      */               }
/*      */             });
/*  548 */         throw new ReportedException(var5);
/*      */       } 
/*      */       
/*  551 */       this.zLevel -= 50.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175030_a(FontRenderer p_175030_1_, ItemStack p_175030_2_, int p_175030_3_, int p_175030_4_) {
/*  557 */     func_180453_a(p_175030_1_, p_175030_2_, p_175030_3_, p_175030_4_, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180453_a(FontRenderer p_180453_1_, ItemStack p_180453_2_, int p_180453_3_, int p_180453_4_, String p_180453_5_) {
/*  562 */     if (p_180453_2_ != null) {
/*      */       
/*  564 */       if (p_180453_2_.stackSize != 1 || p_180453_5_ != null) {
/*      */         
/*  566 */         String itemDamaged = (p_180453_5_ == null) ? String.valueOf(p_180453_2_.stackSize) : p_180453_5_;
/*      */         
/*  568 */         if (p_180453_5_ == null && p_180453_2_.stackSize < 1)
/*      */         {
/*  570 */           itemDamaged = EnumChatFormatting.RED + String.valueOf(p_180453_2_.stackSize);
/*      */         }
/*      */         
/*  573 */         GlStateManager.disableLighting();
/*  574 */         GlStateManager.disableDepth();
/*  575 */         GlStateManager.disableBlend();
/*  576 */         p_180453_1_.drawStringWithShadow(itemDamaged, (p_180453_3_ + 19 - 2 - p_180453_1_.getStringWidth(itemDamaged)), (p_180453_4_ + 6 + 3), 16777215);
/*  577 */         GlStateManager.enableLighting();
/*  578 */         GlStateManager.enableDepth();
/*      */       } 
/*      */       
/*  581 */       boolean itemDamaged1 = p_180453_2_.isItemDamaged();
/*      */       
/*  583 */       if (Reflector.ForgeItem_showDurabilityBar.exists())
/*      */       {
/*  585 */         itemDamaged1 = Reflector.callBoolean(p_180453_2_.getItem(), Reflector.ForgeItem_showDurabilityBar, new Object[] { p_180453_2_ });
/*      */       }
/*      */       
/*  588 */       if (itemDamaged1) {
/*      */         
/*  590 */         int var12 = (int)Math.round(13.0D - p_180453_2_.getItemDamage() * 13.0D / p_180453_2_.getMaxDamage());
/*  591 */         int var7 = (int)Math.round(255.0D - p_180453_2_.getItemDamage() * 255.0D / p_180453_2_.getMaxDamage());
/*      */         
/*  593 */         if (Reflector.ForgeItem_getDurabilityForDisplay.exists()) {
/*      */           
/*  595 */           double var8 = Reflector.callDouble(p_180453_2_.getItem(), Reflector.ForgeItem_getDurabilityForDisplay, new Object[] { p_180453_2_ });
/*  596 */           var12 = (int)Math.round(13.0D - var8 * 13.0D);
/*  597 */           var7 = (int)Math.round(255.0D - var8 * 255.0D);
/*      */         } 
/*      */         
/*  600 */         GlStateManager.disableLighting();
/*  601 */         GlStateManager.disableDepth();
/*  602 */         GlStateManager.func_179090_x();
/*  603 */         GlStateManager.disableAlpha();
/*  604 */         GlStateManager.disableBlend();
/*  605 */         Tessellator var81 = Tessellator.getInstance();
/*  606 */         WorldRenderer var9 = var81.getWorldRenderer();
/*  607 */         int var10 = 255 - var7 << 16 | var7 << 8;
/*  608 */         int var11 = (255 - var7) / 4 << 16 | 0x3F00;
/*  609 */         func_175044_a(var9, p_180453_3_ + 2, p_180453_4_ + 13, 13, 2, 0);
/*  610 */         func_175044_a(var9, p_180453_3_ + 2, p_180453_4_ + 13, 12, 1, var11);
/*  611 */         func_175044_a(var9, p_180453_3_ + 2, p_180453_4_ + 13, var12, 1, var10);
/*      */         
/*  613 */         if (!Reflector.ForgeHooksClient.exists())
/*      */         {
/*  615 */           GlStateManager.enableBlend();
/*      */         }
/*      */         
/*  618 */         GlStateManager.enableAlpha();
/*  619 */         GlStateManager.func_179098_w();
/*  620 */         GlStateManager.enableLighting();
/*  621 */         GlStateManager.enableDepth();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175044_a(WorldRenderer p_175044_1_, int p_175044_2_, int p_175044_3_, int p_175044_4_, int p_175044_5_, int p_175044_6_) {
/*  628 */     p_175044_1_.startDrawingQuads();
/*  629 */     p_175044_1_.func_178991_c(p_175044_6_);
/*  630 */     p_175044_1_.addVertex((p_175044_2_ + 0), (p_175044_3_ + 0), 0.0D);
/*  631 */     p_175044_1_.addVertex((p_175044_2_ + 0), (p_175044_3_ + p_175044_5_), 0.0D);
/*  632 */     p_175044_1_.addVertex((p_175044_2_ + p_175044_4_), (p_175044_3_ + p_175044_5_), 0.0D);
/*  633 */     p_175044_1_.addVertex((p_175044_2_ + p_175044_4_), (p_175044_3_ + 0), 0.0D);
/*  634 */     Tessellator.getInstance().draw();
/*      */   }
/*      */ 
/*      */   
/*      */   private void registerItems() {
/*  639 */     registerBlock(Blocks.anvil, "anvil_intact");
/*  640 */     registerBlock(Blocks.anvil, 1, "anvil_slightly_damaged");
/*  641 */     registerBlock(Blocks.anvil, 2, "anvil_very_damaged");
/*  642 */     registerBlock(Blocks.carpet, EnumDyeColor.BLACK.func_176765_a(), "black_carpet");
/*  643 */     registerBlock(Blocks.carpet, EnumDyeColor.BLUE.func_176765_a(), "blue_carpet");
/*  644 */     registerBlock(Blocks.carpet, EnumDyeColor.BROWN.func_176765_a(), "brown_carpet");
/*  645 */     registerBlock(Blocks.carpet, EnumDyeColor.CYAN.func_176765_a(), "cyan_carpet");
/*  646 */     registerBlock(Blocks.carpet, EnumDyeColor.GRAY.func_176765_a(), "gray_carpet");
/*  647 */     registerBlock(Blocks.carpet, EnumDyeColor.GREEN.func_176765_a(), "green_carpet");
/*  648 */     registerBlock(Blocks.carpet, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_carpet");
/*  649 */     registerBlock(Blocks.carpet, EnumDyeColor.LIME.func_176765_a(), "lime_carpet");
/*  650 */     registerBlock(Blocks.carpet, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_carpet");
/*  651 */     registerBlock(Blocks.carpet, EnumDyeColor.ORANGE.func_176765_a(), "orange_carpet");
/*  652 */     registerBlock(Blocks.carpet, EnumDyeColor.PINK.func_176765_a(), "pink_carpet");
/*  653 */     registerBlock(Blocks.carpet, EnumDyeColor.PURPLE.func_176765_a(), "purple_carpet");
/*  654 */     registerBlock(Blocks.carpet, EnumDyeColor.RED.func_176765_a(), "red_carpet");
/*  655 */     registerBlock(Blocks.carpet, EnumDyeColor.SILVER.func_176765_a(), "silver_carpet");
/*  656 */     registerBlock(Blocks.carpet, EnumDyeColor.WHITE.func_176765_a(), "white_carpet");
/*  657 */     registerBlock(Blocks.carpet, EnumDyeColor.YELLOW.func_176765_a(), "yellow_carpet");
/*  658 */     registerBlock(Blocks.cobblestone_wall, BlockWall.EnumType.MOSSY.func_176657_a(), "mossy_cobblestone_wall");
/*  659 */     registerBlock(Blocks.cobblestone_wall, BlockWall.EnumType.NORMAL.func_176657_a(), "cobblestone_wall");
/*  660 */     registerBlock(Blocks.dirt, BlockDirt.DirtType.COARSE_DIRT.getMetadata(), "coarse_dirt");
/*  661 */     registerBlock(Blocks.dirt, BlockDirt.DirtType.DIRT.getMetadata(), "dirt");
/*  662 */     registerBlock(Blocks.dirt, BlockDirt.DirtType.PODZOL.getMetadata(), "podzol");
/*  663 */     registerBlock((Block)Blocks.double_plant, BlockDoublePlant.EnumPlantType.FERN.func_176936_a(), "double_fern");
/*  664 */     registerBlock((Block)Blocks.double_plant, BlockDoublePlant.EnumPlantType.GRASS.func_176936_a(), "double_grass");
/*  665 */     registerBlock((Block)Blocks.double_plant, BlockDoublePlant.EnumPlantType.PAEONIA.func_176936_a(), "paeonia");
/*  666 */     registerBlock((Block)Blocks.double_plant, BlockDoublePlant.EnumPlantType.ROSE.func_176936_a(), "double_rose");
/*  667 */     registerBlock((Block)Blocks.double_plant, BlockDoublePlant.EnumPlantType.SUNFLOWER.func_176936_a(), "sunflower");
/*  668 */     registerBlock((Block)Blocks.double_plant, BlockDoublePlant.EnumPlantType.SYRINGA.func_176936_a(), "syringa");
/*  669 */     registerBlock((Block)Blocks.leaves, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_leaves");
/*  670 */     registerBlock((Block)Blocks.leaves, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_leaves");
/*  671 */     registerBlock((Block)Blocks.leaves, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_leaves");
/*  672 */     registerBlock((Block)Blocks.leaves, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_leaves");
/*  673 */     registerBlock((Block)Blocks.leaves2, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4, "acacia_leaves");
/*  674 */     registerBlock((Block)Blocks.leaves2, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4, "dark_oak_leaves");
/*  675 */     registerBlock(Blocks.log, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_log");
/*  676 */     registerBlock(Blocks.log, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_log");
/*  677 */     registerBlock(Blocks.log, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_log");
/*  678 */     registerBlock(Blocks.log, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_log");
/*  679 */     registerBlock(Blocks.log2, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4, "acacia_log");
/*  680 */     registerBlock(Blocks.log2, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4, "dark_oak_log");
/*  681 */     registerBlock(Blocks.monster_egg, BlockSilverfish.EnumType.CHISELED_STONEBRICK.func_176881_a(), "chiseled_brick_monster_egg");
/*  682 */     registerBlock(Blocks.monster_egg, BlockSilverfish.EnumType.COBBLESTONE.func_176881_a(), "cobblestone_monster_egg");
/*  683 */     registerBlock(Blocks.monster_egg, BlockSilverfish.EnumType.CRACKED_STONEBRICK.func_176881_a(), "cracked_brick_monster_egg");
/*  684 */     registerBlock(Blocks.monster_egg, BlockSilverfish.EnumType.MOSSY_STONEBRICK.func_176881_a(), "mossy_brick_monster_egg");
/*  685 */     registerBlock(Blocks.monster_egg, BlockSilverfish.EnumType.STONE.func_176881_a(), "stone_monster_egg");
/*  686 */     registerBlock(Blocks.monster_egg, BlockSilverfish.EnumType.STONEBRICK.func_176881_a(), "stone_brick_monster_egg");
/*  687 */     registerBlock(Blocks.planks, BlockPlanks.EnumType.ACACIA.func_176839_a(), "acacia_planks");
/*  688 */     registerBlock(Blocks.planks, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_planks");
/*  689 */     registerBlock(Blocks.planks, BlockPlanks.EnumType.DARK_OAK.func_176839_a(), "dark_oak_planks");
/*  690 */     registerBlock(Blocks.planks, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_planks");
/*  691 */     registerBlock(Blocks.planks, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_planks");
/*  692 */     registerBlock(Blocks.planks, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_planks");
/*  693 */     registerBlock(Blocks.prismarine, BlockPrismarine.EnumType.BRICKS.getMetadata(), "prismarine_bricks");
/*  694 */     registerBlock(Blocks.prismarine, BlockPrismarine.EnumType.DARK.getMetadata(), "dark_prismarine");
/*  695 */     registerBlock(Blocks.prismarine, BlockPrismarine.EnumType.ROUGH.getMetadata(), "prismarine");
/*  696 */     registerBlock(Blocks.quartz_block, BlockQuartz.EnumType.CHISELED.getMetaFromState(), "chiseled_quartz_block");
/*  697 */     registerBlock(Blocks.quartz_block, BlockQuartz.EnumType.DEFAULT.getMetaFromState(), "quartz_block");
/*  698 */     registerBlock(Blocks.quartz_block, BlockQuartz.EnumType.LINES_Y.getMetaFromState(), "quartz_column");
/*  699 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.ALLIUM.func_176968_b(), "allium");
/*  700 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.BLUE_ORCHID.func_176968_b(), "blue_orchid");
/*  701 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.HOUSTONIA.func_176968_b(), "houstonia");
/*  702 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.ORANGE_TULIP.func_176968_b(), "orange_tulip");
/*  703 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.OXEYE_DAISY.func_176968_b(), "oxeye_daisy");
/*  704 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.PINK_TULIP.func_176968_b(), "pink_tulip");
/*  705 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.POPPY.func_176968_b(), "poppy");
/*  706 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.RED_TULIP.func_176968_b(), "red_tulip");
/*  707 */     registerBlock((Block)Blocks.red_flower, BlockFlower.EnumFlowerType.WHITE_TULIP.func_176968_b(), "white_tulip");
/*  708 */     registerBlock((Block)Blocks.sand, BlockSand.EnumType.RED_SAND.func_176688_a(), "red_sand");
/*  709 */     registerBlock((Block)Blocks.sand, BlockSand.EnumType.SAND.func_176688_a(), "sand");
/*  710 */     registerBlock(Blocks.sandstone, BlockSandStone.EnumType.CHISELED.func_176675_a(), "chiseled_sandstone");
/*  711 */     registerBlock(Blocks.sandstone, BlockSandStone.EnumType.DEFAULT.func_176675_a(), "sandstone");
/*  712 */     registerBlock(Blocks.sandstone, BlockSandStone.EnumType.SMOOTH.func_176675_a(), "smooth_sandstone");
/*  713 */     registerBlock(Blocks.red_sandstone, BlockRedSandstone.EnumType.CHISELED.getMetaFromState(), "chiseled_red_sandstone");
/*  714 */     registerBlock(Blocks.red_sandstone, BlockRedSandstone.EnumType.DEFAULT.getMetaFromState(), "red_sandstone");
/*  715 */     registerBlock(Blocks.red_sandstone, BlockRedSandstone.EnumType.SMOOTH.getMetaFromState(), "smooth_red_sandstone");
/*  716 */     registerBlock(Blocks.sapling, BlockPlanks.EnumType.ACACIA.func_176839_a(), "acacia_sapling");
/*  717 */     registerBlock(Blocks.sapling, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_sapling");
/*  718 */     registerBlock(Blocks.sapling, BlockPlanks.EnumType.DARK_OAK.func_176839_a(), "dark_oak_sapling");
/*  719 */     registerBlock(Blocks.sapling, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_sapling");
/*  720 */     registerBlock(Blocks.sapling, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_sapling");
/*  721 */     registerBlock(Blocks.sapling, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_sapling");
/*  722 */     registerBlock(Blocks.sponge, 0, "sponge");
/*  723 */     registerBlock(Blocks.sponge, 1, "sponge_wet");
/*  724 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.BLACK.func_176765_a(), "black_stained_glass");
/*  725 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.BLUE.func_176765_a(), "blue_stained_glass");
/*  726 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.BROWN.func_176765_a(), "brown_stained_glass");
/*  727 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.CYAN.func_176765_a(), "cyan_stained_glass");
/*  728 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.GRAY.func_176765_a(), "gray_stained_glass");
/*  729 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.GREEN.func_176765_a(), "green_stained_glass");
/*  730 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_stained_glass");
/*  731 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.LIME.func_176765_a(), "lime_stained_glass");
/*  732 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_stained_glass");
/*  733 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.ORANGE.func_176765_a(), "orange_stained_glass");
/*  734 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.PINK.func_176765_a(), "pink_stained_glass");
/*  735 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.PURPLE.func_176765_a(), "purple_stained_glass");
/*  736 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.RED.func_176765_a(), "red_stained_glass");
/*  737 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.SILVER.func_176765_a(), "silver_stained_glass");
/*  738 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.WHITE.func_176765_a(), "white_stained_glass");
/*  739 */     registerBlock((Block)Blocks.stained_glass, EnumDyeColor.YELLOW.func_176765_a(), "yellow_stained_glass");
/*  740 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.BLACK.func_176765_a(), "black_stained_glass_pane");
/*  741 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.BLUE.func_176765_a(), "blue_stained_glass_pane");
/*  742 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.BROWN.func_176765_a(), "brown_stained_glass_pane");
/*  743 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.CYAN.func_176765_a(), "cyan_stained_glass_pane");
/*  744 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.GRAY.func_176765_a(), "gray_stained_glass_pane");
/*  745 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.GREEN.func_176765_a(), "green_stained_glass_pane");
/*  746 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_stained_glass_pane");
/*  747 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.LIME.func_176765_a(), "lime_stained_glass_pane");
/*  748 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_stained_glass_pane");
/*  749 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.ORANGE.func_176765_a(), "orange_stained_glass_pane");
/*  750 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.PINK.func_176765_a(), "pink_stained_glass_pane");
/*  751 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.PURPLE.func_176765_a(), "purple_stained_glass_pane");
/*  752 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.RED.func_176765_a(), "red_stained_glass_pane");
/*  753 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.SILVER.func_176765_a(), "silver_stained_glass_pane");
/*  754 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.WHITE.func_176765_a(), "white_stained_glass_pane");
/*  755 */     registerBlock((Block)Blocks.stained_glass_pane, EnumDyeColor.YELLOW.func_176765_a(), "yellow_stained_glass_pane");
/*  756 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.BLACK.func_176765_a(), "black_stained_hardened_clay");
/*  757 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.BLUE.func_176765_a(), "blue_stained_hardened_clay");
/*  758 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.BROWN.func_176765_a(), "brown_stained_hardened_clay");
/*  759 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.CYAN.func_176765_a(), "cyan_stained_hardened_clay");
/*  760 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.GRAY.func_176765_a(), "gray_stained_hardened_clay");
/*  761 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.GREEN.func_176765_a(), "green_stained_hardened_clay");
/*  762 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_stained_hardened_clay");
/*  763 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.LIME.func_176765_a(), "lime_stained_hardened_clay");
/*  764 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_stained_hardened_clay");
/*  765 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.ORANGE.func_176765_a(), "orange_stained_hardened_clay");
/*  766 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.PINK.func_176765_a(), "pink_stained_hardened_clay");
/*  767 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.PURPLE.func_176765_a(), "purple_stained_hardened_clay");
/*  768 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.RED.func_176765_a(), "red_stained_hardened_clay");
/*  769 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.SILVER.func_176765_a(), "silver_stained_hardened_clay");
/*  770 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.WHITE.func_176765_a(), "white_stained_hardened_clay");
/*  771 */     registerBlock(Blocks.stained_hardened_clay, EnumDyeColor.YELLOW.func_176765_a(), "yellow_stained_hardened_clay");
/*  772 */     registerBlock(Blocks.stone, BlockStone.EnumType.ANDESITE.getMetaFromState(), "andesite");
/*  773 */     registerBlock(Blocks.stone, BlockStone.EnumType.ANDESITE_SMOOTH.getMetaFromState(), "andesite_smooth");
/*  774 */     registerBlock(Blocks.stone, BlockStone.EnumType.DIORITE.getMetaFromState(), "diorite");
/*  775 */     registerBlock(Blocks.stone, BlockStone.EnumType.DIORITE_SMOOTH.getMetaFromState(), "diorite_smooth");
/*  776 */     registerBlock(Blocks.stone, BlockStone.EnumType.GRANITE.getMetaFromState(), "granite");
/*  777 */     registerBlock(Blocks.stone, BlockStone.EnumType.GRANITE_SMOOTH.getMetaFromState(), "granite_smooth");
/*  778 */     registerBlock(Blocks.stone, BlockStone.EnumType.STONE.getMetaFromState(), "stone");
/*  779 */     registerBlock(Blocks.stonebrick, BlockStoneBrick.EnumType.CRACKED.getMetaFromState(), "cracked_stonebrick");
/*  780 */     registerBlock(Blocks.stonebrick, BlockStoneBrick.EnumType.DEFAULT.getMetaFromState(), "stonebrick");
/*  781 */     registerBlock(Blocks.stonebrick, BlockStoneBrick.EnumType.CHISELED.getMetaFromState(), "chiseled_stonebrick");
/*  782 */     registerBlock(Blocks.stonebrick, BlockStoneBrick.EnumType.MOSSY.getMetaFromState(), "mossy_stonebrick");
/*  783 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.BRICK.func_176624_a(), "brick_slab");
/*  784 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.COBBLESTONE.func_176624_a(), "cobblestone_slab");
/*  785 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.WOOD.func_176624_a(), "old_wood_slab");
/*  786 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.NETHERBRICK.func_176624_a(), "nether_brick_slab");
/*  787 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.QUARTZ.func_176624_a(), "quartz_slab");
/*  788 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.SAND.func_176624_a(), "sandstone_slab");
/*  789 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a(), "stone_brick_slab");
/*  790 */     registerBlock((Block)Blocks.stone_slab, BlockStoneSlab.EnumType.STONE.func_176624_a(), "stone_slab");
/*  791 */     registerBlock((Block)Blocks.stone_slab2, BlockStoneSlabNew.EnumType.RED_SANDSTONE.func_176915_a(), "red_sandstone_slab");
/*  792 */     registerBlock((Block)Blocks.tallgrass, BlockTallGrass.EnumType.DEAD_BUSH.func_177044_a(), "dead_bush");
/*  793 */     registerBlock((Block)Blocks.tallgrass, BlockTallGrass.EnumType.FERN.func_177044_a(), "fern");
/*  794 */     registerBlock((Block)Blocks.tallgrass, BlockTallGrass.EnumType.GRASS.func_177044_a(), "tall_grass");
/*  795 */     registerBlock((Block)Blocks.wooden_slab, BlockPlanks.EnumType.ACACIA.func_176839_a(), "acacia_slab");
/*  796 */     registerBlock((Block)Blocks.wooden_slab, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_slab");
/*  797 */     registerBlock((Block)Blocks.wooden_slab, BlockPlanks.EnumType.DARK_OAK.func_176839_a(), "dark_oak_slab");
/*  798 */     registerBlock((Block)Blocks.wooden_slab, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_slab");
/*  799 */     registerBlock((Block)Blocks.wooden_slab, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_slab");
/*  800 */     registerBlock((Block)Blocks.wooden_slab, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_slab");
/*  801 */     registerBlock(Blocks.wool, EnumDyeColor.BLACK.func_176765_a(), "black_wool");
/*  802 */     registerBlock(Blocks.wool, EnumDyeColor.BLUE.func_176765_a(), "blue_wool");
/*  803 */     registerBlock(Blocks.wool, EnumDyeColor.BROWN.func_176765_a(), "brown_wool");
/*  804 */     registerBlock(Blocks.wool, EnumDyeColor.CYAN.func_176765_a(), "cyan_wool");
/*  805 */     registerBlock(Blocks.wool, EnumDyeColor.GRAY.func_176765_a(), "gray_wool");
/*  806 */     registerBlock(Blocks.wool, EnumDyeColor.GREEN.func_176765_a(), "green_wool");
/*  807 */     registerBlock(Blocks.wool, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_wool");
/*  808 */     registerBlock(Blocks.wool, EnumDyeColor.LIME.func_176765_a(), "lime_wool");
/*  809 */     registerBlock(Blocks.wool, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_wool");
/*  810 */     registerBlock(Blocks.wool, EnumDyeColor.ORANGE.func_176765_a(), "orange_wool");
/*  811 */     registerBlock(Blocks.wool, EnumDyeColor.PINK.func_176765_a(), "pink_wool");
/*  812 */     registerBlock(Blocks.wool, EnumDyeColor.PURPLE.func_176765_a(), "purple_wool");
/*  813 */     registerBlock(Blocks.wool, EnumDyeColor.RED.func_176765_a(), "red_wool");
/*  814 */     registerBlock(Blocks.wool, EnumDyeColor.SILVER.func_176765_a(), "silver_wool");
/*  815 */     registerBlock(Blocks.wool, EnumDyeColor.WHITE.func_176765_a(), "white_wool");
/*  816 */     registerBlock(Blocks.wool, EnumDyeColor.YELLOW.func_176765_a(), "yellow_wool");
/*  817 */     registerBlock(Blocks.acacia_stairs, "acacia_stairs");
/*  818 */     registerBlock(Blocks.activator_rail, "activator_rail");
/*  819 */     registerBlock((Block)Blocks.beacon, "beacon");
/*  820 */     registerBlock(Blocks.bedrock, "bedrock");
/*  821 */     registerBlock(Blocks.birch_stairs, "birch_stairs");
/*  822 */     registerBlock(Blocks.bookshelf, "bookshelf");
/*  823 */     registerBlock(Blocks.brick_block, "brick_block");
/*  824 */     registerBlock(Blocks.brick_block, "brick_block");
/*  825 */     registerBlock(Blocks.brick_stairs, "brick_stairs");
/*  826 */     registerBlock((Block)Blocks.brown_mushroom, "brown_mushroom");
/*  827 */     registerBlock((Block)Blocks.cactus, "cactus");
/*  828 */     registerBlock(Blocks.clay, "clay");
/*  829 */     registerBlock(Blocks.coal_block, "coal_block");
/*  830 */     registerBlock(Blocks.coal_ore, "coal_ore");
/*  831 */     registerBlock(Blocks.cobblestone, "cobblestone");
/*  832 */     registerBlock(Blocks.crafting_table, "crafting_table");
/*  833 */     registerBlock(Blocks.dark_oak_stairs, "dark_oak_stairs");
/*  834 */     registerBlock((Block)Blocks.daylight_detector, "daylight_detector");
/*  835 */     registerBlock((Block)Blocks.deadbush, "dead_bush");
/*  836 */     registerBlock(Blocks.detector_rail, "detector_rail");
/*  837 */     registerBlock(Blocks.diamond_block, "diamond_block");
/*  838 */     registerBlock(Blocks.diamond_ore, "diamond_ore");
/*  839 */     registerBlock(Blocks.dispenser, "dispenser");
/*  840 */     registerBlock(Blocks.dropper, "dropper");
/*  841 */     registerBlock(Blocks.emerald_block, "emerald_block");
/*  842 */     registerBlock(Blocks.emerald_ore, "emerald_ore");
/*  843 */     registerBlock(Blocks.enchanting_table, "enchanting_table");
/*  844 */     registerBlock(Blocks.end_portal_frame, "end_portal_frame");
/*  845 */     registerBlock(Blocks.end_stone, "end_stone");
/*  846 */     registerBlock(Blocks.oak_fence, "oak_fence");
/*  847 */     registerBlock(Blocks.spruce_fence, "spruce_fence");
/*  848 */     registerBlock(Blocks.birch_fence, "birch_fence");
/*  849 */     registerBlock(Blocks.jungle_fence, "jungle_fence");
/*  850 */     registerBlock(Blocks.dark_oak_fence, "dark_oak_fence");
/*  851 */     registerBlock(Blocks.acacia_fence, "acacia_fence");
/*  852 */     registerBlock(Blocks.oak_fence_gate, "oak_fence_gate");
/*  853 */     registerBlock(Blocks.spruce_fence_gate, "spruce_fence_gate");
/*  854 */     registerBlock(Blocks.birch_fence_gate, "birch_fence_gate");
/*  855 */     registerBlock(Blocks.jungle_fence_gate, "jungle_fence_gate");
/*  856 */     registerBlock(Blocks.dark_oak_fence_gate, "dark_oak_fence_gate");
/*  857 */     registerBlock(Blocks.acacia_fence_gate, "acacia_fence_gate");
/*  858 */     registerBlock(Blocks.furnace, "furnace");
/*  859 */     registerBlock(Blocks.glass, "glass");
/*  860 */     registerBlock(Blocks.glass_pane, "glass_pane");
/*  861 */     registerBlock(Blocks.glowstone, "glowstone");
/*  862 */     registerBlock(Blocks.golden_rail, "golden_rail");
/*  863 */     registerBlock(Blocks.gold_block, "gold_block");
/*  864 */     registerBlock(Blocks.gold_ore, "gold_ore");
/*  865 */     registerBlock((Block)Blocks.grass, "grass");
/*  866 */     registerBlock(Blocks.gravel, "gravel");
/*  867 */     registerBlock(Blocks.hardened_clay, "hardened_clay");
/*  868 */     registerBlock(Blocks.hay_block, "hay_block");
/*  869 */     registerBlock(Blocks.heavy_weighted_pressure_plate, "heavy_weighted_pressure_plate");
/*  870 */     registerBlock((Block)Blocks.hopper, "hopper");
/*  871 */     registerBlock(Blocks.ice, "ice");
/*  872 */     registerBlock(Blocks.iron_bars, "iron_bars");
/*  873 */     registerBlock(Blocks.iron_block, "iron_block");
/*  874 */     registerBlock(Blocks.iron_ore, "iron_ore");
/*  875 */     registerBlock(Blocks.iron_trapdoor, "iron_trapdoor");
/*  876 */     registerBlock(Blocks.jukebox, "jukebox");
/*  877 */     registerBlock(Blocks.jungle_stairs, "jungle_stairs");
/*  878 */     registerBlock(Blocks.ladder, "ladder");
/*  879 */     registerBlock(Blocks.lapis_block, "lapis_block");
/*  880 */     registerBlock(Blocks.lapis_ore, "lapis_ore");
/*  881 */     registerBlock(Blocks.lever, "lever");
/*  882 */     registerBlock(Blocks.light_weighted_pressure_plate, "light_weighted_pressure_plate");
/*  883 */     registerBlock(Blocks.lit_pumpkin, "lit_pumpkin");
/*  884 */     registerBlock(Blocks.melon_block, "melon_block");
/*  885 */     registerBlock(Blocks.mossy_cobblestone, "mossy_cobblestone");
/*  886 */     registerBlock((Block)Blocks.mycelium, "mycelium");
/*  887 */     registerBlock(Blocks.netherrack, "netherrack");
/*  888 */     registerBlock(Blocks.nether_brick, "nether_brick");
/*  889 */     registerBlock(Blocks.nether_brick_fence, "nether_brick_fence");
/*  890 */     registerBlock(Blocks.nether_brick_stairs, "nether_brick_stairs");
/*  891 */     registerBlock(Blocks.noteblock, "noteblock");
/*  892 */     registerBlock(Blocks.oak_stairs, "oak_stairs");
/*  893 */     registerBlock(Blocks.obsidian, "obsidian");
/*  894 */     registerBlock(Blocks.packed_ice, "packed_ice");
/*  895 */     registerBlock((Block)Blocks.piston, "piston");
/*  896 */     registerBlock(Blocks.pumpkin, "pumpkin");
/*  897 */     registerBlock(Blocks.quartz_ore, "quartz_ore");
/*  898 */     registerBlock(Blocks.quartz_stairs, "quartz_stairs");
/*  899 */     registerBlock(Blocks.rail, "rail");
/*  900 */     registerBlock(Blocks.redstone_block, "redstone_block");
/*  901 */     registerBlock(Blocks.redstone_lamp, "redstone_lamp");
/*  902 */     registerBlock(Blocks.redstone_ore, "redstone_ore");
/*  903 */     registerBlock(Blocks.redstone_torch, "redstone_torch");
/*  904 */     registerBlock((Block)Blocks.red_mushroom, "red_mushroom");
/*  905 */     registerBlock(Blocks.sandstone_stairs, "sandstone_stairs");
/*  906 */     registerBlock(Blocks.red_sandstone_stairs, "red_sandstone_stairs");
/*  907 */     registerBlock(Blocks.sea_lantern, "sea_lantern");
/*  908 */     registerBlock(Blocks.slime_block, "slime");
/*  909 */     registerBlock(Blocks.snow, "snow");
/*  910 */     registerBlock(Blocks.snow_layer, "snow_layer");
/*  911 */     registerBlock(Blocks.soul_sand, "soul_sand");
/*  912 */     registerBlock(Blocks.spruce_stairs, "spruce_stairs");
/*  913 */     registerBlock((Block)Blocks.sticky_piston, "sticky_piston");
/*  914 */     registerBlock(Blocks.stone_brick_stairs, "stone_brick_stairs");
/*  915 */     registerBlock(Blocks.stone_button, "stone_button");
/*  916 */     registerBlock(Blocks.stone_pressure_plate, "stone_pressure_plate");
/*  917 */     registerBlock(Blocks.stone_stairs, "stone_stairs");
/*  918 */     registerBlock(Blocks.tnt, "tnt");
/*  919 */     registerBlock(Blocks.torch, "torch");
/*  920 */     registerBlock(Blocks.trapdoor, "trapdoor");
/*  921 */     registerBlock((Block)Blocks.tripwire_hook, "tripwire_hook");
/*  922 */     registerBlock(Blocks.vine, "vine");
/*  923 */     registerBlock(Blocks.waterlily, "waterlily");
/*  924 */     registerBlock(Blocks.web, "web");
/*  925 */     registerBlock(Blocks.wooden_button, "wooden_button");
/*  926 */     registerBlock(Blocks.wooden_pressure_plate, "wooden_pressure_plate");
/*  927 */     registerBlock((Block)Blocks.yellow_flower, BlockFlower.EnumFlowerType.DANDELION.func_176968_b(), "dandelion");
/*  928 */     registerBlock((Block)Blocks.chest, "chest");
/*  929 */     registerBlock(Blocks.trapped_chest, "trapped_chest");
/*  930 */     registerBlock(Blocks.ender_chest, "ender_chest");
/*  931 */     registerItem(Items.iron_shovel, "iron_shovel");
/*  932 */     registerItem(Items.iron_pickaxe, "iron_pickaxe");
/*  933 */     registerItem(Items.iron_axe, "iron_axe");
/*  934 */     registerItem(Items.flint_and_steel, "flint_and_steel");
/*  935 */     registerItem(Items.apple, "apple");
/*  936 */     registerItem((Item)Items.bow, 0, "bow");
/*  937 */     registerItem((Item)Items.bow, 1, "bow_pulling_0");
/*  938 */     registerItem((Item)Items.bow, 2, "bow_pulling_1");
/*  939 */     registerItem((Item)Items.bow, 3, "bow_pulling_2");
/*  940 */     registerItem(Items.arrow, "arrow");
/*  941 */     registerItem(Items.coal, 0, "coal");
/*  942 */     registerItem(Items.coal, 1, "charcoal");
/*  943 */     registerItem(Items.diamond, "diamond");
/*  944 */     registerItem(Items.iron_ingot, "iron_ingot");
/*  945 */     registerItem(Items.gold_ingot, "gold_ingot");
/*  946 */     registerItem(Items.iron_sword, "iron_sword");
/*  947 */     registerItem(Items.wooden_sword, "wooden_sword");
/*  948 */     registerItem(Items.wooden_shovel, "wooden_shovel");
/*  949 */     registerItem(Items.wooden_pickaxe, "wooden_pickaxe");
/*  950 */     registerItem(Items.wooden_axe, "wooden_axe");
/*  951 */     registerItem(Items.stone_sword, "stone_sword");
/*  952 */     registerItem(Items.stone_shovel, "stone_shovel");
/*  953 */     registerItem(Items.stone_pickaxe, "stone_pickaxe");
/*  954 */     registerItem(Items.stone_axe, "stone_axe");
/*  955 */     registerItem(Items.diamond_sword, "diamond_sword");
/*  956 */     registerItem(Items.diamond_shovel, "diamond_shovel");
/*  957 */     registerItem(Items.diamond_pickaxe, "diamond_pickaxe");
/*  958 */     registerItem(Items.diamond_axe, "diamond_axe");
/*  959 */     registerItem(Items.stick, "stick");
/*  960 */     registerItem(Items.bowl, "bowl");
/*  961 */     registerItem(Items.mushroom_stew, "mushroom_stew");
/*  962 */     registerItem(Items.golden_sword, "golden_sword");
/*  963 */     registerItem(Items.golden_shovel, "golden_shovel");
/*  964 */     registerItem(Items.golden_pickaxe, "golden_pickaxe");
/*  965 */     registerItem(Items.golden_axe, "golden_axe");
/*  966 */     registerItem(Items.string, "string");
/*  967 */     registerItem(Items.feather, "feather");
/*  968 */     registerItem(Items.gunpowder, "gunpowder");
/*  969 */     registerItem(Items.wooden_hoe, "wooden_hoe");
/*  970 */     registerItem(Items.stone_hoe, "stone_hoe");
/*  971 */     registerItem(Items.iron_hoe, "iron_hoe");
/*  972 */     registerItem(Items.diamond_hoe, "diamond_hoe");
/*  973 */     registerItem(Items.golden_hoe, "golden_hoe");
/*  974 */     registerItem(Items.wheat_seeds, "wheat_seeds");
/*  975 */     registerItem(Items.wheat, "wheat");
/*  976 */     registerItem(Items.bread, "bread");
/*  977 */     registerItem((Item)Items.leather_helmet, "leather_helmet");
/*  978 */     registerItem((Item)Items.leather_chestplate, "leather_chestplate");
/*  979 */     registerItem((Item)Items.leather_leggings, "leather_leggings");
/*  980 */     registerItem((Item)Items.leather_boots, "leather_boots");
/*  981 */     registerItem((Item)Items.chainmail_helmet, "chainmail_helmet");
/*  982 */     registerItem((Item)Items.chainmail_chestplate, "chainmail_chestplate");
/*  983 */     registerItem((Item)Items.chainmail_leggings, "chainmail_leggings");
/*  984 */     registerItem((Item)Items.chainmail_boots, "chainmail_boots");
/*  985 */     registerItem((Item)Items.iron_helmet, "iron_helmet");
/*  986 */     registerItem((Item)Items.iron_chestplate, "iron_chestplate");
/*  987 */     registerItem((Item)Items.iron_leggings, "iron_leggings");
/*  988 */     registerItem((Item)Items.iron_boots, "iron_boots");
/*  989 */     registerItem((Item)Items.diamond_helmet, "diamond_helmet");
/*  990 */     registerItem((Item)Items.diamond_chestplate, "diamond_chestplate");
/*  991 */     registerItem((Item)Items.diamond_leggings, "diamond_leggings");
/*  992 */     registerItem((Item)Items.diamond_boots, "diamond_boots");
/*  993 */     registerItem((Item)Items.golden_helmet, "golden_helmet");
/*  994 */     registerItem((Item)Items.golden_chestplate, "golden_chestplate");
/*  995 */     registerItem((Item)Items.golden_leggings, "golden_leggings");
/*  996 */     registerItem((Item)Items.golden_boots, "golden_boots");
/*  997 */     registerItem(Items.flint, "flint");
/*  998 */     registerItem(Items.porkchop, "porkchop");
/*  999 */     registerItem(Items.cooked_porkchop, "cooked_porkchop");
/* 1000 */     registerItem(Items.painting, "painting");
/* 1001 */     registerItem(Items.golden_apple, "golden_apple");
/* 1002 */     registerItem(Items.golden_apple, 1, "golden_apple");
/* 1003 */     registerItem(Items.sign, "sign");
/* 1004 */     registerItem(Items.oak_door, "oak_door");
/* 1005 */     registerItem(Items.spruce_door, "spruce_door");
/* 1006 */     registerItem(Items.birch_door, "birch_door");
/* 1007 */     registerItem(Items.jungle_door, "jungle_door");
/* 1008 */     registerItem(Items.acacia_door, "acacia_door");
/* 1009 */     registerItem(Items.dark_oak_door, "dark_oak_door");
/* 1010 */     registerItem(Items.bucket, "bucket");
/* 1011 */     registerItem(Items.water_bucket, "water_bucket");
/* 1012 */     registerItem(Items.lava_bucket, "lava_bucket");
/* 1013 */     registerItem(Items.minecart, "minecart");
/* 1014 */     registerItem(Items.saddle, "saddle");
/* 1015 */     registerItem(Items.iron_door, "iron_door");
/* 1016 */     registerItem(Items.redstone, "redstone");
/* 1017 */     registerItem(Items.snowball, "snowball");
/* 1018 */     registerItem(Items.boat, "boat");
/* 1019 */     registerItem(Items.leather, "leather");
/* 1020 */     registerItem(Items.milk_bucket, "milk_bucket");
/* 1021 */     registerItem(Items.brick, "brick");
/* 1022 */     registerItem(Items.clay_ball, "clay_ball");
/* 1023 */     registerItem(Items.reeds, "reeds");
/* 1024 */     registerItem(Items.paper, "paper");
/* 1025 */     registerItem(Items.book, "book");
/* 1026 */     registerItem(Items.slime_ball, "slime_ball");
/* 1027 */     registerItem(Items.chest_minecart, "chest_minecart");
/* 1028 */     registerItem(Items.furnace_minecart, "furnace_minecart");
/* 1029 */     registerItem(Items.egg, "egg");
/* 1030 */     registerItem(Items.compass, "compass");
/* 1031 */     registerItem((Item)Items.fishing_rod, "fishing_rod");
/* 1032 */     registerItem((Item)Items.fishing_rod, 1, "fishing_rod_cast");
/* 1033 */     registerItem(Items.clock, "clock");
/* 1034 */     registerItem(Items.glowstone_dust, "glowstone_dust");
/* 1035 */     registerItem(Items.fish, ItemFishFood.FishType.COD.getItemDamage(), "cod");
/* 1036 */     registerItem(Items.fish, ItemFishFood.FishType.SALMON.getItemDamage(), "salmon");
/* 1037 */     registerItem(Items.fish, ItemFishFood.FishType.CLOWNFISH.getItemDamage(), "clownfish");
/* 1038 */     registerItem(Items.fish, ItemFishFood.FishType.PUFFERFISH.getItemDamage(), "pufferfish");
/* 1039 */     registerItem(Items.cooked_fish, ItemFishFood.FishType.COD.getItemDamage(), "cooked_cod");
/* 1040 */     registerItem(Items.cooked_fish, ItemFishFood.FishType.SALMON.getItemDamage(), "cooked_salmon");
/* 1041 */     registerItem(Items.dye, EnumDyeColor.BLACK.getDyeColorDamage(), "dye_black");
/* 1042 */     registerItem(Items.dye, EnumDyeColor.RED.getDyeColorDamage(), "dye_red");
/* 1043 */     registerItem(Items.dye, EnumDyeColor.GREEN.getDyeColorDamage(), "dye_green");
/* 1044 */     registerItem(Items.dye, EnumDyeColor.BROWN.getDyeColorDamage(), "dye_brown");
/* 1045 */     registerItem(Items.dye, EnumDyeColor.BLUE.getDyeColorDamage(), "dye_blue");
/* 1046 */     registerItem(Items.dye, EnumDyeColor.PURPLE.getDyeColorDamage(), "dye_purple");
/* 1047 */     registerItem(Items.dye, EnumDyeColor.CYAN.getDyeColorDamage(), "dye_cyan");
/* 1048 */     registerItem(Items.dye, EnumDyeColor.SILVER.getDyeColorDamage(), "dye_silver");
/* 1049 */     registerItem(Items.dye, EnumDyeColor.GRAY.getDyeColorDamage(), "dye_gray");
/* 1050 */     registerItem(Items.dye, EnumDyeColor.PINK.getDyeColorDamage(), "dye_pink");
/* 1051 */     registerItem(Items.dye, EnumDyeColor.LIME.getDyeColorDamage(), "dye_lime");
/* 1052 */     registerItem(Items.dye, EnumDyeColor.YELLOW.getDyeColorDamage(), "dye_yellow");
/* 1053 */     registerItem(Items.dye, EnumDyeColor.LIGHT_BLUE.getDyeColorDamage(), "dye_light_blue");
/* 1054 */     registerItem(Items.dye, EnumDyeColor.MAGENTA.getDyeColorDamage(), "dye_magenta");
/* 1055 */     registerItem(Items.dye, EnumDyeColor.ORANGE.getDyeColorDamage(), "dye_orange");
/* 1056 */     registerItem(Items.dye, EnumDyeColor.WHITE.getDyeColorDamage(), "dye_white");
/* 1057 */     registerItem(Items.bone, "bone");
/* 1058 */     registerItem(Items.sugar, "sugar");
/* 1059 */     registerItem(Items.cake, "cake");
/* 1060 */     registerItem(Items.bed, "bed");
/* 1061 */     registerItem(Items.repeater, "repeater");
/* 1062 */     registerItem(Items.cookie, "cookie");
/* 1063 */     registerItem((Item)Items.shears, "shears");
/* 1064 */     registerItem(Items.melon, "melon");
/* 1065 */     registerItem(Items.pumpkin_seeds, "pumpkin_seeds");
/* 1066 */     registerItem(Items.melon_seeds, "melon_seeds");
/* 1067 */     registerItem(Items.beef, "beef");
/* 1068 */     registerItem(Items.cooked_beef, "cooked_beef");
/* 1069 */     registerItem(Items.chicken, "chicken");
/* 1070 */     registerItem(Items.cooked_chicken, "cooked_chicken");
/* 1071 */     registerItem(Items.rabbit, "rabbit");
/* 1072 */     registerItem(Items.cooked_rabbit, "cooked_rabbit");
/* 1073 */     registerItem(Items.mutton, "mutton");
/* 1074 */     registerItem(Items.cooked_mutton, "cooked_mutton");
/* 1075 */     registerItem(Items.rabbit_foot, "rabbit_foot");
/* 1076 */     registerItem(Items.rabbit_hide, "rabbit_hide");
/* 1077 */     registerItem(Items.rabbit_stew, "rabbit_stew");
/* 1078 */     registerItem(Items.rotten_flesh, "rotten_flesh");
/* 1079 */     registerItem(Items.ender_pearl, "ender_pearl");
/* 1080 */     registerItem(Items.blaze_rod, "blaze_rod");
/* 1081 */     registerItem(Items.ghast_tear, "ghast_tear");
/* 1082 */     registerItem(Items.gold_nugget, "gold_nugget");
/* 1083 */     registerItem(Items.nether_wart, "nether_wart");
/* 1084 */     this.itemModelMesher.register((Item)Items.potionitem, new ItemMeshDefinition()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002440";
/*      */           
/*      */           public ModelResourceLocation getModelLocation(ItemStack p_178113_1_) {
/* 1089 */             return ItemPotion.isSplash(p_178113_1_.getMetadata()) ? new ModelResourceLocation("bottle_splash", "inventory") : new ModelResourceLocation("bottle_drinkable", "inventory");
/*      */           }
/*      */         });
/* 1092 */     registerItem(Items.glass_bottle, "glass_bottle");
/* 1093 */     registerItem(Items.spider_eye, "spider_eye");
/* 1094 */     registerItem(Items.fermented_spider_eye, "fermented_spider_eye");
/* 1095 */     registerItem(Items.blaze_powder, "blaze_powder");
/* 1096 */     registerItem(Items.magma_cream, "magma_cream");
/* 1097 */     registerItem(Items.brewing_stand, "brewing_stand");
/* 1098 */     registerItem(Items.cauldron, "cauldron");
/* 1099 */     registerItem(Items.ender_eye, "ender_eye");
/* 1100 */     registerItem(Items.speckled_melon, "speckled_melon");
/* 1101 */     this.itemModelMesher.register(Items.spawn_egg, new ItemMeshDefinition()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002439";
/*      */           
/*      */           public ModelResourceLocation getModelLocation(ItemStack p_178113_1_) {
/* 1106 */             return new ModelResourceLocation("spawn_egg", "inventory");
/*      */           }
/*      */         });
/* 1109 */     registerItem(Items.experience_bottle, "experience_bottle");
/* 1110 */     registerItem(Items.fire_charge, "fire_charge");
/* 1111 */     registerItem(Items.writable_book, "writable_book");
/* 1112 */     registerItem(Items.emerald, "emerald");
/* 1113 */     registerItem(Items.item_frame, "item_frame");
/* 1114 */     registerItem(Items.flower_pot, "flower_pot");
/* 1115 */     registerItem(Items.carrot, "carrot");
/* 1116 */     registerItem(Items.potato, "potato");
/* 1117 */     registerItem(Items.baked_potato, "baked_potato");
/* 1118 */     registerItem(Items.poisonous_potato, "poisonous_potato");
/* 1119 */     registerItem((Item)Items.map, "map");
/* 1120 */     registerItem(Items.golden_carrot, "golden_carrot");
/* 1121 */     registerItem(Items.skull, 0, "skull_skeleton");
/* 1122 */     registerItem(Items.skull, 1, "skull_wither");
/* 1123 */     registerItem(Items.skull, 2, "skull_zombie");
/* 1124 */     registerItem(Items.skull, 3, "skull_char");
/* 1125 */     registerItem(Items.skull, 4, "skull_creeper");
/* 1126 */     registerItem(Items.carrot_on_a_stick, "carrot_on_a_stick");
/* 1127 */     registerItem(Items.nether_star, "nether_star");
/* 1128 */     registerItem(Items.pumpkin_pie, "pumpkin_pie");
/* 1129 */     registerItem(Items.firework_charge, "firework_charge");
/* 1130 */     registerItem(Items.comparator, "comparator");
/* 1131 */     registerItem(Items.netherbrick, "netherbrick");
/* 1132 */     registerItem(Items.quartz, "quartz");
/* 1133 */     registerItem(Items.tnt_minecart, "tnt_minecart");
/* 1134 */     registerItem(Items.hopper_minecart, "hopper_minecart");
/* 1135 */     registerItem((Item)Items.armor_stand, "armor_stand");
/* 1136 */     registerItem(Items.iron_horse_armor, "iron_horse_armor");
/* 1137 */     registerItem(Items.golden_horse_armor, "golden_horse_armor");
/* 1138 */     registerItem(Items.diamond_horse_armor, "diamond_horse_armor");
/* 1139 */     registerItem(Items.lead, "lead");
/* 1140 */     registerItem(Items.name_tag, "name_tag");
/* 1141 */     this.itemModelMesher.register(Items.banner, new ItemMeshDefinition()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002438";
/*      */           
/*      */           public ModelResourceLocation getModelLocation(ItemStack p_178113_1_) {
/* 1146 */             return new ModelResourceLocation("banner", "inventory");
/*      */           }
/*      */         });
/* 1149 */     registerItem(Items.record_13, "record_13");
/* 1150 */     registerItem(Items.record_cat, "record_cat");
/* 1151 */     registerItem(Items.record_blocks, "record_blocks");
/* 1152 */     registerItem(Items.record_chirp, "record_chirp");
/* 1153 */     registerItem(Items.record_far, "record_far");
/* 1154 */     registerItem(Items.record_mall, "record_mall");
/* 1155 */     registerItem(Items.record_mellohi, "record_mellohi");
/* 1156 */     registerItem(Items.record_stal, "record_stal");
/* 1157 */     registerItem(Items.record_strad, "record_strad");
/* 1158 */     registerItem(Items.record_ward, "record_ward");
/* 1159 */     registerItem(Items.record_11, "record_11");
/* 1160 */     registerItem(Items.record_wait, "record_wait");
/* 1161 */     registerItem(Items.prismarine_shard, "prismarine_shard");
/* 1162 */     registerItem(Items.prismarine_crystals, "prismarine_crystals");
/* 1163 */     this.itemModelMesher.register((Item)Items.enchanted_book, new ItemMeshDefinition()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002437";
/*      */           
/*      */           public ModelResourceLocation getModelLocation(ItemStack p_178113_1_) {
/* 1168 */             return new ModelResourceLocation("enchanted_book", "inventory");
/*      */           }
/*      */         });
/* 1171 */     this.itemModelMesher.register((Item)Items.filled_map, new ItemMeshDefinition()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002436";
/*      */           
/*      */           public ModelResourceLocation getModelLocation(ItemStack p_178113_1_) {
/* 1176 */             return new ModelResourceLocation("filled_map", "inventory");
/*      */           }
/*      */         });
/* 1179 */     registerBlock(Blocks.command_block, "command_block");
/* 1180 */     registerItem(Items.fireworks, "fireworks");
/* 1181 */     registerItem(Items.command_block_minecart, "command_block_minecart");
/* 1182 */     registerBlock(Blocks.barrier, "barrier");
/* 1183 */     registerBlock(Blocks.mob_spawner, "mob_spawner");
/* 1184 */     registerItem(Items.written_book, "written_book");
/* 1185 */     registerBlock(Blocks.brown_mushroom_block, BlockHugeMushroom.EnumType.ALL_INSIDE.func_176896_a(), "brown_mushroom_block");
/* 1186 */     registerBlock(Blocks.red_mushroom_block, BlockHugeMushroom.EnumType.ALL_INSIDE.func_176896_a(), "red_mushroom_block");
/* 1187 */     registerBlock(Blocks.dragon_egg, "dragon_egg");
/*      */     
/* 1189 */     if (Reflector.ModelLoader_onRegisterItems.exists())
/*      */     {
/* 1191 */       Reflector.call(Reflector.ModelLoader_onRegisterItems, new Object[] { this.itemModelMesher });
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void onResourceManagerReload(IResourceManager resourceManager) {
/* 1197 */     this.itemModelMesher.rebuildCache();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void forgeHooksClient_putQuadColor(WorldRenderer renderer, BakedQuad quad, int color) {
/* 1202 */     float cr = (color & 0xFF);
/* 1203 */     float cg = (color >>> 8 & 0xFF);
/* 1204 */     float cb = (color >>> 16 & 0xFF);
/* 1205 */     float ca = (color >>> 24 & 0xFF);
/* 1206 */     int[] vd = quad.func_178209_a();
/* 1207 */     int step = vd.length / 4;
/*      */     
/* 1209 */     for (int i = 0; i < 4; i++) {
/*      */       
/* 1211 */       int vc = vd[3 + step * i];
/* 1212 */       float vcr = (vc & 0xFF);
/* 1213 */       float vcg = (vc >>> 8 & 0xFF);
/* 1214 */       float vcb = (vc >>> 16 & 0xFF);
/* 1215 */       float vca = (vc >>> 24 & 0xFF);
/* 1216 */       int ncr = Math.min(255, (int)(cr * vcr / 255.0F));
/* 1217 */       int ncg = Math.min(255, (int)(cg * vcg / 255.0F));
/* 1218 */       int ncb = Math.min(255, (int)(cb * vcb / 255.0F));
/* 1219 */       int nca = Math.min(255, (int)(ca * vca / 255.0F));
/* 1220 */       renderer.func_178972_a(renderer.getGLCallListForPass(4 - i), ncr, ncg, ncb, nca);
/*      */     } 
/*      */   }
/*      */   
/*      */   static final class SwitchTransformType
/*      */   {
/* 1226 */     static final int[] field_178640_a = new int[(ItemCameraTransforms.TransformType.values()).length];
/*      */     
/*      */     private static final String __OBFID = "CL_00002441";
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 1233 */         field_178640_a[ItemCameraTransforms.TransformType.NONE.ordinal()] = 1;
/*      */       }
/* 1235 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1242 */         field_178640_a[ItemCameraTransforms.TransformType.THIRD_PERSON.ordinal()] = 2;
/*      */       }
/* 1244 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1251 */         field_178640_a[ItemCameraTransforms.TransformType.FIRST_PERSON.ordinal()] = 3;
/*      */       }
/* 1253 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1260 */         field_178640_a[ItemCameraTransforms.TransformType.HEAD.ordinal()] = 4;
/*      */       }
/* 1262 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1269 */         field_178640_a[ItemCameraTransforms.TransformType.GUI.ordinal()] = 5;
/*      */       }
/* 1271 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */