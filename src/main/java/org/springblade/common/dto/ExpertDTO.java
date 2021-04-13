package org.springblade.common.dto;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExpertDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	private Long personId;

	private String zgxl;

	private String zgxw;

	private String zjlx;

	private String zjly;

	private String rzdw;

	private String szejdw;

	private String zw;

	private String zc;

	private String ys;

	private String zyyjfx;

	private String gbxkfl;

	private String gjzrkxjjxkfl;

	private String gxjslyxkfl;

	private String gmjjhyxkfl;

	private String cjxzjljh;

	private String gjjcqnkxjjxm;

	private String yxqnkxjjxm;

	private String gjqrjh;

	private String gjwrjh;

	private String ynsqrjh;

	private String ynswrjh;

	private String qtrcch;

	private String grjj;

	private JSONArray kyxm;

	private String zysp;

	private JSONArray kycg;

	private JSONArray hjjl;

	private JSONArray lw;

	private JSONArray zzcb;

}
