package _04_Money2.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _04_Money2.dao.MemPointDao;
import _04_Money2.model.MoneyBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.OrderItemBean;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.impl.OrderServiceImpl;

// 依照會員編號與訂單編號來讀取某筆訂單的所有資料，這些資料將封裝為一個OrderBean物件

@WebServlet("/_05_orderProcess/PointDetail.do")
public class PointDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("application/json; charset=utf-8");
//		String email=request.getParameter("email");
		MemPointDao mpd=new MemPointDao();
		List <MoneyBean> mb=mpd.getMoneyDetail(1,1);
		request.setAttribute("MoneyBean",mb);
		RequestDispatcher rd=request.getRequestDispatcher("st_point.jsp");
		rd.forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderNo = request.getParameter("orderNo");
		int no = Integer.parseInt(orderNo.trim());

		OrderService orderService = new OrderServiceImpl();
		OrderBean ob = orderService.getOrder(no);
		request.setAttribute("OrderBean", ob);   // 將OrderBean物件暫存到請求物件內
		RequestDispatcher rd = request.getRequestDispatcher("ShowOrderDetail.jsp");
		rd.forward(request, response);
		return;
	}

	public void displayOrderBean(OrderBean ob) {
		System.out.println("ob.getOrderNo()=" + ob.getOrderNo());
		System.out.println("ob.getMemberId()=" + ob.getMemberId());
		System.out.println("ob.getOrderDate=" + ob.getOrderDate());
		System.out.println("ob.getTotalAmount=" + ob.getTotalAmount());
		System.out.println("ob.getInvoiceTitle=" + ob.getInvoiceTitle());
		System.out.println("ob.getBNO=" + ob.getBno());
		System.out.println("ob.getShippingAddress=" + ob.getShippingAddress());
		System.out.println("ob.getCancelTag=" + ob.getCancelTag());
		System.out.println("==============訂單明細=================");
		Set<OrderItemBean> items = ob.getItems();
		for (OrderItemBean oib : items) {
			System.out.println("---------------一筆明細---------------");
			System.out.println("   oib.getSeqno()=" + oib.getSeqno());
			System.out.println("   oib.getOrderNo()=" + oib.getOrderNo());
			System.out.println("   oib.getBookId()=" + oib.getBookId());
			System.out.println("   oib.getDescription()=" + oib.getDescription());
			System.out.println("   oib.getQuantity()=" + oib.getQuantity());
			System.out.println("   oib.getUnitPrice()=" + oib.getUnitPrice());
			System.out.println("   oib.getDiscount()=" + oib.getDiscount());
		}
	}
}