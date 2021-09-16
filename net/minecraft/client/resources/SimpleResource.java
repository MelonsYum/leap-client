/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.resources.data.IMetadataSection;
/*     */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class SimpleResource
/*     */   implements IResource {
/*  17 */   private final Map mapMetadataSections = Maps.newHashMap();
/*     */   
/*     */   private final String field_177242_b;
/*     */   private final ResourceLocation srResourceLocation;
/*     */   private final InputStream resourceInputStream;
/*     */   private final InputStream mcmetaInputStream;
/*     */   private final IMetadataSerializer srMetadataSerializer;
/*     */   private boolean mcmetaJsonChecked;
/*     */   private JsonObject mcmetaJson;
/*     */   private static final String __OBFID = "CL_00001093";
/*     */   
/*     */   public SimpleResource(String p_i46090_1_, ResourceLocation p_i46090_2_, InputStream p_i46090_3_, InputStream p_i46090_4_, IMetadataSerializer p_i46090_5_) {
/*  29 */     this.field_177242_b = p_i46090_1_;
/*  30 */     this.srResourceLocation = p_i46090_2_;
/*  31 */     this.resourceInputStream = p_i46090_3_;
/*  32 */     this.mcmetaInputStream = p_i46090_4_;
/*  33 */     this.srMetadataSerializer = p_i46090_5_;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation func_177241_a() {
/*  38 */     return this.srResourceLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() {
/*  43 */     return this.resourceInputStream;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMetadata() {
/*  48 */     return (this.mcmetaInputStream != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public IMetadataSection getMetadata(String p_110526_1_) {
/*  53 */     if (!hasMetadata())
/*     */     {
/*  55 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  59 */     if (this.mcmetaJson == null && !this.mcmetaJsonChecked) {
/*     */       
/*  61 */       this.mcmetaJsonChecked = true;
/*  62 */       BufferedReader var2 = null;
/*     */ 
/*     */       
/*     */       try {
/*  66 */         var2 = new BufferedReader(new InputStreamReader(this.mcmetaInputStream));
/*  67 */         this.mcmetaJson = (new JsonParser()).parse(var2).getAsJsonObject();
/*     */       }
/*     */       finally {
/*     */         
/*  71 */         IOUtils.closeQuietly(var2);
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     IMetadataSection var6 = (IMetadataSection)this.mapMetadataSections.get(p_110526_1_);
/*     */     
/*  77 */     if (var6 == null)
/*     */     {
/*  79 */       var6 = this.srMetadataSerializer.parseMetadataSection(p_110526_1_, this.mcmetaJson);
/*     */     }
/*     */     
/*  82 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_177240_d() {
/*  88 */     return this.field_177242_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  93 */     if (this == p_equals_1_)
/*     */     {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(p_equals_1_ instanceof SimpleResource))
/*     */     {
/*  99 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 103 */     SimpleResource var2 = (SimpleResource)p_equals_1_;
/*     */     
/* 105 */     if (this.srResourceLocation != null) {
/*     */       
/* 107 */       if (!this.srResourceLocation.equals(var2.srResourceLocation))
/*     */       {
/* 109 */         return false;
/*     */       }
/*     */     }
/* 112 */     else if (var2.srResourceLocation != null) {
/*     */       
/* 114 */       return false;
/*     */     } 
/*     */     
/* 117 */     if (this.field_177242_b != null) {
/*     */       
/* 119 */       if (!this.field_177242_b.equals(var2.field_177242_b))
/*     */       {
/* 121 */         return false;
/*     */       }
/*     */     }
/* 124 */     else if (var2.field_177242_b != null) {
/*     */       
/* 126 */       return false;
/*     */     } 
/*     */     
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 135 */     int var1 = (this.field_177242_b != null) ? this.field_177242_b.hashCode() : 0;
/* 136 */     var1 = 31 * var1 + ((this.srResourceLocation != null) ? this.srResourceLocation.hashCode() : 0);
/* 137 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\SimpleResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */