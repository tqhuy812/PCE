

public class URLHandler {
	private String protocol;
	private String host;
	private String path;
	private int port;
	private String resource;
	private String url;
	
	
	public URLHandler(String url){
		this.url= url;
		parseURL(url);
	}
	
	private void parseURL(String url){
		String delims1 ="(//|\\[|\\])+";
		String[] tokens = url.split(delims1);
		this.host = tokens[1];
		this.protocol = tokens[0].split("\\W")[0];
	}
	
	public String getProtocol(){
		return protocol;
	}
	
	public String getHost(){
		return host;
	}
	
	public String getPath(){
		return path;
	}
	
	public int getPort(){
		return port;
	}
	
	public String getResource(){
		return resource;
	}
}
