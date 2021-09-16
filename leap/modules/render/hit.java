/*     */ package leap.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class hit
/*     */ {
/* 108 */   protected static Minecraft mc = Minecraft.getMinecraft();
/*     */   
/* 110 */   private long startTime = System.currentTimeMillis();
/*     */   
/*     */   private BlockPos pos;
/*     */   private double healthVal;
/* 114 */   private long maxTime = 1000L;
/*     */   
/*     */   public hit(BlockPos pos, double healthVal, boolean dead) {
/* 117 */     this.startTime = System.currentTimeMillis();
/* 118 */     this.pos = pos;
/* 119 */     this.healthVal = healthVal;
/*     */   }
/*     */   
/*     */   public void onRender() {
/* 123 */     double x = (this.pos.getX() + (this.pos.getX() - this.pos.getX()) * mc.timer.renderPartialTicks) - (Minecraft.getMinecraft().getRenderManager()).viewerPosX;
/* 124 */     double y = (this.pos.getY() + (this.pos.getY() - this.pos.getY()) * mc.timer.renderPartialTicks) - (Minecraft.getMinecraft().getRenderManager()).viewerPosY;
/* 125 */     double z = (this.pos.getZ() + (this.pos.getZ() - this.pos.getZ()) * mc.timer.renderPartialTicks) - (Minecraft.getMinecraft().getRenderManager()).viewerPosZ;
/*     */     
/* 127 */     float var10001 = (mc.gameSettings.thirdPersonView == 2) ? -1.0F : 1.0F;
/* 128 */     double size = 1.5D;
/* 129 */     GL11.glPushMatrix();
/* 130 */     GL11.glEnable(3042);
/* 131 */     GL11.glEnable(3042);
/* 132 */     GL11.glBlendFunc(770, 771);
/* 133 */     GL11.glEnable(2848);
/* 134 */     GL11.glDisable(3553);
/* 135 */     GL11.glDisable(2929);
/* 136 */     (Minecraft.getMinecraft()).entityRenderer.setupCameraTransform(mc.timer.renderPartialTicks, 0);
/* 137 */     GL11.glTranslated(x, y, z);
/* 138 */     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 139 */     GL11.glRotatef(-(mc.getRenderManager()).playerViewY, 0.0F, 1.0F, 0.0F);
/* 140 */     GL11.glRotatef((mc.getRenderManager()).playerViewX, var10001, 0.0F, 0.0F);
/* 141 */     GL11.glScaled(-0.025000001303851604D, -0.025000001303851604D, 0.025000001303851604D);
/* 142 */     float sizePercentage = 1.0F;
/* 143 */     long timeLeft = this.startTime + this.maxTime - System.currentTimeMillis();
/* 144 */     float yPercentage = 0.0F;
/* 145 */     if (timeLeft < 75L) {
/* 146 */       sizePercentage = Math.min((float)timeLeft / 75.0F, 1.0F);
/* 147 */       yPercentage = Math.min((float)timeLeft / 75.0F, 1.0F);
/*     */     } else {
/* 149 */       sizePercentage = Math.min((float)(System.currentTimeMillis() - this.startTime) / 300.0F, 1.0F);
/* 150 */       yPercentage = Math.min((float)(System.currentTimeMillis() - this.startTime) / 600.0F, 1.0F);
/*     */     } 
/* 152 */     GlStateManager.scale(2.0F * sizePercentage, 2.0F * sizePercentage, 2.0F * sizePercentage);
/* 153 */     Gui.drawRect(-100.0D, -100.0D, 100.0D, 100.0D, (new Color(255, 0, 0, 0)).getRGB());
/*     */ 
/*     */     
/* 156 */     Color render = new Color(0, 255, 255);
/* 157 */     if (this.healthVal < 3.0D && this.healthVal > 1.0D) {
/* 158 */       render = new Color(255, 255, 0);
/* 159 */     } else if (this.healthVal <= 1.0D) {
/* 160 */       render = new Color(255, 0, 0);
/*     */     } 
/* 162 */     mc.fontRendererObj.drawStringWithShadow((new DecimalFormat("#.#")).format(this.healthVal), 0.0D, -(yPercentage * 4.0F), render.getRGB());
/*     */     
/* 164 */     GL11.glDisable(3042);
/* 165 */     GL11.glEnable(3553);
/* 166 */     GL11.glDisable(2848);
/* 167 */     GL11.glDisable(3042);
/* 168 */     GL11.glEnable(2929);
/* 169 */     GlStateManager.color(1.0F, 1.0F, 1.0F);
/* 170 */     GlStateManager.popMatrix();
/* 171 */     (Minecraft.getMinecraft()).entityRenderer.setupOverlayRendering();
/*     */   }
/*     */   
/*     */   public void onDead() {
/* 175 */     double x = (this.pos.getX() + (this.pos.getX() - this.pos.getX()) * mc.timer.renderPartialTicks) - (Minecraft.getMinecraft().getRenderManager()).viewerPosX;
/* 176 */     double y = (this.pos.getY() + (this.pos.getY() - this.pos.getY()) * mc.timer.renderPartialTicks) - (Minecraft.getMinecraft().getRenderManager()).viewerPosY;
/* 177 */     double z = (this.pos.getZ() + (this.pos.getZ() - this.pos.getZ()) * mc.timer.renderPartialTicks) - (Minecraft.getMinecraft().getRenderManager()).viewerPosZ;
/*     */     
/* 179 */     float var10001 = (mc.gameSettings.thirdPersonView == 2) ? -1.0F : 1.0F;
/* 180 */     double size = 1.5D;
/* 181 */     GL11.glPushMatrix();
/* 182 */     GL11.glEnable(3042);
/* 183 */     GL11.glEnable(3042);
/* 184 */     GL11.glBlendFunc(770, 771);
/* 185 */     GL11.glEnable(2848);
/* 186 */     GL11.glDisable(3553);
/* 187 */     GL11.glDisable(2929);
/* 188 */     (Minecraft.getMinecraft()).entityRenderer.setupCameraTransform(mc.timer.renderPartialTicks, 0);
/* 189 */     GL11.glTranslated(x, y, z);
/* 190 */     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 191 */     GL11.glRotatef(-(mc.getRenderManager()).playerViewY, 0.0F, 1.0F, 0.0F);
/* 192 */     GL11.glRotatef((mc.getRenderManager()).playerViewX, var10001, 0.0F, 0.0F);
/* 193 */     GL11.glScaled(-0.025000001303851604D, -0.025000001303851604D, 0.025000001303851604D);
/* 194 */     float sizePercentage = 1.0F;
/* 195 */     long timeLeft = this.startTime + this.maxTime - System.currentTimeMillis();
/* 196 */     float yPercentage = 0.0F;
/* 197 */     if (timeLeft < 75L) {
/* 198 */       sizePercentage = Math.min((float)timeLeft / 75.0F, 1.0F);
/* 199 */       yPercentage = Math.min((float)timeLeft / 75.0F, 1.0F);
/*     */     } else {
/* 201 */       sizePercentage = Math.min((float)(System.currentTimeMillis() - this.startTime) / 300.0F, 1.0F);
/* 202 */       yPercentage = Math.min((float)(System.currentTimeMillis() - this.startTime) / 600.0F, 1.0F);
/*     */     } 
/* 204 */     GlStateManager.scale(2.0F * sizePercentage, 2.0F * sizePercentage, 2.0F * sizePercentage);
/* 205 */     Gui.drawRect(-100.0D, -100.0D, 100.0D, 100.0D, (new Color(255, 0, 0, 0)).getRGB());
/*     */ 
/*     */     
/* 208 */     Color render = new Color(0, 255, 255);
/* 209 */     if (this.healthVal < 3.0D && this.healthVal > 1.0D) {
/* 210 */       render = new Color(255, 255, 0);
/* 211 */     } else if (this.healthVal <= 1.0D) {
/* 212 */       render = new Color(255, 0, 0);
/*     */     } 
/* 214 */     Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("pics/L.png"));
/* 215 */     Gui.drawModalRectWithCustomSizedTexture(0.0F, -(yPercentage * 4.0F), 0.0F, 0.0F, 20.0F, 20.0F, 20.0F, 20.0F);
/* 216 */     Gui.drawModalRectWithCustomSizedTexture(0.0F, -(yPercentage * 4.0F), 0.0F, 0.0F, 20.0F, 20.0F, 20.0F, 20.0F);
/* 217 */     Gui.drawModalRectWithCustomSizedTexture(0.0F, -(yPercentage * 4.0F), 0.0F, 0.0F, 20.0F, 20.0F, 20.0F, 20.0F);
/* 218 */     Gui.drawModalRectWithCustomSizedTexture(0.0F, -(yPercentage * 4.0F), 0.0F, 0.0F, 20.0F, 20.0F, 20.0F, 20.0F);
/* 219 */     Gui.drawModalRectWithCustomSizedTexture(0.0F, -(yPercentage * 4.0F), 0.0F, 0.0F, 20.0F, 20.0F, 20.0F, 20.0F);
/*     */     
/* 221 */     GL11.glDisable(3042);
/* 222 */     GL11.glEnable(3553);
/* 223 */     GL11.glDisable(2848);
/* 224 */     GL11.glDisable(3042);
/* 225 */     GL11.glEnable(2929);
/* 226 */     GlStateManager.color(1.0F, 1.0F, 1.0F);
/* 227 */     GlStateManager.popMatrix();
/*     */   }
/*     */   
/*     */   public boolean isFinished() {
/* 231 */     return (System.currentTimeMillis() - this.startTime >= this.maxTime);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\hit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */