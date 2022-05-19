package eu.arrowhead.application.skeleton.provider.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.arrowhead.application.skeleton.provider.connectzwaveadapter.PlugEnergyReader;
import eu.arrowhead.application.skeleton.provider.connectzwaveadapter.PlugSwitcher;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.Defaults;

@CrossOrigin(maxAge = Defaults.CORS_MAX_AGE, allowCredentials = Defaults.CORS_ALLOW_CREDENTIALS, allowedHeaders = {
		HttpHeaders.ORIGIN, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT, HttpHeaders.AUTHORIZATION })

@RestController
public class ProviderController {

	// =================================================================================================
	// members

	private final Logger logger = LogManager.getLogger(ProviderController.class);
	PlugSwitcher ps = new PlugSwitcher();
	PlugEnergyReader per = new PlugEnergyReader();

	// =================================================================================================
	// methods

	// -------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echoService() {
		logger.debug("echoService started...");
		return "Got it!";
	}

	@PutMapping(path = "/plug/switch/{switchWishedState}")
	@ResponseBody
	public String PlugSwitching(@PathVariable(name = "switchWishedState") String switchPlugState)
			throws IOException, ParseException, NumberFormatException {

		ps.switchPlug(switchPlugState);

		return "Plugs switched to new state : " + switchPlugState;

	}

	@GetMapping(path = "/plug/energy")
	@ResponseBody
	public String PlugEnergyConsumption() throws IOException, ParseException {
		return per.readEnergyKWH();
	}

	// -------------------------------------------------------------------------------------------------
	// TODO: implement here your provider related REST end points
}
