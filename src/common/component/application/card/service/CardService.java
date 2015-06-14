package common.component.application.card.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.application.card.entity.Card;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;

/**
 * @author huanglf
 *
 * 卡模块，卡增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public interface CardService extends EcTableService{
	
	/**
	 * 功能：卡新增、修改
	 * 描述：
	 */
	public Card save(Card Card) throws ExistException;
	
	/**
	 * 功能：卡删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：卡修改
	 * 描述：
	 */
	public Card update(Card Card);
	
	/**
	 * 功能：卡查询
	 * 描述：
	 */
	public Card getById(String id);

	/**
	 * 功能：卡列表查询
	 * 描述：
	 */
	public List<Card> getList(String condition);
	
	/**
	 * 功能：卡列表查询
	 * 描述：
	 */
	public List<Card> getList();

	/**
	 * 功能：卡列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

	
	public List<Card> getCardParas(String cardkey);
	
	public void initCard();
	public void initCard(String cardkey,Card cardvalue);
	public void initCard(String cardkey);
}
