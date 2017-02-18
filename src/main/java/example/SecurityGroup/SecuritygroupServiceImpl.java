package example.SecurityGroup;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;

import tools.Exception.ApiException;
import tools.Net.NetApi;
import tools.Rest.NetJSON;



/**
 * 
 * @author Jasonlau
 *
 */
@Service("securitygroupService")
public class SecuritygroupServiceImpl implements SecuritygroupService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(SecuritygroupServiceImpl.class);

	public SecuritygroupVO create(String tenantId, String name,String description) throws ApiException {
		LOGGER.info("create security group :"+ name);
		SecuritygroupVO vo=null;
		if (StringUtils.isEmpty(name)){
			LOGGER.error("名字禁止为空");
			throw new ApiException("名字禁止为空");
		}
		if(StringUtils.isEmpty(description)){
			vo=new SecuritygroupVO(name);
		}else{
			vo=new SecuritygroupVO(name,description);
		}
		
		String json=NetApi.doPost(tenantId, NetApi.REST_POINT+"/security-groups", vo.toString());
		vo=NetJSON.fromJson(json, "security_group", SecuritygroupVO.class);
		return vo;
	}

	public void delete(String tenantId, String id) throws ApiException {
		LOGGER.info("deleting security group :" + id);
		if (StringUtils.isEmpty(id)) {
			LOGGER.error("ID 禁止为空！");
			throw new ApiException("ID 禁止为空！");
		}
		NetApi.doDelete(tenantId, NetApi.REST_POINT + "/security-groups" + "/" + id);
		
	}

	public void modify(String tenantId, String id, String newName,String description) throws ApiException {
		LOGGER.info("modifying security group : " + id);
		SecuritygroupVO vo=null;
		if (null==description){
			vo = new SecuritygroupVO(newName);
		}else{
			vo = new SecuritygroupVO(newName,description);
		}
		
		NetApi.doPut(tenantId, NetApi.REST_POINT + "/security-groups" + "/" + id, vo.toString());		
	}

	public SecuritygroupVO details(String tenantId, String id) throws ApiException {
		LOGGER.info("detailing security group : " + id);
		SecuritygroupVO vo=null;
		String json=NetApi.doGet(tenantId,NetApi.REST_POINT + "/security-groups" + "/" + id);
		vo=NetJSON.fromJson(json, "security_group", SecuritygroupVO.class);
		return vo;
	}

	public SecuritygroupRuleVO createRule(String tenantId,SecuritygroupRuleVO sgrule)
					throws ApiException {
		LOGGER.info("create security group rules");
		if(!StringUtils.isEmpty(sgrule.getRemotegroupId())){
			SecuritygroupRuleVO vo=new SecuritygroupRuleVO(sgrule.getSecuritygroupId(),sgrule.getDirection(),sgrule.getPortrangeMin(),sgrule.getPortrangeMax(),sgrule.getEtherType(),sgrule.getProtocol(),sgrule.getRemotegroupId(),null);
			String json=NetApi.doPost(tenantId, NetApi.REST_POINT+"/security-group-rules", vo.toString());
			vo=NetJSON.fromJson(json, "security_group_rule", SecuritygroupRuleVO.class);
			return vo;
		}else if (!StringUtils.isEmpty(sgrule.getRemoteipPrefix())){
			SecuritygroupRuleVO vo=new SecuritygroupRuleVO(sgrule.getSecuritygroupId(),sgrule.getDirection(),sgrule.getPortrangeMin(),sgrule.getPortrangeMax(),"Ipv4",sgrule.getProtocol(),null,sgrule.getRemoteipPrefix());
			String json=NetApi.doPost(tenantId, NetApi.REST_POINT+"/security-group-rules", vo.toString());
			vo=NetJSON.fromJson(json, "security_group_rule", SecuritygroupRuleVO.class);
			return vo;
		}
		return null;
	}
	
	public void deleteRule(String tenantId, String id) throws ApiException {
		LOGGER.info("deleting security group rule:" + id);
		if (StringUtils.isEmpty(id)) {
			LOGGER.error("ID 禁止为空！");
			throw new ApiException("ID 禁止为空！");
		}
		NetApi.doDelete(tenantId, NetApi.REST_POINT + "/security-group-rules" + "/" + id);
		
		
	}

	public List<SecuritygroupVO> list(String tenantId) throws ApiException {
		String url = NetApi.REST_POINT + "/security-groups";
        String json = NetApi.doGet(tenantId,url);
		List<SecuritygroupVO> sgs = NetJSON.fromJson(json, "security_groups", new TypeToken<List<SecuritygroupVO>>() {
		}.getType());
		return sgs;
	}



}
