/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.resources.ResourcePackListEntry;
/*     */ import net.minecraft.client.resources.ResourcePackListEntryDefault;
/*     */ import net.minecraft.client.resources.ResourcePackListEntryFound;
/*     */ import net.minecraft.client.resources.ResourcePackRepository;
/*     */ import net.minecraft.util.Util;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ public class GuiScreenResourcePacks
/*     */   extends GuiScreen {
/*  23 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private GuiScreen field_146965_f;
/*     */   private List field_146966_g;
/*     */   private List field_146969_h;
/*     */   private GuiResourcePackAvailable field_146970_i;
/*     */   private GuiResourcePackSelected field_146967_r;
/*     */   private boolean field_175289_s = false;
/*     */   private static final String __OBFID = "CL_00000820";
/*     */   
/*     */   public GuiScreenResourcePacks(GuiScreen p_i45050_1_) {
/*  34 */     this.field_146965_f = p_i45050_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  42 */     this.buttonList.add(new GuiOptionButton(2, width / 2 - 154, height - 48, I18n.format("resourcePack.openFolder", new Object[0])));
/*  43 */     this.buttonList.add(new GuiOptionButton(1, width / 2 + 4, height - 48, I18n.format("gui.done", new Object[0])));
/*  44 */     this.field_146966_g = Lists.newArrayList();
/*  45 */     this.field_146969_h = Lists.newArrayList();
/*  46 */     ResourcePackRepository var1 = this.mc.getResourcePackRepository();
/*  47 */     var1.updateRepositoryEntriesAll();
/*  48 */     ArrayList var2 = Lists.newArrayList(var1.getRepositoryEntriesAll());
/*  49 */     var2.removeAll(var1.getRepositoryEntries());
/*  50 */     Iterator<ResourcePackRepository.Entry> var3 = var2.iterator();
/*     */ 
/*     */     
/*  53 */     while (var3.hasNext()) {
/*     */       
/*  55 */       ResourcePackRepository.Entry var4 = var3.next();
/*  56 */       this.field_146966_g.add(new ResourcePackListEntryFound(this, var4));
/*     */     } 
/*     */     
/*  59 */     var3 = Lists.reverse(var1.getRepositoryEntries()).iterator();
/*     */     
/*  61 */     while (var3.hasNext()) {
/*     */       
/*  63 */       ResourcePackRepository.Entry var4 = var3.next();
/*  64 */       this.field_146969_h.add(new ResourcePackListEntryFound(this, var4));
/*     */     } 
/*     */     
/*  67 */     this.field_146969_h.add(new ResourcePackListEntryDefault(this));
/*  68 */     this.field_146970_i = new GuiResourcePackAvailable(this.mc, 200, height, this.field_146966_g);
/*  69 */     this.field_146970_i.setSlotXBoundsFromLeft(width / 2 - 4 - 200);
/*  70 */     this.field_146970_i.registerScrollButtons(7, 8);
/*  71 */     this.field_146967_r = new GuiResourcePackSelected(this.mc, 200, height, this.field_146969_h);
/*  72 */     this.field_146967_r.setSlotXBoundsFromLeft(width / 2 + 4);
/*  73 */     this.field_146967_r.registerScrollButtons(7, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  81 */     super.handleMouseInput();
/*  82 */     this.field_146967_r.func_178039_p();
/*  83 */     this.field_146970_i.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasResourcePackEntry(ResourcePackListEntry p_146961_1_) {
/*  88 */     return this.field_146969_h.contains(p_146961_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_146962_b(ResourcePackListEntry p_146962_1_) {
/*  93 */     return hasResourcePackEntry(p_146962_1_) ? this.field_146969_h : this.field_146966_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_146964_g() {
/*  98 */     return this.field_146966_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_146963_h() {
/* 103 */     return this.field_146969_h;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 108 */     if (button.enabled)
/*     */     {
/* 110 */       if (button.id == 2) {
/*     */         
/* 112 */         File var2 = this.mc.getResourcePackRepository().getDirResourcepacks();
/* 113 */         String var3 = var2.getAbsolutePath();
/*     */         
/* 115 */         if (Util.getOSType() == Util.EnumOS.OSX) {
/*     */ 
/*     */           
/*     */           try {
/* 119 */             logger.info(var3);
/* 120 */             Runtime.getRuntime().exec(new String[] { "/usr/bin/open", var3 });
/*     */             
/*     */             return;
/* 123 */           } catch (IOException var9) {
/*     */             
/* 125 */             logger.error("Couldn't open file", var9);
/*     */           }
/*     */         
/* 128 */         } else if (Util.getOSType() == Util.EnumOS.WINDOWS) {
/*     */           
/* 130 */           String var4 = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[] { var3 });
/*     */ 
/*     */           
/*     */           try {
/* 134 */             Runtime.getRuntime().exec(var4);
/*     */             
/*     */             return;
/* 137 */           } catch (IOException var8) {
/*     */             
/* 139 */             logger.error("Couldn't open file", var8);
/*     */           } 
/*     */         } 
/*     */         
/* 143 */         boolean var12 = false;
/*     */ 
/*     */         
/*     */         try {
/* 147 */           Class<?> var5 = Class.forName("java.awt.Desktop");
/* 148 */           Object var6 = var5.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 149 */           var5.getMethod("browse", new Class[] { URI.class }).invoke(var6, new Object[] { var2.toURI() });
/*     */         }
/* 151 */         catch (Throwable var7) {
/*     */           
/* 153 */           logger.error("Couldn't open link", var7);
/* 154 */           var12 = true;
/*     */         } 
/*     */         
/* 157 */         if (var12)
/*     */         {
/* 159 */           logger.info("Opening via system class!");
/* 160 */           Sys.openURL("file://" + var3);
/*     */         }
/*     */       
/* 163 */       } else if (button.id == 1) {
/*     */         
/* 165 */         if (this.field_175289_s) {
/*     */           
/* 167 */           ArrayList<ResourcePackRepository.Entry> var10 = Lists.newArrayList();
/* 168 */           Iterator<ResourcePackListEntry> var11 = this.field_146969_h.iterator();
/*     */           
/* 170 */           while (var11.hasNext()) {
/*     */             
/* 172 */             ResourcePackListEntry var13 = var11.next();
/*     */             
/* 174 */             if (var13 instanceof ResourcePackListEntryFound)
/*     */             {
/* 176 */               var10.add(((ResourcePackListEntryFound)var13).func_148318_i());
/*     */             }
/*     */           } 
/*     */           
/* 180 */           Collections.reverse(var10);
/* 181 */           this.mc.getResourcePackRepository().func_148527_a(var10);
/* 182 */           this.mc.gameSettings.resourcePacks.clear();
/* 183 */           var11 = (Iterator)var10.iterator();
/*     */           
/* 185 */           while (var11.hasNext()) {
/*     */             
/* 187 */             ResourcePackRepository.Entry var14 = (ResourcePackRepository.Entry)var11.next();
/* 188 */             this.mc.gameSettings.resourcePacks.add(var14.getResourcePackName());
/*     */           } 
/*     */           
/* 191 */           this.mc.gameSettings.saveOptions();
/* 192 */           this.mc.refreshResources();
/*     */         } 
/*     */         
/* 195 */         this.mc.displayGuiScreen(this.field_146965_f);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 205 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 206 */     this.field_146970_i.func_148179_a(mouseX, mouseY, mouseButton);
/* 207 */     this.field_146967_r.func_148179_a(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseReleased(int mouseX, int mouseY, int state) {
/* 215 */     super.mouseReleased(mouseX, mouseY, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 223 */     drawBackground(0);
/* 224 */     this.field_146970_i.drawScreen(mouseX, mouseY, partialTicks);
/* 225 */     this.field_146967_r.drawScreen(mouseX, mouseY, partialTicks);
/* 226 */     drawCenteredString(fontRendererObj, I18n.format("resourcePack.title", new Object[0]), (width / 2), 16.0F, 16777215);
/* 227 */     drawCenteredString(fontRendererObj, I18n.format("resourcePack.folderInfo", new Object[0]), (width / 2 - 77), (height - 26), 8421504);
/* 228 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175288_g() {
/* 233 */     this.field_175289_s = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenResourcePacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */