/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelVillager;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderVillager extends RenderLiving {
/*  14 */   private static final ResourceLocation villagerTextures = new ResourceLocation("textures/entity/villager/villager.png");
/*  15 */   private static final ResourceLocation farmerVillagerTextures = new ResourceLocation("textures/entity/villager/farmer.png");
/*  16 */   private static final ResourceLocation librarianVillagerTextures = new ResourceLocation("textures/entity/villager/librarian.png");
/*  17 */   private static final ResourceLocation priestVillagerTextures = new ResourceLocation("textures/entity/villager/priest.png");
/*  18 */   private static final ResourceLocation smithVillagerTextures = new ResourceLocation("textures/entity/villager/smith.png");
/*  19 */   private static final ResourceLocation butcherVillagerTextures = new ResourceLocation("textures/entity/villager/butcher.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00001032";
/*     */   
/*     */   public RenderVillager(RenderManager p_i46132_1_) {
/*  24 */     super(p_i46132_1_, (ModelBase)new ModelVillager(0.0F), 0.5F);
/*  25 */     addLayer((LayerRenderer)new LayerCustomHead((func_177134_g()).villagerHead));
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelVillager func_177134_g() {
/*  30 */     return (ModelVillager)super.getMainModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityVillager p_110775_1_) {
/*  38 */     switch (p_110775_1_.getProfession()) {
/*     */       
/*     */       case 0:
/*  41 */         return farmerVillagerTextures;
/*     */       
/*     */       case 1:
/*  44 */         return librarianVillagerTextures;
/*     */       
/*     */       case 2:
/*  47 */         return priestVillagerTextures;
/*     */       
/*     */       case 3:
/*  50 */         return smithVillagerTextures;
/*     */       
/*     */       case 4:
/*  53 */         return butcherVillagerTextures;
/*     */     } 
/*     */     
/*  56 */     return villagerTextures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityVillager p_77041_1_, float p_77041_2_) {
/*  66 */     float var3 = 0.9375F;
/*     */     
/*  68 */     if (p_77041_1_.getGrowingAge() < 0) {
/*     */       
/*  70 */       var3 = (float)(var3 * 0.5D);
/*  71 */       this.shadowSize = 0.25F;
/*     */     }
/*     */     else {
/*     */       
/*  75 */       this.shadowSize = 0.5F;
/*     */     } 
/*     */     
/*  78 */     GlStateManager.scale(var3, var3, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/*  87 */     preRenderCallback((EntityVillager)p_77041_1_, p_77041_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBase getMainModel() {
/*  92 */     return (ModelBase)func_177134_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 100 */     return getEntityTexture((EntityVillager)p_110775_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */