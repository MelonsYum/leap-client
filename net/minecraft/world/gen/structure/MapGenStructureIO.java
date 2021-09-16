/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class MapGenStructureIO
/*     */ {
/*  12 */   private static final Logger logger = LogManager.getLogger();
/*  13 */   private static Map field_143040_a = Maps.newHashMap();
/*  14 */   private static Map field_143038_b = Maps.newHashMap();
/*  15 */   private static Map field_143039_c = Maps.newHashMap();
/*  16 */   private static Map field_143037_d = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00000509";
/*     */   
/*     */   private static void registerStructure(Class<?> p_143034_0_, String p_143034_1_) {
/*  21 */     field_143040_a.put(p_143034_1_, p_143034_0_);
/*  22 */     field_143038_b.put(p_143034_0_, p_143034_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   static void registerStructureComponent(Class<?> p_143031_0_, String p_143031_1_) {
/*  27 */     field_143039_c.put(p_143031_1_, p_143031_0_);
/*  28 */     field_143037_d.put(p_143031_0_, p_143031_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_143033_a(StructureStart p_143033_0_) {
/*  33 */     return (String)field_143038_b.get(p_143033_0_.getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_143036_a(StructureComponent p_143036_0_) {
/*  38 */     return (String)field_143037_d.get(p_143036_0_.getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureStart func_143035_a(NBTTagCompound p_143035_0_, World worldIn) {
/*  43 */     StructureStart var2 = null;
/*     */ 
/*     */     
/*     */     try {
/*  47 */       Class<StructureStart> var3 = (Class)field_143040_a.get(p_143035_0_.getString("id"));
/*     */       
/*  49 */       if (var3 != null)
/*     */       {
/*  51 */         var2 = var3.newInstance();
/*     */       }
/*     */     }
/*  54 */     catch (Exception var4) {
/*     */       
/*  56 */       logger.warn("Failed Start with id " + p_143035_0_.getString("id"));
/*  57 */       var4.printStackTrace();
/*     */     } 
/*     */     
/*  60 */     if (var2 != null) {
/*     */       
/*  62 */       var2.func_143020_a(worldIn, p_143035_0_);
/*     */     }
/*     */     else {
/*     */       
/*  66 */       logger.warn("Skipping Structure with id " + p_143035_0_.getString("id"));
/*     */     } 
/*     */     
/*  69 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureComponent func_143032_b(NBTTagCompound p_143032_0_, World worldIn) {
/*  74 */     StructureComponent var2 = null;
/*     */ 
/*     */     
/*     */     try {
/*  78 */       Class<StructureComponent> var3 = (Class)field_143039_c.get(p_143032_0_.getString("id"));
/*     */       
/*  80 */       if (var3 != null)
/*     */       {
/*  82 */         var2 = var3.newInstance();
/*     */       }
/*     */     }
/*  85 */     catch (Exception var4) {
/*     */       
/*  87 */       logger.warn("Failed Piece with id " + p_143032_0_.getString("id"));
/*  88 */       var4.printStackTrace();
/*     */     } 
/*     */     
/*  91 */     if (var2 != null) {
/*     */       
/*  93 */       var2.func_143009_a(worldIn, p_143032_0_);
/*     */     }
/*     */     else {
/*     */       
/*  97 */       logger.warn("Skipping Piece with id " + p_143032_0_.getString("id"));
/*     */     } 
/*     */     
/* 100 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 105 */     registerStructure(StructureMineshaftStart.class, "Mineshaft");
/* 106 */     registerStructure(MapGenVillage.Start.class, "Village");
/* 107 */     registerStructure(MapGenNetherBridge.Start.class, "Fortress");
/* 108 */     registerStructure(MapGenStronghold.Start.class, "Stronghold");
/* 109 */     registerStructure(MapGenScatteredFeature.Start.class, "Temple");
/* 110 */     registerStructure(StructureOceanMonument.StartMonument.class, "Monument");
/* 111 */     StructureMineshaftPieces.registerStructurePieces();
/* 112 */     StructureVillagePieces.registerVillagePieces();
/* 113 */     StructureNetherBridgePieces.registerNetherFortressPieces();
/* 114 */     StructureStrongholdPieces.registerStrongholdPieces();
/* 115 */     ComponentScatteredFeaturePieces.registerScatteredFeaturePieces();
/* 116 */     StructureOceanMonumentPieces.func_175970_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenStructureIO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */