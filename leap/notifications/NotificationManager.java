/*     */ package leap.notifications;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.function.Predicate;
/*     */ import leap.Client;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import leap.util.Timer;
/*     */ import leap.util.font.CustomFontRenderer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class NotificationManager
/*     */ {
/*  22 */   private final float spacing = 5.0F;
/*  23 */   private final float initialHeight = 10.0F;
/*     */   
/*     */   double start;
/*     */   double vanish;
/*  27 */   CopyOnWriteArrayList<Notification> notifications = new CopyOnWriteArrayList<>();
/*     */ 
/*     */   
/*     */   public void drawScreen() {
/*  31 */     Minecraft mc = Minecraft.getMinecraft();
/*     */     
/*  33 */     ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/*     */     
/*  35 */     int count = 0;
/*     */     
/*  37 */     if (this.notifications.size() > 6) {
/*  38 */       this.notifications.clear();
/*     */     }
/*     */     
/*  41 */     for (Notification notification : this.notifications) {
/*     */       
/*  43 */       float x = 0.0F;
/*     */       
/*  45 */       if (x >= 0.0F) {
/*  46 */         x = sr.getScaledWidth() - notification.getWidth() + 20000.0F;
/*     */       }
/*     */       
/*  49 */       if (x < sr.getScaledWidth() - notification.getWidth()) {
/*  50 */         x++;
/*     */       } else {
/*  52 */         x = sr.getScaledWidth() - notification.getWidth();
/*     */       } 
/*  54 */       if (x > sr.getScaledWidth() - notification.getWidth()) {
/*  55 */         x--;
/*     */       }
/*     */       
/*  58 */       if (notification.timer.hasTimeElapsed((long)notification.getTime(), false) || (this.notifications.size() > 4 && count == 0)) {
/*     */         
/*  60 */         Timer timer = new Timer();
/*     */ 
/*     */         
/*  63 */         if (notification.getAnimation() != null) {
/*  64 */           if (notification.getAnimation().isDone()) {
/*  65 */             this.notifications.remove(notification);
/*     */             continue;
/*     */           } 
/*     */         } else {
/*  69 */           vanish(notification, Direction.DOWN);
/*     */           
/*     */           continue;
/*     */         } 
/*  73 */       } else if (notification.getAnimation() != null && 
/*  74 */         notification.getAnimation().isDone()) {
/*  75 */         notification.stopAnimation();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  81 */       CustomFontRenderer title = Client.customFontRendererBig;
/*  82 */       CustomFontRenderer description = Client.customFontRendererSmall;
/*     */       
/*  84 */       float barThickness = 2.0F;
/*  85 */       float imageSpace = 17.0F;
/*  86 */       ArrayList<String> strings = (ArrayList<String>)description.wrapWords(notification.getDescription(), (notification.getWidth() - imageSpace - 20.0F - barThickness));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  91 */       float addY = 0.0F;
/*  92 */       for (int i2 = count; i2 > 0; i2--) {
/*  93 */         addY += ((Notification)this.notifications.get(i2)).getHeight();
/*     */       }
/*     */ 
/*     */       
/*  97 */       if (count == 0) notification.y = (float)(sr.getScaledHeight() / 1.1D); 
/*  98 */       notification.y = ((Notification)this.notifications.get(Math.max(count - 1, 0))).y - 5.0F - notification.getHeight();
/*     */       
/* 100 */       if (notification.animationInProgress()) {
/* 101 */         x = (float)(x + notification.getAnimation().getOutput() * notification.getWidth() * notification.getDirection().getXy()[0]);
/* 102 */         notification.y = (float)(notification.y + notification.getAnimation().getOutput() * (notification.getHeight() + barThickness + 10.0F) * notification.getDirection().getXy()[1]);
/* 103 */         float scale = Math.max((float)(1.0D - Math.abs(notification.getAnimation().getOutput())), 0.01F);
/* 104 */         GlStateManager.pushMatrix();
/* 105 */         GlStateManager.translate(x + notification.getWidth() + 1.0F - scale, notification.y, 0.0F);
/* 106 */         GlStateManager.scale(scale, scale, 1.0F);
/* 107 */         GlStateManager.translate(-(x + notification.getWidth() + 1.0F - scale), -notification.y, 0.0F);
/*     */       } 
/*     */       
/* 110 */       int shade = 120;
/* 111 */       Color color = new Color(20, 20, 20, 255);
/*     */       
/* 113 */       if (!notification.isHeightAdjusted()) {
/* 114 */         notification.setHeight(notification.getHeight() + (description.getHeight() * (strings.size() - 1)));
/* 115 */         notification.setHeightAdjusted(true);
/*     */       } 
/*     */       
/* 118 */       Shape.color(color.getRGB());
/* 119 */       Shape.drawRoundedRect(x, (notification.y - barThickness), notification.getWidth(), notification.getHeight(), notification.getRoundness());
/* 120 */       Gui.drawRect(x, (notification.y - barThickness + 33.0F), (x + notification.getWidth() * Math.min((float)notification.timer.getTime() / notification.getTime(), 1.0F)), (notification.y + 30.0F), (new Color(255, 255, 255, 200)).getRGB());
/*     */       
/* 122 */       title.drawString(notification.getTitle(), (x + 45.0F), (notification.y + 2.0F), new JColor(16777215));
/*     */       
/* 124 */       int stringNum = 0;
/* 125 */       for (String string : strings) {
/* 126 */         description.drawString(string, (x + 45.0F), (notification.y + title.getHeight() + 6.0F + (stringNum * 3 + stringNum * description.getHeight() - 5)), new JColor(12303291));
/* 127 */         stringNum++;
/*     */       } 
/* 129 */       Gui.drawRect(0.0D, 0.0D, 0.0D, 0.0D, 16777215);
/*     */       
/* 131 */       if (notification.getTime() - notification.timer.getTime() / 1000.0D > 0.0D) {
/* 132 */         description.drawString(String.valueOf((new DecimalFormat("0.0")).format((notification.getTime() - (float)notification.timer.getTime()) / 1000.0D)) + "s", (x - 15.0F + notification.getWidth()), (notification.y + 2.0F), new JColor(16711422));
/*     */       }
/*     */       
/* 135 */       switch (notification.getType()) {
/*     */         
/*     */         case SUCCESS:
/* 138 */           Shape.color(-1);
/* 139 */           Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("pics/check-simple.png"));
/* 140 */           Gui.drawModalRectWithCustomSizedTexture(x + 10.0F, notification.y + 2.0F, 0.0F, 0.0F, imageSpace, imageSpace, imageSpace, imageSpace);
/*     */           break;
/*     */         
/*     */         case null:
/* 144 */           Shape.color(-1);
/* 145 */           Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("pics/xmark-simple.png"));
/* 146 */           Gui.drawModalRectWithCustomSizedTexture(x + 10.0F, notification.y + 2.0F, 0.0F, 0.0F, imageSpace, imageSpace, imageSpace, imageSpace);
/*     */           break;
/*     */         
/*     */         case INFO:
/* 150 */           Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("pics/info-simple.png"));
/* 151 */           Gui.drawModalRectWithCustomSizedTexture(x + 10.0F, notification.y + 2.0F, 0.0F, 0.0F, imageSpace, imageSpace, imageSpace, imageSpace);
/*     */           break;
/*     */ 
/*     */         
/*     */         case WARNING:
/* 156 */           Shape.color(-1);
/* 157 */           Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("pics/warning-simple.png"));
/* 158 */           Gui.drawModalRectWithCustomSizedTexture(x + 10.0F, notification.y + 2.0F, 0.0F, 0.0F, imageSpace, imageSpace, imageSpace, imageSpace);
/*     */           break;
/*     */         
/*     */         default:
/* 162 */           Gui.drawRect((x + 105.0F), (notification.y + 8.0F), (x + 35.0F), (notification.y + 2.0F + imageSpace), (new Color(0, 0, 0, 255)).getRGB());
/*     */           break;
/*     */       } 
/* 165 */       if (notification.animationInProgress()) {
/* 166 */         GlStateManager.popMatrix();
/*     */       }
/*     */       
/* 169 */       count++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Notification notification) {
/* 176 */     this.notifications.add(notification);
/* 177 */     notification.startAnimation(new SmoothStepAnimation(250, -1.0D, Direction.BACKWARDS));
/* 178 */     notification.setDirection(Direction.DOWN);
/*     */   }
/*     */   
/*     */   public void vanish(int i, Direction direction) {
/*     */     try {
/* 183 */       Notification notification = this.notifications.get(i);
/* 184 */       notification.startAnimation(new SmoothStepAnimation(250, 1.0D));
/* 185 */       notification.setDirection(direction);
/* 186 */     } catch (NullPointerException e) {
/* 187 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void vanish(Notification notification, Direction direction) {
/* 194 */     notification.startAnimation(new LogisticAnimation(250, 1));
/* 195 */     notification.setDirection(direction);
/*     */   }
/*     */   
/*     */   public void removeLast(Predicate<Notification> filter) {
/* 199 */     for (int i = this.notifications.size() - 1; i >= 0; i--) {
/* 200 */       if (filter.test(this.notifications.get(i))) {
/* 201 */         this.notifications.remove(i);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\notifications\NotificationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */