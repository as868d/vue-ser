package com.neuedu.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.pojo.SysMenu;
import com.neuedu.service.IMenuService;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.MenuServiceImpl;
import com.neuedu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class AddMenuServlet
 */
@WebServlet("/addMenu")
public class AddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	 *
	 */
    
    private String icon;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int parentId = Integer.parseInt(request.getParameter("parentId"));
			String menuName = request.getParameter("menuName");
			String orderNum = request.getParameter("orderNum");
			String path = request.getParameter("path");
			String visible = request.getParameter("visible");
			String icon = request.getParameter("icon");
			IMenuService service = new MenuServiceImpl();
			int menuId = 0;
			String parentName = null;
			try {
				menuId = service.selectMaxId()+1;
				parentName = service.selectParentName(parentId);
				//System.out.println(menuId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SysMenu sysMenu = new SysMenu(menuId, menuName, parentName, parentId, orderNum, path, visible, icon);
			try {
				int result = service.addMenu(sysMenu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
