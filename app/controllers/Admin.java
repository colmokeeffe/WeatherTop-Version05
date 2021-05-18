package controllers;

import java.util.List;

import models.Reading;
import play.mvc.Controller;

public class Admin extends Controller
{
    public static void index()
    {
        List<Reading> readings = Reading.findAll();
        render("admin.html", readings);
    }
}