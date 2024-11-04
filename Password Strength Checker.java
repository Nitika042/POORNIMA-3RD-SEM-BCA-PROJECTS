import java.util.Scanner;
public class Password{
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("Enter a password: ");
        String password = input.nextLine() ;
        if (password.length() < 8)
        {
            System.out.println("password is super weak") ;
            System.out.println("password can be cracked in 10s");
        }
        else if(password.matches("[A-Z]+"))
        {
            System.out.println("password is weak") ;
            System.out.println("password can be cracked in 30s"); 
        }
        else if (password.matches("[A-Za-z]+"))
        {
            System.out.println("password is moderate") ;
            System.out.println("password can be cracked in 50s");
        }
        else if (password.matches("[A-Z0-9]+"))
        {
            System.out.println("password is moderate") ;
            System.out.println("password can be cracked in 50s");
        }
        else if (password.matches("[a-z0-9]+"))
        {
            System.out.println("password is moderate") ;
            System.out.println("password can be cracked in 50s");
        }
        else if (password.matches("[A-Za-z0-9]+"))
        {
            System.out.println("password is strong") ;
            System.out.println("password can be cracked in 500s");
        }
        else
        {
            System.out.println("password is super strong") ;
            System.out.println("password can be cracked in 1600s");
        }
    }
}   