package common.component.ectable.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/**
 * ����Excel��DAO�ӿ�
 * @author itfish
 * 
 */
public interface ExportExcelDAOJdbc {

	/**
	 * ��ݴ����SQL���һ���¼Ϊһ��MAP��LIST
	 * 
	 * @param strSql
	 *            SQL���
	 * @return 
	 */
	public List getExportDataList(String strSql);

	/**
	 * ��ݹ����ֶ�4���һ����˼�¼Ϊһ��MAP��LIST
	 * 
	 * @param strSql
	 * @param filterName
	 * @return 
	 */
	public List getExportDataList(String strSql, List filterName);

	/**
	 * ��ݴ�����б�������ʹ����SQL����LIST
	 * 
	 * @param strSql
	 * @param rowMapper
	 * @return
	 */
	public List getExportDataList(String strSql, RowMapper rowMapper);
}
