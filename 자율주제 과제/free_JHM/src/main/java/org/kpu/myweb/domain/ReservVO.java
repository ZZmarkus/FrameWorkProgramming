package org.kpu.myweb.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservVO {
	private String resid;
	private String resname;
	private String resdate;
	private String resusername;
	private String resusermobile;
	private String resuseremail;
	
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}

	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getResdate() {
		return resdate;
	}
	public void setResdate(String resdate) {
		this.resdate = resdate;
	}
	public String getResusermobile() {
		return resusermobile;
	}
	public void setResusermobile(String resusermobile) {
		this.resusermobile = resusermobile;
	}
	public String getResuseremail() {
		return resuseremail;
	}
	public void setResuseremail(String resuseremail) {
		this.resuseremail = resuseremail;
	}
	public String getResusername() {
		return resusername;
	}
	public void setResusername(String resusername) {
		this.resusername = resusername;
	}

}
