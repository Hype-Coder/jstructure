# jstructure

/*
  
  Example of how to use this library.

*/

public class Test {
  
  public static void main(String[] args){
    
    Structure struct = new Structure(){
      
      public Var[] newVar(){
        
        new Var("mychar",new Char(15)),
        
        new Var("myshortint",new ShortInt(14)),
        
        new Var("mychararray",new CharArray(18)),
        
        new Var("myintarray",new IntArray(
          
          new Int[]{
             
             new Int(15),
             
             new Int(14),
             
             new Int(1435)
            
          }
          
        ))
      
      }
    
    }
    
    System.out.println(struct);
    
    for (Byte b : DataTypes.toBytes(struct)){
      
      System.out.printf("%x",b);
      
    }
  
  }

}
