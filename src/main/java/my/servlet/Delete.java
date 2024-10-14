/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my.common.DatabaseUtil;

/**
 *
 * @author ADMIN
 */
public class Delete extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //b1. Lấy giá trị tham số từ client
        String id = request.getParameter("id");
        //b2. Xử lý yêu cầu (truy cập CSDL để đọc tất cả các users)
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DatabaseUtil.getConnection();
            //3. Tạo đối tượng thi hành truy vấn
            ps =conn.prepareStatement("delete from users where id=" + id);
            //Truyền giá trị chp các tham số trong câu lệnh sql
            
            //4. Thi hành truy vấn
            int kq= ps.executeUpdate();
            //5. Xử lý kết quả trả về
            if(kq>0)
            {
                out.println("<h2>Đã xoá 1 user thành công</h2>");
            }else
            {
                out.println("<h2>Thao tác xoá user thất bại</h2>");
            }
            //6. dong ket noi
            conn.close();
        }catch (Exception e){
            System.out.println("Loi:" + e.toString());
            out.println("<h2>Thao tác xoá user thất bại</h2>");
        }
        //Chèn nội dung của ViewServlet vào kết nối hồi đáp
        request.getRequestDispatcher("ViewServlet").include(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Delete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Delete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
