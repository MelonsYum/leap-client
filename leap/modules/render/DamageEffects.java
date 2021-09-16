/*    */ package leap.modules.render;
/*    */ 
/*    */ import io.netty.util.internal.ThreadLocalRandom;
/*    */ import java.util.ArrayList;
/*    */ import leap.Client;
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.modules.combat.KillAura;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageEffects
/*    */   extends Module
/*    */ {
/* 30 */   private ArrayList<hit> hits = new ArrayList<>();
/*    */   private float lastHealth;
/* 32 */   private EntityLivingBase lastTarget = null;
/*    */   
/* 34 */   public static NumberSetting amount = new NumberSetting("Amount", 15.0D, 1.0D, 20.0D, 1.0D);
/* 35 */   public static ModeSetting type = new ModeSetting("Type", "None", new String[] { "None", "Crit", "Enchant" });
/* 36 */   public static BooleanSetting particles = new BooleanSetting("More Particles", true);
/* 37 */   public static BooleanSetting health = new BooleanSetting("Health Particles", true);
/*    */   
/*    */   public DamageEffects() {
/* 40 */     super("Damage Effects", 0, Module.Category.RENDER);
/* 41 */     addSettings(new Setting[] { (Setting)amount, (Setting)type, (Setting)particles });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 45 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 49 */     super.onDisable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEvent(Event e) {
/* 54 */     if (e instanceof leap.events.listeners.EventUpdate && 
/* 55 */       health.isEnabled() && 
/* 56 */       isEnabled()) {
/* 57 */       if (Client.getModule("KillAura").isEnabled() && 
/* 58 */         KillAura.targets != null && 
/* 59 */         !KillAura.targets.isEmpty()) {
/* 60 */         EntityLivingBase target = KillAura.targets.get(0);
/*    */         
/* 62 */         if (target == null) {
/* 63 */           this.lastHealth = 20.0F;
/* 64 */           this.lastTarget = null;
/*    */           
/*    */           return;
/*    */         } 
/* 68 */         if (this.lastTarget == null || target != this.lastTarget) {
/* 69 */           this.lastTarget = target;
/* 70 */           this.lastHealth = target.getHealth();
/*    */           
/*    */           return;
/*    */         } 
/* 74 */         if (target.hurtTime != 0) {
/* 75 */           mc.effectRenderer.func_178926_a((Entity)target, EnumParticleTypes.CRIT);
/*    */         }
/*    */         
/* 78 */         if (target.getHealth() != this.lastHealth && 
/* 79 */           target.getHealth() < this.lastHealth) {
/* 80 */           this.hits.add(new hit(target.getPosition().add(ThreadLocalRandom.current().nextDouble(-0.5D, 0.5D), ThreadLocalRandom.current().nextDouble(1.0D, 1.5D), ThreadLocalRandom.current().nextDouble(-0.5D, 0.5D)), (this.lastHealth - target.getHealth()), false));
/* 81 */           this.lastHealth = target.getHealth();
/*    */         } 
/*    */         
/* 84 */         if (target.getHealth() == 0.0F) {
/* 85 */           this.hits.add(new hit(target.getPosition().add(ThreadLocalRandom.current().nextDouble(-0.5D, 0.5D), ThreadLocalRandom.current().nextDouble(1.0D, 1.5D), ThreadLocalRandom.current().nextDouble(-0.5D, 0.5D)), (this.lastHealth - target.getHealth()), true));
/*    */         }
/*    */       } 
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 93 */     if (e instanceof leap.events.listeners.EventRenderGUI)
/*    */       try {
/* 95 */         for (hit h : this.hits) {
/* 96 */           if (h.isFinished()) {
/* 97 */             this.hits.remove(h); continue;
/*    */           } 
/* 99 */           h.onRender();
/*    */         }
/*    */       
/* :2 */       } catch (Exception exception) {} 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\DamageEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */