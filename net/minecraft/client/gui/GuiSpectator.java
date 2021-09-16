/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
/*     */ import net.minecraft.client.gui.spectator.ISpectatorMenuReciepient;
/*     */ import net.minecraft.client.gui.spectator.SpectatorMenu;
/*     */ import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class GuiSpectator
/*     */   extends Gui implements ISpectatorMenuReciepient {
/*  16 */   private static final ResourceLocation field_175267_f = new ResourceLocation("textures/gui/widgets.png");
/*  17 */   public static final ResourceLocation field_175269_a = new ResourceLocation("textures/gui/spectator_widgets.png");
/*     */   
/*     */   private final Minecraft field_175268_g;
/*     */   private long field_175270_h;
/*     */   private SpectatorMenu field_175271_i;
/*     */   private static final String __OBFID = "CL_00001940";
/*     */   
/*     */   public GuiSpectator(Minecraft mcIn) {
/*  25 */     this.field_175268_g = mcIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175260_a(int p_175260_1_) {
/*  30 */     this.field_175270_h = Minecraft.getSystemTime();
/*     */     
/*  32 */     if (this.field_175271_i != null) {
/*     */       
/*  34 */       this.field_175271_i.func_178644_b(p_175260_1_);
/*     */     }
/*     */     else {
/*     */       
/*  38 */       this.field_175271_i = new SpectatorMenu(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float func_175265_c() {
/*  44 */     long var1 = this.field_175270_h - Minecraft.getSystemTime() + 5000L;
/*  45 */     return MathHelper.clamp_float((float)var1 / 2000.0F, 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175264_a(ScaledResolution p_175264_1_, float p_175264_2_) {
/*  50 */     if (this.field_175271_i != null) {
/*     */       
/*  52 */       float var3 = func_175265_c();
/*     */       
/*  54 */       if (var3 <= 0.0F) {
/*     */         
/*  56 */         this.field_175271_i.func_178641_d();
/*     */       }
/*     */       else {
/*     */         
/*  60 */         int var4 = p_175264_1_.getScaledWidth() / 2;
/*  61 */         float var5 = zLevel;
/*  62 */         zLevel = -90.0F;
/*  63 */         float var6 = p_175264_1_.getScaledHeight() - 22.0F * var3;
/*  64 */         SpectatorDetails var7 = this.field_175271_i.func_178646_f();
/*  65 */         func_175258_a(p_175264_1_, var3, var4, var6, var7);
/*  66 */         zLevel = var5;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175258_a(ScaledResolution p_175258_1_, float p_175258_2_, int p_175258_3_, float p_175258_4_, SpectatorDetails p_175258_5_) {
/*  73 */     GlStateManager.enableRescaleNormal();
/*  74 */     GlStateManager.enableBlend();
/*  75 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  76 */     GlStateManager.color(1.0F, 1.0F, 1.0F, p_175258_2_);
/*  77 */     this.field_175268_g.getTextureManager().bindTexture(field_175267_f);
/*  78 */     func_175174_a((p_175258_3_ - 91), p_175258_4_, 0, 0, 182, 22);
/*     */     
/*  80 */     if (p_175258_5_.func_178681_b() >= 0)
/*     */     {
/*  82 */       func_175174_a((p_175258_3_ - 91 - 1 + p_175258_5_.func_178681_b() * 20), p_175258_4_ - 1.0F, 0, 22, 24, 22);
/*     */     }
/*     */     
/*  85 */     RenderHelper.enableGUIStandardItemLighting();
/*     */     
/*  87 */     for (int var6 = 0; var6 < 9; var6++)
/*     */     {
/*  89 */       func_175266_a(var6, p_175258_1_.getScaledWidth() / 2 - 90 + var6 * 20 + 2, p_175258_4_ + 3.0F, p_175258_2_, p_175258_5_.func_178680_a(var6));
/*     */     }
/*     */     
/*  92 */     RenderHelper.disableStandardItemLighting();
/*  93 */     GlStateManager.disableRescaleNormal();
/*  94 */     GlStateManager.disableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175266_a(int p_175266_1_, int p_175266_2_, float p_175266_3_, float p_175266_4_, ISpectatorMenuObject p_175266_5_) {
/*  99 */     this.field_175268_g.getTextureManager().bindTexture(field_175269_a);
/*     */     
/* 101 */     if (p_175266_5_ != SpectatorMenu.field_178657_a) {
/*     */       
/* 103 */       int var6 = (int)(p_175266_4_ * 255.0F);
/* 104 */       GlStateManager.pushMatrix();
/* 105 */       GlStateManager.translate(p_175266_2_, p_175266_3_, 0.0F);
/* 106 */       float var7 = p_175266_5_.func_178662_A_() ? 1.0F : 0.25F;
/* 107 */       GlStateManager.color(var7, var7, var7, p_175266_4_);
/* 108 */       p_175266_5_.func_178663_a(var7, var6);
/* 109 */       GlStateManager.popMatrix();
/* 110 */       String var8 = String.valueOf(GameSettings.getKeyDisplayString(this.field_175268_g.gameSettings.keyBindsHotbar[p_175266_1_].getKeyCode()));
/*     */       
/* 112 */       if (var6 > 3 && p_175266_5_.func_178662_A_())
/*     */       {
/* 114 */         this.field_175268_g.fontRendererObj.drawStringWithShadow(var8, (p_175266_2_ + 19 - 2 - this.field_175268_g.fontRendererObj.getStringWidth(var8)), (p_175266_3_ + 6.0F + 3.0F), 16777215 + (var6 << 24));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175263_a(ScaledResolution p_175263_1_) {
/* 121 */     int var2 = (int)(func_175265_c() * 255.0F);
/*     */     
/* 123 */     if (var2 > 3 && this.field_175271_i != null) {
/*     */       
/* 125 */       ISpectatorMenuObject var3 = this.field_175271_i.func_178645_b();
/* 126 */       String var4 = (var3 != SpectatorMenu.field_178657_a) ? var3.func_178664_z_().getFormattedText() : this.field_175271_i.func_178650_c().func_178670_b().getFormattedText();
/*     */       
/* 128 */       if (var4 != null) {
/*     */         
/* 130 */         int var5 = (p_175263_1_.getScaledWidth() - this.field_175268_g.fontRendererObj.getStringWidth(var4)) / 2;
/* 131 */         int var6 = p_175263_1_.getScaledHeight() - 35;
/* 132 */         GlStateManager.pushMatrix();
/* 133 */         GlStateManager.enableBlend();
/* 134 */         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 135 */         this.field_175268_g.fontRendererObj.drawStringWithShadow(var4, var5, var6, 16777215 + (var2 << 24));
/* 136 */         GlStateManager.disableBlend();
/* 137 */         GlStateManager.popMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175257_a(SpectatorMenu p_175257_1_) {
/* 144 */     this.field_175271_i = null;
/* 145 */     this.field_175270_h = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175262_a() {
/* 150 */     return (this.field_175271_i != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_175259_b(int p_175259_1_) {
/*     */     int var2;
/* 157 */     for (var2 = this.field_175271_i.func_178648_e() + p_175259_1_; var2 >= 0 && var2 <= 8 && (this.field_175271_i.func_178643_a(var2) == SpectatorMenu.field_178657_a || !this.field_175271_i.func_178643_a(var2).func_178662_A_()); var2 += p_175259_1_);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     if (var2 >= 0 && var2 <= 8) {
/*     */       
/* 164 */       this.field_175271_i.func_178644_b(var2);
/* 165 */       this.field_175270_h = Minecraft.getSystemTime();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175261_b() {
/* 171 */     this.field_175270_h = Minecraft.getSystemTime();
/*     */     
/* 173 */     if (func_175262_a()) {
/*     */       
/* 175 */       int var1 = this.field_175271_i.func_178648_e();
/*     */       
/* 177 */       if (var1 != -1)
/*     */       {
/* 179 */         this.field_175271_i.func_178644_b(var1);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 184 */       this.field_175271_i = new SpectatorMenu(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiSpectator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */