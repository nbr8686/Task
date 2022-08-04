package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.model.RequestId;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;
import org.openqa.selenium.devtools.v85.network.model.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CaptureRequestsSent {

    DevTools devTools;
    public static Map<String, Map<String, String>> data;

    public Map<String, Map<String, String>> captureHttpRequests(WebDriver driver) {
        data = new HashMap<>();
        Map<String, String> reqData = new HashMap<>();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.of(10000)));
        devTools.addListener(Network.requestWillBeSent(), entry -> {
            Request request = entry.getRequest();
            reqData.put("body", request.getPostData().get());
            data.put(request.getUrl(), reqData);
        });

        /*devTools.addListener(Network.responseReceived(), respose -> {
            Response res = respose.getResponse();
            Map<String, String> resData;
            if (data.containsKey(res.getUrl())) {
                resData = data.get(res.getUrl());
                resData.put("status", String.valueOf(res.getStatus()));
                data.put(res.getUrl(), resData);
            } else {
                resData = new HashMap<>();
                resData.put("status", String.valueOf(res.getStatus()));
                data.put(res.getUrl(), resData);
            }
        });*/
        return getStringMapMap();

    }

    public Map<String, Map<String, String>> captureHttpResponse(WebDriver driver) {
        Map<String, String> reqData = new HashMap<>();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        return getStringMapMap();
    }

    private Map<String, Map<String, String>> getStringMapMap() {
        devTools.addListener(Network.responseReceived(), respose -> {
            Response res = respose.getResponse();
            Map<String, String> resData;
            if (data.containsKey(res.getUrl())) {
                resData = data.get(res.getUrl());
                resData.put("status", String.valueOf(res.getStatus()));
                data.put(res.getUrl(), resData);
            } else {
                resData = new HashMap<>();
                resData.put("status", String.valueOf(res.getStatus()));
                data.put(res.getUrl(), resData);
            }
        });
        return data;
    }
}