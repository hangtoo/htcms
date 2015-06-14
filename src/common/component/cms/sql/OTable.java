package common.component.cms.sql;

import java.sql.Types;

import org.hibernate.mapping.PrimaryKey;

import common.util.StringUtil;

public class OTable extends org.hibernate.mapping.Table {
	public OTable(String name) {
		super(name);
	}
	
	public static OColumn getColumn(String columnname,int typecode,Integer typelength){
		OColumn column=new OColumn();
		column.setName(columnname);
		
		if(typecode==Types.BLOB||typecode==Types.CLOB){//对大字段特殊处理，存放在固定的一张表里
			column.setSqlTypeCode(Types.VARCHAR);
			column.setLength(255);
		}else{
			column.setSqlTypeCode(typecode);
			column.setLength(typelength);
		}

		return column;
	}
	
	public static OColumn getColumn(String columnname,String typecode,String typelength){
		int code=Integer.valueOf(typecode);
		int length=0;
		
		if(!StringUtil.isEmptyString(typelength))
			length=Integer.valueOf(typelength);
		
		return getColumn(columnname,code,length);
	}
	
	public static PrimaryKey getPrimaryKey(String keyname,OColumn[] column){
		PrimaryKey primaryKey=new PrimaryKey();
		primaryKey.setName(keyname);
		for(int i=0;i<column.length;i++)
			primaryKey.addColumn(column[i]);
		return primaryKey;
	}
}
