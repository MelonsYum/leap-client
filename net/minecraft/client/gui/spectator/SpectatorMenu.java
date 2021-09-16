/*     */ package net.minecraft.client.gui.spectator;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiSpectator;
/*     */ import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class SpectatorMenu
/*     */ {
/*  16 */   private static final ISpectatorMenuObject field_178655_b = new EndSpectatorObject(null);
/*  17 */   private static final ISpectatorMenuObject field_178656_c = new MoveMenuObject(-1, true);
/*  18 */   private static final ISpectatorMenuObject field_178653_d = new MoveMenuObject(1, true);
/*  19 */   private static final ISpectatorMenuObject field_178654_e = new MoveMenuObject(1, false);
/*  20 */   public static final ISpectatorMenuObject field_178657_a = new ISpectatorMenuObject() {
/*     */       private static final String __OBFID = "CL_00001926";
/*     */       
/*     */       public void func_178661_a(SpectatorMenu p_178661_1_) {}
/*     */       
/*     */       public IChatComponent func_178664_z_() {
/*  26 */         return (IChatComponent)new ChatComponentText("");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean func_178662_A_() {
/*  31 */         return false;
/*     */       }
/*     */       public void func_178663_a(float p_178663_1_, int p_178663_2_) {}
/*     */     }; private final ISpectatorMenuReciepient field_178651_f;
/*  35 */   private final List field_178652_g = Lists.newArrayList();
/*  36 */   private ISpectatorMenuView field_178659_h = new BaseSpectatorGroup();
/*  37 */   private int field_178660_i = -1;
/*     */   
/*     */   private int field_178658_j;
/*     */   private static final String __OBFID = "CL_00001927";
/*     */   
/*     */   public SpectatorMenu(ISpectatorMenuReciepient p_i45497_1_) {
/*  43 */     this.field_178651_f = p_i45497_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public ISpectatorMenuObject func_178643_a(int p_178643_1_) {
/*  48 */     int var2 = p_178643_1_ + this.field_178658_j * 6;
/*  49 */     return (this.field_178658_j > 0 && p_178643_1_ == 0) ? field_178656_c : ((p_178643_1_ == 7) ? ((var2 < this.field_178659_h.func_178669_a().size()) ? field_178653_d : field_178654_e) : ((p_178643_1_ == 8) ? field_178655_b : ((var2 >= 0 && var2 < this.field_178659_h.func_178669_a().size()) ? (ISpectatorMenuObject)Objects.firstNonNull(this.field_178659_h.func_178669_a().get(var2), field_178657_a) : field_178657_a)));
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_178642_a() {
/*  54 */     ArrayList<ISpectatorMenuObject> var1 = Lists.newArrayList();
/*     */     
/*  56 */     for (int var2 = 0; var2 <= 8; var2++)
/*     */     {
/*  58 */       var1.add(func_178643_a(var2));
/*     */     }
/*     */     
/*  61 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ISpectatorMenuObject func_178645_b() {
/*  66 */     return func_178643_a(this.field_178660_i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ISpectatorMenuView func_178650_c() {
/*  71 */     return this.field_178659_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178644_b(int p_178644_1_) {
/*  76 */     ISpectatorMenuObject var2 = func_178643_a(p_178644_1_);
/*     */     
/*  78 */     if (var2 != field_178657_a)
/*     */     {
/*  80 */       if (this.field_178660_i == p_178644_1_ && var2.func_178662_A_()) {
/*     */         
/*  82 */         var2.func_178661_a(this);
/*     */       }
/*     */       else {
/*     */         
/*  86 */         this.field_178660_i = p_178644_1_;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178641_d() {
/*  93 */     this.field_178651_f.func_175257_a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178648_e() {
/*  98 */     return this.field_178660_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178647_a(ISpectatorMenuView p_178647_1_) {
/* 103 */     this.field_178652_g.add(func_178646_f());
/* 104 */     this.field_178659_h = p_178647_1_;
/* 105 */     this.field_178660_i = -1;
/* 106 */     this.field_178658_j = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public SpectatorDetails func_178646_f() {
/* 111 */     return new SpectatorDetails(this.field_178659_h, func_178642_a(), this.field_178660_i);
/*     */   }
/*     */   
/*     */   static class EndSpectatorObject
/*     */     implements ISpectatorMenuObject
/*     */   {
/*     */     private static final String __OBFID = "CL_00001925";
/*     */     
/*     */     private EndSpectatorObject() {}
/*     */     
/*     */     public void func_178661_a(SpectatorMenu p_178661_1_) {
/* 122 */       p_178661_1_.func_178641_d();
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatComponent func_178664_z_() {
/* 127 */       return (IChatComponent)new ChatComponentText("Close menu");
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_178663_a(float p_178663_1_, int p_178663_2_) {
/* 132 */       Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
/* 133 */       Gui.drawModalRectWithCustomSizedTexture(0.0F, 0.0F, 128.0F, 0.0F, 16.0F, 16.0F, 256.0F, 256.0F);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_178662_A_() {
/* 138 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     EndSpectatorObject(Object p_i45496_1_) {
/* 143 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */   static class MoveMenuObject
/*     */     implements ISpectatorMenuObject
/*     */   {
/*     */     private final int field_178666_a;
/*     */     private final boolean field_178665_b;
/*     */     private static final String __OBFID = "CL_00001924";
/*     */     
/*     */     public MoveMenuObject(int p_i45495_1_, boolean p_i45495_2_) {
/* 155 */       this.field_178666_a = p_i45495_1_;
/* 156 */       this.field_178665_b = p_i45495_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_178661_a(SpectatorMenu p_178661_1_) {
/* 161 */       p_178661_1_.field_178658_j = this.field_178666_a;
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatComponent func_178664_z_() {
/* 166 */       return (this.field_178666_a < 0) ? (IChatComponent)new ChatComponentText("Previous Page") : (IChatComponent)new ChatComponentText("Next Page");
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_178663_a(float p_178663_1_, int p_178663_2_) {
/* 171 */       Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
/*     */       
/* 173 */       if (this.field_178666_a < 0) {
/*     */         
/* 175 */         Gui.drawModalRectWithCustomSizedTexture(0.0F, 0.0F, 144.0F, 0.0F, 16.0F, 16.0F, 256.0F, 256.0F);
/*     */       }
/*     */       else {
/*     */         
/* 179 */         Gui.drawModalRectWithCustomSizedTexture(0.0F, 0.0F, 160.0F, 0.0F, 16.0F, 16.0F, 256.0F, 256.0F);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_178662_A_() {
/* 185 */       return this.field_178665_b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\spectator\SpectatorMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */