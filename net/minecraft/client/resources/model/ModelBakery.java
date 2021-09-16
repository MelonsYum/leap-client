/*     */ package net.minecraft.client.resources.model;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Queues;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.BlockModelShapes;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.BlockPart;
/*     */ import net.minecraft.client.renderer.block.model.BlockPartFace;
/*     */ import net.minecraft.client.renderer.block.model.FaceBakery;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.block.model.ItemModelGenerator;
/*     */ import net.minecraft.client.renderer.block.model.ModelBlock;
/*     */ import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
/*     */ import net.minecraft.client.renderer.texture.IIconCreator;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IRegistry;
/*     */ import net.minecraft.util.RegistrySimple;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ModelBakery
/*     */ {
/*  53 */   private static final Set field_177602_b = Sets.newHashSet((Object[])new ResourceLocation[] { new ResourceLocation("blocks/water_flow"), new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/lava_flow"), new ResourceLocation("blocks/lava_still"), new ResourceLocation("blocks/destroy_stage_0"), new ResourceLocation("blocks/destroy_stage_1"), new ResourceLocation("blocks/destroy_stage_2"), new ResourceLocation("blocks/destroy_stage_3"), new ResourceLocation("blocks/destroy_stage_4"), new ResourceLocation("blocks/destroy_stage_5"), new ResourceLocation("blocks/destroy_stage_6"), new ResourceLocation("blocks/destroy_stage_7"), new ResourceLocation("blocks/destroy_stage_8"), new ResourceLocation("blocks/destroy_stage_9"), new ResourceLocation("items/empty_armor_slot_helmet"), new ResourceLocation("items/empty_armor_slot_chestplate"), new ResourceLocation("items/empty_armor_slot_leggings"), new ResourceLocation("items/empty_armor_slot_boots") });
/*  54 */   private static final Logger LOGGER = LogManager.getLogger();
/*  55 */   protected static final ModelResourceLocation MODEL_MISSING = new ModelResourceLocation("builtin/missing", "missing");
/*  56 */   private static final Map BUILT_IN_MODELS = Maps.newHashMap();
/*     */ 
/*     */   
/*  59 */   private final Map field_177599_g = Maps.newHashMap();
/*  60 */   private final Map models = Maps.newLinkedHashMap();
/*  61 */   private final Map variants = Maps.newLinkedHashMap();
/*     */ 
/*     */   
/*  64 */   private final FaceBakery field_177607_l = new FaceBakery();
/*  65 */   private final ItemModelGenerator itemModelGenerator = new ItemModelGenerator();
/*  66 */   private RegistrySimple bakedRegistry = new RegistrySimple();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private Map itemLocations = Maps.newLinkedHashMap();
/*  72 */   private final Map field_177614_t = Maps.newHashMap();
/*  73 */   private Map variantNames = Maps.newIdentityHashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelBakery(IResourceManager p_i46085_1_, TextureMap p_i46085_2_, BlockModelShapes p_i46085_3_) {
/*  78 */     this.resourceManager = p_i46085_1_;
/*  79 */     this.textureMap = p_i46085_2_;
/*  80 */     this.blockModelShapes = p_i46085_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRegistry setupModelRegistry() {
/*  85 */     func_177577_b();
/*  86 */     func_177597_h();
/*  87 */     func_177572_j();
/*  88 */     bakeItemModels();
/*  89 */     bakeBlockModels();
/*  90 */     return (IRegistry)this.bakedRegistry;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177577_b() {
/*  95 */     loadVariants(this.blockModelShapes.getBlockStateMapper().func_178446_a().values());
/*  96 */     this.variants.put(MODEL_MISSING, new ModelBlockDefinition.Variants(MODEL_MISSING.func_177518_c(), Lists.newArrayList((Object[])new ModelBlockDefinition.Variant[] { new ModelBlockDefinition.Variant(new ResourceLocation(MODEL_MISSING.getResourcePath()), ModelRotation.X0_Y0, false, 1) })));
/*  97 */     ResourceLocation var1 = new ResourceLocation("item_frame");
/*  98 */     ModelBlockDefinition var2 = getModelBlockDefinition(var1);
/*  99 */     registerVariant(var2, new ModelResourceLocation(var1, "normal"));
/* 100 */     registerVariant(var2, new ModelResourceLocation(var1, "map"));
/* 101 */     func_177595_c();
/* 102 */     loadItemModels();
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadVariants(Collection p_177591_1_) {
/* 107 */     Iterator<ModelResourceLocation> var2 = p_177591_1_.iterator();
/*     */     
/* 109 */     while (var2.hasNext()) {
/*     */       
/* 111 */       ModelResourceLocation var3 = var2.next();
/*     */ 
/*     */       
/*     */       try {
/* 115 */         ModelBlockDefinition var4 = getModelBlockDefinition(var3);
/*     */ 
/*     */         
/*     */         try {
/* 119 */           registerVariant(var4, var3);
/*     */         }
/* 121 */         catch (Exception var6) {
/*     */           
/* 123 */           LOGGER.warn("Unable to load variant: " + var3.func_177518_c() + " from " + var3);
/*     */         }
/*     */       
/* 126 */       } catch (Exception var7) {
/*     */         
/* 128 */         LOGGER.warn("Unable to load definition " + var3, var7);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerVariant(ModelBlockDefinition p_177569_1_, ModelResourceLocation p_177569_2_) {
/* 135 */     this.variants.put(p_177569_2_, p_177569_1_.func_178330_b(p_177569_2_.func_177518_c()));
/*     */   }
/*     */ 
/*     */   
/*     */   private ModelBlockDefinition getModelBlockDefinition(ResourceLocation p_177586_1_) {
/* 140 */     ResourceLocation var2 = getBlockStateLocation(p_177586_1_);
/* 141 */     ModelBlockDefinition var3 = (ModelBlockDefinition)this.field_177614_t.get(var2);
/*     */     
/* 143 */     if (var3 == null) {
/*     */       
/* 145 */       ArrayList<ModelBlockDefinition> var4 = Lists.newArrayList();
/*     */ 
/*     */       
/*     */       try {
/* 149 */         Iterator<IResource> var5 = this.resourceManager.getAllResources(var2).iterator();
/*     */         
/* 151 */         while (var5.hasNext()) {
/*     */           
/* 153 */           IResource var6 = var5.next();
/* 154 */           InputStream var7 = null;
/*     */ 
/*     */           
/*     */           try {
/* 158 */             var7 = var6.getInputStream();
/* 159 */             ModelBlockDefinition var8 = ModelBlockDefinition.func_178331_a(new InputStreamReader(var7, Charsets.UTF_8));
/* 160 */             var4.add(var8);
/*     */           }
/* 162 */           catch (Exception var13) {
/*     */             
/* 164 */             throw new RuntimeException("Encountered an exception when loading model definition of '" + p_177586_1_ + "' from: '" + var6.func_177241_a() + "' in resourcepack: '" + var6.func_177240_d() + "'", var13);
/*     */           }
/*     */           finally {
/*     */             
/* 168 */             IOUtils.closeQuietly(var7);
/*     */           }
/*     */         
/*     */         } 
/* 172 */       } catch (IOException var15) {
/*     */         
/* 174 */         throw new RuntimeException("Encountered an exception when loading model definition of model " + var2.toString(), var15);
/*     */       } 
/*     */       
/* 177 */       var3 = new ModelBlockDefinition(var4);
/* 178 */       this.field_177614_t.put(var2, var3);
/*     */     } 
/*     */     
/* 181 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation getBlockStateLocation(ResourceLocation p_177584_1_) {
/* 186 */     return new ResourceLocation(p_177584_1_.getResourceDomain(), "blockstates/" + p_177584_1_.getResourcePath() + ".json");
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177595_c() {
/* 191 */     Iterator<ModelResourceLocation> var1 = this.variants.keySet().iterator();
/*     */     
/* 193 */     while (var1.hasNext()) {
/*     */       
/* 195 */       ModelResourceLocation var2 = var1.next();
/* 196 */       Iterator<ModelBlockDefinition.Variant> var3 = ((ModelBlockDefinition.Variants)this.variants.get(var2)).getVariants().iterator();
/*     */       
/* 198 */       while (var3.hasNext()) {
/*     */         
/* 200 */         ModelBlockDefinition.Variant var4 = var3.next();
/* 201 */         ResourceLocation var5 = var4.getModelLocation();
/*     */         
/* 203 */         if (this.models.get(var5) == null)
/*     */           
/*     */           try {
/*     */             
/* 207 */             ModelBlock var6 = loadModel(var5);
/* 208 */             this.models.put(var5, var6);
/*     */           }
/* 210 */           catch (Exception var7) {
/*     */             
/* 212 */             LOGGER.warn("Unable to load block model: '" + var5 + "' for variant: '" + var2 + "'", var7);
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private ModelBlock loadModel(ResourceLocation p_177594_1_) throws IOException {
/*     */     Object var2;
/*     */     ModelBlock var11;
/* 221 */     String var3 = p_177594_1_.getResourcePath();
/*     */     
/* 223 */     if ("builtin/generated".equals(var3))
/*     */     {
/* 225 */       return MODEL_GENERATED;
/*     */     }
/* 227 */     if ("builtin/compass".equals(var3))
/*     */     {
/* 229 */       return MODEL_COMPASS;
/*     */     }
/* 231 */     if ("builtin/clock".equals(var3))
/*     */     {
/* 233 */       return MODEL_CLOCK;
/*     */     }
/* 235 */     if ("builtin/entity".equals(var3))
/*     */     {
/* 237 */       return MODEL_ENTITY;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     if (var3.startsWith("builtin/")) {
/*     */       
/* 245 */       String var4 = var3.substring("builtin/".length());
/* 246 */       String var5 = (String)BUILT_IN_MODELS.get(var4);
/*     */       
/* 248 */       if (var5 == null)
/*     */       {
/* 250 */         throw new FileNotFoundException(p_177594_1_.toString());
/*     */       }
/*     */       
/* 253 */       var2 = new StringReader(var5);
/*     */     }
/*     */     else {
/*     */       
/* 257 */       IResource var9 = this.resourceManager.getResource(getModelLocation(p_177594_1_));
/* 258 */       var2 = new InputStreamReader(var9.getInputStream(), Charsets.UTF_8);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 265 */       ModelBlock var10 = ModelBlock.deserialize((Reader)var2);
/* 266 */       var10.field_178317_b = p_177594_1_.toString();
/* 267 */       var11 = var10;
/*     */     }
/*     */     finally {
/*     */       
/* 271 */       ((Reader)var2).close();
/*     */     } 
/*     */     
/* 274 */     return var11;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceLocation getModelLocation(ResourceLocation p_177580_1_) {
/* 280 */     return new ResourceLocation(p_177580_1_.getResourceDomain(), "models/" + p_177580_1_.getResourcePath() + ".json");
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadItemModels() {
/* 285 */     registerVariantNames();
/* 286 */     Iterator<Item> var1 = Item.itemRegistry.iterator();
/*     */     
/* 288 */     while (var1.hasNext()) {
/*     */       
/* 290 */       Item var2 = var1.next();
/* 291 */       List var3 = getVariantNames(var2);
/* 292 */       Iterator<String> var4 = var3.iterator();
/*     */       
/* 294 */       while (var4.hasNext()) {
/*     */         
/* 296 */         String var5 = var4.next();
/* 297 */         ResourceLocation var6 = getItemLocation(var5);
/* 298 */         this.itemLocations.put(var5, var6);
/*     */         
/* 300 */         if (this.models.get(var6) == null) {
/*     */           
/*     */           try {
/*     */             
/* 304 */             ModelBlock var7 = loadModel(var6);
/* 305 */             this.models.put(var6, var7);
/*     */           }
/* 307 */           catch (Exception var8) {
/*     */             
/* 309 */             LOGGER.warn("Unable to load item model: '" + var6 + "' for item: '" + Item.itemRegistry.getNameForObject(var2) + "'", var8);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerVariantNames() {
/* 318 */     this.variantNames.put(Item.getItemFromBlock(Blocks.stone), Lists.newArrayList((Object[])new String[] { "stone", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth" }));
/* 319 */     this.variantNames.put(Item.getItemFromBlock(Blocks.dirt), Lists.newArrayList((Object[])new String[] { "dirt", "coarse_dirt", "podzol" }));
/* 320 */     this.variantNames.put(Item.getItemFromBlock(Blocks.planks), Lists.newArrayList((Object[])new String[] { "oak_planks", "spruce_planks", "birch_planks", "jungle_planks", "acacia_planks", "dark_oak_planks" }));
/* 321 */     this.variantNames.put(Item.getItemFromBlock(Blocks.sapling), Lists.newArrayList((Object[])new String[] { "oak_sapling", "spruce_sapling", "birch_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling" }));
/* 322 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.sand), Lists.newArrayList((Object[])new String[] { "sand", "red_sand" }));
/* 323 */     this.variantNames.put(Item.getItemFromBlock(Blocks.log), Lists.newArrayList((Object[])new String[] { "oak_log", "spruce_log", "birch_log", "jungle_log" }));
/* 324 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.leaves), Lists.newArrayList((Object[])new String[] { "oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves" }));
/* 325 */     this.variantNames.put(Item.getItemFromBlock(Blocks.sponge), Lists.newArrayList((Object[])new String[] { "sponge", "sponge_wet" }));
/* 326 */     this.variantNames.put(Item.getItemFromBlock(Blocks.sandstone), Lists.newArrayList((Object[])new String[] { "sandstone", "chiseled_sandstone", "smooth_sandstone" }));
/* 327 */     this.variantNames.put(Item.getItemFromBlock(Blocks.red_sandstone), Lists.newArrayList((Object[])new String[] { "red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone" }));
/* 328 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.tallgrass), Lists.newArrayList((Object[])new String[] { "dead_bush", "tall_grass", "fern" }));
/* 329 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.deadbush), Lists.newArrayList((Object[])new String[] { "dead_bush" }));
/* 330 */     this.variantNames.put(Item.getItemFromBlock(Blocks.wool), Lists.newArrayList((Object[])new String[] { "black_wool", "red_wool", "green_wool", "brown_wool", "blue_wool", "purple_wool", "cyan_wool", "silver_wool", "gray_wool", "pink_wool", "lime_wool", "yellow_wool", "light_blue_wool", "magenta_wool", "orange_wool", "white_wool" }));
/* 331 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.yellow_flower), Lists.newArrayList((Object[])new String[] { "dandelion" }));
/* 332 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.red_flower), Lists.newArrayList((Object[])new String[] { "poppy", "blue_orchid", "allium", "houstonia", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy" }));
/* 333 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.stone_slab), Lists.newArrayList((Object[])new String[] { "stone_slab", "sandstone_slab", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab" }));
/* 334 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.stone_slab2), Lists.newArrayList((Object[])new String[] { "red_sandstone_slab" }));
/* 335 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.stained_glass), Lists.newArrayList((Object[])new String[] { "black_stained_glass", "red_stained_glass", "green_stained_glass", "brown_stained_glass", "blue_stained_glass", "purple_stained_glass", "cyan_stained_glass", "silver_stained_glass", "gray_stained_glass", "pink_stained_glass", "lime_stained_glass", "yellow_stained_glass", "light_blue_stained_glass", "magenta_stained_glass", "orange_stained_glass", "white_stained_glass" }));
/* 336 */     this.variantNames.put(Item.getItemFromBlock(Blocks.monster_egg), Lists.newArrayList((Object[])new String[] { "stone_monster_egg", "cobblestone_monster_egg", "stone_brick_monster_egg", "mossy_brick_monster_egg", "cracked_brick_monster_egg", "chiseled_brick_monster_egg" }));
/* 337 */     this.variantNames.put(Item.getItemFromBlock(Blocks.stonebrick), Lists.newArrayList((Object[])new String[] { "stonebrick", "mossy_stonebrick", "cracked_stonebrick", "chiseled_stonebrick" }));
/* 338 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.wooden_slab), Lists.newArrayList((Object[])new String[] { "oak_slab", "spruce_slab", "birch_slab", "jungle_slab", "acacia_slab", "dark_oak_slab" }));
/* 339 */     this.variantNames.put(Item.getItemFromBlock(Blocks.cobblestone_wall), Lists.newArrayList((Object[])new String[] { "cobblestone_wall", "mossy_cobblestone_wall" }));
/* 340 */     this.variantNames.put(Item.getItemFromBlock(Blocks.anvil), Lists.newArrayList((Object[])new String[] { "anvil_intact", "anvil_slightly_damaged", "anvil_very_damaged" }));
/* 341 */     this.variantNames.put(Item.getItemFromBlock(Blocks.quartz_block), Lists.newArrayList((Object[])new String[] { "quartz_block", "chiseled_quartz_block", "quartz_column" }));
/* 342 */     this.variantNames.put(Item.getItemFromBlock(Blocks.stained_hardened_clay), Lists.newArrayList((Object[])new String[] { "black_stained_hardened_clay", "red_stained_hardened_clay", "green_stained_hardened_clay", "brown_stained_hardened_clay", "blue_stained_hardened_clay", "purple_stained_hardened_clay", "cyan_stained_hardened_clay", "silver_stained_hardened_clay", "gray_stained_hardened_clay", "pink_stained_hardened_clay", "lime_stained_hardened_clay", "yellow_stained_hardened_clay", "light_blue_stained_hardened_clay", "magenta_stained_hardened_clay", "orange_stained_hardened_clay", "white_stained_hardened_clay" }));
/* 343 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.stained_glass_pane), Lists.newArrayList((Object[])new String[] { "black_stained_glass_pane", "red_stained_glass_pane", "green_stained_glass_pane", "brown_stained_glass_pane", "blue_stained_glass_pane", "purple_stained_glass_pane", "cyan_stained_glass_pane", "silver_stained_glass_pane", "gray_stained_glass_pane", "pink_stained_glass_pane", "lime_stained_glass_pane", "yellow_stained_glass_pane", "light_blue_stained_glass_pane", "magenta_stained_glass_pane", "orange_stained_glass_pane", "white_stained_glass_pane" }));
/* 344 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.leaves2), Lists.newArrayList((Object[])new String[] { "acacia_leaves", "dark_oak_leaves" }));
/* 345 */     this.variantNames.put(Item.getItemFromBlock(Blocks.log2), Lists.newArrayList((Object[])new String[] { "acacia_log", "dark_oak_log" }));
/* 346 */     this.variantNames.put(Item.getItemFromBlock(Blocks.prismarine), Lists.newArrayList((Object[])new String[] { "prismarine", "prismarine_bricks", "dark_prismarine" }));
/* 347 */     this.variantNames.put(Item.getItemFromBlock(Blocks.carpet), Lists.newArrayList((Object[])new String[] { "black_carpet", "red_carpet", "green_carpet", "brown_carpet", "blue_carpet", "purple_carpet", "cyan_carpet", "silver_carpet", "gray_carpet", "pink_carpet", "lime_carpet", "yellow_carpet", "light_blue_carpet", "magenta_carpet", "orange_carpet", "white_carpet" }));
/* 348 */     this.variantNames.put(Item.getItemFromBlock((Block)Blocks.double_plant), Lists.newArrayList((Object[])new String[] { "sunflower", "syringa", "double_grass", "double_fern", "double_rose", "paeonia" }));
/* 349 */     this.variantNames.put(Items.bow, Lists.newArrayList((Object[])new String[] { "bow", "bow_pulling_0", "bow_pulling_1", "bow_pulling_2" }));
/* 350 */     this.variantNames.put(Items.coal, Lists.newArrayList((Object[])new String[] { "coal", "charcoal" }));
/* 351 */     this.variantNames.put(Items.fishing_rod, Lists.newArrayList((Object[])new String[] { "fishing_rod", "fishing_rod_cast" }));
/* 352 */     this.variantNames.put(Items.fish, Lists.newArrayList((Object[])new String[] { "cod", "salmon", "clownfish", "pufferfish" }));
/* 353 */     this.variantNames.put(Items.cooked_fish, Lists.newArrayList((Object[])new String[] { "cooked_cod", "cooked_salmon" }));
/* 354 */     this.variantNames.put(Items.dye, Lists.newArrayList((Object[])new String[] { "dye_black", "dye_red", "dye_green", "dye_brown", "dye_blue", "dye_purple", "dye_cyan", "dye_silver", "dye_gray", "dye_pink", "dye_lime", "dye_yellow", "dye_light_blue", "dye_magenta", "dye_orange", "dye_white" }));
/* 355 */     this.variantNames.put(Items.potionitem, Lists.newArrayList((Object[])new String[] { "bottle_drinkable", "bottle_splash" }));
/* 356 */     this.variantNames.put(Items.skull, Lists.newArrayList((Object[])new String[] { "skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper" }));
/* 357 */     this.variantNames.put(Item.getItemFromBlock(Blocks.oak_fence_gate), Lists.newArrayList((Object[])new String[] { "oak_fence_gate" }));
/* 358 */     this.variantNames.put(Item.getItemFromBlock(Blocks.oak_fence), Lists.newArrayList((Object[])new String[] { "oak_fence" }));
/* 359 */     this.variantNames.put(Items.oak_door, Lists.newArrayList((Object[])new String[] { "oak_door" }));
/*     */   }
/*     */ 
/*     */   
/*     */   private List getVariantNames(Item p_177596_1_) {
/* 364 */     List<String> var2 = (List)this.variantNames.get(p_177596_1_);
/*     */     
/* 366 */     if (var2 == null)
/*     */     {
/* 368 */       var2 = Collections.singletonList(((ResourceLocation)Item.itemRegistry.getNameForObject(p_177596_1_)).toString());
/*     */     }
/*     */     
/* 371 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation getItemLocation(String p_177583_1_) {
/* 376 */     ResourceLocation var2 = new ResourceLocation(p_177583_1_);
/* 377 */     return new ResourceLocation(var2.getResourceDomain(), "item/" + var2.getResourcePath());
/*     */   }
/*     */ 
/*     */   
/*     */   private void bakeBlockModels() {
/* 382 */     Iterator<ModelResourceLocation> var1 = this.variants.keySet().iterator();
/*     */     
/* 384 */     while (var1.hasNext()) {
/*     */       
/* 386 */       ModelResourceLocation var2 = var1.next();
/* 387 */       WeightedBakedModel.Builder var3 = new WeightedBakedModel.Builder();
/* 388 */       int var4 = 0;
/* 389 */       Iterator<ModelBlockDefinition.Variant> var5 = ((ModelBlockDefinition.Variants)this.variants.get(var2)).getVariants().iterator();
/*     */       
/* 391 */       while (var5.hasNext()) {
/*     */         
/* 393 */         ModelBlockDefinition.Variant var6 = var5.next();
/* 394 */         ModelBlock var7 = (ModelBlock)this.models.get(var6.getModelLocation());
/*     */         
/* 396 */         if (var7 != null && var7.isResolved()) {
/*     */           
/* 398 */           var4++;
/* 399 */           var3.add(bakeModel(var7, var6.getRotation(), var6.isUvLocked()), var6.getWeight());
/*     */           
/*     */           continue;
/*     */         } 
/* 403 */         LOGGER.warn("Missing model for: " + var2);
/*     */       } 
/*     */ 
/*     */       
/* 407 */       if (var4 == 0) {
/*     */         
/* 409 */         LOGGER.warn("No weighted models for: " + var2); continue;
/*     */       } 
/* 411 */       if (var4 == 1) {
/*     */         
/* 413 */         this.bakedRegistry.putObject(var2, var3.first());
/*     */         
/*     */         continue;
/*     */       } 
/* 417 */       this.bakedRegistry.putObject(var2, var3.build());
/*     */     } 
/*     */ 
/*     */     
/* 421 */     var1 = this.itemLocations.entrySet().iterator();
/*     */     
/* 423 */     while (var1.hasNext()) {
/*     */       
/* 425 */       Map.Entry var8 = (Map.Entry)var1.next();
/* 426 */       ResourceLocation var9 = (ResourceLocation)var8.getValue();
/* 427 */       ModelResourceLocation var10 = new ModelResourceLocation((String)var8.getKey(), "inventory");
/* 428 */       ModelBlock var11 = (ModelBlock)this.models.get(var9);
/*     */       
/* 430 */       if (var11 != null && var11.isResolved()) {
/*     */         
/* 432 */         if (isCustomRenderer(var11)) {
/*     */           
/* 434 */           this.bakedRegistry.putObject(var10, new BuiltInModel(new ItemCameraTransforms(var11.getThirdPersonTransform(), var11.getFirstPersonTransform(), var11.getHeadTransform(), var11.getInGuiTransform())));
/*     */           
/*     */           continue;
/*     */         } 
/* 438 */         this.bakedRegistry.putObject(var10, bakeModel(var11, ModelRotation.X0_Y0, false));
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 443 */       LOGGER.warn("Missing model for: " + var9);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Set func_177575_g() {
/* 450 */     HashSet var1 = Sets.newHashSet();
/* 451 */     ArrayList<?> var2 = Lists.newArrayList(this.variants.keySet());
/* 452 */     Collections.sort(var2, new Comparator()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002390";
/*     */           
/*     */           public int func_177505_a(ModelResourceLocation p_177505_1_, ModelResourceLocation p_177505_2_) {
/* 457 */             return p_177505_1_.toString().compareTo(p_177505_2_.toString());
/*     */           }
/*     */           
/*     */           public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 461 */             return func_177505_a((ModelResourceLocation)p_compare_1_, (ModelResourceLocation)p_compare_2_);
/*     */           }
/*     */         });
/* 464 */     Iterator<?> var3 = var2.iterator();
/*     */     
/* 466 */     while (var3.hasNext()) {
/*     */       
/* 468 */       ModelResourceLocation var4 = (ModelResourceLocation)var3.next();
/* 469 */       ModelBlockDefinition.Variants var5 = (ModelBlockDefinition.Variants)this.variants.get(var4);
/* 470 */       Iterator<ModelBlockDefinition.Variant> var6 = var5.getVariants().iterator();
/*     */       
/* 472 */       while (var6.hasNext()) {
/*     */         
/* 474 */         ModelBlockDefinition.Variant var7 = var6.next();
/* 475 */         ModelBlock var8 = (ModelBlock)this.models.get(var7.getModelLocation());
/*     */         
/* 477 */         if (var8 == null) {
/*     */           
/* 479 */           LOGGER.warn("Missing model for: " + var4);
/*     */           
/*     */           continue;
/*     */         } 
/* 483 */         var1.addAll(func_177585_a(var8));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 488 */     var1.addAll(field_177602_b);
/* 489 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private IBakedModel bakeModel(ModelBlock p_177578_1_, ModelRotation p_177578_2_, boolean p_177578_3_) {
/* 494 */     TextureAtlasSprite var4 = (TextureAtlasSprite)this.field_177599_g.get(new ResourceLocation(p_177578_1_.resolveTextureName("particle")));
/* 495 */     SimpleBakedModel.Builder var5 = (new SimpleBakedModel.Builder(p_177578_1_)).func_177646_a(var4);
/* 496 */     Iterator<BlockPart> var6 = p_177578_1_.getElements().iterator();
/*     */     
/* 498 */     while (var6.hasNext()) {
/*     */       
/* 500 */       BlockPart var7 = var6.next();
/* 501 */       Iterator<EnumFacing> var8 = var7.field_178240_c.keySet().iterator();
/*     */       
/* 503 */       while (var8.hasNext()) {
/*     */         
/* 505 */         EnumFacing var9 = var8.next();
/* 506 */         BlockPartFace var10 = (BlockPartFace)var7.field_178240_c.get(var9);
/* 507 */         TextureAtlasSprite var11 = (TextureAtlasSprite)this.field_177599_g.get(new ResourceLocation(p_177578_1_.resolveTextureName(var10.field_178242_d)));
/*     */         
/* 509 */         if (var10.field_178244_b == null) {
/*     */           
/* 511 */           var5.func_177648_a(func_177589_a(var7, var10, var11, var9, p_177578_2_, p_177578_3_));
/*     */           
/*     */           continue;
/*     */         } 
/* 515 */         var5.func_177650_a(p_177578_2_.func_177523_a(var10.field_178244_b), func_177589_a(var7, var10, var11, var9, p_177578_2_, p_177578_3_));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 520 */     return var5.func_177645_b();
/*     */   }
/*     */ 
/*     */   
/*     */   private BakedQuad func_177589_a(BlockPart p_177589_1_, BlockPartFace p_177589_2_, TextureAtlasSprite p_177589_3_, EnumFacing p_177589_4_, ModelRotation p_177589_5_, boolean p_177589_6_) {
/* 525 */     return this.field_177607_l.func_178414_a(p_177589_1_.field_178241_a, p_177589_1_.field_178239_b, p_177589_2_, p_177589_3_, p_177589_4_, p_177589_5_, p_177589_1_.field_178237_d, p_177589_6_, p_177589_1_.field_178238_e);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177597_h() {
/* 530 */     func_177574_i();
/* 531 */     Iterator<ModelBlock> var1 = this.models.values().iterator();
/*     */     
/* 533 */     while (var1.hasNext()) {
/*     */       
/* 535 */       ModelBlock var2 = var1.next();
/* 536 */       var2.getParentFromMap(this.models);
/*     */     } 
/*     */     
/* 539 */     ModelBlock.func_178312_b(this.models);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177574_i() {
/* 544 */     ArrayDeque<ResourceLocation> var1 = Queues.newArrayDeque();
/* 545 */     HashSet<ResourceLocation> var2 = Sets.newHashSet();
/* 546 */     Iterator<ResourceLocation> var3 = this.models.keySet().iterator();
/*     */ 
/*     */     
/* 549 */     while (var3.hasNext()) {
/*     */       
/* 551 */       ResourceLocation var4 = var3.next();
/* 552 */       var2.add(var4);
/* 553 */       ResourceLocation var5 = ((ModelBlock)this.models.get(var4)).getParentLocation();
/*     */       
/* 555 */       if (var5 != null)
/*     */       {
/* 557 */         var1.add(var5);
/*     */       }
/*     */     } 
/*     */     
/* 561 */     while (!var1.isEmpty()) {
/*     */       
/* 563 */       ResourceLocation var7 = var1.pop();
/*     */ 
/*     */       
/*     */       try {
/* 567 */         if (this.models.get(var7) != null) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 572 */         ModelBlock var8 = loadModel(var7);
/* 573 */         this.models.put(var7, var8);
/* 574 */         ResourceLocation var5 = var8.getParentLocation();
/*     */         
/* 576 */         if (var5 != null && !var2.contains(var5))
/*     */         {
/* 578 */           var1.add(var5);
/*     */         }
/*     */       }
/* 581 */       catch (Exception var6) {
/*     */         
/* 583 */         LOGGER.warn("In parent chain: " + field_177601_e.join(func_177573_e(var7)) + "; unable to load model: '" + var7 + "'", var6);
/*     */       } 
/*     */       
/* 586 */       var2.add(var7);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private List func_177573_e(ResourceLocation p_177573_1_) {
/* 592 */     ArrayList<ResourceLocation> var2 = Lists.newArrayList((Object[])new ResourceLocation[] { p_177573_1_ });
/* 593 */     ResourceLocation var3 = p_177573_1_;
/*     */     
/* 595 */     while ((var3 = func_177576_f(var3)) != null)
/*     */     {
/* 597 */       var2.add(0, var3);
/*     */     }
/*     */     
/* 600 */     return var2;
/*     */   }
/*     */   private ResourceLocation func_177576_f(ResourceLocation p_177576_1_) {
/*     */     Map.Entry var3;
/*     */     ModelBlock var4;
/* 605 */     Iterator<Map.Entry> var2 = this.models.entrySet().iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 611 */       if (!var2.hasNext())
/*     */       {
/* 613 */         return null;
/*     */       }
/*     */       
/* 616 */       var3 = var2.next();
/* 617 */       var4 = (ModelBlock)var3.getValue();
/*     */     }
/* 619 */     while (var4 == null || !p_177576_1_.equals(var4.getParentLocation()));
/*     */     
/* 621 */     return (ResourceLocation)var3.getKey();
/*     */   }
/*     */ 
/*     */   
/*     */   private Set func_177585_a(ModelBlock p_177585_1_) {
/* 626 */     HashSet<ResourceLocation> var2 = Sets.newHashSet();
/* 627 */     Iterator<BlockPart> var3 = p_177585_1_.getElements().iterator();
/*     */     
/* 629 */     while (var3.hasNext()) {
/*     */       
/* 631 */       BlockPart var4 = var3.next();
/* 632 */       Iterator<BlockPartFace> var5 = var4.field_178240_c.values().iterator();
/*     */       
/* 634 */       while (var5.hasNext()) {
/*     */         
/* 636 */         BlockPartFace var6 = var5.next();
/* 637 */         ResourceLocation var7 = new ResourceLocation(p_177585_1_.resolveTextureName(var6.field_178242_d));
/* 638 */         var2.add(var7);
/*     */       } 
/*     */     } 
/*     */     
/* 642 */     var2.add(new ResourceLocation(p_177585_1_.resolveTextureName("particle")));
/* 643 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177572_j() {
/* 648 */     final Set var1 = func_177575_g();
/* 649 */     var1.addAll(func_177571_k());
/* 650 */     var1.remove(TextureMap.field_174945_f);
/* 651 */     IIconCreator var2 = new IIconCreator()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002389";
/*     */         
/*     */         public void func_177059_a(TextureMap p_177059_1_) {
/* 656 */           Iterator<ResourceLocation> var2 = var1.iterator();
/*     */           
/* 658 */           while (var2.hasNext()) {
/*     */             
/* 660 */             ResourceLocation var3 = var2.next();
/* 661 */             TextureAtlasSprite var4 = p_177059_1_.func_174942_a(var3);
/* 662 */             ModelBakery.this.field_177599_g.put(var3, var4);
/*     */           } 
/*     */         }
/*     */       };
/* 666 */     this.textureMap.func_174943_a(this.resourceManager, var2);
/* 667 */     this.field_177599_g.put(new ResourceLocation("missingno"), this.textureMap.func_174944_f());
/*     */   }
/*     */ 
/*     */   
/*     */   private Set func_177571_k() {
/* 672 */     HashSet<ResourceLocation> var1 = Sets.newHashSet();
/* 673 */     Iterator<ResourceLocation> var2 = this.itemLocations.values().iterator();
/*     */     
/* 675 */     while (var2.hasNext()) {
/*     */       
/* 677 */       ResourceLocation var3 = var2.next();
/* 678 */       ModelBlock var4 = (ModelBlock)this.models.get(var3);
/*     */       
/* 680 */       if (var4 != null) {
/*     */         
/* 682 */         var1.add(new ResourceLocation(var4.resolveTextureName("particle")));
/*     */ 
/*     */ 
/*     */         
/* 686 */         if (func_177581_b(var4)) {
/*     */           
/* 688 */           for (Iterator<String> var5 = ItemModelGenerator.LAYERS.iterator(); var5.hasNext(); var1.add(var11)) {
/*     */             
/* 690 */             String var10 = var5.next();
/* 691 */             ResourceLocation var11 = new ResourceLocation(var4.resolveTextureName(var10));
/*     */             
/* 693 */             if (var4.getRootModel() == MODEL_COMPASS && !TextureMap.field_174945_f.equals(var11)) {
/*     */               
/* 695 */               TextureAtlasSprite.func_176603_b(var11.toString());
/*     */             }
/* 697 */             else if (var4.getRootModel() == MODEL_CLOCK && !TextureMap.field_174945_f.equals(var11)) {
/*     */               
/* 699 */               TextureAtlasSprite.func_176602_a(var11.toString());
/*     */             } 
/*     */           }  continue;
/*     */         } 
/* 703 */         if (!isCustomRenderer(var4)) {
/*     */           
/* 705 */           Iterator<BlockPart> var5 = var4.getElements().iterator();
/*     */           
/* 707 */           while (var5.hasNext()) {
/*     */             
/* 709 */             BlockPart var6 = var5.next();
/* 710 */             Iterator<BlockPartFace> var7 = var6.field_178240_c.values().iterator();
/*     */             
/* 712 */             while (var7.hasNext()) {
/*     */               
/* 714 */               BlockPartFace var8 = var7.next();
/* 715 */               ResourceLocation var9 = new ResourceLocation(var4.resolveTextureName(var8.field_178242_d));
/* 716 */               var1.add(var9);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 723 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_177581_b(ModelBlock p_177581_1_) {
/* 728 */     if (p_177581_1_ == null)
/*     */     {
/* 730 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 734 */     ModelBlock var2 = p_177581_1_.getRootModel();
/* 735 */     return !(var2 != MODEL_GENERATED && var2 != MODEL_COMPASS && var2 != MODEL_CLOCK);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isCustomRenderer(ModelBlock p_177587_1_) {
/* 741 */     if (p_177587_1_ == null)
/*     */     {
/* 743 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 747 */     ModelBlock var2 = p_177587_1_.getRootModel();
/* 748 */     return (var2 == MODEL_ENTITY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void bakeItemModels() {
/* 754 */     Iterator<ResourceLocation> var1 = this.itemLocations.values().iterator();
/*     */     
/* 756 */     while (var1.hasNext()) {
/*     */       
/* 758 */       ResourceLocation var2 = var1.next();
/* 759 */       ModelBlock var3 = (ModelBlock)this.models.get(var2);
/*     */       
/* 761 */       if (func_177581_b(var3)) {
/*     */         
/* 763 */         ModelBlock var4 = func_177582_d(var3);
/*     */         
/* 765 */         if (var4 != null)
/*     */         {
/* 767 */           var4.field_178317_b = var2.toString();
/*     */         }
/*     */         
/* 770 */         this.models.put(var2, var4); continue;
/*     */       } 
/* 772 */       if (isCustomRenderer(var3))
/*     */       {
/* 774 */         this.models.put(var2, var3);
/*     */       }
/*     */     } 
/*     */     
/* 778 */     var1 = this.field_177599_g.values().iterator();
/*     */     
/* 780 */     while (var1.hasNext()) {
/*     */       
/* 782 */       TextureAtlasSprite var5 = (TextureAtlasSprite)var1.next();
/*     */       
/* 784 */       if (!var5.hasAnimationMetadata())
/*     */       {
/* 786 */         var5.clearFramesTextureData();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ModelBlock func_177582_d(ModelBlock p_177582_1_) {
/* 793 */     return this.itemModelGenerator.func_178392_a(this.textureMap, p_177582_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 798 */     BUILT_IN_MODELS.put("missing", "{ \"textures\": {   \"particle\": \"missingno\",   \"missingno\": \"missingno\"}, \"elements\": [ {     \"from\": [ 0, 0, 0 ],     \"to\": [ 16, 16, 16 ],     \"faces\": {         \"down\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"down\", \"texture\": \"#missingno\" },         \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"up\", \"texture\": \"#missingno\" },         \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"north\", \"texture\": \"#missingno\" },         \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"south\", \"texture\": \"#missingno\" },         \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"west\", \"texture\": \"#missingno\" },         \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"east\", \"texture\": \"#missingno\" }    }}]}");
/* 799 */   } private static final Joiner field_177601_e = Joiner.on(" -> ");
/* 800 */   private static final ModelBlock MODEL_GENERATED = ModelBlock.deserialize("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}");
/* 801 */   private static final ModelBlock MODEL_COMPASS = ModelBlock.deserialize("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}");
/* 802 */   private static final ModelBlock MODEL_CLOCK = ModelBlock.deserialize("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}");
/* 803 */   private static final ModelBlock MODEL_ENTITY = ModelBlock.deserialize("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"); static {
/* 804 */     MODEL_GENERATED.field_178317_b = "generation marker";
/* 805 */     MODEL_COMPASS.field_178317_b = "compass generation marker";
/* 806 */     MODEL_CLOCK.field_178317_b = "class generation marker";
/* 807 */     MODEL_ENTITY.field_178317_b = "block entity marker";
/*     */   }
/*     */   
/*     */   private final IResourceManager resourceManager;
/*     */   private final TextureMap textureMap;
/*     */   private final BlockModelShapes blockModelShapes;
/*     */   private static final String __OBFID = "CL_00002391";
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\model\ModelBakery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */