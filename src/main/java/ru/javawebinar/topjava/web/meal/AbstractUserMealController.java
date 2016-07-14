package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by Nikita on 12.07.2016.
 */
public abstract class AbstractUserMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    @RequestMapping(value = "/meals", params = {"action=delete", "id"}, method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        int id = getId(request);
        log.info("Delete {}", id);
        service.delete(id, AuthorizedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", params = {"action=update", "id"}, method = RequestMethod.GET)
    public String setUpdate(HttpServletRequest request) {
        int id = getId(request);
        UserMeal userMeal = service.get(id, AuthorizedUser.id());
        log.info("Update {}", id);
        request.setAttribute("meal", userMeal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", params = "id=", method = RequestMethod.POST)
    public String create(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        log.info("Create {}", userMeal);
        service.save(userMeal, AuthorizedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", params = "id!=", method = RequestMethod.POST)
    public String update(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("UTF-8");
        final UserMeal userMeal = new UserMeal(
                getId(request),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        log.info("Update {}", userMeal);
        service.update(userMeal, AuthorizedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", params = "action=create", method = RequestMethod.GET)
    public String setCreate(HttpServletRequest request) {
        UserMeal meal = new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000);
        log.info("Create");
        request.setAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(HttpServletRequest request) {
        log.info("getAll");
        request.setAttribute("mealList", UserMealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "mealList";
    }

    @RequestMapping(value = "/meals", params = {"action=filter", "!id"}, method = RequestMethod.POST)
    public String filter(HttpServletRequest request) {
        int userId = AuthorizedUser.id();
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
        log.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);
        request.setAttribute("mealList", UserMealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : TimeUtil.MIN_DATE, endDate != null ? endDate : TimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX, AuthorizedUser.getCaloriesPerDay()
        ));
        return "mealList";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }

}
