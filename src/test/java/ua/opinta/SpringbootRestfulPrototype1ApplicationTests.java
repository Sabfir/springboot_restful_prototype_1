//package ua.opinta;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//public class SpringbootRestfulPrototype1ApplicationTests {
//
//	@Test
//	public void contextLoads() {
//		// some commands to use
////		$ curl -H "Accept: application/json" my-client-with-secret:secret@localhost:8080/oauth/token -d grant_type=client_credentials
////		result = {... "access_token": "b561ff06-4259-466e-92d8-781db1a51901", ...}
////		$ TOKEN=b561ff06-4259-466e-92d8-781db1a51901
////		$ curl -H "Authorization: Bearer $TOKEN" localhost:8080/user
//
////		curl -H "Accept: application/json" my-trusted-client:secret@localhost:8080/oauth/token -d grant_type=client_credentials
//	}
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TodoService todoServiceMock;
//
//    //Add WebApplicationContext field here.
//
//    //The setUp() method is omitted.
//
//    @Test
//    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {
//        Todo first = new TodoBuilder()
//                .id(1L)
//                .description("Lorem ipsum")
//                .title("Foo")
//                .build();
//        Todo second = new TodoBuilder()
//                .id(2L)
//                .description("Lorem ipsum")
//                .title("Bar")
//                .build();
//
//        when(todoServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
//
//        mockMvc.perform(get("/api/todo"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[0].title", is("Foo")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[1].title", is("Bar")));
//
//        verify(todoServiceMock, times(1)).findAll();
//        verifyNoMoreInteractions(todoServiceMock);
//    }
//}
