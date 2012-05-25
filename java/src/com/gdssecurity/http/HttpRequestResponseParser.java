package com.gdssecurity.http;

import java.util.TreeMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.entity.EntityDeserializer;
import org.apache.http.impl.entity.LaxContentLengthStrategy;
import org.apache.http.impl.io.DefaultHttpRequestParser;
import org.apache.http.impl.io.DefaultHttpResponseParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicLineParser;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import burp.IHttpRequestResponse;

import com.gdssecurity.http.mock.MockSessionInputBuffer;

public class HttpRequestResponseParser
{
    public static HttpRequestResponseParser instance = null;
    private static EntityDeserializer serializer;
    
    public synchronized HttpRequestResponseParser getInstance()
    {
        if (instance == null)
            instance = new HttpRequestResponseParser();
        
        return instance;
    }
    
    public HttpRequestResponseParser()
    {
        serializer = createEntityDeserializer();
    }
    
    protected EntityDeserializer createEntityDeserializer()
    {
        return new EntityDeserializer(new LaxContentLengthStrategy());
    }
    
    public HttpRequestResponse parse(IHttpRequestResponse message) throws Exception
    {
        // /////////////////////////////////////////////////////////////////
        //
        // Parse http request
        //
        // /////////////////////////////////////////////////////////////////
        
        SessionInputBuffer requestBuffer = new MockSessionInputBuffer(
                message.getRequest(), message.getRequest().length);
        
        DefaultHttpRequestParser httpRequestParser = new DefaultHttpRequestParser(
                requestBuffer, BasicLineParser.DEFAULT,
                new DefaultHttpRequestFactory(), new BasicHttpParams());
        
        HttpRequest httpRequest = (HttpRequest) httpRequestParser.parse();
        
        RequestLine requestLine = httpRequest.getRequestLine();
        
        String requestMethod = requestLine.getMethod();
        String requestUri = requestLine.getUri();
        String requestProtocolVersion = requestLine.getProtocolVersion().toString();
        
        TreeMap<String, String> requestHeaders = getMessageHeaders(httpRequest.getAllHeaders());
        
        HttpEntity requestEntity = serializer.deserialize(requestBuffer, httpRequest);
        
        String requestData = EntityUtils.toString(requestEntity);
        
        // /////////////////////////////////////////////////////////////////
        //
        // Parse http response
        //
        // /////////////////////////////////////////////////////////////////
        
        SessionInputBuffer responseBuffer = new MockSessionInputBuffer(
                message.getResponse(), message.getResponse().length);
        
        DefaultHttpResponseParser httpResponseParser = new DefaultHttpResponseParser(
                responseBuffer, BasicLineParser.DEFAULT,
                new DefaultHttpResponseFactory(), new BasicHttpParams());
        
        BasicHttpResponse httpResponse = (BasicHttpResponse) httpResponseParser.parse();
        
        StatusLine responseStatusLine = httpResponse.getStatusLine();
        
        int responseStatus = responseStatusLine.getStatusCode();
        String responseReasonPhrase = responseStatusLine.getReasonPhrase();
        String responseProtocolVersion = responseStatusLine.getProtocolVersion().toString();
        
        TreeMap<String, String> responseHeaders = getMessageHeaders(httpResponse.getAllHeaders());
        
        HttpEntity responseEntity = serializer.deserialize(responseBuffer, httpResponse);
        
        String responseBody = EntityUtils.toString(responseEntity);
        
        return new HttpRequestResponse(
                message, requestMethod, requestUri,
                requestProtocolVersion, requestHeaders, requestData,
                responseStatus, responseReasonPhrase, responseProtocolVersion,
                responseHeaders, responseBody);
    }
    
    private TreeMap<String, String> getMessageHeaders(Header[] messageHeaders)
    {
        TreeMap<String, String> headers = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        
        for (Header header : messageHeaders)
        {
            String value = headers.get(header.getName());
            
            if (value != null && header.getValue() != value)
            {
                value = value + ", " + header.getValue();
                headers.put(header.getName(), value);
            }
            else
            {
                headers.put(header.getName(), header.getValue());
            }
        }
        
        return headers;
    }
    
}
