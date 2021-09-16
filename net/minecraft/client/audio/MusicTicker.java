/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class MusicTicker
/*    */   implements IUpdatePlayerListBox {
/* 11 */   private final Random rand = new Random();
/*    */   private final Minecraft mc;
/*    */   private ISound currentMusic;
/* 14 */   private int timeUntilNextMusic = 100;
/*    */   
/*    */   private static final String __OBFID = "CL_00001138";
/*    */   
/*    */   public MusicTicker(Minecraft mcIn) {
/* 19 */     this.mc = mcIn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     MusicType var1 = this.mc.getAmbientMusicType();
/*    */     
/* 29 */     if (this.currentMusic != null) {
/*    */       
/* 31 */       if (!var1.getMusicLocation().equals(this.currentMusic.getSoundLocation())) {
/*    */         
/* 33 */         this.mc.getSoundHandler().stopSound(this.currentMusic);
/* 34 */         this.timeUntilNextMusic = MathHelper.getRandomIntegerInRange(this.rand, 0, var1.getMinDelay() / 2);
/*    */       } 
/*    */       
/* 37 */       if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic)) {
/*    */         
/* 39 */         this.currentMusic = null;
/* 40 */         this.timeUntilNextMusic = Math.min(MathHelper.getRandomIntegerInRange(this.rand, var1.getMinDelay(), var1.getMaxDelay()), this.timeUntilNextMusic);
/*    */       } 
/*    */     } 
/*    */     
/* 44 */     if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
/*    */       
/* 46 */       this.currentMusic = PositionedSoundRecord.createPositionedSoundRecord(var1.getMusicLocation());
/* 47 */       this.mc.getSoundHandler().playSound(this.currentMusic);
/* 48 */       this.timeUntilNextMusic = Integer.MAX_VALUE;
/*    */     } 
/*    */   }
/*    */   
/*    */   public enum MusicType
/*    */   {
/* 54 */     MENU("MENU", 0, (String)new ResourceLocation("minecraft:music.menu"), 20, 600),
/* 55 */     GAME("GAME", 1, (String)new ResourceLocation("minecraft:music.game"), 12000, 24000),
/* 56 */     CREATIVE("CREATIVE", 2, (String)new ResourceLocation("minecraft:music.game.creative"), 1200, 3600),
/* 57 */     CREDITS("CREDITS", 3, (String)new ResourceLocation("minecraft:music.game.end.credits"), 2147483647, 2147483647),
/* 58 */     NETHER("NETHER", 4, (String)new ResourceLocation("minecraft:music.game.nether"), 1200, 3600),
/* 59 */     END_BOSS("END_BOSS", 5, (String)new ResourceLocation("minecraft:music.game.end.dragon"), 0, 0),
/* 60 */     END("END", 6, (String)new ResourceLocation("minecraft:music.game.end"), 6000, 24000);
/*    */     
/*    */     private final ResourceLocation musicLocation;
/*    */     private final int minDelay;
/*    */     private final int maxDelay;
/* 65 */     private static final MusicType[] $VALUES = new MusicType[] { MENU, GAME, CREATIVE, CREDITS, NETHER, END_BOSS, END };
/*    */     
/*    */     private static final String __OBFID = "CL_00001139";
/*    */     
/*    */     MusicType(String p_i45111_1_, int p_i45111_2_, ResourceLocation location, int p_i45111_4_, int p_i45111_5_) {
/* 70 */       this.musicLocation = location;
/* 71 */       this.minDelay = p_i45111_4_;
/* 72 */       this.maxDelay = p_i45111_5_;
/*    */     } static {
/*    */     
/*    */     }
/*    */     public ResourceLocation getMusicLocation() {
/* 77 */       return this.musicLocation;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getMinDelay() {
/* 82 */       return this.minDelay;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getMaxDelay() {
/* 87 */       return this.maxDelay;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\MusicTicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */