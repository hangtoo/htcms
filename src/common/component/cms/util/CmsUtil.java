package common.component.cms.util;

import common.component.cms.entity.Catalogue;
import common.component.cms.entity.Content;
import common.util.DateUtil;

public class CmsUtil {
	public static String getTableName(Catalogue cata){
		if(cata.getVersion()==null)
			return cata.getTablename();
		return cata.getTablename()+"_"+cata.getVersion();
	}
	
	public static String getTableName(Content bean){
		Catalogue cata=bean.getCatalogue();
		
		String tableName=cata.getTablename();
		
		if(common.component.config.GlobeData.isTableMonth()){
			String createTime=DateUtil.DateToYyyyMM(bean.getCreateTime());
			
			if(createTime==null)
				createTime=DateUtil.NOWYYYYMM;
			
			tableName=tableName+createTime;
		}
		
		if(cata.getVersion()!=null)
			tableName=tableName+"_"+cata.getVersion();
		
		return tableName;
	}
}
