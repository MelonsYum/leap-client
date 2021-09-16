/*     */ package optifine;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ public class ChunkUtils
/*     */ {
/*  10 */   private static Field fieldHasEntities = null;
/*     */   
/*     */   private static boolean fieldHasEntitiesMissing = false;
/*     */   
/*     */   public static boolean hasEntities(Chunk chunk) {
/*  15 */     if (fieldHasEntities == null) {
/*     */       
/*  17 */       if (fieldHasEntitiesMissing)
/*     */       {
/*  19 */         return true;
/*     */       }
/*     */       
/*  22 */       fieldHasEntities = findFieldHasEntities(chunk);
/*     */       
/*  24 */       if (fieldHasEntities == null) {
/*     */         
/*  26 */         fieldHasEntitiesMissing = true;
/*  27 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*  33 */       return fieldHasEntities.getBoolean(chunk);
/*     */     }
/*  35 */     catch (Exception var2) {
/*     */       
/*  37 */       Config.warn("Error calling Chunk.hasEntities");
/*  38 */       Config.warn(String.valueOf(var2.getClass().getName()) + " " + var2.getMessage());
/*  39 */       fieldHasEntitiesMissing = true;
/*  40 */       return true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Field findFieldHasEntities(Chunk chunk) {
/*     */     try {
/*  48 */       ArrayList<Field> e = new ArrayList();
/*  49 */       ArrayList<Object> listBoolValuesPre = new ArrayList();
/*  50 */       Field[] fields = Chunk.class.getDeclaredFields();
/*     */       
/*  52 */       for (int listBoolValuesFalse = 0; listBoolValuesFalse < fields.length; listBoolValuesFalse++) {
/*     */         
/*  54 */         Field listBoolValuesTrue = fields[listBoolValuesFalse];
/*     */         
/*  56 */         if (listBoolValuesTrue.getType() == boolean.class) {
/*     */           
/*  58 */           listBoolValuesTrue.setAccessible(true);
/*  59 */           e.add(listBoolValuesTrue);
/*  60 */           listBoolValuesPre.add(listBoolValuesTrue.get(chunk));
/*     */         } 
/*     */       } 
/*     */       
/*  64 */       chunk.setHasEntities(false);
/*  65 */       ArrayList<Object> var13 = new ArrayList();
/*  66 */       Iterator<Field> var14 = e.iterator();
/*     */       
/*  68 */       while (var14.hasNext()) {
/*     */         
/*  70 */         Field listMatchingFields = var14.next();
/*  71 */         var13.add(listMatchingFields.get(chunk));
/*     */       } 
/*     */       
/*  74 */       chunk.setHasEntities(true);
/*  75 */       ArrayList<Object> var15 = new ArrayList();
/*  76 */       Iterator<Field> var16 = e.iterator();
/*     */ 
/*     */       
/*  79 */       while (var16.hasNext()) {
/*     */         
/*  81 */         Field field = var16.next();
/*  82 */         var15.add(field.get(chunk));
/*     */       } 
/*     */       
/*  85 */       ArrayList<Field> var17 = new ArrayList();
/*     */       
/*  87 */       for (int var18 = 0; var18 < e.size(); var18++) {
/*     */         
/*  89 */         Field field1 = e.get(var18);
/*  90 */         Boolean valFalse = (Boolean)var13.get(var18);
/*  91 */         Boolean valTrue = (Boolean)var15.get(var18);
/*     */         
/*  93 */         if (!valFalse.booleanValue() && valTrue.booleanValue()) {
/*     */           
/*  95 */           var17.add(field1);
/*  96 */           Boolean valPre = (Boolean)listBoolValuesPre.get(var18);
/*  97 */           field1.set(chunk, valPre);
/*     */         } 
/*     */       } 
/*     */       
/* 101 */       if (var17.size() == 1)
/*     */       {
/* 103 */         Field field = var17.get(0);
/* 104 */         return field;
/*     */       }
/*     */     
/* 107 */     } catch (Exception var12) {
/*     */       
/* 109 */       Config.warn(String.valueOf(var12.getClass().getName()) + " " + var12.getMessage());
/*     */     } 
/*     */     
/* 112 */     Config.warn("Error finding Chunk.hasEntities");
/* 113 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ChunkUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */