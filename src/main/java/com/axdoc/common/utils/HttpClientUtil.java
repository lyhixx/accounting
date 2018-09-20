/**
 * 
 */
package com.axdoc.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

//	protected static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static HttpClient httpClient = null;

	public static HttpClient getClient() {
		if (httpClient == null) {
			httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		}
		return httpClient;
	}

	public static String HttpGet(String url) {
		HttpGet httpget = new HttpGet(url);
		String result = "";
		try {
			HttpResponse response = HttpClientUtil.getClient().execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				result = convertStreamToString(instreams);
				httpget.abort();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String  httpPost(String httpUrl,byte[] bodys,String fileName,String fileParam) throws IOException{
		ByteArrayBody byteArrayBody = new ByteArrayBody(bodys,fileName);  
		MultipartEntityBuilder meb = MultipartEntityBuilder.create().addPart(fileParam,byteArrayBody);
		
		/*for (String key : params.keySet()) {  
		    meb.addTextBody(key, params.get(key));	  
		}  */
        HttpEntity reqEntity = meb.build();
        HttpPost httpPost = new HttpPost(httpUrl);  
        httpPost.setEntity(reqEntity);  
        
        HttpResponse response = HttpClientUtil.getClient().execute(httpPost);
        HttpEntity resultEntity = response.getEntity();  
        String responseMessage = "";  
        
            if(resultEntity!=null){  
                InputStream is = resultEntity.getContent();  
                BufferedReader br = new BufferedReader(new InputStreamReader(is));  
                StringBuffer sb = new StringBuffer();  
                String line = "";  
                while((line = br.readLine()) != null){  
                    sb.append(line);  
                }  
                responseMessage = sb.toString();  
               
            }  
            EntityUtils.consume(resultEntity);  
       
		return responseMessage;
	}


	/**
	 * post请求
	 * @param httpUrl
	 * @param param
	 * @return
	 */
	public static String httpPost(String httpUrl, String param ,String charsetName) throws Exception {
		String result = "";
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true); // 可以发送数据
			conn.setDoInput(true); // 可以接收数据
			conn.setRequestMethod("POST"); // POST方法
			// 必须注意此处需要设置UserAgent，否则google会返回403
			conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.connect();

			// 写入的POST数据
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), charsetName);
			osw.write(param);
			osw.flush();
			osw.close();

			// 读取响应数据
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),charsetName));
			String line;
			StringBuffer strBuff = new StringBuffer();
			while ((line = in.readLine()) != null) {
				strBuff.append(line);
			}
			in.close();
			result = strBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
