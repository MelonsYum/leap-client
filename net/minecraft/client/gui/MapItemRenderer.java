/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec4b;
/*     */ import net.minecraft.world.storage.MapData;
/*     */ 
/*     */ public class MapItemRenderer
/*     */ {
/*  18 */   private static final ResourceLocation mapIcons = new ResourceLocation("textures/map/map_icons.png");
/*     */   private final TextureManager textureManager;
/*  20 */   private final Map loadedMaps = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00000663";
/*     */   
/*     */   public MapItemRenderer(TextureManager p_i45009_1_) {
/*  25 */     this.textureManager = p_i45009_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148246_a(MapData p_148246_1_) {
/*  30 */     func_148248_b(p_148246_1_).func_148236_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148250_a(MapData p_148250_1_, boolean p_148250_2_) {
/*  35 */     func_148248_b(p_148250_1_).func_148237_a(p_148250_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   private Instance func_148248_b(MapData p_148248_1_) {
/*  40 */     Instance var2 = (Instance)this.loadedMaps.get(p_148248_1_.mapName);
/*     */     
/*  42 */     if (var2 == null) {
/*     */       
/*  44 */       var2 = new Instance(p_148248_1_, null);
/*  45 */       this.loadedMaps.put(p_148248_1_.mapName, var2);
/*     */     } 
/*     */     
/*  48 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148249_a() {
/*  53 */     Iterator<Instance> var1 = this.loadedMaps.values().iterator();
/*     */     
/*  55 */     while (var1.hasNext()) {
/*     */       
/*  57 */       Instance var2 = var1.next();
/*  58 */       this.textureManager.deleteTexture(var2.field_148240_d);
/*     */     } 
/*     */     
/*  61 */     this.loadedMaps.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   class Instance
/*     */   {
/*     */     private final MapData field_148242_b;
/*     */     private final DynamicTexture field_148243_c;
/*     */     private final ResourceLocation field_148240_d;
/*     */     private final int[] field_148241_e;
/*     */     private static final String __OBFID = "CL_00000665";
/*     */     
/*     */     private Instance(MapData p_i45007_2_) {
/*  74 */       this.field_148242_b = p_i45007_2_;
/*  75 */       this.field_148243_c = new DynamicTexture(128, 128);
/*  76 */       this.field_148241_e = this.field_148243_c.getTextureData();
/*  77 */       this.field_148240_d = MapItemRenderer.this.textureManager.getDynamicTextureLocation("map/" + p_i45007_2_.mapName, this.field_148243_c);
/*     */       
/*  79 */       for (int var3 = 0; var3 < this.field_148241_e.length; var3++)
/*     */       {
/*  81 */         this.field_148241_e[var3] = 0;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_148236_a() {
/*  87 */       for (int var1 = 0; var1 < 16384; var1++) {
/*     */         
/*  89 */         int var2 = this.field_148242_b.colors[var1] & 0xFF;
/*     */         
/*  91 */         if (var2 / 4 == 0) {
/*     */           
/*  93 */           this.field_148241_e[var1] = (var1 + var1 / 128 & 0x1) * 8 + 16 << 24;
/*     */         }
/*     */         else {
/*     */           
/*  97 */           this.field_148241_e[var1] = MapColor.mapColorArray[var2 / 4].func_151643_b(var2 & 0x3);
/*     */         } 
/*     */       } 
/*     */       
/* 101 */       this.field_148243_c.updateDynamicTexture();
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_148237_a(boolean p_148237_1_) {
/* 106 */       byte var2 = 0;
/* 107 */       byte var3 = 0;
/* 108 */       Tessellator var4 = Tessellator.getInstance();
/* 109 */       WorldRenderer var5 = var4.getWorldRenderer();
/* 110 */       float var6 = 0.0F;
/* 111 */       MapItemRenderer.this.textureManager.bindTexture(this.field_148240_d);
/* 112 */       GlStateManager.enableBlend();
/* 113 */       GlStateManager.tryBlendFuncSeparate(1, 771, 0, 1);
/* 114 */       GlStateManager.disableAlpha();
/* 115 */       var5.startDrawingQuads();
/* 116 */       var5.addVertexWithUV(((var2 + 0) + var6), ((var3 + 128) - var6), -0.009999999776482582D, 0.0D, 1.0D);
/* 117 */       var5.addVertexWithUV(((var2 + 128) - var6), ((var3 + 128) - var6), -0.009999999776482582D, 1.0D, 1.0D);
/* 118 */       var5.addVertexWithUV(((var2 + 128) - var6), ((var3 + 0) + var6), -0.009999999776482582D, 1.0D, 0.0D);
/* 119 */       var5.addVertexWithUV(((var2 + 0) + var6), ((var3 + 0) + var6), -0.009999999776482582D, 0.0D, 0.0D);
/* 120 */       var4.draw();
/* 121 */       GlStateManager.enableAlpha();
/* 122 */       GlStateManager.disableBlend();
/* 123 */       MapItemRenderer.this.textureManager.bindTexture(MapItemRenderer.mapIcons);
/* 124 */       int var7 = 0;
/* 125 */       Iterator<Vec4b> var8 = this.field_148242_b.playersVisibleOnMap.values().iterator();
/*     */       
/* 127 */       while (var8.hasNext()) {
/*     */         
/* 129 */         Vec4b var9 = var8.next();
/*     */         
/* 131 */         if (!p_148237_1_ || var9.func_176110_a() == 1) {
/*     */           
/* 133 */           GlStateManager.pushMatrix();
/* 134 */           GlStateManager.translate(var2 + var9.func_176112_b() / 2.0F + 64.0F, var3 + var9.func_176113_c() / 2.0F + 64.0F, -0.02F);
/* 135 */           GlStateManager.rotate((var9.func_176111_d() * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
/* 136 */           GlStateManager.scale(4.0F, 4.0F, 3.0F);
/* 137 */           GlStateManager.translate(-0.125F, 0.125F, 0.0F);
/* 138 */           byte var10 = var9.func_176110_a();
/* 139 */           float var11 = (var10 % 4 + 0) / 4.0F;
/* 140 */           float var12 = (var10 / 4 + 0) / 4.0F;
/* 141 */           float var13 = (var10 % 4 + 1) / 4.0F;
/* 142 */           float var14 = (var10 / 4 + 1) / 4.0F;
/* 143 */           var5.startDrawingQuads();
/* 144 */           var5.addVertexWithUV(-1.0D, 1.0D, (var7 * 0.001F), var11, var12);
/* 145 */           var5.addVertexWithUV(1.0D, 1.0D, (var7 * 0.001F), var13, var12);
/* 146 */           var5.addVertexWithUV(1.0D, -1.0D, (var7 * 0.001F), var13, var14);
/* 147 */           var5.addVertexWithUV(-1.0D, -1.0D, (var7 * 0.001F), var11, var14);
/* 148 */           var4.draw();
/* 149 */           GlStateManager.popMatrix();
/* 150 */           var7++;
/*     */         } 
/*     */       } 
/*     */       
/* 154 */       GlStateManager.pushMatrix();
/* 155 */       GlStateManager.translate(0.0F, 0.0F, -0.04F);
/* 156 */       GlStateManager.scale(1.0F, 1.0F, 1.0F);
/* 157 */       GlStateManager.popMatrix();
/*     */     }
/*     */ 
/*     */     
/*     */     Instance(MapData p_i45008_2_, Object p_i45008_3_) {
/* 162 */       this(p_i45008_2_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\MapItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */