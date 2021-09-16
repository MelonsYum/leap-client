/*     */ package optifine;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.image.BufferedImage;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class PlayerItemModel {
/*  16 */   private Dimension textureSize = null;
/*     */   private boolean usePlayerTexture = false;
/*  18 */   private PlayerItemRenderer[] modelRenderers = new PlayerItemRenderer[0];
/*  19 */   private ResourceLocation textureLocation = null;
/*  20 */   private BufferedImage textureImage = null;
/*  21 */   private DynamicTexture texture = null;
/*  22 */   private ResourceLocation locationMissing = new ResourceLocation("textures/blocks/wool_colored_red.png");
/*     */   
/*     */   public static final int ATTACH_BODY = 0;
/*     */   public static final int ATTACH_HEAD = 1;
/*     */   public static final int ATTACH_LEFT_ARM = 2;
/*     */   public static final int ATTACH_RIGHT_ARM = 3;
/*     */   public static final int ATTACH_LEFT_LEG = 4;
/*     */   public static final int ATTACH_RIGHT_LEG = 5;
/*     */   public static final int ATTACH_CAPE = 6;
/*     */   
/*     */   public PlayerItemModel(Dimension textureSize, boolean usePlayerTexture, PlayerItemRenderer[] modelRenderers) {
/*  33 */     this.textureSize = textureSize;
/*  34 */     this.usePlayerTexture = usePlayerTexture;
/*  35 */     this.modelRenderers = modelRenderers;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(ModelBiped modelBiped, AbstractClientPlayer player, float scale, float partialTicks) {
/*  40 */     TextureManager textureManager = Config.getTextureManager();
/*     */     
/*  42 */     if (this.usePlayerTexture) {
/*     */       
/*  44 */       textureManager.bindTexture(player.getLocationSkin());
/*     */     }
/*  46 */     else if (this.textureLocation != null) {
/*     */       
/*  48 */       if (this.texture == null && this.textureImage != null) {
/*     */         
/*  50 */         this.texture = new DynamicTexture(this.textureImage);
/*  51 */         Minecraft.getMinecraft().getTextureManager().loadTexture(this.textureLocation, (ITextureObject)this.texture);
/*     */       } 
/*     */       
/*  54 */       textureManager.bindTexture(this.textureLocation);
/*     */     }
/*     */     else {
/*     */       
/*  58 */       textureManager.bindTexture(this.locationMissing);
/*     */     } 
/*     */     
/*  61 */     for (int i = 0; i < this.modelRenderers.length; i++) {
/*     */       
/*  63 */       PlayerItemRenderer pir = this.modelRenderers[i];
/*  64 */       GlStateManager.pushMatrix();
/*     */       
/*  66 */       if (player.isSneaking())
/*     */       {
/*  68 */         GlStateManager.translate(0.0F, 0.2F, 0.0F);
/*     */       }
/*     */       
/*  71 */       pir.render(modelBiped, scale);
/*  72 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ModelRenderer getAttachModel(ModelBiped modelBiped, int attachTo) {
/*  78 */     switch (attachTo) {
/*     */       
/*     */       case 0:
/*  81 */         return modelBiped.bipedBody;
/*     */       
/*     */       case 1:
/*  84 */         return modelBiped.bipedHead;
/*     */       
/*     */       case 2:
/*  87 */         return modelBiped.bipedLeftArm;
/*     */       
/*     */       case 3:
/*  90 */         return modelBiped.bipedRightArm;
/*     */       
/*     */       case 4:
/*  93 */         return modelBiped.bipedLeftLeg;
/*     */       
/*     */       case 5:
/*  96 */         return modelBiped.bipedRightLeg;
/*     */     } 
/*     */     
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getTextureImage() {
/* 105 */     return this.textureImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextureImage(BufferedImage textureImage) {
/* 110 */     this.textureImage = textureImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicTexture getTexture() {
/* 115 */     return this.texture;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation() {
/* 120 */     return this.textureLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextureLocation(ResourceLocation textureLocation) {
/* 125 */     this.textureLocation = textureLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsePlayerTexture() {
/* 130 */     return this.usePlayerTexture;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerItemModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */