/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureCompass;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.storage.MapData;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shadersmod.client.ShadersTex;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderItemFrame
/*     */   extends Render
/*     */ {
/*  39 */   private static final ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");
/*  40 */   private final Minecraft field_147917_g = Minecraft.getMinecraft();
/*  41 */   private final ModelResourceLocation field_177072_f = new ModelResourceLocation("item_frame", "normal");
/*  42 */   private final ModelResourceLocation field_177073_g = new ModelResourceLocation("item_frame", "map");
/*     */   
/*     */   private RenderItem field_177074_h;
/*     */   private static final String __OBFID = "CL_00001002";
/*     */   
/*     */   public RenderItemFrame(RenderManager p_i46166_1_, RenderItem p_i46166_2_) {
/*  48 */     super(p_i46166_1_);
/*  49 */     this.field_177074_h = p_i46166_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityItemFrame p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*     */     IBakedModel var19;
/*  60 */     GlStateManager.pushMatrix();
/*  61 */     BlockPos var10 = p_76986_1_.func_174857_n();
/*  62 */     double var11 = var10.getX() - p_76986_1_.posX + p_76986_2_;
/*  63 */     double var13 = var10.getY() - p_76986_1_.posY + p_76986_4_;
/*  64 */     double var15 = var10.getZ() - p_76986_1_.posZ + p_76986_6_;
/*  65 */     GlStateManager.translate(var11 + 0.5D, var13 + 0.5D, var15 + 0.5D);
/*  66 */     GlStateManager.rotate(180.0F - p_76986_1_.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  67 */     this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
/*  68 */     BlockRendererDispatcher var17 = this.field_147917_g.getBlockRendererDispatcher();
/*  69 */     ModelManager var18 = var17.func_175023_a().func_178126_b();
/*     */ 
/*     */     
/*  72 */     if (p_76986_1_.getDisplayedItem() != null && p_76986_1_.getDisplayedItem().getItem() == Items.filled_map) {
/*     */       
/*  74 */       var19 = var18.getModel(this.field_177073_g);
/*     */     }
/*     */     else {
/*     */       
/*  78 */       var19 = var18.getModel(this.field_177072_f);
/*     */     } 
/*     */     
/*  81 */     GlStateManager.pushMatrix();
/*  82 */     GlStateManager.translate(-0.5F, -0.5F, -0.5F);
/*  83 */     var17.func_175019_b().func_178262_a(var19, 1.0F, 1.0F, 1.0F, 1.0F);
/*  84 */     GlStateManager.popMatrix();
/*  85 */     GlStateManager.translate(0.0F, 0.0F, 0.4375F);
/*  86 */     func_82402_b(p_76986_1_);
/*  87 */     GlStateManager.popMatrix();
/*  88 */     func_147914_a(p_76986_1_, p_76986_2_ + (p_76986_1_.field_174860_b.getFrontOffsetX() * 0.3F), p_76986_4_ - 0.25D, p_76986_6_ + (p_76986_1_.field_174860_b.getFrontOffsetZ() * 0.3F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityItemFrame p_110775_1_) {
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82402_b(EntityItemFrame p_82402_1_) {
/* 101 */     ItemStack var2 = p_82402_1_.getDisplayedItem();
/*     */     
/* 103 */     if (var2 != null) {
/*     */       
/* 105 */       EntityItem var3 = new EntityItem(p_82402_1_.worldObj, 0.0D, 0.0D, 0.0D, var2);
/* 106 */       Item var4 = var3.getEntityItem().getItem();
/* 107 */       (var3.getEntityItem()).stackSize = 1;
/* 108 */       var3.hoverStart = 0.0F;
/* 109 */       GlStateManager.pushMatrix();
/* 110 */       GlStateManager.disableLighting();
/* 111 */       int var5 = p_82402_1_.getRotation();
/*     */       
/* 113 */       if (var4 instanceof net.minecraft.item.ItemMap)
/*     */       {
/* 115 */         var5 = var5 % 4 * 2;
/*     */       }
/*     */       
/* 118 */       GlStateManager.rotate(var5 * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/* 120 */       if (!Reflector.postForgeBusEvent(Reflector.RenderItemInFrameEvent_Constructor, new Object[] { p_82402_1_, this }))
/*     */       {
/* 122 */         if (var4 instanceof net.minecraft.item.ItemMap) {
/*     */           
/* 124 */           this.renderManager.renderEngine.bindTexture(mapBackgroundTextures);
/* 125 */           GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
/* 126 */           float var12 = 0.0078125F;
/* 127 */           GlStateManager.scale(var12, var12, var12);
/* 128 */           GlStateManager.translate(-64.0F, -64.0F, 0.0F);
/* 129 */           MapData var13 = Items.filled_map.getMapData(var3.getEntityItem(), p_82402_1_.worldObj);
/* 130 */           GlStateManager.translate(0.0F, 0.0F, -1.0F);
/*     */           
/* 132 */           if (var13 != null)
/*     */           {
/* 134 */             this.field_147917_g.entityRenderer.getMapItemRenderer().func_148250_a(var13, true);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 139 */           TextureAtlasSprite var121 = null;
/*     */           
/* 141 */           if (var4 == Items.compass) {
/*     */             
/* 143 */             var121 = this.field_147917_g.getTextureMapBlocks().getAtlasSprite(TextureCompass.field_176608_l);
/*     */             
/* 145 */             if (Config.isShaders()) {
/*     */               
/* 147 */               ShadersTex.bindTextureMapForUpdateAndRender(this.field_147917_g.getTextureManager(), TextureMap.locationBlocksTexture);
/*     */             }
/*     */             else {
/*     */               
/* 151 */               this.field_147917_g.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/*     */             } 
/*     */             
/* 154 */             if (var121 instanceof TextureCompass) {
/*     */               
/* 156 */               TextureCompass var131 = (TextureCompass)var121;
/* 157 */               double var8 = var131.currentAngle;
/* 158 */               double var10 = var131.angleDelta;
/* 159 */               var131.currentAngle = 0.0D;
/* 160 */               var131.angleDelta = 0.0D;
/* 161 */               var131.updateCompass(p_82402_1_.worldObj, p_82402_1_.posX, p_82402_1_.posZ, MathHelper.wrapAngleTo180_float((180 + p_82402_1_.field_174860_b.getHorizontalIndex() * 90)), false, true);
/* 162 */               var131.currentAngle = var8;
/* 163 */               var131.angleDelta = var10;
/*     */             }
/*     */             else {
/*     */               
/* 167 */               var121 = null;
/*     */             } 
/*     */           } 
/*     */           
/* 171 */           GlStateManager.scale(0.5F, 0.5F, 0.5F);
/*     */           
/* 173 */           if (!this.field_177074_h.func_175050_a(var3.getEntityItem()) || var4 instanceof net.minecraft.item.ItemSkull)
/*     */           {
/* 175 */             GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/*     */           }
/*     */           
/* 178 */           GlStateManager.pushAttrib();
/* 179 */           RenderHelper.enableStandardItemLighting();
/* 180 */           this.field_177074_h.func_175043_b(var3.getEntityItem());
/* 181 */           RenderHelper.disableStandardItemLighting();
/* 182 */           GlStateManager.popAttrib();
/*     */           
/* 184 */           if (var121 != null && var121.getFrameCount() > 0)
/*     */           {
/* 186 */             var121.updateAnimation();
/*     */           }
/*     */         } 
/*     */       }
/* 190 */       GlStateManager.enableLighting();
/* 191 */       GlStateManager.popMatrix();
/*     */     } 
/*     */     
/* 194 */     if (Config.isShaders())
/*     */     {
/* 196 */       ShadersTex.updatingTex = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_147914_a(EntityItemFrame p_147914_1_, double p_147914_2_, double p_147914_4_, double p_147914_6_) {
/* 202 */     if (Minecraft.isGuiEnabled() && p_147914_1_.getDisplayedItem() != null && p_147914_1_.getDisplayedItem().hasDisplayName() && this.renderManager.field_147941_i == p_147914_1_) {
/*     */       
/* 204 */       float var8 = 1.6F;
/* 205 */       float var9 = 0.016666668F * var8;
/* 206 */       double var10 = p_147914_1_.getDistanceSqToEntity(this.renderManager.livingPlayer);
/* 207 */       float var12 = p_147914_1_.isSneaking() ? 32.0F : 64.0F;
/*     */       
/* 209 */       if (var10 < (var12 * var12)) {
/*     */         
/* 211 */         String var13 = p_147914_1_.getDisplayedItem().getDisplayName();
/*     */         
/* 213 */         if (p_147914_1_.isSneaking()) {
/*     */           
/* 215 */           FontRenderer var14 = getFontRendererFromRenderManager();
/* 216 */           GlStateManager.pushMatrix();
/* 217 */           GlStateManager.translate((float)p_147914_2_ + 0.0F, (float)p_147914_4_ + p_147914_1_.height + 0.5F, (float)p_147914_6_);
/* 218 */           GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 219 */           GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 220 */           GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 221 */           GlStateManager.scale(-var9, -var9, var9);
/* 222 */           GlStateManager.disableLighting();
/* 223 */           GlStateManager.translate(0.0F, 0.25F / var9, 0.0F);
/* 224 */           GlStateManager.depthMask(false);
/* 225 */           GlStateManager.enableBlend();
/* 226 */           GlStateManager.blendFunc(770, 771);
/* 227 */           Tessellator var15 = Tessellator.getInstance();
/* 228 */           WorldRenderer var16 = var15.getWorldRenderer();
/* 229 */           GlStateManager.func_179090_x();
/* 230 */           var16.startDrawingQuads();
/* 231 */           int var17 = var14.getStringWidth(var13) / 2;
/* 232 */           var16.func_178960_a(0.0F, 0.0F, 0.0F, 0.25F);
/* 233 */           var16.addVertex((-var17 - 1), -1.0D, 0.0D);
/* 234 */           var16.addVertex((-var17 - 1), 8.0D, 0.0D);
/* 235 */           var16.addVertex((var17 + 1), 8.0D, 0.0D);
/* 236 */           var16.addVertex((var17 + 1), -1.0D, 0.0D);
/* 237 */           var15.draw();
/* 238 */           GlStateManager.func_179098_w();
/* 239 */           GlStateManager.depthMask(true);
/* 240 */           var14.drawString(var13, (-var14.getStringWidth(var13) / 2), 0.0D, 553648127);
/* 241 */           GlStateManager.enableLighting();
/* 242 */           GlStateManager.disableBlend();
/* 243 */           GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 244 */           GlStateManager.popMatrix();
/*     */         }
/*     */         else {
/*     */           
/* 248 */           renderLivingLabel((Entity)p_147914_1_, var13, p_147914_2_, p_147914_4_, p_147914_6_, 64);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 259 */     return getEntityTexture((EntityItemFrame)p_110775_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_177067_a(Entity p_177067_1_, double p_177067_2_, double p_177067_4_, double p_177067_6_) {
/* 264 */     func_147914_a((EntityItemFrame)p_177067_1_, p_177067_2_, p_177067_4_, p_177067_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 275 */     doRender((EntityItemFrame)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\RenderItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */