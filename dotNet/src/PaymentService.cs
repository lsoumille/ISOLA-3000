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
  public class PaymentService : IPaymentService
  {

    private Dictionary<int, Payment> accounts = new Dictionary<int, Payment>();
    private int counter;

    public int ReceiveRequest(PaymentRequest request)
    {
      Console.WriteLine("ReceiveRequest: " + request);
      var payment = BuildPayment(request);
      accounts.Add(counter, payment);
      return counter++;
    }

    public Payment FindPaymentById(int id)
    {
      if(!accounts.ContainsKey(id)) {
        WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
        return null;
      }
      return accounts[id];
    }

    public List<int> GetAllPaymentIds()
    {
      return accounts.Keys.ToList();
    }

    private Payment BuildPayment(PaymentRequest request)
    {
      var payment = new Payment();
      payment.Identifier = counter;
      payment.CreditCard = request.CreditCard;
      payment.Amount = request.Amount;
      if (request.CreditCard.Contains("0000")) {
        payment.Status = PaymentStatus.Ok;
      } else {
        payment.Status = PaymentStatus.Ko;
      }
      payment.Date = DateTime.Now.ToString();
      return payment;
    }
  }
}
