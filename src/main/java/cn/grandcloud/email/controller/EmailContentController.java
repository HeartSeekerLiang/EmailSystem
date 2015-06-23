package cn.grandcloud.email.controller;

import cn.grandcloud.email.model.EmailContentEntity;
import cn.grandcloud.email.repository.EmailContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 智康 on 2015/6/18 0018.
 */
@Controller
public class EmailContentController {

    @Autowired
    private EmailContentRepository emailContentRepository;

    // 首页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    // 邮件管理页面
    @RequestMapping(value = "/emails", method = RequestMethod.GET)
    public String getEmails(ModelMap modelMap) {
        modelMap.addAttribute("emailList", emailContentRepository.findAllPubDateDesc());
        return "email/emails";
    }

    // 邮件详情页
    @RequestMapping(value = "/emails/detail/{eid}", method = RequestMethod.GET)
    public String emailDetail(@PathVariable("eid") int eid, ModelMap modelMap) {
        modelMap.addAttribute("email", emailContentRepository.findOne(eid));
        return "email/emailDetail";
    }

    // 添加邮件 GET
    @RequestMapping(value = "/emails/add", method = RequestMethod.GET)
    public String emailAddGet(@ModelAttribute("email") EmailContentEntity emailContentEntity) {
        return "email/emailAdd";
    }

    // 添加邮件 POST
    @RequestMapping(value = "/emails/add", method = RequestMethod.POST)
    public String emailAddPost(@ModelAttribute("email") EmailContentEntity emailContentEntity, BindingResult result) {
        if(result.hasErrors()) {
            return "404";
        }
        Timestamp pubTime = new Timestamp(System.currentTimeMillis());
        emailContentEntity.setPubDate(pubTime);
        emailContentRepository.saveAndFlush(emailContentEntity);
        return "redirect:/emails";
    }

    // 编辑邮件 GET
    @RequestMapping(value = "/emails/update/{eid}", method = RequestMethod.GET)
    public String updateEmail(@PathVariable("eid") int eid, ModelMap modelMap) {
        modelMap.addAttribute("email", emailContentRepository.findOne(eid));
        return "email/emailUpdate";
    }

    // 编辑邮件 POST
    @RequestMapping(value = "/emails/update", method = RequestMethod.POST)
    public String emailUpdate(@ModelAttribute("email") EmailContentEntity emailContentEntity, BindingResult result) {
        if(result.hasErrors()) {
            return "404";
        }
        emailContentRepository.updateEmailById(emailContentEntity.getSubject(), emailContentEntity.getContent(), emailContentEntity.getEid());
        emailContentRepository.flush();
        return "redirect:/emails";
    }

    // 时间转换处理
    @InitBinder
    public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
            new PropertyEditorSupport() {
                public void setAsText(String value) {
                    try {
                        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(value);
                        setValue(new Timestamp(parsedDate.getTime()));
                    } catch (ParseException e) {
                        setValue(null);
                    }
                }
            });
    }

}
