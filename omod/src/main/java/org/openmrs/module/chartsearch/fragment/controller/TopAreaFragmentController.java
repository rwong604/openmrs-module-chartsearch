/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.chartsearch.fragment.controller;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Patient;
import org.openmrs.module.chartsearch.GeneratingJson;
import org.openmrs.module.chartsearch.SearchAPI;
import org.openmrs.module.chartsearch.SearchPhrase;
import org.openmrs.module.chartsearch.page.controller.ChartsearchPageController;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

public class TopAreaFragmentController {
	
	public void controller(FragmentModel model, @RequestParam("patientId") Integer patient) {
		model.addAttribute("patientId", patient);
	}
	
	public String getResultsFromTheServer(FragmentModel model, @BindParams SearchPhrase search_phrase,
	                                      @RequestParam("patientId") Patient patient, HttpServletRequest request) {
		SearchAPI searchAPIInstance = SearchAPI.getInstance();
		searchAPIInstance.clearResults();
		String phrase = search_phrase.getPhrase();
		
		ChartsearchPageController.searchAndReturnResults(search_phrase, patient, request, searchAPIInstance);
		return GeneratingJson.generateJson();
	}
}
