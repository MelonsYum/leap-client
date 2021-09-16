/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiListExtended;
/*     */ import net.minecraft.client.gui.GuiScreenResourcePacks;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public abstract class ResourcePackListEntry
/*     */   implements GuiListExtended.IGuiListEntry {
/*  13 */   private static final ResourceLocation field_148316_c = new ResourceLocation("textures/gui/resource_packs.png");
/*     */   
/*     */   protected final Minecraft field_148317_a;
/*     */   protected final GuiScreenResourcePacks field_148315_b;
/*     */   private static final String __OBFID = "CL_00000821";
/*     */   
/*     */   public ResourcePackListEntry(GuiScreenResourcePacks p_i45051_1_) {
/*  20 */     this.field_148315_b = p_i45051_1_;
/*  21 */     this.field_148317_a = Minecraft.getMinecraft();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
/*  26 */     func_148313_c();
/*  27 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  28 */     Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32.0F, 32.0F, 32.0F, 32.0F);
/*     */ 
/*     */     
/*  31 */     if ((this.field_148317_a.gameSettings.touchscreen || isSelected) && func_148310_d()) {
/*     */       
/*  33 */       this.field_148317_a.getTextureManager().bindTexture(field_148316_c);
/*  34 */       Gui.drawRect(x, y, (x + 32), (y + 32), -1601138544);
/*  35 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  36 */       int var9 = mouseX - x;
/*  37 */       int i = mouseY - y;
/*     */       
/*  39 */       if (func_148309_e()) {
/*     */         
/*  41 */         if (var9 < 32)
/*     */         {
/*  43 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         }
/*     */         else
/*     */         {
/*  47 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  52 */         if (func_148308_f())
/*     */         {
/*  54 */           if (var9 < 16) {
/*     */             
/*  56 */             Gui.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */           }
/*     */           else {
/*     */             
/*  60 */             Gui.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */           } 
/*     */         }
/*     */         
/*  64 */         if (func_148314_g())
/*     */         {
/*  66 */           if (var9 < 32 && var9 > 16 && i < 16) {
/*     */             
/*  68 */             Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */           }
/*     */           else {
/*     */             
/*  72 */             Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */           } 
/*     */         }
/*     */         
/*  76 */         if (func_148307_h())
/*     */         {
/*  78 */           if (var9 < 32 && var9 > 16 && i > 16) {
/*     */             
/*  80 */             Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */           }
/*     */           else {
/*     */             
/*  84 */             Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     String var13 = func_148312_b();
/*  91 */     int var10 = this.field_148317_a.fontRendererObj.getStringWidth(var13);
/*     */     
/*  93 */     if (var10 > 157)
/*     */     {
/*  95 */       var13 = String.valueOf(this.field_148317_a.fontRendererObj.trimStringToWidth(var13, 157 - this.field_148317_a.fontRendererObj.getStringWidth("..."))) + "...";
/*     */     }
/*     */     
/*  98 */     this.field_148317_a.fontRendererObj.drawStringWithShadow(var13, (x + 32 + 2), (y + 1), 16777215);
/*  99 */     List<String> var11 = this.field_148317_a.fontRendererObj.listFormattedStringToWidth(func_148311_a(), 157);
/*     */     
/* 101 */     for (int var12 = 0; var12 < 2 && var12 < var11.size(); var12++)
/*     */     {
/* 103 */       this.field_148317_a.fontRendererObj.drawStringWithShadow(var11.get(var12), (x + 32 + 2), (y + 12 + 10 * var12), 8421504);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract String func_148311_a();
/*     */   
/*     */   protected abstract String func_148312_b();
/*     */   
/*     */   protected abstract void func_148313_c();
/*     */   
/*     */   protected boolean func_148310_d() {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_148309_e() {
/* 120 */     return !this.field_148315_b.hasResourcePackEntry(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_148308_f() {
/* 125 */     return this.field_148315_b.hasResourcePackEntry(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_148314_g() {
/* 130 */     List<ResourcePackListEntry> var1 = this.field_148315_b.func_146962_b(this);
/* 131 */     int var2 = var1.indexOf(this);
/* 132 */     return (var2 > 0 && ((ResourcePackListEntry)var1.get(var2 - 1)).func_148310_d());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_148307_h() {
/* 137 */     List<ResourcePackListEntry> var1 = this.field_148315_b.func_146962_b(this);
/* 138 */     int var2 = var1.indexOf(this);
/* 139 */     return (var2 >= 0 && var2 < var1.size() - 1 && ((ResourcePackListEntry)var1.get(var2 + 1)).func_148310_d());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
/* 147 */     if (func_148310_d() && p_148278_5_ <= 32) {
/*     */       
/* 149 */       if (func_148309_e()) {
/*     */         
/* 151 */         this.field_148315_b.func_146962_b(this).remove(this);
/* 152 */         this.field_148315_b.func_146963_h().add(0, this);
/* 153 */         this.field_148315_b.func_175288_g();
/* 154 */         return true;
/*     */       } 
/*     */       
/* 157 */       if (p_148278_5_ < 16 && func_148308_f()) {
/*     */         
/* 159 */         this.field_148315_b.func_146962_b(this).remove(this);
/* 160 */         this.field_148315_b.func_146964_g().add(0, this);
/* 161 */         this.field_148315_b.func_175288_g();
/* 162 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 168 */       if (p_148278_5_ > 16 && p_148278_6_ < 16 && func_148314_g()) {
/*     */         
/* 170 */         List<ResourcePackListEntry> var7 = this.field_148315_b.func_146962_b(this);
/* 171 */         int var8 = var7.indexOf(this);
/* 172 */         var7.remove(this);
/* 173 */         var7.add(var8 - 1, this);
/* 174 */         this.field_148315_b.func_175288_g();
/* 175 */         return true;
/*     */       } 
/*     */       
/* 178 */       if (p_148278_5_ > 16 && p_148278_6_ > 16 && func_148307_h()) {
/*     */         
/* 180 */         List<ResourcePackListEntry> var7 = this.field_148315_b.func_146962_b(this);
/* 181 */         int var8 = var7.indexOf(this);
/* 182 */         var7.remove(this);
/* 183 */         var7.add(var8 + 1, this);
/* 184 */         this.field_148315_b.func_175288_g();
/* 185 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
/*     */   
/*     */   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\ResourcePackListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */