package common.component.application.card.action;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.application.card.entity.Card;
import common.component.application.card.service.CardService;
import common.exception.ExistException;

public class CardAction extends BaseAction{

	private static final long serialVersionUID = 12L;

	private CardService cardService;
	
	private Card bean=new Card();


	public CardAction(CardService cardService) {
		super();
		this.cardService = cardService;
	}
	
	public String newOne() {
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = cardService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		
		try {
			cardService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}

		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			cardService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		if (bean.getId() != null) {
			cardService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		super.search(cardService,"select t.* from t_card t where 1=1 and t.p_deleted is null ");
		return SUCCESS;
	}
	
	public Card getBean() {
		return bean;
	}

	public void setBean(Card bean) {
		this.bean = bean;
	}

	public CardService getCardService() {
		return cardService;
	}

	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

}
