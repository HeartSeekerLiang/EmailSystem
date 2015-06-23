package cn.grandcloud.email.controller;

import cn.grandcloud.email.model.SendNormalEntity;
import cn.grandcloud.email.repository.SendNormalRepository;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 智康 on 2015/6/23 0023.
 */
@Controller
public class SendNormalController {

    @Autowired
    private SendNormalRepository sendNormalRepository;

    // 邮件发送，普通模式，页面
    @RequestMapping(value = "/send/normal", method = RequestMethod.GET)
    public String getSendNormals(ModelMap modelMap) {
        modelMap.addAttribute("normalList", sendNormalRepository.findAllSendTimeDesc());
        return "send/normals";
    }

    // 邮件发送，普通模式，详情
    @RequestMapping(value = "/send/normal/detail/{snid}", method = RequestMethod.GET)
    public String sendNormalDetail(@PathVariable("snid") int snid, ModelMap modelMap) {
        modelMap.addAttribute("normal", sendNormalRepository.findOne(snid));
        return "send/normalDetail";
    }

    // 邮件发送，普通模式，GET
    @RequestMapping(value = "/send/normal/add", method = RequestMethod.GET)
    public String sendNormalAddGet(@ModelAttribute("normal") SendNormalEntity sendNormalEntity) {
        return "send/normalAdd";
    }

    // 邮件发送，普通模式，POST
    @RequestMapping(value = "/send/normal/add", method = RequestMethod.POST)
    public String sendNormalAddPost(@ModelAttribute("normal") SendNormalEntity sendNormalEntity, BindingResult result, MultipartHttpServletRequest request, ModelMap modelMap) throws Exception {
        if(result.hasErrors()) {
            return "404";
        }
        // 检查是否导入Excel
        MultipartFile file = request.getFile("recv");
        if(file.isEmpty()) {
            modelMap.addAttribute("reason", "请导入用户邮箱Excel文件");
            return "404";
        }

        // 检查文件扩展名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if(!suffix.equals(".xls")) {
            modelMap.addAttribute("reason", "请导入扩展名为xls的用户邮箱Excel文件。");
            return "404";
        }

        POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        HSSFSheet sheet = wb.getSheetAt(0);

        String userEmails = "";
        int i = 0;
        HSSFRow row;
        while((row = sheet.getRow(i)) != null) {
            HSSFCell cell = row.getCell(0);
            String msg = cell.getStringCellValue();
            System.out.println(msg);
            userEmails += msg + ";";
            i++;
        }

        final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
        final String apiUser = "gaussic_test_6honk7";
        final String apiKey = "INs9gzVDmWXge9UU";
        final String rcpt_to = userEmails;

        HttpPost httpost = new HttpPost(url);
        HttpClient httpclient = new DefaultHttpClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("api_user", apiUser));
        params.add(new BasicNameValuePair("api_key", apiKey));
        params.add(new BasicNameValuePair("to", rcpt_to));
        params.add(new BasicNameValuePair("from", "ggg@qbbZvClrEfiBVihJyXa3LZjbQseF3ePa.sendcloud.org"));
        params.add(new BasicNameValuePair("fromname", "gaussic"));
        params.add(new BasicNameValuePair("subject", sendNormalEntity.getSubject()));
        params.add(new BasicNameValuePair("html", sendNormalEntity.getContent()));
        params.add(new BasicNameValuePair("resp_email_id", "true"));

        httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpclient.execute(httpost);

        // response
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            System.out.println(EntityUtils.toString(response.getEntity()));

        } else {
            System.err.println("error");
        }
        httpost.releaseConnection();

        sendNormalEntity.setReciver(rcpt_to);
        Timestamp sentTime = new Timestamp(System.currentTimeMillis());
        sendNormalEntity.setSendTime(sentTime);

        sendNormalRepository.saveAndFlush(sendNormalEntity);
        return "redirect:/send/normal";
    }

}
