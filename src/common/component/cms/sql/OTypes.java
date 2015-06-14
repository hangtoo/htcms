package common.component.cms.sql;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class OTypes {
	private static Map<Integer, String> types = new HashMap<Integer, String>();

	private Integer key;

	private OTypes() {
		types.put(Types.VARCHAR, "VARCHAR");
		types.put(Types.BLOB, "BLOB");
		types.put(Types.CLOB, "CLOB");

		types.put(Types.BIT, "BIT");
		types.put(Types.INTEGER, "INTEGER");
		types.put(Types.FLOAT, "FLOAT");
		types.put(Types.DOUBLE, "DOUBLE");
		types.put(Types.DECIMAL, "DECIMAL");

		types.put(Types.BOOLEAN, "BOOLEAN");
		types.put(Types.DATE, "DATE");
	}

	public static Map getTypes() {
		return types;
	}

	public void setTypes(Map types) {
		this.types = types;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		if (key == null)
			return "";
		return types.get(key);
	}

	public int getBIT() {
		return Types.BIT;
	}

	public int getTINYINT() {
		return Types.TINYINT;
	}

	public int getSMALLINT() {
		return Types.SMALLINT;
	}

	public int getINTEGER() {
		return Types.INTEGER;
	}

	public int getBIGINT() {
		return Types.BIGINT;
	}

	public int getFLOAT() {
		return Types.FLOAT;
	}

	public int getREAL() {
		return Types.REAL;
	}

	public int getDOUBLE() {
		return Types.DOUBLE;
	}

	public int getNUMERIC() {
		return Types.NUMERIC;
	}

	public int getDECIMAL() {
		return Types.DECIMAL;
	}

	public int getCHAR() {
		return Types.CHAR;
	}

	public int getVARCHAR() {
		return Types.VARCHAR;
	}

	public int getLONGVARCHAR() {
		return Types.LONGVARCHAR;
	}

	public int getDATE() {
		return Types.DATE;
	}

	public int getTIME() {
		return Types.TIME;
	}

	public int getTIMESTAMP() {
		return Types.TIMESTAMP;
	}

	public int getBINARY() {
		return Types.BINARY;
	}

	public int getVARBINARY() {
		return Types.VARBINARY;
	}

	public int getLONGVARBINARY() {
		return Types.LONGVARBINARY;
	}

	public int getNULL() {
		return Types.NULL;
	}

	public int getOTHER() {
		return Types.OTHER;
	}

	public int getJAVA_OBJECT() {
		return Types.JAVA_OBJECT;
	}

	public int getDISTINCT() {
		return Types.DISTINCT;
	}

	public int getSTRUCT() {
		return Types.STRUCT;
	}

	public int getARRAY() {
		return Types.ARRAY;
	}

	public int getBLOB() {
		return Types.BLOB;
	}

	public int getCLOB() {
		return Types.CLOB;
	}

	public int getREF() {
		return Types.REF;
	}

	public int getDATALINK() {
		return Types.DATALINK;
	}

	public int getBOOLEAN() {
		return Types.BOOLEAN;
	}

	public int getROWID() {
		return Types.ROWID;
	}

	public int getNCHAR() {
		return Types.NCHAR;
	}

	public int getNVARCHAR() {
		return Types.NVARCHAR;
	}

	public int getLONGNVARCHAR() {
		return Types.LONGNVARCHAR;
	}

	public int getNCLOB() {
		return Types.NCLOB;
	}

	public int getSQLXML() {
		return Types.SQLXML;
	}

}
