import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SampleClientExample {
	public static void main(String[] args) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {
		GetMethod getMethod = null;
		String consumerKey = "MSZHadtBZzb9boqQ0eNOt5mT2";
		String consumerSecret = "6G9vJpo4pfWVIMjs3PaEv6m2kFdsJIcfXk2ZH51SbrXv4rtBLB";
		String token = "66350222-uVLr0bt7CTnBjN5zwW4Tj1VsltK6dfMnJ0yyPrG0v";
		String tokenSecret = "XgJQRiBwiqN82OthczeMQ5wO2akqqNscDRtAsCUJN5edx";

		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
		oAuthConsumer.setTokenWithSecret(token,tokenSecret);

		String uri = "https://api.twitter.com/1.1/statuses/retweets_of_me.json";
		HttpGet httpGet = new HttpGet(uri);

		oAuthConsumer.sign(httpGet);

		httpGet.addHeader("count", "5");
		//httpGet.setHeader("count", "5");
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		String jsonResponse = EntityUtils.toString(entity);
		System.out.println(jsonResponse);
	}
}
