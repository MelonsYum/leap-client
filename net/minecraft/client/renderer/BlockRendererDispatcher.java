/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.SimpleBakedModel;
/*     */ import net.minecraft.client.resources.model.WeightedBakedModel;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.WorldType;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ import shadersmod.client.SVertexBuilder;
/*     */ 
/*     */ public class BlockRendererDispatcher implements IResourceManagerReloadListener {
/*     */   private BlockModelShapes field_175028_a;
/*     */   private final GameSettings field_175026_b;
/*  28 */   private final BlockModelRenderer blockModelRenderer = new BlockModelRenderer();
/*  29 */   private final ChestRenderer chestRenderer = new ChestRenderer();
/*  30 */   private final BlockFluidRenderer fluidRenderer = new BlockFluidRenderer();
/*     */ 
/*     */   
/*     */   public BlockRendererDispatcher(BlockModelShapes p_i46237_1_, GameSettings p_i46237_2_) {
/*  34 */     this.field_175028_a = p_i46237_1_;
/*  35 */     this.field_175026_b = p_i46237_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockModelShapes func_175023_a() {
/*  40 */     return this.field_175028_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175020_a(IBlockState p_175020_1_, BlockPos p_175020_2_, TextureAtlasSprite p_175020_3_, IBlockAccess p_175020_4_) {
/*  45 */     Block var5 = p_175020_1_.getBlock();
/*  46 */     int var6 = var5.getRenderType();
/*     */     
/*  48 */     if (var6 == 3) {
/*     */       
/*  50 */       p_175020_1_ = var5.getActualState(p_175020_1_, p_175020_4_, p_175020_2_);
/*  51 */       IBakedModel var7 = this.field_175028_a.func_178125_b(p_175020_1_);
/*     */       
/*  53 */       if (Reflector.ISmartBlockModel.isInstance(var7)) {
/*     */         
/*  55 */         IBlockState var15 = (IBlockState)Reflector.call(var5, Reflector.ForgeBlock_getExtendedState, new Object[] { p_175020_1_, p_175020_4_, p_175020_2_ });
/*  56 */         EnumWorldBlockLayer[] arr$ = EnumWorldBlockLayer.values();
/*  57 */         int len$ = arr$.length;
/*     */         
/*  59 */         for (int i$ = 0; i$ < len$; i$++) {
/*     */           
/*  61 */           EnumWorldBlockLayer layer = arr$[i$];
/*     */           
/*  63 */           if (Reflector.callBoolean(var5, Reflector.ForgeBlock_canRenderInLayer, new Object[] { layer })) {
/*     */             
/*  65 */             Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, new Object[] { layer });
/*  66 */             IBakedModel targetLayer = (IBakedModel)Reflector.call(var7, Reflector.ISmartBlockModel_handleBlockState, new Object[] { var15 });
/*  67 */             IBakedModel damageModel = (new SimpleBakedModel.Builder(targetLayer, p_175020_3_)).func_177645_b();
/*  68 */             this.blockModelRenderer.func_178259_a(p_175020_4_, damageModel, p_175020_1_, p_175020_2_, Tessellator.getInstance().getWorldRenderer());
/*     */           } 
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  75 */       IBakedModel var8 = (new SimpleBakedModel.Builder(var7, p_175020_3_)).func_177645_b();
/*  76 */       this.blockModelRenderer.func_178259_a(p_175020_4_, var8, p_175020_1_, p_175020_2_, Tessellator.getInstance().getWorldRenderer());
/*     */     } 
/*     */   }
/*     */   public boolean func_175018_a(IBlockState p_175018_1_, BlockPos p_175018_2_, IBlockAccess p_175018_3_, WorldRenderer p_175018_4_) {
/*     */     try {
/*     */       boolean var61;
/*     */       IBakedModel var71;
/*     */       boolean flag3;
/*  84 */       int var8 = p_175018_1_.getBlock().getRenderType();
/*     */       
/*  86 */       if (var8 == -1)
/*     */       {
/*  88 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  92 */       switch (var8) {
/*     */         
/*     */         case 1:
/*  95 */           if (Config.isShaders())
/*     */           {
/*  97 */             SVertexBuilder.pushEntity(p_175018_1_, p_175018_2_, p_175018_3_, p_175018_4_);
/*     */           }
/*     */           
/* 100 */           var61 = this.fluidRenderer.func_178270_a(p_175018_3_, p_175018_1_, p_175018_2_, p_175018_4_);
/*     */           
/* 102 */           if (Config.isShaders())
/*     */           {
/* 104 */             SVertexBuilder.popEntity(p_175018_4_);
/*     */           }
/*     */           
/* 107 */           return var61;
/*     */         
/*     */         case 2:
/* 110 */           return false;
/*     */         
/*     */         case 3:
/* 113 */           var71 = getModelFromBlockState(p_175018_1_, p_175018_3_, p_175018_2_);
/*     */           
/* 115 */           if (Config.isShaders())
/*     */           {
/* 117 */             SVertexBuilder.pushEntity(p_175018_1_, p_175018_2_, p_175018_3_, p_175018_4_);
/*     */           }
/*     */           
/* 120 */           flag3 = this.blockModelRenderer.func_178259_a(p_175018_3_, var71, p_175018_1_, p_175018_2_, p_175018_4_);
/*     */           
/* 122 */           if (Config.isShaders())
/*     */           {
/* 124 */             SVertexBuilder.popEntity(p_175018_4_);
/*     */           }
/*     */           
/* 127 */           return flag3;
/*     */       } 
/*     */       
/* 130 */       return false;
/*     */ 
/*     */     
/*     */     }
/* 134 */     catch (Throwable var9) {
/*     */       
/* 136 */       CrashReport var6 = CrashReport.makeCrashReport(var9, "Tesselating block in world");
/* 137 */       CrashReportCategory var7 = var6.makeCategory("Block being tesselated");
/* 138 */       CrashReportCategory.addBlockInfo(var7, p_175018_2_, p_175018_1_.getBlock(), p_175018_1_.getBlock().getMetaFromState(p_175018_1_));
/* 139 */       throw new ReportedException(var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockModelRenderer func_175019_b() {
/* 145 */     return this.blockModelRenderer;
/*     */   }
/*     */ 
/*     */   
/*     */   private IBakedModel func_175017_a(IBlockState p_175017_1_, BlockPos p_175017_2_) {
/* 150 */     IBakedModel var3 = this.field_175028_a.func_178125_b(p_175017_1_);
/*     */     
/* 152 */     if (p_175017_2_ != null && this.field_175026_b.field_178880_u && var3 instanceof WeightedBakedModel)
/*     */     {
/* 154 */       var3 = ((WeightedBakedModel)var3).func_177564_a(MathHelper.func_180186_a((Vec3i)p_175017_2_));
/*     */     }
/*     */     
/* 157 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBakedModel getModelFromBlockState(IBlockState p_175022_1_, IBlockAccess p_175022_2_, BlockPos p_175022_3_) {
/* 162 */     Block var4 = p_175022_1_.getBlock();
/*     */     
/* 164 */     if (p_175022_2_.getWorldType() != WorldType.DEBUG_WORLD) {
/*     */       
/*     */       try {
/*     */         
/* 168 */         p_175022_1_ = var4.getActualState(p_175022_1_, p_175022_2_, p_175022_3_);
/*     */       }
/* 170 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     IBakedModel var5 = this.field_175028_a.func_178125_b(p_175022_1_);
/*     */     
/* 178 */     if (p_175022_3_ != null && this.field_175026_b.field_178880_u && var5 instanceof WeightedBakedModel)
/*     */     {
/* 180 */       var5 = ((WeightedBakedModel)var5).func_177564_a(MathHelper.func_180186_a((Vec3i)p_175022_3_));
/*     */     }
/*     */     
/* 183 */     if (Reflector.ISmartBlockModel.isInstance(var5)) {
/*     */       
/* 185 */       IBlockState extendedState = (IBlockState)Reflector.call(var4, Reflector.ForgeBlock_getExtendedState, new Object[] { p_175022_1_, p_175022_2_, p_175022_3_ });
/* 186 */       var5 = (IBakedModel)Reflector.call(var5, Reflector.ISmartBlockModel_handleBlockState, new Object[] { extendedState });
/*     */     } 
/*     */     
/* 189 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175016_a(IBlockState p_175016_1_, float p_175016_2_) {
/* 194 */     int var3 = p_175016_1_.getBlock().getRenderType();
/*     */     
/* 196 */     if (var3 != -1) {
/*     */       
/* 198 */       switch (var3) {
/*     */         default:
/*     */           return;
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 205 */           this.chestRenderer.func_178175_a(p_175016_1_.getBlock(), p_175016_2_);
/*     */         case 3:
/*     */           break;
/*     */       } 
/* 209 */       IBakedModel var4 = func_175017_a(p_175016_1_, null);
/* 210 */       this.blockModelRenderer.func_178266_a(var4, p_175016_1_, p_175016_2_, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_175021_a(Block p_175021_1_, int p_175021_2_) {
/* 217 */     if (p_175021_1_ == null)
/*     */     {
/* 219 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 223 */     int var3 = p_175021_1_.getRenderType();
/* 224 */     return (var3 == 3) ? false : ((var3 == 2));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onResourceManagerReload(IResourceManager resourceManager) {
/* 230 */     this.fluidRenderer.func_178268_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\BlockRendererDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */