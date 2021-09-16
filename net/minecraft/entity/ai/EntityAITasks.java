/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class EntityAITasks
/*     */ {
/*  12 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*  15 */   private List taskEntries = Lists.newArrayList();
/*     */ 
/*     */   
/*  18 */   private List executingTaskEntries = Lists.newArrayList();
/*     */   
/*     */   private final Profiler theProfiler;
/*     */   
/*     */   private int tickCount;
/*  23 */   private int tickRate = 3;
/*     */   
/*     */   private static final String __OBFID = "CL_00001588";
/*     */   
/*     */   public EntityAITasks(Profiler p_i1628_1_) {
/*  28 */     this.theProfiler = p_i1628_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTask(int p_75776_1_, EntityAIBase p_75776_2_) {
/*  36 */     this.taskEntries.add(new EntityAITaskEntry(p_75776_1_, p_75776_2_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTask(EntityAIBase p_85156_1_) {
/*  44 */     Iterator<EntityAITaskEntry> var2 = this.taskEntries.iterator();
/*     */     
/*  46 */     while (var2.hasNext()) {
/*     */       
/*  48 */       EntityAITaskEntry var3 = var2.next();
/*  49 */       EntityAIBase var4 = var3.action;
/*     */       
/*  51 */       if (var4 == p_85156_1_) {
/*     */         
/*  53 */         if (this.executingTaskEntries.contains(var3)) {
/*     */           
/*  55 */           var4.resetTask();
/*  56 */           this.executingTaskEntries.remove(var3);
/*     */         } 
/*     */         
/*  59 */         var2.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateTasks() {
/*  66 */     this.theProfiler.startSection("goalSetup");
/*     */ 
/*     */ 
/*     */     
/*  70 */     if (this.tickCount++ % this.tickRate == 0) {
/*     */       
/*  72 */       Iterator<EntityAITaskEntry> var1 = this.taskEntries.iterator();
/*     */       
/*  74 */       while (var1.hasNext()) {
/*     */         
/*  76 */         EntityAITaskEntry var2 = var1.next();
/*  77 */         boolean var3 = this.executingTaskEntries.contains(var2);
/*     */         
/*  79 */         if (var3) {
/*     */           
/*  81 */           if (canUse(var2) && canContinue(var2)) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */           
/*  86 */           var2.action.resetTask();
/*  87 */           this.executingTaskEntries.remove(var2);
/*     */         } 
/*     */         
/*  90 */         if (canUse(var2) && var2.action.shouldExecute())
/*     */         {
/*  92 */           var2.action.startExecuting();
/*  93 */           this.executingTaskEntries.add(var2);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/*  99 */       Iterator<EntityAITaskEntry> var1 = this.executingTaskEntries.iterator();
/*     */       
/* 101 */       while (var1.hasNext()) {
/*     */         
/* 103 */         EntityAITaskEntry var2 = var1.next();
/*     */         
/* 105 */         if (!canContinue(var2)) {
/*     */           
/* 107 */           var2.action.resetTask();
/* 108 */           var1.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     this.theProfiler.endSection();
/* 114 */     this.theProfiler.startSection("goalTick");
/* 115 */     Iterator<EntityAITaskEntry> iterator = this.executingTaskEntries.iterator();
/*     */     
/* 117 */     while (iterator.hasNext()) {
/*     */       
/* 119 */       EntityAITaskEntry var2 = iterator.next();
/* 120 */       var2.action.updateTask();
/*     */     } 
/*     */     
/* 123 */     this.theProfiler.endSection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canContinue(EntityAITaskEntry p_75773_1_) {
/* 131 */     boolean var2 = p_75773_1_.action.continueExecuting();
/* 132 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canUse(EntityAITaskEntry p_75775_1_) {
/* 141 */     Iterator<EntityAITaskEntry> var2 = this.taskEntries.iterator();
/*     */     
/* 143 */     while (var2.hasNext()) {
/*     */       
/* 145 */       EntityAITaskEntry var3 = var2.next();
/*     */       
/* 147 */       if (var3 != p_75775_1_) {
/*     */         
/* 149 */         if (p_75775_1_.priority >= var3.priority) {
/*     */           
/* 151 */           if (!areTasksCompatible(p_75775_1_, var3) && this.executingTaskEntries.contains(var3))
/*     */           {
/* 153 */             return false; } 
/*     */           continue;
/*     */         } 
/* 156 */         if (!var3.action.isInterruptible() && this.executingTaskEntries.contains(var3))
/*     */         {
/* 158 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean areTasksCompatible(EntityAITaskEntry p_75777_1_, EntityAITaskEntry p_75777_2_) {
/* 171 */     return ((p_75777_1_.action.getMutexBits() & p_75777_2_.action.getMutexBits()) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   class EntityAITaskEntry
/*     */   {
/*     */     public EntityAIBase action;
/*     */     public int priority;
/*     */     private static final String __OBFID = "CL_00001589";
/*     */     
/*     */     public EntityAITaskEntry(int p_i1627_2_, EntityAIBase p_i1627_3_) {
/* 182 */       this.priority = p_i1627_2_;
/* 183 */       this.action = p_i1627_3_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAITasks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */