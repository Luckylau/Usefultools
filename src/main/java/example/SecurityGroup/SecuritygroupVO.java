package example.SecurityGroup;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import tools.Rest.NetJSON;

/**
 * 安全组实体
 * @author Jasonlau
 *
 */
public class SecuritygroupVO {
	
	@Expose
	private String id;
	
	@Expose
	private String name;
	
	@Expose
	private String description;
	
	@Expose
	@SerializedName("tenant_id")
	private String orgId;

	@Expose
	@SerializedName("security_group_rules")
	private List<SecuritygroupRuleVO> sgRules;
	
	
	public SecuritygroupVO() {
		
	}
	public SecuritygroupVO(String name) {
		this.name = name;
	}
	
	public SecuritygroupVO(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public List<SecuritygroupRuleVO> getSgRules() {
		return sgRules;
	}
	public void setSgRules(List<SecuritygroupRuleVO> sgRules) {
		this.sgRules = sgRules;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString(){
		 return NetJSON.toJson(this, "security_group");
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		SecuritygroupVO ob=(SecuritygroupVO)obj;
		return Objects.equals(id, ob.getId());
	}
	
	
}


