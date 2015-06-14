package common.component.application.card.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.component.application.card.dao.CardDao;
import common.component.application.card.entity.Card;
import common.component.application.card.service.CardService;
import common.component.application.card.GlobeData;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�卡模块，卡登陆验证、卡增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public class CardServiceImpl extends EcTableServiceImpl implements CardService{
	private CardDao cardDao;
	
	public CardServiceImpl(CardDao cardDao,EcTableDAOJdbc ecTableDAO) {
		this.cardDao = cardDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Card save(Card card) throws ExistException {
		cardDao.save(card);
		
		initCard(card.getHotel().getId(), card);
		
		return card;
	}
	

	@Override
	public void delete(String id) {
		cardDao.deleteTag(id);
	}

	@Override
	public Card update(Card newcard) {
		
	    Card card = getById(newcard.getId());
	    card.setName(newcard.getName());
	    card.setDesc(newcard.getDesc());
	    card.setRemark(newcard.getRemark());
	    
		card=(Card)cardDao.update(card);
		
		initCard(newcard.getHotel().getId());
		return card;
	}
	
	public Card getById(final Serializable id){
		
		Object obj=this.cardDao.getById(id);
		
		if(obj instanceof Card)
			return (Card)obj;
		return null;
	}

	@Override
	public Card getById(String id) {
		Object obj=this.cardDao.getById(id);
		
		if(obj instanceof Card)
			return (Card)obj;
		return null;
	}

	@Override
	public List<Card> getList() {
		String condition=" and deleted is null order by area desc,id desc";
		List list = cardDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Card> getList(String condition) {
		List list = cardDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Card> getCardParas(String cardkey) {
		List list = cardDao.gets(" and area='"+cardkey+"' order by area desc,id desc");
		return list;
	}
	
	/**
	 * 显示参数
	 */
	public List<Card> getCardParas() {
		List<Card> ret=getList();
		return ret;
	}
	
	//启动时，初始化
	public void initCard(){
		List<Card> paras=this.getCardParas();
		
		for(int i=0;i<paras.size();i++){
			
			List<Card> list=GlobeData.getCard(paras.get(i).getHotel().getId());
			
			if(list==null)
				list=new ArrayList<Card>();
			
			list.add(paras.get(i));//更新数值
			
			GlobeData.setCard(paras.get(i).getHotel().getId(), list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(paras.get(i).getHotel().getId()+":"+paras.get(i).getName());
			
		}
	}
	
	//适用于新增时候，增加键值对
	public void initCard(String cardkey,Card cardvalue){

			List<Card> list=GlobeData.getCard(cardkey);
			
			if(list==null)
				list=new ArrayList<Card>();
			
			list.add(cardvalue);//新增数值
			
			GlobeData.setCard(cardkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(cardkey+":"+cardvalue);

	}
	
	//适用于修改时候，初始化指定key的值，对指定主键重新进行初始化
	public void initCard(String cardkey){

		List<Card> paras=this.getCardParas(cardkey);
		
		for(int i=0;i<paras.size();i++){
			
			List<Card> list=new ArrayList<Card>();
			
			list.add(paras.get(i));//更新数值
			
			GlobeData.setCard(cardkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(cardkey+":"+paras.get(i));
			
		}
	}

}
