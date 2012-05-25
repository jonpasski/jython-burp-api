package com.gdssecurity.http;

import java.net.URL;
import java.util.TreeMap;

import burp.IHttpRequestResponse;

public class HttpRequestResponse
{
    public static final boolean PROCESSED = true;
    
    private IHttpRequestResponse messageInfo;
    
    private String requestBody;
    private TreeMap<String, String> requestHeaders;
    private String requestMethod;
    private String requestProtocolVersion;
    private String requestUri;
    
    private String responseBody;
    private TreeMap<String, String> responseHeaders;
    private String responseProtocolVersion;
    private String responseReason;
    private int responseStatus;
    
    public HttpRequestResponse(IHttpRequestResponse messageInfo,
            String requestMethod, String requestUri,
            String requestProtocolVersion,
            TreeMap<String, String> requestHeaders, String requestBody,
            int responseStatus, String responseReason,
            String responseProtocolVersion,
            TreeMap<String, String> responseHeaders, String responseBody)
    {
        super();
        
        this.messageInfo = messageInfo;
        this.requestMethod = requestMethod;
        this.requestUri = requestUri;
        this.requestProtocolVersion = requestProtocolVersion;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
        this.responseStatus = responseStatus;
        this.responseReason = responseReason;
        this.responseProtocolVersion = responseProtocolVersion;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }
    
    public String getComment() throws Exception
    {
        return messageInfo.getComment();
    }
    
    public String getHighlight() throws Exception
	{
		return messageInfo.getHighlight();
	}
    
    public String getHost()
    {
        return messageInfo.getHost();
    }
    
    public IHttpRequestResponse getMessageInfo()
    {
        return messageInfo;
    }
    
    public int getPort()
    {
        return messageInfo.getPort();
    }
    
    public String getProtocol()
    {
        return messageInfo.getProtocol();
    }

	public byte[] getRequest() throws Exception
	{
		return messageInfo.getRequest();
	}

	public String getRequestBody()
    {
        return requestBody;
    }

	public TreeMap<String, String> getRequestHeaders()
    {
        return requestHeaders;
    }

	public String getRequestMethod()
    {
        return requestMethod;
    }
    
    public String getRequestProtocolVersion()
    {
        return requestProtocolVersion;
    }
    
    public String getRequestUri()
    {
        return requestUri;
    }
    
    public byte[] getResponse() throws Exception
	{
		return messageInfo.getResponse();
	}
    
    public String getResponseBody()
    {
        return responseBody;
    }
    
    public TreeMap<String, String> getResponseHeaders()
    {
        return responseHeaders;
    }
    
    public String getResponseProtocolVersion()
    {
        return responseProtocolVersion;
    }
    
    public String getResponseReason()
    {
        return responseReason;
    }
    
    public int getResponseStatus()
    {
        return responseStatus;
    }
    
    public URL getUrl() throws Exception
    {
        return messageInfo.getUrl();
    }
    
    public void setComment(String comment) throws Exception
    {
        messageInfo.setComment(comment);
    }
    
    public void setHighlight(String color) throws Exception
	{
		messageInfo.setHighlight(color);
	}
    
    public void setRequestBody(String requestBody)
    {
        this.requestBody = requestBody;
    }
    
    public void setRequestHeaders(TreeMap<String, String> requestHeaders)
    {
        this.requestHeaders = requestHeaders;
    }
    
    public void setRequestMethod(String requestMethod)
    {
        this.requestMethod = requestMethod;
    }
    
    public void setRequestProtocolVersion(String requestProtocolVersion)
    {
        this.requestProtocolVersion = requestProtocolVersion;
    }
    
    public void setRequestUri(String requestUri)
    {
        this.requestUri = requestUri;
    }
    
    public void setResponseBody(String responseBody)
    {
        this.responseBody = responseBody;
    }
    
    public void setResponseHeaders(TreeMap<String, String> responseHeaders)
    {
        this.responseHeaders = responseHeaders;
    }
    
    public void setResponseProtocolVersion(String responseProtocolVersion)
    {
        this.responseProtocolVersion = responseProtocolVersion;
    }
    
    public void setResponseReason(String responseReason)
    {
        this.responseReason = responseReason;
    }
    
    public void setResponseStatus(int responseStatus)
    {
        this.responseStatus = responseStatus;
    }
    
}