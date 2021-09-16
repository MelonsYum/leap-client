/*     */ package optifine;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class CloudRenderer
/*     */ {
/*     */   private Minecraft mc;
/*     */   private boolean updated = false;
/*     */   private boolean renderFancy = false;
/*     */   int cloudTickCounter;
/*     */   float partialTicks;
/*  16 */   private int glListClouds = -1;
/*  17 */   private int cloudTickCounterUpdate = 0;
/*  18 */   private double cloudPlayerX = 0.0D;
/*  19 */   private double cloudPlayerY = 0.0D;
/*  20 */   private double cloudPlayerZ = 0.0D;
/*     */ 
/*     */   
/*     */   public CloudRenderer(Minecraft mc) {
/*  24 */     this.mc = mc;
/*  25 */     this.glListClouds = GLAllocation.generateDisplayLists(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareToRender(boolean renderFancy, int cloudTickCounter, float partialTicks) {
/*  30 */     if (this.renderFancy != renderFancy)
/*     */     {
/*  32 */       this.updated = false;
/*     */     }
/*     */     
/*  35 */     this.renderFancy = renderFancy;
/*  36 */     this.cloudTickCounter = cloudTickCounter;
/*  37 */     this.partialTicks = partialTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldUpdateGlList() {
/*  42 */     if (!this.updated)
/*     */     {
/*  44 */       return true;
/*     */     }
/*  46 */     if (this.cloudTickCounter >= this.cloudTickCounterUpdate + 20)
/*     */     {
/*  48 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  52 */     Entity rve = this.mc.func_175606_aa();
/*  53 */     boolean belowCloudsPrev = (this.cloudPlayerY + rve.getEyeHeight() < 128.0D + (this.mc.gameSettings.ofCloudsHeight * 128.0F));
/*  54 */     boolean belowClouds = (rve.prevPosY + rve.getEyeHeight() < 128.0D + (this.mc.gameSettings.ofCloudsHeight * 128.0F));
/*  55 */     return belowClouds ^ belowCloudsPrev;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startUpdateGlList() {
/*  61 */     GL11.glNewList(this.glListClouds, 4864);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endUpdateGlList() {
/*  66 */     GL11.glEndList();
/*  67 */     this.cloudTickCounterUpdate = this.cloudTickCounter;
/*  68 */     this.cloudPlayerX = (this.mc.func_175606_aa()).prevPosX;
/*  69 */     this.cloudPlayerY = (this.mc.func_175606_aa()).prevPosY;
/*  70 */     this.cloudPlayerZ = (this.mc.func_175606_aa()).prevPosZ;
/*  71 */     this.updated = true;
/*  72 */     GlStateManager.func_179117_G();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderGlList() {
/*  77 */     Entity entityliving = this.mc.func_175606_aa();
/*  78 */     double exactPlayerX = entityliving.prevPosX + (entityliving.posX - entityliving.prevPosX) * this.partialTicks;
/*  79 */     double exactPlayerY = entityliving.prevPosY + (entityliving.posY - entityliving.prevPosY) * this.partialTicks;
/*  80 */     double exactPlayerZ = entityliving.prevPosZ + (entityliving.posZ - entityliving.prevPosZ) * this.partialTicks;
/*  81 */     double dc = ((this.cloudTickCounter - this.cloudTickCounterUpdate) + this.partialTicks);
/*  82 */     float cdx = (float)(exactPlayerX - this.cloudPlayerX + dc * 0.03D);
/*  83 */     float cdy = (float)(exactPlayerY - this.cloudPlayerY);
/*  84 */     float cdz = (float)(exactPlayerZ - this.cloudPlayerZ);
/*  85 */     GlStateManager.pushMatrix();
/*     */     
/*  87 */     if (this.renderFancy) {
/*     */       
/*  89 */       GlStateManager.translate(-cdx / 12.0F, -cdy, -cdz / 12.0F);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       GlStateManager.translate(-cdx, -cdy, -cdz);
/*     */     } 
/*     */     
/*  96 */     GlStateManager.callList(this.glListClouds);
/*  97 */     GlStateManager.popMatrix();
/*  98 */     GlStateManager.func_179117_G();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 103 */     this.updated = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CloudRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */