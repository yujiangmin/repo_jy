package com.jiangmin.web;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring/beans-datasource-it.xml", "classpath:/spring/beans-service.xml",
		"classpath:/spring/beans-web.xml" })
@Transactional
public class AccountControllerIT {

	@Inject
	private AccountController controller;

	@Value("#{viewNames.accountForm}")
	private String expectedAccountFormViewName;

	private MockHttpServletRequest request;
	private Model model;

	@Before
	public void setUp() throws Exception {
		this.request = new MockHttpServletRequest();
		this.model = new ExtendedModelMap();
	}

	@After
	public void tearDown() throws Exception {
		this.request = null;
		this.model = null;
	}

/*	@Test
	public void testGetContactHappyPath() {
		String viewName = controller.getRegistrationForm(request, model);
		assertEquals(expectedAccountFormViewName, viewName);
		
		 * Contact contact = (Contact) model.asMap().get("contact");
		 * assertNotNull(contact); assertEquals((Long) 1L, contact.getId());
		 * assertEquals("Robert", contact.getFirstName()); assertEquals("A",
		 * contact.getMiddleInitial()); assertEquals("Zimmerman",
		 * contact.getLastName()); assertEquals("bobdylan@example.com",
		 * contact.getEmail());
		 

	}*/

}
