package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryUserMealDAO;
import ru.javawebinar.topjava.dao.UserMealDAO;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Nikita on 14.06.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    private static UserMealDAO userMealDAO = new InMemoryUserMealDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to mealList");

        List<UserMealWithExceed> mealListWithExceed = UserMealsUtil.getFilteredWithExceeded(userMealDAO.listUserMeal(), LocalTime.MIDNIGHT, LocalTime.MAX, 2000);
        request.setAttribute("mealList", mealListWithExceed);
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
//        response.sendRedirect("mealList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), TimeUtil.getFORMATTER());
        String description = request.getParameter("description");
        int calories = Integer.valueOf(request.getParameter("calories"));
        userMealDAO.create(new UserMeal(dateTime, description, calories));
        response.sendRedirect("meals");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
