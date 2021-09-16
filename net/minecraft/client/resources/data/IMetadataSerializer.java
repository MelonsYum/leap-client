/*    */ package net.minecraft.client.resources.data;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import net.minecraft.util.ChatStyle;
/*    */ import net.minecraft.util.EnumTypeAdapterFactory;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.util.IRegistry;
/*    */ import net.minecraft.util.RegistrySimple;
/*    */ 
/*    */ public class IMetadataSerializer {
/* 14 */   private final IRegistry metadataSectionSerializerRegistry = (IRegistry)new RegistrySimple();
/* 15 */   private final GsonBuilder gsonBuilder = new GsonBuilder();
/*    */ 
/*    */   
/*    */   private Gson gson;
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00001101";
/*    */ 
/*    */   
/*    */   public IMetadataSerializer() {
/* 25 */     this.gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent.Serializer());
/* 26 */     this.gsonBuilder.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
/* 27 */     this.gsonBuilder.registerTypeAdapterFactory((TypeAdapterFactory)new EnumTypeAdapterFactory());
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerMetadataSectionType(IMetadataSectionSerializer p_110504_1_, Class p_110504_2_) {
/* 32 */     this.metadataSectionSerializerRegistry.putObject(p_110504_1_.getSectionName(), new Registration(p_110504_1_, p_110504_2_, null));
/* 33 */     this.gsonBuilder.registerTypeAdapter(p_110504_2_, p_110504_1_);
/* 34 */     this.gson = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMetadataSection parseMetadataSection(String p_110503_1_, JsonObject p_110503_2_) {
/* 39 */     if (p_110503_1_ == null)
/*    */     {
/* 41 */       throw new IllegalArgumentException("Metadata section name cannot be null");
/*    */     }
/* 43 */     if (!p_110503_2_.has(p_110503_1_))
/*    */     {
/* 45 */       return null;
/*    */     }
/* 47 */     if (!p_110503_2_.get(p_110503_1_).isJsonObject())
/*    */     {
/* 49 */       throw new IllegalArgumentException("Invalid metadata for '" + p_110503_1_ + "' - expected object, found " + p_110503_2_.get(p_110503_1_));
/*    */     }
/*    */ 
/*    */     
/* 53 */     Registration var3 = (Registration)this.metadataSectionSerializerRegistry.getObject(p_110503_1_);
/*    */     
/* 55 */     if (var3 == null)
/*    */     {
/* 57 */       throw new IllegalArgumentException("Don't know how to handle metadata section '" + p_110503_1_ + "'");
/*    */     }
/*    */ 
/*    */     
/* 61 */     return (IMetadataSection)getGson().fromJson((JsonElement)p_110503_2_.getAsJsonObject(p_110503_1_), var3.field_110500_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Gson getGson() {
/* 71 */     if (this.gson == null)
/*    */     {
/* 73 */       this.gson = this.gsonBuilder.create();
/*    */     }
/*    */     
/* 76 */     return this.gson;
/*    */   }
/*    */ 
/*    */   
/*    */   class Registration
/*    */   {
/*    */     final IMetadataSectionSerializer field_110502_a;
/*    */     final Class field_110500_b;
/*    */     private static final String __OBFID = "CL_00001103";
/*    */     
/*    */     private Registration(IMetadataSectionSerializer p_i1305_2_, Class p_i1305_3_) {
/* 87 */       this.field_110502_a = p_i1305_2_;
/* 88 */       this.field_110500_b = p_i1305_3_;
/*    */     }
/*    */ 
/*    */     
/*    */     Registration(IMetadataSectionSerializer p_i1306_2_, Class p_i1306_3_, Object p_i1306_4_) {
/* 93 */       this(p_i1306_2_, p_i1306_3_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\IMetadataSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */