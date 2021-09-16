/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelHumanoidHead;
/*     */ import net.minecraft.client.model.ModelSkeletonHead;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class TileEntitySkullRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  22 */   private static final ResourceLocation field_147537_c = new ResourceLocation("textures/entity/skeleton/skeleton.png");
/*  23 */   private static final ResourceLocation field_147534_d = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
/*  24 */   private static final ResourceLocation field_147535_e = new ResourceLocation("textures/entity/zombie/zombie.png");
/*  25 */   private static final ResourceLocation field_147532_f = new ResourceLocation("textures/entity/creeper/creeper.png");
/*     */   public static TileEntitySkullRenderer instance;
/*  27 */   private final ModelSkeletonHead field_178467_h = new ModelSkeletonHead(0, 0, 64, 32);
/*  28 */   private final ModelSkeletonHead field_178468_i = (ModelSkeletonHead)new ModelHumanoidHead();
/*     */   
/*     */   private static final String __OBFID = "CL_00000971";
/*     */   
/*     */   public void func_180542_a(TileEntitySkull p_180542_1_, double p_180542_2_, double p_180542_4_, double p_180542_6_, float p_180542_8_, int p_180542_9_) {
/*  33 */     EnumFacing var10 = EnumFacing.getFront(p_180542_1_.getBlockMetadata() & 0x7);
/*  34 */     renderSkull((float)p_180542_2_, (float)p_180542_4_, (float)p_180542_6_, var10, (p_180542_1_.getSkullRotation() * 360) / 16.0F, p_180542_1_.getSkullType(), p_180542_1_.getPlayerProfile(), p_180542_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRendererDispatcher(TileEntityRendererDispatcher p_147497_1_) {
/*  39 */     super.setRendererDispatcher(p_147497_1_);
/*  40 */     instance = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderSkull(float p_180543_1_, float p_180543_2_, float p_180543_3_, EnumFacing p_180543_4_, float p_180543_5_, int p_180543_6_, GameProfile p_180543_7_, int p_180543_8_) {
/*  45 */     ModelSkeletonHead var9 = this.field_178467_h;
/*     */     
/*  47 */     if (p_180543_8_ >= 0) {
/*     */       
/*  49 */       bindTexture(DESTROY_STAGES[p_180543_8_]);
/*  50 */       GlStateManager.matrixMode(5890);
/*  51 */       GlStateManager.pushMatrix();
/*  52 */       GlStateManager.scale(4.0F, 2.0F, 1.0F);
/*  53 */       GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
/*  54 */       GlStateManager.matrixMode(5888);
/*     */     } else {
/*     */       ResourceLocation var10;
/*     */       
/*  58 */       switch (p_180543_6_) {
/*     */ 
/*     */         
/*     */         default:
/*  62 */           bindTexture(field_147537_c);
/*     */           break;
/*     */         
/*     */         case 1:
/*  66 */           bindTexture(field_147534_d);
/*     */           break;
/*     */         
/*     */         case 2:
/*  70 */           bindTexture(field_147535_e);
/*  71 */           var9 = this.field_178468_i;
/*     */           break;
/*     */         
/*     */         case 3:
/*  75 */           var9 = this.field_178468_i;
/*  76 */           var10 = DefaultPlayerSkin.func_177335_a();
/*     */           
/*  78 */           if (p_180543_7_ != null) {
/*     */             
/*  80 */             Minecraft var11 = Minecraft.getMinecraft();
/*  81 */             Map var12 = var11.getSkinManager().loadSkinFromCache(p_180543_7_);
/*     */             
/*  83 */             if (var12.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/*     */               
/*  85 */               var10 = var11.getSkinManager().loadSkin((MinecraftProfileTexture)var12.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*     */             }
/*     */             else {
/*     */               
/*  89 */               UUID var13 = EntityPlayer.getUUID(p_180543_7_);
/*  90 */               var10 = DefaultPlayerSkin.func_177334_a(var13);
/*     */             } 
/*     */           } 
/*     */           
/*  94 */           bindTexture(var10);
/*     */           break;
/*     */         
/*     */         case 4:
/*  98 */           bindTexture(field_147532_f);
/*     */           break;
/*     */       } 
/*     */     } 
/* 102 */     GlStateManager.pushMatrix();
/* 103 */     GlStateManager.disableCull();
/*     */     
/* 105 */     if (p_180543_4_ != EnumFacing.UP) {
/*     */       
/* 107 */       switch (SwitchEnumFacing.field_178458_a[p_180543_4_.ordinal()]) {
/*     */         
/*     */         case 1:
/* 110 */           GlStateManager.translate(p_180543_1_ + 0.5F, p_180543_2_ + 0.25F, p_180543_3_ + 0.74F);
/*     */           break;
/*     */         
/*     */         case 2:
/* 114 */           GlStateManager.translate(p_180543_1_ + 0.5F, p_180543_2_ + 0.25F, p_180543_3_ + 0.26F);
/* 115 */           p_180543_5_ = 180.0F;
/*     */           break;
/*     */         
/*     */         case 3:
/* 119 */           GlStateManager.translate(p_180543_1_ + 0.74F, p_180543_2_ + 0.25F, p_180543_3_ + 0.5F);
/* 120 */           p_180543_5_ = 270.0F;
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 125 */           GlStateManager.translate(p_180543_1_ + 0.26F, p_180543_2_ + 0.25F, p_180543_3_ + 0.5F);
/* 126 */           p_180543_5_ = 90.0F;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 131 */       GlStateManager.translate(p_180543_1_ + 0.5F, p_180543_2_, p_180543_3_ + 0.5F);
/*     */     } 
/*     */     
/* 134 */     float var14 = 0.0625F;
/* 135 */     GlStateManager.enableRescaleNormal();
/* 136 */     GlStateManager.scale(-1.0F, -1.0F, 1.0F);
/* 137 */     GlStateManager.enableAlpha();
/* 138 */     var9.render(null, 0.0F, 0.0F, 0.0F, p_180543_5_, 0.0F, var14);
/* 139 */     GlStateManager.popMatrix();
/*     */     
/* 141 */     if (p_180543_8_ >= 0) {
/*     */       
/* 143 */       GlStateManager.matrixMode(5890);
/* 144 */       GlStateManager.popMatrix();
/* 145 */       GlStateManager.matrixMode(5888);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 151 */     func_180542_a((TileEntitySkull)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 156 */     static final int[] field_178458_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002468";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 163 */         field_178458_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 165 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 172 */         field_178458_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 174 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 181 */         field_178458_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 183 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 190 */         field_178458_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 192 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntitySkullRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */