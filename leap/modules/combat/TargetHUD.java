/*     */ package leap.modules.combat;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.modules.Module;
/*     */ import leap.modules.render.Appearance;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.JColor;
/*     */ import leap.util.OtherUtil;
/*     */ import leap.util.Shape;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.network.play.server.S02PacketChat;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ public class TargetHUD
/*     */   extends Module
/*     */ {
/*  39 */   Timer timer = new Timer();
/*     */   
/*     */   int kills;
/*     */   
/*     */   int wins;
/*     */   
/*     */   public static double MouseAddStatsX;
/*     */   
/*     */   public static double MouseAddStatsY;
/*     */   public double lastReportedHealth;
/*     */   public double TargetHudMove;
/*  50 */   public static BooleanSetting stats = new BooleanSetting("Stats", true);
/*  51 */   public static ModeSetting mode = new ModeSetting("Mode", "Detailed", new String[] { "Simple", "HealthOnly", "Detailed" });
/*     */   public TargetHUD() {
/*  53 */     super("TargetHud", 0, Module.Category.COMBAT);
/*  54 */     addSettings(new Setting[] { (Setting)stats, (Setting)mode });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  58 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  62 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  66 */     if (e instanceof leap.events.listeners.EventRenderGUI) {
/*     */       
/*  68 */       FontRenderer fr = mc.fontRendererObj;
/*     */ 
/*     */       
/*  71 */       if (stats.isEnabled()) {
/*  72 */         Shape.color((new Color(40, 40, 40, 105)).getRGB());
/*  73 */         Shape.drawRoundedRect(15.0D + MouseAddStatsX, 6.0D + MouseAddStatsY, (80 + fr.getStringWidth(Integer.toString((int)(Client.playTimer.getTime() / 60000L)))), 40.0D, 2.0F);
/*  74 */         if (Appearance.look.getMode() == "Custom") {
/*  75 */           Shape.color((new Color((int)Appearance.blue.getValue(), (int)Appearance.green.getValue(), (int)Appearance.red.getValue(), 185)).getRGB());
/*     */         } else {
/*  77 */           Shape.color((new Color(255, 155, 0, 200)).getRGB());
/*     */         } 
/*  79 */         Shape.drawRoundedRect(15.0D + MouseAddStatsX, 6.0D + MouseAddStatsY, (80 + fr.getStringWidth(Integer.toString((int)(Client.playTimer.getTime() / 60000L)))), 7.0D, 0.0F);
/*     */         
/*  81 */         Client.customFontRendererSmall.drawString("Stats", 50.0D + MouseAddStatsX, 8.0D + MouseAddStatsY, new JColor(Color.white.getRGB()));
/*     */         
/*  83 */         Client.customFontRendererSmall.drawString("Kills: " + this.kills, 20.0D + MouseAddStatsX, 20.0D + MouseAddStatsY, new JColor(Color.white.getRGB()));
/*  84 */         Client.customFontRendererSmall.drawString("Wins: " + this.wins, 20.0D + MouseAddStatsX, 30.0D + MouseAddStatsY, new JColor(Color.white.getRGB()));
/*  85 */         Client.customFontRendererSmall.drawString("Play Time: " + ((int)Client.playTimer.getTime() / 60000) + " Minute(s)", 20.0D + MouseAddStatsX, 40.0D + MouseAddStatsY, new JColor(Color.white.getRGB()));
/*     */         
/*  87 */         mc.getTextureManager().bindTexture(new ResourceLocation("pics/kill-icon.png"));
/*  88 */         Gui.drawModalRectWithCustomSizedTexture((float)(90.0D + MouseAddStatsX), (float)(18.0D + MouseAddStatsY), 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
/*     */         
/*  90 */         mc.getTextureManager().bindTexture(new ResourceLocation("pics/win-icon.png"));
/*  91 */         Gui.drawModalRectWithCustomSizedTexture((float)(90.0D + MouseAddStatsX), (float)(28.0D + MouseAddStatsY), 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
/*     */         
/*  93 */         mc.getTextureManager().bindTexture(new ResourceLocation("pics/stats-icon.png"));
/*  94 */         Gui.drawModalRectWithCustomSizedTexture((float)(70.0D + MouseAddStatsX), (float)(6.0D + MouseAddStatsY), 0.0F, 0.0F, 7.0F, 7.0F, 7.0F, 7.0F);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  99 */       GlStateManager.pushMatrix();
/*     */ 
/*     */ 
/*     */       
/* 103 */       if (mode.getMode() == "Detailed") {
/* 104 */         ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/* 105 */         if (mc.thePlayer != null && 
/* 106 */           mc.theWorld != null) {
/* 107 */           if (Client.getModule("KillAura").isEnabled()) {
/* 108 */             if (KillAura.targets != null) {
/* 109 */               if (!KillAura.targets.isEmpty()) {
/* 110 */                 EntityLivingBase target = KillAura.targets.get(0);
/*     */                 
/* 112 */                 if ((GuiScreen.width / 2) - this.TargetHudMove > 0.0D) {
/* 113 */                   this.TargetHudMove -= 100.0D;
/*     */                 }
/*     */                 
/* 116 */                 if (target instanceof net.minecraft.entity.player.EntityPlayer)
/*     */                 {
/* 118 */                   if (this.lastReportedHealth > 30.0D || this.lastReportedHealth <= 0.0D) {
/* 119 */                     this.lastReportedHealth = target.getHealth();
/*     */                   }
/*     */                   
/* 122 */                   if (this.lastReportedHealth <= 0.0D) {
/* 123 */                     this.lastReportedHealth = target.getHealth();
/*     */                   }
/*     */                   
/* 126 */                   if (this.lastReportedHealth > target.getHealth()) {
/* 127 */                     this.lastReportedHealth -= 0.3D;
/*     */                   }
/*     */                   
/* 130 */                   if (this.lastReportedHealth < target.getHealth()) {
/* 131 */                     this.lastReportedHealth += 0.3D;
/*     */                   }
/*     */                   
/* 134 */                   boolean friend = false;
/*     */                   
/* 136 */                   if (KillAura.isOnSameTeam((Entity)target)) {
/* 137 */                     friend = true;
/*     */                   } else {
/* 139 */                     friend = false;
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 150 */                   Shape.color((new Color(1, 1, 1, 150)).getRGB());
/* 151 */                   Shape.drawRoundedRect(sr.getScaledWidth() / 1.98D, sr.getScaledHeight() / 2.94D, sr.getScaledWidth() / 13.33D + 92.5D, sr.getScaledHeight() / 15.5D, 4.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 156 */                   if (target.getHealth() > 0.0F && target.getHealth() <= 30.0F) {
/*     */                     
/* 158 */                     double smoothbar = (KillAura.prevhealth - System.currentTimeMillis() / 100L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 164 */                     Shape.color((new Color(255, 255, 0, 155)).darker().getRGB());
/* 165 */                     Shape.drawRoundedRect(sr.getScaledWidth() / 1.85D, sr.getScaledHeight() / 2.67D, this.lastReportedHealth / 0.2D, sr.getScaledHeight() / 60.67D, 0.0F);
/*     */ 
/*     */                     
/* 168 */                     Shape.color((new Color(255, 255, 0, 155)).getRGB());
/* 169 */                     Shape.drawRoundedRect(sr.getScaledWidth() / 1.85D, sr.getScaledHeight() / 2.67D, target.getHealth() / 0.2D, sr.getScaledHeight() / 60.67D, 0.0F);
/*     */                   } 
/*     */ 
/*     */ 
/*     */                   
/* 174 */                   OtherUtil.drawArmorStatusTarget(sr);
/*     */                   
/* 176 */                   Gui.drawRect(sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight(), (new Color(31, 29, 29)).getRGB());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 183 */                   if (!friend) {
/* 184 */                     Client.customFontRenderer.drawString(String.valueOf(target.getName()) + "  Kills: " + this.kills, sr.getScaledWidth() / 1.82D, sr.getScaledHeight() / 2.85D, new JColor(Color.white));
/* 185 */                   } else if (friend) {
/* 186 */                     Client.customFontRenderer.drawString(String.valueOf(target.getName()) + " (Team)" + "  " + "Dis: " + Integer.toString((int)mc.thePlayer.getDistanceToEntity((Entity)target)), sr.getScaledWidth() / 1.82D, sr.getScaledHeight() / 2.79D, new JColor(Color.white));
/*     */                   } 
/*     */                   
/* 189 */                   Client.customFontRenderer.drawString(Integer.toString((int)target.getHealth()), sr.getScaledWidth() / 1.74D + 77.0D, sr.getScaledHeight() / 2.85D, new JColor(Color.white));
/*     */                   
/* 191 */                   if (target.getHealth() < 5.0F) {
/* 192 */                     mc.getTextureManager().bindTexture(new ResourceLocation("pics/health-icon-low.png"));
/*     */                   }
/* 194 */                   else if (target.getHealth() < 15.0F) {
/* 195 */                     mc.getTextureManager().bindTexture(new ResourceLocation("pics/health-icon-mean.png"));
/*     */                   } else {
/* 197 */                     mc.getTextureManager().bindTexture(new ResourceLocation("pics/health-icon.png"));
/*     */                   } 
/* 199 */                   Gui.drawModalRectWithCustomSizedTexture((float)(sr.getScaledWidth() / 1.74D) + 65.0F, (float)(sr.getScaledHeight() / 2.87D), 0.0F, 0.0F, 10.0F, 10.0F, 10.0F, 10.0F);
/*     */                   
/* 201 */                   mc.getTextureManager().bindTexture(((AbstractClientPlayer)target).getLocationSkin());
/* 202 */                   if (target.hurtTime > 6) {
/* 203 */                     Shape.color((new Color(0, 0, 255)).getRGB());
/*     */                   }
/* 205 */                   Gui.drawModalRectWithCustomSizedTexture((int)(sr.getScaledWidth() / 1.97D), (int)(sr.getScaledHeight() / 2.9D), (float)(sr.getScaledWidth() / 35.5D), (float)(sr.getScaledHeight() / 14.4D), (int)(sr.getScaledWidth() / 35.2D), (sr.getScaledHeight() / 18), (int)(sr.getScaledWidth() / 4.4D), (sr.getScaledHeight() / 2));
/*     */                   
/* 207 */                   if (!target.isEntityAlive() && target.isDead) target.getHealth();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 214 */                   double movingArmor = 2.2D;
/*     */                   
/* 216 */                   for (int index = 0; index < 5; index++) {
/*     */                     
/* 218 */                     if (target.getEquipmentInSlot(index) != null);
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 228 */                   target.isEntityAlive();
/*     */                 }
/*     */               
/*     */               } else {
/*     */                 
/* 233 */                 this.lastReportedHealth = 0.0D;
/* 234 */                 this.TargetHudMove = 0.0D;
/*     */               }
/*     */             
/* 237 */             } else if (KillAura.targets == null) {
/*     */               return;
/*     */             } 
/*     */           }
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 248 */       if (mode.getMode() == "HealthOnly") {
/* 249 */         ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/* 250 */         if (mc.thePlayer != null && 
/* 251 */           mc.theWorld != null) {
/* 252 */           if (Client.getModule("KillAura").isEnabled() && 
/* 253 */             KillAura.targets != null && 
/* 254 */             !KillAura.targets.isEmpty()) {
/* 255 */             EntityLivingBase target = KillAura.targets.get(0);
/* 256 */             if (target instanceof net.minecraft.entity.player.EntityPlayer) {
/* 257 */               double smoothbar = (KillAura.prevhealth - System.currentTimeMillis() / 100L);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 262 */               Shape.color((new Color(250, 250, 250, 255)).getRGB());
/* 263 */               Shape.drawRoundedRect(sr.getScaledWidth() / 1.85D, sr.getScaledHeight() / 2.79D, KillAura.prevhealth / 0.2D, 15.0D, 0.0F);
/*     */               
/* 265 */               Shape.color((new Color(0, 0, 250, 255)).getRGB());
/* 266 */               Shape.drawRoundedRect(sr.getScaledWidth() / 1.85D, sr.getScaledHeight() / 2.79D, target.getHealth() / 0.2D, 15.0D, 0.0F);
/*     */               
/* 268 */               Client.customFontRenderer.drawString(String.valueOf(target.getName()) + " " + target.getHealth(), sr.getScaledWidth() / 1.82D, sr.getScaledHeight() / 2.79D + 2.0D, new JColor(Color.white));
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 277 */       if (mode.getMode() == "Simple") {
/* 278 */         ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/* 279 */         if (mc.thePlayer != null && 
/* 280 */           mc.theWorld != null) {
/* 281 */           if (Client.getModule("KillAura").isEnabled()) {
/* 282 */             if (KillAura.targets != null) {
/* 283 */               if (!KillAura.targets.isEmpty()) {
/* 284 */                 EntityLivingBase target = KillAura.targets.get(0);
/* 285 */                 if (target instanceof net.minecraft.entity.player.EntityPlayer)
/*     */                 {
/* 287 */                   if (this.lastReportedHealth > 30.0D || this.lastReportedHealth <= 0.0D) {
/* 288 */                     this.lastReportedHealth = target.getHealth();
/*     */                   }
/*     */                   
/* 291 */                   if (this.lastReportedHealth <= 0.0D) {
/* 292 */                     this.lastReportedHealth = target.getHealth();
/*     */                   }
/*     */                   
/* 295 */                   if (this.lastReportedHealth > target.getHealth()) {
/* 296 */                     this.lastReportedHealth -= 0.3D;
/*     */                   }
/*     */                   
/* 299 */                   if (this.lastReportedHealth < target.getHealth()) {
/* 300 */                     this.lastReportedHealth += 0.3D;
/*     */                   }
/*     */                   
/* 303 */                   boolean friend = false;
/*     */                   
/* 305 */                   if (KillAura.isOnSameTeam((Entity)target)) {
/* 306 */                     friend = true;
/*     */                   } else {
/* 308 */                     friend = false;
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 318 */                   Shape.color((new Color(1, 1, 1, 105)).getRGB());
/* 319 */                   Shape.drawRoundedRect(sr.getScaledWidth() / 1.98D, sr.getScaledHeight() / 2.95D, sr.getScaledWidth() / 22.33D + 97.5D, sr.getScaledHeight() / 14.94D, 2.0F);
/*     */ 
/*     */ 
/*     */                   
/* 323 */                   if (target.getHealth() > 0.0F && target.getHealth() <= 30.0F) {
/*     */                     
/* 325 */                     double smoothbar = (KillAura.prevhealth - System.currentTimeMillis() / 100L);
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 330 */                     Shape.color((new Color(250, 250, 250, 10)).getRGB());
/* 331 */                     Shape.drawRoundedRect(sr.getScaledWidth() / 1.85D, sr.getScaledHeight() / 2.67D, KillAura.prevhealth / 0.2D, 4.5D, 0.0F);
/*     */                     
/* 333 */                     Shape.color((new Color(0, 0, 250, 180)).getRGB());
/* 334 */                     Shape.drawRoundedRect(sr.getScaledWidth() / 1.85D, sr.getScaledHeight() / 2.67D, this.lastReportedHealth / 0.2D, 4.5D, 0.0F);
/*     */                   } 
/*     */ 
/*     */ 
/*     */                   
/* 339 */                   if (target.getTotalArmorValue() > 0) {
/* 340 */                     Shape.color((new Color(255, 120, 0)).getRGB());
/* 341 */                     Shape.drawRoundedRect(sr.getScaledWidth() / 1.85D, sr.getScaledHeight() / 2.57D, (target.getTotalArmorValue() * 2), 4.0D, 0.0F);
/*     */                   } 
/* 343 */                   OtherUtil.drawArmorStatusTarget(sr);
/*     */                   
/* 345 */                   Gui.drawRect(sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight(), (new Color(31, 29, 29)).getRGB());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 352 */                   if (!friend) {
/* 353 */                     Client.customFontRenderer.drawString(String.valueOf(target.getName()) + " " + "§b" + target.getHealth() + "§f    ", sr.getScaledWidth() / 1.82D, sr.getScaledHeight() / 2.79D, new JColor(Color.white));
/* 354 */                   } else if (friend) {
/* 355 */                     Client.customFontRenderer.drawString(String.valueOf(target.getName()) + " (Team)" + "  ", sr.getScaledWidth() / 1.82D, sr.getScaledHeight() / 2.79D, new JColor(Color.white));
/*     */                   } 
/*     */                   
/* 358 */                   mc.getTextureManager().bindTexture(((AbstractClientPlayer)target).getLocationSkin());
/* 359 */                   if (target.hurtTime > 6) {
/* 360 */                     Shape.color((new Color(0, 0, 255)).getRGB());
/*     */                   }
/* 362 */                   Gui.drawModalRectWithCustomSizedTexture((int)(sr.getScaledWidth() / 1.97D), (int)(sr.getScaledHeight() / 2.9D), (float)(sr.getScaledWidth() / 35.5D), (float)(sr.getScaledHeight() / 14.4D), (int)(sr.getScaledWidth() / 35.2D), (sr.getScaledHeight() / 18), (int)(sr.getScaledWidth() / 4.4D), (sr.getScaledHeight() / 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 368 */                   double movingArmor = 2.2D;
/*     */                   
/* 370 */                   for (int index = 0; index < 5; index++) {
/*     */                     
/* 372 */                     if (target.getEquipmentInSlot(index) != null);
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 382 */                   target.isEntityAlive();
/*     */                 }
/*     */               
/*     */               }
/*     */             
/*     */             }
/* 388 */             else if (KillAura.targets == null) {
/*     */               return;
/*     */             } 
/*     */           }
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 399 */       GlStateManager.popMatrix();
/*     */     } 
/*     */     
/* 402 */     if (e instanceof EventReceivePacket) {
/* 403 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/* 405 */       if (event.getPacket() instanceof S02PacketChat) {
/*     */         
/* 407 */         S02PacketChat packet = (S02PacketChat)event.getPacket();
/* 408 */         String message = packet.func_148915_c().getUnformattedText();
/*     */         
/* 410 */         if (message.toLowerCase().contains("was killed by " + mc.thePlayer.getName().toLowerCase() + ".") || 
/* 411 */           message.toLowerCase().contains("was thrown into the void by " + mc.thePlayer.getName().toLowerCase() + ".") || (
/* 412 */           message.toLowerCase().contains("was thrown off a cliff by " + mc.thePlayer.getName().toLowerCase() + ".") && mc.thePlayer.isEntityAlive() && !mc.thePlayer.isDead)) {
/* 413 */           this.kills++;
/*     */         }
/*     */         
/* 416 */         if (message.toLowerCase().contains("you died!") || message.toLowerCase().contains("game end") || message.toLowerCase().contains("victory!") || message.toLowerCase().contains("you are now a spectator!")) {
/* 417 */           this.kills = 0;
/*     */         }
/* 419 */         if (message.toLowerCase().contains("victory!") || message.toLowerCase().contains("you won!"))
/* 420 */           this.wins++; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\TargetHUD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */