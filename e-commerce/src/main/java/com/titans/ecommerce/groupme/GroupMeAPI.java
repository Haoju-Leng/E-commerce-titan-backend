package com.titans.ecommerce.groupme;

public class GroupMeAPI {
    public static final String VERSION = "0.0.1";

    //just a test
    public static void main(String[] args)
    {
        Bot bot = new Bot("c329741c8d04bfcc46fb1bbe71");
        bot.sendTextMessage("1234");
        //bot.sendImage("Check out this image", "http://www.wtt.com/Pictures/WTT_logo_color_Jan2010.gif");
        //bot.sendLocation("Home", 38.8977, -77.0366, "The White House");
    }
}
