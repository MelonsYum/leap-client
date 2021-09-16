/*     */ package optifine;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.BlockStateBase;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.BlockModelRenderer;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ public class RenderEnv
/*     */ {
/*     */   private IBlockAccess blockAccess;
/*     */   private IBlockState blockState;
/*     */   private BlockPos blockPos;
/*     */   private GameSettings gameSettings;
/*  22 */   private int blockId = -1;
/*  23 */   private int metadata = -1;
/*  24 */   private int breakingAnimation = -1;
/*     */   private float[] quadBounds;
/*     */   private BitSet boundsFlags;
/*     */   private BlockModelRenderer.AmbientOcclusionFace aoFace;
/*     */   private BlockPosM colorizerBlockPosM;
/*     */   private boolean[] borderFlags;
/*  30 */   private static ThreadLocal threadLocalInstance = new ThreadLocal();
/*     */ 
/*     */   
/*     */   private RenderEnv(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos) {
/*  34 */     this.quadBounds = new float[EnumFacing.VALUES.length * 2];
/*  35 */     this.boundsFlags = new BitSet(3);
/*  36 */     this.aoFace = new BlockModelRenderer.AmbientOcclusionFace();
/*  37 */     this.colorizerBlockPosM = null;
/*  38 */     this.borderFlags = null;
/*  39 */     this.blockAccess = blockAccess;
/*  40 */     this.blockState = blockState;
/*  41 */     this.blockPos = blockPos;
/*  42 */     this.gameSettings = Config.getGameSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderEnv getInstance(IBlockAccess blockAccessIn, IBlockState blockStateIn, BlockPos blockPosIn) {
/*  47 */     RenderEnv re = threadLocalInstance.get();
/*     */     
/*  49 */     if (re == null) {
/*     */       
/*  51 */       re = new RenderEnv(blockAccessIn, blockStateIn, blockPosIn);
/*  52 */       threadLocalInstance.set(re);
/*  53 */       return re;
/*     */     } 
/*     */ 
/*     */     
/*  57 */     re.reset(blockAccessIn, blockStateIn, blockPosIn);
/*  58 */     return re;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void reset(IBlockAccess blockAccessIn, IBlockState blockStateIn, BlockPos blockPosIn) {
/*  64 */     this.blockAccess = blockAccessIn;
/*  65 */     this.blockState = blockStateIn;
/*  66 */     this.blockPos = blockPosIn;
/*  67 */     this.blockId = -1;
/*  68 */     this.metadata = -1;
/*  69 */     this.breakingAnimation = -1;
/*  70 */     this.boundsFlags.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockId() {
/*  75 */     if (this.blockId < 0)
/*     */     {
/*  77 */       if (this.blockState instanceof BlockStateBase) {
/*     */         
/*  79 */         BlockStateBase bsb = (BlockStateBase)this.blockState;
/*  80 */         this.blockId = bsb.getBlockId();
/*     */       }
/*     */       else {
/*     */         
/*  84 */         this.blockId = Block.getIdFromBlock(this.blockState.getBlock());
/*     */       } 
/*     */     }
/*     */     
/*  88 */     return this.blockId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadata() {
/*  93 */     if (this.metadata < 0)
/*     */     {
/*  95 */       if (this.blockState instanceof BlockStateBase) {
/*     */         
/*  97 */         BlockStateBase bsb = (BlockStateBase)this.blockState;
/*  98 */         this.metadata = bsb.getMetadata();
/*     */       }
/*     */       else {
/*     */         
/* 102 */         this.metadata = this.blockState.getBlock().getMetaFromState(this.blockState);
/*     */       } 
/*     */     }
/*     */     
/* 106 */     return this.metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] getQuadBounds() {
/* 111 */     return this.quadBounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public BitSet getBoundsFlags() {
/* 116 */     return this.boundsFlags;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockModelRenderer.AmbientOcclusionFace getAoFace() {
/* 121 */     return this.aoFace;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBreakingAnimation(List listQuads) {
/* 126 */     if (this.breakingAnimation < 0 && listQuads.size() > 0)
/*     */     {
/* 128 */       if (listQuads.get(0) instanceof net.minecraft.client.renderer.block.model.BreakingFour) {
/*     */         
/* 130 */         this.breakingAnimation = 1;
/*     */       }
/*     */       else {
/*     */         
/* 134 */         this.breakingAnimation = 0;
/*     */       } 
/*     */     }
/*     */     
/* 138 */     return (this.breakingAnimation == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBreakingAnimation(BakedQuad quad) {
/* 143 */     if (this.breakingAnimation < 0)
/*     */     {
/* 145 */       if (quad instanceof net.minecraft.client.renderer.block.model.BreakingFour) {
/*     */         
/* 147 */         this.breakingAnimation = 1;
/*     */       }
/*     */       else {
/*     */         
/* 151 */         this.breakingAnimation = 0;
/*     */       } 
/*     */     }
/*     */     
/* 155 */     return (this.breakingAnimation == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBreakingAnimation() {
/* 160 */     return (this.breakingAnimation == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState getBlockState() {
/* 165 */     return this.blockState;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosM getColorizerBlockPosM() {
/* 170 */     if (this.colorizerBlockPosM == null)
/*     */     {
/* 172 */       this.colorizerBlockPosM = new BlockPosM(0, 0, 0);
/*     */     }
/*     */     
/* 175 */     return this.colorizerBlockPosM;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[] getBorderFlags() {
/* 180 */     if (this.borderFlags == null)
/*     */     {
/* 182 */       this.borderFlags = new boolean[4];
/*     */     }
/*     */     
/* 185 */     return this.borderFlags;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\RenderEnv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */