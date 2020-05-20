/*
Write down a java program to print even and odd numbers series respectively
from two threads: t1 and t2 synchronizing on a shared object

Let t1 print message “ping — >” and t2 print message “,—-pong”.
Take as command line arguments, the following inputs to the program:
Sleep Interval for thread t1
Write down a java program to print even and odd numbers series respectively from two
threads: t1 and t2 synchronizing on a shared object
Let t1 print message “ping — >” and t2 print message “,—-pong”. Take as command line arguments, the following inputs to the program: Sleep Interval for thread t1 Sleep Interval for thread t2 Message per cycle No of cycles*/




class OddThread extends Thread
{
int limit;
sharedPrinter printer;
public OddThread(int limit, sharedPrinter printer)
{
this.limit = limit;
this.printer = printer;
}
@Override
public void run() 
{
int oddNumber = 1;      
while (oddNumber <= limit)
{
printer.printOdd(oddNumber);       
oddNumber = oddNumber + 2;         
}
}
}

class EvenThread extends Thread
{
int limit;
sharedPrinter printer;
public EvenThread(int limit, sharedPrinter printer)
{
this.limit = limit;
this.printer = printer;
}
@Override
public void run() 
{
int evenNumber = 2;           
while (evenNumber <= limit)
{
printer.printEven(evenNumber);         
evenNumber = evenNumber + 2;          
}
}
}
class sharedPrinter
{

boolean isOddPrinted = false;


synchronized void printOdd(int number)
{
while (isOddPrinted)
{
try 
{
wait();
} 
catch (InterruptedException e)
{
e.printStackTrace();
}
}
System.out.println(Thread.currentThread().getName()+" "+number);
isOddPrinted = true;
try 
{
Thread.sleep(1000);
} 
catch (InterruptedException e) 
{
e.printStackTrace();
}
notify();
}

synchronized void printEven(int number)
{
while (! isOddPrinted)
{
try 
{
wait();
}
catch (InterruptedException e) 
{
e.printStackTrace();
}
}
System.out.println(Thread.currentThread().getName()+" "+number);
isOddPrinted = false;
try 
{
Thread.sleep(1000);
} 
catch (InterruptedException e) 
{
e.printStackTrace();
}
notify();
}
}
public class Main 
{
public static void main(String[] args) 
{
sharedPrinter printer = new sharedPrinter();
OddThread oddThread = new OddThread(20, printer);
oddThread.setName("—-pong");
EvenThread evenThread = new EvenThread(20, printer);
evenThread.setName("ping — >");
oddThread.start();
evenThread.start();
}
}