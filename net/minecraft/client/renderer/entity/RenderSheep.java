/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.passive.EntitySheep;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderSheep extends RenderLiving {
/* 11 */   private static final ResourceLocation shearedSheepTextures = new ResourceLocation("textures/entity/sheep/sheep.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001021";
/*    */   
/*    */   public RenderSheep(RenderManager p_i46145_1_, ModelBase p_i46145_2_, float p_i46145_3_) {
/* 16 */     super(p_i46145_1_, p_i46145_2_, p_i46145_3_);
/* 17 */     addLayer((LayerRenderer)new LayerSheepWool(this));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntitySheep p_110775_1_) {
/* 25 */     return shearedSheepTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 33 */     return getEntityTexture((EntitySheep)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderSheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */