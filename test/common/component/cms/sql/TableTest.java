package common.component.cms.sql;

import java.sql.Types;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.DialectFactory;
import org.hibernate.mapping.PrimaryKey;
import common.component.cms.sql.OTable;
import org.junit.Test;

import common.component.cms.sql.OColumn;

public class TableTest {

	@Test
	public void testSqlCreateString() {
		String catalog=null;
		String schema=null;
		
		OColumn idcolumn=OTable.getColumn("p_id",Types.VARCHAR,10);
		OColumn namecolumn=OTable.getColumn("p_name",Types.INTEGER,10);
		PrimaryKey primaryKey=OTable.getPrimaryKey("key_id",new OColumn[]{idcolumn});
		
		OTable table=new OTable("cms_news");
		table.setSchema(schema);
		table.setCatalog(catalog);
		table.addColumn(idcolumn);
		table.addColumn(namecolumn);
		table.setPrimaryKey(primaryKey);
		
		Dialect dialect=DialectFactory.buildDialect("org.hibernate.dialect.MySQL5Dialect");

		String sql=table.sqlCreateString(dialect, null, catalog, schema);
		
		System.out.println(sql);
	}
	

	
}
