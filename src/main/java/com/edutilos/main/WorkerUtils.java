package com.edutilos.main;

import java.util.List;

/**
 * Created by edutilos on 03/04/2017.
 */
public class WorkerUtils {
    public static boolean verifyFields(List<String> fields) {
        String idStr = fields.get(0),
                nameStr = fields.get(1),
                ageStr = fields.get(2),
                wageStr = fields.get(3);
        try {
            long id = Long.parseLong(idStr);
            int age = Integer.parseInt(ageStr);
            double wage = Double.parseDouble(wageStr);
            return true ;
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

        return false ;
    }
}
