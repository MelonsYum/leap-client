/*     */ package net.minecraft.client.renderer.entity.layers;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.CustomItems;
/*     */ import optifine.Reflector;
/*     */ import shadersmod.client.Shaders;
/*     */ import shadersmod.client.ShadersRender;
/*     */ 
/*     */ public abstract class LayerArmorBase
/*     */   implements LayerRenderer {
/*  21 */   protected static final ResourceLocation field_177188_b = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */   protected ModelBase field_177189_c;
/*     */   protected ModelBase field_177186_d;
/*     */   private final RendererLivingEntity field_177190_a;
/*  25 */   private float field_177187_e = 1.0F;
/*  26 */   private float field_177184_f = 1.0F;
/*  27 */   private float field_177185_g = 1.0F;
/*  28 */   private float field_177192_h = 1.0F;
/*     */   private boolean field_177193_i;
/*  30 */   private static final Map field_177191_j = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00002428";
/*     */   
/*     */   public LayerArmorBase(RendererLivingEntity p_i46125_1_) {
/*  35 */     this.field_177190_a = p_i46125_1_;
/*  36 */     func_177177_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/*  41 */     func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 4);
/*  42 */     func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 3);
/*  43 */     func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 2);
/*  44 */     func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldCombineTextures() {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177182_a(EntityLivingBase p_177182_1_, float p_177182_2_, float p_177182_3_, float p_177182_4_, float p_177182_5_, float p_177182_6_, float p_177182_7_, float p_177182_8_, int p_177182_9_) {
/*  54 */     ItemStack var10 = func_177176_a(p_177182_1_, p_177182_9_);
/*     */     
/*  56 */     if (var10 != null && var10.getItem() instanceof ItemArmor) {
/*     */       int var14; float var15, var16, var17;
/*  58 */       ItemArmor var11 = (ItemArmor)var10.getItem();
/*  59 */       ModelBase var12 = func_177175_a(p_177182_9_);
/*  60 */       var12.setModelAttributes(this.field_177190_a.getMainModel());
/*  61 */       var12.setLivingAnimations(p_177182_1_, p_177182_2_, p_177182_3_, p_177182_4_);
/*     */       
/*  63 */       if (Reflector.ForgeHooksClient_getArmorModel.exists())
/*     */       {
/*  65 */         var12 = (ModelBase)Reflector.call(Reflector.ForgeHooksClient_getArmorModel, new Object[] { p_177182_1_, var10, Integer.valueOf(p_177182_9_), var12 });
/*     */       }
/*     */       
/*  68 */       func_177179_a(var12, p_177182_9_);
/*  69 */       boolean var13 = func_177180_b(p_177182_9_);
/*     */       
/*  71 */       if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(var10, var13 ? 2 : 1, null))
/*     */       {
/*  73 */         if (Reflector.ForgeHooksClient_getArmorTexture.exists()) {
/*     */           
/*  75 */           this.field_177190_a.bindTexture(getArmorResource((Entity)p_177182_1_, var10, var13 ? 2 : 1, null));
/*     */         }
/*     */         else {
/*     */           
/*  79 */           this.field_177190_a.bindTexture(func_177181_a(var11, var13));
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  88 */       if (Reflector.ForgeHooksClient_getArmorTexture.exists()) {
/*     */         
/*  90 */         int i = var11.getColor(var10);
/*     */         
/*  92 */         if (i != -1) {
/*     */           
/*  94 */           float f1 = (i >> 16 & 0xFF) / 255.0F;
/*  95 */           float f2 = (i >> 8 & 0xFF) / 255.0F;
/*  96 */           float f3 = (i & 0xFF) / 255.0F;
/*  97 */           GlStateManager.color(this.field_177184_f * f1, this.field_177185_g * f2, this.field_177192_h * f3, this.field_177187_e);
/*  98 */           var12.render((Entity)p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
/*     */           
/* 100 */           if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(var10, var13 ? 2 : 1, "overlay"))
/*     */           {
/* 102 */             this.field_177190_a.bindTexture(getArmorResource((Entity)p_177182_1_, var10, var13 ? 2 : 1, "overlay"));
/*     */           }
/*     */         } 
/*     */         
/* 106 */         GlStateManager.color(this.field_177184_f, this.field_177185_g, this.field_177192_h, this.field_177187_e);
/* 107 */         var12.render((Entity)p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
/*     */         
/* 109 */         if (!this.field_177193_i && var10.isItemEnchanted() && (!Config.isCustomItems() || !CustomItems.renderCustomArmorEffect(p_177182_1_, var10, var12, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_)))
/*     */         {
/* 111 */           func_177183_a(p_177182_1_, var12, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 117 */       switch (SwitchArmorMaterial.field_178747_a[var11.getArmorMaterial().ordinal()]) {
/*     */         
/*     */         case 1:
/* 120 */           var14 = var11.getColor(var10);
/* 121 */           var15 = (var14 >> 16 & 0xFF) / 255.0F;
/* 122 */           var16 = (var14 >> 8 & 0xFF) / 255.0F;
/* 123 */           var17 = (var14 & 0xFF) / 255.0F;
/* 124 */           GlStateManager.color(this.field_177184_f * var15, this.field_177185_g * var16, this.field_177192_h * var17, this.field_177187_e);
/* 125 */           var12.render((Entity)p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
/*     */           
/* 127 */           if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(var10, var13 ? 2 : 1, "overlay"))
/*     */           {
/* 129 */             this.field_177190_a.bindTexture(func_177178_a(var11, var13, "overlay"));
/*     */           }
/*     */         
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/*     */         case 5:
/* 136 */           GlStateManager.color(this.field_177184_f, this.field_177185_g, this.field_177192_h, this.field_177187_e);
/* 137 */           var12.render((Entity)p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
/*     */           break;
/*     */       } 
/* 140 */       if (!this.field_177193_i && var10.isItemEnchanted() && (!Config.isCustomItems() || !CustomItems.renderCustomArmorEffect(p_177182_1_, var10, var12, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_)))
/*     */       {
/* 142 */         func_177183_a(p_177182_1_, var12, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_177176_a(EntityLivingBase p_177176_1_, int p_177176_2_) {
/* 150 */     return p_177176_1_.getCurrentArmor(p_177176_2_ - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBase func_177175_a(int p_177175_1_) {
/* 155 */     return func_177180_b(p_177175_1_) ? this.field_177189_c : this.field_177186_d;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_177180_b(int p_177180_1_) {
/* 160 */     return (p_177180_1_ == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177183_a(EntityLivingBase p_177183_1_, ModelBase p_177183_2_, float p_177183_3_, float p_177183_4_, float p_177183_5_, float p_177183_6_, float p_177183_7_, float p_177183_8_, float p_177183_9_) {
/* 165 */     if (!Config.isCustomItems() || CustomItems.isUseGlint()) {
/*     */       
/* 167 */       if (Config.isShaders()) {
/*     */         
/* 169 */         if (Shaders.isShadowPass) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 174 */         ShadersRender.layerArmorBaseDrawEnchantedGlintBegin();
/*     */       } 
/*     */       
/* 177 */       float var10 = p_177183_1_.ticksExisted + p_177183_5_;
/* 178 */       this.field_177190_a.bindTexture(field_177188_b);
/* 179 */       GlStateManager.enableBlend();
/* 180 */       GlStateManager.depthFunc(514);
/* 181 */       GlStateManager.depthMask(false);
/* 182 */       float var11 = 0.5F;
/* 183 */       GlStateManager.color(var11, var11, var11, 1.0F);
/*     */       
/* 185 */       for (int var12 = 0; var12 < 2; var12++) {
/*     */         
/* 187 */         GlStateManager.disableLighting();
/* 188 */         GlStateManager.blendFunc(768, 1);
/* 189 */         float var13 = 0.76F;
/* 190 */         GlStateManager.color(0.5F * var13, 0.25F * var13, 0.8F * var13, 1.0F);
/* 191 */         GlStateManager.matrixMode(5890);
/* 192 */         GlStateManager.loadIdentity();
/* 193 */         float var14 = 0.33333334F;
/* 194 */         GlStateManager.scale(var14, var14, var14);
/* 195 */         GlStateManager.rotate(30.0F - var12 * 60.0F, 0.0F, 0.0F, 1.0F);
/* 196 */         GlStateManager.translate(0.0F, var10 * (0.001F + var12 * 0.003F) * 20.0F, 0.0F);
/* 197 */         GlStateManager.matrixMode(5888);
/* 198 */         p_177183_2_.render((Entity)p_177183_1_, p_177183_3_, p_177183_4_, p_177183_6_, p_177183_7_, p_177183_8_, p_177183_9_);
/*     */       } 
/*     */       
/* 201 */       GlStateManager.matrixMode(5890);
/* 202 */       GlStateManager.loadIdentity();
/* 203 */       GlStateManager.matrixMode(5888);
/* 204 */       GlStateManager.enableLighting();
/* 205 */       GlStateManager.depthMask(true);
/* 206 */       GlStateManager.depthFunc(515);
/* 207 */       GlStateManager.disableBlend();
/*     */       
/* 209 */       if (Config.isShaders())
/*     */       {
/* 211 */         ShadersRender.layerArmorBaseDrawEnchantedGlintEnd();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation func_177181_a(ItemArmor p_177181_1_, boolean p_177181_2_) {
/* 218 */     return func_177178_a(p_177181_1_, p_177181_2_, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation func_177178_a(ItemArmor p_177178_1_, boolean p_177178_2_, String p_177178_3_) {
/* 223 */     String var4 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { p_177178_1_.getArmorMaterial().func_179242_c(), Integer.valueOf(p_177178_2_ ? 2 : 1), (p_177178_3_ == null) ? "" : String.format("_%s", new Object[] { p_177178_3_ }) });
/* 224 */     ResourceLocation var5 = (ResourceLocation)field_177191_j.get(var4);
/*     */     
/* 226 */     if (var5 == null) {
/*     */       
/* 228 */       var5 = new ResourceLocation(var4);
/* 229 */       field_177191_j.put(var4, var5);
/*     */     } 
/*     */     
/* 232 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void func_177177_a();
/*     */   
/*     */   protected abstract void func_177179_a(ModelBase paramModelBase, int paramInt);
/*     */   
/*     */   public ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
/* 241 */     ItemArmor item = (ItemArmor)stack.getItem();
/* 242 */     String texture = ((ItemArmor)stack.getItem()).getArmorMaterial().func_179242_c();
/* 243 */     String domain = "minecraft";
/* 244 */     int idx = texture.indexOf(':');
/*     */     
/* 246 */     if (idx != -1) {
/*     */       
/* 248 */       domain = texture.substring(0, idx);
/* 249 */       texture = texture.substring(idx + 1);
/*     */     } 
/*     */     
/* 252 */     String s1 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", new Object[] { domain, texture, Integer.valueOf((slot == 2) ? 2 : 1), (type == null) ? "" : String.format("_%s", new Object[] { type }) });
/* 253 */     s1 = Reflector.callString(Reflector.ForgeHooksClient_getArmorTexture, new Object[] { entity, stack, s1, Integer.valueOf(slot), type });
/* 254 */     ResourceLocation resourcelocation = (ResourceLocation)field_177191_j.get(s1);
/*     */     
/* 256 */     if (resourcelocation == null) {
/*     */       
/* 258 */       resourcelocation = new ResourceLocation(s1);
/* 259 */       field_177191_j.put(s1, resourcelocation);
/*     */     } 
/*     */     
/* 262 */     return resourcelocation;
/*     */   }
/*     */   
/*     */   static final class SwitchArmorMaterial
/*     */   {
/* 267 */     static final int[] field_178747_a = new int[(ItemArmor.ArmorMaterial.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002427";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 274 */         field_178747_a[ItemArmor.ArmorMaterial.LEATHER.ordinal()] = 1;
/*     */       }
/* 276 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 283 */         field_178747_a[ItemArmor.ArmorMaterial.CHAIN.ordinal()] = 2;
/*     */       }
/* 285 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 292 */         field_178747_a[ItemArmor.ArmorMaterial.IRON.ordinal()] = 3;
/*     */       }
/* 294 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 301 */         field_178747_a[ItemArmor.ArmorMaterial.GOLD.ordinal()] = 4;
/*     */       }
/* 303 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 310 */         field_178747_a[ItemArmor.ArmorMaterial.DIAMOND.ordinal()] = 5;
/*     */       }
/* 312 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerArmorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */