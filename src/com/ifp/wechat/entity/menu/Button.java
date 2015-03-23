package com.ifp.wechat.entity.menu;

/**
 * 按钮
 * 
 * @author caspar.chen
 * @version 1.0
 * 
 */
public class Button {

	/**
	 * 按钮名称
	 */
	private String name;

	/**
	 * 按钮类型   
     * 1、click：点击推事件
     * 2、view：跳转URL
	 * 3、scancode_push：扫码推事件
	 * 4、scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框
	 * 5、pic_sysphoto：弹出系统拍照发图
	 * 6、pic_photo_or_album：弹出拍照或者相册发图
	 * 7、pic_weixin：弹出微信相册发图器
	 * 8、location_select：弹出地理位置选择器
	 * 3到8的所有事件，仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户，旧版本微信用户点击后将没有回应，开发者也不能正常接收到事件推送。
	 */
	private String type;

	/**
	 * 按钮key值    菜单KEY值，用于消息接口推送，不超过128字节
	 */
	private String key;

	/**
	 * 按钮url
	 */
	private String url;

	/**
	 * 子按钮列表
	 */
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Button(String name, String type, String key, String url,
			Button[] sub_button) {
		super();
		this.name = name;
		this.type = type;
		this.key = key;
		this.url = url;
		this.sub_button = sub_button;
	}

	public Button() {
		super();
	}

}
