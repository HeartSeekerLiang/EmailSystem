package cn.grandcloud.email.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 智康 on 2015/6/24 0024.
 */
@Controller
public class UnsubscribeController {

    @RequestMapping(value = "/unsub/{email}/", method = RequestMethod.GET)
    public String unsubcribe(@PathVariable("email") String email, ModelMap modelMap) {
        System.out.println("ss:"+email);
        modelMap.addAttribute("email", email);
        return "unsubscribe";
    }

    @RequestMapping(value = "/unsub/do/{email}/", method = RequestMethod.GET)
    public String doUnsubcribe(@PathVariable("email") String email, ModelMap modelMap) throws Exception {
        final String url = "http://sendcloud.sohu.com/webapi/unsubscribes.add.json";
        final String apiUser = "gaussic_test_6honk7";
        final String apiKey = "INs9gzVDmWXge9UU";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);

        System.out.println(email);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("api_user", apiUser));
        params.add(new BasicNameValuePair("api_key", apiKey));
        params.add(new BasicNameValuePair("email", email));

        httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpclient.execute(httpost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            System.out.println(EntityUtils.toString(response.getEntity()));
            modelMap.addAttribute("result", "true");
        } else {
            System.err.println("error");
            modelMap.addAttribute("result", "false");
        }
        return "unscResult";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String get404(ModelMap modelMap) {
        modelMap.addAttribute("reason", "未知");
        return "404";
    }

}
