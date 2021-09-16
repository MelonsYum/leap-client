/*     */ package optifine;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class PlayerConfigurationParser
/*     */ {
/*  17 */   private String player = null;
/*     */   
/*     */   public static final String CONFIG_ITEMS = "items";
/*     */   public static final String ITEM_TYPE = "type";
/*     */   public static final String ITEM_ACTIVE = "active";
/*     */   
/*     */   public PlayerConfigurationParser(String player) {
/*  24 */     this.player = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerConfiguration parsePlayerConfiguration(JsonElement je) {
/*  29 */     if (je == null)
/*     */     {
/*  31 */       throw new JsonParseException("JSON object is null, player: " + this.player);
/*     */     }
/*     */ 
/*     */     
/*  35 */     JsonObject jo = (JsonObject)je;
/*  36 */     PlayerConfiguration pc = new PlayerConfiguration();
/*  37 */     JsonArray items = (JsonArray)jo.get("items");
/*     */     
/*  39 */     if (items != null)
/*     */     {
/*  41 */       for (int i = 0; i < items.size(); i++) {
/*     */         
/*  43 */         JsonObject item = (JsonObject)items.get(i);
/*  44 */         boolean active = Json.getBoolean(item, "active", true);
/*     */         
/*  46 */         if (active) {
/*     */           
/*  48 */           String type = Json.getString(item, "type");
/*     */           
/*  50 */           if (type == null) {
/*     */             
/*  52 */             Config.warn("Item type is null, player: " + this.player);
/*     */             
/*     */             continue;
/*     */           } 
/*  56 */           String modelPath = Json.getString(item, "model");
/*     */           
/*  58 */           if (modelPath == null)
/*     */           {
/*  60 */             modelPath = "items/" + type + "/model.cfg";
/*     */           }
/*     */           
/*  63 */           PlayerItemModel model = downloadModel(modelPath);
/*     */           
/*  65 */           if (model != null) {
/*     */             
/*  67 */             if (!model.isUsePlayerTexture()) {
/*     */               
/*  69 */               String texturePath = Json.getString(item, "texture");
/*     */               
/*  71 */               if (texturePath == null)
/*     */               {
/*  73 */                 texturePath = "items/" + type + "/users/" + this.player + ".png";
/*     */               }
/*     */               
/*  76 */               BufferedImage image = downloadTextureImage(texturePath);
/*     */               
/*  78 */               if (image == null) {
/*     */                 continue;
/*     */               }
/*     */ 
/*     */               
/*  83 */               model.setTextureImage(image);
/*  84 */               ResourceLocation loc = new ResourceLocation("optifine.net", texturePath);
/*  85 */               model.setTextureLocation(loc);
/*     */             } 
/*     */             
/*  88 */             pc.addPlayerItemModel(model);
/*     */           } 
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/*     */     }
/*  95 */     return pc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage downloadTextureImage(String texturePath) {
/* 101 */     String textureUrl = "http://s.optifine.net/" + texturePath;
/*     */ 
/*     */     
/*     */     try {
/* 105 */       byte[] e = HttpPipeline.get(textureUrl, Minecraft.getMinecraft().getProxy());
/* 106 */       BufferedImage image = ImageIO.read(new ByteArrayInputStream(e));
/* 107 */       return image;
/*     */     }
/* 109 */     catch (IOException var5) {
/*     */       
/* 111 */       Config.warn("Error loading item texture " + texturePath + ": " + var5.getClass().getName() + ": " + var5.getMessage());
/* 112 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private PlayerItemModel downloadModel(String modelPath) {
/* 118 */     String modelUrl = "http://s.optifine.net/" + modelPath;
/*     */ 
/*     */     
/*     */     try {
/* 122 */       byte[] e = HttpPipeline.get(modelUrl, Minecraft.getMinecraft().getProxy());
/* 123 */       String jsonStr = new String(e, "ASCII");
/* 124 */       JsonParser jp = new JsonParser();
/* 125 */       JsonObject jo = (JsonObject)jp.parse(jsonStr);
/* 126 */       PlayerItemParser pip = new PlayerItemParser();
/* 127 */       PlayerItemModel pim = PlayerItemParser.parseItemModel(jo);
/* 128 */       return pim;
/*     */     }
/* 130 */     catch (Exception var9) {
/*     */       
/* 132 */       Config.warn("Error loading item model " + modelPath + ": " + var9.getClass().getName() + ": " + var9.getMessage());
/* 133 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerConfigurationParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */