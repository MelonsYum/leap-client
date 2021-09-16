/*     */ package net.minecraft.client.renderer.chunk;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import net.minecraft.client.renderer.RegionRenderCacheBuilder;
/*     */ 
/*     */ public class ChunkCompileTaskGenerator
/*     */ {
/*     */   private final RenderChunk field_178553_a;
/*  12 */   private final ReentrantLock field_178551_b = new ReentrantLock();
/*  13 */   private final List field_178552_c = Lists.newArrayList();
/*     */   
/*     */   private final Type field_178549_d;
/*     */   private RegionRenderCacheBuilder field_178550_e;
/*     */   private CompiledChunk field_178547_f;
/*     */   private Status field_178548_g;
/*     */   private boolean field_178554_h;
/*     */   private static final String __OBFID = "CL_00002466";
/*     */   
/*     */   public ChunkCompileTaskGenerator(RenderChunk p_i46208_1_, Type p_i46208_2_) {
/*  23 */     this.field_178548_g = Status.PENDING;
/*  24 */     this.field_178553_a = p_i46208_1_;
/*  25 */     this.field_178549_d = p_i46208_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public Status func_178546_a() {
/*  30 */     return this.field_178548_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderChunk func_178536_b() {
/*  35 */     return this.field_178553_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompiledChunk func_178544_c() {
/*  40 */     return this.field_178547_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178543_a(CompiledChunk p_178543_1_) {
/*  45 */     this.field_178547_f = p_178543_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public RegionRenderCacheBuilder func_178545_d() {
/*  50 */     return this.field_178550_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178541_a(RegionRenderCacheBuilder p_178541_1_) {
/*  55 */     this.field_178550_e = p_178541_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178535_a(Status p_178535_1_) {
/*  60 */     this.field_178551_b.lock();
/*     */ 
/*     */     
/*     */     try {
/*  64 */       this.field_178548_g = p_178535_1_;
/*     */     }
/*     */     finally {
/*     */       
/*  68 */       this.field_178551_b.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178542_e() {
/*  74 */     this.field_178551_b.lock();
/*     */ 
/*     */     
/*     */     try {
/*  78 */       if (this.field_178549_d == Type.REBUILD_CHUNK && this.field_178548_g != Status.DONE)
/*     */       {
/*  80 */         this.field_178553_a.func_178575_a(true);
/*     */       }
/*     */       
/*  83 */       this.field_178554_h = true;
/*  84 */       this.field_178548_g = Status.DONE;
/*  85 */       Iterator<Runnable> var1 = this.field_178552_c.iterator();
/*     */       
/*  87 */       while (var1.hasNext())
/*     */       {
/*  89 */         Runnable var2 = var1.next();
/*  90 */         var2.run();
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/*  95 */       this.field_178551_b.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178539_a(Runnable p_178539_1_) {
/* 101 */     this.field_178551_b.lock();
/*     */ 
/*     */     
/*     */     try {
/* 105 */       this.field_178552_c.add(p_178539_1_);
/*     */       
/* 107 */       if (this.field_178554_h)
/*     */       {
/* 109 */         p_178539_1_.run();
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 114 */       this.field_178551_b.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ReentrantLock func_178540_f() {
/* 120 */     return this.field_178551_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public Type func_178538_g() {
/* 125 */     return this.field_178549_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178537_h() {
/* 130 */     return this.field_178554_h;
/*     */   }
/*     */   
/*     */   public enum Status
/*     */   {
/* 135 */     PENDING("PENDING", 0, "PENDING", 0),
/* 136 */     COMPILING("COMPILING", 1, "COMPILING", 1),
/* 137 */     UPLOADING("UPLOADING", 2, "UPLOADING", 2),
/* 138 */     DONE("DONE", 3, "DONE", 3);
/* 139 */     private static final Status[] $VALUES = new Status[] { PENDING, COMPILING, UPLOADING, DONE };
/*     */     private static final String __OBFID = "CL_00002465";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Type {
/* 148 */     REBUILD_CHUNK("REBUILD_CHUNK", 0, "REBUILD_CHUNK", 0),
/* 149 */     RESORT_TRANSPARENCY("RESORT_TRANSPARENCY", 1, "RESORT_TRANSPARENCY", 1);
/* 150 */     private static final Type[] $VALUES = new Type[] { REBUILD_CHUNK, RESORT_TRANSPARENCY };
/*     */     private static final String __OBFID = "CL_00002464";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\chunk\ChunkCompileTaskGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */