package cn.grandcloud.email.controller;

import cn.grandcloud.email.repository.EmailContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 智康 on 2015/6/18 0018.
 */
@Controller
public class EmailContentController {

    @Autowired
    private EmailContentRepository emailContentRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/emails", method = RequestMethod.GET)
    public String getEmails(ModelMap modelMap) {
        modelMap.addAttribute("emailList", emailContentRepository.findAllPubDateDesc());
        return "email/emails";
    }


}
