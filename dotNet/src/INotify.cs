using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;

using Data;

namespace Service {
	
	[ServiceContract]
	public interface INotify
	{
		[OperationContract]
    [	WebInvoke( Method = "POST", UriTemplate = "mail",
           RequestFormat = WebMessageFormat.Json,
           ResponseFormat = WebMessageFormat.Json)]
    	int MailRequest(MailRequest request);
	}	
}

