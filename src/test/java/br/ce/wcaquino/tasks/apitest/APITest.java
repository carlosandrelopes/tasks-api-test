package br.ce.wcaquino.tasks.apitest;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
		
	}
	
	
	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		.when()
			.get("/todo")
		.then()
			.log().all()
			.statusCode(200)
		;
				
	}
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured.given()
			.body("{\"task\": \"Test via API\", \"dueDate\": \"2030-04-16\"	}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.log().all()
			.statusCode(201)
		;
		
		}
	
	@Test
	public void naodeveAdicionarTarefaInvalida() {
		RestAssured.given()
			.body("{\"task\": \"Test via API\", \"dueDate\": \"2010-12-30\"	}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.log().all()
			.body("message", CoreMatchers.is("Due date must not be in past"))
			.statusCode(400)	
			;
		
		
		}
	}

