package com.myjeju.vo;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class HouseVO {
	String hid, h_name, h_infor, h_infor2, h_tag, h_addr, h_vpoint, h_hpoint, h_hp, h_img, email, h_file;
	int  rno, h_like, status, review_count;
	float star_avg;
	CommonsMultipartFile hfile1;
	
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	public String getH_name() {
		return h_name;
	}
	public void setH_name(String h_name) {
		this.h_name = h_name;
	}
	public String getH_infor() {
		return h_infor;
	}
	public void setH_infor(String h_infor) {
		this.h_infor = h_infor;
	}
	public String getH_infor2() {
		return h_infor2;
	}
	public void setH_infor2(String h_infor2) {
		this.h_infor2 = h_infor2;
	}
	public String getH_tag() {
		return h_tag;
	}
	public void setH_tag(String h_tag) {
		this.h_tag = h_tag;
	}
	public String getH_addr() {
		return h_addr;
	}
	public void setH_addr(String h_addr) {
		this.h_addr = h_addr;
	}
	public String getH_vpoint() {
		return h_vpoint;
	}
	public void setH_vpoint(String h_vpoint) {
		this.h_vpoint = h_vpoint;
	}
	public String getH_hpoint() {
		return h_hpoint;
	}
	public void setH_hpoint(String h_hpoint) {
		this.h_hpoint = h_hpoint;
	}
	public String getH_hp() {
		return h_hp;
	}
	public void setH_hp(String h_hp) {
		this.h_hp = h_hp;
	}
	public String getH_img() {
		return h_img;
	}
	public void setH_img(String h_img) {
		this.h_img = h_img;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getH_like() {
		return h_like;
	}
	public void setH_like(int h_like) {
		this.h_like = h_like;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public float getStar_avg() {
		return star_avg;
	}
	public void setStar_avg(float star_avg) {
		this.star_avg = star_avg;
	}
	public String getH_file() {
		return h_file;
	}
	public void setH_file(String h_file) {
		this.h_file = h_file;
	}
	public CommonsMultipartFile getHfile1() {
		return hfile1;
	}
	public void setHfile1(CommonsMultipartFile hfile1) {
		this.hfile1 = hfile1;
	}
	
	
	
}
