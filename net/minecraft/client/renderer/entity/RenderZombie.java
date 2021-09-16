/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelZombie;
/*     */ import net.minecraft.client.model.ModelZombieVillager;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderZombie extends RenderBiped {
/*  21 */   private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
/*  22 */   private static final ResourceLocation zombieVillagerTextures = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
/*     */   
/*     */   private final ModelBiped field_82434_o;
/*     */   private final ModelZombieVillager zombieVillagerModel;
/*     */   private final List field_177121_n;
/*     */   private final List field_177122_o;
/*     */   private static final String __OBFID = "CL_00001037";
/*     */   
/*     */   public RenderZombie(RenderManager p_i46127_1_) {
/*  31 */     super(p_i46127_1_, (ModelBiped)new ModelZombie(), 0.5F, 1.0F);
/*  32 */     LayerRenderer var2 = this.field_177097_h.get(0);
/*  33 */     this.field_82434_o = this.modelBipedMain;
/*  34 */     this.zombieVillagerModel = new ModelZombieVillager();
/*  35 */     addLayer((LayerRenderer)new LayerHeldItem(this));
/*  36 */     LayerBipedArmor var3 = new LayerBipedArmor(this)
/*     */       {
/*     */         private static final String __OBFID = "CL_00002429";
/*     */         
/*     */         protected void func_177177_a() {
/*  41 */           this.field_177189_c = (ModelBase)new ModelZombie(0.5F, true);
/*  42 */           this.field_177186_d = (ModelBase)new ModelZombie(1.0F, true);
/*     */         }
/*     */       };
/*  45 */     addLayer((LayerRenderer)var3);
/*  46 */     this.field_177122_o = Lists.newArrayList(this.field_177097_h);
/*     */     
/*  48 */     if (var2 instanceof LayerCustomHead) {
/*     */       
/*  50 */       func_177089_b(var2);
/*  51 */       addLayer((LayerRenderer)new LayerCustomHead(this.zombieVillagerModel.bipedHead));
/*     */     } 
/*     */     
/*  54 */     func_177089_b((LayerRenderer)var3);
/*  55 */     addLayer((LayerRenderer)new LayerVillagerArmor(this));
/*  56 */     this.field_177121_n = Lists.newArrayList(this.field_177097_h);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180579_a(EntityZombie p_180579_1_, double p_180579_2_, double p_180579_4_, double p_180579_6_, float p_180579_8_, float p_180579_9_) {
/*  61 */     func_82427_a(p_180579_1_);
/*  62 */     super.doRender((EntityLiving)p_180579_1_, p_180579_2_, p_180579_4_, p_180579_6_, p_180579_8_, p_180579_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_180578_a(EntityZombie p_180578_1_) {
/*  67 */     return p_180578_1_.isVillager() ? zombieVillagerTextures : zombieTextures;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82427_a(EntityZombie p_82427_1_) {
/*  72 */     if (p_82427_1_.isVillager()) {
/*     */       
/*  74 */       this.mainModel = (ModelBase)this.zombieVillagerModel;
/*  75 */       this.field_177097_h = this.field_177121_n;
/*     */     }
/*     */     else {
/*     */       
/*  79 */       this.mainModel = (ModelBase)this.field_82434_o;
/*  80 */       this.field_177097_h = this.field_177122_o;
/*     */     } 
/*     */     
/*  83 */     this.modelBipedMain = (ModelBiped)this.mainModel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/*  88 */     if (p_77043_1_.isConverting())
/*     */     {
/*  90 */       p_77043_3_ += (float)(Math.cos(p_77043_1_.ticksExisted * 3.25D) * Math.PI * 0.25D);
/*     */     }
/*     */     
/*  93 */     super.rotateCorpse((EntityLivingBase)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
/* 101 */     return func_180578_a((EntityZombie)p_110775_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 112 */     func_180579_a((EntityZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 117 */     rotateCorpse((EntityZombie)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 128 */     func_180579_a((EntityZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 136 */     return func_180578_a((EntityZombie)p_110775_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 147 */     func_180579_a((EntityZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */