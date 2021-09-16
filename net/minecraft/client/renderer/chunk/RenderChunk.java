/*     */ package net.minecraft.client.renderer.chunk;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RegionRenderCache;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.renderer.vertex.VertexBuffer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import optifine.BlockPosM;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ import optifine.ReflectorForge;
/*     */ import shadersmod.client.SVertexBuilder;
/*     */ 
/*     */ public class RenderChunk
/*     */ {
/*     */   private World field_178588_d;
/*     */   private final RenderGlobal field_178589_e;
/*     */   public static int field_178592_a;
/*     */   private BlockPos field_178586_f;
/*     */   public CompiledChunk field_178590_b;
/*     */   private final ReentrantLock field_178587_g;
/*     */   private final ReentrantLock field_178598_h;
/*     */   private ChunkCompileTaskGenerator field_178599_i;
/*     */   private final int field_178596_j;
/*     */   private final FloatBuffer field_178597_k;
/*     */   private final VertexBuffer[] field_178594_l;
/*     */   public AxisAlignedBB field_178591_c;
/*     */   private int field_178595_m;
/*     */   private boolean field_178593_n;
/*     */   private static final String __OBFID = "CL_00002452";
/*     */   private BlockPos[] positionOffsets16;
/*  51 */   private static EnumWorldBlockLayer[] ENUM_WORLD_BLOCK_LAYERS = EnumWorldBlockLayer.values();
/*     */   
/*     */   private EnumWorldBlockLayer[] blockLayersSingle;
/*     */   private boolean isMipmaps;
/*     */   private boolean fixBlockLayer;
/*     */   private boolean playerUpdate;
/*     */   
/*     */   public RenderChunk(World worldIn, RenderGlobal renderGlobalIn, BlockPos blockPosIn, int indexIn) {
/*  59 */     this.positionOffsets16 = new BlockPos[EnumFacing.VALUES.length];
/*  60 */     this.blockLayersSingle = new EnumWorldBlockLayer[1];
/*  61 */     this.isMipmaps = Config.isMipmaps();
/*  62 */     this.fixBlockLayer = !Reflector.BetterFoliageClient.exists();
/*  63 */     this.playerUpdate = false;
/*  64 */     this.field_178590_b = CompiledChunk.field_178502_a;
/*  65 */     this.field_178587_g = new ReentrantLock();
/*  66 */     this.field_178598_h = new ReentrantLock();
/*  67 */     this.field_178599_i = null;
/*  68 */     this.field_178597_k = GLAllocation.createDirectFloatBuffer(16);
/*  69 */     this.field_178594_l = new VertexBuffer[(EnumWorldBlockLayer.values()).length];
/*  70 */     this.field_178595_m = -1;
/*  71 */     this.field_178593_n = true;
/*  72 */     this.field_178588_d = worldIn;
/*  73 */     this.field_178589_e = renderGlobalIn;
/*  74 */     this.field_178596_j = indexIn;
/*     */     
/*  76 */     if (!blockPosIn.equals(func_178568_j()))
/*     */     {
/*  78 */       func_178576_a(blockPosIn);
/*     */     }
/*     */     
/*  81 */     if (OpenGlHelper.func_176075_f())
/*     */     {
/*  83 */       for (int var5 = 0; var5 < (EnumWorldBlockLayer.values()).length; var5++)
/*     */       {
/*  85 */         this.field_178594_l[var5] = new VertexBuffer(DefaultVertexFormats.field_176600_a);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178577_a(int frameIndexIn) {
/*  92 */     if (this.field_178595_m == frameIndexIn)
/*     */     {
/*  94 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  98 */     this.field_178595_m = frameIndexIn;
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexBuffer func_178565_b(int p_178565_1_) {
/* 105 */     return this.field_178594_l[p_178565_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178576_a(BlockPos p_178576_1_) {
/* 110 */     func_178585_h();
/* 111 */     this.field_178586_f = p_178576_1_;
/* 112 */     this.field_178591_c = new AxisAlignedBB(p_178576_1_, p_178576_1_.add(16, 16, 16));
/* 113 */     func_178567_n();
/*     */     
/* 115 */     for (int i = 0; i < this.positionOffsets16.length; i++)
/*     */     {
/* 117 */       this.positionOffsets16[i] = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178570_a(float p_178570_1_, float p_178570_2_, float p_178570_3_, ChunkCompileTaskGenerator p_178570_4_) {
/* 123 */     CompiledChunk var5 = p_178570_4_.func_178544_c();
/*     */     
/* 125 */     if (var5.func_178487_c() != null && !var5.func_178491_b(EnumWorldBlockLayer.TRANSLUCENT)) {
/*     */       
/* 127 */       WorldRenderer worldRenderer = p_178570_4_.func_178545_d().func_179038_a(EnumWorldBlockLayer.TRANSLUCENT);
/* 128 */       func_178573_a(worldRenderer, this.field_178586_f);
/* 129 */       worldRenderer.setVertexState(var5.func_178487_c());
/* 130 */       func_178584_a(EnumWorldBlockLayer.TRANSLUCENT, p_178570_1_, p_178570_2_, p_178570_3_, worldRenderer, var5);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_178581_b(float p_178581_1_, float p_178581_2_, float p_178581_3_, ChunkCompileTaskGenerator p_178581_4_) {
/*     */     RegionRenderCache var9;
/* 136 */     CompiledChunk var5 = new CompiledChunk();
/* 137 */     boolean var6 = true;
/* 138 */     BlockPos var7 = this.field_178586_f;
/* 139 */     BlockPos var8 = var7.add(15, 15, 15);
/* 140 */     p_178581_4_.func_178540_f().lock();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 146 */       if (p_178581_4_.func_178546_a() != ChunkCompileTaskGenerator.Status.COMPILING)
/*     */       {
/*     */         return;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 166 */       p_178581_4_.func_178540_f().unlock();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 171 */     VisGraph var10 = new VisGraph();
/*     */     
/* 173 */     if (!var9.extendedLevelsInChunkCache()) {
/*     */       
/* 175 */       field_178592_a++;
/* 176 */       Iterator<BlockPosM> var11 = BlockPosM.getAllInBoxMutable(var7, var8).iterator();
/* 177 */       boolean forgeHasTileEntityExists = Reflector.ForgeBlock_hasTileEntity.exists();
/* 178 */       boolean forgeBlockCanRenderInLayerExists = Reflector.ForgeBlock_canRenderInLayer.exists();
/* 179 */       boolean forgeHooksSetRenderLayerExists = Reflector.ForgeHooksClient_setRenderLayer.exists();
/*     */       
/* 181 */       while (var11.hasNext()) {
/*     */         EnumWorldBlockLayer[] var28;
/* 183 */         BlockPosM var20 = var11.next();
/* 184 */         IBlockState var21 = var9.getBlockState((BlockPos)var20);
/* 185 */         Block var22 = var21.getBlock();
/*     */         
/* 187 */         if (var22.isOpaqueCube())
/*     */         {
/* 189 */           var10.func_178606_a((BlockPos)var20);
/*     */         }
/*     */         
/* 192 */         if (ReflectorForge.blockHasTileEntity(var21)) {
/*     */           
/* 194 */           TileEntity var23 = var9.getTileEntity(new BlockPos((Vec3i)var20));
/*     */           
/* 196 */           if (var23 != null && TileEntityRendererDispatcher.instance.hasSpecialRenderer(var23))
/*     */           {
/* 198 */             var5.func_178490_a(var23);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 204 */         if (forgeBlockCanRenderInLayerExists) {
/*     */           
/* 206 */           var28 = ENUM_WORLD_BLOCK_LAYERS;
/*     */         }
/*     */         else {
/*     */           
/* 210 */           var28 = this.blockLayersSingle;
/* 211 */           var28[0] = var22.getBlockLayer();
/*     */         } 
/*     */         
/* 214 */         for (int i = 0; i < var28.length; i++) {
/*     */           
/* 216 */           EnumWorldBlockLayer var24 = var28[i];
/*     */           
/* 218 */           if (forgeBlockCanRenderInLayerExists) {
/*     */             
/* 220 */             boolean var16 = Reflector.callBoolean(var22, Reflector.ForgeBlock_canRenderInLayer, new Object[] { var24 });
/*     */             
/* 222 */             if (!var16) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 228 */           if (forgeHooksSetRenderLayerExists)
/*     */           {
/* 230 */             Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, new Object[] { var24 });
/*     */           }
/*     */           
/* 233 */           if (this.fixBlockLayer)
/*     */           {
/* 235 */             var24 = fixBlockLayer(var22, var24);
/*     */           }
/*     */           
/* 238 */           int var30 = var24.ordinal();
/*     */           
/* 240 */           if (var22.getRenderType() != -1) {
/*     */             
/* 242 */             WorldRenderer var17 = p_178581_4_.func_178545_d().func_179039_a(var30);
/* 243 */             var17.setBlockLayer(var24);
/*     */             
/* 245 */             if (!var5.func_178492_d(var24)) {
/*     */               
/* 247 */               var5.func_178493_c(var24);
/* 248 */               func_178573_a(var17, var7);
/*     */             } 
/*     */             
/* 251 */             if (Minecraft.getMinecraft().getBlockRendererDispatcher().func_175018_a(var21, (BlockPos)var20, (IBlockAccess)var9, var17))
/*     */             {
/* 253 */               var5.func_178486_a(var24);
/*     */             }
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */       } 
/* 259 */       EnumWorldBlockLayer[] var25 = ENUM_WORLD_BLOCK_LAYERS;
/* 260 */       int var26 = var25.length;
/*     */       
/* 262 */       for (int var27 = 0; var27 < var26; var27++) {
/*     */         
/* 264 */         EnumWorldBlockLayer var29 = var25[var27];
/*     */         
/* 266 */         if (var5.func_178492_d(var29)) {
/*     */           
/* 268 */           if (Config.isShaders())
/*     */           {
/* 270 */             SVertexBuilder.calcNormalChunkLayer(p_178581_4_.func_178545_d().func_179038_a(var29));
/*     */           }
/*     */           
/* 273 */           func_178584_a(var29, p_178581_1_, p_178581_2_, p_178581_3_, p_178581_4_.func_178545_d().func_179038_a(var29), var5);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 278 */     var5.func_178488_a(var10.func_178607_a());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178578_b() {
/* 283 */     this.field_178587_g.lock();
/*     */ 
/*     */     
/*     */     try {
/* 287 */       if (this.field_178599_i != null && this.field_178599_i.func_178546_a() != ChunkCompileTaskGenerator.Status.DONE)
/*     */       {
/* 289 */         this.field_178599_i.func_178542_e();
/* 290 */         this.field_178599_i = null;
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 295 */       this.field_178587_g.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ReentrantLock func_178579_c() {
/* 301 */     return this.field_178587_g;
/*     */   }
/*     */   
/*     */   public ChunkCompileTaskGenerator func_178574_d() {
/*     */     ChunkCompileTaskGenerator var1;
/* 306 */     this.field_178587_g.lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 311 */       func_178578_b();
/* 312 */       this.field_178599_i = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.REBUILD_CHUNK);
/* 313 */       var1 = this.field_178599_i;
/*     */     }
/*     */     finally {
/*     */       
/* 317 */       this.field_178587_g.unlock();
/*     */     } 
/*     */     
/* 320 */     return var1;
/*     */   }
/*     */   
/*     */   public ChunkCompileTaskGenerator func_178582_e() {
/*     */     ChunkCompileTaskGenerator var2;
/* 325 */     this.field_178587_g.lock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 332 */     try { if (this.field_178599_i != null && this.field_178599_i.func_178546_a() == ChunkCompileTaskGenerator.Status.PENDING) {
/*     */         
/* 334 */         ChunkCompileTaskGenerator chunkCompileTaskGenerator = null;
/* 335 */         return chunkCompileTaskGenerator;
/*     */       } 
/*     */       
/* 338 */       if (this.field_178599_i != null && this.field_178599_i.func_178546_a() != ChunkCompileTaskGenerator.Status.DONE) {
/*     */         
/* 340 */         this.field_178599_i.func_178542_e();
/* 341 */         this.field_178599_i = null;
/*     */       } 
/*     */       
/* 344 */       this.field_178599_i = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY);
/* 345 */       this.field_178599_i.func_178543_a(this.field_178590_b);
/* 346 */       ChunkCompileTaskGenerator var1 = this.field_178599_i;
/*     */        }
/*     */     
/*     */     finally
/*     */     
/* 351 */     { this.field_178587_g.unlock(); }  this.field_178587_g.unlock();
/*     */ 
/*     */     
/* 354 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178573_a(WorldRenderer p_178573_1_, BlockPos p_178573_2_) {
/* 359 */     p_178573_1_.startDrawing(7);
/* 360 */     p_178573_1_.setVertexFormat(DefaultVertexFormats.field_176600_a);
/* 361 */     p_178573_1_.setTranslation(-p_178573_2_.getX(), -p_178573_2_.getY(), -p_178573_2_.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178584_a(EnumWorldBlockLayer p_178584_1_, float p_178584_2_, float p_178584_3_, float p_178584_4_, WorldRenderer p_178584_5_, CompiledChunk p_178584_6_) {
/* 366 */     if (p_178584_1_ == EnumWorldBlockLayer.TRANSLUCENT && !p_178584_6_.func_178491_b(p_178584_1_))
/*     */     {
/* 368 */       p_178584_6_.func_178494_a(p_178584_5_.getVertexState(p_178584_2_, p_178584_3_, p_178584_4_));
/*     */     }
/*     */     
/* 371 */     p_178584_5_.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178567_n() {
/* 376 */     GlStateManager.pushMatrix();
/* 377 */     GlStateManager.loadIdentity();
/* 378 */     float var1 = 1.000001F;
/* 379 */     GlStateManager.translate(-8.0F, -8.0F, -8.0F);
/* 380 */     GlStateManager.scale(var1, var1, var1);
/* 381 */     GlStateManager.translate(8.0F, 8.0F, 8.0F);
/* 382 */     GlStateManager.getFloat(2982, this.field_178597_k);
/* 383 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178572_f() {
/* 388 */     GlStateManager.multMatrix(this.field_178597_k);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompiledChunk func_178571_g() {
/* 393 */     return this.field_178590_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178580_a(CompiledChunk p_178580_1_) {
/* 398 */     this.field_178598_h.lock();
/*     */ 
/*     */     
/*     */     try {
/* 402 */       this.field_178590_b = p_178580_1_;
/*     */     }
/*     */     finally {
/*     */       
/* 406 */       this.field_178598_h.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178585_h() {
/* 412 */     func_178578_b();
/* 413 */     this.field_178590_b = CompiledChunk.field_178502_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178566_a() {
/* 418 */     func_178585_h();
/* 419 */     this.field_178588_d = null;
/*     */     
/* 421 */     for (int var1 = 0; var1 < (EnumWorldBlockLayer.values()).length; var1++) {
/*     */       
/* 423 */       if (this.field_178594_l[var1] != null)
/*     */       {
/* 425 */         this.field_178594_l[var1].func_177362_c();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_178568_j() {
/* 432 */     return this.field_178586_f;
/*     */   }
/*     */   
/*     */   public boolean func_178583_l() {
/*     */     boolean var1;
/* 437 */     this.field_178587_g.lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 442 */       var1 = !(this.field_178599_i != null && this.field_178599_i.func_178546_a() != ChunkCompileTaskGenerator.Status.PENDING);
/*     */     }
/*     */     finally {
/*     */       
/* 446 */       this.field_178587_g.unlock();
/*     */     } 
/*     */     
/* 449 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178575_a(boolean p_178575_1_) {
/* 454 */     this.field_178593_n = p_178575_1_;
/*     */     
/* 456 */     if (this.field_178593_n) {
/*     */       
/* 458 */       if (isWorldPlayerUpdate())
/*     */       {
/* 460 */         this.playerUpdate = true;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 465 */       this.playerUpdate = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178569_m() {
/* 471 */     return this.field_178593_n;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getPositionOffset16(EnumFacing facing) {
/* 476 */     int index = facing.getIndex();
/* 477 */     BlockPos posOffset = this.positionOffsets16[index];
/*     */     
/* 479 */     if (posOffset == null) {
/*     */       
/* 481 */       posOffset = func_178568_j().offset(facing, 16);
/* 482 */       this.positionOffsets16[index] = posOffset;
/*     */     } 
/*     */     
/* 485 */     return posOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isWorldPlayerUpdate() {
/* 490 */     if (this.field_178588_d instanceof WorldClient) {
/*     */       
/* 492 */       WorldClient worldClient = (WorldClient)this.field_178588_d;
/* 493 */       return worldClient.isPlayerUpdate();
/*     */     } 
/*     */ 
/*     */     
/* 497 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlayerUpdate() {
/* 503 */     return this.playerUpdate;
/*     */   }
/*     */ 
/*     */   
/*     */   protected RegionRenderCache createRegionRenderCache(World world, BlockPos from, BlockPos to, int subtract) {
/* 508 */     return new RegionRenderCache(world, from, to, subtract);
/*     */   }
/*     */ 
/*     */   
/*     */   private EnumWorldBlockLayer fixBlockLayer(Block block, EnumWorldBlockLayer layer) {
/* 513 */     if (this.isMipmaps) {
/*     */       
/* 515 */       if (layer == EnumWorldBlockLayer.CUTOUT)
/*     */       {
/* 517 */         if (block instanceof net.minecraft.block.BlockRedstoneWire)
/*     */         {
/* 519 */           return layer;
/*     */         }
/*     */         
/* 522 */         if (block instanceof net.minecraft.block.BlockCactus)
/*     */         {
/* 524 */           return layer;
/*     */         }
/*     */         
/* 527 */         return EnumWorldBlockLayer.CUTOUT_MIPPED;
/*     */       }
/*     */     
/* 530 */     } else if (layer == EnumWorldBlockLayer.CUTOUT_MIPPED) {
/*     */       
/* 532 */       return EnumWorldBlockLayer.CUTOUT;
/*     */     } 
/*     */     
/* 535 */     return layer;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\chunk\RenderChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */