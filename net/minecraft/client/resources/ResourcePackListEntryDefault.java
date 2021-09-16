/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.gui.GuiScreenResourcePacks;
/*    */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.client.resources.data.PackMetadataSection;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class ResourcePackListEntryDefault
/*    */   extends ResourcePackListEntry {
/* 16 */   private static final Logger logger = LogManager.getLogger();
/*    */   
/*    */   private final IResourcePack field_148320_d;
/*    */   private final ResourceLocation field_148321_e;
/*    */   private static final String __OBFID = "CL_00000822";
/*    */   
/*    */   public ResourcePackListEntryDefault(GuiScreenResourcePacks p_i45052_1_) {
/* 23 */     super(p_i45052_1_); DynamicTexture var2;
/* 24 */     this.field_148320_d = (this.field_148317_a.getResourcePackRepository()).rprDefaultResourcePack;
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 29 */       var2 = new DynamicTexture(this.field_148320_d.getPackImage());
/*    */     }
/* 31 */     catch (IOException var4) {
/*    */       
/* 33 */       var2 = TextureUtil.missingTexture;
/*    */     } 
/*    */     
/* 36 */     this.field_148321_e = this.field_148317_a.getTextureManager().getDynamicTextureLocation("texturepackicon", var2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String func_148311_a() {
/*    */     try {
/* 43 */       PackMetadataSection var1 = (PackMetadataSection)this.field_148320_d.getPackMetadata((this.field_148317_a.getResourcePackRepository()).rprMetadataSerializer, "pack");
/*    */       
/* 45 */       if (var1 != null)
/*    */       {
/* 47 */         return var1.func_152805_a().getFormattedText();
/*    */       }
/*    */     }
/* 50 */     catch (JsonParseException var2) {
/*    */       
/* 52 */       logger.error("Couldn't load metadata info", (Throwable)var2);
/*    */     }
/* 54 */     catch (IOException var3) {
/*    */       
/* 56 */       logger.error("Couldn't load metadata info", var3);
/*    */     } 
/*    */     
/* 59 */     return EnumChatFormatting.RED + "Missing " + "pack.mcmeta" + " :(";
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_148309_e() {
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_148308_f() {
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_148314_g() {
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_148307_h() {
/* 79 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String func_148312_b() {
/* 84 */     return "Default";
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_148313_c() {
/* 89 */     this.field_148317_a.getTextureManager().bindTexture(this.field_148321_e);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_148310_d() {
/* 94 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\ResourcePackListEntryDefault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */