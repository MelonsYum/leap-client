/*     */ package net.minecraft.client.renderer.entity.layers;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LayerCustomHead
/*     */   implements LayerRenderer
/*     */ {
/*     */   private final ModelRenderer field_177209_a;
/*     */   private static final String __OBFID = "CL_00002422";
/*     */   
/*     */   public LayerCustomHead(ModelRenderer p_i46120_1_) {
/*  29 */     this.field_177209_a = p_i46120_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/*  34 */     ItemStack var9 = p_177141_1_.getCurrentArmor(3);
/*     */     
/*  36 */     if (var9 != null && var9.getItem() != null) {
/*     */       
/*  38 */       Item var10 = var9.getItem();
/*  39 */       Minecraft var11 = Minecraft.getMinecraft();
/*  40 */       GlStateManager.pushMatrix();
/*     */       
/*  42 */       if (p_177141_1_.isSneaking())
/*     */       {
/*  44 */         GlStateManager.translate(0.0F, 0.2F, 0.0F);
/*     */       }
/*     */       
/*  47 */       boolean var12 = !(!(p_177141_1_ instanceof net.minecraft.entity.passive.EntityVillager) && (!(p_177141_1_ instanceof EntityZombie) || !((EntityZombie)p_177141_1_).isVillager()));
/*     */ 
/*     */       
/*  50 */       if (!var12 && p_177141_1_.isChild()) {
/*     */         
/*  52 */         float var13 = 2.0F;
/*  53 */         float var14 = 1.4F;
/*  54 */         GlStateManager.scale(var14 / var13, var14 / var13, var14 / var13);
/*  55 */         GlStateManager.translate(0.0F, 16.0F * p_177141_8_, 0.0F);
/*     */       } 
/*     */       
/*  58 */       this.field_177209_a.postRender(0.0625F);
/*  59 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  61 */       if (var10 instanceof net.minecraft.item.ItemBlock) {
/*     */         
/*  63 */         float var13 = 0.625F;
/*  64 */         GlStateManager.translate(0.0F, -0.25F, 0.0F);
/*  65 */         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/*  66 */         GlStateManager.scale(var13, -var13, -var13);
/*     */         
/*  68 */         if (var12)
/*     */         {
/*  70 */           GlStateManager.translate(0.0F, 0.1875F, 0.0F);
/*     */         }
/*     */         
/*  73 */         var11.getItemRenderer().renderItem(p_177141_1_, var9, ItemCameraTransforms.TransformType.HEAD);
/*     */       }
/*  75 */       else if (var10 == Items.skull) {
/*     */         
/*  77 */         float var13 = 1.1875F;
/*  78 */         GlStateManager.scale(var13, -var13, -var13);
/*     */         
/*  80 */         if (var12)
/*     */         {
/*  82 */           GlStateManager.translate(0.0F, 0.0625F, 0.0F);
/*     */         }
/*     */         
/*  85 */         GameProfile var16 = null;
/*     */         
/*  87 */         if (var9.hasTagCompound()) {
/*     */           
/*  89 */           NBTTagCompound var15 = var9.getTagCompound();
/*     */           
/*  91 */           if (var15.hasKey("SkullOwner", 10)) {
/*     */             
/*  93 */             var16 = NBTUtil.readGameProfileFromNBT(var15.getCompoundTag("SkullOwner"));
/*     */           }
/*  95 */           else if (var15.hasKey("SkullOwner", 8)) {
/*     */             
/*  97 */             var16 = TileEntitySkull.updateGameprofile(new GameProfile(null, var15.getString("SkullOwner")));
/*  98 */             var15.setTag("SkullOwner", (NBTBase)NBTUtil.writeGameProfile(new NBTTagCompound(), var16));
/*     */           } 
/*     */         } 
/*     */         
/* 102 */         TileEntitySkullRenderer.instance.renderSkull(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, var9.getMetadata(), var16, -1);
/*     */       } 
/*     */       
/* 105 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldCombineTextures() {
/* 111 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerCustomHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */