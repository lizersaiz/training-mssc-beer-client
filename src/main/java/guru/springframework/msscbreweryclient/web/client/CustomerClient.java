package guru.springframework.msscbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;

@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class CustomerClient {

	public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	private String apihost;
	
	private final RestTemplate restTemplate;
	
	public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
		
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public CustomerDto getCustomer(UUID uuid) {
		
		return this.restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + uuid, CustomerDto.class);
	}
	
	public URI 
	
	
	public void setApiHost(String apihost) {
		
		this.apihost = apihost;
	}
	
}
