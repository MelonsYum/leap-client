/*    */ package net.minecraft.client.renderer.tileentity;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTUtil;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityBanner;
/*    */ import net.minecraft.tileentity.TileEntityChest;
/*    */ import net.minecraft.tileentity.TileEntityEnderChest;
/*    */ import net.minecraft.tileentity.TileEntitySkull;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class TileEntityRendererChestHelper {
/* 20 */   public static TileEntityRendererChestHelper instance = new TileEntityRendererChestHelper();
/* 21 */   private TileEntityChest field_147717_b = new TileEntityChest(0);
/* 22 */   private TileEntityChest field_147718_c = new TileEntityChest(1);
/* 23 */   private TileEntityEnderChest field_147716_d = new TileEntityEnderChest();
/* 24 */   private TileEntityBanner banner = new TileEntityBanner();
/* 25 */   private TileEntitySkull field_179023_f = new TileEntitySkull();
/*    */   
/*    */   private static final String __OBFID = "CL_00000946";
/*    */   
/*    */   public void renderByItem(ItemStack p_179022_1_) {
/* 30 */     if (p_179022_1_.getItem() == Items.banner) {
/*    */       
/* 32 */       this.banner.setItemValues(p_179022_1_);
/* 33 */       TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)this.banner, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 35 */     else if (p_179022_1_.getItem() == Items.skull) {
/*    */       
/* 37 */       GameProfile var2 = null;
/*    */       
/* 39 */       if (p_179022_1_.hasTagCompound()) {
/*    */         
/* 41 */         NBTTagCompound var3 = p_179022_1_.getTagCompound();
/*    */         
/* 43 */         if (var3.hasKey("SkullOwner", 10)) {
/*    */           
/* 45 */           var2 = NBTUtil.readGameProfileFromNBT(var3.getCompoundTag("SkullOwner"));
/*    */         }
/* 47 */         else if (var3.hasKey("SkullOwner", 8) && var3.getString("SkullOwner").length() > 0) {
/*    */           
/* 49 */           var2 = new GameProfile(null, var3.getString("SkullOwner"));
/* 50 */           var2 = TileEntitySkull.updateGameprofile(var2);
/* 51 */           var3.removeTag("SkullOwner");
/* 52 */           var3.setTag("SkullOwner", (NBTBase)NBTUtil.writeGameProfile(new NBTTagCompound(), var2));
/*    */         } 
/*    */       } 
/*    */       
/* 56 */       if (TileEntitySkullRenderer.instance != null)
/*    */       {
/* 58 */         GlStateManager.pushMatrix();
/* 59 */         GlStateManager.translate(-0.5F, 0.0F, -0.5F);
/* 60 */         GlStateManager.scale(2.0F, 2.0F, 2.0F);
/* 61 */         GlStateManager.disableCull();
/* 62 */         TileEntitySkullRenderer.instance.renderSkull(0.0F, 0.0F, 0.0F, EnumFacing.UP, 0.0F, p_179022_1_.getMetadata(), var2, -1);
/* 63 */         GlStateManager.enableCull();
/* 64 */         GlStateManager.popMatrix();
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 69 */       Block var4 = Block.getBlockFromItem(p_179022_1_.getItem());
/*    */       
/* 71 */       if (var4 == Blocks.ender_chest) {
/*    */         
/* 73 */         TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)this.field_147716_d, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */       }
/* 75 */       else if (var4 == Blocks.trapped_chest) {
/*    */         
/* 77 */         TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)this.field_147718_c, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */       }
/*    */       else {
/*    */         
/* 81 */         TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)this.field_147717_b, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityRendererChestHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */