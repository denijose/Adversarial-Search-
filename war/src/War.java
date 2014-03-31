//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Writer;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.ArrayList;
//
//
//public class War {
//
//	String mapFile = null;
//	String initFile = null;
//	String outputPathFile = null;
//	String outputLogFile = null;
//	Writer pathWriter = null;
//	Writer logWriter = null;
//	ArrayList<Node> nodeListInfo = new ArrayList<Node>();
//	public War() {
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param args
//	 */
//	
//	public ArrayList<String> paraTroop(ArrayList<Node> list){
//		ArrayList<String> outputList = new ArrayList<String>();
//		for (int i= 0; i<list.size(); i++) {
//			if(list.get(i).team == 0)
//				outputList.add(list.get(i).name);
//        }
//		return outputList;
//	}
//	
//	public ArrayList<String> forceMarch(ArrayList<Node> list, int val){
//		ArrayList<String> outputList = new ArrayList<String>();
//		if(val == 1){
//			for (int i= 0; i<list.size(); i++) {
//				if(list.get(i).team==1){
//					for(String d: list.get(i).neighbours){
//						for (int j= 0; j<list.size(); j++) {
//							if(d.equals(list.get(j).name)){
//								if(list.get(j).team == 0 && !outputList.contains(list.get(j).name))
//							       outputList.add(list.get(j).name);
//							}
//						}
//					}
//				}
//			}
//		}
//		else if(val == -1){	
//			for (int i= 0; i<list.size(); i++) {
//				if(list.get(i).team == -1){
//					for(String d: list.get(i).neighbours){
//						System.out.println("String name: "+d);
//						for (int j= 0; j<list.size(); j++) {
//							if(d.equals(list.get(j).name)){
//								if(list.get(j).team == 0)
//							       outputList.add(list.get(j).name);
//							}
//						}						
//					}
//				}
//			}
//		}
//		return outputList;
//	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		War obj = new War();
//		URL path = null;
//		URL pathTwo = null;
//		int task = Integer.parseInt(args[1]);
//		int cutOffDepth = Integer.parseInt(args[3]);
//		obj.mapFile = args[5];
//		obj.initFile = args[7];
//		//obj.outputPathFile = args[9];
//		//obj.outputLogFile = args[11];
//		path = ClassLoader.getSystemResource(obj.initFile);//3
//		pathTwo = ClassLoader.getSystemResource(obj.mapFile);
//		File fileInit = null;
//		File fileMap = null;
//		try {
//			fileInit = new File(path.toURI());
//			fileMap = new File(pathTwo.toURI());
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BufferedReader brInit, brMap;
//		try {
//			brInit = new BufferedReader(new FileReader(fileInit));
//			
//			String lineInit, lineMap;
//			try {
//				while((lineInit = brInit.readLine()) != null) {
//					String[] tokens = lineInit.split(",");
//					int nodeVal = Integer.parseInt(tokens[1]);
//					int nodeTeam = Integer.parseInt(tokens[2]);
//					brMap = new BufferedReader(new FileReader(fileMap));
//					ArrayList<String> n=new ArrayList<String>();
//					while((lineMap = brMap.readLine()) != null){
//						String[] tokensMap = lineMap.split(",");
//						if(tokensMap[0].equals(tokens[0]))
//							n.add(tokensMap[1]);
//						else if(tokensMap[1].equals(tokens[0]))
//							n.add(tokensMap[0]);
//					}
//					brMap.close();
//						Node newNode = new Node(tokens[0],nodeVal,nodeTeam,n);
//						obj.nodeListInfo.add(newNode);
//										
//					}
//				brInit.close();
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for (int j = 0; j < obj.nodeListInfo.size(); j++) {
//			System.out.println("Elements of NodeListInfo arraylist: ");
//			obj.nodeListInfo.get(j).getNodeInfo();
//        }
//		
//		/*Paratroop drop check*/
//		ArrayList<String> paraTroopList = new ArrayList<String>();
//		paraTroopList = obj.paraTroop(obj.nodeListInfo);
//		System.out.println("Nodes that are available for Paratroop drop: ");
//		for(int k=0;k<paraTroopList.size();k++){
//			System.out.println(paraTroopList.get(k));
//		}
//		
//		/*Force March check*/
//		ArrayList<String> forceMarchList = new ArrayList<String>();
//		forceMarchList = obj.forceMarch(obj.nodeListInfo,1);
//		System.out.println("Nodes that are available for Force March: ");
//		for(int l=0;l<forceMarchList.size();l++){
//			System.out.println(forceMarchList.get(l));
//		}
//		paraTroopList.clear();
//		forceMarchList.clear();
//	}
//}
