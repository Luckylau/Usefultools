package tools.Net;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import example.SecurityGroup.SecuritygroupRuleVO;
import example.SecurityGroup.SecuritygroupServiceImpl;
import example.SecurityGroup.SecuritygroupVO;
import tools.Exception.ApiException;
import tools.Rest.NetJSON;


/**
 * 单元测试
 * @author Jasonlau
 *
 */
public class NetapiTestCase {
	private static final Logger logger = Logger.getLogger(NetapiTestCase.class);
	private static final SecuritygroupServiceImpl sgService = new SecuritygroupServiceImpl();
	private static final String Tenant_ID = "luckylau";
	
	@BeforeClass
	public static void tearUp()throws ApiException{
		NetApi.setRestpoint("http://10.0.38.113:9696/v2.0");
		NetApi.testConnect("http://10.0.38.113:9696/v2.0");
		logger.info("clean Security group ...");
	}
	
	@Test
	public void testCreateSecuritygroup(){
		String name = "测试-securitygroup-" + System.currentTimeMillis();
		String description="";
		SecuritygroupVO sgvo=null;
		try {
			sgvo = sgService.create(Tenant_ID, name,description);
            logger.debug("securitygroup >>> " + sgvo);
		} catch (ApiException e) {
			String type = e.getType();
            e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteSecuritygroup(){
		String id ="621b6822-118d-44c0-9416-6cef987519da";
		try{
			sgService.delete(Tenant_ID, id);
		}catch(ApiException e){
			String type = e.getType();
            e.printStackTrace();
		}
	}
	
	@Test
	public void testModifySecuritygroup(){
		String id="5eb937df-03e7-46b8-b71f-a359822fa9d7";
		String newName="1111111111111111111";
		try{
			sgService.modify(Tenant_ID, id, newName,"");
		}catch(ApiException e){
			String type = e.getType();
            e.printStackTrace();
		}
	}
		
	@Test
	public void testcreateRule(){
		String sgid="5eb937df-03e7-46b8-b71f-a359822fa9d7";
		SecuritygroupRuleVO vo=new SecuritygroupRuleVO();
		vo.setDirection("ingress");
		vo.setOrgId(Tenant_ID);
		vo.setPortrangeMin(80);
		vo.setPortrangeMax(80);
		vo.setProtocol("udp");
		vo.setRemoteipPrefix("190.0.0.6/24");
		vo.setSecuritygroupId(sgid);
		try {
			sgService.createRule(Tenant_ID,vo);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			String type = e.getType();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testdeleteRule(){
		String sgruleId="44df3d22-103e-4a65-9056-77c48ae87cfc";
		try {
			sgService.deleteRule(Tenant_ID, sgruleId);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			String type = e.getType();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testlistsecuritygroup(){
		List<SecuritygroupVO> sg=null;
		try {
			sg=sgService.list(Tenant_ID);
			System.out.println(NetJSON.toJson(sg));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
