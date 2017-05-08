package com.asciipic.journalize.services.custom.statistics;

import com.asciipic.journalize.draw.Statistic;

public class DrawServiceImp {
    public String draw(int totalNumber, int partialNumber) {
        Statistic statistic = new Statistic();
        String result = statistic.drawShape(totalNumber, partialNumber);
        return result;
    }
}
