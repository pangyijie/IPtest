
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class IpTest {


    /**
     * 代理IP有效检测
     *
     * @param proxyIp
     * @param proxyPort
     * @param reqUrl
     */
    public static Boolean checkProxyIp(String proxyIp, int proxyPort, String reqUrl) {

        HttpClient client = new HttpClient();
        client.getHostConfiguration().setProxy(proxyIp, proxyPort);
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
        client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
        GetMethod getMethod = new GetMethod(reqUrl);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        if (getMethod == null) {
            System.out.println("请求协议设置都搞错了，所以我无法完成您的请求");
            return null;
        }
        try {
            int statusCode = client.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println(HttpStatus.SC_OK);
                return true;
            } else {
                System.out.println(HttpStatus.SC_FORBIDDEN);
                return false;
            }
        } catch (HttpException e) {
            System.out.println("Please check your provided http address!");
            return false;
        } catch (IOException e) {
            return false;
        }

    }

    public static void main(String[] args) {

        String url = "http://www.baidu.com";
        String proxyIp = "119.48.185.155";
        int proxyPort = 8118;
        System.out.println(checkProxyIp(proxyIp, proxyPort, url));

    }

}  