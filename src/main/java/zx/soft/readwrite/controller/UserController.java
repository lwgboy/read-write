package zx.soft.readwrite.controller;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.readwrite.domain.SimpleUser;
import zx.soft.readwrite.service.UserToCDH5Service;
import zx.soft.readwrite.service.UserToMySQLService;
import zx.soft.readwrite.service.UserToRedisService;
import zx.soft.readwrite.service.UserToSolrService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Inject
	private UserToRedisService userToRedisService;
	@Inject
	private UserToSolrService userToSolrService;
	@Inject
	private UserToMySQLService userToMySQLService;
	@Inject
	private UserToCDH5Service userToCDH5Service;

	@RequestMapping(value = "/redis", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void insertUserToRedis(@RequestBody SimpleUser user) {
		userToRedisService.insertUser(user);
		userToRedisService.close();
	}

	@RequestMapping(value = "/solr", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void insertUserToSolr(@RequestBody SimpleUser user) {
		userToSolrService.insertUser(user);
		userToSolrService.close();
	}

	@RequestMapping(value = "/mysql", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void insertUserToMySQL(@RequestBody SimpleUser user) {
		userToMySQLService.insertUser(user);
	}

	@RequestMapping(value = "/CDH5", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void insertUserToCDH5(@RequestBody SimpleUser user) {
		userToCDH5Service.insertUser(user);
		userToCDH5Service.close();
	}

	@RequestMapping(value = "/redis/{user}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void getUser(@PathVariable String user) {
		userToRedisService.getUser(user);
		userToRedisService.close();
	}
}
