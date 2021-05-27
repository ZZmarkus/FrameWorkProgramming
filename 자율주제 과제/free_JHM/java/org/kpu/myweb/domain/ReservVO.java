package org.kpu.myweb.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservVO {
	private String resnum;
	private String resname;
	private String resprice;
	private String resdate;
	private String resuserid;
	private String resusername;
	public String getResnum() {
		return resnum;
	}
	public void setResnum(String resnum) {
		this.resnum = resnum;
	}
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	public String getResprice() {
		return resprice;
	}
	public void setResprice(String resprice) {
		this.resprice = resprice;
	}
	public String getResdate() {
		return resdate;
	}
	public void setResdate(String resdate) {
		this.resdate = resdate;
	}
	public String getResuserid() {
		return resuserid;
	}
	public void setResuserid(String resuserid) {
		this.resuserid = resuserid;
	}
	public String getResusername() {
		return resusername;
	}
	public void setResusername(String resusername) {
		this.resusername = resusername;
	}

}
