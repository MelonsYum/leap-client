/*    */ package leap.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public abstract class Command
/*    */ {
/*    */   public String name;
/* 10 */   public List<String> aliases = new ArrayList<>(); public String description; public String syntax;
/*    */   
/*    */   public Command(String name, String description, String syntax, String... aliases) {
/* 13 */     this.name = name;
/* 14 */     this.description = description;
/* 15 */     this.syntax = syntax;
/* 16 */     this.aliases = Arrays.asList(aliases);
/*    */   }
/*    */   
/*    */   public abstract void onCommand(String[] paramArrayOfString, String paramString);
/*    */   
/*    */   public String getName() {
/* 22 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 26 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 30 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 34 */     this.description = description;
/*    */   }
/*    */   
/*    */   public String getSyntax() {
/* 38 */     return this.syntax;
/*    */   }
/*    */   
/*    */   public void setSyntax(String syntax) {
/* 42 */     this.syntax = syntax;
/*    */   }
/*    */   
/*    */   public List<String> getAliases() {
/* 46 */     return this.aliases;
/*    */   }
/*    */   
/*    */   public void setAliases(List<String> aliases) {
/* 50 */     this.aliases = aliases;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\commands\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */