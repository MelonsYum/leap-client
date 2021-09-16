/*     */ package net.minecraft.client.entity;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.client.renderer.IImageBuffer;
/*     */ import net.minecraft.client.renderer.ImageBufferDownload;
/*     */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import optifine.CapeUtils;
/*     */ import optifine.Config;
/*     */ import optifine.PlayerConfigurations;
/*     */ import optifine.Reflector;
/*     */ 
/*     */ public abstract class AbstractClientPlayer
/*     */   extends EntityPlayer {
/*     */   private NetworkPlayerInfo field_175157_a;
/*  28 */   private ResourceLocation locationOfCape = null;
/*  29 */   private String nameClear = null;
/*     */   
/*     */   private static final String __OBFID = "CL_00000935";
/*     */   
/*     */   public AbstractClientPlayer(World worldIn, GameProfile p_i45074_2_) {
/*  34 */     super(worldIn, p_i45074_2_);
/*  35 */     this.nameClear = p_i45074_2_.getName();
/*     */     
/*  37 */     if (this.nameClear != null && !this.nameClear.isEmpty())
/*     */     {
/*  39 */       this.nameClear = StringUtils.stripControlCodes(this.nameClear);
/*     */     }
/*     */     
/*  42 */     CapeUtils.downloadCape(this);
/*  43 */     PlayerConfigurations.getPlayerConfiguration(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175149_v() {
/*  48 */     NetworkPlayerInfo var1 = Minecraft.getMinecraft().getNetHandler().func_175102_a(getGameProfile().getId());
/*  49 */     return (var1 != null && var1.getGameType() == WorldSettings.GameType.SPECTATOR);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCape() {
/*  54 */     return (func_175155_b() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NetworkPlayerInfo func_175155_b() {
/*  59 */     if (this.field_175157_a == null)
/*     */     {
/*  61 */       this.field_175157_a = Minecraft.getMinecraft().getNetHandler().func_175102_a(getUniqueID());
/*     */     }
/*     */     
/*  64 */     return this.field_175157_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSkin() {
/*  69 */     NetworkPlayerInfo var1 = func_175155_b();
/*  70 */     return (var1 != null && var1.func_178856_e());
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getLocationSkin() {
/*  75 */     NetworkPlayerInfo var1 = func_175155_b();
/*  76 */     return (var1 == null) ? DefaultPlayerSkin.func_177334_a(getUniqueID()) : var1.func_178837_g();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getLocationCape() {
/*  81 */     if (!Config.isShowCapes())
/*     */     {
/*  83 */       return null;
/*     */     }
/*  85 */     if (this.locationOfCape != null)
/*     */     {
/*  87 */       return this.locationOfCape;
/*     */     }
/*     */ 
/*     */     
/*  91 */     NetworkPlayerInfo var1 = func_175155_b();
/*  92 */     return (var1 == null) ? null : var1.func_178861_h();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation resourceLocationIn, String username) {
/*  98 */     TextureManager var2 = Minecraft.getMinecraft().getTextureManager();
/*  99 */     Object var3 = var2.getTexture(resourceLocationIn);
/*     */     
/* 101 */     if (var3 == null) {
/*     */       
/* 103 */       var3 = new ThreadDownloadImageData(null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] { StringUtils.stripControlCodes(username) }), DefaultPlayerSkin.func_177334_a(func_175147_b(username)), (IImageBuffer)new ImageBufferDownload());
/* 104 */       var2.loadTexture(resourceLocationIn, (ITextureObject)var3);
/*     */     } 
/*     */     
/* 107 */     return (ThreadDownloadImageData)var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResourceLocation getLocationSkin(String username) {
/* 112 */     return new ResourceLocation("skins/" + StringUtils.stripControlCodes(username));
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_175154_l() {
/* 117 */     NetworkPlayerInfo var1 = func_175155_b();
/* 118 */     return (var1 == null) ? DefaultPlayerSkin.func_177332_b(getUniqueID()) : var1.func_178851_f();
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_175156_o() {
/* 123 */     float var1 = 1.0F;
/*     */     
/* 125 */     if (this.capabilities.isFlying)
/*     */     {
/* 127 */       var1 *= 1.1F;
/*     */     }
/*     */     
/* 130 */     IAttributeInstance var2 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 131 */     var1 = (float)(var1 * (var2.getAttributeValue() / this.capabilities.getWalkSpeed() + 1.0D) / 2.0D);
/*     */     
/* 133 */     if (this.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(var1) || Float.isInfinite(var1))
/*     */     {
/* 135 */       var1 = 1.0F;
/*     */     }
/*     */     
/* 138 */     if (isUsingItem() && getItemInUse().getItem() == Items.bow) {
/*     */       
/* 140 */       int var3 = getItemInUseDuration();
/* 141 */       float var4 = var3 / 20.0F;
/*     */       
/* 143 */       if (var4 > 1.0F) {
/*     */         
/* 145 */         var4 = 1.0F;
/*     */       }
/*     */       else {
/*     */         
/* 149 */         var4 *= var4;
/*     */       } 
/*     */       
/* 152 */       var1 *= 1.0F - var4 * 0.15F;
/*     */     } 
/*     */     
/* 155 */     return Reflector.ForgeHooksClient_getOffsetFOV.exists() ? Reflector.callFloat(Reflector.ForgeHooksClient_getOffsetFOV, new Object[] { this, Float.valueOf(var1) }) : var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameClear() {
/* 160 */     return this.nameClear;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getLocationOfCape() {
/* 165 */     return this.locationOfCape;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocationOfCape(ResourceLocation locationOfCape) {
/* 170 */     this.locationOfCape = locationOfCape;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\entity\AbstractClientPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */