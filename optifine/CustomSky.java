/*     */ package optifine;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Properties;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CustomSky
/*     */ {
/*  15 */   private static CustomSkyLayer[][] worldSkyLayers = null;
/*     */ 
/*     */   
/*     */   public static void reset() {
/*  19 */     worldSkyLayers = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void update() {
/*  24 */     reset();
/*     */     
/*  26 */     if (Config.isCustomSky())
/*     */     {
/*  28 */       worldSkyLayers = readCustomSkies();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static CustomSkyLayer[][] readCustomSkies() {
/*  34 */     CustomSkyLayer[][] wsls = new CustomSkyLayer[10][0];
/*  35 */     String prefix = "mcpatcher/sky/world";
/*  36 */     int lastWorldId = -1;
/*  37 */     int worldCount = 0;
/*     */     
/*  39 */     while (worldCount < wsls.length) {
/*     */       
/*  41 */       String wslsTrim = String.valueOf(prefix) + worldCount + "/sky";
/*  42 */       ArrayList<CustomSkyLayer> i = new ArrayList();
/*  43 */       int sls = 1;
/*     */ 
/*     */ 
/*     */       
/*  47 */       while (sls < 1000) {
/*     */ 
/*     */ 
/*     */         
/*  51 */         String path = String.valueOf(wslsTrim) + sls + ".properties";
/*     */ 
/*     */         
/*     */         try {
/*  55 */           ResourceLocation e = new ResourceLocation(path);
/*  56 */           InputStream in = Config.getResourceStream(e);
/*     */           
/*  58 */           if (in == null) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/*  63 */           Properties props = new Properties();
/*  64 */           props.load(in);
/*  65 */           in.close();
/*  66 */           Config.dbg("CustomSky properties: " + path);
/*  67 */           String defSource = String.valueOf(wslsTrim) + sls + ".png";
/*  68 */           CustomSkyLayer sl = new CustomSkyLayer(props, defSource);
/*     */           
/*  70 */           if (sl.isValid(path)) {
/*     */             
/*  72 */             ResourceLocation locSource = new ResourceLocation(sl.source);
/*  73 */             ITextureObject tex = TextureUtils.getTexture(locSource);
/*     */             
/*  75 */             if (tex == null)
/*     */             {
/*  77 */               Config.log("CustomSky: Texture not found: " + locSource);
/*     */             }
/*     */             else
/*     */             {
/*  81 */               sl.textureId = tex.getGlTextureId();
/*  82 */               i.add(sl);
/*  83 */               in.close();
/*     */             }
/*     */           
/*     */           } 
/*  87 */         } catch (FileNotFoundException var15) {
/*     */           
/*     */           break;
/*     */         }
/*  91 */         catch (IOException var16) {
/*     */           
/*  93 */           var16.printStackTrace();
/*     */         } 
/*     */         
/*  96 */         sls++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 101 */       if (i.size() > 0) {
/*     */         
/* 103 */         CustomSkyLayer[] var19 = i.<CustomSkyLayer>toArray(new CustomSkyLayer[i.size()]);
/* 104 */         wsls[worldCount] = var19;
/* 105 */         lastWorldId = worldCount;
/*     */       } 
/*     */       
/* 108 */       worldCount++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (lastWorldId < 0)
/*     */     {
/* 115 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 119 */     worldCount = lastWorldId + 1;
/* 120 */     CustomSkyLayer[][] var17 = new CustomSkyLayer[worldCount][0];
/*     */     
/* 122 */     for (int var18 = 0; var18 < var17.length; var18++)
/*     */     {
/* 124 */       var17[var18] = wsls[var18];
/*     */     }
/*     */     
/* 127 */     return var17;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderSky(World world, TextureManager re, float celestialAngle, float rainBrightness) {
/* 133 */     if (worldSkyLayers != null)
/*     */     {
/* 135 */       if ((Config.getGameSettings()).renderDistanceChunks >= 8) {
/*     */         
/* 137 */         int dimId = world.provider.getDimensionId();
/*     */         
/* 139 */         if (dimId >= 0 && dimId < worldSkyLayers.length) {
/*     */           
/* 141 */           CustomSkyLayer[] sls = worldSkyLayers[dimId];
/*     */           
/* 143 */           if (sls != null) {
/*     */             
/* 145 */             long time = world.getWorldTime();
/* 146 */             int timeOfDay = (int)(time % 24000L);
/*     */             
/* 148 */             for (int i = 0; i < sls.length; i++) {
/*     */               
/* 150 */               CustomSkyLayer sl = sls[i];
/*     */               
/* 152 */               if (sl.isActive(world, timeOfDay))
/*     */               {
/* 154 */                 sl.render(timeOfDay, celestialAngle, rainBrightness);
/*     */               }
/*     */             } 
/*     */             
/* 158 */             Blender.clearBlend(rainBrightness);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasSkyLayers(World world) {
/* 167 */     if (worldSkyLayers == null)
/*     */     {
/* 169 */       return false;
/*     */     }
/* 171 */     if ((Config.getGameSettings()).renderDistanceChunks < 8)
/*     */     {
/* 173 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 177 */     int dimId = world.provider.getDimensionId();
/*     */     
/* 179 */     if (dimId >= 0 && dimId < worldSkyLayers.length) {
/*     */       
/* 181 */       CustomSkyLayer[] sls = worldSkyLayers[dimId];
/* 182 */       return (sls == null) ? false : ((sls.length > 0));
/*     */     } 
/*     */ 
/*     */     
/* 186 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomSky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */