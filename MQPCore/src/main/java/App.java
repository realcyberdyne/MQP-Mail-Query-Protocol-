import Conf.Config;
import Models.mail_tbl;
import Models.users_tbl;
import Services.Mail.Mail_Service;
import Services.Users.Users_Service;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App
{

    //Static variables
    public static ServerSocket S_Socket;




    //main function start
    public static void main(String []args)
    {

        //Get read properties values
        new Config();


        //Add New User
//        new Users_Service().insert("R","Farazi","RezaFtaa","123","rezafta","");
        try
        {
            mail_tbl mail = new Mail_Service().GetMailById(1);
            System.out.println(mail.getUser_id().getName());
            System.out.println(mail.getTitle());
//            users_tbl user = new Users_Service().GetUserByUsername("rezafta");
//            new Mail_Service().InsertnewMail("reza","farazi",user,"rezafta");
//            mail_tbl mail = new Mail_Service().InsertnewMail("reza", "farazi", user, "rezafta@outlook.com");
//            System.out.println("Data inserted");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
//        new FileManager().NewFile(new File("C:\\Users\\Rezafta\\Desktop\\rezaa.txt"));
//        System.out.println("Opration is done");
        //System.out.println(new Users_Service().CheckUserExist("rezafta1"));




        //Get Run MQP
        try
        {
            //Init new server socket
            S_Socket = new ServerSocket(Config.Port);

            //Print server socket is started
            System.out.println("Socket is ready");

            while(true)
            {

                //Get accept request socket
                Socket client_socket = S_Socket.accept();

                //Create a socket send and resvice instance
                DataInputStream DIS = new DataInputStream(client_socket.getInputStream());
                DataOutputStream DOS = new DataOutputStream(client_socket.getOutputStream());


                //Socket thread work with multi sockets
                //Socket thread start
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            //Get read condition
                            JSONObject Data = new JSONObject(DIS.readUTF());
                            String Condition=Data.get("Condition").toString();


                            //Get new message email exist
                            if(Condition.equals("NEW"))
                            {
                                NewCondition(client_socket,DIS,DOS);
                            }



                        }
                        catch (Exception e)
                        {
                            //Print error condition
                            System.out.println(e.getMessage());
                        }
                    }
                }).start();
                //Socket thread end

            }

        }
        catch (Exception e)
        {
            //Print error condition
            System.out.println(e.getMessage());
        }

    }
    //main function end


    //Get new condition function start
    public static void NewCondition(Socket socket,DataInputStream DIS,DataOutputStream DOS)
    {



    }
    //Get new condition function end




}
