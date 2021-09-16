/*    */ package net.minecraft.tileentity;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class TileEntityFlowerPot
/*    */   extends TileEntity
/*    */ {
/*    */   private Item flowerPotItem;
/*    */   private int flowerPotData;
/*    */   private static final String __OBFID = "CL_00000356";
/*    */   
/*    */   public TileEntityFlowerPot() {}
/*    */   
/*    */   public TileEntityFlowerPot(Item p_i45442_1_, int p_i45442_2_) {
/* 19 */     this.flowerPotItem = p_i45442_1_;
/* 20 */     this.flowerPotData = p_i45442_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound compound) {
/* 25 */     super.writeToNBT(compound);
/* 26 */     ResourceLocation var2 = (ResourceLocation)Item.itemRegistry.getNameForObject(this.flowerPotItem);
/* 27 */     compound.setString("Item", (var2 == null) ? "" : var2.toString());
/* 28 */     compound.setInteger("Data", this.flowerPotData);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound compound) {
/* 33 */     super.readFromNBT(compound);
/*    */     
/* 35 */     if (compound.hasKey("Item", 8)) {
/*    */       
/* 37 */       this.flowerPotItem = Item.getByNameOrId(compound.getString("Item"));
/*    */     }
/*    */     else {
/*    */       
/* 41 */       this.flowerPotItem = Item.getItemById(compound.getInteger("Item"));
/*    */     } 
/*    */     
/* 44 */     this.flowerPotData = compound.getInteger("Data");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet getDescriptionPacket() {
/* 52 */     NBTTagCompound var1 = new NBTTagCompound();
/* 53 */     writeToNBT(var1);
/* 54 */     var1.removeTag("Item");
/* 55 */     var1.setInteger("Item", Item.getIdFromItem(this.flowerPotItem));
/* 56 */     return (Packet)new S35PacketUpdateTileEntity(this.pos, 5, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_145964_a(Item p_145964_1_, int p_145964_2_) {
/* 61 */     this.flowerPotItem = p_145964_1_;
/* 62 */     this.flowerPotData = p_145964_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getFlowerPotItem() {
/* 67 */     return this.flowerPotItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFlowerPotData() {
/* 72 */     return this.flowerPotData;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */