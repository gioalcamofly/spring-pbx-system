package com.example.cdr;

import com.example.cdr.repository.RecordRepository;
import com.example.cdr.model.Record;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CallDetailedRecordApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	RecordRepository recordRepository;

	@Test
	@Order(1)
	public void testCreateProduct() throws Exception {
		this.mvc.perform(post("/cdr/record")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getCreateRecordData().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testGetRecordById() throws Exception {
		Record record = ((List<Record>) recordRepository.findAll()).get(0);
		this.mvc.perform(get("/cdr/record/" + record.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(record.toJsonString()));
	}

	@Test
	@Order(3)
	public void testGetRecords() throws Exception {
		List<Record> records = (List<Record>) recordRepository.findAll();
		List<String> recordsString = records.stream().map(Record::toJsonString).collect(Collectors.toList());
		this.mvc.perform(get("/cdr/records")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(recordsString.toString()));
	}

	@Test
	@Order(4)
	public void testUpdateRecord() throws Exception {
		this.mvc.perform(put("/cdr/record")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getUpdateRecordData().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(5)
	public void testDeleteRecord() throws Exception {
		Record record = ((List<Record>) recordRepository.findAll()).get(0);
		this.mvc.perform(delete("/cdr/record/" + record.getId()))
				.andExpect(status().isNoContent());
	}

	//Utility methods

	private JSONObject getCreateRecordData() throws JSONException {
		JSONObject recordData = new JSONObject();
		recordData.put("uuid", "6c4c0ae0-e99c-466a-8dce-d99207ac2688");
		recordData.put("domainName", "mockpbx.impactpbx.com");
		recordData.put("callerName", "");
		recordData.put("callerNumber", "35725262136");
		recordData.put("destinationNumber", "35799123458");
		recordData.put("direction", "outbound");
		recordData.put("callStart", "2020-08-26T15:49:32.000Z");
		recordData.put("ringStart", "2020-08-26T15:49:36.000Z");
		recordData.put("answerStart", "2020-08-26T15:49:37.000Z");
		recordData.put("callEnd", "2020-08-26T15:50:36.000Z");
		recordData.put("duration", 64);
		recordData.put("recording", "3aad34f5-5915-4b74-9ab3-131feb14f067");
		recordData.put("clickToCall", false);
		recordData.put("clickToCallData", "");
		recordData.put("action", "HANGUP");
		return recordData;
	}

	private JSONObject getUpdateRecordData() throws JSONException {
		Record record = ((List<Record>) recordRepository.findAll()).get(0);
		JSONObject recordData = new JSONObject();
		recordData.put("id", record.getId());
		recordData.put("domainName", "mockpbx.impactpbx.es");
		recordData.put("destinationNumber", "35799123459");
		recordData.put("clickToCall", true);
		recordData.put("clickToCallData", "data");
		return recordData;
	}

}
