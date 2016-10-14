using System.Runtime.Serialization;
using System;
using System.Collections.Generic;

namespace Data {

  [DataContract(Namespace = "http://payment/data/",
                Name = "PaymentRequest")]
  public class PaymentRequest
  {
    [DataMember]
    public string CreditCard { get; set; }

    [DataMember]
    public double Amount { get; set; }

    override public string ToString()
    {
      return "PaymentRequest[" + CreditCard + ", " + Amount + "]";
    }
  }
	
  [DataContract(Namespace = "http://payment/data/",
                Name = "MailRequest")]
  public class MailRequest
  {
    [DataMember]
    public string Message { get; set; }

    [DataMember]
    public string Object { get; set; }

    [DataMember]
    public List<string> Mails { get; set; }

    override public string ToString()
    {
      return "MailRequest[" + Message + "]";
    }
  } 

  [DataContract(Namespace = "http://payment/data/",
                Name = "Payment")]
  public class Payment
  {
    [DataMember]
    public int Identifier { get; set; }

    [DataMember]
    public string CreditCard { get; set; }

    [DataMember]
    public double Amount { get; set; }

    [DataMember]
    public PaymentStatus Status { get; set; }

    [DataMember]
    public string Date { get; set; }

  }

  public enum PaymentStatus { Ok, Ko }

}
