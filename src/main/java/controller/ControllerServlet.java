package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CategoryFacade;

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

    @EJB
    private CategoryFacade categoryFacade;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("catalog", categoryFacade.findAll());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        // if category page is requested
        switch (userPath) {
            case ("/catalog"): {
                // get categoryId from request
                String categoryId = request.getQueryString();

                if (categoryId != null) {
                    // get selected category
                    Category selectedCategory = categoryFacade.find(Short.parseShort(categoryId));

                    // place selected category in request scope
                    request.setAttribute("selectedCategory", selectedCategory);

                    // get all products for selected category
                    Collection<Product> categoryProducts = selectedCategory.getProductCollection();

                    // place category products in request scope
                    request.setAttribute("categoryProducts", categoryProducts);
                }

                userPath = "/catalog_page";
                break;
            }
            case ("/viewCart"): {
                // TODO: Implement cart page request

                userPath = "/cart_page";

                // if checkout page is requested
                break;
            }
            case ("/checkout"): {
                // TODO: Implement checkout page request
                userPath = "/check_page";
                // if user switches language
                break;
            }
            case ("/chooseLanguage"): {
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

        // if addToCart action is called
        switch (userPath) {
            case ("/addToCart"): {
                break;
            }
            case ("/updateCart"): {
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
