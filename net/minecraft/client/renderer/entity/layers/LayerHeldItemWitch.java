/*     */ package net.minecraft.client.renderer.entity.layers;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelWitch;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.entity.RenderWitch;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ public class LayerHeldItemWitch
/*     */   implements LayerRenderer
/*     */ {
/*     */   private final RenderWitch field_177144_a;
/*     */   private static final String __OBFID = "CL_00002407";
/*     */   
/*     */   public LayerHeldItemWitch(RenderWitch p_i46106_1_) {
/*  23 */     this.field_177144_a = p_i46106_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177143_a(EntityWitch p_177143_1_, float p_177143_2_, float p_177143_3_, float p_177143_4_, float p_177143_5_, float p_177143_6_, float p_177143_7_, float p_177143_8_) {
/*  28 */     ItemStack var9 = p_177143_1_.getHeldItem();
/*     */     
/*  30 */     if (var9 != null) {
/*     */       
/*  32 */       GlStateManager.color(1.0F, 1.0F, 1.0F);
/*  33 */       GlStateManager.pushMatrix();
/*     */       
/*  35 */       if ((this.field_177144_a.getMainModel()).isChild) {
/*     */         
/*  37 */         GlStateManager.translate(0.0F, 0.625F, 0.0F);
/*  38 */         GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
/*  39 */         float var10 = 0.5F;
/*  40 */         GlStateManager.scale(var10, var10, var10);
/*     */       } 
/*     */       
/*  43 */       ((ModelWitch)this.field_177144_a.getMainModel()).villagerNose.postRender(0.0625F);
/*  44 */       GlStateManager.translate(-0.0625F, 0.53125F, 0.21875F);
/*  45 */       Item var13 = var9.getItem();
/*  46 */       Minecraft var11 = Minecraft.getMinecraft();
/*     */ 
/*     */       
/*  49 */       if (var13 instanceof net.minecraft.item.ItemBlock && var11.getBlockRendererDispatcher().func_175021_a(Block.getBlockFromItem(var13), var9.getMetadata())) {
/*     */         
/*  51 */         GlStateManager.translate(0.0F, 0.1875F, -0.3125F);
/*  52 */         GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
/*  53 */         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/*  54 */         float var12 = 0.375F;
/*  55 */         GlStateManager.scale(var12, -var12, var12);
/*     */       }
/*  57 */       else if (var13 == Items.bow) {
/*     */         
/*  59 */         GlStateManager.translate(0.0F, 0.125F, 0.3125F);
/*  60 */         GlStateManager.rotate(-20.0F, 0.0F, 1.0F, 0.0F);
/*  61 */         float var12 = 0.625F;
/*  62 */         GlStateManager.scale(var12, -var12, var12);
/*  63 */         GlStateManager.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
/*  64 */         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*  66 */       else if (var13.isFull3D()) {
/*     */         
/*  68 */         if (var13.shouldRotateAroundWhenRendering()) {
/*     */           
/*  70 */           GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
/*  71 */           GlStateManager.translate(0.0F, -0.125F, 0.0F);
/*     */         } 
/*     */         
/*  74 */         this.field_177144_a.func_82422_c();
/*  75 */         float var12 = 0.625F;
/*  76 */         GlStateManager.scale(var12, -var12, var12);
/*  77 */         GlStateManager.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
/*  78 */         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else {
/*     */         
/*  82 */         GlStateManager.translate(0.25F, 0.1875F, -0.1875F);
/*  83 */         float var12 = 0.375F;
/*  84 */         GlStateManager.scale(var12, var12, var12);
/*  85 */         GlStateManager.rotate(60.0F, 0.0F, 0.0F, 1.0F);
/*  86 */         GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
/*  87 */         GlStateManager.rotate(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */       
/*  90 */       GlStateManager.rotate(-15.0F, 1.0F, 0.0F, 0.0F);
/*  91 */       GlStateManager.rotate(40.0F, 0.0F, 0.0F, 1.0F);
/*  92 */       var11.getItemRenderer().renderItem((EntityLivingBase)p_177143_1_, var9, ItemCameraTransforms.TransformType.THIRD_PERSON);
/*  93 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldCombineTextures() {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 104 */     func_177143_a((EntityWitch)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerHeldItemWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */