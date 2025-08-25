package com.football;

import com.football.service.XmlImportService;

public class Main {
    public static void main(String[] args) {
        try {
            XmlImportService xmlService = new XmlImportService();
            int mCount = xmlService.importManagersFromResource("data/managers.xml");
            int pCount = xmlService.importPositionsFromResource("data/positions.xml");
            System.out.println("Imported managers: " + mCount + ", positions: " + pCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
