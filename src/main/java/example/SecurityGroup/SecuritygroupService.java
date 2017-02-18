package example.SecurityGroup;


import java.util.List;

import tools.Exception.ApiException;



/**
 * SecurityGroup服务
 * @author Jasonlau
 *
 */
public interface SecuritygroupService{
	
	
	public SecuritygroupVO create(String tenantId,String name,String description) throws ApiException;
	
	public void delete(String tenantId,String id)throws ApiException;
	
	public void modify(String tenantId,String id,String name,String description)throws ApiException;
	
	public SecuritygroupVO details(String tenantId,String id)throws ApiException;
	
	public SecuritygroupRuleVO createRule(String tenantId,SecuritygroupRuleVO sgrule)throws ApiException;
	
	public void deleteRule(String tenantId, String id)throws ApiException;
	
	public List<SecuritygroupVO> list(String orgId)throws ApiException;

}
