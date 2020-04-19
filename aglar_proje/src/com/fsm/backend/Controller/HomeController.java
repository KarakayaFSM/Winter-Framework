package com.fsm.backend.Controller;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Annotation.Controller;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Interfaces.MyHttpHandler;

import java.util.Arrays;
import java.util.List;

@Controller(path = "auctions")
public class HomeController implements MyHttpHandler {

    @Action(type = TYPE.GET)
    public String index() {
        return "Welcome to Auctions";
    }

    @Action(path = "getAllAuctions")
    public List<Auction> getAuctions() {
        return
                Arrays.asList(
                        new Auction(1, "Antik Vazo"),
                        new Auction(2, "Antik Tablo"),
                        new Auction(3, "Klasik Araba")
                );
    }


}
