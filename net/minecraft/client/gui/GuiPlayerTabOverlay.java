/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.ComparisonChain;
/*     */ import com.google.common.collect.Ordering;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*     */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ public class GuiPlayerTabOverlay extends Gui {
/*  25 */   private static final Ordering field_175252_a = Ordering.from(new PlayerComparator(null));
/*     */   
/*     */   private final Minecraft field_175250_f;
/*     */   private final GuiIngame field_175251_g;
/*     */   private IChatComponent footer;
/*     */   private IChatComponent header;
/*     */   private long field_175253_j;
/*     */   private boolean field_175254_k;
/*     */   private static final String __OBFID = "CL_00001943";
/*     */   
/*     */   public GuiPlayerTabOverlay(Minecraft mcIn, GuiIngame p_i45529_2_) {
/*  36 */     this.field_175250_f = mcIn;
/*  37 */     this.field_175251_g = p_i45529_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_175243_a(NetworkPlayerInfo p_175243_1_) {
/*  42 */     return (p_175243_1_.func_178854_k() != null) ? p_175243_1_.func_178854_k().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)p_175243_1_.func_178850_i(), p_175243_1_.func_178845_a().getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175246_a(boolean p_175246_1_) {
/*  47 */     if (p_175246_1_ && !this.field_175254_k)
/*     */     {
/*  49 */       this.field_175253_j = Minecraft.getSystemTime();
/*     */     }
/*     */     
/*  52 */     this.field_175254_k = p_175246_1_;
/*     */   }
/*     */   
/*     */   public void func_175249_a(int p_175249_1_, Scoreboard p_175249_2_, ScoreObjective p_175249_3_) {
/*     */     int var12;
/*  57 */     NetHandlerPlayClient var4 = this.field_175250_f.thePlayer.sendQueue;
/*  58 */     List<NetworkPlayerInfo> var5 = field_175252_a.sortedCopy(var4.func_175106_d());
/*  59 */     int var6 = 0;
/*  60 */     int var7 = 0;
/*  61 */     Iterator<NetworkPlayerInfo> var8 = var5.iterator();
/*     */ 
/*     */     
/*  64 */     while (var8.hasNext()) {
/*     */       
/*  66 */       NetworkPlayerInfo var9 = var8.next();
/*  67 */       int i = this.field_175250_f.fontRendererObj.getStringWidth(func_175243_a(var9));
/*  68 */       var6 = Math.max(var6, i);
/*     */       
/*  70 */       if (p_175249_3_ != null && p_175249_3_.func_178766_e() != IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
/*     */         
/*  72 */         i = this.field_175250_f.fontRendererObj.getStringWidth(" " + p_175249_2_.getValueFromObjective(var9.func_178845_a().getName(), p_175249_3_).getScorePoints());
/*  73 */         var7 = Math.max(var7, i);
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     var5 = var5.subList(0, Math.min(var5.size(), 80));
/*  78 */     int var28 = var5.size();
/*  79 */     int var29 = var28;
/*     */     int var10;
/*  81 */     for (var10 = 1; var29 > 20; var29 = (var28 + var10 - 1) / var10)
/*     */     {
/*  83 */       var10++;
/*     */     }
/*     */     
/*  86 */     boolean var11 = !(!this.field_175250_f.isIntegratedServerRunning() && !this.field_175250_f.getNetHandler().getNetworkManager().func_179292_f());
/*     */ 
/*     */     
/*  89 */     if (p_175249_3_ != null) {
/*     */       
/*  91 */       if (p_175249_3_.func_178766_e() == IScoreObjectiveCriteria.EnumRenderType.HEARTS)
/*     */       {
/*  93 */         var12 = 90;
/*     */       }
/*     */       else
/*     */       {
/*  97 */         var12 = var7;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 102 */       var12 = 0;
/*     */     } 
/*     */     
/* 105 */     int var13 = Math.min(var10 * ((var11 ? 9 : 0) + var6 + var12 + 13), p_175249_1_ - 50) / var10;
/* 106 */     int var14 = p_175249_1_ / 2 - (var13 * var10 + (var10 - 1) * 5) / 2;
/* 107 */     int var15 = 10;
/* 108 */     int var16 = var13 * var10 + (var10 - 1) * 5;
/* 109 */     List var17 = null;
/* 110 */     List var18 = null;
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (this.header != null) {
/*     */       
/* 116 */       var17 = this.field_175250_f.fontRendererObj.listFormattedStringToWidth(this.header.getFormattedText(), p_175249_1_ - 50);
/*     */       
/* 118 */       for (Iterator<String> var19 = var17.iterator(); var19.hasNext(); var16 = Math.max(var16, this.field_175250_f.fontRendererObj.getStringWidth(var20)))
/*     */       {
/* 120 */         String var20 = var19.next();
/*     */       }
/*     */     } 
/*     */     
/* 124 */     if (this.footer != null) {
/*     */       
/* 126 */       var18 = this.field_175250_f.fontRendererObj.listFormattedStringToWidth(this.footer.getFormattedText(), p_175249_1_ - 50);
/*     */       
/* 128 */       for (Iterator<String> var19 = var18.iterator(); var19.hasNext(); var16 = Math.max(var16, this.field_175250_f.fontRendererObj.getStringWidth(var20)))
/*     */       {
/* 130 */         String var20 = var19.next();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 136 */     if (var17 != null) {
/*     */       
/* 138 */       drawRect((p_175249_1_ / 2 - var16 / 2 - 1), (var15 - 1), (p_175249_1_ / 2 + var16 / 2 + 1), (var15 + var17.size() * this.field_175250_f.fontRendererObj.FONT_HEIGHT), -2147483648);
/*     */       
/* 140 */       for (Iterator<String> var19 = var17.iterator(); var19.hasNext(); var15 += this.field_175250_f.fontRendererObj.FONT_HEIGHT) {
/*     */         
/* 142 */         String var20 = var19.next();
/* 143 */         int var21 = this.field_175250_f.fontRendererObj.getStringWidth(var20);
/* 144 */         this.field_175250_f.fontRendererObj.drawStringWithShadow(var20, (p_175249_1_ / 2 - var21 / 2), var15, -1);
/*     */       } 
/*     */       
/* 147 */       var15++;
/*     */     } 
/*     */     
/* 150 */     drawRect((p_175249_1_ / 2 - var16 / 2 - 1), (var15 - 1), (p_175249_1_ / 2 + var16 / 2 + 1), (var15 + var29 * 9), -2147483648);
/*     */     
/* 152 */     for (int var30 = 0; var30 < var28; var30++) {
/*     */       
/* 154 */       int var31 = var30 / var29;
/* 155 */       int var21 = var30 % var29;
/* 156 */       int var22 = var14 + var31 * var13 + var31 * 5;
/* 157 */       int var23 = var15 + var21 * 9;
/* 158 */       drawRect(var22, var23, (var22 + var13), (var23 + 8), 553648127);
/* 159 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 160 */       GlStateManager.enableAlpha();
/* 161 */       GlStateManager.enableBlend();
/* 162 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*     */       
/* 164 */       if (var30 < var5.size()) {
/*     */         
/* 166 */         NetworkPlayerInfo var24 = var5.get(var30);
/* 167 */         String var25 = func_175243_a(var24);
/*     */         
/* 169 */         if (var11) {
/*     */           
/* 171 */           this.field_175250_f.getTextureManager().bindTexture(var24.func_178837_g());
/* 172 */           Gui.drawScaledCustomSizeModalRect(var22, var23, 8.0F, 8.0F, 8, 8, 8, 8, 64.0F, 64.0F);
/* 173 */           EntityPlayer var26 = this.field_175250_f.theWorld.getPlayerEntityByUUID(var24.func_178845_a().getId());
/*     */           
/* 175 */           if (var26 != null && var26.func_175148_a(EnumPlayerModelParts.HAT))
/*     */           {
/* 177 */             Gui.drawScaledCustomSizeModalRect(var22, var23, 40.0F, 8.0F, 8, 8, 8, 8, 64.0F, 64.0F);
/*     */           }
/*     */           
/* 180 */           var22 += 9;
/*     */         } 
/*     */         
/* 183 */         if (var24.getGameType() == WorldSettings.GameType.SPECTATOR) {
/*     */           
/* 185 */           var25 = EnumChatFormatting.ITALIC + var25;
/* 186 */           this.field_175250_f.fontRendererObj.drawStringWithShadow(var25, var22, var23, -1862270977);
/*     */         }
/*     */         else {
/*     */           
/* 190 */           this.field_175250_f.fontRendererObj.drawStringWithShadow(var25, var22, var23, -1);
/*     */         } 
/*     */         
/* 193 */         if (p_175249_3_ != null && var24.getGameType() != WorldSettings.GameType.SPECTATOR) {
/*     */           
/* 195 */           int var32 = var22 + var6 + 1;
/* 196 */           int var27 = var32 + var12;
/*     */           
/* 198 */           if (var27 - var32 > 5)
/*     */           {
/* 200 */             func_175247_a(p_175249_3_, var23, var24.func_178845_a().getName(), var32, var27, var24);
/*     */           }
/*     */         } 
/*     */         
/* 204 */         func_175245_a(var13, var22 - (var11 ? 9 : 0), var23, var24);
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     if (var18 != null) {
/*     */       
/* 210 */       var15 += var29 * 9 + 1;
/* 211 */       drawRect((p_175249_1_ / 2 - var16 / 2 - 1), (var15 - 1), (p_175249_1_ / 2 + var16 / 2 + 1), (var15 + var18.size() * this.field_175250_f.fontRendererObj.FONT_HEIGHT), -2147483648);
/*     */       
/* 213 */       for (Iterator<String> var19 = var18.iterator(); var19.hasNext(); var15 += this.field_175250_f.fontRendererObj.FONT_HEIGHT) {
/*     */         
/* 215 */         String var20 = var19.next();
/* 216 */         int var21 = this.field_175250_f.fontRendererObj.getStringWidth(var20);
/* 217 */         this.field_175250_f.fontRendererObj.drawStringWithShadow(var20, (p_175249_1_ / 2 - var21 / 2), var15, -1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_175245_a(int p_175245_1_, int p_175245_2_, int p_175245_3_, NetworkPlayerInfo p_175245_4_) {
/*     */     byte var7;
/* 224 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 225 */     this.field_175250_f.getTextureManager().bindTexture(icons);
/* 226 */     byte var5 = 0;
/* 227 */     boolean var6 = false;
/*     */ 
/*     */     
/* 230 */     if (p_175245_4_.getResponseTime() < 0) {
/*     */       
/* 232 */       var7 = 5;
/*     */     }
/* 234 */     else if (p_175245_4_.getResponseTime() < 150) {
/*     */       
/* 236 */       var7 = 0;
/*     */     }
/* 238 */     else if (p_175245_4_.getResponseTime() < 300) {
/*     */       
/* 240 */       var7 = 1;
/*     */     }
/* 242 */     else if (p_175245_4_.getResponseTime() < 600) {
/*     */       
/* 244 */       var7 = 2;
/*     */     }
/* 246 */     else if (p_175245_4_.getResponseTime() < 1000) {
/*     */       
/* 248 */       var7 = 3;
/*     */     }
/*     */     else {
/*     */       
/* 252 */       var7 = 4;
/*     */     } 
/*     */     
/* 255 */     zLevel += 100.0F;
/* 256 */     drawTexturedModalRect(p_175245_2_ + p_175245_1_ - 11, p_175245_3_, 0 + var5 * 10, 176 + var7 * 8, 10, 8);
/* 257 */     zLevel -= 100.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175247_a(ScoreObjective p_175247_1_, int p_175247_2_, String p_175247_3_, int p_175247_4_, int p_175247_5_, NetworkPlayerInfo p_175247_6_) {
/* 262 */     int var7 = p_175247_1_.getScoreboard().getValueFromObjective(p_175247_3_, p_175247_1_).getScorePoints();
/*     */     
/* 264 */     if (p_175247_1_.func_178766_e() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
/*     */       
/* 266 */       this.field_175250_f.getTextureManager().bindTexture(icons);
/*     */       
/* 268 */       if (this.field_175253_j == p_175247_6_.func_178855_p())
/*     */       {
/* 270 */         if (var7 < p_175247_6_.func_178835_l()) {
/*     */           
/* 272 */           p_175247_6_.func_178846_a(Minecraft.getSystemTime());
/* 273 */           p_175247_6_.func_178844_b((this.field_175251_g.getUpdateCounter() + 20));
/*     */         }
/* 275 */         else if (var7 > p_175247_6_.func_178835_l()) {
/*     */           
/* 277 */           p_175247_6_.func_178846_a(Minecraft.getSystemTime());
/* 278 */           p_175247_6_.func_178844_b((this.field_175251_g.getUpdateCounter() + 10));
/*     */         } 
/*     */       }
/*     */       
/* 282 */       if (Minecraft.getSystemTime() - p_175247_6_.func_178847_n() > 1000L || this.field_175253_j != p_175247_6_.func_178855_p()) {
/*     */         
/* 284 */         p_175247_6_.func_178836_b(var7);
/* 285 */         p_175247_6_.func_178857_c(var7);
/* 286 */         p_175247_6_.func_178846_a(Minecraft.getSystemTime());
/*     */       } 
/*     */       
/* 289 */       p_175247_6_.func_178843_c(this.field_175253_j);
/* 290 */       p_175247_6_.func_178836_b(var7);
/* 291 */       int var8 = MathHelper.ceiling_float_int(Math.max(var7, p_175247_6_.func_178860_m()) / 2.0F);
/* 292 */       int var9 = Math.max(MathHelper.ceiling_float_int((var7 / 2)), Math.max(MathHelper.ceiling_float_int((p_175247_6_.func_178860_m() / 2)), 10));
/* 293 */       boolean var10 = (p_175247_6_.func_178858_o() > this.field_175251_g.getUpdateCounter() && (p_175247_6_.func_178858_o() - this.field_175251_g.getUpdateCounter()) / 3L % 2L == 1L);
/*     */       
/* 295 */       if (var8 > 0) {
/*     */         
/* 297 */         float var11 = Math.min((p_175247_5_ - p_175247_4_ - 4) / var9, 9.0F);
/*     */         
/* 299 */         if (var11 > 3.0F) {
/*     */           int var12;
/*     */ 
/*     */           
/* 303 */           for (var12 = var8; var12 < var9; var12++)
/*     */           {
/* 305 */             func_175174_a(p_175247_4_ + var12 * var11, p_175247_2_, var10 ? 25 : 16, 0, 9, 9);
/*     */           }
/*     */           
/* 308 */           for (var12 = 0; var12 < var8; var12++) {
/*     */             
/* 310 */             func_175174_a(p_175247_4_ + var12 * var11, p_175247_2_, var10 ? 25 : 16, 0, 9, 9);
/*     */             
/* 312 */             if (var10) {
/*     */               
/* 314 */               if (var12 * 2 + 1 < p_175247_6_.func_178860_m())
/*     */               {
/* 316 */                 func_175174_a(p_175247_4_ + var12 * var11, p_175247_2_, 70, 0, 9, 9);
/*     */               }
/*     */               
/* 319 */               if (var12 * 2 + 1 == p_175247_6_.func_178860_m())
/*     */               {
/* 321 */                 func_175174_a(p_175247_4_ + var12 * var11, p_175247_2_, 79, 0, 9, 9);
/*     */               }
/*     */             } 
/*     */             
/* 325 */             if (var12 * 2 + 1 < var7)
/*     */             {
/* 327 */               func_175174_a(p_175247_4_ + var12 * var11, p_175247_2_, (var12 >= 10) ? 160 : 52, 0, 9, 9);
/*     */             }
/*     */             
/* 330 */             if (var12 * 2 + 1 == var7)
/*     */             {
/* 332 */               func_175174_a(p_175247_4_ + var12 * var11, p_175247_2_, (var12 >= 10) ? 169 : 61, 0, 9, 9);
/*     */             }
/*     */           } 
/*     */         } else {
/*     */           String str;
/*     */           
/* 338 */           float var16 = MathHelper.clamp_float(var7 / 20.0F, 0.0F, 1.0F);
/* 339 */           int var13 = (int)((1.0F - var16) * 255.0F) << 16 | (int)(var16 * 255.0F) << 8;
/* 340 */           float f1 = var7 / 2.0F;
/*     */           
/* 342 */           if (p_175247_5_ - this.field_175250_f.fontRendererObj.getStringWidth(String.valueOf(f1) + "hp") >= p_175247_4_)
/*     */           {
/* 344 */             str = String.valueOf(f1) + "hp";
/*     */           }
/*     */           
/* 347 */           this.field_175250_f.fontRendererObj.drawStringWithShadow(str, ((p_175247_5_ + p_175247_4_) / 2 - this.field_175250_f.fontRendererObj.getStringWidth(str) / 2), p_175247_2_, var13);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 353 */       String var15 = EnumChatFormatting.YELLOW + var7;
/* 354 */       this.field_175250_f.fontRendererObj.drawStringWithShadow(var15, (p_175247_5_ - this.field_175250_f.fontRendererObj.getStringWidth(var15)), p_175247_2_, 16777215);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFooter(IChatComponent p_175248_1_) {
/* 360 */     this.footer = p_175248_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(IChatComponent p_175244_1_) {
/* 365 */     this.header = p_175244_1_;
/*     */   }
/*     */   
/*     */   static class PlayerComparator
/*     */     implements Comparator
/*     */   {
/*     */     private static final String __OBFID = "CL_00001941";
/*     */     
/*     */     private PlayerComparator() {}
/*     */     
/*     */     public int func_178952_a(NetworkPlayerInfo p_178952_1_, NetworkPlayerInfo p_178952_2_) {
/* 376 */       ScorePlayerTeam var3 = p_178952_1_.func_178850_i();
/* 377 */       ScorePlayerTeam var4 = p_178952_2_.func_178850_i();
/* 378 */       return ComparisonChain.start().compareTrueFirst((p_178952_1_.getGameType() != WorldSettings.GameType.SPECTATOR), (p_178952_2_.getGameType() != WorldSettings.GameType.SPECTATOR)).compare((var3 != null) ? var3.getRegisteredName() : "", (var4 != null) ? var4.getRegisteredName() : "").compare(p_178952_1_.func_178845_a().getName(), p_178952_2_.func_178845_a().getName()).result();
/*     */     }
/*     */ 
/*     */     
/*     */     public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 383 */       return func_178952_a((NetworkPlayerInfo)p_compare_1_, (NetworkPlayerInfo)p_compare_2_);
/*     */     }
/*     */ 
/*     */     
/*     */     PlayerComparator(Object p_i45528_1_) {
/* 388 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiPlayerTabOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */