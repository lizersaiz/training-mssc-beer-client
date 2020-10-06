package guru.springframework.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

	// configure the object that will handle the requests
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		
		// configure connection pooling manager
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(100);
		connectionManager.setDefaultMaxPerRoute(20);
		
		// configure request
		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectionRequestTimeout(3000)
				.setSocketTimeout(3000)
				.build();
		
		// configure http BLOCKING client
		CloseableHttpClient httpClient = HttpClients
				.custom()
				.setConnectionManager(connectionManager) // use pooling manager
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig) // use request config
				.build();
		
		// return apache implementation of ClientHttpRequestFactory
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
	
	// say how the rest template will handle requests
	@Override
	public void customize(RestTemplate restTemplate) {

		restTemplate.setRequestFactory(this.clientHttpRequestFactory());
	}

}
