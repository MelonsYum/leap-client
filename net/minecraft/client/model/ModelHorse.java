/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.util.MathHelper;
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
/*     */ 
/*     */ public class ModelHorse
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer head;
/*     */   private ModelRenderer field_178711_b;
/*     */   private ModelRenderer field_178712_c;
/*     */   private ModelRenderer horseLeftEar;
/*     */   private ModelRenderer horseRightEar;
/*     */   private ModelRenderer muleLeftEar;
/*     */   private ModelRenderer muleRightEar;
/*     */   private ModelRenderer neck;
/*     */   private ModelRenderer horseFaceRopes;
/*     */   private ModelRenderer mane;
/*     */   private ModelRenderer body;
/*     */   private ModelRenderer tailBase;
/*     */   private ModelRenderer tailMiddle;
/*     */   private ModelRenderer tailTip;
/*     */   private ModelRenderer backLeftLeg;
/*     */   private ModelRenderer backLeftShin;
/*     */   private ModelRenderer backLeftHoof;
/*     */   private ModelRenderer backRightLeg;
/*     */   private ModelRenderer backRightShin;
/*     */   private ModelRenderer backRightHoof;
/*     */   private ModelRenderer frontLeftLeg;
/*     */   private ModelRenderer frontLeftShin;
/*     */   private ModelRenderer frontLeftHoof;
/*     */   private ModelRenderer frontRightLeg;
/*     */   private ModelRenderer frontRightShin;
/*     */   private ModelRenderer frontRightHoof;
/*     */   private ModelRenderer muleLeftChest;
/*     */   private ModelRenderer muleRightChest;
/*     */   private ModelRenderer horseSaddleBottom;
/*     */   private ModelRenderer horseSaddleFront;
/*     */   private ModelRenderer horseSaddleBack;
/*     */   private ModelRenderer horseLeftSaddleRope;
/*     */   private ModelRenderer horseLeftSaddleMetal;
/*     */   private ModelRenderer horseRightSaddleRope;
/*     */   private ModelRenderer horseRightSaddleMetal;
/*     */   private ModelRenderer horseLeftFaceMetal;
/*     */   private ModelRenderer horseRightFaceMetal;
/*     */   private ModelRenderer horseLeftRein;
/*     */   private ModelRenderer horseRightRein;
/*     */   private static final String __OBFID = "CL_00000846";
/*     */   
/*     */   public ModelHorse() {
/*  68 */     this.textureWidth = 128;
/*  69 */     this.textureHeight = 128;
/*  70 */     this.body = new ModelRenderer(this, 0, 34);
/*  71 */     this.body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24);
/*  72 */     this.body.setRotationPoint(0.0F, 11.0F, 9.0F);
/*  73 */     this.tailBase = new ModelRenderer(this, 44, 0);
/*  74 */     this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
/*  75 */     this.tailBase.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  76 */     setBoxRotation(this.tailBase, -1.134464F, 0.0F, 0.0F);
/*  77 */     this.tailMiddle = new ModelRenderer(this, 38, 7);
/*  78 */     this.tailMiddle.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7);
/*  79 */     this.tailMiddle.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  80 */     setBoxRotation(this.tailMiddle, -1.134464F, 0.0F, 0.0F);
/*  81 */     this.tailTip = new ModelRenderer(this, 24, 3);
/*  82 */     this.tailTip.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7);
/*  83 */     this.tailTip.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  84 */     setBoxRotation(this.tailTip, -1.40215F, 0.0F, 0.0F);
/*  85 */     this.backLeftLeg = new ModelRenderer(this, 78, 29);
/*  86 */     this.backLeftLeg.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5);
/*  87 */     this.backLeftLeg.setRotationPoint(4.0F, 9.0F, 11.0F);
/*  88 */     this.backLeftShin = new ModelRenderer(this, 78, 43);
/*  89 */     this.backLeftShin.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3);
/*  90 */     this.backLeftShin.setRotationPoint(4.0F, 16.0F, 11.0F);
/*  91 */     this.backLeftHoof = new ModelRenderer(this, 78, 51);
/*  92 */     this.backLeftHoof.addBox(-2.5F, 5.1F, -2.0F, 4, 3, 4);
/*  93 */     this.backLeftHoof.setRotationPoint(4.0F, 16.0F, 11.0F);
/*  94 */     this.backRightLeg = new ModelRenderer(this, 96, 29);
/*  95 */     this.backRightLeg.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5);
/*  96 */     this.backRightLeg.setRotationPoint(-4.0F, 9.0F, 11.0F);
/*  97 */     this.backRightShin = new ModelRenderer(this, 96, 43);
/*  98 */     this.backRightShin.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3);
/*  99 */     this.backRightShin.setRotationPoint(-4.0F, 16.0F, 11.0F);
/* 100 */     this.backRightHoof = new ModelRenderer(this, 96, 51);
/* 101 */     this.backRightHoof.addBox(-1.5F, 5.1F, -2.0F, 4, 3, 4);
/* 102 */     this.backRightHoof.setRotationPoint(-4.0F, 16.0F, 11.0F);
/* 103 */     this.frontLeftLeg = new ModelRenderer(this, 44, 29);
/* 104 */     this.frontLeftLeg.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4);
/* 105 */     this.frontLeftLeg.setRotationPoint(4.0F, 9.0F, -8.0F);
/* 106 */     this.frontLeftShin = new ModelRenderer(this, 44, 41);
/* 107 */     this.frontLeftShin.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3);
/* 108 */     this.frontLeftShin.setRotationPoint(4.0F, 16.0F, -8.0F);
/* 109 */     this.frontLeftHoof = new ModelRenderer(this, 44, 51);
/* 110 */     this.frontLeftHoof.addBox(-2.4F, 5.1F, -2.1F, 4, 3, 4);
/* 111 */     this.frontLeftHoof.setRotationPoint(4.0F, 16.0F, -8.0F);
/* 112 */     this.frontRightLeg = new ModelRenderer(this, 60, 29);
/* 113 */     this.frontRightLeg.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4);
/* 114 */     this.frontRightLeg.setRotationPoint(-4.0F, 9.0F, -8.0F);
/* 115 */     this.frontRightShin = new ModelRenderer(this, 60, 41);
/* 116 */     this.frontRightShin.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3);
/* 117 */     this.frontRightShin.setRotationPoint(-4.0F, 16.0F, -8.0F);
/* 118 */     this.frontRightHoof = new ModelRenderer(this, 60, 51);
/* 119 */     this.frontRightHoof.addBox(-1.6F, 5.1F, -2.1F, 4, 3, 4);
/* 120 */     this.frontRightHoof.setRotationPoint(-4.0F, 16.0F, -8.0F);
/* 121 */     this.head = new ModelRenderer(this, 0, 0);
/* 122 */     this.head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7);
/* 123 */     this.head.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 124 */     setBoxRotation(this.head, 0.5235988F, 0.0F, 0.0F);
/* 125 */     this.field_178711_b = new ModelRenderer(this, 24, 18);
/* 126 */     this.field_178711_b.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6);
/* 127 */     this.field_178711_b.setRotationPoint(0.0F, 3.95F, -10.0F);
/* 128 */     setBoxRotation(this.field_178711_b, 0.5235988F, 0.0F, 0.0F);
/* 129 */     this.field_178712_c = new ModelRenderer(this, 24, 27);
/* 130 */     this.field_178712_c.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5);
/* 131 */     this.field_178712_c.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 132 */     setBoxRotation(this.field_178712_c, 0.5235988F, 0.0F, 0.0F);
/* 133 */     this.head.addChild(this.field_178711_b);
/* 134 */     this.head.addChild(this.field_178712_c);
/* 135 */     this.horseLeftEar = new ModelRenderer(this, 0, 0);
/* 136 */     this.horseLeftEar.addBox(0.45F, -12.0F, 4.0F, 2, 3, 1);
/* 137 */     this.horseLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 138 */     setBoxRotation(this.horseLeftEar, 0.5235988F, 0.0F, 0.0F);
/* 139 */     this.horseRightEar = new ModelRenderer(this, 0, 0);
/* 140 */     this.horseRightEar.addBox(-2.45F, -12.0F, 4.0F, 2, 3, 1);
/* 141 */     this.horseRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 142 */     setBoxRotation(this.horseRightEar, 0.5235988F, 0.0F, 0.0F);
/* 143 */     this.muleLeftEar = new ModelRenderer(this, 0, 12);
/* 144 */     this.muleLeftEar.addBox(-2.0F, -16.0F, 4.0F, 2, 7, 1);
/* 145 */     this.muleLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 146 */     setBoxRotation(this.muleLeftEar, 0.5235988F, 0.0F, 0.2617994F);
/* 147 */     this.muleRightEar = new ModelRenderer(this, 0, 12);
/* 148 */     this.muleRightEar.addBox(0.0F, -16.0F, 4.0F, 2, 7, 1);
/* 149 */     this.muleRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 150 */     setBoxRotation(this.muleRightEar, 0.5235988F, 0.0F, -0.2617994F);
/* 151 */     this.neck = new ModelRenderer(this, 0, 12);
/* 152 */     this.neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8);
/* 153 */     this.neck.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 154 */     setBoxRotation(this.neck, 0.5235988F, 0.0F, 0.0F);
/* 155 */     this.muleLeftChest = new ModelRenderer(this, 0, 34);
/* 156 */     this.muleLeftChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/* 157 */     this.muleLeftChest.setRotationPoint(-7.5F, 3.0F, 10.0F);
/* 158 */     setBoxRotation(this.muleLeftChest, 0.0F, 1.5707964F, 0.0F);
/* 159 */     this.muleRightChest = new ModelRenderer(this, 0, 47);
/* 160 */     this.muleRightChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/* 161 */     this.muleRightChest.setRotationPoint(4.5F, 3.0F, 10.0F);
/* 162 */     setBoxRotation(this.muleRightChest, 0.0F, 1.5707964F, 0.0F);
/* 163 */     this.horseSaddleBottom = new ModelRenderer(this, 80, 0);
/* 164 */     this.horseSaddleBottom.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8);
/* 165 */     this.horseSaddleBottom.setRotationPoint(0.0F, 2.0F, 2.0F);
/* 166 */     this.horseSaddleFront = new ModelRenderer(this, 106, 9);
/* 167 */     this.horseSaddleFront.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2);
/* 168 */     this.horseSaddleFront.setRotationPoint(0.0F, 2.0F, 2.0F);
/* 169 */     this.horseSaddleBack = new ModelRenderer(this, 80, 9);
/* 170 */     this.horseSaddleBack.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2);
/* 171 */     this.horseSaddleBack.setRotationPoint(0.0F, 2.0F, 2.0F);
/* 172 */     this.horseLeftSaddleMetal = new ModelRenderer(this, 74, 0);
/* 173 */     this.horseLeftSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/* 174 */     this.horseLeftSaddleMetal.setRotationPoint(5.0F, 3.0F, 2.0F);
/* 175 */     this.horseLeftSaddleRope = new ModelRenderer(this, 70, 0);
/* 176 */     this.horseLeftSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/* 177 */     this.horseLeftSaddleRope.setRotationPoint(5.0F, 3.0F, 2.0F);
/* 178 */     this.horseRightSaddleMetal = new ModelRenderer(this, 74, 4);
/* 179 */     this.horseRightSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/* 180 */     this.horseRightSaddleMetal.setRotationPoint(-5.0F, 3.0F, 2.0F);
/* 181 */     this.horseRightSaddleRope = new ModelRenderer(this, 80, 0);
/* 182 */     this.horseRightSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/* 183 */     this.horseRightSaddleRope.setRotationPoint(-5.0F, 3.0F, 2.0F);
/* 184 */     this.horseLeftFaceMetal = new ModelRenderer(this, 74, 13);
/* 185 */     this.horseLeftFaceMetal.addBox(1.5F, -8.0F, -4.0F, 1, 2, 2);
/* 186 */     this.horseLeftFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 187 */     setBoxRotation(this.horseLeftFaceMetal, 0.5235988F, 0.0F, 0.0F);
/* 188 */     this.horseRightFaceMetal = new ModelRenderer(this, 74, 13);
/* 189 */     this.horseRightFaceMetal.addBox(-2.5F, -8.0F, -4.0F, 1, 2, 2);
/* 190 */     this.horseRightFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 191 */     setBoxRotation(this.horseRightFaceMetal, 0.5235988F, 0.0F, 0.0F);
/* 192 */     this.horseLeftRein = new ModelRenderer(this, 44, 10);
/* 193 */     this.horseLeftRein.addBox(2.6F, -6.0F, -6.0F, 0, 3, 16);
/* 194 */     this.horseLeftRein.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 195 */     this.horseRightRein = new ModelRenderer(this, 44, 5);
/* 196 */     this.horseRightRein.addBox(-2.6F, -6.0F, -6.0F, 0, 3, 16);
/* 197 */     this.horseRightRein.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 198 */     this.mane = new ModelRenderer(this, 58, 0);
/* 199 */     this.mane.addBox(-1.0F, -11.5F, 5.0F, 2, 16, 4);
/* 200 */     this.mane.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 201 */     setBoxRotation(this.mane, 0.5235988F, 0.0F, 0.0F);
/* 202 */     this.horseFaceRopes = new ModelRenderer(this, 80, 12);
/* 203 */     this.horseFaceRopes.addBox(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
/* 204 */     this.horseFaceRopes.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 205 */     setBoxRotation(this.horseFaceRopes, 0.5235988F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 213 */     EntityHorse var8 = (EntityHorse)p_78088_1_;
/* 214 */     int var9 = var8.getHorseType();
/* 215 */     float var10 = var8.getGrassEatingAmount(0.0F);
/* 216 */     boolean var11 = var8.isAdultHorse();
/* 217 */     boolean var12 = (var11 && var8.isHorseSaddled());
/* 218 */     boolean var13 = (var11 && var8.isChested());
/* 219 */     boolean var14 = !(var9 != 1 && var9 != 2);
/* 220 */     float var15 = var8.getHorseSize();
/* 221 */     boolean var16 = (var8.riddenByEntity != null);
/*     */     
/* 223 */     if (var12) {
/*     */       
/* 225 */       this.horseFaceRopes.render(p_78088_7_);
/* 226 */       this.horseSaddleBottom.render(p_78088_7_);
/* 227 */       this.horseSaddleFront.render(p_78088_7_);
/* 228 */       this.horseSaddleBack.render(p_78088_7_);
/* 229 */       this.horseLeftSaddleRope.render(p_78088_7_);
/* 230 */       this.horseLeftSaddleMetal.render(p_78088_7_);
/* 231 */       this.horseRightSaddleRope.render(p_78088_7_);
/* 232 */       this.horseRightSaddleMetal.render(p_78088_7_);
/* 233 */       this.horseLeftFaceMetal.render(p_78088_7_);
/* 234 */       this.horseRightFaceMetal.render(p_78088_7_);
/*     */       
/* 236 */       if (var16) {
/*     */         
/* 238 */         this.horseLeftRein.render(p_78088_7_);
/* 239 */         this.horseRightRein.render(p_78088_7_);
/*     */       } 
/*     */     } 
/*     */     
/* 243 */     if (!var11) {
/*     */       
/* 245 */       GlStateManager.pushMatrix();
/* 246 */       GlStateManager.scale(var15, 0.5F + var15 * 0.5F, var15);
/* 247 */       GlStateManager.translate(0.0F, 0.95F * (1.0F - var15), 0.0F);
/*     */     } 
/*     */     
/* 250 */     this.backLeftLeg.render(p_78088_7_);
/* 251 */     this.backLeftShin.render(p_78088_7_);
/* 252 */     this.backLeftHoof.render(p_78088_7_);
/* 253 */     this.backRightLeg.render(p_78088_7_);
/* 254 */     this.backRightShin.render(p_78088_7_);
/* 255 */     this.backRightHoof.render(p_78088_7_);
/* 256 */     this.frontLeftLeg.render(p_78088_7_);
/* 257 */     this.frontLeftShin.render(p_78088_7_);
/* 258 */     this.frontLeftHoof.render(p_78088_7_);
/* 259 */     this.frontRightLeg.render(p_78088_7_);
/* 260 */     this.frontRightShin.render(p_78088_7_);
/* 261 */     this.frontRightHoof.render(p_78088_7_);
/*     */     
/* 263 */     if (!var11) {
/*     */       
/* 265 */       GlStateManager.popMatrix();
/* 266 */       GlStateManager.pushMatrix();
/* 267 */       GlStateManager.scale(var15, var15, var15);
/* 268 */       GlStateManager.translate(0.0F, 1.35F * (1.0F - var15), 0.0F);
/*     */     } 
/*     */     
/* 271 */     this.body.render(p_78088_7_);
/* 272 */     this.tailBase.render(p_78088_7_);
/* 273 */     this.tailMiddle.render(p_78088_7_);
/* 274 */     this.tailTip.render(p_78088_7_);
/* 275 */     this.neck.render(p_78088_7_);
/* 276 */     this.mane.render(p_78088_7_);
/*     */     
/* 278 */     if (!var11) {
/*     */       
/* 280 */       GlStateManager.popMatrix();
/* 281 */       GlStateManager.pushMatrix();
/* 282 */       float var17 = 0.5F + var15 * var15 * 0.5F;
/* 283 */       GlStateManager.scale(var17, var17, var17);
/*     */       
/* 285 */       if (var10 <= 0.0F) {
/*     */         
/* 287 */         GlStateManager.translate(0.0F, 1.35F * (1.0F - var15), 0.0F);
/*     */       }
/*     */       else {
/*     */         
/* 291 */         GlStateManager.translate(0.0F, 0.9F * (1.0F - var15) * var10 + 1.35F * (1.0F - var15) * (1.0F - var10), 0.15F * (1.0F - var15) * var10);
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     if (var14) {
/*     */       
/* 297 */       this.muleLeftEar.render(p_78088_7_);
/* 298 */       this.muleRightEar.render(p_78088_7_);
/*     */     }
/*     */     else {
/*     */       
/* 302 */       this.horseLeftEar.render(p_78088_7_);
/* 303 */       this.horseRightEar.render(p_78088_7_);
/*     */     } 
/*     */     
/* 306 */     this.head.render(p_78088_7_);
/*     */     
/* 308 */     if (!var11)
/*     */     {
/* 310 */       GlStateManager.popMatrix();
/*     */     }
/*     */     
/* 313 */     if (var13) {
/*     */       
/* 315 */       this.muleLeftChest.render(p_78088_7_);
/* 316 */       this.muleRightChest.render(p_78088_7_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBoxRotation(ModelRenderer p_110682_1_, float p_110682_2_, float p_110682_3_, float p_110682_4_) {
/* 325 */     p_110682_1_.rotateAngleX = p_110682_2_;
/* 326 */     p_110682_1_.rotateAngleY = p_110682_3_;
/* 327 */     p_110682_1_.rotateAngleZ = p_110682_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float updateHorseRotation(float p_110683_1_, float p_110683_2_, float p_110683_3_) {
/*     */     float var4;
/* 337 */     for (var4 = p_110683_2_ - p_110683_1_; var4 < -180.0F; var4 += 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 342 */     while (var4 >= 180.0F)
/*     */     {
/* 344 */       var4 -= 360.0F;
/*     */     }
/*     */     
/* 347 */     return p_110683_1_ + p_110683_3_ * var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 356 */     super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
/* 357 */     float var5 = updateHorseRotation(p_78086_1_.prevRenderYawOffset, p_78086_1_.renderYawOffset, p_78086_4_);
/* 358 */     float var6 = updateHorseRotation(p_78086_1_.prevRotationYawHead, p_78086_1_.rotationYawHead, p_78086_4_);
/* 359 */     float var7 = p_78086_1_.prevRotationPitch + (p_78086_1_.rotationPitch - p_78086_1_.prevRotationPitch) * p_78086_4_;
/* 360 */     float var8 = var6 - var5;
/* 361 */     float var9 = var7 / 57.295776F;
/*     */     
/* 363 */     if (var8 > 20.0F)
/*     */     {
/* 365 */       var8 = 20.0F;
/*     */     }
/*     */     
/* 368 */     if (var8 < -20.0F)
/*     */     {
/* 370 */       var8 = -20.0F;
/*     */     }
/*     */     
/* 373 */     if (p_78086_3_ > 0.2F)
/*     */     {
/* 375 */       var9 += MathHelper.cos(p_78086_2_ * 0.4F) * 0.15F * p_78086_3_;
/*     */     }
/*     */     
/* 378 */     EntityHorse var10 = (EntityHorse)p_78086_1_;
/* 379 */     float var11 = var10.getGrassEatingAmount(p_78086_4_);
/* 380 */     float var12 = var10.getRearingAmount(p_78086_4_);
/* 381 */     float var13 = 1.0F - var12;
/* 382 */     float var14 = var10.func_110201_q(p_78086_4_);
/* 383 */     boolean var15 = (var10.field_110278_bp != 0);
/* 384 */     boolean var16 = var10.isHorseSaddled();
/* 385 */     boolean var17 = (var10.riddenByEntity != null);
/* 386 */     float var18 = p_78086_1_.ticksExisted + p_78086_4_;
/* 387 */     float var19 = MathHelper.cos(p_78086_2_ * 0.6662F + 3.1415927F);
/* 388 */     float var20 = var19 * 0.8F * p_78086_3_;
/* 389 */     this.head.rotationPointY = 4.0F;
/* 390 */     this.head.rotationPointZ = -10.0F;
/* 391 */     this.tailBase.rotationPointY = 3.0F;
/* 392 */     this.tailMiddle.rotationPointZ = 14.0F;
/* 393 */     this.muleRightChest.rotationPointY = 3.0F;
/* 394 */     this.muleRightChest.rotationPointZ = 10.0F;
/* 395 */     this.body.rotateAngleX = 0.0F;
/* 396 */     this.head.rotateAngleX = 0.5235988F + var9;
/* 397 */     this.head.rotateAngleY = var8 / 57.295776F;
/* 398 */     this.head.rotateAngleX = var12 * (0.2617994F + var9) + var11 * 2.18166F + (1.0F - Math.max(var12, var11)) * this.head.rotateAngleX;
/* 399 */     this.head.rotateAngleY = var12 * var8 / 57.295776F + (1.0F - Math.max(var12, var11)) * this.head.rotateAngleY;
/* 400 */     this.head.rotationPointY = var12 * -6.0F + var11 * 11.0F + (1.0F - Math.max(var12, var11)) * this.head.rotationPointY;
/* 401 */     this.head.rotationPointZ = var12 * -1.0F + var11 * -10.0F + (1.0F - Math.max(var12, var11)) * this.head.rotationPointZ;
/* 402 */     this.tailBase.rotationPointY = var12 * 9.0F + var13 * this.tailBase.rotationPointY;
/* 403 */     this.tailMiddle.rotationPointZ = var12 * 18.0F + var13 * this.tailMiddle.rotationPointZ;
/* 404 */     this.muleRightChest.rotationPointY = var12 * 5.5F + var13 * this.muleRightChest.rotationPointY;
/* 405 */     this.muleRightChest.rotationPointZ = var12 * 15.0F + var13 * this.muleRightChest.rotationPointZ;
/* 406 */     this.body.rotateAngleX = var12 * -45.0F / 57.295776F + var13 * this.body.rotateAngleX;
/* 407 */     this.horseLeftEar.rotationPointY = this.head.rotationPointY;
/* 408 */     this.horseRightEar.rotationPointY = this.head.rotationPointY;
/* 409 */     this.muleLeftEar.rotationPointY = this.head.rotationPointY;
/* 410 */     this.muleRightEar.rotationPointY = this.head.rotationPointY;
/* 411 */     this.neck.rotationPointY = this.head.rotationPointY;
/* 412 */     this.field_178711_b.rotationPointY = 0.02F;
/* 413 */     this.field_178712_c.rotationPointY = 0.0F;
/* 414 */     this.mane.rotationPointY = this.head.rotationPointY;
/* 415 */     this.horseLeftEar.rotationPointZ = this.head.rotationPointZ;
/* 416 */     this.horseRightEar.rotationPointZ = this.head.rotationPointZ;
/* 417 */     this.muleLeftEar.rotationPointZ = this.head.rotationPointZ;
/* 418 */     this.muleRightEar.rotationPointZ = this.head.rotationPointZ;
/* 419 */     this.neck.rotationPointZ = this.head.rotationPointZ;
/* 420 */     this.field_178711_b.rotationPointZ = 0.02F - var14 * 1.0F;
/* 421 */     this.field_178712_c.rotationPointZ = 0.0F + var14 * 1.0F;
/* 422 */     this.mane.rotationPointZ = this.head.rotationPointZ;
/* 423 */     this.horseLeftEar.rotateAngleX = this.head.rotateAngleX;
/* 424 */     this.horseRightEar.rotateAngleX = this.head.rotateAngleX;
/* 425 */     this.muleLeftEar.rotateAngleX = this.head.rotateAngleX;
/* 426 */     this.muleRightEar.rotateAngleX = this.head.rotateAngleX;
/* 427 */     this.neck.rotateAngleX = this.head.rotateAngleX;
/* 428 */     this.field_178711_b.rotateAngleX = 0.0F - 0.09424778F * var14;
/* 429 */     this.field_178712_c.rotateAngleX = 0.0F + 0.15707964F * var14;
/* 430 */     this.mane.rotateAngleX = this.head.rotateAngleX;
/* 431 */     this.horseLeftEar.rotateAngleY = this.head.rotateAngleY;
/* 432 */     this.horseRightEar.rotateAngleY = this.head.rotateAngleY;
/* 433 */     this.muleLeftEar.rotateAngleY = this.head.rotateAngleY;
/* 434 */     this.muleRightEar.rotateAngleY = this.head.rotateAngleY;
/* 435 */     this.neck.rotateAngleY = this.head.rotateAngleY;
/* 436 */     this.field_178711_b.rotateAngleY = 0.0F;
/* 437 */     this.field_178712_c.rotateAngleY = 0.0F;
/* 438 */     this.mane.rotateAngleY = this.head.rotateAngleY;
/* 439 */     this.muleLeftChest.rotateAngleX = var20 / 5.0F;
/* 440 */     this.muleRightChest.rotateAngleX = -var20 / 5.0F;
/* 441 */     float var21 = 1.5707964F;
/* 442 */     float var22 = 4.712389F;
/* 443 */     float var23 = -1.0471976F;
/* 444 */     float var24 = 0.2617994F * var12;
/* 445 */     float var25 = MathHelper.cos(var18 * 0.6F + 3.1415927F);
/* 446 */     this.frontLeftLeg.rotationPointY = -2.0F * var12 + 9.0F * var13;
/* 447 */     this.frontLeftLeg.rotationPointZ = -2.0F * var12 + -8.0F * var13;
/* 448 */     this.frontRightLeg.rotationPointY = this.frontLeftLeg.rotationPointY;
/* 449 */     this.frontRightLeg.rotationPointZ = this.frontLeftLeg.rotationPointZ;
/* 450 */     this.backLeftLeg.rotationPointY += MathHelper.sin(1.5707964F + var24 + var13 * -var19 * 0.5F * p_78086_3_) * 7.0F;
/* 451 */     this.backLeftLeg.rotationPointZ += MathHelper.cos(4.712389F + var24 + var13 * -var19 * 0.5F * p_78086_3_) * 7.0F;
/* 452 */     this.backRightLeg.rotationPointY += MathHelper.sin(1.5707964F + var24 + var13 * var19 * 0.5F * p_78086_3_) * 7.0F;
/* 453 */     this.backRightLeg.rotationPointZ += MathHelper.cos(4.712389F + var24 + var13 * var19 * 0.5F * p_78086_3_) * 7.0F;
/* 454 */     float var26 = (-1.0471976F + var25) * var12 + var20 * var13;
/* 455 */     float var27 = (-1.0471976F + -var25) * var12 + -var20 * var13;
/* 456 */     this.frontLeftLeg.rotationPointY += MathHelper.sin(1.5707964F + var26) * 7.0F;
/* 457 */     this.frontLeftLeg.rotationPointZ += MathHelper.cos(4.712389F + var26) * 7.0F;
/* 458 */     this.frontRightLeg.rotationPointY += MathHelper.sin(1.5707964F + var27) * 7.0F;
/* 459 */     this.frontRightLeg.rotationPointZ += MathHelper.cos(4.712389F + var27) * 7.0F;
/* 460 */     this.backLeftLeg.rotateAngleX = var24 + -var19 * 0.5F * p_78086_3_ * var13;
/* 461 */     this.backLeftShin.rotateAngleX = -0.08726646F * var12 + (-var19 * 0.5F * p_78086_3_ - Math.max(0.0F, var19 * 0.5F * p_78086_3_)) * var13;
/* 462 */     this.backLeftHoof.rotateAngleX = this.backLeftShin.rotateAngleX;
/* 463 */     this.backRightLeg.rotateAngleX = var24 + var19 * 0.5F * p_78086_3_ * var13;
/* 464 */     this.backRightShin.rotateAngleX = -0.08726646F * var12 + (var19 * 0.5F * p_78086_3_ - Math.max(0.0F, -var19 * 0.5F * p_78086_3_)) * var13;
/* 465 */     this.backRightHoof.rotateAngleX = this.backRightShin.rotateAngleX;
/* 466 */     this.frontLeftLeg.rotateAngleX = var26;
/* 467 */     this.frontLeftShin.rotateAngleX = (this.frontLeftLeg.rotateAngleX + 3.1415927F * Math.max(0.0F, 0.2F + var25 * 0.2F)) * var12 + (var20 + Math.max(0.0F, var19 * 0.5F * p_78086_3_)) * var13;
/* 468 */     this.frontLeftHoof.rotateAngleX = this.frontLeftShin.rotateAngleX;
/* 469 */     this.frontRightLeg.rotateAngleX = var27;
/* 470 */     this.frontRightShin.rotateAngleX = (this.frontRightLeg.rotateAngleX + 3.1415927F * Math.max(0.0F, 0.2F - var25 * 0.2F)) * var12 + (-var20 + Math.max(0.0F, -var19 * 0.5F * p_78086_3_)) * var13;
/* 471 */     this.frontRightHoof.rotateAngleX = this.frontRightShin.rotateAngleX;
/* 472 */     this.backLeftHoof.rotationPointY = this.backLeftShin.rotationPointY;
/* 473 */     this.backLeftHoof.rotationPointZ = this.backLeftShin.rotationPointZ;
/* 474 */     this.backRightHoof.rotationPointY = this.backRightShin.rotationPointY;
/* 475 */     this.backRightHoof.rotationPointZ = this.backRightShin.rotationPointZ;
/* 476 */     this.frontLeftHoof.rotationPointY = this.frontLeftShin.rotationPointY;
/* 477 */     this.frontLeftHoof.rotationPointZ = this.frontLeftShin.rotationPointZ;
/* 478 */     this.frontRightHoof.rotationPointY = this.frontRightShin.rotationPointY;
/* 479 */     this.frontRightHoof.rotationPointZ = this.frontRightShin.rotationPointZ;
/*     */     
/* 481 */     if (var16) {
/*     */       
/* 483 */       this.horseSaddleBottom.rotationPointY = var12 * 0.5F + var13 * 2.0F;
/* 484 */       this.horseSaddleBottom.rotationPointZ = var12 * 11.0F + var13 * 2.0F;
/* 485 */       this.horseSaddleFront.rotationPointY = this.horseSaddleBottom.rotationPointY;
/* 486 */       this.horseSaddleBack.rotationPointY = this.horseSaddleBottom.rotationPointY;
/* 487 */       this.horseLeftSaddleRope.rotationPointY = this.horseSaddleBottom.rotationPointY;
/* 488 */       this.horseRightSaddleRope.rotationPointY = this.horseSaddleBottom.rotationPointY;
/* 489 */       this.horseLeftSaddleMetal.rotationPointY = this.horseSaddleBottom.rotationPointY;
/* 490 */       this.horseRightSaddleMetal.rotationPointY = this.horseSaddleBottom.rotationPointY;
/* 491 */       this.muleLeftChest.rotationPointY = this.muleRightChest.rotationPointY;
/* 492 */       this.horseSaddleFront.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
/* 493 */       this.horseSaddleBack.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
/* 494 */       this.horseLeftSaddleRope.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
/* 495 */       this.horseRightSaddleRope.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
/* 496 */       this.horseLeftSaddleMetal.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
/* 497 */       this.horseRightSaddleMetal.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
/* 498 */       this.muleLeftChest.rotationPointZ = this.muleRightChest.rotationPointZ;
/* 499 */       this.horseSaddleBottom.rotateAngleX = this.body.rotateAngleX;
/* 500 */       this.horseSaddleFront.rotateAngleX = this.body.rotateAngleX;
/* 501 */       this.horseSaddleBack.rotateAngleX = this.body.rotateAngleX;
/* 502 */       this.horseLeftRein.rotationPointY = this.head.rotationPointY;
/* 503 */       this.horseRightRein.rotationPointY = this.head.rotationPointY;
/* 504 */       this.horseFaceRopes.rotationPointY = this.head.rotationPointY;
/* 505 */       this.horseLeftFaceMetal.rotationPointY = this.head.rotationPointY;
/* 506 */       this.horseRightFaceMetal.rotationPointY = this.head.rotationPointY;
/* 507 */       this.horseLeftRein.rotationPointZ = this.head.rotationPointZ;
/* 508 */       this.horseRightRein.rotationPointZ = this.head.rotationPointZ;
/* 509 */       this.horseFaceRopes.rotationPointZ = this.head.rotationPointZ;
/* 510 */       this.horseLeftFaceMetal.rotationPointZ = this.head.rotationPointZ;
/* 511 */       this.horseRightFaceMetal.rotationPointZ = this.head.rotationPointZ;
/* 512 */       this.horseLeftRein.rotateAngleX = var9;
/* 513 */       this.horseRightRein.rotateAngleX = var9;
/* 514 */       this.horseFaceRopes.rotateAngleX = this.head.rotateAngleX;
/* 515 */       this.horseLeftFaceMetal.rotateAngleX = this.head.rotateAngleX;
/* 516 */       this.horseRightFaceMetal.rotateAngleX = this.head.rotateAngleX;
/* 517 */       this.horseFaceRopes.rotateAngleY = this.head.rotateAngleY;
/* 518 */       this.horseLeftFaceMetal.rotateAngleY = this.head.rotateAngleY;
/* 519 */       this.horseLeftRein.rotateAngleY = this.head.rotateAngleY;
/* 520 */       this.horseRightFaceMetal.rotateAngleY = this.head.rotateAngleY;
/* 521 */       this.horseRightRein.rotateAngleY = this.head.rotateAngleY;
/*     */       
/* 523 */       if (var17) {
/*     */         
/* 525 */         this.horseLeftSaddleRope.rotateAngleX = -1.0471976F;
/* 526 */         this.horseLeftSaddleMetal.rotateAngleX = -1.0471976F;
/* 527 */         this.horseRightSaddleRope.rotateAngleX = -1.0471976F;
/* 528 */         this.horseRightSaddleMetal.rotateAngleX = -1.0471976F;
/* 529 */         this.horseLeftSaddleRope.rotateAngleZ = 0.0F;
/* 530 */         this.horseLeftSaddleMetal.rotateAngleZ = 0.0F;
/* 531 */         this.horseRightSaddleRope.rotateAngleZ = 0.0F;
/* 532 */         this.horseRightSaddleMetal.rotateAngleZ = 0.0F;
/*     */       }
/*     */       else {
/*     */         
/* 536 */         this.horseLeftSaddleRope.rotateAngleX = var20 / 3.0F;
/* 537 */         this.horseLeftSaddleMetal.rotateAngleX = var20 / 3.0F;
/* 538 */         this.horseRightSaddleRope.rotateAngleX = var20 / 3.0F;
/* 539 */         this.horseRightSaddleMetal.rotateAngleX = var20 / 3.0F;
/* 540 */         this.horseLeftSaddleRope.rotateAngleZ = var20 / 5.0F;
/* 541 */         this.horseLeftSaddleMetal.rotateAngleZ = var20 / 5.0F;
/* 542 */         this.horseRightSaddleRope.rotateAngleZ = -var20 / 5.0F;
/* 543 */         this.horseRightSaddleMetal.rotateAngleZ = -var20 / 5.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 547 */     var21 = -1.3089F + p_78086_3_ * 1.5F;
/*     */     
/* 549 */     if (var21 > 0.0F)
/*     */     {
/* 551 */       var21 = 0.0F;
/*     */     }
/*     */     
/* 554 */     if (var15) {
/*     */       
/* 556 */       this.tailBase.rotateAngleY = MathHelper.cos(var18 * 0.7F);
/* 557 */       var21 = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 561 */       this.tailBase.rotateAngleY = 0.0F;
/*     */     } 
/*     */     
/* 564 */     this.tailMiddle.rotateAngleY = this.tailBase.rotateAngleY;
/* 565 */     this.tailTip.rotateAngleY = this.tailBase.rotateAngleY;
/* 566 */     this.tailMiddle.rotationPointY = this.tailBase.rotationPointY;
/* 567 */     this.tailTip.rotationPointY = this.tailBase.rotationPointY;
/* 568 */     this.tailMiddle.rotationPointZ = this.tailBase.rotationPointZ;
/* 569 */     this.tailTip.rotationPointZ = this.tailBase.rotationPointZ;
/* 570 */     this.tailBase.rotateAngleX = var21;
/* 571 */     this.tailMiddle.rotateAngleX = var21;
/* 572 */     this.tailTip.rotateAngleX = -0.2618F + var21;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */