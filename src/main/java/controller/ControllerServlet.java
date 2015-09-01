package controller;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CategoryFacade;
import session.ProductFacade;

@WebServlet(name = "ControllerServlet",
        loadOnStartup = 1,
        urlPatterns = {"/catalog",
            "/addToCart",
            "/viewCart",
            "/updateCart",
            "/checkout",
            "/purchase",
            "/chooseLanguage"})
public class ControllerServlet extends HttpServlet {

    private String surcharge;

    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductFacade productFacade;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);

        // initialize servlet with configuration information
        surcharge = servletConfig.getServletContext().getInitParameter("deliverySurcharge");

        // store category list in servlet context
        getServletContext().setAttribute("catalog", categoryFacade.findAll());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        Category selectedCategory;
        Collection<Product> categoryProducts;

        // if category page is requested
        switch (userPath) {
            case ("/catalog"): {
                // get categoryId from request
                String categoryId = request.getQueryString();

                if (categoryId != null) {
                    // get selected category
                    selectedCategory = categoryFacade.find(Short.parseShort(categoryId));

                    // place selected category in request scope
                    session.setAttribute("selectedCategory", selectedCategory);

                    // get all products for selected category
                    categoryProducts = selectedCategory.getProductCollection();

                    // place category products in request scope
                    session.setAttribute("categoryProducts", categoryProducts);
                }
                userPath = "/catalog_page";
                break;
            }
            case ("/viewCart"): {
                String clear = request.getParameter("clear");

                if ((clear != null) && clear.equals("true")) {

                    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                    cart.clear();
                }

                userPath = "/cart_page";
                break;
            }
            case ("/checkout"): {
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

                // calculate total
                cart.calculateTotal(surcharge);
                // forward to checkout page and switch to a secure channel
                userPath = "/check_page";
                break;
            }
            case ("/chooseLanguage"): {
                // TODO: Implement language request
                break;
            }
        }

        // use RequestDispatcher to forward request internally
        StringBuilder sb = new StringBuilder();
        sb.append("/WEB-INF/view");
        sb.append(userPath);
        sb.append(".jsp");
        String url = sb.toString();

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        // if addToCart action is called
        switch (userPath) {
            case ("/addToCart"): {
                // if user is adding item to cart for first time
                // create cart object and attach it to user session
                if (cart == null) {

                    cart = new ShoppingCart();
                    session.setAttribute("cart", cart);
                }

                // get user input from request
                String productId = request.getParameter("productId");

                if (!productId.isEmpty()) {

                    Product product = productFacade.find(Integer.parseInt(productId));
                    cart.addItem(product);
                }
                userPath = "/catalog_page";
                break;
            }
            case ("/updateCart"): {
                // get input from request
                String productId = request.getParameter("productId");
                String quantity = request.getParameter("quantity");

                Product product = productFacade.find(Integer.parseInt(productId));
                cart.update(product, quantity);

                userPath = "/cart_page";
                break;
            }
            case ("/purchase"): {
                // TODO: Implement purchase action

                userPath = "/buying_page";
                break;
            }
        }

        // use RequestDispatcher to forward request internally
        StringBuilder sb = new StringBuilder();
        sb.append("/WEB-INF/view");
        sb.append(userPath);
        sb.append(".jsp");
        String url = sb.toString();

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
