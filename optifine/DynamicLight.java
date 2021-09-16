/*     */ package optifine;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.client.renderer.chunk.CompiledChunk;
/*     */ import net.minecraft.client.renderer.chunk.RenderChunk;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class DynamicLight
/*     */ {
/*  20 */   private Entity entity = null;
/*  21 */   private double offsetY = 0.0D;
/*  22 */   private double lastPosX = -2.147483648E9D;
/*  23 */   private double lastPosY = -2.147483648E9D;
/*  24 */   private double lastPosZ = -2.147483648E9D;
/*  25 */   private int lastLightLevel = 0;
/*     */   private boolean underwater = false;
/*  27 */   private long timeCheckMs = 0L;
/*  28 */   private Set<BlockPos> setLitChunkPos = new HashSet<>();
/*  29 */   private BlockPosM blockPosMutable = new BlockPosM(0, 0, 0);
/*     */ 
/*     */   
/*     */   public DynamicLight(Entity entity) {
/*  33 */     this.entity = entity;
/*  34 */     this.offsetY = entity.getEyeHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(RenderGlobal renderGlobal) {
/*  39 */     if (Config.isDynamicLightsFast()) {
/*     */       
/*  41 */       long posX = System.currentTimeMillis();
/*     */       
/*  43 */       if (posX < this.timeCheckMs + 500L) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  48 */       this.timeCheckMs = posX;
/*     */     } 
/*     */     
/*  51 */     double posX1 = this.entity.posX - 0.5D;
/*  52 */     double posY = this.entity.posY - 0.5D + this.offsetY;
/*  53 */     double posZ = this.entity.posZ - 0.5D;
/*  54 */     int lightLevel = DynamicLights.getLightLevel(this.entity);
/*  55 */     double dx = posX1 - this.lastPosX;
/*  56 */     double dy = posY - this.lastPosY;
/*  57 */     double dz = posZ - this.lastPosZ;
/*  58 */     double delta = 0.1D;
/*     */     
/*  60 */     if (Math.abs(dx) > delta || Math.abs(dy) > delta || Math.abs(dz) > delta || this.lastLightLevel != lightLevel) {
/*     */       
/*  62 */       this.lastPosX = posX1;
/*  63 */       this.lastPosY = posY;
/*  64 */       this.lastPosZ = posZ;
/*  65 */       this.lastLightLevel = lightLevel;
/*  66 */       this.underwater = false;
/*  67 */       WorldClient world = renderGlobal.getWorld();
/*     */       
/*  69 */       if (world != null) {
/*     */         
/*  71 */         this.blockPosMutable.setXyz(MathHelper.floor_double(posX1), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
/*  72 */         IBlockState setNewPos = world.getBlockState(this.blockPosMutable);
/*  73 */         Block dirX = setNewPos.getBlock();
/*  74 */         this.underwater = (dirX == Blocks.water);
/*     */       } 
/*     */       
/*  77 */       HashSet<BlockPos> setNewPos1 = new HashSet();
/*     */       
/*  79 */       if (lightLevel > 0) {
/*     */         
/*  81 */         EnumFacing dirX1 = ((MathHelper.floor_double(posX1) & 0xF) >= 8) ? EnumFacing.EAST : EnumFacing.WEST;
/*  82 */         EnumFacing dirY = ((MathHelper.floor_double(posY) & 0xF) >= 8) ? EnumFacing.UP : EnumFacing.DOWN;
/*  83 */         EnumFacing dirZ = ((MathHelper.floor_double(posZ) & 0xF) >= 8) ? EnumFacing.SOUTH : EnumFacing.NORTH;
/*  84 */         BlockPos pos = new BlockPos(posX1, posY, posZ);
/*  85 */         RenderChunk chunkView = renderGlobal.getRenderChunk(pos);
/*  86 */         RenderChunk chunkX = renderGlobal.getRenderChunk(chunkView, dirX1);
/*  87 */         RenderChunk chunkZ = renderGlobal.getRenderChunk(chunkView, dirZ);
/*  88 */         RenderChunk chunkXZ = renderGlobal.getRenderChunk(chunkX, dirZ);
/*  89 */         RenderChunk chunkY = renderGlobal.getRenderChunk(chunkView, dirY);
/*  90 */         RenderChunk chunkYX = renderGlobal.getRenderChunk(chunkY, dirX1);
/*  91 */         RenderChunk chunkYZ = renderGlobal.getRenderChunk(chunkY, dirZ);
/*  92 */         RenderChunk chunkYXZ = renderGlobal.getRenderChunk(chunkYX, dirZ);
/*  93 */         updateChunkLight(chunkView, this.setLitChunkPos, setNewPos1);
/*  94 */         updateChunkLight(chunkX, this.setLitChunkPos, setNewPos1);
/*  95 */         updateChunkLight(chunkZ, this.setLitChunkPos, setNewPos1);
/*  96 */         updateChunkLight(chunkXZ, this.setLitChunkPos, setNewPos1);
/*  97 */         updateChunkLight(chunkY, this.setLitChunkPos, setNewPos1);
/*  98 */         updateChunkLight(chunkYX, this.setLitChunkPos, setNewPos1);
/*  99 */         updateChunkLight(chunkYZ, this.setLitChunkPos, setNewPos1);
/* 100 */         updateChunkLight(chunkYXZ, this.setLitChunkPos, setNewPos1);
/*     */       } 
/*     */       
/* 103 */       updateLitChunks(renderGlobal);
/* 104 */       this.setLitChunkPos = setNewPos1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateChunkLight(RenderChunk renderChunk, Set<BlockPos> setPrevPos, Set<BlockPos> setNewPos) {
/* 110 */     if (renderChunk != null) {
/*     */       
/* 112 */       CompiledChunk compiledChunk = renderChunk.func_178571_g();
/*     */       
/* 114 */       if (compiledChunk != null && !compiledChunk.func_178489_a())
/*     */       {
/* 116 */         renderChunk.func_178575_a(true);
/*     */       }
/*     */       
/* 119 */       BlockPos pos = renderChunk.func_178568_j();
/*     */       
/* 121 */       if (setPrevPos != null)
/*     */       {
/* 123 */         setPrevPos.remove(pos);
/*     */       }
/*     */       
/* 126 */       if (setNewPos != null)
/*     */       {
/* 128 */         setNewPos.add(pos);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateLitChunks(RenderGlobal renderGlobal) {
/* 135 */     Iterator<BlockPos> it = this.setLitChunkPos.iterator();
/*     */     
/* 137 */     while (it.hasNext()) {
/*     */       
/* 139 */       BlockPos posOld = it.next();
/* 140 */       RenderChunk chunkOld = renderGlobal.getRenderChunk(posOld);
/* 141 */       updateChunkLight(chunkOld, null, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getEntity() {
/* 147 */     return this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLastPosX() {
/* 152 */     return this.lastPosX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLastPosY() {
/* 157 */     return this.lastPosY;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLastPosZ() {
/* 162 */     return this.lastPosZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastLightLevel() {
/* 167 */     return this.lastLightLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUnderwater() {
/* 172 */     return this.underwater;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffsetY() {
/* 177 */     return this.offsetY;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 182 */     return "Entity: " + this.entity + ", offsetY: " + this.offsetY;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\DynamicLight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */