/*    */ package net.minecraft.client.main;
/*    */ 
/*    */ import com.mojang.authlib.properties.PropertyMap;
/*    */ import java.io.File;
/*    */ import java.net.Proxy;
/*    */ import net.minecraft.util.Session;
/*    */ 
/*    */ 
/*    */ public class GameConfiguration
/*    */ {
/*    */   public final UserInformation field_178745_a;
/*    */   public final DisplayInformation field_178743_b;
/*    */   public final FolderInformation field_178744_c;
/*    */   public final GameInformation field_178741_d;
/*    */   public final ServerInformation field_178742_e;
/*    */   private static final String __OBFID = "CL_00001918";
/*    */   
/*    */   public GameConfiguration(UserInformation p_i45491_1_, DisplayInformation p_i45491_2_, FolderInformation p_i45491_3_, GameInformation p_i45491_4_, ServerInformation p_i45491_5_) {
/* 19 */     this.field_178745_a = p_i45491_1_;
/* 20 */     this.field_178743_b = p_i45491_2_;
/* 21 */     this.field_178744_c = p_i45491_3_;
/* 22 */     this.field_178741_d = p_i45491_4_;
/* 23 */     this.field_178742_e = p_i45491_5_;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class DisplayInformation
/*    */   {
/*    */     public final int field_178764_a;
/*    */     public final int field_178762_b;
/*    */     public final boolean field_178763_c;
/*    */     public final boolean field_178761_d;
/*    */     private static final String __OBFID = "CL_00001917";
/*    */     
/*    */     public DisplayInformation(int p_i45490_1_, int p_i45490_2_, boolean p_i45490_3_, boolean p_i45490_4_) {
/* 36 */       this.field_178764_a = p_i45490_1_;
/* 37 */       this.field_178762_b = p_i45490_2_;
/* 38 */       this.field_178763_c = p_i45490_3_;
/* 39 */       this.field_178761_d = p_i45490_4_;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static class FolderInformation
/*    */   {
/*    */     public final File field_178760_a;
/*    */     public final File field_178758_b;
/*    */     public final File field_178759_c;
/*    */     public final String field_178757_d;
/*    */     private static final String __OBFID = "CL_00001916";
/*    */     
/*    */     public FolderInformation(File p_i45489_1_, File p_i45489_2_, File p_i45489_3_, String p_i45489_4_) {
/* 53 */       this.field_178760_a = p_i45489_1_;
/* 54 */       this.field_178758_b = p_i45489_2_;
/* 55 */       this.field_178759_c = p_i45489_3_;
/* 56 */       this.field_178757_d = p_i45489_4_;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static class GameInformation
/*    */   {
/*    */     public final boolean field_178756_a;
/*    */     public final String field_178755_b;
/*    */     private static final String __OBFID = "CL_00001915";
/*    */     
/*    */     public GameInformation(boolean p_i45488_1_, String p_i45488_2_) {
/* 68 */       this.field_178756_a = p_i45488_1_;
/* 69 */       this.field_178755_b = p_i45488_2_;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ServerInformation
/*    */   {
/*    */     public final String field_178754_a;
/*    */     public final int field_178753_b;
/*    */     private static final String __OBFID = "CL_00001914";
/*    */     
/*    */     public ServerInformation(String p_i45487_1_, int p_i45487_2_) {
/* 81 */       this.field_178754_a = p_i45487_1_;
/* 82 */       this.field_178753_b = p_i45487_2_;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static class UserInformation
/*    */   {
/*    */     public final Session field_178752_a;
/*    */     public final PropertyMap field_178750_b;
/*    */     public final Proxy field_178751_c;
/*    */     private static final String __OBFID = "CL_00001913";
/*    */     
/*    */     public UserInformation(Session p_i45486_1_, PropertyMap p_i45486_2_, Proxy p_i45486_3_) {
/* 95 */       this.field_178752_a = p_i45486_1_;
/* 96 */       this.field_178750_b = p_i45486_2_;
/* 97 */       this.field_178751_c = p_i45486_3_;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\main\GameConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */