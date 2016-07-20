import java.util.HashMap;

public class Node {

		private int	rank;
		private String nodeAddr;
		private String parentAddr;
		private HashMap<Integer,Node> tree = new HashMap<Integer,Node>();
		
		
		public int getRank(){
			return rank;
		}
		
		public String getNodeAddr(){
			return nodeAddr;
		}
		
		public String getParentAddr(){
			return parentAddr;
		}
		
		public HashMap<Integer, Node> getTree(){
			return tree;
		}
		
		public void setNodeAddr(String nodeAddr){
			this.nodeAddr = nodeAddr;
		}
		
		public void setParentAddr(String parentAddr){
			this.parentAddr = parentAddr;
		}
		
		public void setRank(int rank){
			this.rank = rank;
		}
		
		public HashMap<Integer, Node> addTree(HashMap<Integer, Node> hm){
			
			return hm;
			
		}
}
