package cn.itcast.oa0909.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Kynamic implements Serializable{
	private Long kid;
	private Long pid;
	private String name;
	public Long getKid() {
		return kid;
	}

	public void setKid(Long kid) {
		this.kid = kid;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	@JSON(serialize=false)
	public Set<Version> getVersons() {
		return versons;
	}

	public void setVersons(Set<Version> versons) {
		this.versons = versons;
	}

	private Boolean isParent;
	
	private Set<Version> versons;
}
