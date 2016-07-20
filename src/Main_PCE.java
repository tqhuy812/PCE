//package org.eclipse.californium.examples; 

import java.io.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main_PCE {

//	public static ArrayList<Node> nodeList = new ArrayList<Node>();
//	public static int noOfNodes;
	
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		int noOfNodes;
		int offset = 256;
		File file = new File("NodeInfo.txt");
		String coapURL = "coap://[fd00::c30c:0:0:2]:5683/test/routing-info";
		
		CoapClient client = new CoapClient(coapURL);
		CoapResponse response = client.get();
		
		if (response!=null) {
//				System.out.println(response.getCode());
//				System.out.println(response.getOptions());
//				System.out.println(response.getResponseText());
			

			String content = response.getResponseText();

			URLHandler url = new URLHandler(coapURL);
			
			JsonFactory factory = new JsonFactory();
			JsonParser parser = factory.createParser(content);
			
			
			
			if (!file.exists()){
				file.createNewFile();
			}
			
			Writer fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
/*=========================================================================*/
			//Put nodeAddr into NodeInfo file
			Node node = new Node();
			
			node.setNodeAddr(url.getHost());
			
			
			if(nodeList.size()!=0){			
				for(Node scanNode : nodeList){
					System.out.println("aaa" + scanNode.getNodeAddr());
					if(scanNode.getNodeAddr()==node.getNodeAddr()){
						
						while(!parser.isClosed()){
							JsonToken jt = parser.nextToken();
							if(JsonToken.FIELD_NAME.equals(jt)){
								String fieldName = parser.getCurrentName();
								jt = parser.nextToken();
								if ("P".equals(fieldName)){
									scanNode.setParentAddr(parser.getValueAsString());
									
	//								bw.write(scanNode.getParentAddr());
	//								bw.write("\t");
									
								} else if ("R".equals(fieldName)) {
									scanNode.setRank(parser.getValueAsInt());
									
	//								bw.write(String.valueOf(scanNode.getRank())) ;
									
								}
							}
						}
						
					} else {
						
	//	//				bw.write(url.getHost());
	//	//				bw.write("\t");

						while(!parser.isClosed()){
							JsonToken jt = parser.nextToken();
							if(JsonToken.FIELD_NAME.equals(jt)){
								String fieldName = parser.getCurrentName();
								jt = parser.nextToken();
								if ("P".equals(fieldName)){
									node.setParentAddr(parser.getValueAsString());
									
	//	//							bw.write(node.getParentAddr());
	//	//							bw.write("\t");
									
								} else if ("R".equals(fieldName)) {
									node.setRank(parser.getValueAsInt());
									
	//	//							bw.write(String.valueOf(node.getRank())) ;
									
								}
							}
						}
						nodeList.add(node);
					}
				}
		} else {
			nodeList.add(node);
			while(!parser.isClosed()){
				JsonToken jt = parser.nextToken();
				if(JsonToken.FIELD_NAME.equals(jt)){
					String fieldName = parser.getCurrentName();
					jt = parser.nextToken();
					if ("P".equals(fieldName)){
						node.setParentAddr(parser.getValueAsString());
						
//	//							bw.write(node.getParentAddr());
//	//							bw.write("\t");
						
					} else if ("R".equals(fieldName)) {
						node.setRank(parser.getValueAsInt());
						
//	//							bw.write(String.valueOf(node.getRank())) ;
						
					}
				}
			}
			
		}
			for(Node scanNode : nodeList){
				
				bw.write(scanNode.getNodeAddr());
				bw.write("\t");				
				bw.write(scanNode.getParentAddr());
				bw.write("\t");
				bw.write(String.valueOf(scanNode.getRank())) ;
				bw.write("\n");
			}
			bw.close();
			
/*=========================================================================*/			
			
//				System.out.println("\nADVANCED\n");
			// access advanced API with access to more details through .advanced()
//				System.out.println(Utils.prettyPrint(response));
			
			//Read from NodeInfo.txt
//			Reader fr = new FileReader("NodeInfo.txt");
//			BufferedReader br = new BufferedReader(fr);
//			
//			noOfNodes = 0;
//			while(br.readLine() == null){
//				br.readLine();
//			}
//			fr.close();
			
			noOfNodes = nodeList.size();
			
			int maxRank = 0;
			for(Node scanNode : nodeList){
				if (scanNode.getRank()>maxRank){
					maxRank = scanNode.getRank();
				}
			}
			
			for (int rank=0;rank<=maxRank;rank+=offset){
				for (Node scanNode : nodeList){
					
				}
			}
			
		} else {
			System.out.println("No response received.");
		}
	}
}
