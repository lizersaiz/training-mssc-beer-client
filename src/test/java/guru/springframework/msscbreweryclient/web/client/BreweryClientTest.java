package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

	// START BEER SECTION
    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
    	
        BeerDto dto = client.getBeerById(UUID.randomUUID());

        assertNotNull(dto);
    }
    
    @Test
    void testSaveNewBeer() {
    	
    	//given
    	BeerDto dto = BeerDto.builder().id(null)
    			.beerName("create beer1")
    			.build();
    	URI uri = client.saveNewBeer(dto);
    	
    	assertNotNull(uri);
    }
    
    @Test
    void testUpdateBeer() {
    	
    	//given
    	BeerDto dto = BeerDto.builder().id(null)
    			.beerName("update beer")
    			.build();
    	
    	client.updateBeer(UUID.randomUUID(), dto);
    }
    
    @Test
    void testDeleteBeer() {
    	
    	client.deleteBeer(UUID.randomUUID());
    }
    // FINISH BEER SECTION
    
    // START CUSTOMER SECTION
	@Test
	void testGetCustomer() {
		
		//given
		CustomerDto dto = client.getCustomer(UUID.randomUUID());
		
		assertNotNull(dto);
	}

	@Test
	void testCreateNewCustomer() {

		//given
		CustomerDto dto = CustomerDto.builder().name("create customer name").build();
		URI uri = client.createNewCustomer(dto);
	
		assertNotNull(uri);
	}

	@Test
	void testUpdateCustomer() {

		//given
		CustomerDto dto = CustomerDto.builder().name("update customer name").build();
		
		client.updateCustomer(UUID.randomUUID(), dto);
	}

	@Test
	void testDeleteCustomer() {
		
		client.deleteCustomer(UUID.randomUUID());
	}
	// FINISH CUSTOMER SECTION
}