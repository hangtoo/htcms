package common.component.permission.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeTree {
	private Map<String,Node> tree=new HashMap<String,Node>();
	
	public void addChildNode(Node ele){
		if(ele.getPid()==null){//根节点
			Node i=tree.get(ele.getId());//取出自己，并更新自己
			if(i!=null){
				i.setId(ele.getId());
				i.setName(ele.getName());
				i.setPid("-1");
				i.setUrl(ele.getUrl());
				tree.put(ele.getId(), i);
				
				Node root=new Node();
				root.getChildren().add(i);
				tree.put("-1", root);
			}else{
				tree.put(ele.getId(), ele);
				
				Node root=new Node();
				root.getChildren().add(ele);
				tree.put("-1", root);
			}
			return;
		}
		Node pele=tree.get(ele.getPid());//取出父节点
		
		if(pele==null){
			pele=new Node();
			pele.setId(ele.getPid());
			pele.getChildren().add(ele);
		}else{
			pele.getChildren().add(ele);
		}
		tree.put(ele.getPid(), pele);//加入子节点
		
	}
	
	private String menustart="menu:{items:[";
	private String menuend="]}";
	
	public String getChildNode(String pid){

		Node pele=tree.get(pid);
		if(pele==null)
			return "";
		
		StringBuffer ret=new StringBuffer("");
		
		List<Node> children=pele.getChildren();
		int i=0;
		for(Node ele:children){
			if(i!=0){
				ret.append(",");
			}
			i++;
			System.out.println(i);
			ret.append("{windowId:"+ele.getId()+",");
			
			ret.append("text:'"+ele.getName()+"',");
			ret.append("URL:'"+ele.getUrl()+"',");
			ret.append("scope: this,");
			
			String childstr=getChildNode(ele.getId());
			if(!"".equalsIgnoreCase(childstr)){
				ret.append("handler :  function() {return false;},");
				ret.append(menustart+childstr+menuend+",");
			}else
				ret.append("handler :  this.createWindow,");
			ret.append("iconCls:'bogus'}");
		}
		
		return ret.toString();
	}
}
