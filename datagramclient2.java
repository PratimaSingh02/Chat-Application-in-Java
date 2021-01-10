import java.net.*;
import java.util.*;
import java.io.*;

//use while(true){} inside run(){} bcuz run() called only once

class datagramclient2 extends Thread{
String str3=new String();
DatagramSocket ds;
byte b[]=new byte[50];
datagramclient2() throws Exception{
ds=new DatagramSocket(4002);
}

public void run(){
while(true){
try{
System.out.println("Receiving.....");
DatagramPacket dp1=new DatagramPacket(b,b.length);
ds.receive(dp1);
String str2=new String(dp1.getData(),0,dp1.getLength());
str3=str2;
System.out.println("Server: "+str2);
if(str2.equalsIgnoreCase("bye")){
System.out.println("Quiting");
stop();
break;

}//if
Thread.sleep(500);
}
catch(Exception e){
System.out.println("Receiving failed.");
}
}
}

public static void main(String[] args) throws Exception{
datagramclient2 t1=new datagramclient2();
Scanner sc=new Scanner(System.in);
BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
String str="";
//cc t2=new cc();
//t2.start();
t1.start();

while(true){
if(t1.str3.equalsIgnoreCase("bye")){
break;
}//if
System.out.println("Enter data: ");
str=bf.readLine();
System.out.println("Data sent: "+str);
t1.ds.send(new DatagramPacket(str.getBytes(),str.length(),InetAddress.getLocalHost(),4001));
if(str.equalsIgnoreCase("bye")){
t1.stop();
System.out.println("Exiting");
break;
}
Thread.sleep(500);
}


}
}


/*class cc extends Thread{
DatagramSocket ds1;

cc() throws Exception{
ds1=new DatagramSocket();
}
public void run(){
synchronized(this){
try{

}
catch(Exception e){
}
}
}

}*/