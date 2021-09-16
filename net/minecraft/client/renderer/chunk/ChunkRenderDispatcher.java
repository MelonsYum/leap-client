/*     */ package net.minecraft.client.renderer.chunk;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Queues;
/*     */ import com.google.common.util.concurrent.Futures;
/*     */ import com.google.common.util.concurrent.ListenableFuture;
/*     */ import com.google.common.util.concurrent.ListenableFutureTask;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RegionRenderCacheBuilder;
/*     */ import net.minecraft.client.renderer.VertexBufferUploader;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.WorldVertexBufferUploader;
/*     */ import net.minecraft.client.renderer.vertex.VertexBuffer;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ChunkRenderDispatcher
/*     */ {
/*  29 */   private static final Logger field_178523_a = LogManager.getLogger();
/*  30 */   private static final ThreadFactory field_178521_b = (new ThreadFactoryBuilder()).setNameFormat("Chunk Batcher %d").setDaemon(true).build();
/*  31 */   private final List field_178522_c = Lists.newArrayList();
/*  32 */   private final BlockingQueue field_178519_d = Queues.newArrayBlockingQueue(100);
/*  33 */   private final BlockingQueue field_178520_e = Queues.newArrayBlockingQueue(5);
/*  34 */   private final WorldVertexBufferUploader field_178517_f = new WorldVertexBufferUploader();
/*  35 */   private final VertexBufferUploader field_178518_g = new VertexBufferUploader();
/*  36 */   private final Queue field_178524_h = Queues.newArrayDeque();
/*     */   
/*     */   private final ChunkRenderWorker field_178525_i;
/*     */   
/*     */   private static final String __OBFID = "CL_00002463";
/*     */   
/*     */   public ChunkRenderDispatcher() {
/*     */     int var1;
/*  44 */     for (var1 = 0; var1 < 2; var1++) {
/*     */       
/*  46 */       ChunkRenderWorker var2 = new ChunkRenderWorker(this);
/*  47 */       Thread var3 = field_178521_b.newThread(var2);
/*  48 */       var3.start();
/*  49 */       this.field_178522_c.add(var2);
/*     */     } 
/*     */     
/*  52 */     for (var1 = 0; var1 < 5; var1++)
/*     */     {
/*  54 */       this.field_178520_e.add(new RegionRenderCacheBuilder());
/*     */     }
/*     */     
/*  57 */     this.field_178525_i = new ChunkRenderWorker(this, new RegionRenderCacheBuilder());
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_178504_a() {
/*  62 */     return String.format("pC: %03d, pU: %1d, aB: %1d", new Object[] { Integer.valueOf(this.field_178519_d.size()), Integer.valueOf(this.field_178524_h.size()), Integer.valueOf(this.field_178520_e.size()) });
/*     */   }
/*     */   
/*     */   public boolean func_178516_a(long p_178516_1_) {
/*     */     long var8;
/*  67 */     boolean var3 = false;
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  72 */       boolean var4 = false;
/*  73 */       Queue var5 = this.field_178524_h;
/*  74 */       Queue var81 = this.field_178524_h;
/*     */       
/*  76 */       synchronized (this.field_178524_h) {
/*     */         
/*  78 */         if (!this.field_178524_h.isEmpty()) {
/*     */           
/*  80 */           ((ListenableFutureTask)this.field_178524_h.poll()).run();
/*  81 */           var4 = true;
/*  82 */           var3 = true;
/*     */         } 
/*     */       } 
/*     */       
/*  86 */       if (p_178516_1_ == 0L || !var4) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*  91 */       var8 = p_178516_1_ - System.nanoTime();
/*     */     }
/*  93 */     while (var8 >= 0L && var8 <= 1000000000L);
/*     */     
/*  95 */     return var3;
/*     */   }
/*     */   
/*     */   public boolean func_178507_a(RenderChunk p_178507_1_) {
/*     */     boolean var4;
/* 100 */     p_178507_1_.func_178579_c().lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 105 */       final ChunkCompileTaskGenerator var2 = p_178507_1_.func_178574_d();
/* 106 */       var2.func_178539_a(new Runnable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002462";
/*     */             
/*     */             public void run() {
/* 111 */               ChunkRenderDispatcher.this.field_178519_d.remove(var2);
/*     */             }
/*     */           });
/* 114 */       boolean var3 = this.field_178519_d.offer(var2);
/*     */       
/* 116 */       if (!var3)
/*     */       {
/* 118 */         var2.func_178542_e();
/*     */       }
/*     */       
/* 121 */       var4 = var3;
/*     */     }
/*     */     finally {
/*     */       
/* 125 */       p_178507_1_.func_178579_c().unlock();
/*     */     } 
/*     */     
/* 128 */     return var4;
/*     */   }
/*     */   
/*     */   public boolean func_178505_b(RenderChunk p_178505_1_) {
/*     */     boolean var3;
/* 133 */     p_178505_1_.func_178579_c().lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 138 */       ChunkCompileTaskGenerator var2 = p_178505_1_.func_178574_d();
/*     */ 
/*     */       
/*     */       try {
/* 142 */         this.field_178525_i.func_178474_a(var2);
/*     */       }
/* 144 */       catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 149 */       var3 = true;
/*     */     }
/*     */     finally {
/*     */       
/* 153 */       p_178505_1_.func_178579_c().unlock();
/*     */     } 
/*     */     
/* 156 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178514_b() {
/* 161 */     func_178513_e(); do {
/*     */     
/* 163 */     } while (func_178516_a(0L));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     ArrayList<RegionRenderCacheBuilder> var1 = Lists.newArrayList();
/*     */     
/* 170 */     while (var1.size() != 5) {
/*     */ 
/*     */       
/*     */       try {
/* 174 */         var1.add(func_178515_c());
/*     */       }
/* 176 */       catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     this.field_178520_e.addAll(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178512_a(RegionRenderCacheBuilder p_178512_1_) {
/* 187 */     this.field_178520_e.add(p_178512_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public RegionRenderCacheBuilder func_178515_c() throws InterruptedException, InterruptedException {
/* 192 */     return this.field_178520_e.take();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkCompileTaskGenerator func_178511_d() throws InterruptedException, InterruptedException {
/* 197 */     return this.field_178519_d.take();
/*     */   }
/*     */   
/*     */   public boolean func_178509_c(RenderChunk p_178509_1_) {
/*     */     boolean var4;
/* 202 */     p_178509_1_.func_178579_c().lock();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     try { final ChunkCompileTaskGenerator var2 = p_178509_1_.func_178582_e();
/*     */ 
/*     */       
/* 210 */       if (var2 == null) {
/*     */         
/* 212 */         boolean bool = true;
/* 213 */         return bool;
/*     */       } 
/*     */       
/* 216 */       var2.func_178539_a(new Runnable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002461";
/*     */             
/*     */             public void run() {
/* 221 */               ChunkRenderDispatcher.this.field_178519_d.remove(var2);
/*     */             }
/*     */           });
/* 224 */       boolean var3 = this.field_178519_d.offer(var2);
/*     */        }
/*     */     
/*     */     finally
/*     */     
/* 229 */     { p_178509_1_.func_178579_c().unlock(); }  p_178509_1_.func_178579_c().unlock();
/*     */ 
/*     */     
/* 232 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public ListenableFuture func_178503_a(final EnumWorldBlockLayer p_178503_1_, final WorldRenderer p_178503_2_, final RenderChunk p_178503_3_, final CompiledChunk p_178503_4_) {
/* 237 */     if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
/*     */       
/* 239 */       if (OpenGlHelper.func_176075_f()) {
/*     */         
/* 241 */         func_178506_a(p_178503_2_, p_178503_3_.func_178565_b(p_178503_1_.ordinal()));
/*     */       }
/*     */       else {
/*     */         
/* 245 */         func_178510_a(p_178503_2_, ((ListedRenderChunk)p_178503_3_).func_178600_a(p_178503_1_, p_178503_4_), p_178503_3_);
/*     */       } 
/*     */       
/* 248 */       p_178503_2_.setTranslation(0.0D, 0.0D, 0.0D);
/* 249 */       return Futures.immediateFuture(null);
/*     */     } 
/*     */ 
/*     */     
/* 253 */     ListenableFutureTask var5 = ListenableFutureTask.create(new Runnable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002460";
/*     */           
/*     */           public void run() {
/* 258 */             ChunkRenderDispatcher.this.func_178503_a(p_178503_1_, p_178503_2_, p_178503_3_, p_178503_4_);
/*     */           }
/* 260 */         }null);
/* 261 */     Queue var6 = this.field_178524_h;
/* 262 */     Queue var7 = this.field_178524_h;
/*     */     
/* 264 */     synchronized (this.field_178524_h) {
/*     */       
/* 266 */       this.field_178524_h.add(var5);
/* 267 */       return (ListenableFuture)var5;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_178510_a(WorldRenderer p_178510_1_, int p_178510_2_, RenderChunk p_178510_3_) {
/* 274 */     GL11.glNewList(p_178510_2_, 4864);
/* 275 */     GlStateManager.pushMatrix();
/* 276 */     p_178510_3_.func_178572_f();
/* 277 */     this.field_178517_f.draw(p_178510_1_, p_178510_1_.func_178976_e());
/* 278 */     GlStateManager.popMatrix();
/* 279 */     GL11.glEndList();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178506_a(WorldRenderer p_178506_1_, VertexBuffer p_178506_2_) {
/* 284 */     this.field_178518_g.func_178178_a(p_178506_2_);
/* 285 */     this.field_178518_g.draw(p_178506_1_, p_178506_1_.func_178976_e());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178513_e() {
/* 290 */     while (!this.field_178519_d.isEmpty()) {
/*     */       
/* 292 */       ChunkCompileTaskGenerator task = this.field_178519_d.poll();
/*     */       
/* 294 */       if (task != null)
/*     */       {
/* 296 */         task.func_178542_e();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\chunk\ChunkRenderDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */