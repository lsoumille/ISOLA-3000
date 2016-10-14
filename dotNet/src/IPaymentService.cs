using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;

using Data;

namespace Service {

  [ServiceContract]
  public interface IPaymentService
  {
    [OperationContract]
    [WebInvoke( Method = "POST", UriTemplate = "withdraw",
                RequestFormat = WebMessageFormat.Json,
                ResponseFormat = WebMessageFormat.Json)]
    int ReceiveRequest(PaymentRequest request);


    [OperationContract]
    [WebInvoke( Method = "GET", UriTemplate = "payments/{id}",
                ResponseFormat = WebMessageFormat.Json)]
    Payment FindPaymentById(int id);

    [OperationContract]
    [WebInvoke( Method = "GET", UriTemplate = "payments",
                ResponseFormat = WebMessageFormat.Json)]
    List<int> GetAllPaymentIds();

    }
}
