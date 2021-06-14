package com.example.pbx;

import com.example.pbx.model.Pbx;
import com.example.pbx.repository.PbxRepository;
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

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PbxApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	PbxRepository pbxRepository;

	@Test
	@Order(1)
	public void testCreatePbx() throws Exception {
		this.mvc.perform(post("/pbx")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getCreatePbxData().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testGetPbxById() throws Exception {
		Pbx pbx = ((List<Pbx>) pbxRepository.findAll()).get(0);
		this.mvc.perform(get("/pbx/" + pbx.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(pbx.toJsonString()));
	}

	@Test
	@Order(3)
	public void testGetPbxs() throws Exception {
		List<Pbx> pbxs = (List<Pbx>) pbxRepository.findAll();
		List<String> pbxsString = pbxs.stream().map(Pbx::toJsonString).collect(Collectors.toList());
		this.mvc.perform(get("/pbx")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(pbxsString.toString()));
	}

	@Test
	@Order(4)
	public void testUpdatePbx() throws Exception {
		this.mvc.perform(put("/pbx")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getUpdatePbxData().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(5)
	public void testDeletePbx() throws Exception {
		Pbx pbx = ((List<Pbx>) pbxRepository.findAll()).get(0);
		this.mvc.perform(delete("/pbx/" + pbx.getId()))
				.andExpect(status().isNoContent());
	}

	//Utility methods

	private JSONObject getCreatePbxData() throws JSONException {
		JSONObject pbxData = new JSONObject();
		pbxData.put("name", "mockup");
		pbxData.put("url", "http://localhost/mockup");
		pbxData.put("endpoints", "{get: get_cdr}");
		return pbxData;
	}

	private JSONObject getUpdatePbxData() throws JSONException {
		Pbx pbx = ((List<Pbx>) pbxRepository.findAll()).get(0);
		JSONObject pbxData = new JSONObject();
		pbxData.put("id", pbx.getId());
		pbxData.put("name", "mockup test");
		pbxData.put("endpoints", "{get: get_pbx}");
		return pbxData;
	}

}
