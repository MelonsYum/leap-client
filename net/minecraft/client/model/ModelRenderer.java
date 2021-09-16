/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import optifine.ModelSprite;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelRenderer
/*     */ {
/*     */   public float textureWidth;
/*     */   public float textureHeight;
/*     */   private int textureOffsetX;
/*     */   private int textureOffsetY;
/*     */   public float rotationPointX;
/*     */   public float rotationPointY;
/*     */   public float rotationPointZ;
/*     */   public float rotateAngleX;
/*     */   public float rotateAngleY;
/*     */   public float rotateAngleZ;
/*     */   private boolean compiled;
/*     */   private int displayList;
/*     */   public boolean mirror;
/*     */   public boolean showModel;
/*     */   public boolean isHidden;
/*     */   public List cubeList;
/*     */   public List childModels;
/*     */   public final String boxName;
/*     */   private ModelBase baseModel;
/*     */   public float offsetX;
/*     */   public float offsetY;
/*     */   public float offsetZ;
/*     */   private static final String __OBFID = "CL_00000874";
/*     */   public List spriteList;
/*     */   public boolean mirrorV;
/*     */   float savedScale;
/*     */   
/*     */   public ModelRenderer(ModelBase p_i1172_1_, String p_i1172_2_) {
/*  56 */     this.spriteList = new ArrayList();
/*  57 */     this.mirrorV = false;
/*  58 */     this.textureWidth = 64.0F;
/*  59 */     this.textureHeight = 32.0F;
/*  60 */     this.showModel = true;
/*  61 */     this.cubeList = Lists.newArrayList();
/*  62 */     this.baseModel = p_i1172_1_;
/*  63 */     p_i1172_1_.boxList.add(this);
/*  64 */     this.boxName = p_i1172_2_;
/*  65 */     setTextureSize(p_i1172_1_.textureWidth, p_i1172_1_.textureHeight);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer(ModelBase p_i1173_1_) {
/*  70 */     this(p_i1173_1_, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer(ModelBase p_i46358_1_, int p_i46358_2_, int p_i46358_3_) {
/*  75 */     this(p_i46358_1_);
/*  76 */     setTextureOffset(p_i46358_2_, p_i46358_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChild(ModelRenderer p_78792_1_) {
/*  84 */     if (this.childModels == null)
/*     */     {
/*  86 */       this.childModels = Lists.newArrayList();
/*     */     }
/*     */     
/*  89 */     this.childModels.add(p_78792_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer setTextureOffset(int p_78784_1_, int p_78784_2_) {
/*  94 */     this.textureOffsetX = p_78784_1_;
/*  95 */     this.textureOffsetY = p_78784_2_;
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer addBox(String p_78786_1_, float p_78786_2_, float p_78786_3_, float p_78786_4_, int p_78786_5_, int p_78786_6_, int p_78786_7_) {
/* 101 */     p_78786_1_ = String.valueOf(this.boxName) + "." + p_78786_1_;
/* 102 */     TextureOffset var8 = this.baseModel.getTextureOffset(p_78786_1_);
/* 103 */     setTextureOffset(var8.textureOffsetX, var8.textureOffsetY);
/* 104 */     this.cubeList.add((new ModelBox(this, this.textureOffsetX, this.textureOffsetY, p_78786_2_, p_78786_3_, p_78786_4_, p_78786_5_, p_78786_6_, p_78786_7_, 0.0F)).func_78244_a(p_78786_1_));
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer addBox(float p_78789_1_, float p_78789_2_, float p_78789_3_, int p_78789_4_, int p_78789_5_, int p_78789_6_) {
/* 110 */     this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, p_78789_1_, p_78789_2_, p_78789_3_, p_78789_4_, p_78789_5_, p_78789_6_, 0.0F));
/* 111 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer addBox(float p_178769_1_, float p_178769_2_, float p_178769_3_, int p_178769_4_, int p_178769_5_, int p_178769_6_, boolean p_178769_7_) {
/* 116 */     this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, p_178769_1_, p_178769_2_, p_178769_3_, p_178769_4_, p_178769_5_, p_178769_6_, 0.0F, p_178769_7_));
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBox(float p_78790_1_, float p_78790_2_, float p_78790_3_, int p_78790_4_, int p_78790_5_, int p_78790_6_, float p_78790_7_) {
/* 125 */     this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, p_78790_1_, p_78790_2_, p_78790_3_, p_78790_4_, p_78790_5_, p_78790_6_, p_78790_7_));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationPoint(float p_78793_1_, float p_78793_2_, float p_78793_3_) {
/* 130 */     this.rotationPointX = p_78793_1_;
/* 131 */     this.rotationPointY = p_78793_2_;
/* 132 */     this.rotationPointZ = p_78793_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(float p_78785_1_) {
/* 137 */     if (!this.isHidden && this.showModel) {
/*     */       
/* 139 */       if (!this.compiled)
/*     */       {
/* 141 */         compileDisplayList(p_78785_1_);
/*     */       }
/*     */       
/* 144 */       GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);
/*     */ 
/*     */       
/* 147 */       if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
/*     */         
/* 149 */         if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F)
/*     */         {
/* 151 */           GlStateManager.callList(this.displayList);
/*     */           
/* 153 */           if (this.childModels != null)
/*     */           {
/* 155 */             for (int var2 = 0; var2 < this.childModels.size(); var2++)
/*     */             {
/* 157 */               ((ModelRenderer)this.childModels.get(var2)).render(p_78785_1_);
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 163 */           GlStateManager.translate(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);
/* 164 */           GlStateManager.callList(this.displayList);
/*     */           
/* 166 */           if (this.childModels != null)
/*     */           {
/* 168 */             for (int var2 = 0; var2 < this.childModels.size(); var2++)
/*     */             {
/* 170 */               ((ModelRenderer)this.childModels.get(var2)).render(p_78785_1_);
/*     */             }
/*     */           }
/*     */           
/* 174 */           GlStateManager.translate(-this.rotationPointX * p_78785_1_, -this.rotationPointY * p_78785_1_, -this.rotationPointZ * p_78785_1_);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 179 */         GlStateManager.pushMatrix();
/* 180 */         GlStateManager.translate(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);
/*     */         
/* 182 */         if (this.rotateAngleZ != 0.0F)
/*     */         {
/* 184 */           GlStateManager.rotate(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */         }
/*     */         
/* 187 */         if (this.rotateAngleY != 0.0F)
/*     */         {
/* 189 */           GlStateManager.rotate(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 192 */         if (this.rotateAngleX != 0.0F)
/*     */         {
/* 194 */           GlStateManager.rotate(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */         }
/*     */         
/* 197 */         GlStateManager.callList(this.displayList);
/*     */         
/* 199 */         if (this.childModels != null)
/*     */         {
/* 201 */           for (int var2 = 0; var2 < this.childModels.size(); var2++)
/*     */           {
/* 203 */             ((ModelRenderer)this.childModels.get(var2)).render(p_78785_1_);
/*     */           }
/*     */         }
/*     */         
/* 207 */         GlStateManager.popMatrix();
/*     */       } 
/*     */       
/* 210 */       GlStateManager.translate(-this.offsetX, -this.offsetY, -this.offsetZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderWithRotation(float p_78791_1_) {
/* 216 */     if (!this.isHidden && this.showModel) {
/*     */       
/* 218 */       if (!this.compiled)
/*     */       {
/* 220 */         compileDisplayList(p_78791_1_);
/*     */       }
/*     */       
/* 223 */       GlStateManager.pushMatrix();
/* 224 */       GlStateManager.translate(this.rotationPointX * p_78791_1_, this.rotationPointY * p_78791_1_, this.rotationPointZ * p_78791_1_);
/*     */       
/* 226 */       if (this.rotateAngleY != 0.0F)
/*     */       {
/* 228 */         GlStateManager.rotate(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       
/* 231 */       if (this.rotateAngleX != 0.0F)
/*     */       {
/* 233 */         GlStateManager.rotate(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       
/* 236 */       if (this.rotateAngleZ != 0.0F)
/*     */       {
/* 238 */         GlStateManager.rotate(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/* 241 */       GlStateManager.callList(this.displayList);
/* 242 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void postRender(float p_78794_1_) {
/* 251 */     if (!this.isHidden && this.showModel) {
/*     */       
/* 253 */       if (!this.compiled)
/*     */       {
/* 255 */         compileDisplayList(p_78794_1_);
/*     */       }
/*     */       
/* 258 */       if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
/*     */         
/* 260 */         if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F)
/*     */         {
/* 262 */           GlStateManager.translate(this.rotationPointX * p_78794_1_, this.rotationPointY * p_78794_1_, this.rotationPointZ * p_78794_1_);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 267 */         GlStateManager.translate(this.rotationPointX * p_78794_1_, this.rotationPointY * p_78794_1_, this.rotationPointZ * p_78794_1_);
/*     */         
/* 269 */         if (this.rotateAngleZ != 0.0F)
/*     */         {
/* 271 */           GlStateManager.rotate(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */         }
/*     */         
/* 274 */         if (this.rotateAngleY != 0.0F)
/*     */         {
/* 276 */           GlStateManager.rotate(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 279 */         if (this.rotateAngleX != 0.0F)
/*     */         {
/* 281 */           GlStateManager.rotate(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void compileDisplayList(float p_78788_1_) {
/* 292 */     if (this.displayList == 0) {
/*     */       
/* 294 */       this.savedScale = p_78788_1_;
/* 295 */       this.displayList = GLAllocation.generateDisplayLists(1);
/*     */     } 
/*     */     
/* 298 */     GL11.glNewList(this.displayList, 4864);
/* 299 */     WorldRenderer var2 = Tessellator.getInstance().getWorldRenderer();
/*     */     
/*     */     int i;
/* 302 */     for (i = 0; i < this.cubeList.size(); i++)
/*     */     {
/* 304 */       ((ModelBox)this.cubeList.get(i)).render(var2, p_78788_1_);
/*     */     }
/*     */     
/* 307 */     for (i = 0; i < this.spriteList.size(); i++) {
/*     */       
/* 309 */       ModelSprite sprite = this.spriteList.get(i);
/* 310 */       sprite.render(Tessellator.getInstance(), p_78788_1_);
/*     */     } 
/*     */     
/* 313 */     GL11.glEndList();
/* 314 */     this.compiled = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelRenderer setTextureSize(int p_78787_1_, int p_78787_2_) {
/* 322 */     this.textureWidth = p_78787_1_;
/* 323 */     this.textureHeight = p_78787_2_;
/* 324 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSprite(float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd) {
/* 329 */     this.spriteList.add(new ModelSprite(this, this.textureOffsetX, this.textureOffsetY, posX, posY, posZ, sizeX, sizeY, sizeZ, sizeAdd));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCompiled() {
/* 334 */     return this.compiled;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDisplayList() {
/* 339 */     return this.displayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetDisplayList() {
/* 344 */     if (this.compiled) {
/*     */       
/* 346 */       this.compiled = false;
/* 347 */       compileDisplayList(this.savedScale);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */