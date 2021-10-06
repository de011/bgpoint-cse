/*
 * package com.dromedian.comunico.bgpoint.chequebook; import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import java.net.URI; import java.net.URISyntaxException; import
 * java.util.ArrayList; import java.util.List;
 * 
 * import org.hamcrest.Matchers; import org.junit.Assert; import org.junit.Test;
 * import org.junit.runner.RunWith; import org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.mock.mockito.MockBean; import
 * org.springframework.boot.web.server.LocalServerPort; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.context.junit4.SpringRunner; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook; import
 * com.dromedian.comunico.bgpoint.cse.service.impl.ChequeBookServiceImpl; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest
 * 
 * @WebMvcTest public class BgpointChequebookApplicationTests {
 * 
 * @LocalServerPort int randomServerPort;
 * 
 * @Test public void testGetEmployeeListSuccess() throws URISyntaxException {
 * RestTemplate restTemplate = new RestTemplate();
 * 
 * final String baseUrl = "http://localhost:" + randomServerPort +
 * "/dromedian/save/chequeBooks"; URI uri = new URI(baseUrl);
 * 
 * ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
 * 
 * //Verify request succeed Assert.assertEquals(200,
 * result.getStatusCodeValue()); Assert.assertEquals(true,
 * result.getBody().contains("listchequebook")); }
 * 
 * }
 */