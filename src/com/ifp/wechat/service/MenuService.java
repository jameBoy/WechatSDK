package com.ifp.wechat.service;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ifp.wechat.entity.menu.Button;
import com.ifp.wechat.entity.menu.Menu;
import com.ifp.wechat.util.WeixinUtil;

/**
 * 菜单创建
 * 
 * @author caspar.chen
 * @version 1.1
 * 
 */
public class MenuService {

	public static Logger log = Logger.getLogger(MenuService.class);

	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
	public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 菜单查询
	 */
	public static String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param jsonMenu
	 *            json格式
	 * @return 状态 0 表示成功、其他表示失败
	 */
	public static Integer createMenu(String jsonMenu) {
		int result = 0;
		String token = WeixinUtil.getToken();
		if (token != null) {
			// 拼装创建菜单的url
			String url = MENU_CREATE.replace("ACCESS_TOKEN", token);
			// 调用接口创建菜单
			JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", jsonMenu);

			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getInt("errcode");
					log.error("创建菜单失败 errcode:" + jsonObject.getInt("errcode")
							+ "，errmsg:" + jsonObject.getString("errmsg"));
				}
			}
		}
		return result;
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @return 0表示成功，其他值表示失败
	 */
	public static Integer createMenu(Menu menu) {
		return createMenu(JSONObject.fromObject(menu).toString());
	}


	/**
	 * 查询菜单
	 * 
	 * @return 菜单结构json字符串
	 */
	public static JSONObject getMenuJson() {
		JSONObject result = null;
		String token = WeixinUtil.getToken();
		if (token != null) {
			String url = MENU_GET.replace("ACCESS_TOKEN", token);
			result = WeixinUtil.httpsRequest(url, "GET", null);
		}
		return result;
	}

	/**
	 * 查询菜单
	 * @return Menu 菜单对象
	 */
	public static Menu getMenu() {
		JSONObject json = getMenuJson().getJSONObject("menu");
		System.out.println(json);
		Menu menu = (Menu) JSONObject.toBean(json, Menu.class);
		return menu;
	}

	public static void main(String[] args) {
//		getMenu();
		
		// 第二级菜单
		Button btn1_1 = new Button("知果观点","view","btn1_1","http://www.zhiguoguo.com/index.shtml",null);
		Button btn1_2 = new Button("思聊","view","btn1_2","http://s.zhiguoguo.com/",null);
		Button btn1_3 = new Button("","","",null,null);
		Button btn1_4 = new Button("","","",null,null);
		Button btn1_5 = new Button("","","",null,null);
		//第一级菜单
		Button btn1 = new Button("涨知识","click","btn1",null,new Button[]{btn1_1,btn1_2});
	
		Button btn2_1 = new Button("成为会员","view","btn2_1","http://www.zhiguoguo.com/reg",null);
		Button btn2_2 = new Button("订单查询","view","btn2_1","http://www.zhiguoguo.com/user/order?act=1",null);
		Button btn2_3 = new Button("","","",null,null);
		Button btn2_4 = new Button("","","",null,null);
		Button btn2_5 = new Button("","","",null,null);
		Button btn2 = new Button("知果会员","click","btn2",null,new Button[]{btn2_1,btn2_2});
		
		Button btn3_1 = new Button("知果果网","view","btn3_1","http://www.zhiguoguo.com/index.shtml",null);
		Button btn3_2 = new Button("免费注册商标","view","btn3_2","http://www.zhiguoguo.com/product/show/tr_reg",null);
		Button btn3_3 = new Button("","","",null,null);
		Button btn3_4 = new Button("","","",null,null);
		Button btn3_5 = new Button("","","",null,null);
		Button btn3 = new Button("免费商标","click","btn3",null,new Button[]{btn3_1,btn3_2});
		
		
	

		Menu menu = new Menu(new Button[] { btn1, btn2, btn3 });
		int result = createMenu(menu);
		log.info(""+result);
		
	}
}
