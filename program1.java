(program1.java)
Using methods charAt() & length() of String class, write a program to print the
frequency of each character in a string.

“Hello friend”
Output should be
-: 1
d: 1
e: 2
f: 1
(continued for all character in the string)import java.util.*;

public class Main
{
   public static void main(String args[])
   {
        int i;
        String s;
        int c[] = new int[256];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a String : ");
        s=sc.nextLine();
         for (i = 0; i < s.length(); i++)
            c[(int) s.charAt(i)]++;
        for (i = 0; i < 256; i++) {
            if (c[i] != 0) {
                  System.out.println((char)i + ": " + c[i]);
            }
        }
   }
}