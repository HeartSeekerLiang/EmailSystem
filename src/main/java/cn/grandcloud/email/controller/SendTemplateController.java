package cn.grandcloud.email.controller;

import cn.grandcloud.email.model.SendTemplateEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 智康 on 2015/6/24 0024.
 */
@Controller
public class SendTemplateController {





    public static String convert(List<String> emailList) {

        JSONObject ret = new JSONObject();

        JSONArray to = new JSONArray();

        JSONArray addrs = new JSONArray();


        for(String email : emailList) {
            to.put(email);
            addrs.put(email);
        }

        JSONObject sub = new JSONObject();
        sub.put("%addr%", addrs);

        ret.put("to", to);
        ret.put("sub", sub);

        return ret.toString();
    }

    @RequestMapping(value = "/send/template", method = RequestMethod.GET)
    public String getSendTemplates() {
        return "send/templates";
    }

    @RequestMapping(value = "/send/template/add", method = RequestMethod.GET)
    public String templateAddGet(@ModelAttribute("template")SendTemplateEntity sendTemplateEntity) {

        return "send/templateAdd";
    }

    @RequestMapping(value = "/send/template/add", method = RequestMethod.POST)
    public String templateAddPost(@ModelAttribute("template") SendTemplateEntity sendTemplateEntity, BindingResult result, MultipartHttpServletRequest request, ModelMap modelMap) throws Exception {

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

        List<String> emailList = new ArrayList<String>();

        String userEmails = "";
        int i = 0;
        HSSFRow row;
        while((row = sheet.getRow(i)) != null) {
            HSSFCell cell = row.getCell(0);
            String msg = cell.getStringCellValue();
            // System.out.println(msg);
            emailList.add(msg);
            userEmails += msg + ";";
            i++;
        }

        final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";
        final String apiUser = "gaussic_test_6honk7";
        final String apiKey = "INs9gzVDmWXge9UU";
        final String rcpt_to = userEmails;

        final String vars = convert(emailList);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("api_user", apiUser));
        params.add(new BasicNameValuePair("api_key", apiKey));
        params.add(new BasicNameValuePair("substitution_vars", vars));
        params.add(new BasicNameValuePair("template_invoke_name", "test1"));
        params.add(new BasicNameValuePair("from", "ggg@qbbZvClrEfiBVihJyXa3LZjbQseF3ePa.sendcloud.org"));
        params.add(new BasicNameValuePair("fromname", "ggg"));
        params.add(new BasicNameValuePair("subject", "来自sendcloud的测试邮件"));
        params.add(new BasicNameValuePair("resp_email_id", "true"));

        httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpclient.execute(httpost);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.err.println("error");
        }





        return "redirect:/send/template";
    }






    public static void send_template() throws ClientProtocolException, IOException {
        final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";
        final String apiUser = "***";
        final String apiKey = "***";
    }
}
