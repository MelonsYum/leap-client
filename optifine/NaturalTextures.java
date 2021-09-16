/*     */ package optifine;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class NaturalTextures
/*     */ {
/*  14 */   private static NaturalProperties[] propertiesByIndex = new NaturalProperties[0];
/*     */ 
/*     */   
/*     */   public static void update() {
/*  18 */     propertiesByIndex = new NaturalProperties[0];
/*     */     
/*  20 */     if (Config.isNaturalTextures()) {
/*     */       
/*  22 */       String fileName = "optifine/natural.properties";
/*     */ 
/*     */       
/*     */       try {
/*  26 */         ResourceLocation e = new ResourceLocation(fileName);
/*     */         
/*  28 */         if (!Config.hasResource(e)) {
/*     */           
/*  30 */           Config.dbg("NaturalTextures: configuration \"" + fileName + "\" not found");
/*     */           
/*     */           return;
/*     */         } 
/*  34 */         boolean defaultConfig = Config.isFromDefaultResourcePack(e);
/*  35 */         InputStream in = Config.getResourceStream(e);
/*  36 */         ArrayList<NaturalProperties> list = new ArrayList(256);
/*  37 */         String configStr = Config.readInputStream(in);
/*  38 */         in.close();
/*  39 */         String[] configLines = Config.tokenize(configStr, "\n\r");
/*     */         
/*  41 */         if (defaultConfig) {
/*     */           
/*  43 */           Config.dbg("Natural Textures: Parsing default configuration \"" + fileName + "\"");
/*  44 */           Config.dbg("Natural Textures: Valid only for textures from default resource pack");
/*     */         }
/*     */         else {
/*     */           
/*  48 */           Config.dbg("Natural Textures: Parsing configuration \"" + fileName + "\"");
/*     */         } 
/*     */         
/*  51 */         TextureMap textureMapBlocks = TextureUtils.getTextureMapBlocks();
/*     */         
/*  53 */         for (int i = 0; i < configLines.length; i++) {
/*     */           
/*  55 */           String line = configLines[i].trim();
/*     */           
/*  57 */           if (!line.startsWith("#")) {
/*     */             
/*  59 */             String[] strs = Config.tokenize(line, "=");
/*     */             
/*  61 */             if (strs.length != 2) {
/*     */               
/*  63 */               Config.warn("Natural Textures: Invalid \"" + fileName + "\" line: " + line);
/*     */             }
/*     */             else {
/*     */               
/*  67 */               String key = strs[0].trim();
/*  68 */               String type = strs[1].trim();
/*  69 */               TextureAtlasSprite ts = textureMapBlocks.getSpriteSafe("minecraft:blocks/" + key);
/*     */               
/*  71 */               if (ts == null) {
/*     */                 
/*  73 */                 Config.warn("Natural Textures: Texture not found: \"" + fileName + "\" line: " + line);
/*     */               }
/*     */               else {
/*     */                 
/*  77 */                 int tileNum = ts.getIndexInMap();
/*     */                 
/*  79 */                 if (tileNum < 0) {
/*     */                   
/*  81 */                   Config.warn("Natural Textures: Invalid \"" + fileName + "\" line: " + line);
/*     */                 }
/*     */                 else {
/*     */                   
/*  85 */                   if (defaultConfig && !Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/" + key + ".png"))) {
/*     */                     return;
/*     */                   }
/*     */ 
/*     */                   
/*  90 */                   NaturalProperties props = new NaturalProperties(type);
/*     */                   
/*  92 */                   if (props.isValid()) {
/*     */                     
/*  94 */                     while (list.size() <= tileNum)
/*     */                     {
/*  96 */                       list.add(null);
/*     */                     }
/*     */                     
/*  99 */                     list.set(tileNum, props);
/* 100 */                     Config.dbg("NaturalTextures: " + key + " = " + type);
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 108 */         propertiesByIndex = list.<NaturalProperties>toArray(new NaturalProperties[list.size()]);
/*     */       }
/* 110 */       catch (FileNotFoundException var17) {
/*     */         
/* 112 */         Config.warn("NaturalTextures: configuration \"" + fileName + "\" not found");
/*     */         
/*     */         return;
/* 115 */       } catch (Exception var18) {
/*     */         
/* 117 */         var18.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static BakedQuad getNaturalTexture(BlockPos blockPosIn, BakedQuad quad) {
/* 124 */     TextureAtlasSprite sprite = quad.getSprite();
/*     */     
/* 126 */     if (sprite == null)
/*     */     {
/* 128 */       return quad;
/*     */     }
/*     */ 
/*     */     
/* 132 */     NaturalProperties nps = getNaturalProperties(sprite);
/*     */     
/* 134 */     if (nps == null)
/*     */     {
/* 136 */       return quad;
/*     */     }
/*     */ 
/*     */     
/* 140 */     int side = ConnectedTextures.getSide(quad.getFace());
/* 141 */     int rand = Config.getRandom(blockPosIn, side);
/* 142 */     int rotate = 0;
/* 143 */     boolean flipU = false;
/*     */     
/* 145 */     if (nps.rotation > 1)
/*     */     {
/* 147 */       rotate = rand & 0x3;
/*     */     }
/*     */     
/* 150 */     if (nps.rotation == 2)
/*     */     {
/* 152 */       rotate = rotate / 2 * 2;
/*     */     }
/*     */     
/* 155 */     if (nps.flip)
/*     */     {
/* 157 */       flipU = ((rand & 0x4) != 0);
/*     */     }
/*     */     
/* 160 */     return nps.getQuad(quad, rotate, flipU);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NaturalProperties getNaturalProperties(TextureAtlasSprite icon) {
/* 167 */     if (!(icon instanceof TextureAtlasSprite))
/*     */     {
/* 169 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 173 */     int tileNum = icon.getIndexInMap();
/*     */     
/* 175 */     if (tileNum >= 0 && tileNum < propertiesByIndex.length) {
/*     */       
/* 177 */       NaturalProperties props = propertiesByIndex[tileNum];
/* 178 */       return props;
/*     */     } 
/*     */ 
/*     */     
/* 182 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\NaturalTextures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */