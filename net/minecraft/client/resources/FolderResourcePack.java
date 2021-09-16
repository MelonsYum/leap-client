/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.io.filefilter.DirectoryFileFilter;
/*    */ 
/*    */ public class FolderResourcePack
/*    */   extends AbstractResourcePack {
/*    */   private static final String __OBFID = "CL_00001076";
/*    */   
/*    */   public FolderResourcePack(File p_i1291_1_) {
/* 19 */     super(p_i1291_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected InputStream getInputStreamByName(String p_110591_1_) throws IOException {
/* 24 */     return new BufferedInputStream(new FileInputStream(new File(this.resourcePackFile, p_110591_1_)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean hasResourceName(String p_110593_1_) {
/* 29 */     return (new File(this.resourcePackFile, p_110593_1_)).isFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getResourceDomains() {
/* 34 */     HashSet<String> var1 = Sets.newHashSet();
/* 35 */     File var2 = new File(this.resourcePackFile, "assets/");
/*    */     
/* 37 */     if (var2.isDirectory()) {
/*    */       
/* 39 */       File[] var3 = var2.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY);
/* 40 */       int var4 = var3.length;
/*    */       
/* 42 */       for (int var5 = 0; var5 < var4; var5++) {
/*    */         
/* 44 */         File var6 = var3[var5];
/* 45 */         String var7 = getRelativeName(var2, var6);
/*    */         
/* 47 */         if (!var7.equals(var7.toLowerCase())) {
/*    */           
/* 49 */           logNameNotLowercase(var7);
/*    */         }
/*    */         else {
/*    */           
/* 53 */           var1.add(var7.substring(0, var7.length() - 1));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 58 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\FolderResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */