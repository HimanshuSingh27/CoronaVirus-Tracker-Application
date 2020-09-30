package io.himanshu.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.himanshu.coronavirustracker.models.LocationStats;
import io.himanshu.coronavirustracker.services.CoronavirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronavirusDataService coronavirusDataService;
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allstats = coronavirusDataService.getAllStats();
		int totalReportedCases = allstats.stream().mapToInt(stat-> stat.getLatestTotalCases()).sum();
		int totalNewCases = allstats.stream().mapToInt(stat-> stat.getDifferenceFromPrevDay()).sum();
		model.addAttribute("locationStats",allstats );
		model.addAttribute("totalReportedCases",totalReportedCases );
		model.addAttribute("totalNewCases",totalNewCases );
		
		
		return "home";
	}
}
