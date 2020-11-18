import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "hello.hbs");
        }, new HandlebarsTemplateEngine());


        get("/favorite_photos", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "favorite_photos.hbs");
        }, new HandlebarsTemplateEngine());


        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/greeting_card", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String recipient = request.queryParams("recipient");
            String sender = request.queryParams("sender");
            model.put("recipient", recipient);
            model.put("sender", sender);
            return new ModelAndView(model, "greeting_card.hbs");
        }, new HandlebarsTemplateEngine());

        get("/welcome", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("username", request.session().attribute("username"));
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        post("/welcome", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String inputtedUsername = request.queryParams("username");
            request.session().attribute("username", inputtedUsername);
            model.put("username", inputtedUsername);
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());
    }
}