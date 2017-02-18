package example.SecurityGroup;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import tools.Rest.NetJSON;
/**
 * 
 * @author Jasonlau
 *
 */
public class SecuritygroupRuleVO{
	
	@Expose
	private String id;
	
	@Expose
	@SerializedName("security_group_id")
	private String securitygroupId;
	
	@Expose
	private String direction;
	
	@Expose
	@SerializedName("port_range_min")
	private Integer portrangeMin;
	
	@Expose
	@SerializedName("port_range_max")
	private Integer portrangeMax;
	
	@Expose
	@SerializedName("ethertype")
	private String etherType;
	
	@Expose
	private String protocol;
	
	@Expose
	@SerializedName("remote_group_id")
	private String remotegroupId;
	
	@Expose
	@SerializedName("remote_ip_prefix")
	private String remoteipPrefix;
	
	
	@Expose
	@SerializedName("tenant_id")
	private String orgId;

    public SecuritygroupRuleVO(){
    	
    }
 
	public SecuritygroupRuleVO(String securitygroupId, String direction, Integer portrangeMin, Integer portrangeMax,
			String etherType, String protocol, String remotegroupId, String remoteipPrefix ) {

		this.securitygroupId = securitygroupId;
		this.direction = direction;
		this.portrangeMin = portrangeMin;
		this.portrangeMax = portrangeMax;
		this.etherType = etherType;
		this.protocol = protocol;
		this.remotegroupId = remotegroupId;
		this.remoteipPrefix= remoteipPrefix;
	}



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSecuritygroupId() {
		return securitygroupId;
	}


	public void setSecuritygroupId(String securitygroupId) {
		this.securitygroupId = securitygroupId;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public Integer getPortrangeMin() {
		return portrangeMin;
	}


	public void setPortrangeMin(Integer portrangeMin) {
		this.portrangeMin = portrangeMin;
	}


	public Integer getPortrangeMax() {
		return portrangeMax;
	}


	public void setPortrangeMax(Integer portrangeMax) {
		this.portrangeMax = portrangeMax;
	}


	public String getEtherType() {
		return etherType;
	}


	public void setEtherType(String etherType) {
		this.etherType = etherType;
	}


	public String getProtocol() {
		return protocol;
	}


	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


	public String getRemotegroupId() {
		return remotegroupId;
	}


	public void setRemotegroupId(String remotegroupId) {
		this.remotegroupId = remotegroupId;
	}


	public String getRemoteipPrefix() {
		return remoteipPrefix;
	}


	public void setRemoteipPrefix(String remoteipPrefix) {
		this.remoteipPrefix = remoteipPrefix;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Override
	public String toString(){
		 return NetJSON.toJson(this, "security_group_rule");
	}
}

