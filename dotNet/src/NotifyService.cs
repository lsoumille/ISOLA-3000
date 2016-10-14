using System;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;
using System.Linq;
using Data;
using System.Net.Mail;
using System.Net;
using System.Security.Cryptography.X509Certificates;
using System.Net.Security;

namespace Service {

  [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
  public class NotifyService : INotify
  {
    public int MailRequest(MailRequest request)
    {
      Console.WriteLine("MailRequest: " + request);
      SmtpClient client = new SmtpClient();
      client.UseDefaultCredentials = false;
      client.Credentials = new NetworkCredential("nextbuscasparf@gmail.com", "polytechnicesophia");
      client.Port = 587;
      client.Host = "smtp.gmail.com";
      client.EnableSsl = true;
      for(int i = 0 ; i < request.Mails.Count ; ++i){
        MailMessage mail = new MailMessage();
        mail.From = new MailAddress("nextbuscasparf@gmail.com");
        mail.To.Add(request.Mails[i]);
        //mail.To.Add("lucassoumille@yahoo.fr");
        mail.Subject = request.Object;
        mail.Body = request.Message;
        ServicePointManager.ServerCertificateValidationCallback =
        delegate(object s, X509Certificate certificate, X509Chain chain, SslPolicyErrors sslPolicyErrors)
        { return true; };
        //client.Send(mail);
      }
      return 0;
    }

  }
}