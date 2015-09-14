package com.winston.jornada.controller.jsf.mapa;

import java.util.List;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefault;
import com.powerlogic.jcompany.config.collaboration.PlcConfigSelection;
import com.powerlogic.jcompany.controller.rest.controllers.PlcBaseGridController;
import com.powerlogic.jcompany.controller.util.PlcIocControllerFacadeUtil;
import com.winston.jornada.entity.mapa.Mapa;
import com.winston.jornada.facade.IAppFacade;

@Specializes
public class MapaGridController<E, I> extends PlcBaseGridController<E, I> {

//	@Inject
//	@QPlcDefault
//	protected PlcBeanPopulateUtil beanPopulateUtil;
//
//
//	@Inject @Named(PlcConstants.PlcJsfConstantes.PLC_CONTROLE_CONVERSACAO)
//	protected PlcConversationControl plcControleConversacao;	
//
//	@Inject @QPlcDefault 
//	protected PlcCreateContextUtil contextMontaUtil;	
//
//    @Inject @QPlcDefault 
//    protected PlcContextUtil contextUtil;
	
	@Inject @QPlcDefault 
	protected PlcIocControllerFacadeUtil iocControleFacadeUtil;

	private HttpServletRequest request;

	private Integer page;

	private Integer rows;

	private String orderBy;

	private String order;

	private Long total;

	public HttpServletRequest getRequest() {
		return request;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getRows() {
		return rows;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getOrder() {
		return order;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Inject
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Inject
	public void setPage(@QueryParam("page") @DefaultValue("1") Integer page) {
		this.page = page;
	}

	@Inject
	public void setRows(@QueryParam("rows") @DefaultValue("10") Integer rows) {
		this.rows = rows;
	}

	@Inject
	public void setOrderBy(@QueryParam("sidx") String orderBy) {
		this.orderBy = orderBy;
	}

	@Inject
	public void setOrder(@QueryParam("sord") String order) {
		this.order = order;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void retrieveCollection() {

		try {
			Object instancia = getEntityType().newInstance();
			
			if (instancia instanceof Mapa) {
				Mapa mapa = (Mapa) instancia;
				this.beanPopulateUtil.transferMapToBean(request.getParameterMap(), mapa);
				this.retrieveCollectionBefore();
				this.setTotal(iocControleFacadeUtil.getFacade(IAppFacade.class).recuperarContagemMapa(getContext(), mapa));
				List<E> lista = (List<E>) iocControleFacadeUtil.getFacade(IAppFacade.class).recuperarMapa(getContext(), mapa, ((page - 1) * rows), (rows));
				this.setEntityCollection(lista);
				this.retrieveCollectionAfter();
			} else {
				PlcConfigSelection configSelecao = getConfigCollaboration().selection();

				String querySel = null;
				if (configSelecao != null) {
					querySel = configSelecao.apiQuerySel();
				}

				PlcBaseContextVO context = getContext();

				context.setApiQuerySel(StringUtils.isNotBlank(querySel) ? querySel : null);

				String orderByDinamico = null;
				if (StringUtils.isNotEmpty(orderBy)) {
					orderByDinamico = orderBy + " " + StringUtils.defaultIfEmpty(order, "asc");
				}

				this.beanPopulateUtil.transferMapToBean(request.getParameterMap(), instancia);
				
				this.retrieveCollectionBefore();

				this.setTotal(getFacade().findCount(context, instancia));

				List<E> lista = (List<E>) getFacade().findList(context, instancia, orderByDinamico, ((page - 1) * rows), (rows));
				
				while(lista.isEmpty() && page>1){
					page--;
					lista = (List<E>) getFacade().findList(context, instancia, orderByDinamico, ((page - 1) * rows), (rows));
				}
				
				this.setEntityCollection(lista);
				this.retrieveCollectionAfter();
			}
			
		} catch (Exception e) {
			handleExceptions(e);
		}
	}

}
