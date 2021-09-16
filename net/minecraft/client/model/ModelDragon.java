/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.EntityDragon;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelDragon
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer head;
/*     */   private ModelRenderer spine;
/*     */   private ModelRenderer jaw;
/*     */   private ModelRenderer body;
/*     */   private ModelRenderer rearLeg;
/*     */   private ModelRenderer frontLeg;
/*     */   private ModelRenderer rearLegTip;
/*     */   private ModelRenderer frontLegTip;
/*     */   private ModelRenderer rearFoot;
/*     */   private ModelRenderer frontFoot;
/*     */   private ModelRenderer wing;
/*     */   private ModelRenderer wingTip;
/*     */   private float partialTicks;
/*     */   private static final String __OBFID = "CL_00000870";
/*     */   
/*     */   public ModelDragon(float p_i46360_1_) {
/*  50 */     this.textureWidth = 256;
/*  51 */     this.textureHeight = 256;
/*  52 */     setTextureOffset("body.body", 0, 0);
/*  53 */     setTextureOffset("wing.skin", -56, 88);
/*  54 */     setTextureOffset("wingtip.skin", -56, 144);
/*  55 */     setTextureOffset("rearleg.main", 0, 0);
/*  56 */     setTextureOffset("rearfoot.main", 112, 0);
/*  57 */     setTextureOffset("rearlegtip.main", 196, 0);
/*  58 */     setTextureOffset("head.upperhead", 112, 30);
/*  59 */     setTextureOffset("wing.bone", 112, 88);
/*  60 */     setTextureOffset("head.upperlip", 176, 44);
/*  61 */     setTextureOffset("jaw.jaw", 176, 65);
/*  62 */     setTextureOffset("frontleg.main", 112, 104);
/*  63 */     setTextureOffset("wingtip.bone", 112, 136);
/*  64 */     setTextureOffset("frontfoot.main", 144, 104);
/*  65 */     setTextureOffset("neck.box", 192, 104);
/*  66 */     setTextureOffset("frontlegtip.main", 226, 138);
/*  67 */     setTextureOffset("body.scale", 220, 53);
/*  68 */     setTextureOffset("head.scale", 0, 0);
/*  69 */     setTextureOffset("neck.scale", 48, 0);
/*  70 */     setTextureOffset("head.nostril", 112, 0);
/*  71 */     float var2 = -16.0F;
/*  72 */     this.head = new ModelRenderer(this, "head");
/*  73 */     this.head.addBox("upperlip", -6.0F, -1.0F, -8.0F + var2, 12, 5, 16);
/*  74 */     this.head.addBox("upperhead", -8.0F, -8.0F, 6.0F + var2, 16, 16, 16);
/*  75 */     this.head.mirror = true;
/*  76 */     this.head.addBox("scale", -5.0F, -12.0F, 12.0F + var2, 2, 4, 6);
/*  77 */     this.head.addBox("nostril", -5.0F, -3.0F, -6.0F + var2, 2, 2, 4);
/*  78 */     this.head.mirror = false;
/*  79 */     this.head.addBox("scale", 3.0F, -12.0F, 12.0F + var2, 2, 4, 6);
/*  80 */     this.head.addBox("nostril", 3.0F, -3.0F, -6.0F + var2, 2, 2, 4);
/*  81 */     this.jaw = new ModelRenderer(this, "jaw");
/*  82 */     this.jaw.setRotationPoint(0.0F, 4.0F, 8.0F + var2);
/*  83 */     this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
/*  84 */     this.head.addChild(this.jaw);
/*  85 */     this.spine = new ModelRenderer(this, "neck");
/*  86 */     this.spine.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
/*  87 */     this.spine.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
/*  88 */     this.body = new ModelRenderer(this, "body");
/*  89 */     this.body.setRotationPoint(0.0F, 4.0F, 8.0F);
/*  90 */     this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
/*  91 */     this.body.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
/*  92 */     this.body.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
/*  93 */     this.body.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
/*  94 */     this.wing = new ModelRenderer(this, "wing");
/*  95 */     this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
/*  96 */     this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
/*  97 */     this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
/*  98 */     this.wingTip = new ModelRenderer(this, "wingtip");
/*  99 */     this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
/* 100 */     this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
/* 101 */     this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
/* 102 */     this.wing.addChild(this.wingTip);
/* 103 */     this.frontLeg = new ModelRenderer(this, "frontleg");
/* 104 */     this.frontLeg.setRotationPoint(-12.0F, 20.0F, 2.0F);
/* 105 */     this.frontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
/* 106 */     this.frontLegTip = new ModelRenderer(this, "frontlegtip");
/* 107 */     this.frontLegTip.setRotationPoint(0.0F, 20.0F, -1.0F);
/* 108 */     this.frontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
/* 109 */     this.frontLeg.addChild(this.frontLegTip);
/* 110 */     this.frontFoot = new ModelRenderer(this, "frontfoot");
/* 111 */     this.frontFoot.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 112 */     this.frontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
/* 113 */     this.frontLegTip.addChild(this.frontFoot);
/* 114 */     this.rearLeg = new ModelRenderer(this, "rearleg");
/* 115 */     this.rearLeg.setRotationPoint(-16.0F, 16.0F, 42.0F);
/* 116 */     this.rearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
/* 117 */     this.rearLegTip = new ModelRenderer(this, "rearlegtip");
/* 118 */     this.rearLegTip.setRotationPoint(0.0F, 32.0F, -4.0F);
/* 119 */     this.rearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
/* 120 */     this.rearLeg.addChild(this.rearLegTip);
/* 121 */     this.rearFoot = new ModelRenderer(this, "rearfoot");
/* 122 */     this.rearFoot.setRotationPoint(0.0F, 31.0F, 4.0F);
/* 123 */     this.rearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
/* 124 */     this.rearLegTip.addChild(this.rearFoot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 133 */     this.partialTicks = p_78086_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 141 */     GlStateManager.pushMatrix();
/* 142 */     EntityDragon var8 = (EntityDragon)p_78088_1_;
/* 143 */     float var9 = var8.prevAnimTime + (var8.animTime - var8.prevAnimTime) * this.partialTicks;
/* 144 */     this.jaw.rotateAngleX = (float)(Math.sin((var9 * 3.1415927F * 2.0F)) + 1.0D) * 0.2F;
/* 145 */     float var10 = (float)(Math.sin((var9 * 3.1415927F * 2.0F - 1.0F)) + 1.0D);
/* 146 */     var10 = (var10 * var10 * 1.0F + var10 * 2.0F) * 0.05F;
/* 147 */     GlStateManager.translate(0.0F, var10 - 2.0F, -3.0F);
/* 148 */     GlStateManager.rotate(var10 * 2.0F, 1.0F, 0.0F, 0.0F);
/* 149 */     float var11 = -30.0F;
/* 150 */     float var13 = 0.0F;
/* 151 */     float var14 = 1.5F;
/* 152 */     double[] var15 = var8.getMovementOffsets(6, this.partialTicks);
/* 153 */     float var16 = updateRotations(var8.getMovementOffsets(5, this.partialTicks)[0] - var8.getMovementOffsets(10, this.partialTicks)[0]);
/* 154 */     float var17 = updateRotations(var8.getMovementOffsets(5, this.partialTicks)[0] + (var16 / 2.0F));
/* 155 */     var11 += 2.0F;
/* 156 */     float var18 = var9 * 3.1415927F * 2.0F;
/* 157 */     var11 = 20.0F;
/* 158 */     float var12 = -12.0F;
/*     */ 
/*     */     
/* 161 */     for (int var19 = 0; var19 < 5; var19++) {
/*     */       
/* 163 */       double[] var20 = var8.getMovementOffsets(5 - var19, this.partialTicks);
/* 164 */       float var21 = (float)Math.cos((var19 * 0.45F + var18)) * 0.15F;
/* 165 */       this.spine.rotateAngleY = updateRotations(var20[0] - var15[0]) * 3.1415927F / 180.0F * var14;
/* 166 */       this.spine.rotateAngleX = var21 + (float)(var20[1] - var15[1]) * 3.1415927F / 180.0F * var14 * 5.0F;
/* 167 */       this.spine.rotateAngleZ = -updateRotations(var20[0] - var17) * 3.1415927F / 180.0F * var14;
/* 168 */       this.spine.rotationPointY = var11;
/* 169 */       this.spine.rotationPointZ = var12;
/* 170 */       this.spine.rotationPointX = var13;
/* 171 */       var11 = (float)(var11 + Math.sin(this.spine.rotateAngleX) * 10.0D);
/* 172 */       var12 = (float)(var12 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 173 */       var13 = (float)(var13 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 174 */       this.spine.render(p_78088_7_);
/*     */     } 
/*     */     
/* 177 */     this.head.rotationPointY = var11;
/* 178 */     this.head.rotationPointZ = var12;
/* 179 */     this.head.rotationPointX = var13;
/* 180 */     double[] var22 = var8.getMovementOffsets(0, this.partialTicks);
/* 181 */     this.head.rotateAngleY = updateRotations(var22[0] - var15[0]) * 3.1415927F / 180.0F * 1.0F;
/* 182 */     this.head.rotateAngleZ = -updateRotations(var22[0] - var17) * 3.1415927F / 180.0F * 1.0F;
/* 183 */     this.head.render(p_78088_7_);
/* 184 */     GlStateManager.pushMatrix();
/* 185 */     GlStateManager.translate(0.0F, 1.0F, 0.0F);
/* 186 */     GlStateManager.rotate(-var16 * var14 * 1.0F, 0.0F, 0.0F, 1.0F);
/* 187 */     GlStateManager.translate(0.0F, -1.0F, 0.0F);
/* 188 */     this.body.rotateAngleZ = 0.0F;
/* 189 */     this.body.render(p_78088_7_);
/*     */     
/* 191 */     for (int var23 = 0; var23 < 2; var23++) {
/*     */       
/* 193 */       GlStateManager.enableCull();
/* 194 */       float var21 = var9 * 3.1415927F * 2.0F;
/* 195 */       this.wing.rotateAngleX = 0.125F - (float)Math.cos(var21) * 0.2F;
/* 196 */       this.wing.rotateAngleY = 0.25F;
/* 197 */       this.wing.rotateAngleZ = (float)(Math.sin(var21) + 0.125D) * 0.8F;
/* 198 */       this.wingTip.rotateAngleZ = -((float)(Math.sin((var21 + 2.0F)) + 0.5D)) * 0.75F;
/* 199 */       this.rearLeg.rotateAngleX = 1.0F + var10 * 0.1F;
/* 200 */       this.rearLegTip.rotateAngleX = 0.5F + var10 * 0.1F;
/* 201 */       this.rearFoot.rotateAngleX = 0.75F + var10 * 0.1F;
/* 202 */       this.frontLeg.rotateAngleX = 1.3F + var10 * 0.1F;
/* 203 */       this.frontLegTip.rotateAngleX = -0.5F - var10 * 0.1F;
/* 204 */       this.frontFoot.rotateAngleX = 0.75F + var10 * 0.1F;
/* 205 */       this.wing.render(p_78088_7_);
/* 206 */       this.frontLeg.render(p_78088_7_);
/* 207 */       this.rearLeg.render(p_78088_7_);
/* 208 */       GlStateManager.scale(-1.0F, 1.0F, 1.0F);
/*     */       
/* 210 */       if (var23 == 0)
/*     */       {
/* 212 */         GlStateManager.cullFace(1028);
/*     */       }
/*     */     } 
/*     */     
/* 216 */     GlStateManager.popMatrix();
/* 217 */     GlStateManager.cullFace(1029);
/* 218 */     GlStateManager.disableCull();
/* 219 */     float var24 = -((float)Math.sin((var9 * 3.1415927F * 2.0F))) * 0.0F;
/* 220 */     var18 = var9 * 3.1415927F * 2.0F;
/* 221 */     var11 = 10.0F;
/* 222 */     var12 = 60.0F;
/* 223 */     var13 = 0.0F;
/* 224 */     var15 = var8.getMovementOffsets(11, this.partialTicks);
/*     */     
/* 226 */     for (int var25 = 0; var25 < 12; var25++) {
/*     */       
/* 228 */       var22 = var8.getMovementOffsets(12 + var25, this.partialTicks);
/* 229 */       var24 = (float)(var24 + Math.sin((var25 * 0.45F + var18)) * 0.05000000074505806D);
/* 230 */       this.spine.rotateAngleY = (updateRotations(var22[0] - var15[0]) * var14 + 180.0F) * 3.1415927F / 180.0F;
/* 231 */       this.spine.rotateAngleX = var24 + (float)(var22[1] - var15[1]) * 3.1415927F / 180.0F * var14 * 5.0F;
/* 232 */       this.spine.rotateAngleZ = updateRotations(var22[0] - var17) * 3.1415927F / 180.0F * var14;
/* 233 */       this.spine.rotationPointY = var11;
/* 234 */       this.spine.rotationPointZ = var12;
/* 235 */       this.spine.rotationPointX = var13;
/* 236 */       var11 = (float)(var11 + Math.sin(this.spine.rotateAngleX) * 10.0D);
/* 237 */       var12 = (float)(var12 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 238 */       var13 = (float)(var13 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 239 */       this.spine.render(p_78088_7_);
/*     */     } 
/*     */     
/* 242 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float updateRotations(double p_78214_1_) {
/* 252 */     while (p_78214_1_ >= 180.0D)
/*     */     {
/* 254 */       p_78214_1_ -= 360.0D;
/*     */     }
/*     */     
/* 257 */     while (p_78214_1_ < -180.0D)
/*     */     {
/* 259 */       p_78214_1_ += 360.0D;
/*     */     }
/*     */     
/* 262 */     return (float)p_78214_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */