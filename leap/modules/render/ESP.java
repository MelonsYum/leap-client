/*     */ package leap.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3d;
/*     */ import javax.vecmath.Vector4d;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.RenderUtil;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ESP
/*     */   extends Module
/*     */ {
/*  39 */   public ModeSetting mode = new ModeSetting("Mode", "2D", new String[] { "Outline", "Solid", "2D" });
/*     */   
/*  41 */   public final List<Entity> collectedEntities = new ArrayList<>();
/*  42 */   private final IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
/*  43 */   private final FloatBuffer modelview = GLAllocation.createDirectFloatBuffer(16);
/*  44 */   private final FloatBuffer projection = GLAllocation.createDirectFloatBuffer(16);
/*  45 */   private final FloatBuffer vector = GLAllocation.createDirectFloatBuffer(4);
/*     */ 
/*     */   
/*     */   public ESP() {
/*  49 */     super("ESP", 0, Module.Category.RENDER);
/*  50 */     addSettings(new Setting[] { (Setting)this.mode });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  54 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  58 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  62 */     if (e instanceof leap.events.listeners.EventRenderWorld) {
/*  63 */       GlStateManager.pushMatrix();
/*  64 */       for (Object entlist : mc.theWorld.loadedEntityList) {
/*  65 */         if (!(entlist instanceof EntityLivingBase)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/*  70 */         float red = 1.0F;
/*  71 */         float green = 0.5F;
/*  72 */         float blue = 0.5F;
/*     */ 
/*     */         
/*  75 */         EntityLivingBase entity = (EntityLivingBase)entlist;
/*     */         
/*  77 */         mc.getRenderManager(); double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/*  78 */         mc.getRenderManager(); double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/*  79 */         mc.getRenderManager(); double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */         
/*  81 */         if (entity != mc.thePlayer && entity instanceof net.minecraft.entity.player.EntityPlayer) {
/*  82 */           if (this.mode.getMode() == "Solid") {
/*  83 */             mc.entityRenderer.func_175072_h();
/*  84 */             if (entity.hurtTime == 0) {
/*  85 */               RenderUtil.drawEntityESPIDK(xPos, yPos, zPos, entity.width, entity.height + 0.1D, 0.0F, 0.0F, 255.0F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F);
/*     */             } else {
/*     */               
/*  88 */               RenderUtil.drawEntityESPIDK(xPos, yPos, zPos, entity.width, entity.height + 0.1D, 255.0F, 0.0F, 0.0F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F);
/*     */             } 
/*     */           } 
/*     */           
/*  92 */           if (this.mode.getMode() == "Outline") {
/*  93 */             mc.entityRenderer.func_175072_h();
/*     */             
/*  95 */             RenderUtil.drawOutlinedEntityESP(xPos, yPos, zPos, entity.width, entity.height, red, green, blue, 1.0F);
/*     */           } 
/*     */         } 
/*     */         
/*  99 */         if (entity instanceof net.minecraft.entity.player.EntityPlayer) {
/* 100 */           if (entity != mc.thePlayer)
/* 101 */             player(entity); 
/*     */           continue;
/*     */         } 
/* 104 */         if (entity instanceof net.minecraft.entity.monster.EntityMob) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 109 */         if (entity instanceof net.minecraft.entity.passive.EntityAnimal);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       mc.entityRenderer.setupOverlayRendering();
/* 118 */       GlStateManager.popMatrix();
/*     */     } 
/* 120 */     if (e instanceof leap.events.listeners.EventRender2D && 
/* 121 */       this.mode.getMode() == "2D") {
/*     */ 
/*     */ 
/*     */       
/* 125 */       float partialTicks = mc.timer.renderPartialTicks;
/*     */       
/* 127 */       ScaledResolution scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/* 128 */       int scaleFactor = scaledResolution.getScaleFactor();
/* 129 */       EntityRenderer entityRenderer = mc.entityRenderer;
/* 130 */       RenderManager renderMng = mc.getRenderManager();
/*     */       
/* 132 */       int black = (new Color(0, 0, 0)).getRGB();
/* 133 */       int color = (new Color(0, 180, 255)).getRGB();
/*     */       
/* 135 */       List<Entity> collectedEntities = this.collectedEntities;
/* 136 */       for (Object entlist : mc.theWorld.loadedEntityList) {
/*     */         
/* 138 */         Entity entity = (Entity)entlist;
/*     */         
/* 140 */         if (entity instanceof net.minecraft.entity.player.EntityPlayer && entity != mc.thePlayer) {
/* 141 */           double x = RenderUtil.interpolate(entity.posX, entity.lastTickPosX, partialTicks);
/* 142 */           double y = RenderUtil.interpolate(entity.posY, entity.lastTickPosY, partialTicks);
/* 143 */           double z = RenderUtil.interpolate(entity.posZ, entity.lastTickPosZ, partialTicks);
/* 144 */           double width = entity.width / 1.5D;
/* 145 */           double height = entity.height + (entity.isSneaking() ? -0.3D : 0.2D);
/*     */           
/* 147 */           AxisAlignedBB aabb = new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width);
/* 148 */           List<Vector3d> vectors = Arrays.asList(new Vector3d[] { new Vector3d(aabb.minX, aabb.minY, aabb.minZ), new Vector3d(aabb.minX, aabb.maxY, aabb.minZ), 
/* 149 */                 new Vector3d(aabb.maxX, aabb.minY, aabb.minZ), new Vector3d(aabb.maxX, aabb.maxY, aabb.minZ), 
/* 150 */                 new Vector3d(aabb.minX, aabb.minY, aabb.maxZ), new Vector3d(aabb.minX, aabb.maxY, aabb.maxZ), 
/* 151 */                 new Vector3d(aabb.maxX, aabb.minY, aabb.maxZ), new Vector3d(aabb.maxX, aabb.maxY, aabb.maxZ) });
/* 152 */           entityRenderer.setupCameraTransform(partialTicks, 0);
/* 153 */           Vector4d position = null;
/* 154 */           GL11.glPushMatrix();
/* 155 */           for (Vector3d vector : vectors) {
/* 156 */             vector = project2D(scaleFactor, vector.x - renderMng.viewerPosX, vector.y - renderMng.viewerPosY, vector.z - renderMng.viewerPosZ);
/*     */             
/* 158 */             if (vector != null && vector.z >= 0.0D && vector.z < 1.0D) {
/* 159 */               if (position == null) {
/* 160 */                 position = new Vector4d(vector.x, vector.y, vector.z, 0.0D);
/*     */               }
/*     */               
/* 163 */               position.x = Math.min(vector.x, position.x);
/* 164 */               position.y = Math.min(vector.y, position.y);
/* 165 */               position.z = Math.max(vector.x, position.z);
/* 166 */               position.w = Math.max(vector.y, position.w);
/*     */             } 
/*     */           } 
/* 169 */           GL11.glPopMatrix();
/*     */           
/* 171 */           if (position != null) {
/* 172 */             entityRenderer.setupOverlayRendering();
/* 173 */             double posX = position.x;
/* 174 */             double posY = position.y;
/* 175 */             double endPosX = position.z;
/* 176 */             float endPosY = (float)position.w;
/*     */ 
/*     */             
/* 179 */             int count = 0;
/*     */ 
/*     */             
/* 182 */             Gui.drawRect(posX + 0.5D, posY, posX - 1.0D, posY + (endPosY - posY) / 1.0D + 0.5D, black);
/* 183 */             Gui.drawRect(posX - 1.0D, endPosY, posX + 0.5D, endPosY - (endPosY - posY) / 1.0D - 0.5D, black);
/* 184 */             Gui.drawRect(posX - 1.0D, posY - 0.5D, posX + (endPosX - posX) / 1.0D + 0.5D, posY + 1.0D, black);
/* 185 */             Gui.drawRect(endPosX - (endPosX - posX) / 1.0D - 0.5D, posY - 0.5D, endPosX, posY + 1.0D, black);
/* 186 */             Gui.drawRect(endPosX - 1.0D, posY, endPosX + 0.5D, posY + (endPosY - posY) / 4.0D + 0.5D, black);
/* 187 */             Gui.drawRect(endPosX - 1.0D, endPosY, endPosX + 0.5D, endPosY - (endPosY - posY) / 1.0D - 0.5D, black);
/* 188 */             Gui.drawRect(posX - 1.0D, (endPosY - 1.0F), posX + (endPosX - posX) / 3.0D + 0.5D, endPosY + 0.5D, black);
/* 189 */             Gui.drawRect(endPosX - (endPosX - posX) / 1.0D - 0.5D, (endPosY - 1.0F), endPosX + 0.5D, endPosY + 0.5D, black);
/* 190 */             Gui.drawRect(posX, posY, posX - 0.5D, posY + (endPosY - posY) / 1.0D, color);
/* 191 */             Gui.drawRect(posX, endPosY, posX - 0.5D, endPosY - (endPosY - posY) / 1.0D, color);
/* 192 */             Gui.drawRect(posX - 0.5D, posY, posX + (endPosX - posX) / 1.0D, posY + 0.5D, color);
/* 193 */             Gui.drawRect(endPosX - (endPosX - posX) / 1.0D, posY, endPosX, posY + 0.5D, color);
/* 194 */             Gui.drawRect(endPosX - 0.5D, posY, endPosX, posY + (endPosY - posY) / 1.0D, color);
/* 195 */             Gui.drawRect(endPosX - 0.5D, endPosY, endPosX, endPosY - (endPosY - posY) / 1.0D, color);
/* 196 */             Gui.drawRect(posX, endPosY - 0.5D, posX + (endPosX - posX) / 1.0D, endPosY, color);
/* 197 */             Gui.drawRect(endPosX - (endPosX - posX) / 1.0D, endPosY - 0.5D, endPosX - 0.5D, endPosY, color);
/*     */ 
/*     */             
/* 200 */             Gui.drawRect(endPosX + 15.0D, endPosY, endPosX + 3.0D, endPosY - (endPosY - posY) / 1.0D - 0.5D, (new Color(80, 80, 80)).getRGB());
/* 201 */             Gui.drawRect(endPosX + 15.0D, endPosY, endPosX + 3.0D, endPosY - (endPosY - posY) / (((EntityLivingBase)entity).getHealth() / 20.0F) - 0.5D, (new Color(255, 0, 0)).getRGB());
/* 202 */             Gui.drawRect(endPosX + 15.0D, endPosY, endPosX + 3.0D, (endPosY - 1.0F), black);
/* 203 */             Gui.drawRect(endPosX + 2.0D, endPosY, endPosX + 3.0D, endPosY - (endPosY - posY) / 1.0D - 0.3D, black);
/* 204 */             Gui.drawRect(endPosX + 15.0D, endPosY - (endPosY - posY) / 1.0D - 0.5D + 1.0D, endPosX + 3.0D, endPosY - (endPosY - posY) / 1.0D - 0.5D, black);
/* 205 */             Gui.drawRect(endPosX + 15.0D, endPosY, endPosX + 14.0D, endPosY - (endPosY - posY) / 1.0D - 0.1D, black);
/*     */           } 
/*     */         } 
/* 208 */       }  entityRenderer.setupOverlayRendering();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void player(EntityLivingBase entity) {
/* 217 */     float red = 0.5F;
/* 218 */     float green = 0.5F;
/* 219 */     float blue = 1.0F;
/*     */     
/* 221 */     mc.getRenderManager(); double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 222 */     mc.getRenderManager(); double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 223 */     mc.getRenderManager(); double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 225 */     render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
/*     */   }
/*     */   
/*     */   public static void mob(EntityLivingBase entity) {
/* 229 */     float red = 1.0F;
/* 230 */     float green = 0.5F;
/* 231 */     float blue = 0.5F;
/*     */     
/* 233 */     mc.getRenderManager(); double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 234 */     mc.getRenderManager(); double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 235 */     mc.getRenderManager(); double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 237 */     render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
/*     */   }
/*     */   
/*     */   public static void animal(EntityLivingBase entity) {
/* 241 */     float red = 0.5F;
/* 242 */     float green = 1.0F;
/* 243 */     float blue = 0.5F;
/*     */     
/* 245 */     mc.getRenderManager(); double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 246 */     mc.getRenderManager(); double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 247 */     mc.getRenderManager(); double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 249 */     render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
/*     */   }
/*     */   
/*     */   public static void passive(EntityLivingBase entity) {
/* 253 */     float red = 0.5F;
/* 254 */     float green = 0.5F;
/* 255 */     float blue = 0.5F;
/*     */     
/* 257 */     mc.getRenderManager(); double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 258 */     mc.getRenderManager(); double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 259 */     mc.getRenderManager(); double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 261 */     render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
/*     */   }
/*     */   
/*     */   public static void render(float red, float green, float blue, double x, double y, double z, float width, float height) {
/* 265 */     RenderUtil.drawOutlinedEntityESP(x, y, z, width, height, red, green, blue, 0.45F);
/*     */   }
/*     */   
/*     */   private Vector3d project2D(int scaleFactor, double x, double y, double z) {
/* 269 */     GL11.glGetFloat(2982, this.modelview);
/* 270 */     GL11.glGetFloat(2983, this.projection);
/* 271 */     GL11.glGetInteger(2978, this.viewport);
/*     */     
/* 273 */     if (GLU.gluProject((float)x, (float)y, (float)z, this.modelview, this.projection, this.viewport, this.vector)) {
/* 274 */       return new Vector3d((this.vector.get(0) / scaleFactor), ((Display.getHeight() - this.vector.get(1)) / scaleFactor), this.vector.get(2));
/*     */     }
/*     */     
/* 277 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\ESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */