using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Net.Mail;
using System.Net;
using System.Security.Cryptography.X509Certificates;
using System.Net.Security;
using Service;

/**
 * References:
 * http://badger.developpez.com/tutoriels/dotnet/web-service-rest-avec-wcf-3-5/#LII-B
 **/
public class Server
{
  // mono server.exe               ==> localhost:9090
  // mono server.exe HOSTNAME      ==> HOSTNAME:9090
  // mono server.exe HOSTNAME PORT ==> HOSTNAME:PORT

  public static void Main(string[] args)
  {
    Console.Write("Starting server... ");
    string hostname = "localhost";
    string port = "9090";
    string url = "http://localhost:9090";
    string url2 = "http://localhost:9091";

    WebHttpBinding binding = new WebHttpBinding();
    WebServiceHost host = new WebServiceHost(typeof(PaymentService), new Uri (url));
    WebServiceHost host2 = new WebServiceHost(typeof(NotifyService), new Uri (url2));
    host.AddServiceEndpoint(typeof(IPaymentService), binding, "");
    host2.AddServiceEndpoint(typeof(INotify), binding, "");
    host.Open();
    host2.Open();
    Console.WriteLine("Server ready\n");
    Console.WriteLine("  Listening to "+ url + "\n\n");
    Console.WriteLine("  Listening to " + url2 + "\n\n");
    Console.ReadLine();
    host2.Close();
    host.Close();
    Console.WriteLine("Server shutdown complete!");
  }

}
