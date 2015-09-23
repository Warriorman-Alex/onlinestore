package controller;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import session.CategoryFacade;
import session.OrderManager;
import session.ProductFacade;
import validate.Validator;

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
    private static final Logger logger = Logger.getLogger(ControllerServlet.class);

    private String surcharge;

    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private OrderManager orderManager;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("start initialize servlet");
        
        super.init(servletConfig);       
        
        // initialize servlet with configuration information
        surcharge = servletConfig.getServletContext().getInitParameter("deliverySurcharge");
        logger.debug(surcharge);     
        
        // store category list in servlet context
        getServletContext().setAttribute("catalog", categoryFacade.findAll());
        
        logger.info("end initialize servlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();        
        HttpSession session = request.getSession();
        Category selectedCategory;
        Collection<Product> categoryProducts;
        
        logger.debug(userPath);
        logger.debug(session);

        // if category page is requested
        switch (userPath) {
            case ("/catalog"): {
                // get categoryId from request
                String categoryId = request.getQueryString();
                logger.debug(categoryId);
                
                if (categoryId != null) {
                    // get selected category                   
                    
                    selectedCategory = categoryFacade.find(categoryFacade.findIdByName(categoryId));
                    logger.debug(selectedCategory);
                    
                    // place selected category in request scope
                    session.setAttribute("selectedCategory", selectedCategory);

                    // get all products for selected category
                    categoryProducts = selectedCategory.getProductCollection();
                    logger.debug(categoryProducts);
                    
                    // place category products in request scope
                    session.setAttribute("categoryProducts", categoryProducts);
                }
                userPath = "/catalog_page";
                break;
            }
            case ("/viewCart"): {
                String clear = request.getParameter("clear");
                logger.debug(clear);
                
                if ((clear != null) && clear.equals("true")) {

                    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                    logger.debug(cart);
                    
                    cart.clear();
                }

                userPath = "/cart_page";
                break;
            }
            case ("/checkout"): {
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                logger.debug(cart);
                
                // calculate total
                cart.calculateTotal(surcharge);
                
                // forward to checkout page and switch to a secure channel
                userPath = "/check_page";
                break;
            }
            case ("/chooseLanguage"): {
                // get language choice
                String language = request.getParameter("language");
                logger.debug(language);
                
                // place in request scope
                request.setAttribute("language", language);

                String userView = (String) session.getAttribute("view");
                logger.debug(userView);
                
                if ((userView != null)
                        && (!userView.equals("/index"))) {     // index.jsp exists outside 'view' folder
                    // so must be forwarded separately
                    userPath = userView;
                    logger.debug(userPath);
                    
                } else {

                    // if previous view is index or cannot be determined, send user to welcome page
                    try {
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    } catch (Exception ex) {
                        logger.error("Exception", ex);                        
                    }
                    return;
                }
            }
        }

        // use RequestDispatcher to forward request internally
        StringBuilder sb = new StringBuilder();
        sb.append("/WEB-INF/view");
        sb.append(userPath);
        sb.append(".jsp");
        String url = sb.toString();
        logger.debug(url);
                
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        
        logger.debug(userPath);
        logger.debug(session);
        logger.debug(cart);
        
        logger.info("Start Create Validator");
        Validator validator = new Validator();
        logger.info("End Create Validator");

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
                logger.debug(productId);
                
                if (!productId.isEmpty()) {

                    Product product = productFacade.find(Integer.parseInt(productId));
                    logger.debug(product);
                    
                    cart.addItem(product);
                }
                userPath = "/catalog_page";
                break;
            }
            case ("/updateCart"): {
                // get input from request
                String productId = request.getParameter("productId");
                String quantity = request.getParameter("quantity");
                logger.debug(productId);
                logger.debug(quantity);
                
                Product product = productFacade.find(Integer.parseInt(productId));
                logger.debug(product);
                
                cart.update(product, quantity);

                userPath = "/cart_page";
                break;
            }
            case ("/purchase"): {
                if (cart != null) {

                    // extract user data from request
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String cityRegion = request.getParameter("cityRegion");
                    String ccNumber = request.getParameter("creditcard");
                    
                    logger.debug(name);
                    logger.debug(email);
                    logger.debug(phone);
                    logger.debug(address);
                    logger.debug(cityRegion);
                    logger.debug(ccNumber);

                    // validate user data
                    boolean validationErrorFlag = false;
                    validationErrorFlag = validator.validateForm(name, email, phone, address, cityRegion, ccNumber, request);
                    logger.debug(validationErrorFlag);
                    
                    // if validation error found, return user to checkout
                    if (validationErrorFlag == true) {
                        request.setAttribute("validationErrorFlag", validationErrorFlag);
                        userPath = "/check_page";

                        // otherwise, save order to database
                    } else {

                        int orderId = orderManager.placeOrder(name, email, phone, address, cityRegion, ccNumber, cart);
                        logger.debug(orderId);
                        
                        if (orderId != 0) {
                            // in case language was set using toggle, get language choice before destroying session
                            Locale locale = (Locale) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
                            logger.debug(locale);
                            
                            String language = "";

                            if (locale != null) {

                                language = (String) locale.getLanguage();
                                logger.debug(language);                               
                            }

                            // dissociate shopping cart from session
                            cart = null;

                            // end session
                            session.invalidate();

                            if (!language.isEmpty()) {                       // if user changed language using the toggle,
                                // reset the language attribute - otherwise
                                request.setAttribute("language", language);  // language will be switched on confirmation page!
                            }

                            // get order details
                            Map orderMap = orderManager.getOrderDetails(orderId);
                            logger.debug(orderMap);
                            
                            // place order details in request scope
                            request.setAttribute("customer", orderMap.get("customer"));
                            request.setAttribute("products", orderMap.get("products"));
                            request.setAttribute("orderRecord", orderMap.get("orderRecord"));
                            request.setAttribute("orderedProducts", orderMap.get("orderedProducts"));

                            userPath = "/buying_page";
                            break;

                            // otherwise, send back to checkout page and display error
                        } else {
                            userPath = "/check_page";
                            request.setAttribute("orderFailureFlag", true);
                            break;
                        }
                    }

                }
            }
        }

        // use RequestDispatcher to forward request internally
        StringBuilder sb = new StringBuilder();
        sb.append("/WEB-INF/view");
        sb.append(userPath);
        sb.append(".jsp");
        String url = sb.toString();
        logger.debug(url);
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            logger.error("Exception", ex);            
        }
    }
}
