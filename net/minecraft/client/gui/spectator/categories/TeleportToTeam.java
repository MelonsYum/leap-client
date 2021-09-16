/*     */ package net.minecraft.client.gui.spectator.categories;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiSpectator;
/*     */ import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
/*     */ import net.minecraft.client.gui.spectator.ISpectatorMenuView;
/*     */ import net.minecraft.client.gui.spectator.SpectatorMenu;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class TeleportToTeam
/*     */   implements ISpectatorMenuView, ISpectatorMenuObject {
/*  26 */   private final List field_178672_a = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00001920";
/*     */   
/*     */   public TeleportToTeam() {
/*  31 */     Minecraft var1 = Minecraft.getMinecraft();
/*  32 */     Iterator<ScorePlayerTeam> var2 = var1.theWorld.getScoreboard().getTeams().iterator();
/*     */     
/*  34 */     while (var2.hasNext()) {
/*     */       
/*  36 */       ScorePlayerTeam var3 = var2.next();
/*  37 */       this.field_178672_a.add(new TeamSelectionObject(var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_178669_a() {
/*  43 */     return this.field_178672_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent func_178670_b() {
/*  48 */     return (IChatComponent)new ChatComponentText("Select a team to teleport to");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178661_a(SpectatorMenu p_178661_1_) {
/*  53 */     p_178661_1_.func_178647_a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent func_178664_z_() {
/*  58 */     return (IChatComponent)new ChatComponentText("Teleport to team member");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178663_a(float p_178663_1_, int p_178663_2_) {
/*  63 */     Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
/*  64 */     Gui.drawModalRectWithCustomSizedTexture(0.0F, 0.0F, 16.0F, 0.0F, 16.0F, 16.0F, 256.0F, 256.0F);
/*     */   }
/*     */   
/*     */   public boolean func_178662_A_() {
/*     */     ISpectatorMenuObject var2;
/*  69 */     Iterator<ISpectatorMenuObject> var1 = this.field_178672_a.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  74 */       if (!var1.hasNext())
/*     */       {
/*  76 */         return false;
/*     */       }
/*     */       
/*  79 */       var2 = var1.next();
/*     */     }
/*  81 */     while (!var2.func_178662_A_());
/*     */     
/*  83 */     return true;
/*     */   }
/*     */   
/*     */   class TeamSelectionObject
/*     */     implements ISpectatorMenuObject
/*     */   {
/*     */     private final ScorePlayerTeam field_178676_b;
/*     */     private final ResourceLocation field_178677_c;
/*     */     private final List field_178675_d;
/*     */     private static final String __OBFID = "CL_00001919";
/*     */     
/*     */     public TeamSelectionObject(ScorePlayerTeam p_i45492_2_) {
/*  95 */       this.field_178676_b = p_i45492_2_;
/*  96 */       this.field_178675_d = Lists.newArrayList();
/*  97 */       Iterator<String> var3 = p_i45492_2_.getMembershipCollection().iterator();
/*     */       
/*  99 */       while (var3.hasNext()) {
/*     */         
/* 101 */         String var4 = var3.next();
/* 102 */         NetworkPlayerInfo var5 = Minecraft.getMinecraft().getNetHandler().func_175104_a(var4);
/*     */         
/* 104 */         if (var5 != null)
/*     */         {
/* 106 */           this.field_178675_d.add(var5);
/*     */         }
/*     */       } 
/*     */       
/* 110 */       if (!this.field_178675_d.isEmpty()) {
/*     */         
/* 112 */         String var6 = ((NetworkPlayerInfo)this.field_178675_d.get((new Random()).nextInt(this.field_178675_d.size()))).func_178845_a().getName();
/* 113 */         this.field_178677_c = AbstractClientPlayer.getLocationSkin(var6);
/* 114 */         AbstractClientPlayer.getDownloadImageSkin(this.field_178677_c, var6);
/*     */       }
/*     */       else {
/*     */         
/* 118 */         this.field_178677_c = DefaultPlayerSkin.func_177335_a();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_178661_a(SpectatorMenu p_178661_1_) {
/* 124 */       p_178661_1_.func_178647_a(new TeleportToPlayer(this.field_178675_d));
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatComponent func_178664_z_() {
/* 129 */       return (IChatComponent)new ChatComponentText(this.field_178676_b.func_96669_c());
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_178663_a(float p_178663_1_, int p_178663_2_) {
/* 134 */       int var3 = -1;
/* 135 */       String var4 = FontRenderer.getFormatFromString(this.field_178676_b.getColorPrefix());
/*     */       
/* 137 */       if (var4.length() >= 2)
/*     */       {
/* 139 */         var3 = (Minecraft.getMinecraft()).fontRendererObj.func_175064_b(var4.charAt(1));
/*     */       }
/*     */       
/* 142 */       if (var3 >= 0) {
/*     */         
/* 144 */         float var5 = (var3 >> 16 & 0xFF) / 255.0F;
/* 145 */         float var6 = (var3 >> 8 & 0xFF) / 255.0F;
/* 146 */         float var7 = (var3 & 0xFF) / 255.0F;
/* 147 */         Gui.drawRect(1.0D, 1.0D, 15.0D, 15.0D, MathHelper.func_180183_b(var5 * p_178663_1_, var6 * p_178663_1_, var7 * p_178663_1_) | p_178663_2_ << 24);
/*     */       } 
/*     */       
/* 150 */       Minecraft.getMinecraft().getTextureManager().bindTexture(this.field_178677_c);
/* 151 */       GlStateManager.color(p_178663_1_, p_178663_1_, p_178663_1_, p_178663_2_ / 255.0F);
/* 152 */       Gui.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
/* 153 */       Gui.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_178662_A_() {
/* 158 */       return !this.field_178675_d.isEmpty();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\spectator\categories\TeleportToTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */