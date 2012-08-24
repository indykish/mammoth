package org.megam.mammoth.cloud.compute.info;

public class CloudInstance {

	private String instid;
	private String imgid;
	private String insttype;
	private String state;
	private String Desc;
	private String name;
	private String platfrm;
	private String Owner;
	private String publ;
	private String Id;
	private String error;
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getInstid() {
		return instid;
	}

	public void setInstid(String instid) {
		this.instid = instid;
	}

	public String getImgid() {
		return imgid;
	}

	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

	public String getInsttype() {
		return insttype;
	}

	public void setInsttype(String insttype) {
		this.insttype = insttype;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatfrm() {
		return platfrm;
	}

	public void setPlatfrm(String platfrm) {
		this.platfrm = platfrm;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getPubl() {
		return publ;
	}

	public void setPubl(String publ) {
		this.publ = publ;
	}

	public String getId() {
		return Id;
	}

	
	}
