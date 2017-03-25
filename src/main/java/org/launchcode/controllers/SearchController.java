package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */


@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    // mapped to results in search controller use search to overload

    @RequestMapping(value = "results")
    public String search (Model model, @RequestParam String searchType,
                          @RequestParam String searchTerm) {
        // depends what they select find array of "all" search for term
        // term what contains and add to

        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            //            model.addAttribute("title", "All Jobs");

        } else {
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
//            model.addAttribute("title", "All " + columnChoices.get(column) + " Values");

        }
// columns = columns in header we need to repopulate the columns after we render the
        // new page  column.choices

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";

    }

}
