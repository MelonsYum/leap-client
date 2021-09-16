/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiScreenResourcePacks;
/*    */ 
/*    */ public class ResourcePackListEntryFound
/*    */   extends ResourcePackListEntry
/*    */ {
/*    */   private final ResourcePackRepository.Entry field_148319_c;
/*    */   private static final String __OBFID = "CL_00000823";
/*    */   
/*    */   public ResourcePackListEntryFound(GuiScreenResourcePacks p_i45053_1_, ResourcePackRepository.Entry p_i45053_2_) {
/* 12 */     super(p_i45053_1_);
/* 13 */     this.field_148319_c = p_i45053_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_148313_c() {
/* 18 */     this.field_148319_c.bindTexturePackIcon(this.field_148317_a.getTextureManager());
/*    */   }
/*    */ 
/*    */   
/*    */   protected String func_148311_a() {
/* 23 */     return this.field_148319_c.getTexturePackDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   protected String func_148312_b() {
/* 28 */     return this.field_148319_c.getResourcePackName();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourcePackRepository.Entry func_148318_i() {
/* 33 */     return this.field_148319_c;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\ResourcePackListEntryFound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */