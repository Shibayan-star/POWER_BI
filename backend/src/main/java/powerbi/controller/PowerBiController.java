package powerbi.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import powerbi.service.PowerBiService;
import java.io.IOException;

@RestController
@RequestMapping("/v1/")

public class PowerBiController {

    @Autowired
    private PowerBiService powerBiService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getEmbedToken")
    @ResponseBody
    public String getEmbedToken() throws IOException, InterruptedException {
        return powerBiService.getEmbedToken();
    }

    //if you are using MVC or else Ignore
    @GetMapping("/showReport")
    public String showReport(Model model) throws IOException, InterruptedException {
        // Configure your report parameters
        String accessToken = powerBiService.getAccessToken();
        model.addAttribute("tokenType", 0);
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("embedUrl", "https://app.powerbi.com/reportEmbed?reportId=83339961-4bdd-45c3-b0f4-069fbe53298b&autoAuth=true&ctid=9652d7c2-1ccf-4940-8151-4a92bd474ed0");
        model.addAttribute("permissions", 7);
        model.addAttribute("viewMode", 0);
        model.addAttribute("filterPaneEnabled", false);
        model.addAttribute("navContentPaneEnabled", false);
        model.addAttribute("useCustomSaveAsDialog", false);

        return "report"; // Assuming you have a Thymeleaf or JSP template named "report"
    }

}
