/*    */ package net.minecraft.world;
/*    */ 
/*    */ import net.minecraft.profiler.Profiler;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.village.VillageCollection;
/*    */ import net.minecraft.world.border.IBorderListener;
/*    */ import net.minecraft.world.border.WorldBorder;
/*    */ import net.minecraft.world.storage.DerivedWorldInfo;
/*    */ import net.minecraft.world.storage.ISaveHandler;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ 
/*    */ public class WorldServerMulti
/*    */   extends WorldServer {
/*    */   private WorldServer delegate;
/*    */   private static final String __OBFID = "CL_00001430";
/*    */   
/*    */   public WorldServerMulti(MinecraftServer server, ISaveHandler saveHandlerIn, int dimensionId, WorldServer delegate, Profiler profilerIn) {
/* 18 */     super(server, saveHandlerIn, (WorldInfo)new DerivedWorldInfo(delegate.getWorldInfo()), dimensionId, profilerIn);
/* 19 */     this.delegate = delegate;
/* 20 */     delegate.getWorldBorder().addListener(new IBorderListener()
/*    */         {
/*    */           private static final String __OBFID = "CL_00002273";
/*    */           
/*    */           public void onSizeChanged(WorldBorder border, double newSize) {
/* 25 */             WorldServerMulti.this.getWorldBorder().setTransition(newSize);
/*    */           }
/*    */           
/*    */           public void func_177692_a(WorldBorder border, double p_177692_2_, double p_177692_4_, long p_177692_6_) {
/* 29 */             WorldServerMulti.this.getWorldBorder().setTransition(p_177692_2_, p_177692_4_, p_177692_6_);
/*    */           }
/*    */           
/*    */           public void onCenterChanged(WorldBorder border, double x, double z) {
/* 33 */             WorldServerMulti.this.getWorldBorder().setCenter(x, z);
/*    */           }
/*    */           
/*    */           public void onWarningTimeChanged(WorldBorder border, int p_177691_2_) {
/* 37 */             WorldServerMulti.this.getWorldBorder().setWarningTime(p_177691_2_);
/*    */           }
/*    */           
/*    */           public void onWarningDistanceChanged(WorldBorder border, int p_177690_2_) {
/* 41 */             WorldServerMulti.this.getWorldBorder().setWarningDistance(p_177690_2_);
/*    */           }
/*    */           
/*    */           public void func_177696_b(WorldBorder border, double p_177696_2_) {
/* 45 */             WorldServerMulti.this.getWorldBorder().func_177744_c(p_177696_2_);
/*    */           }
/*    */           
/*    */           public void func_177695_c(WorldBorder border, double p_177695_2_) {
/* 49 */             WorldServerMulti.this.getWorldBorder().setDamageBuffer(p_177695_2_);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void saveLevel() throws MinecraftException {}
/*    */ 
/*    */ 
/*    */   
/*    */   public World init() {
/* 61 */     this.mapStorage = this.delegate.func_175693_T();
/* 62 */     this.worldScoreboard = this.delegate.getScoreboard();
/* 63 */     String var1 = VillageCollection.func_176062_a(this.provider);
/* 64 */     VillageCollection var2 = (VillageCollection)this.mapStorage.loadData(VillageCollection.class, var1);
/*    */     
/* 66 */     if (var2 == null) {
/*    */       
/* 68 */       this.villageCollectionObj = new VillageCollection(this);
/* 69 */       this.mapStorage.setData(var1, (WorldSavedData)this.villageCollectionObj);
/*    */     }
/*    */     else {
/*    */       
/* 73 */       this.villageCollectionObj = var2;
/* 74 */       this.villageCollectionObj.func_82566_a(this);
/*    */     } 
/*    */     
/* 77 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldServerMulti.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */