/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelArmorStand;
/*    */ import net.minecraft.client.model.ModelArmorStandArmor;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityArmorStand;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class ArmorStandRenderer extends RendererLivingEntity {
/* 17 */   public static final ResourceLocation field_177103_a = new ResourceLocation("textures/entity/armorstand/wood.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00002447";
/*    */   
/*    */   public ArmorStandRenderer(RenderManager p_i46195_1_) {
/* 22 */     super(p_i46195_1_, (ModelBase)new ModelArmorStand(), 0.0F);
/* 23 */     LayerBipedArmor var2 = new LayerBipedArmor(this)
/*    */       {
/*    */         private static final String __OBFID = "CL_00002446";
/*    */         
/*    */         protected void func_177177_a() {
/* 28 */           this.field_177189_c = (ModelBase)new ModelArmorStandArmor(0.5F);
/* 29 */           this.field_177186_d = (ModelBase)new ModelArmorStandArmor(1.0F);
/*    */         }
/*    */       };
/* 32 */     addLayer((LayerRenderer)var2);
/* 33 */     addLayer((LayerRenderer)new LayerHeldItem(this));
/* 34 */     addLayer((LayerRenderer)new LayerCustomHead((func_177100_a()).bipedHead));
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_177102_a(EntityArmorStand p_177102_1_) {
/* 39 */     return field_177103_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelArmorStand func_177100_a() {
/* 44 */     return (ModelArmorStand)super.getMainModel();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_177101_a(EntityArmorStand p_177101_1_, float p_177101_2_, float p_177101_3_, float p_177101_4_) {
/* 49 */     GlStateManager.rotate(180.0F - p_177101_3_, 0.0F, 1.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_177099_b(EntityArmorStand p_177099_1_) {
/* 54 */     return p_177099_1_.getAlwaysRenderNameTag();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canRenderName(EntityLivingBase targetEntity) {
/* 62 */     return func_177099_b((EntityArmorStand)targetEntity);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 67 */     func_177101_a((EntityArmorStand)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelBase getMainModel() {
/* 72 */     return (ModelBase)func_177100_a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 80 */     return func_177102_a((EntityArmorStand)p_110775_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_177070_b(Entity p_177070_1_) {
/* 85 */     return func_177099_b((EntityArmorStand)p_177070_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\ArmorStandRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */