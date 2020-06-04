package com.chinaweal.sdcs.crcs.common.entity.dto.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求参数包装
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 14:18
 **/
@ApiModel(value = "分页请求体")
public class PageRequestDto {

	private static final Integer DEFAULT_PAGE = 1;

	private static final Integer DEFAULT_PAGE_SIZE = 10;

	private static final String ORDER_DEFAULT = "updatedate";

	private static final String COMMA = ",";
	/**
	 * 当前分页
	 */
	@ApiModelProperty(value = "当前分页（默认1）")
	private Integer curPage;
	/**
	 * 分页大小
	 */
	@ApiModelProperty(value = "分页大小（默认10）")
	private Integer pageSize;
	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段（默认updatedate）")
	private String orderField;
	/**
	 * 排序规则
	 */
	@ApiModelProperty(value = "排序规则（默认desc）")
	private String orderingRule;
	/**
	 * 排序规则布尔值
	 */
	private Boolean orderingRuleBoolean;
	/**
	 * 	参数直接用key-value作为查询条件
	 */
	@ApiModelProperty(value = "直接使用key-value作为eq参数的map")
	private Map<String, Object> paramMap;
	/**
	 * 	自定义过滤条件，用于特殊处理
	 */
	@ApiModelProperty(value = "使用key-value作为自定义判断的map")
	private Map<String, Object> customParamMap;
	/**
	 *  参数直接用key-value作为like查询条件
	 */
	@ApiModelProperty(value = "使用key-value作为like %% 的map")
	private Map<String, String> likeParamMap;
	/**
	 *  参数直接用key-value作为in查询条件
	 */
	@ApiModelProperty(value = "使用key-value作为in参数的map，in的内容用半角逗号隔开，推荐前端直接使用array对象的toString（）方法")
	private Map<String, String> inParamMap;

	/**
	 * 包装key-value的eq和like查询条件
	 * @param wrapper 包装对象
	 * @param <T> 包装类型
	 * @return 封装参数后的包装对象
	 * @author lroyia
	 */
	public <T> QueryWrapper<T> packingParam(QueryWrapper<T> wrapper){
		// 常规参数封装
		if (MapUtils.isNotEmpty(paramMap)) {
			paramMap.forEach(wrapper::eq);
			//paramMap.foreach((k,v)->wrapper.eq(k,v));
		}
		// 常规like参数封装
		if (MapUtils.isNotEmpty(likeParamMap)) {
			likeParamMap.forEach((k,v) -> {
				if(StringUtils.isNotBlank(v)){
					wrapper.like(k, v);
				}
			});
		}
		// 常规in参数封装,约定参数使用,隔开
		if(MapUtils.isNotEmpty(inParamMap)){
			inParamMap.forEach((k,v)->{
				if(StringUtils.isNotBlank(v)){
					wrapper.in(k, v.split(COMMA));
				}
			});
		}
		return wrapper;
	}

	/**
	 * 校验分页参数自定义参数map
	 * @param pageRequestDto 分页dto
	 * @return pageRequestDto
	 */
	public static PageRequestDto checkCustomParamMapEmpty(PageRequestDto pageRequestDto){
		if(pageRequestDto == null) pageRequestDto = new PageRequestDto();
		Map<String, Object> customParamMap = pageRequestDto.getCustomParamMap();
		if(customParamMap == null){
			customParamMap = new HashMap<>();
			pageRequestDto.setCustomParamMap(customParamMap);
		}
		return pageRequestDto;
	}

	/**
	 *
	 * @param curPageDefault 默认当前分页
	 * @param pageSizeDefault 默认分页大小
	 * @param orderFieldDefault 默认排序规则
	 * @return 默认分页参数检查
	 */
	public PageRequestDto checkDefault(Integer curPageDefault, Integer pageSizeDefault, String orderFieldDefault){
		if(curPageDefault == null) curPageDefault = 1;
		if(pageSizeDefault == null) pageSizeDefault = DEFAULT_PAGE_SIZE;
		if(StringUtils.isBlank(orderFieldDefault)) orderFieldDefault = ORDER_DEFAULT;
		this.curPage = this.curPage == null ? curPageDefault : this.curPage;
		this.pageSize = this.pageSize == null ? pageSizeDefault : this.pageSize;
		this.orderField = StringUtils.isBlank(this.orderField) ? orderFieldDefault : this.orderField;
		this.orderingRuleBoolean = !(StringUtils.isBlank(this.orderingRule)
				|| this.orderingRule.equalsIgnoreCase("desc"));
		return this;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderingRule() {
		return orderingRule;
	}

	public void setOrderingRule(String orderingRule) {
		this.orderingRule = orderingRule;
	}

	public Boolean getOrderingRuleBoolean() {
		return orderingRuleBoolean;
	}

	public void setOrderingRuleBoolean(Boolean orderingRuleBoolean) {
		this.orderingRuleBoolean = orderingRuleBoolean;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Map<String, Object> getCustomParamMap() {
		return customParamMap;
	}

	public void setCustomParamMap(Map<String, Object> customParamMap) {
		this.customParamMap = customParamMap;
	}

	public Map<String, String> getLikeParamMap() {
		return likeParamMap;
	}

	public void setLikeParamMap(Map<String, String> likeParamMap) {
		this.likeParamMap = likeParamMap;
	}

	public Map<String, String> getInParamMap() {
		return inParamMap;
	}

	public void setInParamMap(Map<String, String> inParamMap) {
		this.inParamMap = inParamMap;
	}
}
